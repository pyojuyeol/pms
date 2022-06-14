package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.DbcnCriteria;
import kr.or.pms.dto.DbcnVO;

public interface DbcnDAO {
	
	public List<DbcnVO> selectDbcnList(DbcnCriteria cri) throws SQLException;
	
	public int countTotalDbcn(DbcnCriteria cri) throws SQLException;
	
	int insert(DbcnVO dbcnVO) throws SQLException;

	public void update(DbcnVO dbcnVo) throws SQLException;
	
	public void delete(int dbcnNo) throws SQLException;
	
	public DbcnVO selectDbcnByNo(int dbcnNo) throws SQLException;

	List<DbcnVO> selectDbcnByStt(String stt) throws SQLException;



	
}
