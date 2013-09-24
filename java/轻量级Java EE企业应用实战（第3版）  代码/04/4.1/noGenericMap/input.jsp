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
	<title>直接封装成Map</title>
</head>
<body>
<h3>直接封装成Map</h3>
<s:form action="login">
	<s:textfield name="users['one'].name" label="第one个用户名"/>
	<s:textfield name="users['one'].pass" label="第one个密码"/>
	<s:textfield name="users['two'].name" label="第two个用户名"/>
	<s:textfield name="users['two'].pass" label="第two个密码"/>
	<tr>
		<td colspan="2"><s:submit value="转换" theme="simple"/>
		<s:reset value="重填" theme="simple"/></td>
	</tr>
</s:form>
</body>
</html>
