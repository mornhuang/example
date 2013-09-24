<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>将监听器绑定到Bean属性</title>
	</head>
	<body>
		<h1><h:outputText value="猜图书"/></h1>
		<h:form prependId="false" id="addForm">
		<!-- 将下面UI组件的值绑定到Bean属性 -->
		书名：<h:inputText id="name" value="#{bookBean.name}"/><br/>
		<!-- 将下面UI组件本身绑定到Bean属性 -->
		价格：<h:inputText id="price" binding="#{bookBean.price}"/><br/>
		<h:commandButton value="处理">
			<!-- 将监听器绑定到Bean属性 -->
			<f:actionListener 
				binding="#{bookBean.listener}"/>
		</h:commandButton>
		</h:form>
	</body>
</html>
</f:view>
