package kr.or.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.pms.command.WorkCriteria;
import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.ComCodeService;
import kr.or.pms.service.WorkService;


@Controller
@RequestMapping("/myPage/myWorks")
public class MyWorkController {
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private ComCodeService comCodeService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(HttpServletRequest request, Model model, WorkCriteria cri) throws Exception {
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		
		Map<String, Object> dataMap = workService.getMyWorkListById(cri, loginUser.getUserId());
		
		// 공통코드
		List<ComCodeVO> workSttList = comCodeService.getComCodeListByCodeGrp("WORKSTT");
		List<ComCodeVO> ipcrList = comCodeService.getComCodeListByCodeGrp("IPCR");
		model.addAttribute("workSttList", workSttList);
		model.addAttribute("ipcrList", ipcrList);
		
		model.addAttribute("dataMap", dataMap);
	}
	
}
