<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.ipo.heading1"/></span><b></b></span> <span class="position"><bean:message key="label.ipo.heading1"/> >
            <bean:message key="label.ipo.heading2"/></span>
    </h1>
    <div class="page-content">
        <h2><bean:message key="label.ipo3.heading"/></h2>
        <ul class="form-number-ul">
            <li><span class="number">1</span><bean:message key="message.ipo3.row1"/></li>
            <li><span class="number">2</span><bean:message key="message.ipo3.row2"/></li>
            <li><bean:message key="message.ipo3.row21"/></li>
            <li><span class="number">3</span><bean:message key="message.ipo3.row3"/></li>
            <li><span class="number">4</span><bean:message key="message.ipo3.row4"/></li>
            <li><span class="number">5</span><bean:message key="message.ipo3.row5"/></li>
            <li><span class="number">6</span><bean:message key="message.ipo3.row6"/></li>
        </ul>
        <p>
            <input type="button" class="yellow-btn" value="<bean:message key="message.ipo3.button1"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/jsp/IPO-4.jsp'" />
            <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo3.button2"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webIOPQueryList.do'" />
            <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo3.button3"/>" onclick="javascript:window.print()" />
        </p>
    </div>
</body>
</html>
