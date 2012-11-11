<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>

<html>
<head>
<title><bean:message key="regist.welcome"/></title>
<link rel="stylesheet" href="css/common.css">

</head>
<body>
<center>
	<h1><bean:message key="regist.welcome"/></h1>
	<br>
	<table width="500">
		<html:form method="post" action="/regist" >
		<tr>
			<td colspan="2"><html:errors/></td>
		</tr>
		<tr>
			<td align="right"><bean:message key="logon.username"/></td>
			<td><html:text property="username"/> *</td>
		</tr>
		<tr>
			<td align="right"><bean:message key="logon.password"/></td>
			<td><html:password property="password"/> *</td>
		</tr>
		<tr>
			<td align="right"><bean:message key="regist.repassword"/></td>
			<td><html:password property="repassword"/> *</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<html:submit><bean:message key="logon.regist"/></html:submit>
				&nbsp;&nbsp;
				<html:reset><bean:message key="regist.reset"/></html:reset>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<img height="200" src="img/register.gif">
			</td>
		</tr>
		</html:form>
	</table>
</center>
</body>
</html>