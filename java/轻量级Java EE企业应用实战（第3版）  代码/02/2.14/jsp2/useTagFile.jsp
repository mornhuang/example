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
<%@ page import="java.util.*" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>迭代器tag file</title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
	<h2>迭代器tag file</h2>
	<%
	//创建集合对象，用于测试Tag File所定义的标签
	List<String> a = new ArrayList<String>();
	a.add("hello");
	a.add("world");
	a.add("java");
	//将集合对象放入页面范围
	request.setAttribute("a" , a);
	%>
	//使用自定义标签
	<tags:iterator bgColor="#99dd99" cellColor="#9999cc"
		title="迭代器标签" bean="a" />
</body>
</html>