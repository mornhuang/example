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
	<title>使用s:merge标签迭代Map</title>
</head>
<body>
<s:merge id="newList">
	<s:param value="#{'疯狂Java讲义':'李刚',
		'轻量级Java EE企业应用实战':'李刚',
		'经典Java EE企业应用实战':'李刚'}" />
	<s:param value="#{'http://www.crazyit.org', 
		'http://blog.crazyit.org'}" />
</s:merge>

<table border="1" width="320">
<s:iterator value="#newList" status="st">
	<tr <s:if test="#st.odd">style="background-color:#bbbbbb"</s:if>>
		<td><s:property value="key"/></td>
		<td><s:property value="value"/></td>
	</tr>
</s:iterator>
</table>
</body>
</html>
