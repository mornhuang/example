<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2010, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<!-- 导入Struts 2标签库 -->
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
	<title>查看所有消息</title>
</head>
<body>
<h1><s:text name="succTitle"/></h1>
<h2>所有消息</h2>
<table border="1" width="660">
	<tr>
	<th>消息ID</th>
	<th>消息标题</th>
	<th>消息内容</th>
	</tr
	<!-- 迭代输出ValueStack中的newsList对象，其中status是迭代的序号 -->
	<s:iterator value="newsList" status="index">
	<!-- 判断序号是否为奇数 -->
	<s:if test="#index.odd == true"> 
	<tr style="background-color:#ccffcc">
	</s:if>
	<!-- 判断迭代的序号是否为偶数 -->
	<s:else> 
	<tr>
	</s:else>
		<td><s:property value="id"/></td>
		<td><s:property value="title"/></td>
		<td><s:property value="content"/></td>
	</tr>
   	</s:iterator>
</table>
</body>
</html>
