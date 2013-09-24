<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2010, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>所有流拍的物品</title>
</head>
<body>
<table width="780" align="center" cellspacing="0"
	background="images/bodybg.jpg">
<tr>
<td>
<div align="center">
<h3>系统所有流拍的物品</h3>
<h:dataTable width="80%" border="1" 
	cellpadding="0"
	cellspacing="1"
	style="border:1px solid black"
	value="#{viewFail.failItems}" var="item"
	rowClasses="odd,even">
	<!-- 定义第一列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="物品名"/>
		</f:facet>
		<h:outputText value="#{item.itemName}"/>
	</h:column>
	<!-- 定义第二列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="物品种类"/>
		</f:facet>
		<h:outputText value="#{item.kind.kindName}"/>
	</h:column>
	<!-- 定义第三列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="最高竞价"/>
		</f:facet>
		<h:outputText value="#{item.maxPrice}"/>
	</h:column>
	<!-- 定义第四列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="物品备注"/>
		</f:facet>
		<h:outputText value="#{item.itemRemark}"/>
	</h:column>
</h:dataTable>
</div>
</td>
</tr>
</table>
</body>
</html>
</f:view>