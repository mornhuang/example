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
</head>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">
<body>
<form action="">
<table align="left">
<tr bgcolor="#A5B7C5"><td colspan="2" align="center">
  <STRONG>Service Product Detail</STRONG>
</td></tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Product ID</td>
		<td>${product.prodId}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Product Status</td>
		<td>${product.prodStatus}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Service Mode</td>
		<td>${product.svcMode}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Validity of Service</td>
		<td>${product.valtOfSvc}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Price in HKD</td>
		<td>${product.priceInHkd}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Access Unit</td>
		<td>${product.acesUnit}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Billing Type</td>
		<td>${product.billType}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Discount Type</td>
		<td>${product.discType}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Effective Date</td>
		<td>${product.effDate}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Expire Date</td>
		<td>${product.exprDate}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Remark</td>
		<td>${product.remarks}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Init By</td>
		<td>${product.initBy}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Init Date</td>
		<td>${product.initDate}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Update By</td>
		<td>${product.updBy}</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Update Date</td>
		<td>${product.updDate}</td>
	</tr>
	<tr>
	<td align="center" colspan="2"><input type="button"
				value="GoBack" onClick="history.go(-1)" /></td>
	</tr>
</table>
</form>
</body>