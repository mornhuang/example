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
	<title>管理拍卖种类</title>
</head>
<body>
<table width="780" align="center" 
	cellspacing="0" background="images/bodybg.jpg">
<tr>
<td>
<div align="center">
<h3>当前系统中已有的物品种类</h3>
<h:dataTable width="80%" border="1"
	cellpadding="0"
	cellspacing="1"
	style="border:1px solid black"
	value="#{mgrKind.kinds}" var="kind"
	rowClasses="odd,even">
	<!-- 定义第一列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="种类名"/>
		</f:facet>
		<h:outputText value="#{kind.kindName}"/>
	</h:column>
	<!-- 定义第二列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="种类描述"/>
		</f:facet>
		<h:outputText value="#{kind.kindDesc}"/>
	</h:column>
</h:dataTable>
</div>
<div align="center">
<h3>添加新种类</h3>
<div align="center">
<h:outputText value="#{addKind.errInfo}" styleClass="tip"/>
<h:form>
	种&nbsp;类&nbsp;名：<h:inputText value="#{addKind.name}"/><br/>
	种类描述：<h:inputText value="#{addKind.desc}"/><br/>
	验&nbsp;证&nbsp;码：<h:inputText value="#{addKind.vercode}"/><br/>
	<h:commandButton value="添加种类" action="#{addKind.proAdd}"/>
</h:form>
验证码：<img id="d" src="authImg.jpg">
</div>
</td>
</tr>
</table>
</body>
</html>
</f:view>