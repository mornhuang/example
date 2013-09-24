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
	<title>使用s:action标签在页面中调用Action</title>
</head>
<body>
下面调用第一个Action，并将结果包含到本页面中。<br/>
<s:action name="tag1" executeResult="true"/>
<hr/>
下面调用第二个Action，并将结果包含到本页面中。<br/>
但阻止本页面请求参数传入Action。<br/>
<s:action name="tag2" executeResult="true"
	ignoreContextParams="true"/>
<hr/>
下面调用第三个Action，且并不将结果包含到本页面中。<br/>
<s:action name="tag2" executeResult="false"/>
本页面是否可访问？<s:property value="author"/>
</body>
</html>
