<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/zh-HK/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
</head>
<body>
    <form action="${result.ipgClientURL }/iPGClient/eps_transfer2.asp" method="post">
    	<input type="hidden" name="lang" value="${result.lang }"/><br/>
    	<input type="hidden" name="user_id" value="${result.user_id }"/><br/>
    	<input type="hidden" name="ac" value="${result.accountId }"/><br/>
    	<input type="hidden" name="tamount" value="${result.tamount }"/><br/>
    </form>
</body>
<script type="text/javascript">
	document.forms[0].submit();
</script>
</html>
