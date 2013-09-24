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
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 访问容器启动后的属性 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//获取系统属性
Properties props = System.getProperties();
//遍历系统属性
for(String name : props.stringPropertyNames())
{
	out.println(name + " --> " + props.getProperty(name)
		+ "<br />");
}
%>
</body>
</html>