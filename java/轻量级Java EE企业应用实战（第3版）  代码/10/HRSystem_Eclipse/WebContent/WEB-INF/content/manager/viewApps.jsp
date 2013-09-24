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
   <title>处理申请</title>
</head>
<body>
<s:if test="tip!=null">
	<script>
		alert('<s:property value="tip"/>');
	</script>
</s:if>
<%@include file="../header.jsp"%>
<%@include file="mgrheader.jsp"%>
<table width=780 align="center"
	background="${pageContext.request.contextPath}/images/bodybg.jpg">
<tr>
<td><br>
<table width="80%" border="0" align="center" bgcolor="#cccccc">
  <tr bgcolor="#e1e1e1" >
	<td colspan="5" ><div class="mytitle">处理申请</div></td> 
  </tr>
  <tr class="pt9" height="30">
	<td><b>员工名</b></td>
	<td><b>缺勤类型</b></td>
	<td><b>申请类型</b></td>
	<td><b>理由</b></td>
	<td>&nbsp;</td>
  </tr>
<s:iterator value="apps" status="index">	
 	<s:if test="#index.odd == true"> 
		 <tr style="background-color:#cccccc" class="pt9" height="24">
	</s:if> 
	<s:else> 
		 <tr class="pt9" height="24">
	</s:else>
	<td><s:property value="emp"/></td>
	<td><s:property value="unAttend"/></td>
	<td><s:property value="toAttend"/></td>
	<td><s:property value="reason"/></td>
	<td>
	<a href='check.action?result=pass&appid=<s:property value="id"/>'>通过</a>&nbsp;&nbsp;
	<a href='check.action?result=deny&appid=<s:property value="id"/>'>拒绝</a>
	</td>
  </tr>
</s:iterator>
</table>
</td>
</tr>
</table>
<%@include file="../footer.jsp"%>
</body>
</html>