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
	<title>使用s:append标签拼接两个集合</title>
</head>
<body>
<!-- 使用append标签将两个集合拼接成新的集合，
	新集合的名字是newList，新集合放入Stack Context中 -->
<s:append var="newList">
	<s:param value="{'疯狂Java讲义',
		'轻量级Java EE企业应用实战',
		'经典Java EE企业应用实战'}" />
	<s:param value="{'http://www.crazyit.org', 
		'http://blog.crazyit.org'}" />
</s:append>
<table border="1" width="260">
<!-- 使用iterator迭代newList集合 -->
<s:iterator value="#newList" status="st" id="ele">
	<tr>
		<td><s:property value="#st.count"/></td>
		<td><s:property value="ele"/></td>
	</tr>
</s:iterator>
</table>
</body>
</html>
