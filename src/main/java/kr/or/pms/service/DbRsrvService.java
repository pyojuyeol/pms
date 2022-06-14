package kr.or.pms.service;

import java.util.Map;

import kr.or.pms.command.DbcnCriteria;
import kr.or.pms.dto.DbRsrvVO;

public interface DbRsrvService {

	Map<String, Object> getDbRsrvList(DbcnCriteria rsrvCri) throws Exception;

	String regist(DbRsrvVO dbRsrvVO, String pass) throws Exception;

	void remove(int dbRsrvNo, String modifier) throws Exception;


}
	