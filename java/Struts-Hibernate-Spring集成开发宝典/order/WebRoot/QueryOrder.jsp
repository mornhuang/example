<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>


<html>
<head>
	<title><bean:message key="queryorder.title"/></title>
</head>
<body>
	<html:form action="FindOrderID">			

	<h3><bean:message key="queryorder.title"/></h3>

	<table width="730" border="0">
		<tr>
			<td>
				<table width="100%" border="1" bgcolor="#ffffcc">
					<tr>
						<td>
							<font size="-1">
								<bean:message key="queryorder.prompt"/>
							</font>
						</td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
							<html:errors />
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="neworder.orderid"/>
						</td>
						<td>
							<html:text property="orderId" size="25" 
								maxlength="25" styleClass="textBox" 
								tabindex="1" />
						</td>
					</tr>
				</table>
				<br>
				<table width="100%">
					<tr>
						<td align="center">
							<br>
							<html:submit styleClass="normal">
								<bean:message key="queryorder.queryorder"/>
							</html:submit>
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
			</td>
		</tr>
	</table>
</html:form>
</body>
</html>
