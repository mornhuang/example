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
<%@ page import="org.springframework.web.context.*" %>
<%@ page import="org.springframework.web.context.support.*" %>
<%@ page import="org.crazyit.app.service.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Spring Bean的作用域</title>
</head>
<body>
<%
WebApplicationContext ctx = 
	WebApplicationContextUtils.getWebApplicationContext(application);
Person p1 = (Person)ctx.getBean("p");
Person p2 = (Person)ctx.getBean("p");
out.println((p1 == p2) + "<br/>");
out.println(p1);
%>
</body>
</html>