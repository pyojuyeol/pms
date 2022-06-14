package kr.or.pms.service;

import java.util.List;
import java.util.Map;

import kr.or.pms.command.WorkCriteria;
import kr.or.pms.dto.WorkVO;

public interface WorkService {

	// 일감 목록 조회
	Map<String, Object> getWorkListCriteriaByPno(WorkCriteria cri, int prjNo) throws Exception;
	List<WorkVO> getWorkListByPno(int prjNo) throws Exception;
	List<WorkVO> getWorkListHomeById(String userId) throws Exception;
	List<WorkVO> getWorkListProjectHomeById(Map<String, String> map) throws Exception;
	
	// 내 일감 목록 조회
	Map<String, Object> getMyWorkListById(WorkCriteria cri, String userId) throws Exception;
	// 내 일감 총 개수 조회
	int getMyWorkListTotalCountById(WorkCriteria cri, String userId) throws Exception;
	
	// 일감 상태 별 개수 조회
	List<Integer> getSttCodeWorkCountListByPno(int prjNo) throws Exception;
	
	// 일감 상세조회
	public WorkVO getWork(int workNo) throws Exception;
	
	// 일감 전체 개수
	int getWorkListTotalCountByPno(WorkCriteria cri, int prjNo) throws Exception;
	
	// 일감 등록
	void regist(WorkVO work) throws Exception;
	
	// 일감 수정
	void modify(WorkVO work) throws Exception;
	
	// 일감 삭제
	void remove(int workNo) throws Exception;
	
	// 프로젝트 번호 조회
	int getPnoByWno(int workNo) throws Exception; 
	
	// 간트
	public List<WorkVO> getWorkListFromGantt(WorkVO workVO) throws Exception;
	
}
