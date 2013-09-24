<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2010, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="javax.naming.*,javax.swing.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 测试JNDI绑定 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
final String TEST_JNDI = "testName";
//初始化Context，使用InitialContext初始化Context
Context ctx = new InitialContext(); 
//随意创建一个对象
java.util.Date date = new java.util.Date();
//执行绑定
ctx.rebind(TEST_JNDI , date);
out.println("<h3>JNDI绑定成功！</h3>");
%>
</body>
</html>