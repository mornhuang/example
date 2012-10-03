<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ page import="com.itsz.sht.common.auto.ProcessStatus"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>
<html>
<head>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function doProcess(){
	document.all.Process.disabled = true;
	window.location.href = "<%=request.getContextPath()%>/exeMemoDebit.do?method=exeMemoDebit";
}

function doReturn(){
	document.all.Process.disabled = true;
	window.location.href = "<%=request.getContextPath()%>/showProcessAuto.do?method=showProcessAuto";
}
</script>
</head>
<body>
<form action="exeMemoDebit.do?method=exeMemoDebit" method="post">
<table align="left">
<logic:notPresent name="userProductAllotmentList">
  <tr>
  <td colspan="2"><strong>No records need to do MemoDebit.</strong></td>
  </tr>
  
  <tr>
		<td>
		<center><br>
		<br>
		<input type="button" name="Process" onclick="javascript:doReturn();"
			value="Return"></center>
		</td>
	</tr>
</logic:notPresent>
<logic:present name="userProductAllotmentList">
<tr align="left">
<td>
	<table border=0 width=100% cellspacing=0 cellpadding=2 align="left">
		<tr>
			<td bgcolor="#A5B7C5"><font color="#ffffff"><b><strong>For MemoDebit Data</strong></b></font></td>
		</tr>
		<tr><td><br></td></tr>
		<tr align="center">
			<td>
				<input type="button" name="Process" onclick="javascript:doProcess();" value="Memodebit For Auto-Enrollment">
			</td>
		</tr>
		<tr align="left">
			<td>
				<table border=1 width="50%" cellspacing=0 cellpadding=0>
					<tr bgcolor="#A5B7C5">
						<td>Index</td>
						<td>Client ID</td>
						<td>Product ID</td>
					</tr>
				<logic:iterate id="userProductAllotment" name="userProductAllotmentList" indexId="indexId">
					<tr>
						<td>${indexId + 1}</td>
						<td>${userProductAllotment.clntId}</td>
						<td>${userProductAllotment.prodId}</td>
					</tr>
				</logic:iterate>	
				</table>	
			</td>
		</tr>
	</table>
</td>
		</tr>
</logic:present>
</table>
</form>
</body>
</html>