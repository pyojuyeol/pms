package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.dto.DbRsrvVO;
import kr.or.pms.dto.DbVO;
import kr.or.pms.dto.DbcnVO;

public interface DbDAO {
	
	public List<DbVO> selectDbList() throws SQLException;
	public DbVO selectDbByNm(String dbNm) throws SQLException;

	public DbcnVO selectDbcnBydbNo(int dbNo) throws SQLException;
	public DbcnVO selectDbcnBydbNm(String dbNm) throws SQLException;

	public DbVO selectDbByDbcnNo(int dbcnNo) throws SQLException;

	public void insertRsrv(DbRsrvVO dbRsrvVO) throws SQLException;

	void update(DbVO dbVO) throws SQLException;




}
