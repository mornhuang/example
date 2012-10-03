<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}"/>
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="../Script/jquery.fmatter-min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/jselect.js"></script>
    <script type="text/javascript" src="../Script/sht.locale-${sessionScope['org.apache.struts.action.LOCALE']}.js"></script>
    <script type="text/javascript" src="../Script/until.js"></script>
    <script type="text/javascript" src="../Script/shieldingMouse.js"></script>
</head>
<body onload="onPageLoad()">
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="changePassword"/></span><b></b></span> <span class="position"><bean:message key="accountServices"/> >
           <bean:message key="changePassword"/></span>
    </h1>
    <div class="page-content">
        <form name="chgpwd_form" id="chgpwd_form" method="post" autocomplete="off" action="../changePwd.do?CLV=${sessionScope.CLV}" onSubmit="return checkPassWord()" onReset="javascript:oldPassword.focus();">
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    <bean:message key="changePassword"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                  <bean:message key="label.reg.login_id"/>
                </th>
                <td>
                    ${user.userName }
                    <p>
      				${user.loginId }</p>
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="oldPassword"/>
                </th>
                <td>
                    <input type="password" class="form-input" id="oldPassword" name="oldPassword"  />
                </td>
            </tr>
            <tr>
                <th>
                  <bean:message key="newPassword1"/>
                </th>
                <td>
                    <input type="password" class="form-input" id="passWord" name="passWord" />
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="newPassword2"/>
                </th>
                <td>
                    <input type="password" class="form-input" id="newPassWord" name="newPassWord" />
                </td>
            </tr>
            <tr>
                <th>
                </th>
                <td>
                    <input type="submit" class="form-button" value="<bean:message key='submit'/>" />
                    <input type="reset" class="form-button" value="<bean:message key='reeset'/>" />
                </td>
            </tr>
        </table>
        </form>
        <p>
            <bean:message key="WEB021032"/></p>
        <p>
            <bean:message key="passwordEffect"/></p>
    </div>
</body>
</html>

<script type="text/javascript">
document.oncontextmenu = function() {
	if (window.event)
		window.event.returnValue=false;
	return false;
};
function onPageLoad(){
	var resultStatus="${resultStatusCPWD}";
		if(resultStatus=="NORMAL"){
			location.href = "${pageContext.request.contextPath}/jsp/ChangePasswordSuccess.jsp";
		}else if(resultStatus=="FAILED"){
			alert("<bean:message key='INV_PASS'/>");
			return;
		}else if(resultStatus=="WARN"){
			alert("<bean:message key='label.reg.DBError'/>")
			return;
		}
	}

	function checkPassWord() {
		var orig_pwd = document.getElementById("oldPassword").value;
		var new_pwd = document.getElementById("passWord").value
		var confirm_pwd = document.getElementById("newPassWord").value
		var psw_P = /^[A-Za-z0-9]{6,8}$/;
		var result = true;
		if (!psw_P.test(orig_pwd)) {
			alert("<bean:message key='WEB051005'/>");
//			document.chgpwd_form.oldPassWrod.focus();
			result = false;
		} else if (!psw_P.test(new_pwd)) {
			alert("<bean:message key='WEB051004'/>");
//			document.chgpwd_form.passWord.focus();
			result = false;
		} else if (!psw_P.test(confirm_pwd)) {
			alert("<bean:message key='WEB051006'/>");
//			document.chgpwd_form.newPassWord.focus();
			result = false;	
		} else if (orig_pwd == new_pwd) {
			alert("<bean:message key='WEB051015'/>");
//			document.chgpwd_form.newPassword.focus();
			result = false;
		} else if (new_pwd != confirm_pwd) {
			alert("<bean:message key='WEB051016'/>");
//			document.chgpwd_form.newPassword.focus();
			result = false;
		}
		return result;
	}
</script>