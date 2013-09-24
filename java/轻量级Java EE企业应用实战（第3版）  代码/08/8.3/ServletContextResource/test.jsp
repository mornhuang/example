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
<%@ page import="org.springframework.web.context.support.ServletContextResource"%>
<%@ page import="org.dom4j.*,org.dom4j.io.*,java.util.*"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>测试ServletContextResource</title>
</head>
<body>
<h3>测试ServletContextResource</h3>
<%
//从Web Context下的WEB-INF路径下读取book.xml资源
ServletContextResource src = new ServletContextResource
	(application , "WEB-INF/book.xml");
//获取该资源的简单信息
System.out.println(src.getFilename());
System.out.println(src.getDescription());
//创建Dom4j的解析器
SAXReader reader = new SAXReader();
Document doc = reader.read(src.getFile());
//获取根元素
Element el = doc.getRootElement();
List l = el.elements();
//遍历根元素的全部子元素
for (Iterator it = l.iterator();it.hasNext() ; )
{
	//每个节点都是<书>节点
	Element book = (Element)it.next();
	List ll = book.elements();
	//遍历<书>节点的全部子节点
	for (Iterator it2 = ll.iterator();it2.hasNext() ; )
	{
		Element eee = (Element)it2.next();
		out.println(eee.getText());
		out.println("<br/>");
	}
}
%>
</body>
</html>