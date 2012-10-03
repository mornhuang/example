<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webchannels.tld" prefix="taifook" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.itsz.sht.common.Constants" %>
<%@ page import="com.itsz.sht.common.PropertyConfig" %>

 
<%
	response.addHeader("Progma", "No-cache");
	response.addHeader("Cache-Control", "no-cache");
	response.addDateHeader("Expires", 1);
	String ItsHandlerURL = PropertyConfig.getCommonProperty(Constants.ItsHandlerURL);
	System.out.println("ItsHandlerURL "+ItsHandlerURL); 
%>


<html>
<head>
	<meta http-equiv=pragma content=no-cache>
</head>

<body bgcolor="white">
	<form action="<%=ItsHandlerURL%>" method="post">
		<html:hidden name="bocTransferForm" property="mch_merchID" />
		<html:hidden name="bocTransferForm" property="mch_merchRef" />
		<html:hidden name="bocTransferForm" property="mch_custID" />
		<html:hidden name="bocTransferForm" property="mch_payAmt" />
		<html:hidden name="bocTransferForm" property="mch_locale" />
		<html:hidden name="bocTransferForm" property="mch_timeStamp" />
		<html:hidden name="bocTransferForm" property="mch_returnURL" />
	</form>
</body>
</html>

<script language=javascript>
	document.forms[0].submit();
</script>
