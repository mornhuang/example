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
<f:view>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
	<title>添加消息</title>
</head>
<body>
<h3>
<h:outputText value="通过ThreadLocal使用线程安全的EntityManager"/>
</h3>
<h:form id="loginForm">
	<h:outputText value="消息标题"/>
	<!-- 将下面单行输入框的值绑定到addNews Bean的name属性 -->
	<h:inputText value="#{addNews.title}" /><br/>
	<h:outputText value="消息内容"/>
	<!-- 将下面单行输入框的值绑定到addNews Bean的pass属性 -->
	<h:inputText id="pass" value="#{addNews.content}"/><br/>
	<!-- 将下面按钮的动作绑定到addNews Bean的valid方法 -->
	<h:commandButton action="#{addNews.add}"
		value="添加" />
</h:form>
</body>
</html>
</f:view>