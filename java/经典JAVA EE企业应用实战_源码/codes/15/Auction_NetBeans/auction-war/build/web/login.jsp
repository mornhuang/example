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
	用户名：<h:inputText value="#{loginPro.username}"/><br/>
	密&nbsp;&nbsp;码：<h:inputText value="#{loginPro.password}"/><br/>
	验证码：<h:inputText value="#{loginPro.vercode}"/><br/>
	<h:commandButton value="登录" action="#{loginPro.execute}"/>
</h:form>
验证码：<img id="d" src="authImg.jpg">
</div>
</td>
</tr>
</table>
</body>
</html>
</f:view>