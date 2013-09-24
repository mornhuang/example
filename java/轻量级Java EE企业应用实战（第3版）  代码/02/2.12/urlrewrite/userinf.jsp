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
<%
//获取请求参数
String user = request.getParameter("username");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> <%=user%>的个人信息 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//此处应该通过数据库读取该用户对应的信息
//此处只是模拟，因此简单输出：
out.println("现在时间是：" + new java.util.Date() + "<br/>");
out.println("用户名：" + user);
%>
</body>
</html>