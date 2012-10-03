<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>
<%@ page import="java.util.Iterator,java.util.List"%>
<%@ page import="com.taifook.adminportal.common.util.page.Page"%>
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
<form  action="listRtqAccount.do?method=listRtqAccount" name="rtqAccountForm" method="post">
<table  align="left" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#A5B7C5">
  <td colspan="4" align="center"> <STRONG> More RTQ Account List </STRONG></td>
  </tr>
	<tr bgcolor="#A5B7C5">
		<td>&nbsp;&nbsp;&nbsp;&nbsp; </td>
		<td>Product ID </td>
		<td>RTQ Login ID</td>
		<td>RTQ Login PassWord</td>
	</tr>
		<%
  try{
    	Page testpage=(Page)request.getAttribute("page");
   		List result=(List)testpage.getThisPageElements();
   		pageContext.setAttribute("result",result);
       }catch(java.lang.Exception es){}
    
%>
	<c:forEach var="rtqAccount" items="${result}" varStatus="status">
	  <c:choose>
		<c:when test="${status.index%2==0}">  
			<tr bgcolor="#ffffff">
		</c:when>
		<c:otherwise><tr></c:otherwise>
	  </c:choose>
	  	<td>${status.index +1 }</td>
	  	<td>${rtqAccount.id.prodId}</td>
	    <td>${rtqAccount.id.rtqLognId}</td>
	    <td>${rtqAccount.rtqLognPwd}</td>
	  </tr>
	</c:forEach>
	<tr>
		<td colspan="4">
		<div align="center"><c:catch>
			<%Page testpage=(Page)request.getAttribute("page");%>
			<def:ymshowPageSplit currentPage="<%=testpage.getThisPageNumber()%>"
				maxPage="<%=testpage.getLastPageNumber()%>"
				totalSize="<%=testpage.getTotalNumberOfElements()%>"
				url="<%=request.getContextPath()+"/listRtqAccount.do?method=listRtqAccount"%>"
				parameters="${URLParam}" currentPageName="currentpage" />
		</c:catch></div>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
</body>