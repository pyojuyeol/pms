package kr.or.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.pms.command.ProjectCriteria;
import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.FavoritesVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.ComCodeService;
import kr.or.pms.service.FavoritesService;
import kr.or.pms.service.ProjectService;


@Controller
@RequestMapping(value = "library")
public class LibraryController {

	@Autowired
	private ProjectService prjService;

	@Autowired
	private FavoritesService favoritesService;
	
	@Autowired
	private ComCodeService comCodeService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(HttpServletRequest request, ProjectCriteria cri, Model model) throws Exception {
		Map<String, Object> dataMap = prjService.getRecordList(cri);
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		
		List<FavoritesVO> favoritesList = favoritesService.getFavoritesList(loginUser.getUserId());
		
		List<ComCodeVO> prjRoleList = comCodeService.getComCodeListByCodeGrp("PRJROLE");
		model.addAttribute("prjRoleList", prjRoleList);
		
		model.addAttribute("favoritesList", favoritesList);
		model.addAttribute("dataMap", dataMap);
	}
	
	@RequestMapping(value = "/favorites/regist", method = RequestMethod.GET)
	public ResponseEntity<String> regist(HttpServletRequest request, String prjNo) throws Exception {
		ResponseEntity<String> entity = null;
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		
		FavoritesVO favorites = new FavoritesVO();
		favorites.setUserId(loginUser.getUserId());
		favorites.setPrjNo(Integer.parseInt(prjNo));
		
		favoritesService.registFavorites(favorites);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value = "/favorites/remove", method = RequestMethod.GET)
	public ResponseEntity<String> remove(HttpServletRequest request, String prjNo) throws Exception {
		ResponseEntity<String> entity = null;
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		
		FavoritesVO favorites = new FavoritesVO();
		favorites.setUserId(loginUser.getUserId());
		favorites.setPrjNo(Integer.parseInt(prjNo));
		
		favoritesService.removeFavorites(favorites);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
}
