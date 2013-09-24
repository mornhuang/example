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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> Java Bean测试 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<!-- 创建lee.Person的实例，该实例的实例名为p1 -->
<jsp:useBean id="p1" class="lee.Person" scope="page"/>
<!-- 设置p1的name属性值 -->
<jsp:setProperty name="p1" property="name" value="wawa"/>
<!-- 设置p1的age属性值 -->
<jsp:setProperty name="p1" property="age" value="23"/>
<!-- 输出p1的name属性值 -->
<jsp:getProperty name="p1" property="name"/><br/>
<!-- 输出p1的age属性值 -->
<jsp:getProperty name="p1" property="age"/>
</body>
</html>