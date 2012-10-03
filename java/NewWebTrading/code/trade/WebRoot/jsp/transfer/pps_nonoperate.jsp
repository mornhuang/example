<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webchannels.tld" prefix="taifook" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<bean:define id="remarks" name="ppsTransferForm" property="remarks" type="java.lang.String" />
<html>
<head>
	<title><bean:message bundle="STT" key="label.ppsTransferForm.title"/></title>	
    <link rel=StyleSheet HREF="<%=request.getContextPath()%>/css/other.css" TYPE="text/css">
</head>

<script language="Javascript">
	function ReloadPage(){
		document.location = "<%=org.apache.struts.util.RequestUtils.getActionMappingURL("/ppsEnquiry", pageContext)%>";
	} 

	function CloseWindows(){
		top.close();
	}
</script>

<body bgcolor="white" onLoad="popupErrors();" >
<taifook:errors methodName="popupErrors" />
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
    <table border="0" cellpadding="5" cellspacing="0" >
        <tr>
            <td><font class="odesc1"><bean:message bundle="STT" key="label.ppsTransferForm.transfer"/><input  type=radio name=OpType value=1 checked></font></td>
            <td width=20></td>
            <td><font class="odesc1"><bean:message bundle="STT" key="label.ppsTransferForm.enquiry"/><input  onclick="ReloadPage()" type=radio name=OpType value=2></font></td>
        </tr>
    </table>
    <table border="0" cellpadding="5" cellspacing="0" width=400>
        <tr align=center>
            <td colspan=3>
            <BR><font class="odesc1"><%=remarks%><BR><BR><input type=button value="<bean:message bundle="STT" key="label.ppsTransferForm.close1"/>" onclick="CloseWindows()"><BR><BR></font>
            </td>
        </tr>			
    </table>
			

<!--Here is the new HTML (end)-->
	
<!--Display the discalimer-->

    <%@ include file="../copyright_footer.jsp" %>

</body>
</html>
