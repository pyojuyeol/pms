package kr.or.pms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.pms.util.MyUtil;

public class MaintainSiteMeshOpen extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getServletPath();
		if(url.indexOf(".open") > 0) {
			request.setAttribute("open", "1");
		}
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		//파라미터 구성
//		Object open = request.getAttribute("open");
//		String openStr = null;
//		if(open != null) {
//			openStr = (String) open;
//		}
//		
//		if("1".equals(openStr)) {
//			if( request.getRequestURL().indexOf("redirect") >= 0) {
//				MyUtil.alert(request.getRequestURL() );
//				response.sendRedirect( request.getRequestURL().toString().replace(".do", ".open") );
//			}
//		}
//		super.postHandle(request, response, handler, modelAndView);
	}
}
