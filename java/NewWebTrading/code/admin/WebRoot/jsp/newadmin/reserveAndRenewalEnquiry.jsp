<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ page import="java.util.Iterator,java.util.List"%>
<%@ page import="com.taifook.adminportal.common.util.page.Page"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>
<html>
<head>
<title>Create Product</title>
<link href="<%=request.getContextPath()%>/jsp/css/link.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript">

</script>
</head>
<body>
<br/>
<br/>
<form name="productForm" action="reserveAndRenewalEnquiry.do?method=reserveAndRenewalEnquiry"  method="post">
 <table align="left">
 <tr bgcolor="#A5B7C5">
  <td>Product ID &nbsp;&nbsp;&nbsp;<html:select property="prodId" name="productForm">
     <html:optionsCollection name="productList"/>
  </html:select></td>
  <td><html:submit value="Submit"/></td>
 </tr>
 </table>
</form>
<br/>
<br/>
<br/>
<br/>
<form name="productForm" action="">
<table align="left" width="100%" border="0" cellspacing="0" cellpadding="0">
<tr bgcolor="#A5B7C5">
  <td colspan="8" align="center"> <STRONG>Reserve And Renewal Enquiry List</STRONG></td>
</tr>
	<tr bgcolor="#A5B7C5">
	    <td>Client ID</td>
		<td>Product ID</td>
		<td>Product Name</td>
		<td>Product Price(HKD)</td>
		<td>Debit Account</td>
		<td>Update By</td>
		<td>Update Date</td>
		<td>Type</td>
	</tr>
	
  <logic:iterate id="reserveAndRenewalEnquiry" name="reserveAndRenewalEnquiryList" indexId="index">
  		<c:choose>
			<c:when test="${index%2==0}">  
				<tr bgcolor="#ffffff">
			</c:when>
			<c:otherwise><tr></c:otherwise>
		</c:choose>
		    <td>&nbsp;${reserveAndRenewalEnquiry.clientId}</td>
			<td>&nbsp;${reserveAndRenewalEnquiry.productId}</td>
		    <td>&nbsp;<bean:message bundle="admin" key="${reserveAndRenewalEnquiry.productId}"/></td>
			<td>&nbsp;${reserveAndRenewalEnquiry.priceInHK}</td>
			<td>&nbsp;${reserveAndRenewalEnquiry.defAccount}</td>
			<td>&nbsp;${reserveAndRenewalEnquiry.updBy}</td>	
			<td>&nbsp;${reserveAndRenewalEnquiry.updDate}</td>	
			<td>&nbsp;${reserveAndRenewalEnquiry.type}</td>	
			</tr>
	</logic:iterate>
		<tr>
		<td colspan="7">
		<div align="center"><c:catch>
			<%Page testpage=(Page)request.getAttribute("page");%>
			<def:ymshowPageSplit currentPage="<%=testpage.getThisPageNumber()%>"
				maxPage="<%=testpage.getLastPageNumber()%>"
				totalSize="<%=testpage.getTotalNumberOfElements()%>"
				url="<%=request.getContextPath()+"/reserveAndRenewalEnquiry.do?method=reserveAndRenewalEnquiry"%>"
				parameters="${URLParam}" currentPageName="currentpage" />
		</c:catch></div>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
</body>