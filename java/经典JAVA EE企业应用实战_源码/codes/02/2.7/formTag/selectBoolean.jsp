<%@page contentType="text/html" pageEncoding="GBK"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
	<title>表单标签</title>
</head>
<body>
<h1>表单标签</h1>
<h:form>
HtmlSelectOneCheckbox：
<h:selectBooleanCheckbox value="#{user.name}"/>
<br/>
</h:form>
</body>
</html>
</f:view>
