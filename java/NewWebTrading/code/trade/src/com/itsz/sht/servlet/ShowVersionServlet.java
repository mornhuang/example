/* ShowVersionServlet.java
 * createdate:2005-2-22
 * author:huxin
 */
package com.itsz.sht.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowVersionServlet extends HttpServlet {

	private static final long serialVersionUID = 2755217205924955439L;
	
	private static final String CONTENT_TYPE = "text/html";
	private static final String THREEG_PORTAL_VERSION="v3.1.3";
	private static final String UPDATE_DATE="2007.04.28";
	
	public void init() throws ServletException {
		
	}
	//Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();								
			out.print("---------- Portal Version -----------");
			out.print("<br>");
			out.print("  Portal Version: "+THREEG_PORTAL_VERSION+"("+UPDATE_DATE+")");
			out.print("<br>");			
			
	}

	//Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//Clean up resources
	public void destroy() {
	}
}
