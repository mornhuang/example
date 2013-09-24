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
<%@ page import="java.sql.*,lee.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 用户在线信息 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
在线用户：
<table width="640" border="1">
<%
DbDao dd = new DbDao("com.mysql.jdbc.Driver"
	, "jdbc:mysql://localhost:3306/online_inf"
	, "root"
	, "32147");
//查询online_inf表（在线用户表）的全部记录
ResultSet rs = dd.query("select * from online_inf" , false);
while (rs.next())
{%>
<tr>
	<td><%=rs.getString(1)%>
	<td><%=rs.getString(2)%>
	<td><%=rs.getString(3)%>
	<td><%=rs.getString(4)%>
</tr>
<%}%>
</body>
</html>