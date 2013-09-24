<%-- 
    Document   : show
    Created on : Feb 8, 2010, 11:31:55 PM
    Author     : yeeku
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>添加结果</title>
        </head>
        <body>
			<h1>添加结果</h1>
            书名：<h:outputText value="#{bookBean.name}" /><br/>
			ISBN：<h:outputText value="#{bookBean.isbn}" /><br/>
			简介：<h:outputText value="#{bookBean.desc}" /><br/>
        </body>
</html>
</f:view>
