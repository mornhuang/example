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
<title>aclUserProfileShow</title>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">   
<script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/commutil.js"></script>
</head>
<body>

<table width="50%" align="left">
<tr bgcolor="#A5B7C5"><td colspan="2">
  <STRONG>Acl UserProfile Detail</STRONG>
</td></tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Login ID</td>
		<td><bean:write name="aclUserProfile" property="lognId" ></bean:write></td>
	</tr>
    <tr>
		<td bgcolor="#A5B7C5" width="15%">Role ID</td>
		<td><bean:write name="aclUserProfile" property="roleId" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Department</td>
		<td><bean:write name="aclUserProfile" property="deptNme" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">UserName</td>
		<td><bean:write name="aclUserProfile" property="usrNme" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Email</td>
		<td><bean:write name="aclUserProfile" property="emailAddr" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Status</td>
		<td><bean:write name="aclUserProfile" property="status" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Init By</td>
		<td><bean:write name="aclUserProfile" property="initBy" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Init Date</td>
		<td><bean:write name="aclUserProfile" property="initDate" format="yyyy-MM-dd HH:mm:ss"></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Update By</td>
		<td><bean:write name="aclUserProfile" property="updBy" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Update Date</td>
		<td><bean:write name="aclUserProfile" property="updDate"  format="yyyy-MM-dd HH:mm:ss"></bean:write></td>
	</tr>
	<tr>
	<td align="center" ><input type="button"
				value="GoBack" onClick="history.go(-1)" /></td>
	</tr>
</table>
</body>