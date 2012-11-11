<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<html>
	<head>
		<title><bean:message key="userRegistration.title"/></title>
	</head>
	<body>
		<h1><bean:message key="userRegistration.title"/></h1>
		<html:errors/>
		<table>
		<html:form action="userRegistration">
			<tr>
				<td>
					<bean:message key="userRegistration.userName" />*
				</td>
				<td>
					<html:text property="userName" />
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="userRegistration.logName" />*
				</td>
				<td>
					<html:text property="logName" />
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="userRegistration.password" />*
				</td>
				<td>
					<html:password property="password" />
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="userRegistration.password" />*
				</td>
				<td>
					<html:password property="passwordCheck" />
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="userRegistration.email" />*
				</td>
				<td>
					<html:text property="email" />
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="userRegistration.phone" />
				</td>
				<td>
					<html:text property="phone" />
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="userRegistration.fax" />
				</td>
				<td>
					<html:text property="fax" />
				</td>
			</tr>
			<tr>
				<td>
					<html:submit />
				</td>
				<td>
					<html:cancel />
				</td>
			</tr>
			</html:form>
		</table>
	</body>
</html>