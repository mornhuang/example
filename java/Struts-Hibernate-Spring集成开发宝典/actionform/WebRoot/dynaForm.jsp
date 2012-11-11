<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Dyna Action Form</title>
</head>
<body>
    <h2>Dyna Action Form</h2>
        Your First Name:<bean:write name="dynaForm" property="firstName"/><br />
        Your Last Name:<bean:write name="dynaForm" property="lastName"/><br />
        Your Friends:<br />
        Friend 1: <bean:write name="dynaForm" property="friend[0]"/><br />
        Friend 2: <bean:write name="dynaForm" property="friend[1]"/><br />
        Friend 3: <bean:write name="dynaForm" property="friend[2]"/><br />
</body>
</html>