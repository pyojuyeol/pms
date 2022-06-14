package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.dao.DbDAO;
import kr.or.pms.dao.DbcnDAO;
import kr.or.pms.dto.DbRsrvVO;
import kr.or.pms.dto.DbVO;
import kr.or.pms.dto.DbcnVO;

public class DbServiceImpl implements DbService {

	private DbDAO dbDAO;
	public void setDbDAO(DbDAO dbDAO) {
		this.dbDAO = dbDAO;
	}
	private DbcnDAO dbcnDAO;
	public void setDbcnDAO(DbcnDAO dbcnDAO) {
		this.dbcnDAO = dbcnDAO;
	}
	
	@Override
	public List<DbVO> getDbList() throws SQLException {
		return dbDAO.selectDbList();
	}

	@Override
	public void registRsrv(DbRsrvVO dbRsrvVO) throws SQLException {
		dbDAO.insertRsrv(dbRsrvVO);
	}
	
	@Override
	public void modify(DbVO dbVO) throws SQLException {
	
		int dbNo = dbVO.getDbNo();
		DbcnVO oldDbcn = dbDAO.selectDbcnBydbNo(dbNo);
		oldDbcn.setSttCode("N");
		dbcnDAO.update(oldDbcn);
		
		int dbcnNo = dbVO.getDbcnNo();
		DbcnVO newDbcn = dbcnDAO.selectDbcnByNo(dbcnNo);
		newDbcn.setSttCode("Y");
		dbcnDAO.update(newDbcn);
		
		dbDAO.update(dbVO);
	

		
	}



}
