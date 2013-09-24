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
<%@ page import="javax.naming.*,java.sql.*,javax.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>测试Tomcat数据源</title>
</head>
<body>
<%
//初始化Context，使用InitialContext初始化Context
Context ctx=new InitialContext(); 
/*
通过JNDI查找数据源，该JNDI为java:comp/env/jdbc/dstest，分成两个部分
java:comp/env是Tomcat固定的，Tomcat提供的JNDI绑定都必须加该前缀
jdbc/dstest是定义数据源时的数据源名
*/
DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/dstest");
//获取数据库连接
Connection conn=ds.getConnection();
//获取Statement
Statement stmt=conn.createStatement();
//执行查询，返回ResulteSet对象
ResultSet rs=stmt.executeQuery("select * from news_inf");
while(rs.next())
{
	out.println(rs.getString(1) 
		+ "\t" + rs.getString(2) + "<br/>");
}
%>
</body>
</html>