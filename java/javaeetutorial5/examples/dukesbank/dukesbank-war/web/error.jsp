<html>
<head>
    <title>
        <fmt:message key="TitleError"/>
    </title>
</head>
<%@ include file="/template/banner.jsp" %>
<jsp:include page="/template/links.jsp"/>
<center>
    <h3>${requestScope.errorMessage}</h3>
</center>
</html>
