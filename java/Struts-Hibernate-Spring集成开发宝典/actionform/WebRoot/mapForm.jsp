<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Map-backed Form</title>
</head>
<body>
    <h2>Map Form Test</h2>
    <c:if test="${not empty mapForm.skills}">
        Java Skill: <c:out value="${mapForm.skills.java}"/><br />
        JSP Skill: <c:out value="${mapForm.skills.jsp}"/><br />
        Struts Skill: <c:out value="${mapForm.skills.struts}"/><br />
    </c:if>
</body>
</html>