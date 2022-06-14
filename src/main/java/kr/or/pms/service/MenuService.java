package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.dto.MenuVO;

public interface MenuService {

	List<MenuVO> getMenuList(String mType, String authGrpCode) throws SQLException;
	
}
