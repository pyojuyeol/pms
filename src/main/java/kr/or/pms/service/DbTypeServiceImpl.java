package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.dao.DbTypeDAO;
import kr.or.pms.dto.DbTypeVO;

public class DbTypeServiceImpl implements DbTypeService {

	private DbTypeDAO dbTypeDAO;
	public void setDbTypeDAO(DbTypeDAO dbTypeDAO) {
		this.dbTypeDAO = dbTypeDAO;
	}

	@Override
	public List<DbTypeVO> getDbTypeList() throws SQLException {
		return dbTypeDAO.selectDbTypeList();
	}

	@Override
	public DbTypeVO getDbType(String dbTypeId) throws SQLException {
		return dbTypeDAO.selectDbType(dbTypeId);
	}




}
