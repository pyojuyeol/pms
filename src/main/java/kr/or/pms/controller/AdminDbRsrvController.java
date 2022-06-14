package kr.or.pms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.pms.command.DbRsrvCommand;
import kr.or.pms.dto.DbRsrvVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.DbRsrvService;

@Controller
@RequestMapping("/admin/dbRsrv")
public class AdminDbRsrvController {
	
	@Autowired
	private DbRsrvService dbRsrvService;
		
	@RequestMapping("/regist")
	@ResponseBody
	public ResponseEntity<String> regist(DbRsrvCommand cmd, @RequestParam(defaultValue="N") String pass, HttpSession session) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String role = loginUser.getAuthGrpCode();

		DbRsrvVO dbRsrvVO = cmd.toDbRsrvVO();
		dbRsrvVO.setWriter(loginUser.getUserId());
		
		String msg = "";
		
		if(role.equals("ROLE_ADMIN")) {
			msg = dbRsrvService.regist(dbRsrvVO, pass);
		} else {
			msg = "denied";
		}
		entity = new ResponseEntity<String>(msg, HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping("/cancel")
	@ResponseBody
	public ResponseEntity<String> cancel(String dbRsrvNo, HttpSession session) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		int dbRsrvNoInt = Integer.parseInt(dbRsrvNo);
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		dbRsrvService.remove(dbRsrvNoInt, loginUser.getUserId());
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		return entity;
	}

}
