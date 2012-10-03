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
<script type="text/javascript">
function goAddProduct(){
 document.productForm.action="goAddProduct.do?method=goAddProduct";
 document.productForm.submit();
}
</script>
</head>
<body>
<form name="productForm" action="" method="post">
<table width="100%" border="1" cellspacing="0" cellpadding="0" align="left">
<tr bgcolor="#A5B7C5">
  <td colspan="8" align="center"> <STRONG> More Service Product List </STRONG></td>
	<tr bgcolor="#A5B7C5">
		<td>Product ID</td>
		<td>Service Mode</td>
		<td>Validity of Service</td>
		<td>Price in HK</td>
		<td>Discount Type</td>
		<td>Product Status</td>
		<td colspan="2">Operation</td>
	</tr>
	<c:forEach var="product" items="${productList}">
		<tr>
			<td>&nbsp;${product.prodId}</td>
			<td>&nbsp;${product.svcMode}</td>
			<td>&nbsp;${product.valtOfSvc}</td>
			<td>&nbsp;${product.priceInHkd}</td>
			<td>&nbsp;${product.discType}</td>
			<td>&nbsp;${product.prodStatus}</td>
			<td><a href="<%=request.getContextPath()%>/showProduct.do?method=showProduct&prodId=${product.prodId}">view</a></td>
			<td><a href="<%=request.getContextPath()%>/goUpdateProduct.do?method=goUpdateProduct&prodId=${product.prodId}">update</a></td>
		</tr>
	</c:forEach>
	<tr>
	<td><input type="button" value="Add Product" onclick="goAddProduct();"></input></td>
	</tr>
</table>
</form>
</body>