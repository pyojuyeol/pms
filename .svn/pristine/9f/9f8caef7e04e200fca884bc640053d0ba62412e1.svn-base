package kr.or.pms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.DbcnCriteria;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dao.DbRsrvDAO;
import kr.or.pms.dao.DbcnDAO;
import kr.or.pms.dto.DbRsrvVO;
import kr.or.pms.dto.DbcnVO;

public class DbRsrvServiceImpl implements DbRsrvService {

	private DbRsrvDAO dbRsrvDAO;
	public void setDbRsrvDAO(DbRsrvDAO dbRsrvDAO) {
		this.dbRsrvDAO = dbRsrvDAO;
	}
	
	private DbcnDAO dbcnDAO;
	public void setDbcnDAO(DbcnDAO dbcnDAO) {
		this.dbcnDAO = dbcnDAO;
	}

	
	@Override
	public Map<String, Object> getDbRsrvList(DbcnCriteria cri) throws Exception {
		
		List<DbRsrvVO> dbRsrvList = dbRsrvDAO.selectDbRsrvList(cri);
		
		Map<String,Object> dataMap = new HashMap<String, Object>();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(dbRsrvDAO.selectTotalCountDbRsrv(cri));
		
		dataMap.put("dbRsrvList", dbRsrvList);
		dataMap.put("pageMaker",pageMaker);
		dataMap.put("type","dbRsrv");
		
		return dataMap;
	}

	@Override
	public String regist(DbRsrvVO dbRsrvVO, String pass) throws Exception {
		
		String msg = "";
		//이전에 예약된 내역 체크
		DbRsrvVO oldDbRsrvVO = dbRsrvDAO.selectDbRsrvByDbNo(dbRsrvVO.getDbNo());
		
		if(oldDbRsrvVO != null) {
			if(!pass.equals("Y")) {
				return msg = "reserved";
			}
			//이전에 예약된 Dbcn 상태 변경
			int oldDbcnNo = oldDbRsrvVO.getDbcnNo();
			DbcnVO oldDbcn = dbcnDAO.selectDbcnByNo(oldDbcnNo);
			oldDbcn.setSttCode("N");
			dbcnDAO.update(oldDbcn);
			
			//이전 예약 테이블 상태변경
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("modifier", dbRsrvVO.getWriter());
			dataMap.put("dbRsrvNo", oldDbRsrvVO.getDbRsrvNo());
			dbRsrvDAO.delete(dataMap);
		} 
		//예약할 Dbcn 상태 변경
		int dbcnNo = dbRsrvVO.getDbcnNo();
		DbcnVO dbcn = dbcnDAO.selectDbcnByNo(dbcnNo);
		dbcn.setSttCode("R");
		dbcnDAO.update(dbcn);
		
		//예약
		dbRsrvDAO.insert(dbRsrvVO);
		msg = "success";
		
		return msg;
	}
	
	@Override
	public void remove(int dbRsrvNo, String modifier) throws Exception {
		
		DbRsrvVO dbRsrvVO = dbRsrvDAO.selectDbRsrvByNo(dbRsrvNo);
		//예약할 Dbcn 상태 변경
		int dbcnNo = dbRsrvVO.getDbcnNo();
		DbcnVO dbcn = dbcnDAO.selectDbcnByNo(dbcnNo);
		dbcn.setSttCode("N");
		dbcnDAO.update(dbcn);
		
		//예약 테이블 상태변경
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("modifier", modifier);
		dataMap.put("dbRsrvNo", dbRsrvNo);
		dbRsrvDAO.delete(dataMap);
		
	}

	


}
