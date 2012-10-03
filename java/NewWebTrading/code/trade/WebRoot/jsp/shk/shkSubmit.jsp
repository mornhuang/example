<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
	<meta http-equiv=pragma content=no-cache>
	<title><bean:message key="company.name"/></title>
</head>

<body bgcolor="white">
	<form action='<bean:write name="shkcpRes" property="url"/>' method='post'>
		<input name='security' type='hidden' value='<bean:write name="shkcpRes" property="security"/>'>
		<input name='language' type='hidden' value='<bean:write name="shkcpRes" property="language"/>'>
		<logic:notEmpty name="shkcpRes" property="stockcode">
		    <input name='stockcode' type='hidden' value='<bean:write name="shkcpRes" property="stockcode"/>'>
		</logic:notEmpty>		
	</form>
</body>
</html>

<script language=javascript>
	document.forms[0].submit();
</script>