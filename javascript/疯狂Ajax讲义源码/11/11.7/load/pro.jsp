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
//获取请求参数
String user = request.getParameter("user");
String[] books = request.getParameterValues("books");
//生成HTML字符串响应
out.println(user + ",您好，现在时间是:" + new java.util.Date());
out.println("<br />您喜欢的图书如下：");
out.println("<ol>");
for(int i = 0 ; i < books.length ; i++)
{
	out.println("<li>" + books[i] + "</li>");
}
out.println("</ol>");
%>