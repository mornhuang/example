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
    <script src="../Script/taifook.warrants-cbbcs.js" type="text/javascript"></script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.warrant.head"/></span><b></b></span>
        <input type="button" onclick="javascript:parent.changeCenterSrc('Warrants-CBBCs.jsp');"
            class="short-button" value="<bean:message key="label.warrant.button"/>" />
    </h1>
    <div class="page-content">
        <ul class="warrants-cbbcs-nav warrants-nav  ui-helper-clearfix">
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.warrant.message1"/>',800);">
                <bean:message key="label.warrant.message1"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.warrant.message2"/>',550);">
               <bean:message key="label.warrant.message2"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.warrant.message3"/>',800);"
            	 class="long">
                <bean:message key="label.warrant.message3"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.warrant.message4"/>',550);"
                class="long">
                <bean:message key="label.warrant.message4"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.warrant.message5"/>',550);">
                <bean:message key="label.warrant.message5"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.warrant.message6"/>',800);">
                <bean:message key="label.warrant.message6"/></a></li>
            <li><a href="javascript:changeTopTenSrc('<bean:message key="link.warrant.message7"/>',300);"
                class="long">
                <bean:message key="label.warrant.message7"/></a></li>
            <li><a href="javascript:changeInnerSrc('<bean:message key="link.warrant.message8"/>',700);">
               <bean:message key="label.warrant.message8"/></a></li>
        </ul>
        <h2 class="warrants-cbbcs-top-ten-title">
            <bean:message key="label.warrant.message9"/></h2>
        <iframe id="center-frame-1" class="iframe" src="" frameborder="0"></iframe>
        <h2 class="warrants-cbbcs-top-ten-title">
            <bean:message key="label.warrant.message10"/></h2>
        <iframe id="center-frame-2" class="iframe" src="" frameborder="0"></iframe>
        <h2 class="warrants-cbbcs-top-ten-title">
            <bean:message key="label.warrant.message11"/></h2>
        <iframe id="center-frame-3" class="iframe" src="" frameborder="0"></iframe>
    </div>
</body>
</html>

