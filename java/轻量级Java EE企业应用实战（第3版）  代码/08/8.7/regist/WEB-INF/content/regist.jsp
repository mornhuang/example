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
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户注册</title>
</head>
<body>
<h3>用户注册</h3>
<s:form action="registPro">
	<s:textfield name="person.name" label="用户名"/>
	<s:textfield name="person.age" label="年龄"/>
	<tr>
		<td colspan="2">
		<s:submit value="注册" theme="simple"/>
		<s:reset value="重填" theme="simple"/></td>
	</tr>
</s:form>
</body>
</html>
