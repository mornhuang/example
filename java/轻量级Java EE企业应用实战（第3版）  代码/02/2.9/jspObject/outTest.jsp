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
	<title> out测试 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//注册数据库驱动
Class.forName("com.mysql.jdbc.Driver");
//获取数据库连接
Connection conn = DriverManager.getConnection(
	"jdbc:mysql://localhost:3306/javaee","root","32147");
//创建Statement对象
Statement stmt = conn.createStatement();
//执行查询，获取ResultSet对象
ResultSet rs = stmt.executeQuery("select * from news_inf");
%>
<table bgcolor="#9999dd" border="1" width="400">
<%
//遍历结果集
while(rs.next())
{
	//输出表格行
	out.println("<tr>");
	//输出表格列
	out.println("<td>");
	//输出结果集的第二列的值
	out.println(rs.getString(1));
	//关闭表格列
	out.println("</td>");
	//开始表格列
	out.println("<td>");
	//输出结果集的第三列的值
	out.println(rs.getString(2));
	//关闭表格列
	out.println("</td>");
	//关闭表格行
	out.println("</tr>");
}
%>
<table>
</body>
</html>