<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	response.addHeader("Progma", "No-cache");
	response.addHeader("Cache-Control", "no-cache");
	response.addDateHeader("Expires", 1);
%>
<html>
<head>
	<meta http-equiv=pragma content=no-cache>
</head>
<body bgcolor="white">
<form name="defaultForm" action="<bean:write name="aaStockRTQRequest" property="link"/>" method="post">
    <html:hidden name="aaStockRTQRequest" property="uname"/>
    <html:hidden name="aaStockRTQRequest" property="password"/>
    <html:hidden name="aaStockRTQRequest" property="broker"/>
    <html:hidden name="aaStockRTQRequest" property="ver"/>
    <c:choose><c:when test="${aastock_enabled=='1'}"><html:hidden name="aaStockRTQRequest" property="lang"/></c:when></c:choose>
</form>
</body>
</html>
<script language="javascript">
    document.defaultForm.submit();
</script>
