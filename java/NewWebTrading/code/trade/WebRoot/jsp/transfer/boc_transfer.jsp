<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.itsz.sht.common.util.FormatConversion" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webchannels.tld" prefix="taifook" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<script language="JavaScript" src="<%=request.getContextPath()%>/js/FormChek.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/general.js"></script>
<script language="javascript">
<!--
function customValidateBocTransferForm(form) {
	var result1 = false;
	var result2 = false;
	if (validateBocTransferForm(form)) {
		disableIt(document.forms[0].submit);            
            	result1 = true;
            	
		var fcr = checkDecimalPoint(form.mch_payAmt.value, false);
		if (!isPositiveFloat(form.mch_payAmt.value)) {
			alert('<bean:message bundle="STT" key="errors.bocTransferForm.transferAmount"/>');
			document.forms[0].submit.disabled = false;
		} else if (fcr == -1 || fcr > 2) {
			alert('<bean:message bundle="STT" key="errors.bocTransferForm.transferAmountDP"/>');
			document.forms[0].submit.disabled = false;
		} else {
			result2 = true;
		}
	}
	return result1&&result2;
}
function CloseWindows(){
		top.close();
	}
-->
</script>

<html>
<head>
<title><bean:message bundle="STT" key="label.bocTransferForm.title"/></title>
<script language="JavaScript">
	function popBOC() {
		var TfBOC = window.open("https://its.bochk.com", "BOCPop",'toolbar=1,location=1,directories=1,status=1,menubar=1,scrollbars=1,resizable=1, width=800, height=600');
	}
</script>
<link rel=StyleSheet HREF="<%=request.getContextPath()%>/css/other.css" TYPE="text/css">
</head>
<%
	System.out.println("popupErrors() befor");
%>
<body bgcolor=FFFFFF onLoad="popupErrors();" >
<taifook:errors methodName="popupErrors" />
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

				<html:form action="/bocTransfer" method="post" onsubmit="return customValidateBocTransferForm(this)">
					<html:hidden property="disableFromAccount" />
					<table border=0 align=center >

						<tr>
							<td><font class="odesc1"><bean:message bundle="STT" key="label.bocTransferForm.transferFrom"/> : </font></td>
							<td><font class="odesc1"><bean:message bundle="STT" key="label.bocTransferForm.transferFrom2"/></font></td>
						</tr>

						<tr>
							<td><font class="odesc1"><bean:message bundle="STT" key="label.bocTransferForm.transferTo"/> : </font></td>
							<td>
								<logic:equal name="bocTransferForm" property="disableFromAccount" value="false">									
									<html:select property="mch_custID">
										<logic:iterate id="account" name="bocTransferForm" property="fromAccounts" type="com.itsz.sht.common.user.AcEnquiryInfo">
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
								<logic:equal name="bocTransferForm" property="disableFromAccount" value="true">									
									<html:select property="mch_custID" disabled="true">
										<logic:iterate id="account" name="bocTransferForm" property="fromAccounts" type="com.itsz.sht.common.user.AcEnquiryInfo">
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
									<html:hidden property="mch_custID" />
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td colspan=3><br></td>
						</tr>

						<tr>
							<td><font class="odesc1"><bean:message bundle="STT" key="label.bocTransferForm.transferAmount"/> : </font></td>
							<td><html:text property="mch_payAmt" size="9" maxlength="9"/></td>
						</tr>

						<tr>
							<td colspan="2" height="10"></td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" name="submit" value='<bean:message bundle="STT" key="label.bocTransferForm.submit"/>'>
								<input type=button value='<bean:message bundle="STT" key="label.ppsTransferForm.close1"/>' onclick="CloseWindows()">
							</td>
						</tr>
					</table>
				</html:form>
				<html:javascript formName="bocTransferForm"/>
				
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
