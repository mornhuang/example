<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def"%>
<%@ page import="java.util.Iterator,java.util.List"%>
<%@ page import="com.taifook.adminportal.common.util.page.Page"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>
<html>
<head>
<title>Create Product</title>
<link href="<%=request.getContextPath()%>/jsp/css/link.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css"
	rel="stylesheet" type="text/css">
<script language="JavaScript"
	src="<%=request.getContextPath()%>/jsp/js/commutil.js"></script>
	<script type="text/javascript">
	function goAddAclUserProfile(){
	 document.aclUserProfileForm.action="goAddAclUserProfile.do?method=goAddAclUserProfile";
	 document.aclUserProfileForm.submit();
	}
	function comfirmToDel(elementId) {
		if (confirm("Confirm to Delete?")) {
			elementId.click();
			return true;
		} else {
			return false;
		}
	}
	function deleteAclUserProfile(aclUserId){
         if(aclUserId=="admins"){
             alert("Admin User can't delete!");
            }
         else{
         document.aclUserProfileForm.action="deleteAclUserProfile.do?method=deleteAclUserProfile&loginId="+aclUserId;
         document.aclUserProfileForm.submit();
		}
	}
	function checkSearch(){
           var lognId=document.aclUserProfileForm.lognId.value;
           if(lognId==""){
                alert("Login ID is empty!");
                return false;
               }
           return true;
		}
	</script>
</head>
<body>
<form name="aclUserProfileForm"
	action="listAclUserProfile.do?method=listAclUserProfile" method="post" onsubmit="return checkSearch();">
<table width="50%" align="left">
	<tr bgcolor="#A5B7C5">
		<td>Login Id:<html:text name="aclUserProfileForm"
			property="lognId"></html:text></td>
		<td><html:submit value="Search"></html:submit></td>
	</tr>
	<logic:present name="returnMessage">
	    <tr>
	    <td colspan="2"><font color="red">${returnMessage}</font></td>
	    </tr>
	</logic:present>
</table>
</form>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="75%" border="1" align="left" cellpadding="0"
	cellspacing="0">
	 <tr bgcolor="#A5B7C5">
	 <td colspan="8" align="center"> <STRONG> More Acl UserProfile List </STRONG></td>
	 </tr> 
	<tr bgcolor="#A5B7C5">
		<td>Login ID</td>
		<td>Role ID</td>
		<td>Deptmente</td>
		<td>Email</td>
		<td>Status</td>
		<td colspan="3" align="center">Operation</td>
	</tr>
	<%
  try{
    	Page testpage=(Page)request.getAttribute("page");
   		List result=(List)testpage.getThisPageElements();
   		pageContext.setAttribute("result",result);
       }catch(java.lang.Exception es){}
    
%>
	<c:forEach var="aclUserProfile" items="${result}">
		<tr>
			<td>&nbsp;${aclUserProfile.lognId }</td>
			<td>&nbsp;${aclUserProfile.roleId }</td>
			<td>&nbsp;${aclUserProfile.deptNme }</td>
			<td>&nbsp;${aclUserProfile.emailAddr }</td>
			<td>&nbsp;${aclUserProfile.status }</td>
			<td><html:link action="showAclUserProfile.do?method=showAclUserProfile&loginId=${aclUserProfile.lognId}">View</html:link></td>
			<td><html:link action="goUpdateAclUserProfile.do?method=goUpdateAclUserProfile&loginId=${aclUserProfile.lognId}">Update</html:link></td>
			<td>
				<c:if test="${aclUserProfile.lognId == 'admins'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				<c:if test="${aclUserProfile.lognId != 'admins'}"><a href='javascript:deleteAclUserProfile("${aclUserProfile.lognId}")' onmousedown="comfirmToDel(this)" >Delete</a></c:if>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="8">
		<div align="center"><c:catch>
			<%Page testpage=(Page)request.getAttribute("page");%>
			<def:ymshowPageSplit currentPage="<%=testpage.getThisPageNumber()%>"
				maxPage="<%=testpage.getLastPageNumber()%>"
				totalSize="<%=testpage.getTotalNumberOfElements()%>"
				url="<%=request.getContextPath()+"/listAclUserProfile.do?method=listAclUserProfile"%>"
				parameters="${URLParam}" currentPageName="currentpage" />
		</c:catch></div>
		</td>
		
	</tr>
	<tr>
		<td align="center"><input type="button" value="Add" onclick="goAddAclUserProfile();"></td>
		<td colspan="7">&nbsp;</td>
	</tr>
</table>

</body>