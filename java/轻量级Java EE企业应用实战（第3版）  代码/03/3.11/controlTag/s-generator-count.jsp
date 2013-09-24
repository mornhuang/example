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
	<title>使用s:generator生成集合</title>
</head>
<body>
<!-- 使用generator将一个字符串解析成一个集合
	，指定了var和count属性 -->
<s:generator val="'疯狂Java讲义
	,轻量级Java EE企业应用实战,
	经典Java EE企业应用实战'" separator=","
	var="books" count="2"/>
<table border="1" width="300">
<!-- 迭代输出Stack Congtext中的books集合 -->
<s:iterator value="#books">
	<tr>
		<td><s:property/></td>
	</tr>
</s:iterator>
</table>
${requestScope.books}
</body>
</html>
