package com.manning.ajaxinaction.web;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.manning.ajaxinaction.security.Encrypter;

/**
 * security filyter that checks the referrer header and only lets in values matching 
 * a given string or regexp
 * 
 * @author dave
 */
public class SecretHeaderSecurityFilter extends GenericSecurityFilter {

	private String headerName=null;
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		super.init(config);
		headerName=config.getInitParameter("headerName");
	}
	
	/* (non-Javadoc)
	 * @see com.manning.ajaxinaction.web.GenericSecurityFilter#isValidRequest(javax.servlet.ServletRequest)
	 */
	protected boolean isValidRequest(ServletRequest request) {
	  boolean valid=true;	
	  HttpServletRequest hrequest=(HttpServletRequest)request; 
	  if (headerName!=null){
	  	valid=false;
	  	String headerVal=hrequest.getHeader(headerName);
	  	Encrypter crypt=EncryptUtils.retrieve(hrequest);
	  	if (crypt!=null){
	  	  valid=crypt.compare(headerVal);
	  	}
	  }
 	  return valid;
	}

}
