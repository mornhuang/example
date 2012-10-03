<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>
<html>
<head>
<title>aclUserProfileUpdate.jsp</title>
<script type="text/javascript">
function updateAclUserProfile(){
	var emailid=document.getElementById('emailAddr');
		if(!check_email(emailid))
  		{
			alert("invalid email !");
	  		return false;
  		}
		return true;	
}
</script>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">  
</head>
<body>
<form action="updateAclUserProfile.do?method=updateAclUserProfile" name="aclUserProfileForm" method="post" onsubmit="return updateAclUserProfile();">
<table align="left">
<tr bgcolor="#A5B7C5">
	 <td colspan="2" align="center" > <STRONG>Update Acl UserProfile</STRONG></td>
	 </tr> 
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Login ID </td>
		<td><html:text property="lognId" name="aclUserProfileForm" readonly="true"></html:text></td>
	</tr>
    <tr>
		<td bgcolor="#A5B7C5" width="15%">Role ID</td>
	 <td>
	 	<c:if test="${aclUserProfileForm.lognId != 'admins' }">
		 	<html:select property="roleId"  name="aclUserProfileForm" >
			    <html:optionsCollection name="roleList"/>
			</html:select>
		</c:if>
		<c:if test="${aclUserProfileForm.lognId == 'admins' }">
			<html:text property="roleId" name="aclUserProfileForm" readonly="true"></html:text>
		</c:if>
	 </td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Department </td>
		<td><html:text property="deptNme" name="aclUserProfileForm" maxlength="30"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">UserName</td>
		<td><html:text property="usrNme" name="aclUserProfileForm" maxlength="20"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Email </td>
		<td><html:text property="emailAddr" name="aclUserProfileForm" maxlength="50" size="51"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Status</td>
		<td>
			<c:if test="${aclUserProfileForm.lognId != 'admins' }">
			 	<html:select property="status" name="aclUserProfileForm" >
				 <html:optionsCollection name="statusList"/>
				</html:select>
			</c:if>
			<c:if test="${aclUserProfileForm.lognId == 'admins' }">
				<html:text property="status" name="aclUserProfileForm" readonly="true"></html:text>
			</c:if>
		
		</td>
	</tr>
	<tr>
	<td><input type="submit" value="Submit" ></td>
	<td align="left" ><input type="button"
				value="GoBack" onClick="history.go(-1)" /></td>
	</tr>
</table>
</form>
</body>