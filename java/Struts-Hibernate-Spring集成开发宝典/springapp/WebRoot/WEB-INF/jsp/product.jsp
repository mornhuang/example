<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
		<title><fmt:message key="title" /></title>
	</head>
	<body>
		<h1>
			<fmt:message key="heading" />
		</h1>
		<p>
			<fmt:message key="greeting" />
			<c:out value="${model.now}" />
		</p>
		<h3>
			Products
		</h3>
		<c:forEach items="${model.products}" var="prod">
			<c:out value="${prod.description}" />
			<i>$<c:out value="${prod.price}" /></i>
			<br>
			<br>
		</c:forEach>
		<br>
		<a href="<c:url value="productadd.htm"/>">Add Product</a>
	</body>
</html>
