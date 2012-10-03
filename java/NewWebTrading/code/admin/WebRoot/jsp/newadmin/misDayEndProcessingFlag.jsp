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
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">

</head>
<body>
<br/><br/><br/><br/><br/><br/>
<form action="updateMisDayEndProcessingFlag.do?method=updateMisDayEndProcessingFlag" method="post">
<table border="1" align="left">
<tr bgcolor="#A5B7C5">
<td align="center"><strong>Change Day End Processing Status</strong></td>
</tr>
<logic:present name="returnMessage">
<tr>
<td><font color="red">${returnMessage}</font></td>
</tr>
</logic:present>
<tr>
   <td bgcolor="#A5B7C5">Day End Processing Status &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <html:select property="dayEndFlag" name="rtqAccountForm">
    	<html:option value="Y">In Processing</html:option>
		<html:option value="N">Not In Process</html:option>
    </html:select>
   </td>
</tr>
<tr>
 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <html:submit value="Change Status"></html:submit></td>
</tr>
</table>
</form>
</body>
</html>