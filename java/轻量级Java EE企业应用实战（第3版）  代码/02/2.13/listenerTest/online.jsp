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
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 用户在线信息 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
在线用户：
<table width="400" border="1">
<%
Map<String , String> online = (Map<String , String>)application
	.getAttribute("online");
for (String sessionId : online.keySet())
{%>
<tr>
	<td><%=sessionId%>
	<td><%=online.get(sessionId)%>
</tr>
<%}%>
</body>
</html>