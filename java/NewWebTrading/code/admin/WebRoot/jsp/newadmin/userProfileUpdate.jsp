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
	 <td colspan="2" align="center" > <STRONG>Update User Profile</STRONG></td>
	 </tr> 
	<tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Client ID :</td>
		<td><html:text property="clntId"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Client Login ID :</td>
		<td><html:text property="clntLoginId"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Default Debit Account :</td>
		<td><html:text property="defDebtAcc"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">China Discount Flag :</td>
		<td><html:text property="cnDiscFlag"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Init By :</td>
		<td><html:text property="cnDiscFlag"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Init Date :</td>
		<td><html:text property="cnDiscFlag"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Update By:</td>
		<td><html:text property="cnDiscFlag"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Update Date:</td>
		<td><html:text property="cnDiscFlag"></html:text></td>
	</tr>
	<tr>
	<td><input type="submit" value="Submit" onclick="checkSubmit();" ></td>
	<td align="center" ><input type="button"
				value="GoBack" onClick="history.go(-1)" /></td>
	</tr>
</table>
</body>
</html>