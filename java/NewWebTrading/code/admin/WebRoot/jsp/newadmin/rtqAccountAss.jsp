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

</script>
</head>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">   
<script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/commutil.js"></script>
<body>
<table align="left" width="50%" border="0" cellpadding="0" cellspacing="0">
			 <tr>
				<td >
				|&nbsp;<html:link action="listRTQAccountView.do?method=listRTQAccountView"><font color="blue">RTQ Account Usage Enquiry</font></html:link>
				&nbsp;|&nbsp;<html:link action="listRtqAccountAssignment.do?method=listRtqAccountAssignment"><font color="blue">RTQ Account Assignment Enquiry</font></html:link>
				&nbsp;|&nbsp;<html:link action="listRtqAccount.do?method=listRtqAccount"><font color="blue">RTQ Account Maintenance</font></html:link>
				</td>
			 </tr>
	</table>
<p>&nbsp;</p>
<table align="left" width="62%" border="0" cellpadding="0" cellspacing="0">
<tr bgcolor="#A5B7C5">
		<td colspan="5" align="center"><STRONG>${productId} RTQ Account</STRONG></td>
	</tr>
			<tr bgcolor="#A5B7C5">
			    <td width="2%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td width="15%">Product ID</td>
				<td width="15%">RTQ Login ID</td>
				<td width="15%">RTQ Login Pwd</td>
				<td width="15%">Client ID</td>
			</tr>
			<logic:present name="rtqAccountAssDtoList" scope="request">
			   <logic:iterate id="rtqAccountAssDto" name="rtqAccountAssDtoList" indexId="idIndex">
				<tr>
				   <td>${idIndex+1}</td>
					<td>${rtqAccountAssDto.productId}</td>
					<td>${rtqAccountAssDto.rtqLoginId}</td>
					<td>${rtqAccountAssDto.rtqLoginPwd}</td>
					<td>${rtqAccountAssDto.clientId}</td>
				</tr>
				</logic:iterate>
			</logic:present>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
</body>
</html>