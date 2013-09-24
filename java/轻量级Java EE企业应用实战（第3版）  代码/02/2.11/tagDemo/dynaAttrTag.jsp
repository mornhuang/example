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
<!-- 导入标签库，指定mytag前缀的标签，
	由http://www.crazyit.org/mytaglib的标签库处理 -->
<%@ taglib uri="http://www.crazyit.org/mytaglib" prefix="mytag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>自定义标签示范</title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body bgcolor="#ffffc0">
<h2>下面显示的是自定义标签中的内容</h2>
<h4>指定两个属性</h4>
<mytag:dynaAttr name="crazyit" url="crazyit.org"/><br/>
<h4>指定四个属性</h4>
<mytag:dynaAttr 书名="疯狂Java讲义" 价格="99.0"
	出版时间="2008年" 描述="Java图书"/><br/>
</body>
</html>