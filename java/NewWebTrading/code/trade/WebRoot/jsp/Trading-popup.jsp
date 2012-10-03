<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<html:base/>
    <title><bean:message key="company.name"/></title>	
</head>
<script type="text/javascript">
function popupRTQ(url) {
	var desURL = url; 
	var top = (screen.height - 600)/2;
	var left = (screen.width - 800)/2;
	location.href = "<%=request.getContextPath()%>/jsp/Trading-hk-stock.jsp";
	var popupManual = window.open(desURL, "RTQApplication", "width=800,height=600, location=no,toolbar=no,resizable=yes,scrollbars=yes,status=yes,top="+top+",left="+left);
}
</script>
<body  bgcolor=white background="images/QuotationBG.gif" onload="popupRTQ('${pageContext.request.contextPath}/webAccessErrorRTQ.do?error=${error }&productId=${productId}');">
</body>
</html>
