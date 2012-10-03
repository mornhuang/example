<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<html>
	<head>
		<title><bean:message key="userRegistrationList.title"/></title>
	</head>
	<body>
		<h1><bean:message key="userRegistrationList.title"/></h1>
		<table border="1">
			<tr>
				<td><bean:message key="userRegistration.userName" /></td>
				<td><bean:message key="userRegistration.logName" /></td>
				<td><bean:message key="userRegistration.email" /></td>
				<td><bean:message key="userRegistration.phone" /></td>
				<td><bean:message key="userRegistration.fax" /></td>
			</tr>
			<logic:iterate id="user" name="users">
			<tr>
				<td><bean:write name="user" property="userName" /></td>
				<td><bean:write name="user" property="logName" /></td>
				<td><bean:write name="user" property="email" /></td>
				<td><bean:write name="user" property="phone" /></td>
				<td><bean:write name="user" property="fax" /></td>
			</tr>	
			</logic:iterate>
		</table>
	</body>
</html>