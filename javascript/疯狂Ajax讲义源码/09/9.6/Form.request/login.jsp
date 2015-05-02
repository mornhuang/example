<%--
网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2010, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%
	//获取请求参数user
	String user = request.getParameter("user");
	String pass = request.getParameter("pass");
	//实际应用中此处可调用业务组件的业务方法。但此处仅仅示范Ajax交互，
	//所以直接要求user为yeeku，pass为123456才可正常登录。
	if(user.equals("yeeku")
		&& pass.equals("123456"))
	{
		out.println("登录成功！");
	}
	else
	{
		out.println("登录失败！");
	}
%>