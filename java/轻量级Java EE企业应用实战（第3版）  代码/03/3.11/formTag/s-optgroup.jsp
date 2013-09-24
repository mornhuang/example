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
	<title>使用s:optgroup生成下拉选择框的选项组</title>
	<s:head/>
</head>
<body>
<h3>使用s:optgroup生成下拉选择框的选项组</h3>
<s:form>
<!-- 直接使用Map为列表框生成选项 -->
<s:select label="选择您喜欢的图书" name="book" size="7"
	list="#{'疯狂Java讲义':'李刚'
		,'轻量级Java EE企业应用实战':'李刚'
		,'经典Java EE企业应用实战':'李刚'}"
	listKey="value"
	listValue="key">
	<!-- 使用Map对象来生成选择框的选项组 -->
	<s:optgroup label="Rod Johnson"
		list="#{'Expert One-on-One J2EE Design and Development':'Johnson'}"
		listKey="value"
		listValue="key"/>
	<s:optgroup label="David Flanagan"
		list="#{'JavaScript: The Definitive Guide':'David'}"
		listKey="value"
		listValue="key"/>
</s:select>
</s:form>
</body>
</html>
