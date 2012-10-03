<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><bean:message key="company.name"/></title>
</head>
<body>
    <form action="${result.itsHandlerURL }" method="post">
    	<input type="hidden" name="mch_merchID" value="${result.mch_merchID }"/>
    	<input type="hidden" name="mch_merchRef" value="${result.bocTransferResponse.transactionRefCode }"/>
    	<input type="hidden" name="mch_custID" value="${result.mch_custID }"/>
    	<input type="hidden" name="mch_payAmt" value="${result.mch_payAmt }"/>
    	<input type="hidden" name="mch_locale" value="${result.mch_locale }"/>
    	<input type="hidden" name="mch_timeStamp" value="${result.mch_timeStamp }"/>
    	<input type="hidden" name="mch_returnURL" value="${result.mch_returnURL }"/>
    </form>
</body>
<script type="text/javascript">
	document.forms[0].submit();
</script>
</html>
