<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>JSF消息测试</title>
	</head>
	<body>
		<h1><h:outputText value="用户注册"/></h1>
		<h:messages/>
		<h:form>
		<!-- 将下面UI组件的值绑定到Bean属性 -->
		用户名：<h:inputText value="#{userBean.name}"/><br/>
		<!-- 将下面UI组件的值绑定到Bean属性 -->
		年龄：<h:inputText id="age" value="#{userBean.age}"/><br/>
		<h:commandButton value="注册" action="#{userBean.regist}"/><br/>
		</h:form>
	</body>
</html>
</f:view>
