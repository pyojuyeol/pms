package kr.or.pms.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.pms.command.HistoryCriteria;
import kr.or.pms.dto.HistoryVO;

public interface HistoryService {
	
	Map<String, Object> getList(HistoryCriteria cri) throws SQLException;
	
	void regist(HistoryVO history) throws SQLException;
	
}
