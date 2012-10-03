<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/zh-HK/style.css" />
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
        <span class="shaddow"><span><bean:message key="changePassword"/></span><b></b></span> <span class="position"><bean:message key="accountServices"/> >
            <bean:message key="changePassword"/></span>
    </h1>
    <div class="page-content">
        <div class="form-table-center ui-corner-all">
            <div class="title">
                <bean:message key="changePassword"/></div>
            <table>
                <tr>
                    <td class="message">
                         <bean:message key="message.setting.chgpwd.success"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="button" class="short-button" value="<bean:message key="button.common.back"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/jsp/Trading-hk-stock.jsp'" />
                    </td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>