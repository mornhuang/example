<%@page contentType="text/html" pageEncoding="gbk"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
	<head>
		<title>添加用户</title>
	</head>
	<body>
<h1>添加用户</h1>
<h:form>
用户名：<h:inputText value="#{userBean.name}"/><br/>
儿子：<h:inputText value="#{userBean.son}" id="son"
	converterMessage="您为儿子属性输入的字符串格式不正确！">
	<f:converter converterId="sonConverter"/>
</h:inputText>（请按"姓名：身高：年龄"的格式输入）
<h:message for="son" style="color:red;font-weight:bold"/><br/>
<h:commandButton value="添加" action="#{userBean.add}"/>
</h:form>
	</body>
</html>
</f:view>
