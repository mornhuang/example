<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Iterator,java.util.List"%>
<%@ page import="com.taifook.adminportal.common.util.page.Page"%>
<%@ include file="../js/checkurl.jsp"%>
<html>
<head>
<title>userProfileList.jsp</title>
<link href="./css/link.css" rel="stylesheet" type="text/css">
<link href="./css/global.css" rel="stylesheet" type="text/css">     
 <script language="JavaScript" src="../js/commutil.js"></script>
<script type="text/javascript">
function searchKeydowm(e){
    dkey = e.keyCode;
    if(dkey=="13"){
    	if(document.userProfileForm.clntId.value=="" && document.userProfileForm.clntLoginId.value==""){
            alert("ClinetID and ClientLoginID cannot empty in same time!");
            document.userProfileForm.clntId.focus();
        }else{
       		searchA();
        }
    }
}
function searchA(){
	if(document.userProfileForm.clntId.value=="" && document.userProfileForm.clntLoginId.value==""){
        alert("ClinetID and ClientLoginID cannot empty in same time!");
        document.userProfileForm.clntId.focus();
    }else{
	    document.userProfileForm.action="listUserProfile.do?method=listUserProfile";
	    document.userProfileForm.submit();
    }
}
function clearAll(){
	document.userProfileForm.clntId.value = "";
	document.userProfileForm.clntLoginId.value = "";
}

</script>
</head>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">   
<script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/commutil.js"></script>
<body>
<form method="post" name="userProfileForm">
<table width="50%" align="left">
	<tr bgcolor="#A5B7C5">
		<td>Client ID:<html:text property="clntId" name="userProfileForm"   size="15"   onkeypress="return searchKeydowm(event);" ></html:text></td>
		<td>Client Login ID:<html:text property="clntLoginId" name="userProfileForm"   size="15"   onkeypress="return searchKeydowm(event);" ></html:text></td>
		<td>
			<input type="button" name="Submit" value="Search" onclick="searchA();">&nbsp;&nbsp;&nbsp;
			<input type="button" name="Reset" value="Reset" onclick="clearAll();">
		</td>
	</tr>
</table>
</form>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table align="left" width="50%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>Client ID:${clientId} &nbsp;&nbsp;|&nbsp;&nbsp; Client Login ID:${clientLoginId}</td>
	</tr>   
	<tr>
		<td >
			|&nbsp;&nbsp;<html:link action="listUserProductByClientId.do?method=listUserProductByClientId&clientIdss=${userProfileForm.clntId}&clientLoginIdss=${userProfileForm.clntLoginId}"><font color="blue">Change User Product Status</font></html:link>
			&nbsp;|&nbsp;&nbsp;<html:link action="listCancelUserProduct.do?method=listCancelUserProduct&clientIdss=${userProfileForm.clntId}&clientLoginIdss=${userProfileForm.clntLoginId}"><font color="blue">Cancel User Product</font></html:link>
			&nbsp;|&nbsp;&nbsp;<html:link action="listReserveProduct.do?method=listReserveProduct&clientIdss=${userProfileForm.clntId}&clientLoginIdss=${userProfileForm.clntLoginId}"><font color="blue">Reserve Product</font></html:link>
			&nbsp;|&nbsp;&nbsp;<html:link action="listUserProfile.do?method=listUserProfile&clientIdss=${userProfileForm.clntId}&clientLoginIdss=${userProfileForm.clntLoginId}"><font color="blue">Selected Service</font></html:link> 
		</td>
	</tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table align="left" width="50%" border="0" cellpadding="0" cellspacing="0">
			<tr bgcolor="#A5B7C5">
				<td width="15%">Client ID</td>
				<td width="25%">Client Login ID</td>
				<td width="30%">Default Debit Account</td>
				<td width="30%">China Discount Flag</td>
			</tr>
			<logic:present name="userProfile" scope="request">
				<tr>
					<td><bean:write name="userProfile" property="clientId" /></td>
					<td><bean:write name="userProfile" property="clientLoginId" /></td>
					<td><bean:write name="userProfile" property="defaultDebitAccount" /></td>
					<td><bean:write name="userProfile" property="chinaDiscountFlag" /></td>
				</tr>
			</logic:present>
</table>
<p>&nbsp;</p>
	<%@ include file="./selectedServicesList.jsp"%>
</body>
</html>