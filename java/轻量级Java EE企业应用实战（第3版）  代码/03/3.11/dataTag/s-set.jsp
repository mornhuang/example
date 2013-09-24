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
	<title>使用s:set设置一个新变量</title>
</head>
<body>
<h2>使用s:set设置一个新变量</h2>
<!-- 使用bean标签定义一个JavaBean实例 -->
<s:bean name="org.crazyit.app.dto.Person" id="p">
	<s:param name="name" value="'yeeku'"/>
	<s:param name="age" value="29"/>
</s:bean>
将Stack Context中的p值放入默认范围（action）内。<br/>
<s:set value="#p" name="xxx"/>
Stack Context内xxx对象的name属性：<s:property value="#xxx.name"/><br/>
Stack Context内xxx对象的age属性：<s:property value="#xxx.age"/><br/>
request范围的xxx对象的name属性：${requestScope.xxx.name}<br/>
request范围的xxx对象的age属性：${requestScope.xxx.age}<hr/>
将Stack Context中的p值放入application范围内。<br/>
<s:set value="#p" name="yyy" scope="application"/>
application范围的yyy对象的name属性：${applicationScope.yyy.name}<br/>
application范围的yyy对象的age属性：${applicationScope.yyy.age}<hr/>
将Stack Context中的p值放入session范围内。<br/>
<s:set value="#p" name="zzz" scope="session"/>
session范围的zzz对象的name属性：${sessionScope.zzz.name}<br/>
session范围的zzz对象的age属性：${sessionScope.zzz.age}
</body>
</html>
