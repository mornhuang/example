<%@page contentType="text/html" pageEncoding="gbk"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view >
<html>
	<head>
		<title>查看图书</title>
	</head>
	<body>
		<h1>查看图书</h1>
		<h:outputText value="#{bookBean.name}"/><br/>
		<h:outputText value="#{bookBean.price}">
			<!-- 转换出来的日期时间将只包括日期部分 -->
			<f:convertNumber pattern="￥0000.00"/>
		</h:outputText>
	</body>
</html>
</f:view>
