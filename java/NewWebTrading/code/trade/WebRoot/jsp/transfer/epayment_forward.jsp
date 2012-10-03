<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
	String paymentURL = (String)request.getAttribute("forwardUrl");
	String token = (String)request.getAttribute("token");
%>

<html>
<head>
	<meta http-equiv=pragma content=no-cache>
</head>

<body bgcolor="white">
	<form action="<%=paymentURL%>" method="post">
		<input type="hidden" name="token" value="<%=token%>" />
	</form>
</body>
</html>

<script language=javascript>
	document.forms[0].submit();
</script>
