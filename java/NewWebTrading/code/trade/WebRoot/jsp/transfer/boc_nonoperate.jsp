<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webchannels.tld" prefix="taifook" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<bean:define id="remarks" name="bocTransferForm" property="remarks" type="java.lang.String" />
<html>
<head>
<title><bean:message bundle="STT" key="label.bocTransferForm.title"/></title>
<meta http-equiv=Pragma content=no-cache>
<link rel=StyleSheet HREF="<%=request.getContextPath()%>/css/other.css" TYPE="text/css">
<script language="JavaScript">
	function popBOC() {
		var TfBOC = window.open("https://its.bochk.com", "BOCPop",'toolbar=1,location=1,directories=1,status=1,menubar=1,scrollbars=1,resizable=1, width=800, height=600');
	}
</script>
</head>
<body bgcolor=FFFFFF>
	<table align=center border=0>
			<tr valign=bottom>
				<td colspan=3>
					<p><font class="odesc1"><html:img bundle="STT" srcKey="image.boc_transfer.heading"/></font></p>
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
            <td align=center><font class="odesc1"><%=remarks%></font></td>
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
