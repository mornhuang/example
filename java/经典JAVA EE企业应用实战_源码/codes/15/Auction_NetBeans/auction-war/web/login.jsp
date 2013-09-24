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
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商业拍卖Java EE程序框架</title>
</head>
<body>
<table width="780" align="center" cellspacing="0"
	background="images/bodybg.jpg">
<tr>
<td >
<h3>请输入用户名和密码登录系统</h3>
<div align="center">
<h:outputText value="#{loginPro.errInfo}" styleClass="tip"/>
<h:form>
	<table width="50%">
	<tr>
		<td width="16%">用户名：</td>
		<td width="40%"><h:inputText id="username" value="#{loginPro.username}" 
		required="true"
		requiredMessage="用户名必须填写！"
		validatorMessage="用户名长度必须在4～20之间">
		<f:validateLength minimum="4" maximum="20"/>
		</h:inputText></td>
		<td width="44%"><h:message for="username" style="color:red"/></td>
	</tr>
	<tr>
		<td>密&nbsp;&nbsp;码：</td>
		<td><h:inputText id="password" value="#{loginPro.password}"
		required="true"
		requiredMessage="密码必须填写！"
		validatorMessage="密码长度必须在4～20之间">
		<f:validateLength minimum="4" maximum="20"/>
		</h:inputText></td>
		<td><h:message for="password" style="color:red"/></td>
	</tr>
	<tr>
		<td>验证码：</td>
		<td><h:inputText id="vercode" value="#{loginPro.vercode}"
		required="true"
		requiredMessage="验证码必须填写！"
		validatorMessage="验证码长度必须等于6">
		<f:validateLength minimum="6" maximum="6"/>
		</h:inputText></td>
		<td><h:message for="vercode" style="color:red"/></td>
	</tr>
	<tr>
		<td colspan="3">
		<h:commandButton value="登录" action="#{loginPro.execute}"/></td>
	</tr>
	</table>
</h:form>
验证码：<img id="d" src="authImg.jpg" alt="验证码"/>
</div>
</td>
</tr>
</table>
</body>
</html>
</f:view>