<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def" %>
<%@ page import="java.util.Iterator,java.util.List"%>
<%@ page import="com.taifook.adminportal.common.util.page.Page"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>
<html>
<head>
<title>aclRoleList.jsp</title>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">   
<script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/commutil.js"></script>

<script type="text/javascript">
function addAclRole(){
   document.aclRoleForm.action="goAddAclRole.do?method=goAddAclRole";
   document.aclRoleForm.submit();
}

</script>
</head>
<body > 
<table>
<tr><%
  String message=(String) request.getAttribute("message");
  pageContext.setAttribute("message",message);
  %>
<td><font color="#FF0000">${message}</font></td>
</tr>
</table>     
<form  name="aclRoleForm" action="listAclRole.do?method=listAclRole" method="post">
<table width="50%" align="left">
	<tr bgcolor="#A5B7C5">
		<td>Role ID:<html:text property="roleId"  name="aclRoleForm" size="15" onkeypress="return searchKeydowm(event);"></html:text> 
			</td>
		<td><input type="submit" name="Submit" value="Search"></td>
	</tr>
</table>
</form>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="15%" border="1" align="left" cellpadding="0" cellspacing="0">
	<tr bgcolor="#A5B7C5">
		<td width="2%">Role ID </td>
		<td width="10%">Description </td>
		<td width="3%" colspan="3" align="center">Operation</td>
	</tr>
   	<%
  try{
    	Page testpage=(Page)request.getAttribute("page");
   		List result=(List)testpage.getThisPageElements();
   		pageContext.setAttribute("result",result);
       }catch(java.lang.Exception es){}
    
%>
	<c:forEach var="aclRole" items="${result}">
		<tr>
			<td>&nbsp;${aclRole.roleId}</td>
			<td>&nbsp;${aclRole.descr}</td>
			<td><html:link action="showAclRole.do?method=showAclRole&roleId=${aclRole.roleId}">View</html:link></td>
			<td>
				<c:if test="${aclRole.roleId != 'admin'}">
					<html:link action="goUpdateAclRole.do?method=goUpdateAclRole&roleId=${aclRole.roleId}">Update</html:link>
				</c:if>
				<c:if test="${aclRole.roleId == 'admin'}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
			</td>
			<td>
				<c:if test="${aclRole.roleId != 'admin'}">
					<html:link action="deleteAclRole.do?method=deleteAclRole&roleId=${aclRole.roleId}">Delete</html:link>
				</c:if>
				<c:if test="${aclRole.roleId == 'admin'}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
			</td>
		</tr>
	</c:forEach>
	<tr> 
    <td colspan="5">
    	<div align="center">
    	<c:catch>
			<%Page testpage=(Page)request.getAttribute("page");%>
		    <def:ymshowPageSplit currentPage="<%=testpage.getThisPageNumber()%>" maxPage="<%=testpage.getLastPageNumber()%>" totalSize="<%=testpage.getTotalNumberOfElements()%>" url="<%=request.getContextPath()+"/listAclRole.do?method=listAclRole"%>" parameters="${URLParam}" currentPageName="currentpage" />
	    </c:catch>
	    </div>
	</td>
  </tr>
  <tr>
  	<td align="center" ><input type="button" value="Add" onclick="addAclRole();" /></td>
			<td colspan="4">&nbsp;</td>
	</tr>
</table>
</body>