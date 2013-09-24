<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--
	This file is an entry point for JavaServer Faces application.
--%>
<f:view>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>JSP Page</title>
	</head>
	<body>
		<h1><h:outputText value="添加图书"/></h1>
		<h:form>
		书名：<h:inputText label="书名：" value="#{bookBean.name}"/><br/>
		ISBN：<h:inputText label="ISBN：" value="#{bookBean.isbn}"/><br/>
		简介：<h:inputTextarea rows="4" cols="50" label="描述："
			value="#{bookBean.desc}"/><br/>
		<h:commandButton value="添加" action="#{bookBean.addBook}"/><br/>
		</h:form>
	</body>
</html>
</f:view>
