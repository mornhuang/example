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
	<title>利用拦截器实现文件过滤</title>
	<s:head/>
</head>
<body>
<s:form action="uploadPro"
	enctype="multipart/form-data">
	<s:textfield name="title" label="文件标题"/><br />
	<s:file name="upload" label="选择文件"/><br />
	<s:submit value="上传"/>
</s:form>
</body>
</html>
