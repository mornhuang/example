<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
	<meta http-equiv=pragma content=no-cache>
	<title><bean:message key="company.name"/></title>
</head>

<body bgcolor="white">
	<form action='' method='post'>
		LoginId:<input name='loginID'  value='<%=request.getParameter("loginID")%>'>
		Password:<input name='pw' value='<%=request.getParameter("pw")%>'>
		Language:<input name='lang' value='<%=request.getParameter("lang")%>'>	
	</form>
</body>
</html>