package com.manning.ajaxinaction.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * filter for restricting source of requests for ajax data feeds
 * 
 * @author dave
 */
public abstract class GenericSecurityFilter implements Filter {

	protected String rejectUrl=null;
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		rejectUrl=config.getInitParameter("rejectUrl");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

	  if (isValidRequest(request)){	
        chain.doFilter(request, response);
      }else if (rejectUrl!=null){
		RequestDispatcher dispatcher = request.getRequestDispatcher(rejectUrl);
        dispatcher.forward(request, response);
      }
	}
	
	protected abstract boolean isValidRequest(ServletRequest request);

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

}
