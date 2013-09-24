<%@page contentType="text/html" pageEncoding="GBK"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title>JSP Page</title>
	</head>
	<body>
		<h1><h:outputText value="猜图书"/></h1>
		<b>${sessionScope.tip}</b>
		<h:form>
		<!-- 将下面UI组件的值绑定到Bean属性 -->
		书名：<h:inputText value="#{bookBean.name}"/><br/>
		<!-- 将下面UI组件本身绑定到Bean属性 -->
		价格：<h:inputText value="#{bookBean.price}"/><br/>
		<h:commandButton value="处理" action="#{bookBean.process}"/><br/>
		</h:form>
	</body>
</html>
</f:view>
