<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}"/>
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
        <span class="shaddow"><span><bean:message key="label.email.title"/></span><b></b></span> <span class="position"><bean:message key="label.email.head"/></span>
    </h1>
    <div class="page-content">
        <div class="form-table-center ui-corner-all">
            <div class="title">
                <bean:message key="label.email.title"/></div>
            <table>
                <tr>
                    <td class="message">
                        <bean:message key="label.email.content.row13"/>
                        <p>${result.userNotificationEmail }</p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="button" class="yellow-btn" value="<bean:message key="label.email.button2"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/jsp/Email-setting-3.jsp'" />
                        <input type="button" class="graw-short-btn" value="<bean:message key="label.email.button3"/>" onclick="javascript:history.back();" />
                    </td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
