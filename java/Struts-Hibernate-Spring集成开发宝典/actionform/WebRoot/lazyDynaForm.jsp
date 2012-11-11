<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Dyna Action Form</title>
</head>
<body>
    <h2>Dyna Action Form</h2>
        Your First Name:<bean:write name="lazyDynaForm" property="firstName"/><br />
        Your Last Name:<bean:write name="lazyDynaForm" property="lastName"/><br />
        Your Friends:<br />
        Friend 1: <bean:write name="lazyDynaForm" property="friend[0].name"/><br />
        Friend 2: <bean:write name="lazyDynaForm" property="friend[1].name"/><br />
        Friend 3: <bean:write name="lazyDynaForm" property="friend[2].name"/><br />
</body>
</html>