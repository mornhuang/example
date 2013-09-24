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
<title>员工首页</title>
</head>
<body>
<%@include file="../header.jsp"%> 
<%@include file="empheader.jsp"%> 
<table width="780" align="center" 
	 background="${pageContext.request.contextPath}/images/bodybg.jpg">
  <tr height="60">
	<td>&nbsp;</td>
  </tr>
  <tr>
	<td>
<s:if test="tip!=null">
<div class="mytitle">
	<s:property value="tip"/>
</div>
</s:if>	
	</td>
  </tr>
  <tr height="80">
	<td>&nbsp;</td>
  </tr>
  <tr>
	<td><s:property value="#session.user"/>
	，欢迎您使用JavaEE简单工作流系统，您是普通员工</td>
  </tr>
  <tr height="60">
	<td>&nbsp;</td>
  </tr>
</table>
<%@include file="../footer.jsp"%> 
</body>
</html>
