<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>登录</title>
	</head>
	<body>
		<h1><h:outputText value="用户登录"/></h1>
		<h:form>
		用户名：<h:inputText value="#{loginBean.name}"/><br/>
		密码：<h:inputText value="#{loginBean.pass}"/><br/>
		<h:commandButton value="登录" action="#{loginBean.process}"/>
		</h:form>
	</body>
</html>
</f:view>
