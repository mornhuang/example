<%@page contentType="text/html" pageEncoding="gbk"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view >
<html>
	<head>
		<title>备案成功</title>
	</head>
	<body>
		<h1>备案成功</h1>
		网站域名：<h:outputText value="#{registBean.name}"/><br/>
		运行时间：<h:outputText value="#{registBean.duration}"/>年<br/>
		每年投入：<h:outputText value="#{registBean.cost * 1000}"/>元
	</body>
</html>
</f:view>
