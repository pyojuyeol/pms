package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.HistoryCriteria;
import kr.or.pms.dto.HistoryVO;

public class HistoryDAOImpl implements HistoryDAO {
	
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<HistoryVO> selectHistoryCriteria(HistoryCriteria cri) throws SQLException {
		
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		List<HistoryVO> historyList = session.selectList("History-Mapper.selectHistoryCriteria",cri,rowBounds);	
			
		return historyList;
	}

	@Override
	public int selectHistoryCriteriaTotalCount(HistoryCriteria cri) throws SQLException {
		
		int count = session.selectOne("History-Mapper.selectHistoryCriteriaTotalCount",cri);
		return count;
	}

	@Override
	public void insertHistory(HistoryVO history) throws SQLException {
		session.update("History-Mapper.insertHistory",history);

	}

}
