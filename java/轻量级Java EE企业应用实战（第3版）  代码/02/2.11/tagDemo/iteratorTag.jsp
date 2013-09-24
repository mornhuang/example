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
<%@ page import="java.util.*"%>
<!-- 导入标签库，指定mytag前缀的标签，
由http://www.crazyit.org/mytaglib的标签库处理 -->
<%@ taglib uri="http://www.crazyit.org/mytaglib" prefix="mytag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 带标签体的标签-迭代器标签 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
	<h2>带标签体的标签-迭代器标签</h2><hr/>
	<%
	//创建一个List对象
	List<String> a = new ArrayList<String>();
	a.add("疯狂Java");
	a.add("www.crazyit.org");
	a.add("java");
	//将List对象放入page范围内
	pageContext.setAttribute("a" , a);
	%>
	<table border="1" bgcolor="#aaaadd" width="300">
		<!-- 使用迭代器标签，对a集合进行迭代 -->
		<mytag:iterator collection="a" item="item">
		<tr>
			<td>${pageScope.item}</td>
		<tr>
		</mytag:iterator>
	</table>
</body>
</html>