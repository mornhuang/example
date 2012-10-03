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
<title>MonthlyPurchaseEnquiry</title>
<link href="<%=request.getContextPath()%>/jsp/css/global.css"
	rel="stylesheet" type="text/css">
<script language="JavaScript" src="./js/wpCalendar.js"></script> 
</head>
<body>
<br/>
<br/>
<form name="productForm" action="monthlyPurchaseEnquiry.do?method=monthlyPurchaseEnquiry"  method="post">
 <table align="left">
 <tr bgcolor="#A5B7C5">
  <td>Product ID &nbsp;&nbsp;&nbsp;<html:select property="prodId" name="productForm">
     <html:optionsCollection name="productList"/>
  </html:select></td>
  <td>Period &nbsp;&nbsp;&nbsp;	
  <html:select property="startYear" name="productForm">	
		<html:optionsCollection  name="yearList"/>
		</html:select>
  <html:select property="startMonth" name="productForm">
  	<html:optionsCollection  name="monthList"/>
  	</html:select>
   </td>
  <td><html:submit value="Submit"/></td>
 </tr>
 </table>
</form>
<br/>
<br/>
<br/>
<br/>
<table align="left" width="100%" border="0" cellspacing="0" cellpadding="0">
<tr bgcolor="#A5B7C5">
  <td colspan="13" align="center"> <STRONG>Monthly Purchase Enquiry List </STRONG></td>
	<tr bgcolor="#A5B7C5">
	    <td>Client ID</td>
		<td>Product ID</td>
		<td>Product Name</td>
		<td>Effective Date</td>
		<td>Expire Date</td>
		<td>Payment Request ID</td>
		<td>Charge Date</td>
		<td>Charge Cash(HKD)</td>
		<td>Charge Account</td>
		<td>Response Message</td>
		<td>Pay Status</td>
		<td>Update Date</td>
		<td>Update By</td>
	</tr>
		<%
  try{
    	Page testpage=(Page)request.getAttribute("page");
   		List result=(List)testpage.getThisPageElements();
   		pageContext.setAttribute("result",result);
       }catch(java.lang.Exception es){}
    
%>
	<c:forEach var="monthlyPurchaseEnquiry" items="${result}" varStatus="var">
		<c:choose>
			<c:when test="${var.index%2==0}">  
				<tr bgcolor="#ffffff">
			</c:when>
			<c:otherwise><tr></c:otherwise>
		</c:choose>
		    <td>&nbsp;${monthlyPurchaseEnquiry.clientId}</td>
			<td>&nbsp;${monthlyPurchaseEnquiry.productId}</td>		
			<td>&nbsp;<bean:message bundle="admin" key="${monthlyPurchaseEnquiry.productId}"/></td>
			<td>&nbsp;${monthlyPurchaseEnquiry.effectiveDate}</td>
			<td>&nbsp;${monthlyPurchaseEnquiry.expireDate}</td>
			<td>&nbsp;${monthlyPurchaseEnquiry.paymentRequestId}</td>
			<td>&nbsp;${monthlyPurchaseEnquiry.chargeDate}</td>
			<td>&nbsp;${monthlyPurchaseEnquiry.chargeCash}</td>
			<td>&nbsp;${monthlyPurchaseEnquiry.chargeAccount}</td>
			<td>&nbsp;${monthlyPurchaseEnquiry.responseMessage}</td>
			<td>&nbsp;${monthlyPurchaseEnquiry.payStatus}</td>
			<td>&nbsp;${monthlyPurchaseEnquiry.updateDate}</td>
			<td>&nbsp;${monthlyPurchaseEnquiry.updateBy}</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="11">
		<div align="center"><c:catch>
			<%Page testpage=(Page)request.getAttribute("page");%>
			<def:ymshowPageSplit currentPage="<%=testpage.getThisPageNumber()%>"
				maxPage="<%=testpage.getLastPageNumber()%>"
				totalSize="<%=testpage.getTotalNumberOfElements()%>"
				url="<%=request.getContextPath()+"/monthlyPurchaseEnquiry.do?method=monthlyPurchaseEnquiry"%>"
				parameters="${URLParam}" currentPageName="currentpage" />
		</c:catch></div>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>

</body>