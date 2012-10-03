package com.taifook.adminportal.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowVersionServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html";

	private static final String THREEG_PORTAL_VERSION = "v2.0.10";

	private static final String UPDATE_DATE = "2006.08.21";

	public void init() throws ServletException {

	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print("---------- Web AdminPortal Version -----------");
		out.print("<br>");
		out.print("  Portal Version: " + THREEG_PORTAL_VERSION + "("
				+ UPDATE_DATE + ")");
		out.print("<br>");
		out.flush();
		out.close();
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Clean up resources
	public void destroy() {
	}
}
