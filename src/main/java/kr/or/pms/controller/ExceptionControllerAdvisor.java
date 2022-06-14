package kr.or.pms.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.or.pms.exception.ConsoleLog;
import kr.or.pms.exception.ProjectAccessException;

@ControllerAdvice
public class ExceptionControllerAdvisor {
	

	private static final Logger logger = 
			LoggerFactory.getLogger(ExceptionControllerAdvisor.class);
	
	@ExceptionHandler(ProjectAccessException.class)
	public String projectNotFoundExceptionPage(Exception e,Model model,HttpSession session){
		String url="error/exception";
		
		model.addAttribute("exception",e);
		model.addAttribute("url","/prjSetting/list.do");
		model.addAttribute("message",e.getMessage());
		
		return url;			
	}
	
	@ExceptionHandler(ConsoleLog.class)
	public String ConsoleLogPage(Exception e,Model model,HttpSession session){
		String url="error/alertPage";
		
		model.addAttribute("exception",e);
		model.addAttribute("url","/prjSetting/list.do");
		model.addAttribute("message",e.getMessage());
		
		return url;			
	}
//	
//	@ExceptionHandler(SQLException.class)
//	public String sqlExceptionPage(Exception e,Model model,HttpSession session){
//		String url="error/sqlException";
//		
//		//logger.error(e.toString());
//		e.printStackTrace();
//		
//		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
//		
//		model.addAttribute("exception",e);
//		model.addAttribute("user",loginUser!=null ? loginUser.getUserNm()+"님":"");
//		
//		
//		return url;		
//	}
//	
//	@ExceptionHandler(NullPointerException.class)
//	public String nullPointerExceptionPage(Exception e,Model model,HttpSession session){
//		String url="error/500";
//		
//		//logger.error(e.toString());
//		e.printStackTrace();
//		
//		model.addAttribute("exception",e);		
//		
//		return url;		
//	}
//	
//	@ExceptionHandler(Exception.class)
//	public String exceptionPage(Exception e,Model model,HttpSession session){
//		String url="error/500";
//		
//		//logger.error(e.toString());
//		e.printStackTrace();
//		
//		return url;		
//	}
}
