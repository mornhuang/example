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
<title>aclRoleAdd.jsp</title>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function checkSubmit(){
	var flag=0;
	var roleId=document.aclRoleForm.roleId.value;
	var functionId=document.getElementsByName("functionId");
	if(roleId==""){
               alert("roleId required!");
               return false;
		}
	for (i = 0; i < functionId.length; i++) {
		 if(functionId[i].checked==true){
		       flag++;
		  }
	  }
		 if(flag==0){
		      alert("Pls Select Function!");
		   return false;
	 }
		 return true;
}
function deaultCheck() {
	var a = document.getElementsByName("functionId");
	var fuctionId='<%=request.getAttribute("functionStr")%>';
	for (i = 0; i < a.length; i++) {
       var temp=fuctionId.split(",");
          for(j=0;j<temp.length-1;j++){
        	  if(a[i].value==temp[j]){
             	 a[i].checked="true";
             	 break;
                }
             }
          }
	document.all.treeflagParameters.src = "<%=basePath%>/jsp/images/tridown.gif";
	document.all.idParameters.style.display = "";

	document.all.treeflagAdministrator.src = "<%=basePath%>/jsp/images/tridown.gif";
	document.all.idAdministrator.style.display = "";

	document.all.treeflagUser.src = "<%=basePath%>/jsp/images/tridown.gif";
	document.all.idUser.style.display = "";

	document.all.treeflagRtqApp.src = "<%=basePath%>/jsp/images/tridown.gif";
	document.all.idRtqApp.style.display = "";

	document.all.treeflagServiceProduct.src = "<%=basePath%>/jsp/images/tridown.gif";
	document.all.idServiceProduct.style.display = "";

	document.all.treeflagAutoProcess.src = "<%=basePath%>/jsp/images/tridown.gif";
	document.all.idAutoProcess.style.display = "";

	document.all.treeflagReport.src = "<%=basePath%>/jsp/images/tridown.gif";
	document.all.idReport.style.display = "";
	
	document.all.treeflagNoDataExport.src = "<%=basePath%>/jsp/images/tridown.gif";
	document.all.idNoDataExport.style.display = "";
	
}

	function CheckAll(){
		   var a=document.getElementById("checkAllId");
		   var b=document.getElementsByName("functionId");
		   for(i = 0; i < b.length; i++){
			   b[i].checked=a.checked;
			   }	
		}

</script>
</head>
<body onload="deaultCheck();">
<form action="addAclRole.do?method=addAclRole" name="aclRoleForm"  method="post" onsubmit="return checkSubmit();">
<table align="left">
 <tr bgcolor="#A5B7C5">
	 <td colspan="2" align="center" > <STRONG>Add Acl Role</STRONG></td>
	 </tr> 
	<tr>
	<logic:present name="returnMessage">
	 <tr>
	 <td colspan="2" align="center" > <font color="red">${returnMessage}</font></td>
	 </tr> 
	 </logic:present>
		<td bgcolor="#A5B7C5" width="15%">Role ID </td>
		<td><html:text property="roleId" name="aclRoleForm" maxlength="20"></html:text><font color="red">*</font></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Description </td>
		<td><html:textarea property="descr"  name="aclRoleForm" cols="20" rows="10" ></html:textarea></td>
	</tr>
	<tr>
	<td><input type="submit" value="Submit"  ></td>
	<td align="center" ><input type="button"
				value="GoBack" onClick="history.go(-1)" /></td>
	</tr>
</table>
<div  id="menuId" align="center"><%@ include file="./menuSelect.jsp"%></div>
</form>
</body>