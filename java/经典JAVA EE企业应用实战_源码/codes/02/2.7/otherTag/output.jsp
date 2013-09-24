<%@page contentType="text/html" pageEncoding="GBK"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
	<title>其他标签</title>
</head>
<body>
<h1>其他标签</h1>
输出国际化资源：<h:outputText value="#{mess.name}"/><br/>
输出Bean属性：<h:outputText value="#{user.name}" /><br/>
生成label标签：<h:outputLabel value="#{user.name}" /><br/>
生成普通超级链接：<h:outputLink value="http://www.crazyit.org">
文字链接</h:outputLink><br/>
生成普通超级链接：<h:outputLink value="http://www.crazyit.org">
<img src="http://www.crazyit.org/logo.jpg"></h:outputLink><br/>
输出带占位符的国际化消息：<h:outputFormat value="#{mess.hello}">
<f:param value="孙悟空"/>
<f:param value="www.crazyit.org"/>
</h:outputFormat>
</body>
</html>
</f:view>
