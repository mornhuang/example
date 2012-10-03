<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>

<html>
<head>
<title><bean:message key="message.update.welcome"/></title>
<link rel="stylesheet" href="css/common.css">

</head>
<body>
<center>
	<h1><bean:message key="message.update.welcome"/></h1>
	<br>
	<table width="500">
		<html:form method="post" action="/messageSaveUpdate" >
		<html:hidden name="message" property="id"/>
		<tr>
			<td colspan="2"><html:errors/></td>
		</tr>
		<tr>
			<td align="right"><bean:message key="message.title"/></td>
			<td><html:text name="message" property="title"/> *</td>
		</tr>
		<tr>
			<td align="right"><bean:message key="message.content"/></td>
			<td><html:textarea name="message" property="content"/> *</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<html:submit><bean:message key="message.add.submit"/></html:submit>
				&nbsp;&nbsp;
				<html:reset><bean:message key="regist.reset"/></html:reset>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<img height="200" src="img/update.gif">
			</td>
		</tr>
		</html:form>
	</table>
</center>
</body>
</html>