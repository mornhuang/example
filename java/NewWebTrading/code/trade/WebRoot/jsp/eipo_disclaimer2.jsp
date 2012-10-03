<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/jselect.js"></script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.ipo.heading1"/></span><b></b></span> <span class="position"><bean:message key="label.ipo.heading1"/> >
            <bean:message key="label.ipo.heading2"/></span>
    </h1>
    <div class="page-content">
        <h2>
            <bean:message key="label.ipo4.heading"/> </h2>
        <ul class="form-number-ul">
            <li><span class="number">1</span><bean:message key="message.ipo4.row1"/></li>
            <li><span class="number">2</span><bean:message key="message.ipo4.row2"/></li>
            <li><span class="number">3</span><bean:message key="message.ipo4.row3"/></li>
            <li><span class="number">4</span><bean:message key="message.ipo4.row4"/></li>
            <li><span class="number">5</span><bean:message key="message.ipo4.row5"/></li>
            <li><span class="number">6</span><bean:message key="message.ipo4.row6"/></li>
            <li><span class="number">7</span><bean:message key="message.ipo4.row7"/></li>
            <li><span class="number">8</span><bean:message key="message.ipo4.row8"/>
                <p>
                    a. <bean:message key="message.ipo4.row8a"/>
                </p>
                <p>
                    b. <bean:message key="message.ipo4.row8b"/>
                </p>
            </li>
            <li><span class="number">9</span><bean:message key="message.ipo4.row9"/></li>
            <li><span class="number">10</span><bean:message key="message.ipo4.row10"/></li>
            <li><span class="number">11</span><bean:message key="message.ipo4.row11"/></li>
            <li><span class="number">12</span><bean:message key="message.ipo4.row12"/></li>
            <li><span class="number">13</span><bean:message key="message.ipo4.row13"/></li>
            <li><span class="number">14</span><bean:message key="message.ipo4.row14"/></li>
            <li><span class="number">15</span><bean:message key="message.ipo4.row15"/></li>
            <li><span class="number">16</span><bean:message key="message.ipo4.row16"/></li>
            <li><span class="number">17</span><bean:message key="message.ipo4.row17"/></li>
        </ul>
        <p>
            <input type="button" class="yellow-btn" value="<bean:message key="message.ipo4.button1"/>" onclick="javascript:window.location='eipo_disclaimer3.jsp?CLV=${sessionScope.CLV}'" />
            <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo4.button2"/>"  onclick="window.location='../webIOPQueryList.do?CLV=${sessionScope.CLV}'" />
            <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo4.button3"/>" onclick="javascript:window.print()" />
        </p>
    </div>

</BODY>
</HTML>
