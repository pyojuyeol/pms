package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.HistoryCriteria;
import kr.or.pms.command.HistoryPageMaker;
import kr.or.pms.dao.HistoryDAO;
import kr.or.pms.dto.HistoryVO;

public class HistoryServiceImpl implements HistoryService {
	
	private HistoryDAO historyDAO;
	public void setHistoryDAO(HistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}

	@Override
	public Map<String, Object> getList(HistoryCriteria cri) throws SQLException {
		List<HistoryVO> historyList = historyDAO.selectHistoryCriteria(cri);


		HistoryPageMaker pageMaker = new HistoryPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(historyDAO.selectHistoryCriteriaTotalCount(cri));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("historyList", historyList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	@Override
	public void regist(HistoryVO history) throws SQLException {
		
		historyDAO.insertHistory(history);
	}

}
