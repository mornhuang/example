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
	<title>添加新消息</title>
</head>
<body>
<f:view>
<h3>
<!-- 输出国际化资源文件中的国际化信息 -->
<h:outputText value="#{msg.loginHeader}"/>
</h3>
<h:form id="loginForm">
	<!-- 输出国际化资源文件中的国际化信息 -->
	<h:outputText value="#{msg.namePrompt}"/>
	<!-- 将下面单行输入框的值绑定到addNews Bean的name属性 -->
	<h:inputText value="#{addNews.title}" /><br/>
	<!-- 输出国际化资源文件中的国际化信息 -->
	<h:outputText value="#{msg.contentPrompt}"/>
	<!-- 将下面单行输入框的值绑定到addNews Bean的pass属性 -->
	<h:inputText id="pass" value="#{addNews.content}"/><br/>
	<!-- 将下面按钮的动作绑定到addNews Bean的addNews方法 -->
	<h:commandButton action="#{addNews.addNews}"
		value="#{msg.buttonTitle}" />
</h:form>
</f:view>
</body>
</html>
