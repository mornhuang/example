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
<script type="text/javascript">



document.oncontextmenu = function() {
	if (window.event)
		window.event.returnValue=false;
	return false;
};
function onPageLoad(){
	var alertMsgs="${resultStatus}";
	if(alertMsgs=="GRACELOGIN"){
		alert("<bean:message key='GRACELOGIN'/>");
	}

	
	<c:if test="${not empty changePasswordStatus}">
		<c:choose>
			<c:when test="${changePasswordStatus == 'SUCCESS'}">
				alert("<bean:message key='${StatusKey}'/>");
				location.href = "${pageContext.request.contextPath}/jsp/ChangePasswordSuccess.jsp";
			</c:when>
			<c:otherwise>
				alert("<bean:message key='${StatusKey}'/>");
			</c:otherwise>
		</c:choose>
	</c:if>
}
function force_submit_chgPwd() {
	var psw_P = /^[A-Za-z0-9]{6,8}$/;
	var result = true;
	var orig_pwd = document.chgpwd_form.oldPassword.value;
	var new_pwd = document.chgpwd_form.newPassword.value;
	var confirm_pwd = document.chgpwd_form.confirm_pwd.value;
	if (!psw_P.test(orig_pwd)) {
		alert("<bean:message key='WEB051005'/>");
		document.chgpwd_form.oldPassword.focus();
		result = false;
	} else if (!psw_P.test(new_pwd)) {
		alert("<bean:message key='WEB051004'/>");
		document.chgpwd_form.newPassword.focus();
		result = false;
	} else if (!psw_P.test(confirm_pwd)) {
		alert("<bean:message key='WEB051006'/>");
		document.chgpwd_form.confirm_pwd.focus();
		result = false;
	} else if (orig_pwd == new_pwd) {
		alert("<bean:message key='WEB051015'/>");
		document.chgpwd_form.newPassword.focus();
		result = false;
	} else if (new_pwd != confirm_pwd) {
		alert("<bean:message key='WEB051016'/>");
		document.chgpwd_form.newPassword.focus();
		result = false;
	}
	return result;
}
</script>
<body onload="onPageLoad()">
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="changePassword"/></span><b></b></span> <span class="position"><bean:message key="accountServices"/> >
           <bean:message key="changePassword"/></span>
    </h1>
    <div class="page-content">
        <form name="chgpwd_form" id="chgpwd_form" autocomplete="off" action="../ChangePsw.do?CLV=${sessionScope.CLV}" onSubmit="return force_submit_chgPwd();" onReset="javascript:oldPassword.focus();" method="post">
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    <bean:message key="changePassword"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="loginName"/>
                </th>
                <td>
                    ${User.loginName}
                    <p>
                    ${loginUserInfo.custName}
                    </p>
                    
               </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="oldPassword"/>
                </th>
                <td>
                    <input autocomplete="off" type="password" name="oldPassword" id="oldPassword" class="form-input" value="" size="10" maxlength=10>
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="newPassword1"/>
                </th>
                <td>
                	
                    <input autocomplete="off" type="password" name="newPassword" id="newPassword" class="form-input" value="" size="10" maxlength=10>
                </td>
            </tr>
            <tr class="alternating">
                <th>
                 	 <bean:message key="newPassword2"/>
                </th>
                <td>
                    <input autocomplete="off" type="password" name="newPassword1" id="confirm_pwd" class="form-input" value="" size="10" maxlength=10>
                </td>
            </tr>
            <tr>
                <th>
                </th>
                <td>
                	<input type="hidden" id="loginId" name="loginId" value="${User.tradeInfoObject.custCode}">
                    <input type="submit" class="form-button" value="<bean:message key='submit'/>"/>
                    <input type="reset" class="form-button" value="<bean:message key='reeset'/>" />
                </td>
            </tr>
        </table>
        </form>
        <p>
            <bean:message key="passwordEffect2"/></p>
        <p>
             <bean:message key="passwordEffect"/></p>
    </div>
</body>
</html>