<%@page contentType="text/html" pageEncoding="GBK"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
	<style type="text/css">
		.odd {
			background-color:#ddffdd;
		}
		.even {
			background-color:#aaaaff;
		}
	</style>
	<title>其他标签</title>
</head>
<body>
<h1>其他标签</h1>
<h:dataTable width="600px" border="1"
	value="#{viewBook.books}" var="book"
	rowClasses="odd,even"
	first="2" rows="2">
	<!-- 使用facet标签生成caption -->
	<f:facet name="caption">
		<h:outputText value="图书列表"/>
	</f:facet>
	<!-- 定义第一列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="图书ID"/>
		</f:facet>
		<h:inputText value="#{book.id}" size="3"/>
	</h:column>
	<!-- 定义第二列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="图书书名"/>
		</f:facet>
		<h:outputText value="#{book.name}"/>
	</h:column>
	<!-- 定义第三列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="技术支持站点"/>
		</f:facet>
		<h:outputLink value="http://www.#{book.website}">
			<h:outputText value="疯狂Java联盟"/>
		</h:outputLink>
	</h:column>
	<f:facet name="footer">
		<h:panelGroup>
			<h:outputText value="总数：5本图书"/>,
			<h:outputText value="总价：268.00元"/>
		</h:panelGroup>
	</f:facet>
</h:dataTable>
</body>
</html>
</f:view>
