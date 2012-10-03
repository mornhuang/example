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
<link href="<%=request.getContextPath()%>/jsp/css/link.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css"
	rel="stylesheet" type="text/css">
</head>
<body>
<p>&nbsp;</p>

<table align="left">
	<tr>
		<td>
		<table align="left" width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr bgcolor="#A5B7C5">
				<td colspan="5"><STRONG>RTQ登录名称及密码 </STRONG></td>
			</tr>
			<tr bgcolor="#A5B7C5">
				<td>Product ID</td>
				<td>RTQ Login ID</td>
				<td>PassWord</td>
				<td>最后访问时间</td>
			</tr>
			<logic:present name="rtqAccAssList">
				<logic:iterate indexId="ids" id="rtqAccAss" name="rtqAccAssList">
					<tr>
						<td>${rtqAccAss.productId}</td>
						<td>${rtqAccAss.rtqLoginId}</td>
						<td>${rtqAccAss.rtqLoginPwd}</td>
						<td>${rtqAccAss.lastAccessTime}&nbsp;</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
		<table align="left" width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr bgcolor="#A5B7C5">
				<td colspan="5"><STRONG>现有服务</STRONG></td>
			</tr>
			<tr bgcolor="#A5B7C5">
				<td>Product ID</td>
				<td>Effective Date</td>
				<td>Expire Date</td>
				<td>Allow Renewal</td>
				<td>User Product Status</td>
			</tr>
			<logic:present name="existUserProductList">
				<logic:iterate indexId="ids" id="userProduct"
					name="existUserProductList">
					<tr>
						<td>${userProduct.id.prodId}</td>
						<td><bean:write name="userProduct" property="updDate" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><bean:write name="userProduct" property="exprDate" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${userProduct.allwRenl}</td>
						<td>${userProduct.status}</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
		<table align="left" width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr bgcolor="#A5B7C5">
				<td colspan="5"><STRONG>预定服务</STRONG></td>
			</tr>
			<tr bgcolor="#A5B7C5">
				<td>Product ID</td>
				<td>Allot Date</td>
				<td>Effective Date</td>
				<td>服务产品价格</td>
				<td>Allow Renewal</td>
			</tr>
			<logic:present name="userProductAllotList">
				<logic:iterate indexId="ids" id="userProductAllot"
					name="userProductAllotList">
					<tr>
						<td>${userProductAllot.prodId}</td>
						<td><bean:write name="userProductAllot" property="alltTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><bean:write name="userProductAllot" property="effDate" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${userProductAllot.priceInHkd}&nbsp;</td>
						<td>${userProductAllot.alltStatus}&nbsp;</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
		</td>
	</tr>
</table>
</body>