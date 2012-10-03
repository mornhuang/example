<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>List-backed Form Property</title>
</head>
<body>
    <h2>List Form Output</h2>
        Your friends:<br />
        Friend 1: <bean:write name="listForm" property="friend[0]"/><br />
        Friend 2: <bean:write name="listForm" property="friend[1]"/><br />
        Friend 3: <bean:write name="listForm" property="friend[2]"/><br />
        Your addresses:<br />
        Address 1: ${listForm.addresses[0]}<br />
        Address 2: ${listForm.addresses[1]}<br />
        Address 3: ${listForm.addresses[2]}<br />
</body>
</html>