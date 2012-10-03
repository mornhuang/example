<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.epayment.eps.title"/></span><b></b></span> <span class="position"><bean:message key="label.epayment.head"/></span>
    </h1>
    <div class="page-content">
        <h2>
            <bean:message key="label.epayment.eps.title"/></h2>
        <ul class="form-number-ul">
            <li><span class="number">1</span><bean:message key="label.epayment.eps.row1"/></li>
            <li><span class="number">2</span><bean:message key="label.epayment.eps.row2"/></li>
            <li><span class="number">3</span><bean:message key="label.epayment.eps.row3"/></li>
        </ul>
        <p>
            <input type="button" class="form-button" value="<bean:message key="label.epayment.eps.button"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webEPaymentEntrance.do'" />
        </p>
    </div>
</body>
</html>

