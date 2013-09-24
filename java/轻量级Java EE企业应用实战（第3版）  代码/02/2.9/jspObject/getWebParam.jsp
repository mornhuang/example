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
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>application测试</title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//从配置参数中获取驱动
String driver = application.getInitParameter("driver");
//从配置参数中获取数据库url
String url = application.getInitParameter("url");
//从配置参数中获取用户名
String user = application.getInitParameter("user");
//从配置参数中获取密码
String pass = application.getInitParameter("pass");
//注册驱动
Class.forName(driver);
//获取数据库连接
Connection conn = DriverManager.getConnection(url,user,pass);
//创建Statement对象
Statement stmt = conn.createStatement();
//执行查询
ResultSet rs = stmt.executeQuery("select * from news_inf");
%>
<table bgcolor="#9999dd" border="1" width="480">
<%
//遍历结果集
while(rs.next())
{
%>
	<tr>
		<td><%=rs.getString(1)%></td>
		<td><%=rs.getString(2)%></td>
	</tr>
<%
}
%>
<table>
</body>
</html>