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
	<title> 表达式语言 - 算术运算符 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
	<h2>表达式语言 - 算术运算符</h2><hr/>
	<table border="1" bgcolor="#aaaadd">
		<tr>
			<td><b>表达式语言</b></td>
			<td><b>计算结果</b></td>
		</tr>
		<!-- 直接输出常量 -->
		<tr>
			<td>\${1}</td>
			<td>${1}</td>
		</tr>
		<!-- 计算加法 -->
		<tr>
			<td>\${1.2 + 2.3}</td>
			<td>${1.2 + 2.3}</td>
		</tr>
		<!-- 计算加法 -->
		<tr>
			<td>\${1.2E4 + 1.4}</td>
			<td>${1.2E4 + 1.4}</td>
		</tr>
		<!-- 计算减法 -->
		<tr>
			<td>\${-4 - 2}</td>
			<td>${-4 - 2}</td>
		</tr>
		<!-- 计算乘法 -->
		<tr>
			<td>\${21 * 2}</td>
			<td>${21 * 2}</td>
		</tr>
		<!-- 计算除法 -->
		<tr>
			<td>\${3/4}</td>
			<td>${3/4}</td>
		</tr>
		<!-- 计算除法 -->
		<tr>
			<td>\${3 div 4}</td>
			<td>${3 div 4}</td>
		</tr>
		<!-- 计算除法 -->
		<tr>
			<td>\${3/0}</td>
			<td>${3/0}</td>
		</tr>
		<!-- 计算求余 -->
		<tr>
			<td>\${10%4}</td>
			<td>${10%4}</td>
		</tr>
		<!-- 计算求余 -->
		<tr>
			<td>\${10 mod 4}</td>
			<td>${10 mod 4}</td>
		</tr>
		<!-- 计算三目运算符 -->
		<tr>
			<td>\${(1==2) ? 3 : 4}</td>
			<td>${(1==2) ? 3 : 4}</td>
		</tr>
	</table>
</body>
</html>