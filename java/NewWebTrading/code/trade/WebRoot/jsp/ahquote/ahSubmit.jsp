<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
	<meta http-equiv=pragma content=no-cache>
	<title><bean:message key="company.name"/></title>
</head>

<body bgcolor="white">
	<form action='<bean:write name="ahcpRes" property="url"/>' method='post'>
		<input name='loginID' type='hidden' value='<bean:write name="ahcpRes" property="loginId"/>'>
		<input name='pw' type='hidden' value='<bean:write name="ahcpRes" property="pwd"/>'>
		<input name='lang' type='hidden' value='<bean:write name="ahcpRes" property="language"/>'>	
	</form>
</body>
</html>

<script language=javascript>
	document.forms[0].submit();
</script>