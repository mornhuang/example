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
<title>rtqApplicationUpdate.jsp</title>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">  
<script type="text/javascript">
function checkSubmit(){
	document.rtqApplicationForm.submit();
}
</script>
</head>
<body>
<form action="updateRtqApplication.do?method=updateRtqApplication" name="rtqApplicationForm" method="post">
<table align="left">
<tr bgcolor="#A5B7C5">
	 <td colspan="2" align="center" > <STRONG>Update RTQ Application</STRONG></td>
	 </tr> 
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Product ID </td>
		<td><html:text property="prodId" name="rtqApplicationForm" readonly="true"></html:text></td>
	</tr>
    <tr>
		<td bgcolor="#A5B7C5" width="15%">RTQ Provider </td>
		<td><html:text property="rtqPrdr" name="rtqApplicationForm"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">RTQ URL </td>
		<td><html:text property="rtqUrl"  name="rtqApplicationForm" size="100"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">RTQ Status </td>
		<td><html:select property="rtqStatus" name="rtqApplicationForm" >
               <html:optionsCollection name="statusList"/>
		</html:select></td>
	</tr>
	<tr>
	<tr>
	<td><input type="submit" value="Submit" onclick="checkSubmit();" ></td>
	<td align="center" ><input type="button"
				value="GoBack" onClick="history.go(-1)" /></td>
	</tr>
</table>
</form>
</body>