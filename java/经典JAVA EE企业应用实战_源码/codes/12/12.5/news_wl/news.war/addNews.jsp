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
<!-- 导入Struts 2标签库 -->
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
	<title>添加新消息</title>
</head>
<body>
<h3>
<s:text name="pageHeader"/>
</h3>
<!-- 使用form标签生成表单元素 -->
<s:form action="addNews">
	<!-- 生成一个用户名文本输入框 -->
	<s:textfield name="title" key="title"/>
	<!-- 生成一个密码文本输入框 -->
	<s:textfield name="content" key="content"/>
	<!-- 生成一个提交按钮 -->
	<s:submit key="add"/>
</s:form>
</body>
</html>
