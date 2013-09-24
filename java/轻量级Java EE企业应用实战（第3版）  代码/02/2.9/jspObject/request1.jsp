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
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 获取请求头/请求参数 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//获取所有请求头的名称
Enumeration<String> headerNames = request.getHeaderNames();
while(headerNames.hasMoreElements())
{
	String headerName = headerNames.nextElement();
	//获取每个请求、及其对应的值
	out.println(
		headerName + "-->" + request.getHeader(headerName) + "<br/>");
}
out.println("<hr/>");
//设置解码方式，对于简体中文，使用gb2312解码
request.setCharacterEncoding("gb2312");
//下面依次获取表单域的值
String name = request.getParameter("name");
String gender = request.getParameter("gender");
//如果某个请求参数有多个值，将使用该方法获取多个值
String[] color = request.getParameterValues("color");
String national = request.getParameter("country");
%>
<!-- 下面依次输出表单域的值 -->
您的名字：<%=name%><hr/>
您的性别：<%=gender%><hr/>
<!-- 输出复选框获取的数组值 -->
您喜欢的颜色：<%for(String c : color)
{out.println(c + " ");}%><hr/>
您来自的国家：<%=national%><hr/>
</body>
</html>