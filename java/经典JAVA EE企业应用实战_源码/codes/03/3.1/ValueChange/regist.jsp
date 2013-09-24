<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>处理值改变事件的方法</title>
	</head>
	<body>
		<h1><h:outputText value="注册用户"/></h1>
		<h:form>
		<!-- 将下面UI组件的值绑定到Bean属性 -->
		<!-- 使用valueChangeListener属性指定用托管Bean的
			方法来监听该组件的值改变事件 -->
		用户名：<h:inputText value="#{registBean.name}"
		valueChangeListener="#{registBean.judgeName}"/>
		<h:outputText rendered="false" style="color:red" 
			binding="#{registBean.tip}"/><br/>
		密码：<h:inputSecret value="#{registBean.pass}"/><br/>
		<h:commandButton value="注册"/>
		</h:form>
	</body>
</html>
</f:view>
