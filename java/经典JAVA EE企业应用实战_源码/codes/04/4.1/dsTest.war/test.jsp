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
<%@ page import="javax.naming.*,java.sql.*,javax.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 测试容器管理的数据源 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//初始化Context，使用InitialContext初始化Context
Context ctx = new InitialContext(); 
//JBoss中实际JNDI名字必须在配置的JNDI之前加上java:/前缀
DataSource ds = (DataSource)ctx.lookup("java:/firstds");
//获取数据库连接
Connection conn = ds.getConnection();
//获取Statement
Statement stmt = conn.createStatement();
//执行查询，返回ResulteSet对象
ResultSet rs = stmt.executeQuery("select * from item");
while(rs.next())
{
	out.println(rs.getString(1) + "&nbsp;&nbsp;"
		+ rs.getString(2) + "<br/>");
}
rs.close();
stmt.close();
conn.close();
%>
</body>
</html>