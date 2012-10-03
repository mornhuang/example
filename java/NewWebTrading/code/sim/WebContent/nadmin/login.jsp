<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="${pageContext.request.requestURL}" />
<title>海通国际证券集团有限公司</title>
    <link rel="Stylesheet" type="text/css" href="${pageContext.request.contextPath}/Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="${pageContext.request.contextPath}/Style/blue/zh_CN/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/taifook.layout.js"></script>
    <script src="${pageContext.request.contextPath}/Script/jselect.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/until.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/shieldingMouse.js"></script>
</head>
<script type="text/javascript">

if (window != top) 
	top.location.href = location.href;

function loginValidator(){
	var lid_p = /[A-Za-z0-9]{5,20}/;
	var psw_P = /[A-Za-z0-9]{6,8}/;
	if (!lid_p.test(document.loginForm.loginId.value)) {
		alert("登入名称或密码错误");
		document.loginForm.loginId.focus();
		return false;
	}
	if (!psw_P.test(document.loginForm.passWord.value)) {
		alert("登入名称或密码错误");
		document.loginForm.passWord.focus();
		return false;
	}
	$(".login-btn-submit").attr("disabled",true);
	return true;
}


function onPage() {
	 if($.browser.msie && $.browser.version == "6.0"){ 
		 alert('你的浏览器版本过低，本网站未能支援，请升级至Internet Explorer 7.0或以上版本.');
	}
	var resultStatus="${status}"
	if(resultStatus == "FAILED"){
		alert("登入名称或密码错误");
	}else if(resultStatus == "WARN"){
		alert("系统暂时未能提供服务...");
	}
}
</script>

<body class="body" onload="onPage()">
    <div class="warp">
        <div id="header">
        	<ul class="header-lang">
                <li><a href="javascript:;" class="logout disabled">登出</a></li>
                <li><a href="javascript:;" class="en_US">Eng</a></li>
                <li><a href="javascript:;" class="zh_CN active">简</a></li>
                <li><a href="javascript:;" class="zh_TW">繁</a></li>
                <li><a href="javascript:;" class="help"></a></li>
            </ul> 
        </div>
        
        <div id="container">
            <div class="login">
                <form action="${pageContext.request.contextPath}/nadmin/adminLogin.do" name="loginForm" id="loginForm" action="Main.jsp" class="login-form" method="post" onsubmit="return loginValidator();">
                <div>
                    
                    <input type="text" value="" id="loginId" name="uname" class="name" tabindex="1" />
                    <a href="javascript:;" class="login-clear"></a>
                </div>
                <div>
                    
                    <input type="password" id="passWord" name="upass" value="" class="password" onkeypress="return parent.pswKeyPress(event);" tabindex="2" />
                    <a href="javascript:;" class="login-clear"></a>
                </div>
                <input type="submit" tabindex="3" class="login-btn-submit" value="" />
                </form>
                <div class="login-nav">
                </div>
            </div>
        </div>
     
        <div id="footer">
        <span>Copyright &copy;  2011 海通国际证券有限公司.版权所有</span>
        </div>
        <div id="dialog" class="hide">
            <iframe src="" frameborder="0"></iframe>
        </div>
    </div>
</body>
</html>