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

	<title>请输入您的用户信息</title>
	<s:head/>

</head>


<body>
<h3>请输入您的用户信息</h3>
<s:form action="loginPro">
	<s:textfield label="用户名" name="name"/>
	<s:password label="密码" name="pass"/>
	<s:textfield label="年龄" name="age"/>
	<s:textfield label="生日" name="birth"/>
	<tr><td colspan="2">
	<s:submit value="注册" theme="simple"
		onclick="this.form.action='registPro';"/>
	<s:submit value="登录" theme="simple"
		onclick="this.form.action='loginPro';"/>
	</td></tr>
</s:form>

</body>

</html>