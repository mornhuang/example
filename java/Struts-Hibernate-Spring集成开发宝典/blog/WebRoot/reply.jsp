<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>

<html>
<head>
<title><bean:message key="reply.add.welcome"/></title>
<link rel="stylesheet" href="css/common.css">

</head>
<body>
<center>
	<h1><bean:message key="reply.add.welcome"/></h1>
	<br>
	<table width="500">
		<tr>
			<td colspan="2">${message.title}--${message.pubdate}</td>
		</tr>
		<tr>
			<td  colspan="2">${message.content}</td>
		</tr>
		<html:form method="post" action="/replySave" >
		<input type="hidden" name="messageId" value="${message.id}" >
		<tr>
			<td colspan="2"><html:errors/></td>
		</tr>
		<tr>
			<td align="right"><bean:message key="reply.title"/></td>
			<td><html:text property="title"/> *</td>
		</tr>
		<tr>
			<td align="right"><bean:message key="reply.username"/></td>
			<td><html:text property="username"/> *</td>
		</tr>
		<tr>
			<td align="right"><bean:message key="reply.content"/></td>
			<td><html:textarea property="content"/> *</td>
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
				<img height="200" src="img/welcome.gif">
			</td>
		</tr>
		</html:form>
	</table>
</center>
</body>
</html>