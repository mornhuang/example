<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webchannels.tld" prefix="taifook" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.List" %>

<%@ page import="com.itsz.sht.common.FieldUtil" %>
<%@ page import="com.itsz.sht.common.model.common.PPSRecord" %>

<%@ include file="EPSConstant.jsp" %>

<bean:define id="ppsRecords" name="ppsEnquiryForm" property="ppsRecords" type="java.util.List"/>

<% 
response.addHeader("Progma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
response.addDateHeader("Expires",1);
%>

<%
int NoOfRecord;
PPSRecord ppsRecord = null;

if (ppsRecords.size() > 10) {
	NoOfRecord=10;
}else{
	NoOfRecord=ppsRecords.size();
}

 %>

<html>
<head>
<title><bean:message bundle="STT" key="label.ppsEnquiryForm.title"/></title>

<script language=javascript>


var	proceed=0;	

function ReloadPage(){
		document.forms[0].action = "<%=org.apache.struts.util.RequestUtils.getActionMappingURL("/ppsTransferInit", pageContext)%>";
		document.forms[0].submit();
}


function CloseWindows(){
	//self.close();
	top.close();	
}


function UpdateAmount(){
	var Index;
		
	Index=document.makeenq.IndexList.options[document.makeenq.IndexList.selectedIndex].value
	
	if (Index==1) {
		document.makeenq.ReferenceNo.value=document.makeenq.ReferenceNo1.value
		document.makeenq.Amount.value=document.makeenq.Amount1.value
	}	
	
	if (Index==2) {
		document.makeenq.ReferenceNo.value=document.makeenq.ReferenceNo2.value
		document.makeenq.Amount.value=document.makeenq.Amount2.value	
	}	
	
	if (Index==3) {
		document.makeenq.ReferenceNo.value=document.makeenq.ReferenceNo3.value
		document.makeenq.Amount.value=document.makeenq.Amount3.value	
	}
	
	if (Index==4) {
		document.makeenq.ReferenceNo.value=document.makeenq.ReferenceNo4.value
		document.makeenq.Amount.value=document.makeenq.Amount4.value	
	}
		
	if (Index==5) {
		document.makeenq.ReferenceNo.value=document.makeenq.ReferenceNo5.value
		document.makeenq.Amount.value=document.makeenq.Amount5.value	
	}
	
	if (Index==6) {
		document.makeenq.ReferenceNo.value=document.makeenq.ReferenceNo6.value
		document.makeenq.Amount.value=document.makeenq.Amount6.value	
	}
	
	if (Index==7) {
		document.makeenq.ReferenceNo.value=document.makeenq.ReferenceNo7.value
		document.makeenq.Amount.value=document.makeenq.Amount7.value	
	}
	
	if (Index==8) {
		document.makeenq.ReferenceNo.value=document.makeenq.ReferenceNo8.value
		document.makeenq.Amount.value=document.makeenq.Amount8.value	
	}
	
	if (Index==9) {
		document.makeenq.ReferenceNo.value=document.makeenq.ReferenceNo9.value
		document.makeenq.Amount.value=document.makeenq.Amount9.value	
	}
	
	if (Index==10) {
		document.makeenq.ReferenceNo.value=document.makeenq.ReferenceNo10.value
		document.makeenq.Amount.value=document.makeenq.Amount10.value	
	}
		
	document.makeenq.IndexList.focus();

}

</script>
</head>

<body onload=" UpdateAmount()" bgcolor=FFFFFF >
	<br>
	<!--Display the header -->
	<table align=center border=0>
			<tr valign=bottom>
				<td colspan=3>
					<p><html:img bundle="STT" srcKey="image.pps_transfer.heading"/></p>
				</td>
			</tr>
			<tr height=25 valign=top>
				<td colspan=3>
					<p><html:img bundle="STT" srcKey="image.common.line"/></p>
				</td>
			</tr>
	</table>
	
	
	<!--<form name=makeenq ID=makeenq action="mkenq01.asp" method=post >-->
	<form name=makeenq ID=makeenq action="<%=EPSServer%>/iPGClient/genenq.asp" method=post >
		<table align=center cellpadding=5 cellspacing=0>		

			<tr>
				<td><bean:message bundle="STT" key="label.ppsEnquiryForm.fundTrans"/><input  onclick="ReloadPage()" type=radio name=OpType value=1></td>
				<td></td>
				<td><bean:message bundle="STT" key="label.ppsEnquiryForm.makeEnq"/><input   type=radio name=OpType value=2 checked></td>
			</tr>

			
			<tr>
				<td><bean:message bundle="STT" key="label.ppsEnquiryForm.refNo"/></td>
				<td width=20></td>
				<td>
					<select  Name=IndexList  onchange='UpdateAmount()'>
						<%if (ppsRecords.size() > 0){
							for (int Index=0; Index < NoOfRecord ;Index++){
								ppsRecord = (PPSRecord) ppsRecords.get(Index); %>
									<% String TempString=("0000000000000000"+ppsRecord.getTxRef());%>
									<%if (Index < 10){%>
										<Option value="<%=Index+1%>">&nbsp;&nbsp;<%=Index+1%>.&nbsp;&nbsp;<%=TempString.substring(TempString.length()-16,TempString.length())%>&nbsp;&nbsp;&nbsp;(<%=Integer.parseInt(ppsRecord.getTxDate().substring(6,8))%>-<%=Integer.parseInt(ppsRecord.getTxDate().substring(4,6))%>-<%=ppsRecord.getTxDate().substring(0,4)%>)</Option>
									<%}else{%>
										<Option value="<%=Index+1%>"><%=Index+1%>.&nbsp;&nbsp;<%=TempString.substring(TempString.length()-16,TempString.length())%>&nbsp;&nbsp;&nbsp;(<%=Integer.parseInt(ppsRecord.getTxDate().substring(6,8))%>-<%=Integer.parseInt(ppsRecord.getTxDate().substring(4,6))%>-<%=ppsRecord.getTxDate().substring(0,4)%>)</Option>							
									<%}%>
							<%}%>
						<%}else{%>
							<Option value=0><bean:message bundle="STT" key="errors.ppsEnquiryForm.txRecord"/></Option>
						<%}%>
					</select>
				</td>				 
			</tr>
								
			<input type=hidden Name=ReferenceNo value="">
			<%for (int Index=0; Index < NoOfRecord ;Index ++) {
				ppsRecord = (PPSRecord) ppsRecords.get(Index); 
				String TempString=("0000000000000000"+ppsRecord.getTxRef());%>
				<input type=hidden Name=<%="ReferenceNo" + (Index+1)%> value=<%=TempString.substring(TempString.length()-16,TempString.length())%> >
			<%}%>
			
			<tr>
				<td><bean:message bundle="STT" key="label.ppsEnquiryForm.amount"/></td>
				<td></td>
				<td><input type=text name=Amount size=16 maxlength=12 readonly></td>
			</tr>			
			
			<%for (int Index=0; Index < NoOfRecord ;Index ++) {
				ppsRecord = (PPSRecord) ppsRecords.get(Index); %>
				<input type=hidden  name=<%="Amount" + (Index+1)%> value="<%=FieldUtil.formatBigDecimal(ppsRecord.getTxAmt(), "####.00")%>" >
			<%}%>
			
			<tr height=30>
				<td colspan=3></td>
			</tr>
			
			<tr align=center>			
				<td colspan=3 >
				<%if(NoOfRecord>0){%>
					<input type=submit value="<bean:message bundle="STT" key="label.ppsTransferForm.submit"/>">	
				<%}else{%>
					<input type=submit value="<bean:message bundle="STT" key="label.ppsTransferForm.submit"/>" disabled="disabled">
				<%}%>
				<input type=button value="<bean:message bundle="STT" key="label.ppsTransferForm.close1"/>" onclick="CloseWindows()" id=button1 name=button1>
				</td>
			</tr>
		</table>
		
		<input type=hidden name=OpCode value="<bean:write name="ppsEnquiryForm" property="opCode" />">		
		<input type=hidden name=Locale value="<bean:write name="ppsEnquiryForm" property="locale" />">
		<input type=hidden name=lang value="<bean:write name="ppsEnquiryForm" property="lang" />">
	
	</form>
		
    <center><%@ include file="../copyright_footer.jsp" %></center>
</body>
</html>



