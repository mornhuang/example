<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webchannels.tld" prefix="taifook" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html>
<head>
    <title><bean:message bundle="STT" key="label.ppsTransferForm.title"/></title>	
    <link rel=StyleSheet HREF="<%=request.getContextPath()%>/css/other.css" TYPE="text/css">
</head>

<script language="Javascript">
	function CloseWindows(){
		top.close();
	}
</script>

<body bgcolor=white>
<center>
<br>
	<table align=center border=0>
			<tr>
				<td><br></td>
			</tr>
			<tr>
				<td colspan=3><p><font class="odesc1"><html:img bundle="STT" srcKey="image.pps_transfer.heading"/></font></p></td>
			</tr>
			
			<tr valign=top>
				<td><p><html:img bundle="STT" srcKey="image.common.line"/></p></td>
			</tr>
	</table>
    <center>
    <table border="0" cellpadding="5" cellspacing="0" width=400>
        <tr align=center>
            <td colspan=3>
            <font class="odesc1"><BR><bean:message bundle="STT" key="errors.ppsTransfer.unavailable"/><BR><BR><input type=button value="<bean:message bundle="STT" key="label.ppsTransferForm.close1"/>" onclick="CloseWindows()"><BR><BR></font>
            </td>
        </tr>			
    </table>
			

<!--Here is the new HTML (end)-->
	
<!--Display the discalimer-->

    <%@ include file="../copyright_footer.jsp" %>

</body>
</html>
