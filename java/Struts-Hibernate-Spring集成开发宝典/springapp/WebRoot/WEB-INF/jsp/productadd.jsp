<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<title><fmt:message key="title" /></title>
	</head>
	<body>
		<h1>
			<fmt:message key="productadd.heading" />
		</h1>
		<form method="post">
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<tr>
					<td align="right" width="20%">
						Product Description:
					</td>
					<spring:nestedPath path="product">
					<spring:bind path="description">
						<td width="20%">
							<input type="text" name="${status.expression}" value="<c:out value="${status.value}"/>">
						</td>
						<td width="60%">
							<font color="red"><c:out value="${status.errorMessage}" /></font>
						</td>
					</spring:bind>
					</spring:nestedPath>
				</tr>
				<tr>
					<td align="right" width="20%">
						Product Price:
					</td>
					<spring:bind path="product.price">
						<td width="20%">
							<input type="text" name="${status.expression}" value="<c:out value="${status.value}"/>">
						</td>
						<td width="60%">
							<font color="red"><c:out value="${status.errorMessage}" /></font>
						</td>
					</spring:bind>
				</tr>
			</table>
			<br>
			<spring:hasBindErrors name="product">
				<b>Please fix all errors!</b>
			</spring:hasBindErrors>
			<br>
			<br>
			<input type="submit" align="center" value="Execute">
		</form>
		<a href="<c:url value="product.htm"/>">Home</a>
	</body>
</html>
