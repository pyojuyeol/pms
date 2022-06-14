package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.dto.MenuVO;

public class MenuDAOImpl implements MenuDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}


	@Override
	public List<MenuVO> selectMenuList(String mType, String authGrpCode) throws SQLException {

		HashMap<String, String> menuRequestData = new HashMap<String, String>();
		menuRequestData.put("mType", mType);
		menuRequestData.put("authGrpCode", authGrpCode);
		
		return session.selectList("Menu-Mapper.selectMenuList", menuRequestData);
	}

}
