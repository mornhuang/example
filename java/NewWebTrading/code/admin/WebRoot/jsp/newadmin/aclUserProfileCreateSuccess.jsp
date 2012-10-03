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
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="50%" align="center">
<tr bgcolor="#A5B7C5"><td colspan="2">
  <STRONG>Acl UserProfile Create Success</STRONG>
</td></tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Login ID</td>
		<td>${loginId}</td>
	</tr>
    <tr>
		<td bgcolor="#A5B7C5" width="15%">PassWord</td>
		<td>${passWord}</td>
	</tr>
	<tr>
	<td  colspan="2" >
   <html:link action="listAclUserProfile.do?method=listAclUserProfile">Acl UserProfile List</html:link>
    </td>
	</tr>
</table>
</body>