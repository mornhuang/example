<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webchannels.tld" prefix="taifook" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<script language="Javascript">
	function CloseWindows(){
		top.close();
	}
</script>

<html>
<head>
<title><bean:message bundle="STT" key="label.bocTransferForm.title"/></title>
<meta http-equiv=Pragma content=no-cache>
<link rel=StyleSheet HREF="<%=request.getContextPath()%>/css/other.css" TYPE="text/css">
</head>
<body bgcolor=FFFFFF>


	<table align=center border=0>
			<tr valign=bottom>
				<td colspan=3>
					<p><html:img bundle="STT" srcKey="image.boc_transfer.heading"/></p>
				</td>
			</tr>
			<tr height=25 valign=top>
				<td colspan=3>
					<p><html:img bundle="STT" srcKey="image.common.line"/></p>
				</td>
			</tr>
	</table>
	<br><br>
	<table align=center width=600>
			<tr>
				<td align=center><font class="odesc1"><bean:message bundle="STT" key="errors.ppsTransfer.unavailable"/></font><br><Br><br><Br>
				<input type=button value="<bean:message bundle="STT" key="label.bocTransferForm.close"/>" onclick="CloseWindows()">
				</td>
			</tr>			
			
	</table>
	 
	<!--Display BOC info link-->
	<br><br>
	<table align=center width=600>
			<tr>
				<td align=center><font class="odesc1"><bean:message bundle="STT" key="label.bocTransferForm.remarks"/></font></td>
			</tr>
	</table>

	<!--Display discalimers-->	
	<center><%@ include file="../copyright_footer.jsp" %></center>
</body>
</html>
