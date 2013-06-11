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
 * 在逃人口查询，获得照片路径
 */
public class PeopleImgServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 获得请求参数，身份证号码
		String idno = request.getParameter("idno");
		// 调用后台Dao执行查询
		PeopleDao dao = new PeopleDaoImpl();
		String path = dao.get(idno).getPic();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 响应请求，将查询结果写到客户端
		out.write(path);
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	public void init() throws ServletException {
	}
	public PeopleImgServlet() {
		super();
	}
	public void destroy() {
		super.destroy();
	}

}
