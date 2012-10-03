<%@ page language="java" import="com.taifook.mcs.core.beans.msg.StockInfo"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
<head>
	<meta http-equiv=pragma content=no-cache>
	<title><bean:message key="company.name"/></title>
</head>

<body bgcolor="white">
	<form action='${url}' method='post'>
		<input name='security' type='hidden' value='${security}'>
		<input name='language' type='hidden' value='${language}'>
	</form>
</body>
</html>

<script language=javascript>
	document.forms[0].submit();
</script>