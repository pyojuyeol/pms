package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.PushVO;

public interface PushService {
	
	// 리스트조회
	Map<String, Object> getList(Criteria cri, String userId) throws SQLException;
	
	// 메뉴바 알림 리스트 조회
	Map<String, Object> getMenuPushList(String userId) throws SQLException;
	
	int modifyReadCode(List<Integer> targetPushNoList) throws SQLException;

	int remove(List<Integer> targetPushNoList) throws SQLException;

	int regist(PushVO push) throws SQLException;

	int modifyReadCode(int pushNo) throws SQLException;

	void registPushList(List<PushVO> pushList) throws SQLException;



	
}
