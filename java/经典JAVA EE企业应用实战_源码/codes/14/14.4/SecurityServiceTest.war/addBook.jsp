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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 添加图书 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<img src="http://www.crazyit.org/logo.jpg"
	alt="crazyit.org"/>
<h2>添加图书</h2>
<form method="post" action="proAdd.jsp">
	书名：<input type="text" name="bookName"/><br/>
	价格：<input type="text" name="price"/><br/>
	<input type="submit" value="添加"/>
</form>
</body>
</html>