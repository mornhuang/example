<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2010, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>显示结果</title>
	<meta name="website" content="http://www.crazyit.org" />
</head>	
<body>
	<h3>显示结果</h3>
	name属性值：<h:outputText value="#{test.name}" /><br/>
	疯狂Java讲义的价格：<h:outputText 
		value="#{test.books['疯狂Java讲义']}"/><br/>
	疯狂Ajax讲义的价格：<h:outputText
		value="#{test.books['疯狂Ajax讲义']}"/><br/>
	疯狂XML讲义的价格：<h:outputText 
		value="#{test.books['疯狂XML讲义']}"/><br/>
	学校列表：<h:outputText value="#{test.schools}"/>
</body>
</html>
</f:view>
