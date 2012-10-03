<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/webchannels.tld" prefix="taifook"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.itsz.sht.common.LocaleUtil,com.itsz.sht.common.CLVSplitUtil"%>

<html>
<head>
<title><bean:message key="company.name" /></title>
<link rel=StyleSheet HREF="<%=request.getContextPath()%>/css/other.css"	TYPE="text/css">
</head>

<script language="Javascript">
	<!--
	function CloseWindows(){
		window.top.opener=null;
		top.close();
	}
	alert('<bean:message bundle="STT" key="message.general.sessionExpired"/>');
	CloseWindows();
	-->
</script>

<body bgcolor=white>
<%
	//根据用户的语言设定显示的语言 
	String clv = (String) request.getAttribute("CLV");
	String language = CLVSplitUtil.getLanguage(clv);
	if ("".equals(language)) {
		language = "BIG5";
	}
	if (!(clv == null)) {
		LocaleUtil.setLocale(request, language);
	}
%>
<center><br>
<table align=center border=0>
	<tr>
		<td><br>
		</td>
	</tr>
</table>
<center>
<table border="0" cellpadding="5" cellspacing="0" width=400>
	<tr align=center>
		<td colspan=3><font class="odesc1"><BR>
		<bean:message bundle="STT" key="message.general.sessionExpired" /><BR>
		<BR>
		<input type=button value="<bean:message bundle="STT" key="label.ppsTransferForm.close1"/>" onclick="CloseWindows()">
		<BR>
		<BR>
		</font></td>
	</tr>
</table>
</body>
</html>
