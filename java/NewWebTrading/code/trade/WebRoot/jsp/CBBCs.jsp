<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
    <script src="../Script/taifook.warrants-cbbcs.js" type="text/javascript"></script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.cbbcs.head"/></span><b></b></span>
        <input type="button" onclick="javascript:parent.changeCenterSrc('Warrants-CBBCs.jsp');"
            class="short-button" value="<bean:message key="label.cbbcs.button"/>" />
    </h1>
    <div class="page-content">
        <ul class="warrants-cbbcs-nav cbbcs-nav ui-helper-clearfix">
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.cbbcs.row1"/>',800);">
                <bean:message key="label.cbbcs.row1"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.cbbcs.row2"/>',550);">
                <bean:message key="label.cbbcs.row2"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.cbbcs.row3"/>',600);">
                <bean:message key="label.cbbcs.row3"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.cbbcs.row4"/>',800);">
                <bean:message key="label.cbbcs.row4"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.cbbcs.row5"/>',550);">
                <bean:message key="label.cbbcs.row5"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.cbbcs.row6"/>',920);"
                class="long">
                <bean:message key="label.cbbcs.row6"/></a></li>
            <li><a href="javascript:changeTopTenSrc('<bean:message key="link.cbbcs.row7"/>',320);"
                class="long">
                <bean:message key="label.cbbcs.row7"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.cbbcs.row8"/>',700);"
                class="long">
                <bean:message key="label.cbbcs.row8"/></a></li>
        </ul>
        <h2 class="warrants-cbbcs-top-ten-title">
            <bean:message key="label.cbbcs.row9"/></h2>
        <iframe id="center-frame-1" class="iframe" src="" frameborder="0"></iframe>
        <h2 class="warrants-cbbcs-top-ten-title">
            <bean:message key="label.cbbcs.row10"/></h2>
        <iframe id="center-frame-2" class="iframe" src="" frameborder="0"></iframe>
        <h2 class="warrants-cbbcs-top-ten-title">
            <bean:message key="label.cbbcs.row11"/></h2>
        <iframe id="center-frame-3" class="iframe" src="" frameborder="0"></iframe>
    </div>
</body>
</html>
