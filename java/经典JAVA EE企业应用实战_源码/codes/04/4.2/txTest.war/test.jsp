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
<%@ page import="javax.naming.*,java.sql.*,javax.sql.*,javax.transaction.*"%>
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
//通过JNDI查找第一个数据源
DataSource oracleDs = (DataSource)ctx.lookup("java:/oracle");
//通过JNDI查找第二个数据源
DataSource otherDs = (DataSource)ctx.lookup("java:/other");
//通过JDNI查找获取JBoss服务器提供的事务管理器
UserTransaction tx = (UserTransaction)ctx
	.lookup("UserTransaction");
//获取数据库连接
Connection oracleConn = oracleDs.getConnection();
Connection otherConn = otherDs.getConnection();
Statement oracleStmt = null;
Statement otherStmt = null;
//开始事务
tx.begin();
try 
{
	//获取Statement
	oracleStmt = oracleConn.createStatement();
	otherStmt = otherConn.createStatement();
	//下面语句可以插入成功
	int result = oracleStmt.executeUpdate(
		"insert into dept values(50 , '研发部' , '广州')");
	out.println(result == 1 ? "第一个数据库插入成功!" : "失败!");
	//下面语句将失败（因为Oracle的dept表中已有主键为40的记录）
	otherStmt.executeUpdate(
		"insert into dept values(40 , '市场部' , '广州')");
	//提交事务
	tx.commit();
}
catch(SQLException ex)
{
	ex.printStackTrace();
	//回滚事务
	tx.rollback();
}
finally
{
	//关闭资源
	oracleStmt.close();
	otherStmt.close();
	oracleConn.close();
	otherConn.close();
}
%>
</body>
</html>