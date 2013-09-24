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
	<title>登录页面</title>
</head>
<body>
<table width="300" align="center">
<form action="loginAction" method="post">
	<tr>
		<td>用户名:</td>
		<td><input type="text" name="username"/></td>
	</tr>
	<tr>
		<td>密&nbsp;&nbsp;码:</td>
		<td><input type="text" name="password"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="登录"/></td>
		<td><input type="submit" value="注册"
			onclick="regist();"/></td>
	</tr>
</form>
<table>
<script type="text/javascript">
function regist()
{
	//获取页面的第一个表单
	targetForm = document.forms[0];
	//动态修改表单的action属性
	targetForm.action = "registAction";
}
</script>
</body>
</html>
