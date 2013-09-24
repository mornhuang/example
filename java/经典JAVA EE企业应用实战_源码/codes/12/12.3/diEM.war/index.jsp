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
	<title> 添加消息 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<h2>使用依赖注入的EntityManager执行持久化</h2>
<form method="post" action="addNews">
	消息标题：<input type="text" id="title" name="title"/><br/>
	消息内容：<input type="text" id="content" name="content" /><br/>
	<input type="submit" value="添加"/>
</form>
</body>
</html>