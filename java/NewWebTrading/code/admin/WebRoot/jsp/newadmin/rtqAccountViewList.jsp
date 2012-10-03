<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../js/checkurl.jsp"%>
<html>
<head>
<title>Create Product</title>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">
</head>
<body>
<table align="left" width="50%" border="0" cellpadding="0" cellspacing="0">
			 <tr>
				<td >
				|&nbsp;<html:link action="listRTQAccountView.do?method=listRTQAccountView"><font color="blue">RTQ Account Usage Enquiry</font></html:link>
				&nbsp;|&nbsp;<html:link action="listRtqAccountAssignment.do?method=listRtqAccountAssignment"><font color="blue">RTQ Account Assignment Enquiry</font></html:link>
				&nbsp;|&nbsp;<html:link action="listRtqAccount.do?method=listRtqAccount"><font color="blue">RTQ Account Maintenance</font></html:link>
				</td>
			 </tr>
	</table>
<p>&nbsp;</p>
<table align="left" width="50%" border="0" cellspacing="0" cellpadding="0">
	<tr bgcolor="#A5B7C5">
		<td colspan="8" align="center"><STRONG>RTQ Account Usage Enquiry</STRONG></td>
	</tr>
	<tr bgcolor="#A5B7C5">
		<td colspan="8" align="center">&nbsp;</td>
	</tr>
	<tr bgcolor="#A5B7C5">
		<td>Product ID</td>
		<td>Account total</td>
		<td>Account required<br>this month</td>
		<td>Account in using<br>this month</td>
		<td>Account required<br>next month</td> 
		<td>Last account id</td>
	</tr>
	<logic:present name="rtqAccountViewList" scope="request">
		<c:forEach var="rtqAccountView" items="${rtqAccountViewList}">
			<tr>
				<c:choose>
					<c:when test="${rtqAccountView.productId eq 'SHK'}">
						<td>SHK</td>
						<td>N/A</td>
						<td>N/A</td>
						<td>${rtqAccountView.accountRequiredThisMonth}</td>
						<td>${rtqAccountView.accountRequiredNextMonth}</td>
						<td>N/A</td>
					</c:when>
					<c:otherwise>
						<td><html:link action="listRtqAccountAssByProductId.do?method=listRtqAccountAssByProductId&productId=${rtqAccountView.productId}" >${rtqAccountView.productId}</html:link></td>
						<td>${rtqAccountView.accountTotal}</td>
						<c:choose>
							<c:when test="${rtqAccountView.accountRequiredThisMonth > rtqAccountView.accountTotal}">
								<td><font color="red">${rtqAccountView.accountRequiredThisMonth}</font></td>
							</c:when>
							<c:otherwise>
								<td>${rtqAccountView.accountRequiredThisMonth}</td>
							</c:otherwise>
						</c:choose>
						<td>${rtqAccountView.accountInUseThisMonth}</td>
						<c:choose>
							<c:when test="${rtqAccountView.accountRequiredNextMonth > rtqAccountView.accountTotal}">
								<td><font color="red">${rtqAccountView.accountRequiredNextMonth}</font></td>
							</c:when>
							<c:otherwise>
								<td>${rtqAccountView.accountRequiredNextMonth}</td>
							</c:otherwise>
						</c:choose>
						<td>${rtqAccountView.lastAccountId}</td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
	</logic:present>
	<tr><td colspan="6">&nbsp;</td></tr>
	<tr>
	<td colspan="6">  <%@ include file="./rtqAccountOperate.jsp"%></td>
	</tr>
</table>

 
</body>