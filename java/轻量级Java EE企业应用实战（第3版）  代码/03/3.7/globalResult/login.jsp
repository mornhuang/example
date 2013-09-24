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
	<title>转向页面</title>
</head>
<body>
	<table width="400" align="center">
	<form action="MyAction.action" method="post">
		<tr>
			<td>转入的目标页面:</td>
			<td><input type="text" name="target"/></td>
		</tr>
		<tr>
			<td colspan="2">注意：<br/>
				您应该输入welcome，系统只提供welcome.jsp页面</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="转入"/></td>
		</tr>
	</form>
	<table>
</body>
</html>
