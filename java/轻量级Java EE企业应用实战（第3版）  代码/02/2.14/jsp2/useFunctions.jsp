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
<%@ taglib prefix="crazyit" uri="http://www.crazyit.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> new document </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
	<h2>表达式语言 - 自定义函数</h2><hr/>
	请输入一个字符串：
	<form action="useFunctions.jsp" method="post">
		字符串 = <input type="text" name="name" value="${param['name']}">
		<input type="submit"  value="提交">
	</form>
	<table border="1" bgcolor="aaaadd">
		<tr>
		<td><b>表达式语言</b></td>
		<td><b>计算结果</b></td>
		<tr>
		<tr>
			<td>\${param["name"]}</td>
			<td>${param["name"]}&nbsp;</td>
		</tr>
		<!--  使用reverse函数-->
		<tr>
			<td>\${crazyit:reverse(param["name"])}</td>
			<td>${crazyit:reverse(param["name"])}&nbsp;</td>
		</tr>
		<tr>
			<td>\${crazyit:reverse(crazyit:reverse(param["name"]))}</td>
			<td>${crazyit:reverse(crazyit:reverse(param["name"]))}&nbsp;</td>
		</tr>
		<!-- 使用countChar函数 -->
		<tr>
			<td>\${crazyit:countChar(param["name"])}</td>
			<td>${crazyit:countChar(param["name"])}&nbsp;</td>
		</tr>
	</table>
</body>
</html>