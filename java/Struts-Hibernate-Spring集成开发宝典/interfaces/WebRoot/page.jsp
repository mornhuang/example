<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
	<title>Paging</title>
</head>
<body>
<div align="center">
	<h3>Paging</h3>
	<c:set var="listSize" value="${fn:length(presidents)}"/>
	<c:set var="pageSize" value="10"/>
	<c:set var="pageBegin" value="${param.pageBegin}"/>  
	<c:set var="pageEnd" value="${pageBegin + pageSize - 1}"/>
	<c:if test="${(pageBegin - pageSize) ge 0}">
		<a href='<c:url value="/page.do">
		           <c:param name="pageBegin" value="${pageBegin - pageSize}"/>
		         </c:url>'>
		  Prev
		</a>
	</c:if>
	&nbsp;
	<c:if test="${(listSize gt pageSize) and (pageEnd lt listSize)}">
		<a href='<c:url value="/page.do">
		           <c:param name="pageBegin" value="${pageBegin + pageSize}"/>
		         </c:url>'>
		  Next
		</a>
	</c:if>
	<table border="2">
		<tr>
			  <th>First Name</th>
			  <th>Last Name</th>
			  <th>Term of Office</th>
		</tr>
		<c:forEach var="pres" items="${presidents}" 
		         begin="${pageBegin}" end="${pageEnd}">
		<tr>
			<td><c:out value="${pres.firstName}"/></td>
			<td><c:out value="${pres.lastName}"/></td>
			<td><c:out value="${pres.term}"/></td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>