package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.dto.DbRsrvVO;
import kr.or.pms.dto.DbVO;
import kr.or.pms.dto.DbcnVO;

public class DbDAOImpl implements DbDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	@Override
	public List<DbVO> selectDbList() throws SQLException {
		return session.selectList("Db-Mapper.selectDbList");
	}
	@Override
	public DbVO selectDbByNm(String dbNm) throws SQLException {
		return session.selectOne("Db-Mapper.selectDbByNm", dbNm);
	}

	@Override
	public DbcnVO selectDbcnBydbNo(int dbNo) throws SQLException {
		return session.selectOne("Db-Mapper.selectDbcnBydbNo", dbNo);
	}
	@Override
	public DbcnVO selectDbcnBydbNm(String dbNm) throws SQLException {
		return session.selectOne("Db-Mapper.selectDbcnBydbNm", dbNm);
	}
	
	@Override
	public DbVO selectDbByDbcnNo(int dbcnNo) throws SQLException {
		return session.selectOne("Db-Mapper.selectDbByDbcnNo", dbcnNo);
	}
	@Override
	public void update(DbVO dbVO) throws SQLException {
		session.update("Db-Mapper.update", dbVO);
	}
	@Override
	public void insertRsrv(DbRsrvVO dbRsrvVO) throws SQLException {
		session.update("Db-Mapper.insertRsrv", dbRsrvVO);
	}

	

}
