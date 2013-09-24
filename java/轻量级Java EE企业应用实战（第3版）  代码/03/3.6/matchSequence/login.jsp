<%@ page language="java" contentType="text/html; charset=GBK"%>
<script>
	function regist()
	{
		targetForm = document.forms[0];
		targetForm.action = "RegistAction.action";
		targetForm.submit();
	}
</script>
<html>
<head>
<title>登录页面</title>
</head>
<body>
<table width="300" align="center">
<form action="LoginAction.action" method="post">
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
<td><input type="button" value="注册" onClick="regist();"/></td>
</tr>
</form>
<table>
</body>
</html>
