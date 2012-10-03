<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}" />
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
       	<span class="shaddow"><span><bean:message key="label.fundTransferForm.head1"/></span><b></b></span> 
        <span class="position"><bean:message key="label.fundTransferForm.head2"/> > <bean:message key="label.fundTransferForm.head3"/></span>
    </h1>
    <div class="page-content">
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    <bean:message key="label.fundTransferForm.success"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="label.fundTransferForm.fromAC"/>
                </th>
                <td>
                    ${result.fromAccountId }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="label.fundTransferForm.toAC"/>
                </th>
                <td>
                    ${result.toAccountId }
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
                    <bean:message key="label.fundTransferForm.transAmount"/>
                </th>
                <td>
                    $${result.amount }
                </td>
            </tr>
        </table>
        <p>
            <input type="button" class="short-button" value="<bean:message key="label.fundTransferForm.button" />" onclick="javascript:location.href='${pageContext.request.contextPath}/webEnquireMisAccount.do'" />
        </p>
    </div>
</body>
</html>

