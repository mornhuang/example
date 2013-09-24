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
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 该句加载在classes下的messages的国际化资源文件 -->
<f:loadBundle basename="messages" var="msg"/>
<html>
<head>
	<title>登录</title>
</head>
<body>
<!-- 开始使用JSF的视图输出 -->
<f:view>
<h3>
<!-- 输出国际化资源文件中的国际化信息 -->
<h:outputText value="#{msg.loginHeader}"/>
</h3>
<!-- 输出login Bean的err属性 -->
<b><h:outputText value="#{login.err}"/></b>
<h:form id="loginForm">
	<!-- 输出国际化资源文件中的国际化信息 -->
	<h:outputText value="#{msg.namePrompt}"/>
	<!-- 将下面单行输入框的值绑定到login Bean的name属性 -->
	<h:inputText value="#{login.name}" /><br/>
	<!-- 输出国际化资源文件中的国际化信息 -->
	<h:outputText value="#{msg.passPrompt}"/>
	<!-- 将下面单行输入框的值绑定到login Bean的pass属性 -->
	<h:inputText id="pass" value="#{login.pass}"/><br/>
	<!-- 将下面按钮的动作绑定到login Bean的valid方法 -->
	<h:commandButton action="#{login.valid}"
		value="#{msg.buttonTitle}" />
</h:form>
</f:view>
</body>
</html>
