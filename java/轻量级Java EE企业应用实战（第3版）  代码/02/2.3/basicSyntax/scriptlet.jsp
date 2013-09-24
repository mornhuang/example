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
	<title> 小脚本测试 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<table bgcolor="#9999dd" border="1" width="300px">
<!-- Java脚本，这些脚本会对HTML的标签产生作用 -->
<%
for(int i = 0 ; i < 10 ; i++)
{
%>
	<!-- 上面的循环将控制<tr>标签循环 -->
	<tr>
		<td>循环值:</td>
		<td><%=i%></td>
	</tr>
<%
}
%>
<table>
</body>
</html>