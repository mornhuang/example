<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2010, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=GBK" language="java" errorPage="login.jsp"%>
<%@ page import="javax.naming.*"%>
<%@ page import="org.crazyit.service.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 添加图书的结果 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
request.setCharacterEncoding("GBK");
String book = request.getParameter("bookName");
String price = request.getParameter("price");
Context ctx = new InitialContext();
SecurityService ss = (SecurityService)ctx
	.lookup("SecurityService#org.crazyit.service.SecurityService");
//调用EJB的方法，调用该方法必须拥有admin角色。
ss.addItem(book , Double.parseDouble(price));
%>
<h3>图书添加成功！</h3>
跳转到<a href="bookList.jsp">图书列表页</a>
</body>
</html>