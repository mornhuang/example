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
<title>rtqApplicationShow.jsp</title>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">  
</head>
<body>
<form action="">
<table width="50%"  align="left">
<tr bgcolor="#A5B7C5"><td colspan="2">
  <STRONG>RTQ Application Detail</STRONG>
</td></tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Product ID </td>
		<td><bean:write name="RtqApplication" property="prodId" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">RTQ Login ID</td>
		<td><bean:write name="RtqApplication" property="rtqPrdr" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Client ID</td>
		<td><bean:write name="RtqApplication" property="rtqUrl" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Init By</td>
		<td><bean:write name="RtqApplication" property="initBy" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Init Date</td>
		<td><bean:write name="RtqApplication" property="initDate"  format="yyyy-MM-dd HH:mm:ss"></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%" >Update By</td>
		<td><bean:write name="RtqApplication" property="updBy" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Update Date</td>
		<td><bean:write name="RtqApplication" property="updDate" format="yyyy-MM-dd HH:mm:ss"></bean:write></td>
	</tr>
	<tr><td align="center" ><input type="button" value="GoBack" onClick="history.go(-1)" /></td></tr>
</table>
</form>
</body>