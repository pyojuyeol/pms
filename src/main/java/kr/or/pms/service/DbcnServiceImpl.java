package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.DbcnCriteria;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dao.DbTypeDAO;
import kr.or.pms.dao.DbcnDAO;
import kr.or.pms.dto.DbTypeVO;
import kr.or.pms.dto.DbcnVO;
import kr.or.pms.util.JdbcPropertiesFileIoAPI;

public class DbcnServiceImpl implements DbcnService {

	private DbcnDAO dbcnDAO;
	public void setDbcnDAO(DbcnDAO dbcnDAO) {
		this.dbcnDAO = dbcnDAO;
	}
//	private DbDAO dbDAO;
//	public void setDbDAO(DbDAO dbDAO) {
//		this.dbDAO = dbDAO;
//	}
	private DbTypeDAO dbTypeDAO;
	public void setDbTypeDAO(DbTypeDAO dbTypeDAO) {
		this.dbTypeDAO = dbTypeDAO;
	}
	
	@Override
	public Map<String, Object> getDbcnList(DbcnCriteria cri) throws SQLException {
		
		List<DbcnVO> dbcnList = dbcnDAO.selectDbcnList(cri);
		
		Map<String,Object> dataMap = new HashMap<String, Object>();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(dbcnDAO.countTotalDbcn(cri));
		
		dataMap.put("dbcnList", dbcnList);
		dataMap.put("pageMaker", pageMaker);
		dataMap.put("type","dbcn");
		
		return dataMap;
	}

	@Override
	public List<DbcnVO> getDbcnBySttList(String stt) throws SQLException {
		return dbcnDAO.selectDbcnByStt(stt);
	}
	
	@Override
	public DbcnVO getDbcnByNo(int dbcnNo) throws SQLException {
		return dbcnDAO.selectDbcnByNo(dbcnNo);
	}

	@Override
	public int regist(DbcnVO dbcnVO) throws Exception {
		
		DbTypeVO dbTypeVO = dbTypeDAO.selectDbType(dbcnVO.getDbType());
		dbcnVO.setDbDriver(dbTypeVO.getDbDriver());
		
		dbcnVO = JdbcPropertiesFileIoAPI.callMakeJdbcProperties(dbcnVO);
		
		return dbcnDAO.insert(dbcnVO);
	}
	
	@Override
	public String modify(DbcnVO dbcnVO) throws SQLException {
		
		String message = "";

		String stt = dbcnVO.getSttCode();
		if(stt.equals("Y")) {
			message = "using";
		} else if(stt.equals("R")) {
			message = "reserved";			
		} else {
			
			try {
				JdbcPropertiesFileIoAPI.callMakeJdbcProperties(dbcnVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			dbcnDAO.update(dbcnVO);
			message = "success";
		}
		return message;
	}

	@Override
	public String remove(int dbcnNo) throws SQLException {
		
		DbcnVO dbcnVO = dbcnDAO.selectDbcnByNo(dbcnNo);
		String message = "";
		String stt = dbcnVO.getSttCode();
		if(stt.equals("Y")) {
			message = "using";
		} else if(stt.equals("R")) {
			message = "reserved";			
		} else {
			
			try {
				JdbcPropertiesFileIoAPI.callRemoveJdbcProperties(dbcnVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			dbcnDAO.delete(dbcnNo);
			message = "success";
		}
		return message;
		
		
		
	}

}
