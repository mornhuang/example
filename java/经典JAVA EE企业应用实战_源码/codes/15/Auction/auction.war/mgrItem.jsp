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
	<title>管理自己的拍卖物品</title>
</head>
<body>
<table width="780" align="center" cellspacing="0"
	background="images/bodybg.jpg">
<tr>
<td>
<div align="center">
<h3>您当前的拍卖物品：</h3>
<h:dataTable width="80%" border="1" 
	cellpadding="0"
	cellspacing="1"
	style="border:1px solid black"
	value="#{mgrItem.items}" var="item"
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
<tr>
<td>
<h3>添加新物品</h3>
<div align="center">
<h:outputText value="#{addItem.tipInfo}" styleClass="tip"/>
<h:form>
<table width="50%">
<tr>
	<td>物&nbsp;品&nbsp;名：</td>
	<td><h:inputText id="name" value="#{addItem.name}"
		required="true"
		requiredMessage="物品名必须填写！"
		validatorMessage="物品名长度必须在2～20之间">
		<f:validateLength minimum="2" maximum="20"/>
		</h:inputText></td>
	<td><h:message for="name" style="color:red"/></td>	
</tr>
<tr>
	<td>物品描述：</td>
	<td><h:inputText id="desc" value="#{addItem.desc}">
		</h:inputText></td>
	<td>&nbsp;</td>	
</tr>
<tr>
	<td>物品备注：</td>
	<td><h:inputText id="remark" value="#{addItem.remark}">
		</h:inputText></td>
	<td>&nbsp;</td>	
</tr>
<tr>
<td>起拍价格：</td>
	<td><h:inputText id="initPrice" value="#{addItem.initPrice}"
		required="true"
		requiredMessage="物品的起拍价格必须填写！"
		converterMessage="您输入的起拍价格无效！">
		</h:inputText></td>
	<td><h:message for="initPrice" style="color:red"/></td>
</tr>
<tr>
	<td>有效时间：</td>
	<td><h:selectOneMenu value="#{addItem.avail}">
		<f:selectItem itemLabel="一天" itemValue="1"/>
		<f:selectItem itemLabel="二天" itemValue="2"/>
		<f:selectItem itemLabel="三天" itemValue="3"/>
		<f:selectItem itemLabel="四天" itemValue="4"/>
		<f:selectItem itemLabel="五天" itemValue="5"/>
		<f:selectItem itemLabel="一个星期" itemValue="6"/>
		<f:selectItem itemLabel="一个月" itemValue="7"/>
		<f:selectItem itemLabel="一年" itemValue="8"/>
	</h:selectOneMenu></td>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>物品种类：</td>
	<td><h:selectOneMenu value="#{addItem.kind}" >
		<f:selectItems value="#{mgrItem.kinds}"/>
	</h:selectOneMenu></td>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>验证码：</td>
	<td><h:inputText id="vercode" value="#{addItem.vercode}"
	required="true"
	requiredMessage="验证码必须填写！"
	validatorMessage="验证码长度必须等于6">
	<f:validateLength minimum="6" maximum="6"/>
	</h:inputText></td>
	<td><h:message for="vercode" style="color:red"/></td>
</tr>
<tr>
<td colspan="3">
<h:commandButton value="添加" action="#{addItem.proAdd}"/></td>
</tr>
</table>
</h:form>
验证码：<img id="d" src="authImg.jpg">
</div>
</td>
</tr>
</table>
</body>
</html>
</f:view>