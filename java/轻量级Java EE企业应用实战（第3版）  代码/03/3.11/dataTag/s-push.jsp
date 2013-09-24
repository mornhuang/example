<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2012, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>使用s:push来将某个值放入ValueStack的栈顶</title>
</head>
<body>
<h2>使用s:push来将某个值放入ValueStack的栈顶</h2>
<!-- 使用bean标签创建一个JavaBean实例，
	指定var属性，并将其放入Stack Context中 -->
<s:bean name="org.crazyit.app.dto.Person" var="p">
	<s:param name="name" value="'yeeku'"/>
	<s:param name="age" value="29"/>
</s:bean>
<!-- 将Stack Context中的p对象放入ValueStack栈顶-->
<s:push value="#p">
	<!-- 输出ValueStack栈顶对象的name和age属性 -->
	ValueStack栈顶对象的name属性：<s:property value="name"/><br/>
	ValueStack栈顶对象的age属性：<s:property value="age"/><br/>
</s:push>
</body>
</html>
