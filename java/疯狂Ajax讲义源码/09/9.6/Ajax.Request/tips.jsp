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
	//获取请求参数favorite
	String hdchar = request.getParameter("favorite");
	System.out.println(hdchar);
	//如果请求参数是apple的前几个字符，则输出apple
	if ("apple".startsWith(hdchar))
	{
		out.println("apple");
	}
	//如果请求参数是banana的前几个字符，则输出banana
	else if("banana".startsWith(hdchar))
	{
		out.println("banana");
	}
	//如果请求参数是peach的前几个字符，则输出peach
	else if("peach".startsWith(hdchar))
	{
		out.println("peach");
	}
	//否则将输出other fruit
	else
	{
		out.println("other fruit");
	}
%>