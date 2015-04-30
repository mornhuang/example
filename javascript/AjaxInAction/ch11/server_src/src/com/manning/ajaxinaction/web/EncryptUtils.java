package com.manning.ajaxinaction.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.manning.ajaxinaction.security.Encrypter;

/**
 * @author dave
 */
public class EncryptUtils {

	public final static String SESSION_KEY="encrypter";

	/**
	 * @param hrequest
	 * @return
	 */
	public static Encrypter retrieve(HttpServletRequest request) {
		Encrypter result=null;
		HttpSession session=request.getSession();
		if (session!=null){
		  result=(Encrypter)(session.getAttribute(SESSION_KEY));
		}
		return result;
	}
	/**
	 * 
	 * @param request
	 * @param encrypter
	 */
	public static void store(HttpServletRequest request,Encrypter encrypter){
		HttpSession session=request.getSession(true);
		session.setAttribute(SESSION_KEY,encrypter);
	}

}
