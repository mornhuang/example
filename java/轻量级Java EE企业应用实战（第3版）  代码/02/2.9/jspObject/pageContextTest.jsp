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
	<title> pageContext测试 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//使用pageContext设置属性，该属性默认在page范围内
pageContext.setAttribute("page","hello");
//使用request设置属性，该属性默认在request范围内
request.setAttribute("request","hello");
//使用pageContext将属性设置在request范围中
pageContext.setAttribute("request2","hello" 
	, pageContext.REQUEST_SCOPE);
//使用session将属性设置在session范围中
session.setAttribute("session","hello");
//使用pageContext将属性设置在session范围中
pageContext.setAttribute("session2","hello" 
	, pageContext.SESSION_SCOPE);
//使用application将属性设置在application范围中
application.setAttribute("app","hello");
//使用pageContext将属性设置在application范围中
pageContext.setAttribute("app2","hello" 
	, pageContext.APPLICATION_SCOPE);
//下面获取各属性所在的范围：
out.println("page变量所在范围：" + 
	pageContext.getAttributesScope("page") + "<br/>");
out.println("request变量所在范围：" +
	pageContext.getAttributesScope("request") + "<br/>");
out.println("request2变量所在范围："+
	pageContext.getAttributesScope("request2") + "<br/>");
out.println("session变量所在范围：" +
	pageContext.getAttributesScope("session") + "<br/>");
out.println("session2变量所在范围：" +
	pageContext.getAttributesScope("session2") + "<br/>");
out.println("app变量所在范围：" +
	pageContext.getAttributesScope("app") + "<br/>");
out.println("app2变量所在范围：" + 
	pageContext.getAttributesScope("app2") + "<br/>");
%>
</body>
</html>