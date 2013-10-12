package org.crazyjava.inputtip.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.IOException;

import org.crazyjava.inputtip.service.*;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class GetDetailServlet extends HttpServlet 
{
	public void service(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, java.io.IOException
	{
		//通过WebApplicationContextUtils工具类获得Spring容器，
		//通过Spring容器访问容器中的业务逻辑组件
		TipService ts = (TipService)WebApplicationContextUtils
			.getWebApplicationContext(getServletContext())
			.getBean("tipService");
		//设置解码用的字符集，Ajax的POST请求都采用UTF-8的编码集
		request.setCharacterEncoding("utf-8");
		//获取请求参数
		String model = request.getParameter("model");
		response.setContentType("text/html;charset=GBK");
		PrintWriter out =  response.getWriter();
		//输出响应
		out.println(ts.getDescByModel(model));
	}
}