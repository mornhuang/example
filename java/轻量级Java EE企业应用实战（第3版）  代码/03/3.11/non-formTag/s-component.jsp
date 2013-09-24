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
	<title>使用s:component标签</title>
</head>
<body>
<h3>使用s:component标签</h3>
使用默认主题(xhtml)，默认主题目录(template)<br/>  
使用mytemplate.jsp作为视图组件
<s:component template="mytemplate.jsp">
	<s:param name="list" value="{'疯狂Java讲义'
	,'轻量级Java EE企业应用实战'
	,'经典Java EE企业应用实战'}"/>
</s:component>
<hr/>
使用自定义主题，自定义主题目录<br/>  
使用myAnotherTemplate.jsp作为视图组件
<s:component
	templateDir="myTemplateDir"
	theme="myTheme"
	template="myAnotherTemplate.jsp">
	<s:param name="list" value="{'疯狂Java讲义'
	,'轻量级Java EE企业应用实战'
	,'经典Java EE企业应用实战'}" />
</s:component>
</body>
</html>

