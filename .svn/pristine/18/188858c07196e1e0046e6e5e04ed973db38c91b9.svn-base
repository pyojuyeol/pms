package kr.or.pms.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import kr.or.pms.dto.ConnectLogVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.LogService;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		
		User user = (User)authentication.getDetails();	
		
		UserVO loginUser = user.getUserVO();  
		HttpSession session = request.getSession();		
		session.setAttribute("loginUser", loginUser);
		
		session.setMaxInactiveInterval(24 * 60 * 60);
		
		String loginUserId = loginUser.getUserId();
		
		// 로그아웃 처리 여부 확인
		try {
			ConnectLogVO connectLog = logService.checkLogout(loginUserId);
			if (connectLog != null) {
				System.out.println("로그아웃 처리 되지 않음");
				logService.modifyLogoutLog(loginUserId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ConnectLogVO connect = new ConnectLogVO();
		connect.setUserId(loginUserId);
		connect.setCnKeyCode("login");
		connect.setIp(getClientIp(request));
		
		super.onAuthenticationSuccess(request, response, authentication);
		
		try {
			logService.registLoginLog(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	    }

	    return ip;
	}
	
}
