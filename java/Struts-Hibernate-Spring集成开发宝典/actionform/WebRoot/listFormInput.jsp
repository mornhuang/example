<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<html>
<head>
    <title>List-backed Form Property</title>
</head>
<body>
    <h2>List Form Input</h2>
    <html:form action="/listForm">
        Who are your 3 friends:<br />
        Friend 1: <html:text property="friend[0]"/><br />
        Friend 2: <html:text property="friend[1]"/><br />
        Friend 3: <html:text property="friend[2]"/><br />
        Who are your 3 addresses:<br />
        Address 1: <html:text property="address[0]"/><br />
        Address 2: <html:text property="address[1]"/><br />
        Address 3: <html:text property="address[2]"/><br />
        <html:submit/>
    </html:form>
</body>
</html>