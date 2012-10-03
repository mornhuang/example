<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}"/>
    <title>海通国际证券集团有限公司</title>
    <link rel="Stylesheet" type="text/css" href="${pageContext.request.contextPath}/Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="${pageContext.request.contextPath}/Style/blue/zh_CN/style.css" />
    <script src="${pageContext.request.contextPath}/Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/taifook.layout.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/jselect.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/admin.main.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/shieldingMouse.js"></script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span>更改密码</span><b></b></span>
    </h1>
    <div class="page-content">
    <div class="register-demo">
        <form action="#" id="adminInfo" method="post">
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    更改密码
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    登入名称
                </th>
                <td>
                    ${sessionScope.admin.uname}<input type="hidden" id="uname" name="uname" value="${sessionScope.admin.uname}"/>
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    旧密码
                </th>
                <td>
                    <input type="password" class="form-input" id="upass" name="upass"/>
                </td>
            </tr>
            <tr>
                <th>
                    新密码
                </th>
                <td>
                    <input type="password" class="form-input" id="newUpass" name="newUpass"/>
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    确认新密码
                </th>
                <td>
                    <input type="password" class="form-input" id="newUpassConf" />
                </td>
            </tr>
            <tr>
                <th>
                </th>
                <td>
                    <input type="button" class="form-button" value="送出" onclick="checkBeforeSubmitAdminPwd(false)"/>
                    <input type="reset" class="form-button" value="重置"/>
                </td>
            </tr>
        </table>
        </form>
        <p style="text-align:center">密码可以为6至8个数字或字母组合而成。 </p>
    </div>
    </div>
</body>
</html>
<script>
var ContextPath = "${pageContext.request.contextPath}";
$(function(){
	onPageLoad();
});
onPageLoad = function() {
	var status="${status}";
	if (status == "SUCCESS") {
		alert("修改密码成功");
	} else if (status == "FAILED"){
		alert("旧密码不正确");
	}
}
</script>