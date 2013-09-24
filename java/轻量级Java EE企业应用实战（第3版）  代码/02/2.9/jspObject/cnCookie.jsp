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
	<title> 中文Cookie </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//以编码后的字符串为值，创建一个Cookie对象
Cookie c = new Cookie("cnName" 
	, java.net.URLEncoder.encode("孙悟空" , "gbk"));
//设置Cookie对象的生存期限
c.setMaxAge(24 * 3600);
//向客户端增加Cookie对象
response.addCookie(c);

//获取本站在客户端上保留的所有Cookie
Cookie[] cookies = request.getCookies();
//遍历客户端上的每个Cookie
for (Cookie cookie : cookies)
{
	//如果Cookie的名为username，表明该Cookie是我们需要访问的Cookie
	if(cookie.getName().equals("cnName"))
	{
		//使用java.util.URLDecoder对Cookie值进行解码
		out.println(java.net.URLDecoder
			.decode(cookie.getValue()));
	}
}
%>
</body>
</html>