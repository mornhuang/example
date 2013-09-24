<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2012, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=gb2312" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>电子打卡</title>
</head>
<body>
<%@include file="../header.jsp"%> 
<%@include file="empheader.jsp"%> 
<table width="780" align="center"
	background="${pageContext.request.contextPath}/images/bodybg.jpg">
  <tr>
	<td colspan="3"><br/><br/><div class="mytitle">电子打卡系统</div></td>
  </tr>
  <tr>
	<td colspan="3" style="text-align : center;">
		<br/><br/><br/>
<!-- 当punchIsValid为1、3时，可上班打卡 -->
<s:if test="punchIsValid==1 
	|| punchIsValid==3">
<s:form action="employeeCome" theme="simple">
	<s:submit key="come.punch"/>
</s:form>
</s:if>
<!-- 当punchIsValid为2、3时，可下班打卡 -->	
<s:if test="punchIsValid==2
	|| punchIsValid==3">
<s:form action="employeeLeave" theme="simple">
	<s:hidden value="leave"/>
	<s:submit key="leave.punch"/>
</s:form>
</s:if>
<br/>
</div>
	</td>
  </tr>
</table>
<%@include file="../footer.jsp"%> 
</body>
</html>
