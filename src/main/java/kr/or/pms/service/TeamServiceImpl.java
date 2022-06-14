package kr.or.pms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dao.ProjectDAO;
import kr.or.pms.dao.PushDAO;
import kr.or.pms.dao.TeamDAO;
import kr.or.pms.dao.TmemDAO;
import kr.or.pms.dao.TmemLogDAO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.PushVO;
import kr.or.pms.dto.TeamVO;
import kr.or.pms.dto.TmemLogVO;
import kr.or.pms.dto.TmemVO;

public class TeamServiceImpl implements TeamService {

	private TeamDAO teamDAO;
	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}
	private TmemDAO tmemDAO;
	public void setTmemDAO(TmemDAO tmemDAO) {
		this.tmemDAO = tmemDAO;
	}
	private TmemLogDAO tmemLogDAO;
	public void setTmemLogDAO(TmemLogDAO tmemLogDAO) {
		this.tmemLogDAO = tmemLogDAO;
	}
	private PushDAO pushDAO;
	public void setPushDAO(PushDAO pushDAO) {
		this.pushDAO = pushDAO;
	}
	private ProjectDAO projectDAO;
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
	@Override
	public Map<String, Object> getTeamList(Criteria cri) throws Exception {
		List<TeamVO> teamList = teamDAO.selectTeamCriteria(cri);
		
		if (teamList != null)
			for (TeamVO team : teamList) {
				// TeamVO에 팀 멤버 주입
				addTeamMemberList(team);
				// 진행중, 완료된, 보류중인 프로젝트 개수 주입
				int teamNo = team.getTeamNo();
				team.setCompleteCnt(projectDAO.selectCompletePrjListCount(teamNo));
				team.setProceedingCnt(projectDAO.selectProceedingPrjListCount(teamNo));
				team.setHoldCnt(projectDAO.selectHoldPrjListCount(teamNo));
				
				// 팀 프로젝트 리스트 주입
				List<ProjectVO> teamPrjList = projectDAO.selectTeamProjectListByTno(team.getTeamNo());
				team.setPrjList(teamPrjList);
			}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(teamDAO.selectTeamListTotalCount(cri));

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("teamList", teamList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	
	@Override
	public List<TeamVO> getTeamList() throws Exception {
		List<TeamVO> teamList = teamDAO.selectTeamList();
		
		if (teamList != null)
			for (TeamVO team : teamList) {
				addTeamMemberList(team);
				// 진행중, 완료된, 보류중인 프로젝트 개수 주입
				int teamNo = team.getTeamNo();
				team.setCompleteCnt(projectDAO.selectCompletePrjListCount(teamNo));
				team.setProceedingCnt(projectDAO.selectProceedingPrjListCount(teamNo));
				team.setHoldCnt(projectDAO.selectHoldPrjListCount(teamNo)); 
			}
		
		return teamList;
	}
	
	@Override
	public TeamVO getTeam(int teamNo) throws Exception {
		TeamVO team = teamDAO.selectTeamByTno(teamNo);
		addTeamMemberList(team);
		addLogList(team);
		
		// 진행중, 완료된, 보류중인 프로젝트 개수 주입
		team.setCompleteCnt(projectDAO.selectCompletePrjListCount(teamNo));
		team.setProceedingCnt(projectDAO.selectProceedingPrjListCount(teamNo));
		team.setHoldCnt(projectDAO.selectHoldPrjListCount(teamNo)); 
		
		return team;
	}
	
	@Override
	public void regist(TeamVO team, String register) throws Exception {
		
		teamDAO.insertTeam(team);
		
		// 팀원 추가
		if (team.getAddMembers() != null) {
			addTeamMembers(team, register);
		}
	}
	
	@Override
	public void modify(TeamVO team) throws Exception {
		
		// 팀 정보 DB 수정.
		teamDAO.updateTeam(team);
		
		// 컨트롤러에서 넘어온 추가 팀원 목록
		List<String> userIds = team.getAddMembers();
		
		// 현재 DB에 저장된 팀원 목록. (활성)
		List<String> enabledIds = new ArrayList<>();;
		List<TmemVO> enTmems = tmemDAO.selectTeamMembersByTno(team.getTeamNo());
		for (TmemVO enTmem : enTmems) {
			enabledIds.add(enTmem.getUserId());
		}
		
		// 활성팀원 목록을 Iterator로 변환하여 각각의 아이디가 추가팀원 목록에 있는지 확인.
		// 존재 한다면 중복이기 때문에 추가팀원 목록에서 삭제. 
		// 없다면 해당 멤버 DB에서 삭제(비활성).
		Iterator<String> enIt = enabledIds.iterator();
		while (enIt.hasNext()) {
		    String enId = enIt.next(); 
	    	if (userIds.indexOf(enId) != -1) {
	    		userIds.remove(userIds.indexOf(enId));
		    } else {
		        TmemVO tmem = new TmemVO();
		        tmem.setTeamNo(team.getTeamNo());
		        tmem.setUserId(enId);
		        tmemDAO.deleteTeamMember(tmem);
		        
		        // 팀원 제외 로그 & 푸시
		        removeTmemLog(team.getTeamNo(), enId);
		        removePush(team, enId);
		    }
		}
		
		// 현재 DB에 저장된 삭제된 팀원 목록. (비활성)
		List<String> disabledIds = new ArrayList<>();;
		List<TmemVO> disTmems = tmemDAO.selectTeamMembersByTnoD(team.getTeamNo());
		for (TmemVO disTmem : disTmems) {
			disabledIds.add(disTmem.getUserId());
		}
		
		// remove 할 인덱스를 저장할 리스트.
		List<String> removeIndexs = new ArrayList<>();
		
		// 추가팀원 목록 중 비활성 팀원 목록과 중복되는 인원이 있다면
		// 기존에 팀원이였다가 삭제된 케이스이기 때문에 재활성 하고 추가팀원 목록에서 제외.
		Iterator<String> disIt = userIds.iterator();
		while (disIt.hasNext()) {
		    String disId = disIt.next();
	    	if (disabledIds.indexOf(disId) != -1) {
		        TmemVO tmem = new TmemVO();
		        tmem.setTeamNo(team.getTeamNo());
		        tmem.setUserId(disId);
		        tmem.setLeader("n");
		        tmemDAO.updateTeamMember(tmem);
		        
				// 팀원 추가 로그 & 푸시
				addTmemLog(team.getTeamNo(), disId);
				addPush(team, disId);
		        
		        // 해당 리스트가 for loop 를 도는 중에 리스트 Element를 add & remove하면
		        // java.util.ConcurrentModificationException 발생.
		        // 때문에 remove 할 인덱스를 다른 배열에 넣어 두고 for문 종료 후 삭제 진행.
//		        userIds.remove(userIds.indexOf(dId));
		        removeIndexs.add(userIds.indexOf(disId) + "");
	    	} 
		}
		
		// 오름차순으로 remove(index)를하면 인덱스가 앞으로 땡겨지며
		// 다음 인덱스 삭제에 오류가 발생하므로 내림차순으로 정렬 후 remove 
		Collections.sort(removeIndexs, Collections.reverseOrder());
		for (String removeIndex : removeIndexs) {
			userIds.remove(Integer.parseInt(removeIndex));
		}
		
		// 남은 인원 DB 저장.
		for (String addId : userIds) {
			TmemVO tmem = new TmemVO();
			tmem.setTeamNo(team.getTeamNo());
			tmem.setUserId(addId);
			tmem.setLeader("n");
			tmemDAO.insertTeamMember(tmem);
			
			// 팀원 추가 로그 & 푸시
			addTmemLog(team.getTeamNo(), addId);
			addPush(team, addId);
		}
	}
	
	@Override
	public void remove(int teamNo) throws Exception {
		teamDAO.deleteTeam(teamNo);
	}
	
	@Override
	public int getSeqNextValue() throws SQLException {
		int teamNo = teamDAO.getSeqNextValue();
		return teamNo;
	}
	
	
	// 팀 조회 시 TeamVO에 해당 팀원 목록 주입
	private void addTeamMemberList(TeamVO team) throws Exception {
		
		if (team == null) return;
		
		int teamNo = team.getTeamNo();
		List<TmemVO> tmemList = tmemDAO.selectTeamMembersByTno(teamNo);
		
		team.setTeamMemberList(tmemList);
	}
	
	// 팀 조회 시 TeamVO에 해당 Log 목록 주입
	private void addLogList(TeamVO team) throws Exception {
		
		if (team == null) return;
		
		int teamNo = team.getTeamNo();
		List<TmemLogVO> tmemLogList = tmemLogDAO.selectTeamMemberLogsByTno(teamNo);
		
		team.setLogList(tmemLogList);
	}

	// 팀 생성 시 팀원 추가 메서드
	public void addTeamMembers(TeamVO team, String register) throws Exception {
		for (String userId : team.getAddMembers()) {
			TmemVO tmem = new TmemVO();
			tmem.setTeamNo(team.getTeamNo());
			tmem.setUserId(userId);
			if (userId.equals(register)) {
				tmem.setLeader("y");
            } else {
            	tmem.setLeader("n");
            }
			tmemDAO.insertTeamMember(tmem);
			
			// 팀원 추가 로그 & 푸시
			addTmemLog(team.getTeamNo(), userId);
			addPush(team, userId);
			
		}
	}
	
	// 팀원 추가 알림
	public void addPush(TeamVO team, String userId) throws Exception {
		PushVO push = new PushVO();
		push.setFromWhere("팀");		// 안내문 title로 나오는 내용
		push.setReceiver(userId);		// 알림 받을 UserId
		push.setMessage(team.getTeamNm() + " 팀 멤버로 초대되었습니다.");
		push.setUrl("/team/detail?teamNo=" + team.getTeamNo() + "&from=push");
		pushDAO.insert(push);
	}
	
	// 팀원 제외 알림
	public void removePush(TeamVO team, String userId) throws Exception {
		PushVO push = new PushVO();
		push.setFromWhere("팀");		// 안내문 title로 나오는 내용
		push.setReceiver(userId);		// 알림 받을 UserId
		push.setMessage(team.getTeamNm() + " 팀 멤버에서 제외되었습니다.");
		push.setUrl("/team/detail?teamNo=" + team.getTeamNo() + "&from=push");
		pushDAO.insert(push);
	}
	
	// 팀원 추가 로그 DB 저장
	public void addTmemLog(int teamNo, String userId) throws Exception {
		TmemLogVO tmemLog = new TmemLogVO();
		tmemLog.setTeamNo(teamNo);
		tmemLog.setUserId(userId);
		tmemLogDAO.insertTeamMemberLog(tmemLog);
	}
	
	// 팀원 삭제 로그 DB 저장
	public void removeTmemLog(int teamNo, String userId) throws Exception {
		TmemLogVO tmemLog = new TmemLogVO();
		tmemLog.setTeamNo(teamNo);
		tmemLog.setUserId(userId);
		tmemLogDAO.deleteTeamMemberLog(tmemLog);
	}
	
	
}
