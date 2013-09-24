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
<!-- 使用i18n作为s:text标签的父标签，
	临时指定国际化资源文件的baseName为tmp -->
<!-- 使用s:text输出国际化消息 -->
<s:i18n name="tmp">
	<title><s:text name="loginPage"/></title>
</s:i18n>
</head>
<body>
<!-- 使用i18n作为s:from标签的父标签，
	临时指定国际化资源文件的baseName为tmp -->
<s:i18n name="tmp">
<s:form action="Login">
	<s:textfield name="username" key="user"/>
	<s:textfield name="password" key="pass"/>
	<s:submit key="login"/>
</s:form>
</s:i18n>
</body>
</html>
