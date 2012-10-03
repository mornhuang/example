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
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/until.js" ></script>
</head>

<script type="text/javascript">

document.oncontextmenu = function() {
		if (window.event)
			window.event.returnValue=false;
		return false;
};


function onPageLoad(){
	$(".header-lang .${sessionScope['org.apache.struts.action.LOCALE']}").addClass("active");
	var actionMsgs = null;
	var alertMsgs="${resultStatus}";

	<c:if test="${not empty resultStatus}">
		actionMsgs = {msg: "<bean:message key='${resultStatus}'/>"};
		<c:choose>
			<c:when test="${resultStatus == 'GRACELOGIN'}">
			if (!confirm("<bean:message key='${resultStatus}'/>")) {
						location.href = "${pageContext.request.contextPath}/jsp/Risk-disclosure.jsp";
				};
			</c:when>

			<c:when test="${resultStatus == 'CHGPASS'}">
			
				alert("<bean:message key='${resultStatus}'/>");
		
			</c:when>

			<c:when test="${resultStatus == 'ISSUED'}">
			
				alert("<bean:message key='${resultStatus}'/>");
			
			</c:when>

			<c:when test="${resultStatus == 'GRACECNT'}">
				alert("<bean:message key='${resultStatus}'/>");
			</c:when>
			

			<c:otherwise>
				alert("<bean:message key='${resultStatus}'/>");
			</c:otherwise>
		</c:choose>
	</c:if>

//↑↑↑↑验证登陆状态↑↑↑	
	
	<c:if test="${not empty StatusKey}">
		<c:choose>
			<c:when test="${changePasswordStatus == 'SUCCESS'}">
				alert("<bean:message key='${StatusKey}'/>");
				location.href = "${pageContext.request.contextPath}/jsp/Risk-disclosure.jsp";
			</c:when>
			<c:otherwise>
				alert("<bean:message key='${StatusKey}'/>");
			</c:otherwise>
		</c:choose>
	</c:if>

}
//↑↑↑↑验证修改密码状态↑↑↑	
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

function logout(){
    if(window.confirm('<bean:message key="topic.logout"/>')){
        window.location="${pageContext.request.contextPath}/logout.do?CLV=${sessionScope.CLV}";
    }
}
</script>

<body class="body" onLoad="onPageLoad()">
    <div class="warp">
        <div id="header">
            <ul class="header-lang">
                <li><a href="javascript:logout();" class="logout"><bean:message key="label.menu.logoff"/></a></li>
                <li><a href="../changeLang.do?CLV=WE25S&page=froceChangePage" class="en_US"><bean:message key="lang.en"/></a></li>
                <li><a href="../changeLang.do?CLV=WG25S&page=froceChangePage" class="zh_CN"><bean:message key="lang.gb"/></a></li>
                <li><a href="../changeLang.do?CLV=WT25S&page=froceChangePage" class="zh_TW"><bean:message key="lang.tw"/></a></li>
                <li><a href="javascript:openCommonDialog('<bean:message key="label.bottom.help"/>','FAQ.jsp');" class="help"></a></li>
            </ul>
        </div>
        <div id="container">
            <div class="ui-center">
                <div class="ui-center-content">
                    <h1 class="page-title">
                        <span class="shaddow"><span><bean:message key="changePassword"/></span><b></b></span> <span class="position"><bean:message key="accountServices"/>>
                            <bean:message key="changePassword"/></span>
                    </h1>
                    <div class="page-content">
					<form name="chgpwd_form" id="chgpwd_form" autocomplete="off" action="../forceChangePsw.do?CLV=${sessionScope.CLV}" onSubmit="return force_submit_chgPwd()" onReset="javascript:oldPassword.focus();" method="post">
                        <div class="login-change-password">
                            
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
                                       ${User.loginName}&nbsp;&nbsp;&nbsp;${loginUserInfo.custName}</p>
                                      <input name="loginId" id="loginId" type="hidden" value="${User.tradeInfoObject.custCode}" />
                                    </td>
                                </tr>
                                <tr class="alternating">
                                    <th>
                                     <bean:message key="oldPassword"/>
                                    </th>
                                    <td>
                                    	<input autocomplete="off" type="password" name="oldPassword" id="oldPassword" value="" size="10" maxlength=10>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                       <bean:message key="newPassword1"/>
                                    </th>
                                    <td>
                                    	<input autocomplete="off" type="password" name="newPassword" id="newPassword" value="" size="10" maxlength=10>
                                    </td>
                                </tr>
                                <tr class="alternating">
                                    <th>
                                        <bean:message key="newPassword2"/>
                                    </th>
                                    <td>
                                        <input autocomplete="off" type="password" name="newPassword1" id="confirm_pwd" value="" size="10" maxlength=10>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                    </th>
                                    <td>
                                        <input type="reset" class="form-button" value="<bean:message key='reeset'/>" />
                                        <input type="submit" class="form-button" value="<bean:message key='submit'/>" />
                                    </td>
                                </tr>
                            </table>
         
                            <p>
                                <bean:message key="passwordEffect2"/></p>
                            <p>
                                <bean:message key="passwordEffect"/></p>
                                 
                         
                        </div>
				        </form>
                     </div>
                </div>
            </div>
        </div>
        <div id="footer">
            <span>Copyright &copy; 2011 <bean:message key="company.name"/>. <bean:message key="label.bottom.all"/> </span> <a href="javascript:openCommonDialog('<bean:message key="label.bottom.disclaimer"/>','Disclaimers.jsp');">
                <bean:message key="label.bottom.disclaimer"/></a> | <a href="javascript:openCommonDialog('<bean:message key="label.bottom.dpp"/>','Data-Privacy-Policy.jsp');">
                   <bean:message key="label.bottom.dpp"/> </a>
        </div>
        <div id="dialog" class="hide">
            <iframe src="" frameborder="0"></iframe>
        </div>
    </div>
</body>
</html>