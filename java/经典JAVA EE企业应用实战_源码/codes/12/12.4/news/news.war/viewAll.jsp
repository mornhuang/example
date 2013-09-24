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
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 该句加载在classes下的messages的国际化资源文件 -->
<f:loadBundle basename="messages" var="msg"/>
<html>
<head>
	<title>查看所有消息</title>
	<style type="text/css">
		.odd {
			background-color:#ddffdd;
		}
		.even {
			background-color:#aaaaff;
		}
	</style>
</head>
<body>
<h1>恭喜您！基于JBoss的JSF+EJB 3+JPA整合成功</h1>
<h2>所有消息</h2>
<h:dataTable width="600px" border="1"
	value="#{viewAll.newsList}" var="news"
	rowClasses="odd,even">
	<!-- 使用facet标签生成caption -->
	<f:facet name="caption">
		<h:outputText value="消息列表"/>
	</f:facet>
	<!-- 定义第一列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="消息ID"/>
		</f:facet>
		<h:outputText value="#{news.id}"/>
	</h:column>
	<!-- 定义第二列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="消息标题"/>
		</f:facet>
		<h:outputText value="#{news.title}"/>
	</h:column>
	<!-- 定义第三列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="消息内容"/>
		</f:facet>
		<h:outputText value="#{news.content}"/>
	</h:column>
</h:dataTable>
</body>
</html>
</f:view>