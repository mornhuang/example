<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<html>
	<head>
		<title><bean:message key="registration.success" /></title>
	</head>
<body>
	<h1><bean:message key="registration.success" /></h1>
	<table>
		<tr>
			<td>
				<bean:message key="userRegistration.userName" />:
			</td>
			<td>
				<bean:write name="userRegistrationForm" property="userName" />
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="userRegistration.logName" />:
			</td>
			<td>
				<bean:write name="user" property="logName" />
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="userRegistration.email" />:
			</td>
			<td>
				<bean:write name="user" property="email" />
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="userRegistration.phone" />:
			</td>
			<td>
				<bean:write name="user" property="phone" />
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="userRegistration.fax" />:
			</td>
			<td>
				<bean:write name="user" property="fax" />
			</td>
		</tr>
	</table>
</body>
</html>