package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.dto.DbTypeVO;

public class DbTypeDAOImpl implements DbTypeDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<DbTypeVO> selectDbTypeList() throws SQLException {
		return session.selectList("DbType-Mapper.selectDbTypeList");
	}

	@Override
	public DbTypeVO selectDbType(String dbTypeId) throws SQLException {
		return session.selectOne("DbType-Mapper.selectDbType", dbTypeId);
	}

}
