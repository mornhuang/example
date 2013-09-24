<%--
利嫋: <a href="http://www.crazyit.org">決髄Java選男</a>
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
	<title><s:text name="succPage"/></title>
</head>
<body>
	${requestScope.tip}<br/>
	<jsp:useBean id="d" class="java.util.Date" scope="page"/>
	<s:text name="welcomeMsg">
		<s:param>${d}</s:param>
	</s:text>
</body>
</html>