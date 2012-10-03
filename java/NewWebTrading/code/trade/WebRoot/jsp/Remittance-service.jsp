<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}" />
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/zh-hk/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.icbc.title"/></span><b></b></span> <span class="position"><bean:message key="label.icbc.head"/>
            > <bean:message key="label.icbc.title"/></span>
    </h1>
    <div class="page-content">
        <h2>
           <bean:message key="label.icbc.message"/></h2>
        <p>
            <bean:message key="label.icbc.message1"/></p>
        <br />
        <h2>
            <bean:message key="label.icbc.message2"/></h2>
        <p>
            <bean:message key="label.icbc.message3"/></p>
        <ul class="form-number-ul">
            <li><span class="number">1</span><bean:message key="label.icbc.message4"/></li>
            <li><span class="number">2</span><bean:message key="label.icbc.message5"/></li>
            <li><span class="number">3</span><bean:message key="label.icbc.message6"/></li>
            <li><span class="number">4</span><bean:message key="label.icbc.message7"/></li>
        </ul>
        <p>
            <bean:message key="label.icbc.message8"/></p>
        <br />
        <h2>
            <bean:message key="label.icbc.message9"/>
        </h2>
        <p>
            <bean:message key="label.icbc.message10"/></p>
        <p>
           <bean:message key="label.icbc.message11"/> </p>
        <p>
            <bean:message key="label.icbc.message12"/></p>
        <p>
            <bean:message key="label.icbc.message13"/></p>
        <p>
            <bean:message key="label.icbc.message14"/></p>
        <br />
        <h2>
            <bean:message key="label.icbc.message15"/></h2>
        <ul class="form-number-ul">
            <li><span class="number-yellow">1</span><bean:message key="label.icbc.message16"/></li>
            <li><span class="number-yellow">2</span><bean:message key="label.icbc.message17"/> </li>
            <li><span class="number-yellow">3</span><bean:message key="label.icbc.message18"/> </li>
        </ul>
    </div>
</body>
</html>
