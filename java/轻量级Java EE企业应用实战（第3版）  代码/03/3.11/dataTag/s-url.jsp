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
	<title>使用s:url来生成一个URL地址</title>
</head>
<body>
<h2>s:url来生成一个URL地址</h2>
只指定value属性的形式。<br/>
<s:url value="editGadget.action"/>
<hr/>
指定action属性,且使用param传入参数的形式。<br/>
<s:url action="showBook">
	<s:param name="author" value="'yeeku'" />
</s:url>
<hr/>
既不指定action属性,也不指定value属性,且使用param传入参数的形式。<br/>
<s:url includeParams="get"  >
	<s:param name="id" value="%{'22'}"/>
</s:url>
<hr/>
同时指定action属性和value属性,且使用param传入参数的形式。<br/>
<s:url action="showBook" value="xxxx">
	<s:param name="author" value="'yeeku'" />
</s:url>
</body>
</html>
