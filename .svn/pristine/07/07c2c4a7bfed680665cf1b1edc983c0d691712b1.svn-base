package kr.or.pms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.PageMaker;
import kr.or.pms.command.ProjectCriteria;
import kr.or.pms.dao.CloudDAO;
import kr.or.pms.dao.PrjUserDAO;
import kr.or.pms.dao.ProjectDAO;
import kr.or.pms.dao.PushDAO;
import kr.or.pms.dao.WorkDAO;
import kr.or.pms.dto.CloudDirectoryVO;
import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.PushVO;
import kr.or.pms.dto.WorkVO;

public class ProjectServiceImpl implements ProjectService {

	private ProjectDAO projectDAO;
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	private PrjUserDAO prjUserDAO;
	public void setPrjUserDAO(PrjUserDAO prjUserDAO) {
		this.prjUserDAO = prjUserDAO;
	}
	private CloudDAO cloudDAO;
	public void setCloudDAO(CloudDAO cloudDAO) {
		this.cloudDAO = cloudDAO;
	}
	private WorkDAO workDAO;
	public void setWorkDAO(WorkDAO workDAO) {
		this.workDAO = workDAO;
	}
	private PushDAO pushDAO;
	public void setPushDAO(PushDAO pushDAO) {
		this.pushDAO = pushDAO;
	}
	
