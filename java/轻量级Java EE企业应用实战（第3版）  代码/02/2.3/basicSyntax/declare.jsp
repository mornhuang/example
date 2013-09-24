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
	<title> 声明示例 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<!-- 下面是JSP声明部分 -->
<%!
//声明一个整形变量
public int count;
//声明一个方法
public String info()
{
	return "hello";
}
%>
<body>
<%
//将count的值输出后再加1
out.println(count++);
%>
<br/>
<%
//输出info()方法的返回值
out.println(info());
%>
</body>
</html>