<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2012, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 增加Cookie </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//获取请求参数
String name = request.getParameter("name");
//以获取到的请求参数为值，创建一个Cookie对象
Cookie c = new Cookie("username" , name);
//设置Cookie对象的生存期限
c.setMaxAge(24 * 3600);
//向客户端增加Cookie对象
response.addCookie(c);
%>
</body>
</html>