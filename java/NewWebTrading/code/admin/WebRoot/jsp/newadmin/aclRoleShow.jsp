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
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
function deaultCheck() {
	var all=document.getElementById("checkAllId");
	all.disabled="disabled";
	var a = document.getElementsByName("functionId");
	var fuctionId='<%=request.getAttribute("functionStr")%>';
	for (i = 0; i < a.length; i++) {
		a[i].disabled="disabled";
       var temp=fuctionId.split(",");
          for(j=0;j<temp.length;j++){
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

	//document.all.treeflagOtherOper.src = "<%=basePath%>/jsp/images/tridown.gif";
	//document.all.idOtherOper.style.display = "";
	}

</script>
</head>
<body onload="deaultCheck();">
<form action="" name="aclRoleForm"  method="post">
<table >
	<tr bgcolor="#A5B7C5">
	<td colspan="2" align="center" ><STRONG>Acl Role Detail</STRONG></td> 
  </tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Role ID </td>
		<td><bean:write  property="roleId" name="aclRoleForm" ></bean:write></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Description </td>
		<td><bean:write property="descr"  name="aclRoleForm" ></bean:write></td>
	</tr>
	<tr>
	<td align="center" ><input type="button"
				value="GoBack" onClick="history.go(-1)" /></td>
	</tr>
</table>
<div id="menuId" align="center"><%@ include file="./menuSelect.jsp"%></div>
</form>
</body>