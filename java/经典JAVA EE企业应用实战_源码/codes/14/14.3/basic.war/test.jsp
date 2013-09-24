<%@page contentType="text/html;charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
	<title>JAAS测试页面</title>
</head>
<body>
	<h1>JAAS测试页面</h1>
	<div style="font-wight:bold">
		HttpServletRequest中提供了如下方法与JAAS相关：
		<ul>
			<li>getRemoteUser() </li>
			<li>getUserPrincipal()</li>
			<li>isUserInRole(String role) </li>
		</ul>
	</div>
	<p><%=request.getRemoteUser()%>,欢迎您</p>
	<p>身份主体是：<%=request.getUserPrincipal()%></p>
	<p>您是否为crazyit角色：<%=request.isUserInRole("crazyit")%></p>
</body>
</html>
