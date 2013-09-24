<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>成功页面</title>
</head>
<body>
	本站访问次数为：${applicationScope.counter}<br/>
	${sessionScope.user}，您已经登录！<br/>
	${requestScope.tip}
</body>
</html>