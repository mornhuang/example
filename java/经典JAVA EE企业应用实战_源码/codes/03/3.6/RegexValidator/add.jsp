<%@page contentType="text/html" pageEncoding="gbk"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="crazyit" uri="http://www.crazyit.org/taglib"%>

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
电子邮件：<h:inputText value="#{userBean.email}" id="email">
	<!-- 使用专门的标签来引用指定输入校验器 -->
	<crazyit:regexValidator 
		pattern="/w+([-+.]/w+)*@/w+([-.]/w+)*/./w+([-.]/w+)*"/>
</h:inputText>
<h:message for="email" style="color:red;font-weight:bold"/><br/>
<h:commandButton value="添加" action="#{userBean.add}"/>
</h:form>
	</body>
</html>
</f:view>
