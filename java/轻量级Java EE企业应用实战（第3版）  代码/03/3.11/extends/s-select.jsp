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
	<title>使用自定义模板来生成下拉列表</title>
</head>
<body>
<!-- 使用select标签生成一个列表框，指定使用lee的主题 -->
<s:select name="aa" theme="lee" list="{'疯狂Java讲义'
	,'轻量级Java EE企业应用实战'
	,'经典Java EE企业应用实战',
	'疯狂Ajax讲义'}" size="5"/>
</body>
</html>
