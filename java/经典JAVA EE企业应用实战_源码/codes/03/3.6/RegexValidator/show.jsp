<%@page contentType="text/html" pageEncoding="gbk"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view >
<html>
	<head>
		<title>添加成功</title>
	</head>
	<body>
		<h1>添加成功</h1>
		用户名：<h:outputText value="#{userBean.name}"/><br/>
		电子邮件：<h:outputText value="#{userBean.email}">
		</h:outputText>
	</body>
</html>
</f:view>
