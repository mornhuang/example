package com.itsz.sht.struts.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.MySessionContext;
import com.itsz.sht.common.user.UserObject;

/**
 * 
 * @author pbxie
 */
public class SecurityFilter implements Filter {
	// the login page uri
	private static final String LOGIN_PAGE_URI = "Login.";

	// the logger object
	private Log logger = LogFactory.getLog(this.getClass());

	// a set of restricted resources
	private Set restrictedResources;

	/**
	 * Initializes the Filter.
	 */
	@SuppressWarnings("unchecked")
	public void init(FilterConfig filterConfig) throws ServletException {
		this.restrictedResources = new HashSet();
		this.restrictedResources.add("do");
		this.restrictedResources.add("html");
		this.restrictedResources.add("jsp");
	}

	/**
	 * Standard doFilter object.
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		this.logger.debug("doFilter");

		String contextPath = ((HttpServletRequest) req).getContextPath();
		String requestUri = ((HttpServletRequest) req).getRequestURI();
       
		this.logger.debug("contextPath = " + contextPath);
		this.logger.debug("requestUri = " + requestUri);
		// System.err.println("requestUri = " + requestUri);
		// System.err.println("contextPath =  " + contextPath);
		if ((this.contains(requestUri, contextPath) && !this.authorize((HttpServletRequest) req))
				|| !this.securityUrl(req)) {
			this.logger.info("authorization failed");
			((HttpServletRequest) req).getRequestDispatcher("/jsp/index.jsp").forward(req, res);
			// ((HttpServletRequest)
			// req).getRequestDispatcher("/main/login.jsf")
			// .include(req, res);
			//((HttpServletResponse) res).sendRedirect("index.do");
		} else {
			try{
				this.logger.debug("authorization succeeded");
				chain.doFilter(req, res);
			}catch(Exception e){
				logger.warn(e.getMessage());
			}
		}
	}

	public void destroy() {
	}
	
	/****
	 *可以通过
	 *http://10.100.1.161:8080/SRPS/main/login.action?('\u0023_memberAccess[\'allowStaticMethodAccess\']')(meh)=true&(aaa)(('\u0023context[\'xwork.MethodAccessor.denyMethodExecution\']\u003d\u0023foo')(\u0023foo\u003dnew%20java.lang.Boolean("false")))&(asdf)(('\u0023rt.exit(1)')(\u0023rt\u003d@java.lang.Runtime@getRuntime()))=1
	 * 来关闭服务
	 * securityUrl阻止此漏洞发生
	 * 
	 */
	@SuppressWarnings("unchecked")
	private boolean securityUrl(ServletRequest req){//解除外部请求调用java.lang.Runtime@getRuntime()关闭服务的安全隐患
		Enumeration e=((HttpServletRequest) req).getParameterNames();
		while(e.hasMoreElements()){
			String tem=e.nextElement().toString();
			if(tem.indexOf("getRuntime")>-1||tem.indexOf("xwork.MethodAccessor.denyMethodExecution")>-1) 
				return false;
		}
		return true;
	}

	private boolean contains(String value, String contextPath) {
		if (value.indexOf(LOGIN_PAGE_URI) > -1 || value.endsWith("Disclaimers.jsp") || value.endsWith("Data-Privacy-Policy.jsp")
				|| value.endsWith(".css")|| value.endsWith(".js")|| value.endsWith("FAQ.jsp") || value.indexOf("images") > -1) {
			return false;
		} else {
			return true;
		}
	}

	private boolean authorize(HttpServletRequest req) {
		UserObject user = (UserObject) req.getSession().getAttribute(Constants.USER);
		if(user==null){
			String sessionId=req.getParameter("sessionId");
			HttpSession session = MySessionContext.getSession(sessionId);
			if(null!=session)
			    user=(UserObject)session.getAttribute(Constants.USER);
		}
		if (user != null ) {
			// user logged in
			return true;
		} else {
			return false;
		}
	}
}
