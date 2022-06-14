package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.DbcnCriteria;
import kr.or.pms.dto.DbcnVO;

public class DbcnDAOImpl implements DbcnDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<DbcnVO> selectDbcnList(DbcnCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<DbcnVO>dbcnList = session.selectList("Dbcn-Mapper.selectDbcnList",cri, rowBounds);
		return dbcnList;
		
	}
	
	@Override
	public int countTotalDbcn(DbcnCriteria cri) throws SQLException {
		int cnt = session.selectOne("Dbcn-Mapper.countTotalDbcn", cri);
		return cnt;
	}
	
	@Override
	public DbcnVO selectDbcnByNo(int dbcnNo) throws SQLException {
		return session.selectOne("Dbcn-Mapper.selectDbcnByNo", dbcnNo);
	}
	
	@Override
	public int insert(DbcnVO dbcnVO) throws SQLException {
		return session.update("Dbcn-Mapper.insert", dbcnVO);
	}
	
	@Override
	public void update(DbcnVO dbcnVo) throws SQLException {
		session.update("Dbcn-Mapper.update", dbcnVo);
	}
	
	@Override
	public void delete(int dbcnNo) throws SQLException {
		session.update("Dbcn-Mapper.delete", dbcnNo);
	}

	@Override
	public List<DbcnVO> selectDbcnByStt(String stt) throws SQLException {
		List<DbcnVO> list = session.selectList("Dbcn-Mapper.selectDbcnByStt", stt);
		return list;
	}

}
