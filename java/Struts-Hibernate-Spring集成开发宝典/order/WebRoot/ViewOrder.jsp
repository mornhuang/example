<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html>
 <head>
	<title>View Order</title>
 </head>

<c:set var="form" value="${OrderForm}"/>

<body>
	<html:form action="FindOrderID">
	<html:errors />
	<table width="730" border="0">
		<tr>
			<td>
				<table width="100%" border="0">
					<tr>
						<td width="10%">Order ID:</td>
						<td width="90%">
							<c:out value="${form.order.id}"/>
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="neworder.placedby" />
						</td>
						<td>
							<c:out value="${form.order.userName}"/>
						</td>
					</tr>
				</table>
				<br>
				<table width="100%" border="0">
					<tr bgcolor="ccccff">
						<td>&nbsp;</td>											
						<td>
							<b><bean:message key="neworder.itemdesc"/></b>
						</td>
						<td>
							<b><bean:message key="neworder.price"/></b>
						</td>
					</tr>
					<c:forEach items="${form.order.orderLineItems}" var="item" 
							varStatus="status">
						<tr bgcolor="eeeeee">
							<td>
								<c:out value="${status.count}"/>
							</td>
							<td>
								<c:out value="${item.description}"/>
							</td>												
							<td>
								<c:out value="${item.lineItemPrice}"/>
							</td>
						</tr>
					</c:forEach>
					<tr bgcolor="ffffff">
						<td>&nbsp;</td>											
						<td>&nbsp;</td>
						<td>
							<b><c:out value="${form.order.total}"/></b>
						</td>
					</tr>
				</td>
			</tr>
		</table>
		<table width="100%">
			<tr>
				<td align="center">
					<br><br>
					<html:link action="/Index.do">
						<bean:message key="index.home"/>
					</html:link>
				</td>
			</tr>
		</table>			

	</html:form>
</body>
</html>