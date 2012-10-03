<%@ page isErrorPage="true" contentType="text/html;charset=utf-8"%>

<html>
	<head>
		<title>空指针异常！</title>
	</head>
	<body>
		<CENTER><h2>空指针异常！</h2></CENTER>
		<jsp:useBean id="now" class="java.util.Date" />
		${now}<br>
		请求地址: ${pageContext.errorData.requestURI} <br>
		状态码: ${pageContext.errorData.statusCode} <br>
		异常: ${pageContext.errorData.throwable} <br>
	</body>
</html>	