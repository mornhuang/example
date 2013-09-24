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
	生日：<h:inputText value="#{userBean.birthday}" id="birthday">
		<!-- 指定用户按指定格式输入日期、时间 -->
		<f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss"/>
	</h:inputText>（请按yyyy-MM-dd HH:mm:ss格式输入）
	<!-- 指定输出birthday组件的值类型转换失败、校验失败的错误信息 -->
	<h:message for="birthday"/><br/>
	<h:commandButton value="添加" action="#{userBean.add}"/>
	</h:form>
	</body>
</html>
</f:view>
