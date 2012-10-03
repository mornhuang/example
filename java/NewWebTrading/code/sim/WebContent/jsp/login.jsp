<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="${pageContext.request.requestURL}" />
<title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/jselect.js"></script>
    <script type="text/javascript" src="../Script/until.js" ></script>
    <script type="text/javascript" src="../Script/shieldingMouse.js"></script>
</head>
<script type="text/javascript">

if (window != top) 
	top.location.href = location.href;

function loginValidator(){
	var lid_p = /[A-Za-z0-9]{5,20}/;
	var psw_P = /[A-Za-z0-9]{6,8}/;
	if (!lid_p.test(document.loginForm.loginId.value)) {
		alert("<bean:message key='errors.logonForm.logonDetailIncorrect'/>");
		document.loginForm.loginId.focus();
		return false;
	}
	if (!psw_P.test(document.loginForm.passWord.value)) {
		alert("<bean:message key='errors.logonForm.logonDetailIncorrect'/>");
		document.loginForm.passWord.focus();
		return false;
	}
	$(".login-btn-submit").attr("disabled",true);
	return true;
}


function onPage() {
	$(".header-lang .${sessionScope['org.apache.struts.action.LOCALE']}").addClass("active");
	 if($.browser.msie && $.browser.version == "6.0"){ 
		 alert('<bean:message key="label.login.browser.version"/>');
	}
	var resultStatus="${resultStatus}"
	if(resultStatus == "FAILED"){
		alert("<bean:message key='errors.logonForm.logonDetailIncorrect'/>");
	}else if(resultStatus == "WARN"){
		alert("<bean:message key='label.reg.DBError'/>");
	}
	<c:if test="${!empty result}">
	alert("<bean:message key='sys002'/>");
	</c:if>
}
</script>

<body class="body" onload="onPage()">
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
            <div class="login-watermark">
                <div class="login-active-account">
                    <strong><bean:message key="label.login.line1"/><b><bean:message key="label.login.line2"/></b><bean:message key="label.login.line3"/></strong>
                    <input type="button" class="login-btn-active-account" value="" />
                   <bean:message key="label.login.line4"/><br />
                   <bean:message key="label.login.line5"/>
                </div>
                <form name="loginForm" id="loginForm" action="../webLogin.do?CLV=${sessionScope.CLV}" class="login-form" method="post" onsubmit="return loginValidator();">
                <div>
                    <label>
                      <bean:message key="label.login.login_id"/></label>
                    <input type="text" value="" id="loginId" name="loginId" class="name" tabindex="1" />
                    <a href="javascript:;" class="login-clear"></a>
                </div>
                <div>
                    <label>
                        <bean:message key="label.login.password"/></label>
                    <input type="password" id="passWord" name="passWord" value="" class="password" onkeypress="return parent.pswKeyPress(event);" tabindex="2" />
                    <a href="javascript:;" class="login-clear"></a>
                </div>
                <input type="submit" tabindex="3" class="login-btn-submit" value="" />
                </form>
                <div class="login-nav">
                    <a href="${pageContext.request.contextPath}/jsp/reg.jsp" class="link-open" title="<bean:message key="label.login.line6"/>"></a>
                </div>
                <p class="login-notice">
                  <bean:message key="label.lblCaution"/><br/>
                  <bean:message key="label.lblCompRegStatus"/>
                </p>
            </div>
            </div>
        </div>
     
        <div id="footer">
            <span><bean:message key="copyright"/> <bean:message key="copyright.year"/> <bean:message key="company.name"/>.<bean:message key="label.bottom.all"/></span> <a href="javascript:openCommonDialog('<bean:message key="label.bottom.disclaimer"/>','${pageContext.request.contextPath}/html/${sessionScope['org.apache.struts.action.LOCALE']}/Disclaimers.html');">
               <bean:message key="label.bottom.disclaimer"/></a> | <a href="javascript:openCommonDialog('<bean:message key="label.bottom.dpp"/>','${pageContext.request.contextPath}/html/${sessionScope['org.apache.struts.action.LOCALE']}/Data-Privacy-Policy.html');">
                    <bean:message key="label.bottom.dpp"/></a>
        </div>
        <div id="dialog" class="hide">
            <iframe src="" frameborder="0"></iframe>
        </div>
    </div>
</body>
</html>