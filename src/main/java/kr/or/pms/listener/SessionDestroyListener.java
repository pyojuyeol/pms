package kr.or.pms.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import kr.or.pms.dto.UserVO;
import kr.or.pms.service.LogService;


public class SessionDestroyListener implements HttpSessionListener {
	private LogService logService;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		logService = (LogService) context.getBean("logService");
		
		UserVO user = (UserVO) session.getAttribute("loginUser");
		
		try {
			if (user != null) {
				String loginUserId = user.getUserId();
				logService.modifyLogoutLog(loginUserId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
