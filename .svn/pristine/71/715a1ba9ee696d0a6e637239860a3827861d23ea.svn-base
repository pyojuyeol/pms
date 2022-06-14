package kr.or.pms.dao;

import java.util.List;
import java.util.Map;

import kr.or.pms.command.WorkCriteria;
import kr.or.pms.dto.WorkVO;

public interface WorkDAO {

	// 일감 목록 조회
	List<WorkVO> selectWorkListCriteriaByPno(WorkCriteria cri, int prjNo) throws Exception;
	List<WorkVO> selectWorkListByPno(int prjNo) throws Exception;
	List<WorkVO> selectWorkListHomeById(String userId) throws Exception;
	List<WorkVO> selectWorkListProjectHomeById(Map<String, String> map) throws Exception;
	
	// 하위 일감 목록 조회
	List<WorkVO> selectSubWorkListByUno(int upWork) throws Exception;
	List<WorkVO> selectSubWorksByUno(int upWork) throws Exception;
	
	// MyWork 일감 목록 조회
	List<WorkVO> selectMyWorkListById(WorkCriteria cri, String userId) throws Exception;
	// Mywork 일감 총 개수 조회
	int selectMyWorkListTotalCountById(WorkCriteria cri, String userId) throws Exception;
	
	// 일감 전체 개수
	int selectWorkListTotalCountCriteriaByPno(WorkCriteria cri, int prjNo) throws Exception;
	int selectWorkListTotalCountByPno(int prjNo) throws Exception;
	
	// 일감 상태 별 개수 조회
	int selectRequestWorkCountByPno(int prjNo) throws Exception;
	int selectProceedingWorkCountByPno(int prjNo) throws Exception;
	int selectFeedbackWorkCountByPno(int prjNo) throws Exception;
	int selectCompleteWorkCountByPno(int prjNo) throws Exception;
	int selectHoldWorkCountByPno(int prjNo) throws Exception;
	
	// 일감 정보 조회
	WorkVO selectWorkByWno(int workNo) throws Exception;
	
	// 일감 추가
	void insertWork(WorkVO work) throws Exception;
	
	// 일감 수정
	void updateWork(WorkVO work) throws Exception;
	
	// 일감 마감
	void endWork(WorkVO work) throws Exception;
	
	// 일감 삭제
	void deleteWork(int workNo) throws Exception;
	
	// 시퀀스
	int getSeqNextValue() throws Exception;
	
	// 프로젝트 번호 조회
	int selectPnoByWno(int workNo) throws Exception;
	
	// 간트차트
	public List<WorkVO> selectWorkListFromGantt(WorkVO workVO) throws Exception;
	
	// 담당자 이름 조회
	public String selectUserNmById(String userId) throws Exception;
}
