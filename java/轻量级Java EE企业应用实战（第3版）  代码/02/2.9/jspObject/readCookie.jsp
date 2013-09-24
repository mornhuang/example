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
	<title> 读取Cookie </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//获取本站在客户端上保留的所有Cookie
Cookie[] cookies = request.getCookies();
//遍历客户端上的每个Cookie
for (Cookie c : cookies)
{
	//如果Cookie的名为username，表明该Cookie是我们需要访问的Cookie
	if(c.getName().equals("username"))
	{
		out.println(c.getValue());
	}
}
%>
</body>
</html>