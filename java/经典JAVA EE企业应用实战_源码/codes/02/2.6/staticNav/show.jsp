<%-- 
    Document   : show
    Created on : Feb 8, 2010, 11:31:55 PM
    Author     : yeeku
--%>

<%@page contentType="text/html" pageEncoding="GBK"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>您的输入</title>
	</head>
	<body>
		<h1>您的输入</h1>
		书名：<h:outputText value="#{bookBean.name}" /><br/>
		价格：<h:outputText value="#{bookBean.price}" /><br/>
	</body>
</html>
</f:view>
