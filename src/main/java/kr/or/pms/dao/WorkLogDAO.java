package kr.or.pms.dao;

import java.util.List;

import kr.or.pms.dto.WorkLogVO;

public interface WorkLogDAO {

	// 일감 로그 리스트 조회 By WorkNo
	public List<WorkLogVO> selectWorkLogListByWno(int workNo) throws Exception;
	
	// 일감 로그 추가
	public void insertWorkLog(WorkLogVO workLog) throws Exception;
	
	// 유저명 검색
	public String selectUserNmById(String userId) throws Exception;
	
}
