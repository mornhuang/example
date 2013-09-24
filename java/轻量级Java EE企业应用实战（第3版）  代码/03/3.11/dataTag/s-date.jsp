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
	<title>使用s:date标签格式化日期</title>
</head>
<body>
<s:bean var="now" name="java.util.Date"/>
nice="false"，且指定format="dd/MM/yyyy"<br/>
<s:date name="#now" format="dd/MM/yyyy" nice="false"/><hr/>
nice="true"，且指定format="dd/MM/yyyy"<br/>
<s:date name="#now" format="dd/MM/yyyy" nice="true"/><hr/>
指定nice="true"<br/>
<s:date name="#now" nice="true" /><hr/>
nice="false"，且没有指定format属性<br/>
<s:date name="#now" nice="false"/><hr/>
nice="false"，没有指定format属性，指定了var<br/>
<s:date name="#now" nice="false" var="abc"/><hr/>
${requestScope.abc} <s:property value="#abc"/>
</body>
</html>
