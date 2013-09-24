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
<table border="1" width="240">
<!-- 使用generator标签将指定字符串解析成Iterator集合
	在generator标签内，得到的List集合位于ValueStack顶端 -->
<s:generator val="'疯狂Java讲义
	,轻量级Java EE企业应用实战,
	经典Java EE企业应用实战'" separator=",">
<!-- 没有指定迭代哪个集合，直接迭代ValueStack顶端的集合 -->
<s:iterator status="st">
	<tr <s:if test="#st.odd">
		style="background-color:#bbbbbb"</s:if>>
		<td><s:property/></td>
	</tr>
</s:iterator>
</s:generator>
</table>
</body>
</html>
