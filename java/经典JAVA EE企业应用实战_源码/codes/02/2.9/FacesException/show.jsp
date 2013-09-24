<%-- 
    Document   : show
    Created on : Feb 8, 2010, 11:31:55 PM
    Author     : yeeku
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>注册结果</title>
	</head>
	<body>
		<h:messages/>

		
		<h1>注册结果</h1>
		用户名：<h:outputText value="#{userBean.name}" /><br/>
		年龄：<h:outputText value="#{userBean.age}" /><h:message for="age" showSummary="true"/><br/>
	</body>
</html>
</f:view>
