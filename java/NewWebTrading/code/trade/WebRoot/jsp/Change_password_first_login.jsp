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
    <title>海通國際證券集團有限公司</title>
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
	var actionMsgs = null;
	<c:if test="${not empty loginStatus}">
		actionMsgs = {msg: "<bean:message key='${loginStatus}'/>"};
		<c:choose>
			<c:when test="${loginStatus == 'GRACELOGIN'}">
				actionMsgs.action = function() {
				  	if (!confirm(this.msg)) {
						location.href = "${pageContext.request.contextPath}/jsp/Risk-disclosure.jsp";
					}
				};
			</c:when>

			<c:when test="${loginStatus == 'CHGPASS'}">
			actionMsgs.action = function() {
			  	actionMsgs.action = function() {alert(this.msg)};
			};
			</c:when>

			<c:otherwise>
				actionMsgs.action = function() {};
			</c:otherwise>
		</c:choose>
	</c:if>
	
	if (actionMsgs) {
		actionMsgs.action();
	}

//↑↑↑↑验证登陆状态↑↑↑	
	
	<c:if test="${not empty changePasswordStatus}">
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
function submit_chgPwd() {
		var psw_P = /[A-Za-z0-9]{6,8}/;
		var result = true;
		var orig_pwd = document.chgpwd_form.oldPassword.value;
		var new_pwd = document.chgpwd_form.newPassword.value;
		var confirm_pwd = document.chgpwd_form.confirm_pwd.value;
		if (!psw_P.test(orig_pwd)) {
			alert("阁下输入的旧密码错误. 旧密码必须为6-8位数字和字母组合");
			document.chgpwd_form.oldPassword.focus();
			result = false;
		} else if (!psw_P.test(new_pwd)) {
			alert("阁下输入的新密码错误. 新密码必须为6-8位数字和字母组合");
			document.chgpwd_form.newPassword.focus();
			result = false;
		} else if (!psw_P.test(confirm_pwd)) {
			alert("阁下输入的确认密码错误. 确认密码必须为6-8位数字和字母组合");
			document.chgpwd_form.confirm_pwd.focus();
			result = false;
		} else if (orig_pwd == new_pwd) {
			alert("新密码不能与旧密码相同，请重新输入");
			document.chgpwd_form.newPassword.focus();
			result = false;
		} else if (new_pwd != confirm_pwd) {
			alert("新密码”必须与“确认密码”相同");
			document.chgpwd_form.newPassword.focus();
			result = false;
		}
		return result;
}
</script>

<body class="body" onload="onPageLoad()">
    <div class="warp">
        <div id="header">
            <ul class="header-lang">
                <li><a href="#" class="logout disabled">登出</a></li>
                <li><a href="#" class="en">Eng</a></li>
                <li><a href="#" class="zh-cn">简</a></li>
                <li><a href="#" class="zh-hk active">繁</a></li>
                <li><a href="#" class="ja">日</a></li>
                <li><a href="#" class="help"></a></li>
            </ul>
        </div>
        	<c:if test="${not empty changePasswordStatus}">
	
	</c:if>
	
	<form name="chgpwd_form" id="chgpwd_form" autocomplete="off" action="../firstChangePsw.do" onSubmit="return submit_chgPwd();" onReset="javascript:oldPassword.focus();" method="post">
        <div id="container">
            <div class="ui-center">
                <div class="ui-center-content">
                    <h1 class="page-title">
                        <span class="shaddow"><span>更改密碼</span><b></b></span> <span class="position">帳戶服務 >
                            更改密碼</span>
                    </h1>
                    <div class="page-content">
                        <div class="login-change-password">
                            <form action="">
                            <table class="form-table ui-corner-all">
                                <tr>
                                    <td colspan="2" class="title">
                                        更改密碼
                                    </td>
                                </tr>
                                <tr class="form-table-first">
                                    <th>
                                        登入名稱
                                    </th>
                                    <td>
                                       ${User.loginName}&nbsp;&nbsp;&nbsp;${loginUserInfo.custName}<input  name="loginId" id="loginId" type="hidden" value="${User.loginName}" />
                                    </td>
                                </tr>
                                <tr class="alternating">
                                    <th>
                                        舊密碼
                                    </th>
                                    <td>
                                    	<input autocomplete="off" type="password" name="oldPassword" id="oldPassword" value="" size="10" maxlength=10>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                        新密碼
                                    </th>
                                    <td>
                                    <input autocomplete="off" type="password" name="newPassword" id="newPassword" value="" size="10" maxlength=10>
                                    </td>
                                </tr>
                                <tr class="alternating">
                                    <th>
                                        確認新密碼
                                    </th>
                                    <td>
                                        <input autocomplete="off" type="password" name="newPassword1" id="confirm_pwd" value="" size="10" maxlength=10>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                    </th>
                                    <td>
                                        <input type="reset" class="form-button" value="重設" />
                                        <input type="submit" class="form-button" value="送出" />
                                    </td>
                                </tr>
                            </table>
         
                            <p>
                                密碼可以為6至8個數字或字母組合而成。</p>
                            <p>
                                新密碼將即時生效於所有互聯網交易服務媒介。</p>
                                 
                            </form>
                        </div>
                       
                    </div>
                </div>
            </div>
        </div>
        </form>
        <div id="footer">
            <span>Copyright &copy; 2010 海通國際證券集團有限公司。 版權所有</span> <a href="javascript:openCommonDialog('免責聲明','Disclaimers.html');">
                免責聲明</a> | <a href="javascript:openCommonDialog('個人資料私隱政策','Data-Privacy-Policy.html');">
                    個人資料私隱政策</a>
        </div>
    </div>
</body>
</html>