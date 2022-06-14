package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.dao.MenuDAO;
import kr.or.pms.dto.MenuVO;

public class MenuServiceImpl implements MenuService {

	private MenuDAO menuDAO;
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}


	@Override
	public List<MenuVO> getMenuList(String mType, String authGrpCode) throws SQLException {
		
		List<MenuVO> menuList = null;
		
		menuList = menuDAO.selectMenuList(mType, authGrpCode);
		
		return menuList;
	}

}
