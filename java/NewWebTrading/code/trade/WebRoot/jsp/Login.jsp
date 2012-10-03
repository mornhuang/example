<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/until.js" ></script>
</head>
<script type="text/javascript">
if (window != top) 
	top.location.href = location.href;

$(function () {
    if($.browser.msie && $.browser.version == "6.0"){
    alert('<bean:message key="label.login.browser.version"/>');
 }
});

function loginValidator(){
	var lid_p = /[A-Za-z0-9]{5,20}/;
	var psw_P = /[A-Za-z0-9]{6,8}/;
	if (!lid_p.test(document.loginForm.loginId.value)) {
		alert("<bean:message key='errors.logonForm.logonDetailIncorrect'/>");
		document.loginForm.loginId.focus();
		return false;
	}
	if (!psw_P.test(document.loginForm.password.value)) {
		alert("<bean:message key='errors.logonForm.logonDetailIncorrect'/>");
		document.loginForm.password.focus();
		return false;
	}
	$(".login-btn-submit").attr("disabled",true);
	return true;
}

function popupAAS() {
	var desURL = '${AAS_url}';
	var popupManual = window.open(desURL, "popupAAS", "width=800,height=600, location=no,toolbar=no,resizable=yes,scrollbars=yes,status=yes,top=10,left=10");
}

$(function () {
	$(".header-lang .${sessionScope['org.apache.struts.action.LOCALE']}").addClass("active");
	<c:if test="${resultStatus != success}">
	<c:choose>
	<c:when test="${!empty returnCode}">
	alert('<bean:message key="${resultStatus}"/>[${returnCode}]');
	</c:when>
	<c:otherwise>
	alert('<bean:message key="${resultStatus}"/>');
	</c:otherwise>
	</c:choose>
	</c:if>
	<c:if test="${!empty result}">
	alert('<bean:message key="message.general.sessionKickedOff"/>');
	</c:if>
});
</script>

<body class="body">
    <div class="warp">
        <div id="header">
            <ul class="header-lang">
                <li><a href="#" class="logout disabled"><bean:message key="label.menu.logoff"/></a></li>                
                <li><a href="../changeLang.do?CLV=WE25S&page=loginPage" class="en_US"><bean:message key="lang.en"/></a></li>
                <li><a href="../changeLang.do?CLV=WG25S&page=loginPage" class="zh_CN"><bean:message key="lang.gb"/></a></li>
                <li><a href="../changeLang.do?CLV=WT25S&page=loginPage" class="zh_TW"><bean:message key="lang.tw"/></a></li>
                <li><a href="javascript:openCommonDialog('<bean:message key="label.bottom.help"/>','FAQ.jsp');" class="help"></a></li>
            </ul>
        </div>
        <div id="container">
            <div class="login">
                <div class="login-active-account">
                    <strong><bean:message key="label.login.line1" /><b><bean:message key="label.login.line2" /></b><bean:message key="label.login.line3" /></strong>
                    <input type="button" class="login-btn-active-account" value="" onclick="popupAAS();" />
                    <bean:message key="label.login.line4" /><br />
                    <bean:message key="label.login.line5" />
                </div>
                <form name="loginForm" action="../webLogin.do?CLV=${sessionScope.CLV}" class="login-form" method="post" onsubmit="return loginValidator();">
                <div>
                    <label>
                    	<bean:message key="label.login.login_id"/>
                        </label>
                    <input id="lid" type="text" value="" class="name" name="loginId" tabindex="1" />
                    <a href="javascript:;" class="login-clear"></a>
                </div>
                <div>
                    <label>
                    	<bean:message key="label.login.password"/>
                        </label>
                    <input id="pwd" type="password" value="" class="password" name="password" tabindex="2" onkeypress="return parent.pswKeyPress(event);" />
                    <a href="javascript:;" class="login-clear"></a>
                </div>
                <input type="submit" class="login-btn-submit" value="" tabindex="2" />
                </form>
                <div class="login-nav">
                    <a href="${openAccountUrl }" target="_blank" class="link-open" title="<bean:message key="label.login.AccountOpen"/>" ></a><a href="${tradeServiceUrl }" target="_blank" class="link-tradding"
                        title="<bean:message key="label.login.TradingDemo"/>"></a><a href="${simTradeUrl }" class="link-demo" target="_blank" title="<bean:message key="label.login.SimTrading"/>"></a>
                </div>
                <p class="login-notice">
                	<bean:message key="label.lblCaution"/><br/>
                	<bean:message key="label.lblCompRegStatus"/>
                   </p>
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
