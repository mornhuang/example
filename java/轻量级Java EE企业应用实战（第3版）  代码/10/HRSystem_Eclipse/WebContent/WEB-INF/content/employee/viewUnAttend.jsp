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
   <title>查看自己的非正常出勤</title>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="empheader.jsp"%>
<table width="780" align="center"
	background="${pageContext.request.contextPath}/images/bodybg.jpg">
<tr>
<td>
<table width="80%" border="0" align="center" bgcolor="#cccccc">
  <tr bgcolor="#e1e1e1" >
	<td colspan="4" ><div class="mytitle">当前用户：<s:property value="#session.user"/></div></td> 
  </tr>
  <tr bgcolor="#e1e1e1" >
	<td colspan="4" >您只能查看最近三天的非正常打卡，如有异议，请立即向经理申请</td> 
  </tr>
  <tr class="pt9" height="30">
	<td width="29%"><b>打卡日期</b></td>
	<td width="20%"><b>异动名称</b></td>
	<td width="26%"><b>打卡时间</b></td>
	<td width="25%">&nbsp;</td>
  </tr>
<s:iterator value="unAttend" status="index">  
 	<s:if test="#index.odd == true"> 
		 <tr style="background-color:#dddddd" class="pt9" height="24">
	</s:if> 
	<s:else> 
		 <tr class="pt9" height="24">
	</s:else> 
	<td><s:property value="dutyDay"/></td>
	<td><s:property value="unType"/></td>
	<td><s:property value="time"/></td>
	<td><a href='appChange.action?attid=<s:property value="id"/>'>申请改变</a></td>
</tr>
</s:iterator>
</table>
</td>
</tr>
</table>
<%@include file="../footer.jsp"%>
</body>
</html>