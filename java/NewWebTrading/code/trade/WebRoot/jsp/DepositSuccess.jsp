<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
    <script type="text/javascript" src="../Script/CheckFundDeposit.js"></script>
</head>

<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="ConfirmationDeposit"/></span><b></b></span> <span class="position">帳戶服務 >
            <bean:message key="ConfirmationDeposit"/></span>
    </h1>
    <div class="page-content">
        <div class="form-table-center ui-corner-all">
            <div class="title">
                <bean:message key="ConfirmationDeposit"/></div>
            <table>
                <tr>
                    <td class="message">
                        <bean:message key="label.deposit.message5"/>
                        <p>
                            ${requestNo}</p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="button" class="short-button" value="返回" onclick="javascript:location.href='${pageContext.request.contextPath}/jsp/CheckFundDeposit.jsp'" />
                    </td>
                </tr>
            </table>
        </div>
        <h2>
           <bean:message key="label.deposit.message4"/></h2>
        <ul class="form-number-ul">
            <li><span class="number">1</span><bean:message key="label.deposit.message1"/></span></li>
            <li><span class="number">2</span><bean:message key="label.deposit.message2"/></span></li>
            <li><span class="number">3</span><bean:message key="label.deposit.message3"/></span></li>
        </ul>
    </div>
</body>
</html>

