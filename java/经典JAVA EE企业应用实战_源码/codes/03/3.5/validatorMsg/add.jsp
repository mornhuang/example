<%@page contentType="text/html" pageEncoding="gbk"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
	<head>
		<title>添加备案</title>
	</head>
	<body>
	<h1>添加备案</h1>
	<h:form prependId="false">
	网站域名：<h:inputText value="#{registBean.name}"
		id="name">
		<f:validateLength minimum="5" maximum="25"/>
	</h:inputText>
	<h:message for="name"/><br/>
	运行时间（年）：<h:inputText value="#{registBean.duration}"
		id="duration">
		<f:validateLongRange minimum="0" maximum="30"/>
	</h:inputText>
	<h:message for="duration"/><br/>
	投入费用（千元/年）：<h:inputText value="#{registBean.cost}"
		id="cost">
		<f:validateDoubleRange minimum="0" maximum="20"/>
	</h:inputText>
	<h:message for="cost"/><br/>
	<h:commandButton value="添加备案" action="#{registBean.add}"/>
	</h:form>
	</body>
</html>
</f:view>
