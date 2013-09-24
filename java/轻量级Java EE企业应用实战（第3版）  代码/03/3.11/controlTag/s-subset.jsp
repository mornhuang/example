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
	<title>使用s:subset标签截取集合元素</title>
</head>
<body>
<table border="1" width="300">
<!-- 使用subset标签截取目标集合的4个元素，从第2个元素开始截取 -->
<s:subset source="{'疯狂Java讲义'
	,'轻量级Java EE企业应用实战'
	,'经典Java EE企业应用实战'
	,'疯狂Ajax讲义'
	,'疯狂XML讲义'}" 
	start="1" count="4">
	<!-- 使用iterator标签来迭代目标集合，因为没有指定value属性值，
		故迭代ValueStack栈顶的集合 -->
	<s:iterator status="st">
		<!-- 根据当前迭代元素的索引是否为奇数决定是否使用CSS样式 -->
		<tr <s:if test="#st.odd">
			style="background-color:#bbbbbb"</s:if>>
			<td><s:property/></td>
		</tr>
	</s:iterator>
</s:subset>
</table>
</body>
</html>
