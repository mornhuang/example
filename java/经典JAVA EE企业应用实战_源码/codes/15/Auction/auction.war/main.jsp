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
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>商业拍卖Java EE程序框架</title>
<link href="images/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="780" align="center" cellspacing="0"
	background="images/bodybg.jpg">
<tr> 
<td colspan="3" valign="top"> 
<br /><br />
<p>这仅仅是一个Java EE的框架程序。程序模拟一个简单电子拍卖站点的功能。</p>      
<p>程序的功能不是很复杂，使用的是经典Java EE架构。技术包括：SiteMesh,JSF,Session Bean,<br />
Message Driven Bean ,JPA ,JavaMail,整个应用使用Session Bean封装的EAO组件操作数据库,<br />
同时利用EJB 3的声明式事务。程序中的权限检查使用JSF的监听器的支持，也利用了Listener来执行任务调度<br />
系统的邮件支持由Message Driven Bean完成，Message Driven Bean中通过JavaMail提供邮件支持。<br />
JPA为底层的数据库访问提供支持,作为O/R Mapping框架使用。<br />
表现层数据处理利用的JSF内置的类型转换和数据校验机制。整个应用使用了SiteMesh的页面装饰技术。</p>
<p>本程序的源代码随程序一起发布，版权属于李刚（http://www.crazyit.org）<br />
任何个人可用来参考学习Java EE架构、规范，但请勿在本程序的基础上修改，用做任何商业用途。<br />
本人保留依法追究相关责任的权利。转载和学习请保留此信息。
<br /></p>
</td>
</tr>
</table>
</body>
</html>
</f:view>