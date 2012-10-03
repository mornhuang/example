<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ page import="com.itsz.sht.common.auto.ProcessStatus"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>
<html>
<head>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/calendar.js"></script>
<script language="javascript">

    function doSubmit(){
            var formObj = document.autoPurchaseForm ;
/*
            if (formObj.status.value ==""){
                    alert ("Please select Purchase Status .");
                    formObj.status.focus();
                    return;
            }
           */
            if (checkDate(formObj.startYear.value,formObj.startMonth.value,formObj.startDay.value,formObj.endYear.value,formObj.endMonth.value,formObj.endDay.value))
            {
                    formObj.btnSubmit.disabled = true;
                    formObj.btnReset.disabled = true;
                    formObj.submit();
            }else{
                    alert("From date cann't be after To date");
                    return;
            }
    }
	function backToProcess()
	{
		window.location.href = "<%= request.getContextPath() %>/showAutoPurchaseStatus.do?method=showAutoPurchaseStatus";
	}
    </script>
</head>

<body bgcolor="#ffffff" topmargin="0" leftmargin="0" marginwidth="0"
	marginheight="0">
<form name="autoPurchaseForm" method="POST"
			action="<%=request.getContextPath()%>/autoPurchaseEnquiry.do?method=autoPurchaseEnquiry">
<table border=0 width=100% cellspacing=0 cellpadding=2 align="left">
	<tr>
		<td height="23" bgcolor="#A5B7C5"><font color="#ffffff"><b>Auto Purchase Enquiry</b></font></td>
	</tr>
	<tr>
		<td><!--************************************** main **************************************-->
		

		<table width="100%" align="center" cellspacing=2>
			<tr>
				<td width="35%">
				<div align="right">Purchase Status:</div>
				</td>
				<td width="65%">
					<html:select property="status" name="autoPurchaseForm">
						<html:optionsCollection name="statusList"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<Td align=right>Period From:</td>
				<td>
					<html:select property="startYear" name="autoPurchaseForm" onchange="setDays(this.form.startMonth,this.form.startYear,this.form.startDay);">
						<html:optionsCollection name="yearList"/>
					</html:select>&nbsp;&nbsp;
					<html:select property="startMonth" name="autoPurchaseForm" onchange="setDays(this.form.startMonth,this.form.startYear,this.form.startDay);">
						<html:optionsCollection name="monthList"/>
					</html:select>&nbsp;&nbsp;	
					<html:select property="startDay" name="autoPurchaseForm">
						<html:optionsCollection name="fdayList"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<Td align=right>To:</td>
				<td>
					<html:select property="endYear" name="autoPurchaseForm" onchange="setDays(this.form.endMonth,this.form.endYear,this.form.endDay);">
						<html:optionsCollection name="yearList"/>
					</html:select>&nbsp;&nbsp;
					<html:select property="endMonth"  name="autoPurchaseForm" onchange="setDays(this.form.endMonth,this.form.endYear,this.form.endDay);">
						<html:optionsCollection name="monthList"/>
					</html:select>&nbsp;&nbsp;
					<html:select property="endDay" name="autoPurchaseForm">
						<html:optionsCollection name="tdayList"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;&nbsp;
					<input TYPE="button" name="btnSubmit" value="Submit" onclick="doSubmit();"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<INPUT TYPE="reset" name="btnReset" value="Reset">
				</td>
			</tr>
		</table>
		<!--************************************** main **************************************-->
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="1" 
			 bgcolor="#A5B7C5" align="left">
				<tr bgcolor="#A5B7C5">
					<td width="5%" nowrap>
					<div align="center"><font color="#FFFFFF">Index</font></div>
					</td>
					<td width="10%" nowrap>
					<div align="center"><font color="#FFFFFF">Client ID</font></div>
					</td>
					<td width="10%" nowrap>
					<div align="center"><font color="#FFFFFF">Product ID</font></div>
					</td>
					<td width="20%" nowrap>
					<div align="center"><font color="#FFFFFF">Remarks</font></div>
					</td>
					<td width="10%" nowrap>
					<div align="center"><font color="#FFFFFF">Price In HK</font></div>
					</td>
					<td width="15%" nowrap>
					<div align="center"><font color="#FFFFFF">Purchase Status</font></div>
					</td>
					<td width="20%" nowrap>
					<div align="center"><font color="#FFFFFF">Purchase Time</font></div>
					</td>
					<td width="10%" nowrap>
					<div align="center"><font color="#FFFFFF">Update By</font></div>
					</td>
				</tr>
			<%int success=0, lackMoney=0, sysError=0; %>
			<logic:present name="autoPurchaseList" scope="request">
				<c:forEach var="autoPurchase" items="${autoPurchaseList}" varStatus="varStatus">
					<c:choose>
						<c:when test="${autoPurchase.status=='SUCCESS'}">
							<% success++;%>
						</c:when>
						<c:when test="${autoPurchase.status=='NOT-ENOUGH-MONEY'}">
							<% lackMoney++;%>
						</c:when>
						<c:otherwise>
							<% sysError++;%>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${varStatus.index%2==0}">  
							<tr bgcolor="#ffffff">
						</c:when>
						<c:otherwise><tr></c:otherwise>
					</c:choose>
						<td align="left">${varStatus.index+1 }</td>
						<td align="center">${autoPurchase.clntId }</td>
						<td align="center">${autoPurchase.prodId }</td>
						<td align="center">${autoPurchase.debtRemarks }</td>
						<td align="center">${autoPurchase.priceInHkd }</td>
						<td align="center">${autoPurchase.status }</td>
						<td align="center">${autoPurchase.updDate }</td>
						<td align="center">${autoPurchase.updBy }</td>
					</tr>
				</c:forEach>
			</logic:present>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<br>
			<table border=0 width="100%" cellspacing=0 cellpadding=0>
				<tr>
					<td width="25%">Total Records :</td>
					<td width="75%"><%= success+lackMoney+sysError%></td>
				</tr>
				<tr>
					<td>Success :</td>
					<td><%= success%></td>
				</tr>
				<tr>
					<td>Not Enough Money :</td>
					<td><%= lackMoney%></td>
				</tr>
				<tr>
					<td>Sys Error :</td>
					<td><%= sysError%></td>
				</tr>
				<tr>
					<td><B>Report Created Date:&nbsp;</B></td>
					<td>${createdDate }</td>
				</tr>
				<tr>
					<td><B>Report Created By:&nbsp;</B></td>
					<td>${createdBy }</td>
				</tr>
			</table>
		</td>
	</tr>
		
	<tr>
		<td align="center">
		<br>
		<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type=button name="back" value="Return" onclick="backToProcess()">
		</td>
	</tr>
</table>


</form>
</body>
</html>
