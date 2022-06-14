package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.dto.PushVO;

public interface PushDAO {

	List<PushVO> selectPushCriteria(Map<String, Object> searchMap) throws SQLException;

	int selectPushCriteriaTotalCount(Map<String, Object> searchMap) throws SQLException;
	
	// 메뉴바 알림 리스트 조회
	int selectReadCodeNCount(String userId);
	
	// 메뉴바 읽지 않은 알림 갯수 조회
	List<PushVO> selectMenuPushList(String userId);

	int insert(PushVO push) throws SQLException;

	int updateReadCode(List<Integer> targetPushNoList) throws SQLException;

	int delete(List<Integer> targetPushNoList) throws SQLException;


}
