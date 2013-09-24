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
物&nbsp;品&nbsp;名：<h:inputText value="#{addItem.name}"/><br />
物品描述：<h:inputText value="#{addItem.desc}"/><br />
物品备注：<h:inputText value="#{addItem.remark}"/><br />
起拍价格：<h:inputText value="#{addItem.initPrice}"/><br />
有效时间：<h:selectOneMenu value="#{addItem.avail}">
	<f:selectItem itemLabel="一天" itemValue="1"/>
	<f:selectItem itemLabel="二天" itemValue="2"/>
	<f:selectItem itemLabel="三天" itemValue="3"/>
	<f:selectItem itemLabel="四天" itemValue="4"/>
	<f:selectItem itemLabel="五天" itemValue="5"/>
	<f:selectItem itemLabel="一个星期" itemValue="6"/>
	<f:selectItem itemLabel="一个月" itemValue="7"/>
	<f:selectItem itemLabel="一年" itemValue="8"/>
</h:selectOneMenu><br />
物品种类：<h:selectOneMenu value="#{addItem.kind}" >
	<f:selectItems value="#{mgrItem.kinds}"/>
</h:selectOneMenu><br />
验&nbsp;证&nbsp;码：<h:inputText value="#{addItem.vercode}"/><br />
<h:commandButton value="添加" action="#{addItem.proAdd}"/>
</h:form>
验证码：<img id="d" src="authImg.jpg">
</div>
</td>
</tr>
</table>
</body>
</html>
</f:view>