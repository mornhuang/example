<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>
<html>
<head>
<title>Create Product</title>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function exeAutoEnrollment(){
	document.forms[0].Process.disabled = true;
	window.location.href = "<%=request.getContextPath()%>/exeAutoEnrollment.do?method=exeAutoEnrollment";
}
function doReleaseAccount(){
	document.forms[0].Release.disabled = true;
	window.location.href = "<%=request.getContextPath()%>/releaseRTQAccountAssignment.do?method=releaseRTQAccountAssignment";
}
function exeMemoDebit(){
	document.forms[0].Memo.disabled = true;
	window.location.href = "<%=request.getContextPath()%>/showDataForMemoDebit.do?method=showDataForMemoDebit";
}

</script>
</head>
<body>
<form name="productForm" action="" method="post">
<table align="left" width="100%" >
	<tr><td>
	<table width="100%" align="left">
	<tr>
		<td><strong>Process Auto Purchase</strong></td>
		<td align="right">
			<a href="<%=request.getContextPath()%>/showAutoPurchaseStatus.do?method=showAutoPurchaseStatus">View Process Progress</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/autoPurchaseEnquiry.do?method=autoPurchaseEnquiry">Seach Process Result</a>
		</td>
	</tr>
	</table>
	</td></tr>
	<tr><td><br>
	</td></tr>
	<tr><td>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" align="left">
	    <tr>
	   <td>
	    <logic:present name="message">
	       <font color="red">
	      <bean:message key="${message}" bundle="admin"/>
	       </font>
	    </logic:present>
	   </td>
	    </tr>
		<tr>
			<td align=center>
				<table width="70%" border="1">
					<tr style="line-height:24pt;">
						<td align="left" width="40%">
							Task 1:   Auto Enrollment
						</td>
						<td align="left" width="60%">
							Please process at 01:00 on first day morning of Month<br/>
							<input type="button" name="Process" onclick="javascript:exeAutoEnrollment();" value="Execute Auto Enrollment">
							<br/>
						</td>
					</tr>
					<tr style="line-height:24pt;">
						<td align="left" width="40%">
							Task 2:   Redistribute RTQ Account
						</td>
						
						<td align="left" width="60%">
						<br/>
							<input type="button" name="Release" onclick="javascript:doReleaseAccount();" value="Redistribute RTQ Account">
								
						</td>
					</tr>
					<tr style="line-height:24pt;">
						<td align="left" width="40%">
							Task 3:   Memo Debit
						</td>
						<td align="left" width="60%">
							Please process after day-end process on first day of Month<br/>
							<input type="button" name="Memo" onclick="javascript:exeMemoDebit();"  value="Search For MemoDebit">
							<br/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</td></tr>
</table>
</form>
</body>