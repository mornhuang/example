<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
	<head>
		<title><bean:message key="blog.welcome" /></title>
		<link rel="stylesheet" href="css/common.css">
	</head>
	<body>
		<center>
			<h1>
				<bean:message key="blog.welcome" />
			</h1>
			<br>
			<html:errors />
			<c:if test="${not (empty user)}">
				<table width="500">
					<c:forEach var="message" items="${user.messages}">
						<tr>
							<td>
								${message.title}--${message.pubdate}
							</td>
							<td align="right">
								<html:link action="/replyAdd" paramId="id" paramName="message" paramProperty="id">
									<bean:message key="reply.add" />
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
													${reply.title}--${reply.pubdate}--${reply.username}
												</td>
											</tr>
											<tr>
												<td>
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
			</c:if>
		</center>
	</body>
</html>
