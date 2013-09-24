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
	<title>s:itertor标签测试</title>
</head>
<body>
<table border="1" width="300">
<!-- 迭代输出List集合 -->
<s:iterator value="{'疯狂Java讲义',
	'轻量级Java EE企业应用实战',
	'经典Java EE企业应用实战'}"
	id="name" status="st">
	<tr <s:if test="#st.odd">
		style="background-color:#bbbbbb"</s:if>>
		<td><s:property value="name"/></td>
	</tr>
</s:iterator>
</table>
<table border="1" width="350">
	<tr>
		<th>书名</th>
		<th>作者</th>
	</tr>
<!-- 对指定的Map对象进行迭代输出,并指定status属性 -->
<s:iterator value="#{'疯狂Java讲义':'李刚',
	'轻量级Java EE企业应用实战':'李刚' ,
	'经典Java EE企业应用实战':'李刚'}"
	id="score" status="st">
	<!-- 根据当前被迭代元素的索引是否为奇数来决定是否使用背景色 -->
	<tr <s:if test="#st.odd">
		style="background-color:#bbbbbb"</s:if>>
		<!-- 输出Map对象里Entry的key -->
		<td><s:property value="key"/></td>
		<!-- 输出Map对象里Entry的value -->
		<td><s:property value="value"/></td>
	</tr>
</s:iterator>
</table>
</body>
</html>
