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
<title>Profile Detail</title>
</head>
<body>
<div id="userId">
<table align="left">
<tr>
	<td>Client ID:</td>
	<td></td>
</tr>
</table>
</div>
<div id="rtqService">
<table width="80%" border="1" cellpadding="0" cellspacing="0">
	<th colspan="5" bgcolor="">RQT登录账户及密码</th>
	<tr bgcolor="#A5B7C5">
		<td>服务产品名称</td>
		<td>RTQ登入账户</td>
		<td>RTQ登入密码</td>
		<td>RTQ账户状态</td>
		<td>最后访问时间</td>
	</tr>
	<c:forEach var="parameter" items="${rqlServiceList}">
		<tr>
			<td>${selectServiceList.productName}</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</c:forEach>
</table>
</div>
<div id="selectService">
<table width="80%" border="1" cellpadding="0" cellspacing="0">
	<th colspan="5" bgcolor="">现有服务产品</th>
	<tr bgcolor="#A5B7C5">
		<td>服务产品名称</td>
		<td>生效日期</td>
		<td>失效日期</td>
		<td>是否允许预定</td>
		<td>产品状态</td>
	</tr>
	<c:forEach var="parameter" items="${selectServiceList}">
		<tr>
			<td>${selectServiceList.productName}</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</c:forEach>
</table>
</div>
<div id="reserveService">
<table width="80%" border="1" cellpadding="0" cellspacing="0">
	<th colspan="5" bgcolor="">预定服务产品</th>
	<tr bgcolor="#A5B7C5">
		<td>服务产品名称</td>
		<td>服务产品价格</td>
		<td>预定时间</td>
		<td>生效时间</td>
		<td>是否允许预定</td>
	</tr>
	<c:forEach var="parameter" items="${reserveServiceList}">
		<tr>
			<td>${selectServiceList.productName}</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</c:forEach>
</table>
</div>
<table>
	<tr>
		<td align="center" colspan="2"><input type="button"
			value="GoBack" onClick="history.go(-1)" /></td>
	</tr>
</table>

</body>
</html>