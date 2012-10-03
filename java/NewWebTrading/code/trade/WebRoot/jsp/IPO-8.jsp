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
        <h2>
       	<bean:message key="label.ipo8.title1"/>
            </h2>
        <p>
            ${result.ipoRequest.applyDate_dsply }</p>
        <br />
        <h2>
            <bean:message key="label.ipo8.title2"/>: ${result.ipoRequest.applyId }</h2>
        <p>
            <bean:message key="label.ipo8.title21"/> ${result.ipoRecord.ipoName_dsply }( ${result.ipoRecord.stockCode }) <bean:message key="label.ipo8.title22"/></p>
        <p class="form-notice">
            <strong><b><bean:message key="label.ipo8.title3"/>：</b><bean:message key="label.ipo8.title4a"/> ${result.ipoRecord.depositDate_dsply } <bean:message key="label.ipo8.title4b"/>
             ${result.ipoRecord.debitDate_dsply } <bean:message key="label.ipo8.title4c"/> ${result.ipoRecord.depositDate_dsply } <bean:message key="label.ipo8.title4d"/>
            </strong> 
        </p>
        <table class="form-table ui-corner-all">
            <tr>
                <td class="title" colspan="2">
                    <bean:message key="label.ipo.heading1"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="message.ipo8.row1"/>
                </th>
                <td>
                    ${result.ipoRecord.ipoName_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo8.row2"/>
                </th>
                <td>
                    ${result.ipoRecord.stockCode }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="label.currency"/>
                </th>
                <td>
                    HKD
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo8.row4"/>
                </th>
                <td>
                    1%
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo8.row5"/>
                </th>
                <td>
                    0.003%
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo8.row6"/>
                </th>
                <td>
                    0.000%
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo8.row7"/>
                </th>
                <td>
                    0.005%
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo8.row8"/>
                </th>
                <td>
                    ${result.ipoRecord.deadLine_dsply }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo8.row9"/>
                </th>
                <td>
                    ${result.ipoRecord.depositDate_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo8.row9a"/>
                </th>
                <td>
                    ${result.ipoRecord.debitDate_dsply }
                </td>
            </tr>
            <tr>
                <th>
                    &nbsp;
                </th>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <strong><bean:message key="message.ipo8.row10"/></strong>
                </th>
                <td>
                    $${result.ipoRecord.price_dsply }
                </td>
            </tr>
            <tr>
                <th>
                    <strong><bean:message key="message.ipo8.row11"/></strong>
                </th>
                <td>
                    ${result.ipoRequest.applyQuantity }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <strong><bean:message key="message.ipo8.row12"/></strong>
                </th>
                <td>
                    $${result.ipoRecord.fee }
                </td>
            </tr>
            <tr>
                <th>
                    <strong><bean:message key="message.ipo8.row13"/></strong>
                </th>
                <td>
                    $${result.ipoRequest.dsptAmount }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <strong><bean:message key="message.ipo8.row14"/></strong>
                </th>
                <td>
                    ${result.ipoRequest.telephone }
                </td>
            </tr>
            <tr>
                <th>
                    <strong><bean:message key="message.ipo8.row15"/></strong>
                </th>
                <td>
                    ${result.ipoRequest.email }
                </td>
            </tr>
        </table>
        <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo8.buttuon1"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webIOPQueryList.do'" />
        <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo8.buttuon2"/>" onclick="javascript:window.print()" />
    </div>
</body>
</html>