	@Override
	public Map<String, Object> getProjectList(ProjectCriteria cri) throws Exception {
		List<ProjectVO> prjList = projectDAO.selectProjectCriteria(cri);
		
		if (prjList != null)
			for (ProjectVO prj : prjList) {
				addProjectUserList(prj);
				addPrjRate(prj);
			}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(projectDAO.selectProjectListTotalCount(cri));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("prjList", prjList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public Map<String, Object> getRecordList(ProjectCriteria cri) throws Exception {
		List<ProjectVO> recordList = projectDAO.selectRecordListCriteria(cri);
		
		if (recordList != null)
			for (ProjectVO prj : recordList) {
				addProjectUserList(prj);
				addPrjRate(prj);
			}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(projectDAO.selectProjectListTotalCount(cri));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("recordList", recordList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	@Override
	public List<ProjectVO> getJoinProjectList(String userId) throws Exception {
		List<ProjectVO> joinPrjList = projectDAO.selectJoinProjectById(userId);
		return joinPrjList;
	}
	
	@Override
	public List<ProjectVO> getTeamProjectList(String userId) throws Exception {
		List<ProjectVO> teamPrjList = projectDAO.selectTeamProjectListById(userId);
		return teamPrjList;
	}
	
	@Override
	public List<ProjectVO> getTeamProjectListByTno(int teamNo) throws Exception {
		List<ProjectVO> teamPrjList = projectDAO.selectTeamProjectListByTno(teamNo);
		
		if (teamPrjList != null)
			for (ProjectVO teamPrj : teamPrjList) {
				addProjectUserList(teamPrj);
				addPrjRate(teamPrj);
			}
		
		return teamPrjList;
	}
	
	@Override
	public List<ProjectVO> getTeamProceedingProjectListByTno(int teamNo) throws Exception {
		List<ProjectVO> teamPrjList = projectDAO.selectTeamProceedingProjectListByTno(teamNo);
		
		if (teamPrjList != null)
			for (ProjectVO teamPrj : teamPrjList) {
				addProjectUserList(teamPrj);
				addPrjRate(teamPrj);
			}
		
		return teamPrjList;
	}

	@Override
	public List<ProjectVO> getTeamCompleteProjectListByTno(int teamNo) throws Exception {
		List<ProjectVO> teamPrjList = projectDAO.selectTeamCompleteProjectListByTno(teamNo);
		
		if (teamPrjList != null)
			for (ProjectVO teamPrj : teamPrjList) {
				addProjectUserList(teamPrj);
				addPrjRate(teamPrj);
			}
		
		return teamPrjList;
	}
	
	
	
	@Override
	public ProjectVO getProject(int prjNo) throws Exception {
		ProjectVO prj = projectDAO.selectProjectByPno(prjNo);
		addProjectUserList(prj);
		
		return prj;
	}

	@Override
	public void regist(ProjectVO prj, String userId) throws Exception {
		
		int prjNo = prj.getPrjNo();

		// 프로젝트 정보 DB 저장
		projectDAO.insertProject(prj);
		
		// project cloud 생성.
		createProjectCloud(prj, userId);
		
		// 유저 추가
		List<PrjUserVO> AddPrjUserList = prj.getAddPrjUsers();
		if (AddPrjUserList != null) {
			for (PrjUserVO prjUsers : AddPrjUserList) {
				PrjUserVO prjUser = new PrjUserVO();
				prjUser.setPrjNo(prjNo);
				prjUser.setUserId(prjUsers.getUserId());
				prjUser.setPrjRole(prjUsers.getPrjRole());
				prjUser.setSttCode("w");
//				if (prjUsers.getPrjRole().equals("PL")) {
//	            	prjUser.setLeader("y");
//	            } else {
//	            	prjUser.setLeader("n");
//	            }
				
				 // 프로젝트 참여 유저 DB 저장
				prjUserDAO.insertPrjUser(prjUser);
				
				// 프로젝트 초대 알림
				projectInvitationPush(prjUsers, prj);
			}
		}
	}

	@Override
	public void modify(ProjectVO prj) throws Exception {
		
		// 프로젝트 정보 DB 저장
		projectDAO.updateProject(prj);
		
		int prjNo = prj.getPrjNo();
		
		
		// 컨트롤러에서 넘어온 추가 팀원 목록 (new Member)
		List<PrjUserVO> addUsers = prj.getAddPrjUsers();
		
		List<String> addUserIds = new ArrayList<>();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("화면단에서 넘어온 신규 팀원.");
		for (PrjUserVO prjUser : addUsers) {
			addUserIds.add(prjUser.getUserId());
			System.out.println(prjUser.getUserId());
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		// 현재 DB에 저장된 팀원 목록. (활성)
		List<String> enabledIds = new ArrayList<>();;
		List<PrjUserVO> enUsers = prjUserDAO.selectPrjUsersByPno(prjNo);
		for (PrjUserVO enUser : enUsers) {
			enabledIds.add(enUser.getUserId());
		}

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("DB에서 가져온 활성화 팀원.");
		for (PrjUserVO enUser : enUsers) {
			System.out.println(enUser.getUserId());
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		// 활성팀원 목록을 Iterator로 변환하여 각각의 아이디가 추가팀원 목록에 있는지 확인.
		// 없다면 해당 멤버 DB에서 삭제.
		Iterator<String> enIt = enabledIds.iterator();
		while (enIt.hasNext()) {
		    String enId = enIt.next(); 
	    	if (addUserIds.indexOf(enId) == -1) {
	    		System.out.println("enUser : " + enId + " 이 추가멤버 목록에 없다?   그럼 DB에서 삭제.");
		        PrjUserVO user = new PrjUserVO();
		        user.setPrjNo(prjNo);
		        user.setUserId(enId);
		        prjUserDAO.deletePrjUser(user);

		        // 프로젝트 멤버 제외 알림.
	    	}
		}
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		// remove 할 인덱스를 저장할 리스트.
		List<String> removeIndexs = new ArrayList<>();
		
		// 활성팀원 목록을 Iterator로 변환하여 각각의 아이디가 추가팀원 목록에 있는지 확인.
		// 있다면 역활이 변경 되었는지 확인.
		for (int i = 0; i < enUsers.size(); i++) {
			PrjUserVO enUser = enUsers.get(i);
			String enId = enUser.getUserId();
			String enRole = enUser.getPrjRole();
			
			// 추가 팀원 목록 (new Member)
			for (int j = 0; j < addUsers.size(); j++) {
				PrjUserVO addUser = addUsers.get(j);
				String addId = addUser.getUserId();
				String addRole = addUser.getPrjRole();
				
				// 아이디가 동일 할 때.
				if (enId.equals(addId)) {
					// 아이디가 같고 역할이 같을때.
					if (enRole.equals(addRole)) {
						System.out.println("newId :" + addId + " / enId : " + enId);
						System.out.println("아이디만 같네? 그럼 그냥 중복이니 addUsers에서 제외..");
						
						removeIndexs.add(j + "");
					}
					
					// 아이디가 같지만 역활이 변경 됬을때.
					if (!enRole.equals(addRole)) {
						System.out.println("addRole : " + addRole + " / enRole : " + enRole);
						System.out.println("addRole : " + addRole + " / enRole : " + enRole);
						System.out.println("아이디 같고 역할이 다르네?  역할 변경 업데이트 후 addUsers에서 제외.");
						
						// 아이디 활성 및 역할 변경 필요.
						PrjUserVO prjUser = new PrjUserVO();
						prjUser.setPrjNo(prjNo);
						prjUser.setUserId(enId);
						prjUser.setPrjRole(addRole);
						prjUserDAO.updatePrjUserRole(prjUser);
						
						projectChangeRolePush(prjUser, prj);
						
						removeIndexs.add(j + "");
					}
				}
			}
		}
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("활성 인원 기준 수정 및 중복 제거할 인원 인덱스");
		
		for (String removeIndex : removeIndexs) {
			System.out.println(removeIndex);
		}
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		// 활성 인원 기준 수정 및 중복 제거된 유저 addUsers 리스트에서 제외.
		Collections.sort(removeIndexs, Collections.reverseOrder());
		for (String removeIndex : removeIndexs) {
			addUsers.remove(Integer.parseInt(removeIndex));
		}
		removeIndexs.clear();
		
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("removeIndexs 클리어 후 확인.");
		if (removeIndexs != null) {
			for (String removeIndex : removeIndexs) {
				System.out.println(removeIndex);
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		// 현재 DB에 저장된 삭제된 팀원 목록. (비활성)
		List<String> disabledIds = new ArrayList<>();;
		List<PrjUserVO> disUsers = prjUserDAO.selectDisabledPrjUsersByPno(prjNo);
		for (PrjUserVO disUser : disUsers) {
			disabledIds.add(disUser.getUserId());
		}
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("DB에서 가져온 비활성 팀원.");
		
		for (PrjUserVO disUser : disUsers) {
			System.out.println(disUser.getUserId());
		}

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		// 추가팀원 목록 중 비활성 팀원 목록과 중복되는 인원이 있다면
		// 기존에 팀원이였다가 삭제된 케이스이기 때문에 재활성 & 역할 변경 후 추가팀원 목록에서 제외.
		
		// DB에 저장된 삭제된 팀원 목록. (비활성)
		for (int i = 0; i < addUsers.size(); i++) {
			PrjUserVO addUser = addUsers.get(i);
			String addId = addUser.getUserId();
			String addRole = addUser.getPrjRole();
			
			// 추가 팀원 목록 (new Member)
			for (int j = 0; j < disUsers.size(); j++) {
				PrjUserVO disUser = disUsers.get(j);
				String disId = disUser.getUserId();
				String disRole = disUser.getPrjRole();
				
				// 아이디가 동일 할 때.
				if (addId.equals(disId)) {
					// 아이디가 같고 역할이 동일 할때.
					if (addRole.equals(disRole)) {
						// 아이디 활성 업데이트 필요.
						System.out.println("newId : " + addId + " / disId : " + disId);
						System.out.println("아이디만 같네?  활성화 후  addUsers에서 제외..");
						
						// 아이디 활성 업데이트 필요.
						PrjUserVO prjUser = new PrjUserVO();
						prjUser.setPrjNo(prjNo);
						prjUser.setUserId(disId);
						prjUserDAO.updatePrjUser(prjUser);
						
						// 팀원 추가 로그 & 푸시
						projectInvitationPush(prjUser, prj);
						
						removeIndexs.add(i + "");
					}
					
					// 아이디가 같지만 역활이 변경 됬을때.
					if (!addRole.equals(disRole)) {
						// 아이디 활성 및 역할 변경 필요.
						System.out.println("addId : " + addId + " / disId : " + disId);
						System.out.println("addRole : " + addRole + " / disRole : " + disRole);
						
						System.out.println("아이디 같고 역할이 다르네? 활성화 및 역할 변경 후 addUsers에서 제외.");
						
						// 아이디 활성 및 역할 변경 필요.
						PrjUserVO prjUser = new PrjUserVO();
						prjUser.setPrjNo(prjNo);
						prjUser.setUserId(disId);
						prjUser.setPrjRole(addRole);
						prjUserDAO.updatePrjUser(prjUser);
						
						// 팀원 역활 로그 & 푸시
						projectInvitationPush(prjUser, prj);
						
						removeIndexs.add(i + "");
					}
				}
			}
		}
		
		// 비활성 인원 기준 수정 및 중복 제거된 유저 addUsers 리스트에서 제외.
		Collections.sort(removeIndexs, Collections.reverseOrder());
		for (String removeIndex : removeIndexs) {
			addUsers.remove(Integer.parseInt(removeIndex));
		}
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("insert 유저 목록.");
		
		for (PrjUserVO addUser : addUsers) {
			System.out.println(addUser.getUserId());
		}
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		// 남은 인원 DB 저장.
		for (PrjUserVO addUser : addUsers) {
			PrjUserVO prjUser = new PrjUserVO();
			prjUser.setPrjNo(prjNo);
			prjUser.setUserId(addUser.getUserId());
			prjUser.setPrjRole(addUser.getPrjRole());
			prjUserDAO.insertPrjUser(prjUser);
			
			// 팀원 추가 로그 & 푸시
			projectInvitationPush(prjUser, prj);
		}
		
	}

	
	@Override
	public void remove(int prjNo) throws Exception {
		projectDAO.deleteProject(prjNo);
	}
	
	@Override
	public int getSeqNextValue() throws Exception {
		int prjNo = projectDAO.getSeqNextValue();
		return prjNo;
	}
	
	// projectUserList 가져오기
	public List<PrjUserVO> getProjectUserList(int prjNo) throws Exception {
		List<PrjUserVO> prjUserList = prjUserDAO.selectPrjUsersByPno(prjNo);
		return prjUserList;
	}
	
	
	
	// ProjectVO에 User 목록 저장
	private void addProjectUserList(ProjectVO prj) throws Exception {
		
		if (prj == null) return;
		
		int prjNo = prj.getPrjNo();
		List<PrjUserVO> prjUserList = prjUserDAO.selectPrjUsersByPno(prjNo);
		
		prj.setPrjUserList(prjUserList);
	}


	// ProjectVO에 전체 진행도 저장
	public void addPrjRate(ProjectVO prj) throws Exception {
		double prjRate = 0;
		int subWorkSumRate = 0;
		List<WorkVO> workList = workDAO.selectWorkListByPno(prj.getPrjNo());
		if (workList != null) {
			for (WorkVO work : workList) {
				int subWorkListCnt = workDAO.selectSubWorkListByUno(work.getWorkNo()).size();
				if (subWorkListCnt == 0) {
					int workRate = work.getWorkRate();
					subWorkSumRate = subWorkSumRate + workRate;
				}
			}
			int subWorkTotalRate = workList.size() * 100;
			prjRate = subWorkSumRate / (double) subWorkTotalRate * 100;
			prj.setPrjRate((int) prjRate);
		}
	}

	// 프로젝트 초대 알림 메서드
	public void projectInvitationPush(PrjUserVO prjUser, ProjectVO prj) throws Exception {
		PushVO push = new PushVO();
		push.setFromWhere("프로젝트");				// 안내문 title로 나오는 내용
		push.setPrjNo(prj.getPrjNo() + "");
		push.setReceiver(prjUser.getUserId());		// 알림 받을 UserId
		push.setMessage(prj.getPrjNm() + " 프로젝트 멤버로 초대되었습니다.");
		push.setUrl("/prjSetting/invitation?prjNo=" + prj.getPrjNo() + "&userId=" + prjUser.getUserId());
		
		pushDAO.insert(push);
	}
	
	// 프로젝트 역활 변경 알림 메서드
	public void projectChangeRolePush(PrjUserVO prjUser, ProjectVO prj) throws Exception {
		PushVO push = new PushVO();
		push.setFromWhere("프로젝트");				// 안내문 title로 나오는 내용
		push.setPrjNo(prj.getPrjNo() + "");
		push.setReceiver(prjUser.getUserId());		// 알림 받을 UserId
		push.setMessage(prj.getPrjNm() + " 프로젝트 " + prjUser.getPrjRole() + "로 역할 변경 되었습니다..");
		push.setUrl("/prjSetting/invitation?prjNo=" + prj.getPrjNo() + "&userId=" + prjUser.getUserId());
		
		pushDAO.insert(push);
	}
	
	// 프로젝트 멤버 제외 알림 메서드
	public void projectRemevePush(PrjUserVO prjUser, ProjectVO prj) throws Exception {
		PushVO push = new PushVO();
		push.setFromWhere("프로젝트");				// 안내문 title로 나오는 내용
		push.setPrjNo(prj.getPrjNo() + "");
		push.setReceiver(prjUser.getUserId());		// 알림 받을 UserId
		push.setMessage(prj.getPrjNm() + " 프로젝트 멤버에서 제외되었습니다..");
		push.setUrl("/prjSetting/invitation?prjNo=" + prj.getPrjNo() );
		
		pushDAO.insert(push);
	}


	// 프로젝트 클라우드 생성.
	public void createProjectCloud(ProjectVO prj, String userId) throws Exception {
		// project cloud 생성.
		CloudDirectoryVO cdv = new CloudDirectoryVO();
		cdv.setDirNo(cloudDAO.selectCloudDirectorySeqNextValue());
		cdv.setDirNm(prj.getPrjNm());
		cdv.setDirPath(prj.getPrjNm());
		cdv.setCldKeyCode("1");
		cdv.setPrjNo(prj.getPrjNo());
		cdv.setUserId(userId);
		
		cloudDAO.insertProjectCloudDirectory(cdv);
	}
}
