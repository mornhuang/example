<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Welcome</title>
</head>
<body>
<center>Welcome</center>
<table> 
	<c:forEach var="word" items="${wordList}">
	<tr>
		<td><c:out value="${word}"/></td> 
    </tr> 
	</c:forEach> 
</table> 
</body>
</html>