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
	<title>登录页面</title>
</head>
<body>
<form action="login.action" method="post">
	<table align="center">
	<caption><h3>用户登录</h3></caption>
		<tr>
			<td>用户名：<input type="text" name="username"/></td>
		</tr>
		<tr align="center">
			<td><input type="submit" value="登录"/><input type="reset" value="重填" /></td>
		</tr>
	</table>
</form>
</body>
</html>
