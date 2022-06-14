package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.HistoryCriteria;
import kr.or.pms.dto.HistoryVO;

public interface HistoryDAO {
	
	List<HistoryVO> selectHistoryCriteria(HistoryCriteria cri) throws SQLException;
	int selectHistoryCriteriaTotalCount(HistoryCriteria cri) throws SQLException;
	
	void insertHistory(HistoryVO history) throws SQLException;
}
