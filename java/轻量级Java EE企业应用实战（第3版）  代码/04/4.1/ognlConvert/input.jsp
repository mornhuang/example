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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>利用OGNL的进行类型转换</title>
</head>
<body>
<h3>利用OGNL的进行类型转换</h3>
<s:form action="login">
	<!-- 该表单域封装的请求参数名为user.name -->
	<s:textfield name="user.name" label="用户名"/>
	<!-- 该表单域封装的请求参数名为user.pass -->
	<s:textfield name="user.pass" label="密码"/>
	<tr>
		<td colspan="2"><s:submit value="转换" theme="simple"/>
		<s:reset value="重填" theme="simple"/></td>
	</tr>
</s:form>
</body>
</html>
