package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.DbcnCriteria;
import kr.or.pms.dto.DbcnVO;

public interface DbcnService {

	Map<String, Object> getDbcnList(DbcnCriteria cri) throws SQLException;

	List<DbcnVO> getDbcnBySttList (String str) throws SQLException;

	DbcnVO getDbcnByNo(int dbcnNo) throws SQLException;

	int regist(DbcnVO dbcnVO) throws Exception;
	
	String modify(DbcnVO dbcnVO) throws SQLException;

	String remove(int dbcnNo) throws SQLException;



	
}
