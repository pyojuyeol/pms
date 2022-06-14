package kr.or.pms.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.pms.util.SystemLockerManager;

public class SystemLockerCheckFilter implements Filter {
	
	private List<String> exURLs;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		String path = httpReq.getServletPath();
		
		// 제외할 url들이면 그냥 흘려보내준다.
		if(excludeCheck(path)){
	    	chain.doFilter(request, response);
	    	return;
	    }	    
		
		if (SystemLockerManager.isLocked()) {

    		String url = "/pms/security/systemLock";    		
    		httpResp.sendRedirect(url);
    	} else {    		
    		chain.doFilter(request, response);
    	}
	    
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String excludePattern = fConfig.getInitParameter("excludedUrls");
		exURLs = Arrays.asList(excludePattern.split(","));
	}
	
	private boolean excludeCheck(String url) {
		boolean result = false;
		result = result || url.length() <= 1;
	    
		for (String exURL : exURLs) {
			result = result || url.contains(exURL);
		}
		return result;
	}

}
