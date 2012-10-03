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
    <script src="../Script/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
</head>
<body>
    <div class="text-page">
        <h1>
           <bean:message key="label.data.head1"/></h1>
        <p>
            <bean:message key="label.data.head2"/></p>
        <h2>
            <bean:message key="label.data.directory1"/></h2>
        <p>
            <strong>a.</strong> <bean:message key="label.data.directory1.riskA"/></p>
        <ul class="form-number-ul">
            <li><span class="number-yellow">1</span><bean:message key="label.data.directory1.riskA.row1"/></li>
            <li><span class="number-yellow">2</span><bean:message key="label.data.directory1.riskA.row2"/></li>
            <li><span class="number-yellow">3</span><bean:message key="label.data.directory1.riskA.row3"/></li>
            <li><span class="number-yellow">4</span><bean:message key="label.data.directory1.riskA.row4"/></li>
            <li><span class="number-yellow">5</span><bean:message key="label.data.directory1.riskA.row5"/></li>
            <li><span class="number-yellow">6</span><bean:message key="label.data.directory1.riskA.row6"/></li>
            <li><span class="number-yellow">7</span><bean:message key="label.data.directory1.riskA.row7"/></li>
            <li><span class="number-yellow">8</span><bean:message key="label.data.directory1.riskA.row8"/></li>
            <li><span class="number-yellow">9</span><bean:message key="label.data.directory1.riskA.row9"/></li>
        </ul>
        <p>
            <strong>b.</strong> <bean:message key="label.data.directory1.riskB"/></p>
        <ul class="form-number-ul">
            <li><span class="number-yellow">1</span><bean:message key="label.data.directory1.riskB.row1"/></li>
            <li><span class="number-yellow">2</span><bean:message key="label.data.directory1.riskB.row2"/></li>
            <li><span class="number-yellow">3</span><bean:message key="label.data.directory1.riskB.row3"/></li>
            <li><span class="number-yellow">4</span><bean:message key="label.data.directory1.riskB.row4"/></li>
            <li><span class="number-yellow">5</span><bean:message key="label.data.directory1.riskB.row5"/></li>
            <li><span class="number-yellow">6</span><bean:message key="label.data.directory1.riskB.row6"/></li>
            <li><span class="number-yellow">7</span><bean:message key="label.data.directory1.riskB.row7"/></li>
            <li><span class="number-yellow">8</span><bean:message key="label.data.directory1.riskB.row8"/></li>
        </ul>
        <h2>
            <bean:message key="label.data.directory2"/></h2>
        <p>
            <strong>a.</strong><bean:message key="label.data.directory2.riskA"/></p>
        <ul class="form-number-ul">
            <li><span class="number-yellow">1</span><bean:message key="label.data.directory2.riskA.row1"/></li>
            <li><span class="number-yellow">2</span><bean:message key="label.data.directory2.riskA.row2"/></li>
            <li><span class="number-yellow">3</span><bean:message key="label.data.directory2.riskA.row3"/></li>
            <li><span class="number-yellow">4</span><bean:message key="label.data.directory2.riskA.row4"/></li>
        </ul>
        <p>
            <strong>b.</strong><bean:message key="label.data.directory2.riskB"/></p>
        <ul class="form-number-ul">
            <li><span class="number-yellow">1</span><bean:message key="label.data.directory2.riskB.row1"/></li>
            <li><span class="number-yellow">2</span><bean:message key="label.data.directory2.riskB.row2"/> </li>
            <li><span class="number-yellow">3</span><bean:message key="label.data.directory2.riskB.row3"/></li>
        </ul>
        <p>
            <bean:message key="label.data.message1"/><a href="mailto:online@htisec.com">online@htisec.com</a></p>
        <p>
            <bean:message key="label.data.message2"/></p>
        <p>
            <bean:message key="label.data.message3"/></p>
        <p>
            <bean:message key="label.data.message4"/></p>
    </div>
</body>
</html>
