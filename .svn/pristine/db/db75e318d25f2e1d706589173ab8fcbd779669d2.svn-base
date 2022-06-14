package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.DbcnCriteria;
import kr.or.pms.dto.DbRsrvVO;

public class DbRsrvDAOImpl implements DbRsrvDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<DbRsrvVO> selectDbRsrvList(DbcnCriteria cri) throws SQLException {
		
		List<DbRsrvVO> dbRsrvList = null;
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		dbRsrvList = session.selectList("DbRsrv-Mapper.selectDbRsrvList",cri, rowBounds);	
		
		return dbRsrvList;
	}

	@Override
	public int selectTotalCountDbRsrv(DbcnCriteria cri) throws SQLException {
		int totalCount = session.selectOne("DbRsrv-Mapper.selectTotalCountDbRsrv", cri);
		return totalCount;
	}

	@Override
	public void insert(DbRsrvVO dbRsrvVO) throws SQLException {
		session.update("DbRsrv-Mapper.insert", dbRsrvVO);
	}

	@Override
	public DbRsrvVO selectDbRsrvByDbNo(int dbNo) throws SQLException {
		DbRsrvVO vo = session.selectOne("DbRsrv-Mapper.selectDbRsrvByDbNo", dbNo);
		return vo;
	}

	@Override
	public void delete(Map<String, Object> dataMap) throws SQLException {
		session.update("DbRsrv-Mapper.delete", dataMap);
	}

	@Override
	public DbRsrvVO selectDbRsrvByNo(int dbRsrvNo) throws SQLException {
		return session.selectOne("DbRsrv-Mapper.selectDbRsrvByNo", dbRsrvNo);
	}

	@Override
	public List<DbRsrvVO> selectTodayRsrvList() throws SQLException {
		return session.selectList("DbRsrv-Mapper.selectTodayRsrvList");
	}

	@Override
	public void updateStt(Map<String, Object> paramMap) throws SQLException {
		session.update("DbRsrv-Mapper.updateStt", paramMap);
	}

}
