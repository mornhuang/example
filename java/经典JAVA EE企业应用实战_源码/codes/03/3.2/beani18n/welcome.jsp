<%@page contentType="text/html" pageEncoding="gbk"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
	<head>
		<title>JSP Page</title>
	</head>
	<body>
	<!-- 加载国际化资源文件，加载后可通过local变量访问它 -->
	<f:loadBundle basename="local" var="local"/>	
	<!-- 使用全局资源文件中的消息 -->
	<h1><h:outputText value="#{global.title}"/></h1>
	<b>${sessionScope.tip}</b>
	<h:form>
	<!-- 使用局部资源文件中的消息 -->
	<h:outputText value="#{local.name}"/>
	<h:inputText value="#{bookBean.name}"/><br/>
	<!-- 使用局部资源文件中的消息 -->
	<h:outputText value="#{local.price}"/>
	<h:inputText value="#{bookBean.price}"/><br/>
	<h:commandButton value="#{local.process}" action="#{bookBean.process}"/><br/>
	</h:form>
	</body>
</html>
</f:view>
