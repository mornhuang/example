<%--
网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2010, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html;charset=GBK" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 登录聊天室 </title>
	<meta name="website" content="http://www.leegang.org" />
</head>
<body>
<table width="540" border="1" align="center" bgcolor="#dddddd">
<tr>
<td align="center">
<form id="loginForm" method="post" action="login.do">
<font color="red">
${requestScope.error}
</font>
<hr/>
<table>
<tr>
	<td colspan="2" align="center">
		请输入用户名和密码登陆
	</td>
</tr>
<tr>
	<td>用户名：</td>
	<td><input id="name" type="text" name="name"/></td>
</tr>
<tr>
	<td>密&nbsp;&nbsp;码：</td>
	<td><input id="pass" type="text" name="pass"/></td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" value="提交"/>
		<input type="reset" value="重设"/>
	</td>
</tr>
</table>
<br/>
<div align="center">
<a href="reg.jsp">注册新用户</a>
</div>
</form>
</td>
</tr>
</table>
<script>
	function check()
	{
		var name = document.getElementById("name");
		var pass = document.getElementById("pass");
		var errStr = "";
		if (name.value == "" || name.value == null)
		{
			errStr += "用户名不能为空\n";
		}
		if (pass.value == "" || pass.value == null)
		{
			errStr += "密码不能为空\n";
		}
		if (errStr == "" || errStr == null)
		{
			return true;
		}
		alert(errStr);
		return false;
	}
	document.getElementById("loginForm").onsubmit = check;
</script>
</body>
</html>
