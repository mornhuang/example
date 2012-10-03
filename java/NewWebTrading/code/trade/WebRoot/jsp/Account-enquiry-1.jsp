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
    <script src="../Script/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
$(function(){
	<c:if test="${!empty error}">
		alert('<bean:message key="${error}"/>');
	</c:if>
	$("#btnSearch").removeAttr("disabled");
});
function ValidatorSubmit(){
	$("#btnSearch").attr('disabled','disabled');
	return true;
}
</script>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.accountEnquiryForm.head1" /></span><b></b></span> <span class="position"><bean:message key="label.accountEnquiryForm.head2" /></span>
    </h1>
    <div class="page-content">
        <form action="../webAccountPosition.do" method="post" onsubmit="return ValidatorSubmit();">
        <div class="form-table-center ui-corner-all">
            <div class="title">
                <bean:message key="label.accountEnquiryForm.head3" /></div>
            <table>
                <tr>
                    <th>
                        &nbsp;
                    </th>
                    <td>
                        <bean:message key="label.accountEnquiryForm.accountNo1" />
                    </td>
                </tr>
                <tr>
                    <th>
                        &nbsp;
                    </th>
                    <td>
                        <select class="jquery-select" name="indexId">
                        	<c:forEach var="account" varStatus="s" items="${result}">
								<option value="${s.index}">${account.accountId}</option>
							</c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>
                    </th>
                    <td>
                        <input type="submit" id="btnSearch" class="short-button" value="<bean:message key="label.accountEnquiryForm.submit" />" />
                    </td>
                </tr>
            </table>
        </div>
        </form>
    </div>
</body>
</html>
