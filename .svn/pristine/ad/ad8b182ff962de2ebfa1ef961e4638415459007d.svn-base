package kr.or.pms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import kr.or.pms.dto.UserVO;
import kr.or.pms.service.LogService;


public class SessionExpiredCheckFilter implements Filter {

	public void destroy() {}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpSession session = httpRequest.getSession();
//		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
//		
//		LogService logService = (LogService) context.getBean("logService");
//		
//		UserVO user = (UserVO) session.getAttribute("loginUser");
//		
//		try {
//			if (user != null) {
//				String loginUserId = user.getUserId();
//				logService.modifyLogoutLog(loginUserId);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String url="/WEB-INF/views/security/sessionOut.jsp";
		
		String message =" 중복 로그인이 확인되었습니다.\\n"
						+" 다시 로그인하면 다른 장치에서 로그인은 취소됩니다."; 
		
		request.setAttribute("message", message);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {}

}
