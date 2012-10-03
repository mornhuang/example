<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webchannels.tld" prefix="taifook" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.itsz.sht.common.PropertyConfig" %>
<%@ page import="com.itsz.sht.common.Constants" %>

<%
	response.addHeader("Progma", "No-cache");
	response.addHeader("Cache-Control", "no-cache");
	response.addDateHeader("Expires", 1);

    //String ppsGateWay = WebConfigBuilder.instance().buildWebConfig().getPPSConfig().getIpgClientURL();
	String ppsGateWay = PropertyConfig.getCommonProperty(Constants.IpgClientURL);  
%>

<html>
<head>
	<meta http-equiv=pragma content=no-cache>
</head>

<body bgcolor="white">
	<form action="<%=ppsGateWay%>/iPGClient/eps_transfer2.asp" method="post">
		<html:hidden name="ppsTransferForm" property="lang" />
		<html:hidden name="ppsTransferForm" property="user_id" />
		<html:hidden name="ppsTransferForm" property="token" />
		<html:hidden name="ppsTransferForm" property="accountId" />
		<html:hidden name="ppsTransferForm" property="tamount" />
	</form>
</body>
</html>

<script language=javascript>
	document.forms[0].submit();
</script>
