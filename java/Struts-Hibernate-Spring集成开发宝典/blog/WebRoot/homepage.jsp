<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
	<head>
		<title><bean:message key="logon.welcome" /></title>
		<link rel="stylesheet" href="css/common.css">
	</head>
	<body>
		<center>
			<h1>
				<bean:message key="logon.welcome" />
			</h1>
			<br>
			<table width="500">
				<tr>
					<td colspan="2" align="right">
						<html:link action="/messageAdd">
							<bean:message key="message.add" />
						</html:link>
					</td>
				</tr>
				<c:forEach var="message" items="${user.messages}">
					<tr>
						<td>
							${message.title}--${message.pubdate}
						</td>
						<td align="right">
							<html:link action="/messageUpdate" paramId="id" paramName="message" paramProperty="id">
								<bean:message key="message.update" />
							</html:link>|
							<html:link action="/messageDelete" paramId="id" paramName="message" paramProperty="id">
								<bean:message key="message.delete" />
							</html:link>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							${message.content}
						</td>
					</tr>
					<c:set var="listSize" value="${fn:length(message.replies)}" />
					<c:if test="${listSize ge 0}">
						<tr>
							<td colspan="2">
								<table width="100%">
									<c:forEach var="reply" items="${message.replies}">
										<tr>
											<td>
												${reply.title}--${reply.pubdate}
											</td>
											<td align="right">
							<html:link action="/replyDelete" paramId="id" paramName="reply" paramProperty="id">
								<bean:message key="reply.delete" />
							</html:link>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												${reply.content}
											</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</center>
	</body>
</html>
