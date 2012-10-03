<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html>
<head>
  <title>Token Test</title>
</head>
<body bgcolor="white">
<h2>Token Test</h2>
  <html:errors/>
  <html:form action="/checkToken">
      <html:text property="name"/>
      <html:submit/>
  </html:form>
  <html:link action="/checkToken" transaction="true">Save</html:link>
</body>
</html>