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
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>转换成功</title>
</head>
<body>
	转换成功!<br/>
	<s:iterator value="users" status="status">
		用户<s:property value="%{#status.index}"/>的用户名：<s:property value="name"/><br/>
		用户<s:property value="%{#status.index}"/>的密码：<s:property value="pass"/><br/>
	</s:iterator>
</body>
</html>
