<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.itsz.sht.common.Constants" %>
<%@ page import="com.itsz.sht.common.util.FormatConversion" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webchannels.tld" prefix="taifook" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<script language="JavaScript" src="<%=request.getContextPath()%>/Script/FormChek.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/Script/general.js"></script>
<script language="javascript">
<!--
function customValidatePpsTransferForm(form) {
	var result1 = false;
	var result2 = false;
	
	 if (validatePpsTransferForm(form)) {
            disableIt(document.forms[0].submit);            
            result1 = true;
            
            var fcr = checkDecimalPoint(form.tamount.value, false);
		if (!isPositiveFloat(form.tamount.value)) {
			alert('<bean:message bundle="STT" key="errors.ppsTransferForm.transferAmount"/>');
			document.forms[0].submit.disabled = false;
		} else if (fcr == -1 || fcr > 2) {
			alert('<bean:message bundle="STT" key="errors.ppsEnquiryForm.amountDP"/>');
			document.forms[0].submit.disabled = false;
		} else {
			result2 = true;
		} 
        }

	return result1&&result2;
}

function popWindow(links) {
    var popupManual = window.open(links, "popupwindow", "width=800,height=600, location=no,toolbar=no,resizable=yes,scrollbars=yes,status=yes,top=0,left=0");
}
-->
</script>

<html>
<head>
<title><bean:message bundle="STT" key="label.ppsTransferForm.title"/></title>

<script language="Javascript">
	<!--
	function ReloadPage(){
		document.location = "<%=org.apache.struts.util.RequestUtils.getActionMappingURL("/ppsEnquiry", pageContext)%>";
//		document.forms[0].action = "<%=org.apache.struts.util.RequestUtils.getActionMappingURL("/ppsEnquiry", pageContext)%>";
//		document.forms[0].submit();
	}
	
	function CloseWindows(){
		top.close();
	}
	
-->
</script>
<link rel=StyleSheet HREF="<%=request.getContextPath()%>/Style/other.css" TYPE="text/css">
</head>
<body bgcolor="white" onLoad="popupErrors();" >
<taifook:errors methodName="popupErrors" />
	<table align=center border=0>
			<tr>
				<td><br></td>
			</tr>
			<tr>
				<td colspan=3><p><html:img bundle="STT" srcKey="image.pps_transfer.heading"/></p></td>
			</tr>
			
			<tr valign=top>
				<td><p><html:img bundle="STT" srcKey="image.common.line"/></p></td>
			</tr>
	</table>
	
	<!-- If the operation period is valid (This part is not to be modified for jsp)-->
	
		
			<table border=0 cellpadding=5 cellspacing=0 width=90% align=center>
				<tr align=center>
					<td>
					
						<html:form action="/ppsTransfer" method="post" onsubmit="return customValidatePpsTransferForm(this)">
						<html:hidden property="disableFromAccount" />
						<table border="0" cellpadding="5" cellspacing="0" >
	
							<tr>
								<td><font class="odesc1"><bean:message bundle="STT" key="label.ppsTransferForm.transfer"/></font><input  type=radio name=OpType value=1 checked></td>
								<td width=20></td>
								<td><font class="odesc1"><bean:message bundle="STT" key="label.ppsTransferForm.enquiry"/></font><input  onclick="ReloadPage()" type=radio name=OpType value=2></td>
							</tr>
										
							<tr>
								<td><font class="odesc1"><bean:message bundle="STT" key="label.ppsTransferForm.transferFrom"/></font></td>
								<td></td>
								<td>
								<logic:equal name="ppsTransferForm" property="disableFromAccount" value="false">
									<html:select property="accountId">
										<logic:iterate id="account" name="ppsTransferForm" property="fromAccounts" type="com.itsz.sht.common.user.AcEnquiryInfo">	
										<%
											String ac = "";
											try{
												ac = FormatConversion.formatAcShort(account.getAccountId());
											}catch(Exception e){
											ac = "";
											}
										%>	
											<html:option value="<%=account.getAccountId()%>"><%=ac+" ("%><bean:message bundle="STT" key="<%="label.fundTransferForm.acType." + account.getAccountType().trim()%>"/><%=")"%></html:option>
										</logic:iterate>
									</html:select>
								</logic:equal>
								<logic:equal name="ppsTransferForm" property="disableFromAccount" value="true">
									
									<html:select property="accountId" disabled="true">
										<logic:iterate id="account" name="ppsTransferForm" property="fromAccounts" type="com.itsz.sht.common.user.AcEnquiryInfo">
										<%
											String acc = "";
											try{
												acc = FormatConversion.formatAcShort(account.getAccountId());
											}catch(Exception e){
											acc = "";
											}
										%>
											<html:option value="<%=account.getAccountId()%>"><%=acc+" ("%><bean:message bundle="STT" key="<%="label.fundTransferForm.acType." + account.getAccountType().trim()%>"/><%=")"%></html:option>
										</logic:iterate>
									</html:select>
									<html:hidden property="accountId" />
								</logic:equal>
								</td>
							</tr>
							
							<tr>
								<td colspan=3><br></td>
							</tr>
							
							<tr>				
								<td align="center"><font class="odesc1"><bean:message bundle="STT" key="label.ppsTransferForm.transferAmount"/></font></td>
								<td></td>
								<td>
									<html:text property="tamount" size="9" maxlength="9"/>
								</td>							
							</tr>
							
							<tr height=30>
								<td colspan=3><br></td>
							</tr>
							<tr>
								<td align="center" align="center" colspan="3">
								<input type="submit" name="submit" value='<bean:message bundle="STT" key="label.ppsTransferForm.submit"/>'>
									<input type=button value='<bean:message bundle="STT" key="label.ppsTransferForm.close1"/>' onclick="CloseWindows()">									
								</td>
							</tr>
						</table>
					</td>
					</html:form>
					<html:javascript formName="ppsTransferForm"/>
				</tr>
			</table>
			<table width=650 align=center>
				<tr align=center>			
					<td></td>			
				<tr align=center>
                </tr>
				<tr align=center>
					<td><font class="odesc1"><bean:message bundle="STT" key="label.ppsTransferForm.remarks"/></font></td>
				</tr>
                <tr>
                  <td><%@ include file="../copyright_footer.jsp" %></td>
                </tr>
			</table>			
</BODY>
</HTML>
