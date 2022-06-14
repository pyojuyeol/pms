package kr.or.pms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.PageMaker;
import kr.or.pms.command.WorkCriteria;
import kr.or.pms.dao.HistoryDAO;
import kr.or.pms.dao.PushDAO;
import kr.or.pms.dao.WorkDAO;
import kr.or.pms.dao.WorkLogDAO;
import kr.or.pms.dto.HistoryVO;
import kr.or.pms.dto.PushVO;
import kr.or.pms.dto.WorkLogVO;
import kr.or.pms.dto.WorkVO;

public class WorkServiceImpl implements WorkService {

	private WorkDAO workDAO;
	public void setWorkDAO(WorkDAO workDAO) {
		this.workDAO = workDAO;
	}
	private WorkLogDAO workLogDAO;
	public void setWorkLogDAO(WorkLogDAO workLogDAO) {
		this.workLogDAO = workLogDAO;
	}
	private HistoryDAO historyDAO;
	public void setHistoryDAO(HistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}
	private PushDAO pushDAO;
	public void setPushDAO(PushDAO pushDAO) {
		this.pushDAO = pushDAO;
	}

	@Override
	public Map<String, Object> getWorkListCriteriaByPno(WorkCriteria cri, int prjNo) throws Exception {
		List<WorkVO> workList = workDAO.selectWorkListCriteriaByPno(cri, prjNo);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(workDAO.selectWorkListTotalCountCriteriaByPno(cri, prjNo));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("workList", workList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	@Override
	public Map<String, Object> getMyWorkListById(WorkCriteria cri, String userId) throws Exception {
		List<WorkVO> myWorkList = workDAO.selectMyWorkListById(cri, userId);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(workDAO.selectMyWorkListTotalCountById(cri, userId));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("myWorkList", myWorkList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	@Override
	public int getWorkListTotalCountByPno(WorkCriteria cri, int prjNo) throws Exception {
		int totalCount = workDAO.selectWorkListTotalCountByPno(prjNo);
		return totalCount;
	}
	
	@Override
	public List<WorkVO> getWorkListByPno(int prjNo) throws Exception {
		List<WorkVO> workList = workDAO.selectWorkListByPno(prjNo);
		return workList;
	}
	
	@Override
	public List<WorkVO> getWorkListHomeById(String userId) throws Exception {
		List<WorkVO> workList = workDAO.selectWorkListHomeById(userId);
		return workList;
	}
	
	@Override
	public List<WorkVO> getWorkListProjectHomeById(Map<String, String> map) throws Exception {
		List<WorkVO> workList = workDAO.selectWorkListProjectHomeById(map);
		return workList;
	}

	@Override
	public int getMyWorkListTotalCountById(WorkCriteria cri, String userId) throws Exception {
		int totalCount = workDAO.selectMyWorkListTotalCountById(cri, userId);
		return totalCount;
	}

	
	@Override
	public WorkVO getWork(int workNo) throws Exception {
		WorkVO work = workDAO.selectWorkByWno(workNo);
		
		// 하위일감 목록 주입
		List<WorkVO> subWorkList = workDAO.selectSubWorkListByUno(workNo);
		if (subWorkList != null && subWorkList.size() > 0) work.setSubWorkList(subWorkList);
		
		// 해당 일감 로그 내역 주입
		List<WorkLogVO> workLogList = workLogDAO.selectWorkLogListByWno(workNo);
		
		work.setWorkLogList(workLogList);
		
		return work;
	}
	
	@Override
	public int getPnoByWno(int workNo) throws Exception {
		int prjNo = workDAO.selectPnoByWno(workNo);
		return prjNo;
	}

	@Override
	public void regist(WorkVO work) throws Exception {
		// workNo 시퀀스
		int workNo = workDAO.getSeqNextValue();
		work.setWorkNo(workNo);

		// work url 셋팅
		String workUrl = work.getWorkUrl();
		work.setWorkUrl(workUrl + workNo);
		
		// DB 저장
		workDAO.insertWork(work);

		// History 저장
		addHistory(work);
		
		// 알림
		workRegistPush(work);
		
		// 상위일감 존재 여부 확인
		int upWorkNo = work.getUpWork();
		if (upWorkNo > 0) {
			WorkVO upWork = workDAO.selectWorkByWno(upWorkNo);
			// 하위일감 등록 시 상위일감 진척도 초기화 후 하위일감 들의 총 진척도로 변경.
			checkRateUpWork(work, upWork);
			// 상위일감이 있을 때 날짜 변경시 상위일감 날짜에 반영
			checkDateUpWork(work, upWork);
		}
		
	}

	@Override
	public void modify(WorkVO work) throws Exception {
		// DB 저장
		workDAO.updateWork(work);
		
		// 히스토리 DB 저장
		addHistory(work);

		// 알림
		workModifyPush(work);
		
		// 상위일감 존재 여부 확인
		int upWorkNo = work.getUpWork();
		if (upWorkNo > 0) {
			WorkVO upWork = workDAO.selectWorkByWno(upWorkNo);
			// 하위일감 등록 시 상위일감 진척도 초기화 후 하위일감 들의 총 진척도로 변경.
			checkRateUpWork(work, upWork);
			// 상위일감이 있을 때 날짜 변경시 상위일감 날짜에 반영
			checkDateUpWork(work, upWork);
		}
	}

	@Override
	public void remove(int workNo) throws Exception {
		workDAO.deleteWork(workNo);
	}

	@Override
	public List<WorkVO> getWorkListFromGantt(WorkVO workVO) throws Exception {
		List<WorkVO> workList = workDAO.selectWorkListFromGantt(workVO);
		return workList;
	}

	
	
	// 일감 등록 & 수정 시 히스토리 등록 메서드
	public void addHistory(WorkVO work) throws Exception {
		String workStatus = "";
		String sttCode = work.getSttCode();
		if (sttCode.equals("r")) workStatus = "요청"; 
		if (sttCode.equals("p")) workStatus = "진행"; 
		if (sttCode.equals("f")) workStatus = "피드백"; 
		if (sttCode.equals("c")) workStatus = "완료"; 
		if (sttCode.equals("h")) workStatus = "보류"; 
		
		HistoryVO history = new HistoryVO();
		if (work.getWorkRegister() != null) {
			history.setUserId(work.getWorkRegister());
		} else {
			history.setUserId(work.getWorkUpdater());
		}
		history.setPrjNo(work.getPrjNo());
		history.setTitle("일감 #" + work.getWorkNo() + " (" + workStatus + ") : " + work.getWorkTitle());
		history.setContent(work.getWorkComment());
		history.setFromWhere("WORK");
		history.setUrl("/project/work/detail.do?workNo=" + work.getWorkNo());
		
		historyDAO.insertHistory(history);
	}

	
	// 하위 일감 날짜 변경 시 상위일감 날짜에 반영하는 메서드
	public void checkDateUpWork(WorkVO work, WorkVO upWork) throws Exception {
		
		// 수정 로그 기록을 위해 상위일감 기한 업데이트 전 일자 변수에 담기
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String oldBgnDtStr = formatter.format(upWork.getBgnDt());
		String oldEndDtStr = formatter.format(upWork.getEndDt());
		
		// 하위일감의 기한이 상위일감 보다 클때 하위일감 기한으로 변경
		// 하위일감의 시작일자가 상위일감 보다 빠른 경우
		if (upWork.getBgnDt().after(work.getBgnDt())) upWork.setBgnDt(work.getBgnDt());
		// 하위일감의 종료일자가 상위 일감보다 늦는 경우
		if (upWork.getEndDt().before(work.getEndDt())) upWork.setEndDt(work.getEndDt());
		
		// 하위일감 기한이 상위일감 보다 좁을때 하위일감 기한으로 변경
		// 모든 하위일감과 비교하여 그중 가장 빠른 일자 || 가장 늦는 일자로 변경
		List<WorkVO> subWorkList = workDAO.selectSubWorksByUno(work.getUpWork());
		if (subWorkList != null) {
			// 배열에서 가장 작은 수와 가장 큰 수를 구하는 공식을 활용하여 가장 빠른날과 늦는 날을 구함.
			Date minDate = work.getBgnDt();
			Date maxDate = work.getEndDt();
			for (WorkVO subWork : subWorkList) {
				if (minDate.after(subWork.getBgnDt())) minDate = subWork.getBgnDt();
				if (maxDate.before(subWork.getEndDt())) maxDate = subWork.getEndDt();
			}
			upWork.setBgnDt(minDate);
			upWork.setEndDt(maxDate);
			
			// 전후 비교를 위한 변경 된 상위 일감의 시작일자 종료일자 저장
			String newBgnDtStr = formatter.format(minDate);
			String newEndDtStr = formatter.format(maxDate);
			
			// 상위일감 기한 수정 발생 시 수정 로그 기록
			WorkLogVO workLog = new WorkLogVO();
			workLog.setWorkNo(upWork.getWorkNo());
			workLog.setUpdater(upWork.getWorkUpdater());
			
			// 시작일자 로그 DB 저장
			if (!newBgnDtStr.equals(oldBgnDtStr)) {
				workLog.setWhat("시작일자");
				workLog.setBefore(oldBgnDtStr);
				workLog.setAfter(newBgnDtStr);
				workLogDAO.insertWorkLog(workLog);
			}
			// 종료일자 로그 DB 저장
			if (!newEndDtStr.equals(oldEndDtStr)) {
				workLog.setWhat("완료기한");
				workLog.setBefore(oldEndDtStr);
				workLog.setAfter(newEndDtStr);
				workLogDAO.insertWorkLog(workLog);
			}
		}
		// 상위 일감 날짜 반영
		workDAO.updateWork(upWork);
		
	}
	
	// 하위일감 등록 시 상위일감 진척도 초기화 후 하위일감 합산 진척도로 변경
	public void checkRateUpWork(WorkVO work, WorkVO upWork) throws Exception {
		double upWorkRate = 0;
		int upWorkNo = work.getUpWork();
		List<WorkVO> subWorkList = workDAO.selectSubWorksByUno(upWorkNo);
		int subWorkSumRate = 0;
		for (WorkVO subWork : subWorkList) {
			int subWorkRate = subWork.getWorkRate();
			subWorkSumRate = subWorkSumRate + subWorkRate;
		}
		int subWorkTotalRate = subWorkList.size() * 100;
		upWorkRate = subWorkSumRate / (double)subWorkTotalRate * 100;
		upWork.setWorkRate((int)upWorkRate);
		workDAO.updateWork(upWork);
	}
	
	
	// 일감 등록 알림 메서드
	public void workRegistPush(WorkVO work) throws Exception {
		PushVO push = new PushVO();
		push.setFromWhere("일감");				// 안내문 title로 나오는 내용
		push.setPrjNo(work.getPrjNo() + "");
		push.setReceiver(work.getWorker());		// 알림 받을 UserId
		
		String userNm = workDAO.selectUserNmById(work.getWorker());
		push.setMessage(userNm + " 님이 " + work.getWorkTitle() + " 일감 담당자가 되었습니다.");
		push.setUrl("/project/work/detail.do?workNo=" + work.getWorkNo() + "&from=push");
		pushDAO.insert(push);
	}
	// 일감 수정 알림 메서드
	public void workModifyPush(WorkVO work) throws Exception {
		PushVO push = new PushVO();
		push.setFromWhere("일감");				// 안내문 title로 나오는 내용
		push.setPrjNo(work.getPrjNo() + "");
		push.setReceiver(work.getWorker());		// 알림 받을 UserId
		push.setMessage("담당 일감의 정보가 수정되었습니다.");
		push.setUrl("/project/work/detail.do?workNo=" + work.getWorkNo() + "&from=push");
		pushDAO.insert(push);
	}

	@Override
	public List<Integer> getSttCodeWorkCountListByPno(int prjNo) throws Exception {
		List<Integer> cntList = new ArrayList<>();
		int rCnt = workDAO.selectRequestWorkCountByPno(prjNo);
		int pCnt = workDAO.selectProceedingWorkCountByPno(prjNo);
		int fCnt = workDAO.selectFeedbackWorkCountByPno(prjNo);
		int cCnt = workDAO.selectCompleteWorkCountByPno(prjNo);
		int hCnt = workDAO.selectHoldWorkCountByPno(prjNo);
		
		cntList.add(rCnt);
		cntList.add(pCnt);
		cntList.add(fCnt);
		cntList.add(cCnt);
		cntList.add(hCnt);
		
		return cntList;
	}




}
