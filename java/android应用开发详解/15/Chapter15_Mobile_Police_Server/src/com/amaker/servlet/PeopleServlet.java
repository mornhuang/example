package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.PeopleDao;
import com.amaker.dao.impl.PeopleDaoImpl;

/**
 * 
 * @author 郭宏志
 * 在逃人口查询Servlet
 */
public class PeopleServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得请求参数
		String idno = request.getParameter("idno");
		// 调用后台Dao实现查询
		PeopleDao dao = new PeopleDaoImpl();
		String msg = dao.getString(idno);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 将查询信息写到客户端
		out.write(msg);
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	
	public void init() throws ServletException {
	}
	
	public PeopleServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
