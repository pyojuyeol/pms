package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.dto.DbRsrvVO;
import kr.or.pms.dto.DbVO;

public interface DbService {

	List<DbVO> getDbList() throws SQLException;

	void modify(DbVO dbVO) throws SQLException;

	void registRsrv(DbRsrvVO dbRsrvVO) throws SQLException;

	
}
