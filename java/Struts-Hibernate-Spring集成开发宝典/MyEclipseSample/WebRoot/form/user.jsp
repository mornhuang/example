 
<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for userForm form</title>
	</head>
	<body>
		<html:form action="/user">
			<html:errors /><br/>
			name : <html:text property="name"/><html:errors property="name"/><br/>
			password : <html:password property="password"/><html:errors property="password"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
	</body>
</html>
