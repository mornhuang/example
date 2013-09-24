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
	<title> request处理 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
//获取请求的钱数
String bal = request.getParameter("balance");
//将钱数的字符串转换成双精度浮点数
double qian = Double.parseDouble(bal);	
//对取出的钱进行判断
if (qian < 500)
{
	out.println("给你" + qian + "块");
	out.println("账户减少" + qian);
}
else
{
	//创建了一个List对象
	List<String> info = new ArrayList<String>();
	info.add("1111111");
	info.add("2222222");
	info.add("3333333");
	//将info对象放入request范围内
	request.setAttribute("info" , info);
%>
<!-- 实现转发 -->
<jsp:forward page="second.jsp"/>
<%}%>
</body>
</html>