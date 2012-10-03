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
function goAddRtqApp(){
	 alert("暂不实现!!!");
	 return false;
	 document.rtqApplicationForm.action="goAddRtqApplication.do?method=goAddRtqApplication";
	 document.rtqApplicationForm.submit();
}
</script>
</head>
<body>

<form action="listRtqApplication.do?method=listRtqApplication" name="rtqApplicationForm" method="post">
<table width="80%" border="1" cellspacing="0" cellpadding="0"  align="left">
  <tr bgcolor="#A5B7C5">
  <td colspan="8" align="center"> <STRONG> More RTQ List </STRONG></td>
  </tr>
	<tr bgcolor="#A5B7C5">
		<td>Product ID </td>
		<td>RTQ Provider</td>
		<td>RTQ URL</td>
		<td>RTQ Status</td>
		<td colspan="3" align="center">Operation</td>
	</tr>
	<c:forEach  var="rtqApp" items="${rtqAppList}" >
	<tr>
	<td>&nbsp;${rtqApp.prodId}</td>
	<td>&nbsp;${rtqApp.rtqPrdr}</td>
	<td>&nbsp;${rtqApp.rtqUrl}</td>
	<td>&nbsp;${rtqApp.rtqStatus}</td>
	<td><html:link action="showRtqApplication.do?method=showRtqApplication&productId=${rtqApp.prodId}">View</html:link></td>
	<td><html:link action="goUpdateRtqApplication.do?method=goUpdateRtqApplication&productId=${rtqApp.prodId}">Update</html:link></td>
<!--	<td><html:link action="deleteRtqApplication.do?method=deleteRtqApplication&productId=${rtqApp.prodId}" onclick="goAddRtqApp();">Delete</html:link></td>-->
	</tr>
	</c:forEach>
</table>
<table>
<tr>
<!--	<td align="center" ><input type="button" value="GoBack" onClick="history.go(-1)" /></td>-->
<!--	<td><input type="button" value="Add" onClick="goAddRtqApp();" /></td>-->
	</tr>
</table>
</form>
</body>