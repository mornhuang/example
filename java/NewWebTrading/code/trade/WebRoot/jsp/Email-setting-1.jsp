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
<script type="text/javascript">
function validatorEmail(){
	var email =/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!email.test($("#userNotificationEmail").val())){
		alert('<bean:message key="label.email.test"/>');
		$("#userNotificationEmail").focus();
		return false;
	}
	return true;
}
</script>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.email.title"/></span><b></b></span> <span class="position"><bean:message key="label.email.head"/></span>
    </h1>
    <form action="../webUpdateUserNotificationEmail.do" method="post" onsubmit="return validatorEmail();">
    <div class="page-content">
        <table class="form-table ui-corner-all">
            <tr>
                <td class="title">
                    <bean:message key="label.email.content.row1"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <td>
                    <label for="radio-eform">
                        <bean:message key="label.email.content.row2"/></label>
                    <ul class="form-number-ul">
                        <li><bean:message key="label.email.content.row3"/></li>
                        <li><bean:message key="label.email.content.row4"/><input class="form-input" id="userNotificationEmail" name="userNotificationEmail" type="text" value="${result.userNotificationEmail }" /><span class="required">*</span></li>
                        <li><span class="required">*</span><bean:message key="label.email.content.row5"/></li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td class="title">
                    <bean:message key="label.email.content.row6"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <td>
                	<c:choose>
                		<c:when test="${result.notiFlag == true}">
                			<input type="checkbox" name="notiFlag" checked="checked" />
                		</c:when>
                		<c:otherwise>
                			<input type="checkbox" name="notiFlag" />
                		</c:otherwise>
                	</c:choose>
                    
                    <label for="radio-notice">
                        <bean:message key="label.email.content.row7"/></label>
                    <ul class="form-number-ul">
                        <li><bean:message key="label.email.content.row8"/></li>
                    </ul>
                </td>
            </tr>
        </table>
        <h2>
            <bean:message key="label.email.content.row9"/></h2>
        <ul class="form-number-ul">
            <li><span class="number">1</span><bean:message key="label.email.content.row10"/></li>
            <li><span class="number">2</span><bean:message key="label.email.content.row11"/></li>
            <li><span class="number">3</span><bean:message key="label.email.content.row12"/></li>
        </ul>
        <p>
            <input type="submit" class="yellow-btn" value="<bean:message key="label.email.button1"/>" />
        </p>
    </div>
    </form>
</body>
</html>
