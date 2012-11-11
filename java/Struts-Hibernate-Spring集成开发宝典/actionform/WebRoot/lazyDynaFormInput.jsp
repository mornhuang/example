<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<html>
<head>
    <title>Dyna Action Form</title>
</head>
<body>
    <h2>Dyna Action Form</h2>
    <html:form action="/lazyDynaForm">
		Your First Name:<html:text property="firstName"/><br />
		Your Last Name:<html:text property="lastName"/><br />
        Who are your 3 friends:<br />
        Friend 1: <html:text property="friend[0].name"/><br />
        Friend 2: <html:text property="friend[1].name"/><br />
        Friend 3: <html:text property="friend[2].name"/><br />
        <html:submit/>
    </html:form>
</body>
</html>