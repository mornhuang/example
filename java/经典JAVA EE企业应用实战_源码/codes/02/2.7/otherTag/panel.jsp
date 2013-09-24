<%@page contentType="text/html" pageEncoding="GBK"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
	<title>其他标签</title>
</head>
<body>
<h1>其他标签</h1>
<h:panelGrid columns="3" width="500px" border="1">
	<!-- 使用facet标签生成caption -->
	<f:facet name="caption">
		<h:outputText value="表格标题"/>
	</f:facet>
	<!-- 指定生成表格头 -->
	<f:facet name="header">
		<h:outputText value="表格头"/>
	</f:facet>
	<h:outputText value="第1个单元格"/>
	<h:outputText value="第2个单元格"/>
	<h:outputText value="第3个单元格"/>
	<h:outputText value="第4个单元格"/>
	<h:outputText value="第5个单元格"/>
	<h:outputText value="第6个单元格"/>
	<h:outputText value="第7个单元格"/>
	<!-- 将两个输出元素组合成一个元素 -->
	<h:panelGroup layout="block"
		style="background-color:#dddddd">
	<h:outputText value="第8个单元格"/>
	<h:outputText value="第9个单元格"/>
	</h:panelGroup>
	<h:outputText value="第10个单元格"/>
</h:panelGrid>
<!-- 将两个元素组合成一个元素 -->
<h:panelGroup layout="block" 
	style="background-color:#dddddd">
	<h:inputText/>
	<h:inputText/>
</h:panelGroup>
</body>
</html>
</f:view>
