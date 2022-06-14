package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.DbcnCriteria;
import kr.or.pms.dto.DbRsrvVO;

public interface DbRsrvDAO {

	List<DbRsrvVO> selectDbRsrvList(DbcnCriteria cri) throws SQLException;

	int selectTotalCountDbRsrv(DbcnCriteria cri) throws SQLException;

	void insert(DbRsrvVO dbRsrvVO) throws SQLException;

	DbRsrvVO selectDbRsrvByDbNo(int dbNo) throws SQLException;

	void delete(Map<String, Object> dataMap) throws SQLException;

	DbRsrvVO selectDbRsrvByNo(int dbRsrvNo) throws SQLException;

	List<DbRsrvVO> selectTodayRsrvList() throws SQLException;

	void updateStt(Map<String, Object> paramMap) throws SQLException;

}
