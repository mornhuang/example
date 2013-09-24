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
	<title> 获取包含非西欧字符的GET请求参数 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//获取请求里包含的查询字符串
String rawQueryStr = request.getQueryString();
out.println("原始查询字符串为：" + rawQueryStr + "<hr/>");
//使用URLDecoder解码字符串
String queryStr = java.net.URLDecoder.decode(
	rawQueryStr , "gbk");
out.println("解码后的查询字符串为：" + queryStr + "<hr/>");
//以&符号分解查询字符串
String[] paramPairs = queryStr.split("&");
for(String paramPair : paramPairs)
{
	out.println("每个请求参数名、值对为：" + paramPair + "<br/>");
	//以=来分解请求参数名和值
	String[] nameValue = paramPair.split("=");
	out.println(nameValue[0] + "参数的值是：" + 
		nameValue[1]+ "<hr/>");
}
%>
</body>
</html>