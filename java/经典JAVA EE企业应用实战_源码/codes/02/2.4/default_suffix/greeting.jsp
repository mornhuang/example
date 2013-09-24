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
<!--该句绑定在classes下的messages的资源文件-->
<f:loadBundle basename="messages" var="msg"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>登录成功</title>
</head>    
<body>
<f:view>
	 <h3><h:outputText value="#{msg.greeting}"/></h3>
	 <h:outputText value="#{msg.namePrompt}"/>
	 <h:outputText value="#{login.name}" /><br/>
	 <h:outputText value="#{msg.passPrompt}"/>
	 <h:outputText value="#{login.pass}" />
	 <h:outputText value="#{msg.sign}"/>
</f:view>
</body>
</html> 