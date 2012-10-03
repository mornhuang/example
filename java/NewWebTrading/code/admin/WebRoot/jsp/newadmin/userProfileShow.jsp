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
<title>Profile Detail</title>
</head>
<body>
<table width="80%" border="1" cellpadding="0" cellspacing="0" align="left">
	<tr bgcolor="#A5B7C5">
		<td>Client ID :</td>
		<td>${userProfile }</td>
	</tr>
	<tr bgcolor="#A5B7C5">
		<td>Default Debit Account :</td>
		<td>${userProfile }</td>
	</tr>
	<tr bgcolor="#A5B7C5">
		<td>China Discount Flag :</td>
		<td>${userProfile }</td>
	</tr>
	<tr bgcolor="#A5B7C5">
		<td>Init By :</td>
		<td>${userProfile }</td>
	</tr>
	<tr bgcolor="#A5B7C5">
		<td>Init Date :</td>
		<td>${userProfile }</td>
	</tr>
	<tr bgcolor="#A5B7C5">
		<td>Update By :</td>
		<td>${userProfile }</td>
	</tr>
	<tr bgcolor="#A5B7C5">
		<td>Update Date :</td>
		<td>${userProfile }</td>
	</tr>
	<tr>
	<td align="center" colspan="2"><input type="button"
				value="GoBack" onClick="history.go(-1)" /></td>
	</tr>
</table>
</body>
</html>