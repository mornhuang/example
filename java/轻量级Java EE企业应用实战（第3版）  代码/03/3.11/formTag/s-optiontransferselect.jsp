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
	<title>使用s:optiontransferselect来生成可移动列表项的下拉列表框</title>
	<s:head/>
</head>
<body>
<h3>使用s:optiontransferselect来生成可移动列表项的下拉列表框</h3>
<s:form>
<!-- 使用简单集合对象来生成可移动的下拉列表框 -->
 <s:optiontransferselect 
  	label="请选择你喜欢的图书"
	name="cnbook" 
	leftTitle="中文图书："
	rightTitle="外文图书"
	list="{'疯狂Java讲义' ,'Struts 2权威指南',
		'轻量级Java EE企业应用实战','经典Java EE企业应用实战'}" 
	multiple="true"
	addToLeftLabel="向左移动"
	selectAllLabel="全部选择"
	addAllToRightLabel="全部右移"
	headerKey="cnKey"
	headerValue="--- 选择中文图书 ---"
	emptyOption="true"
	doubleList="{'Expert One-on-One J2EE Design and Development',
		'JavaScript: The Definitive Guide'}" 
	doubleName="enBook"
	doubleHeaderKey="enKey"
	doubleHeaderValue="--- 选择外文图书 ---" 
	doubleEmptyOption="true"
	doubleMultiple="true"
/>
</s:form>
</body>
</html>
