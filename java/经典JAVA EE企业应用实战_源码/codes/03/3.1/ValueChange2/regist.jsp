<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>处理值改变事件的监听器</title>
	</head>
	<body>
		<h1><h:outputText value="注册用户"/></h1>
		<h:form id="registForm" prependId="false">
		<!-- 将下面UI组件的值绑定到Bean属性 -->
		用户名：<h:inputText value="#{registBean.name}">
		<!-- 使用标签来绑定事件监听器 -->
		<f:valueChangeListener
			type="org.crazyit.jsf.CrazyValueChangeListener"/> 
		</h:inputText>
		<h:outputText id="tip" rendered="false" style="color:red" 
		binding="#{registBean.tip}"/><br/>
		密码：<h:inputSecret value="#{registBean.pass}"/><br/>
		<h:commandButton value="注册"/>
		</h:form>
	</body>
</html>
</f:view>
