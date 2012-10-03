package cn.itsz.newsim.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class SecurityFilterBase implements Filter {
	
	private static Log log = LogFactory.getLog(SecurityFilterBase.class);
	

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		String requestUri = ((HttpServletRequest) req).getRequestURI();
		log.info("Filter uri: " + requestUri);
		
		doMyFilter(req, res, chain, requestUri);
	}
	
	protected abstract void doMyFilter(ServletRequest req, ServletResponse res,
			FilterChain chain, String uri) throws IOException, ServletException ;
	
	
	protected boolean isContain(String v, String[] st) {
		for (String s : st){
			if (v.indexOf(s) > -1) {
				return true;
			} 
		}
		return false;
	}
	
	protected boolean authorize(HttpServletRequest req, String name) {
		return req.getSession().getAttribute(name) == null ? false : true;
	}
}
