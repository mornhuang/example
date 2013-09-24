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
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--该句绑定在classes下的messages的资源文件-->
<f:loadBundle basename="messages" var="msg"/>
<f:view>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>添加成功</title>
</head>    
<body>
<h1>恭喜您！基于JBoss的JSF+EJB 3+JPA整合成功</h1>
<h2>添加消息成功</h2>
<h:outputLink value="faces/viewAll.jsp">查看所有消息</h:outputLink>
</body>
</html>
</f:view>