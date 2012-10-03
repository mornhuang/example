<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<body class="body">
    <h1 class="page-title">
        <span class="shaddow"><span>新增用户</span><b></b></span>
    </h1>
    <div class="page-content">
        <div class="register-demo">
            <form action="#" method="post" id="registerForm" name="registerForm">
            <table class="form-table ui-corner-all">
                <tr>
                    <td colspan="2" class="title">
                        新增用户
                    </td>
                </tr>
                <tr class="form-table-first">
                    <th>
                        登入名称
                    </th>
                    <td>
                        <input id="loginId" name="loginId" type="text" class="form-input" onblur="isUserExist()" />
                    </td>
                </tr>
          <tr class="alternating">
			<th>登入密码</th>
			<td><input id="passWord" name="passWord" type="password" class="form-input" /></td>
		  </tr>
                <tr>
                    <th>
                        地区号码
                    </th>
                    <td>
                        <select class="jquery-select" id="addNo" name="addNo">
                            <option value="86">86</option>
                            <option value="852" selected>852</option>
                            <option value="853">853</option>
                        </select>
                        (如：中国大陆：86; 香港：852; 澳门: 853)
                    </td>
                </tr>
                <tr class="alternating">
                    <th>
                        手机号码
                    </th>
                    <td>
                        <input type="text" class="form-input" id="telephone" name="telephone" />
                    </td>
                </tr>
                <tr>
                    <th>
                        电邮地址
                    </th>
                    <td>
                        <input type="text" class="form-input" id="email" name="email"/>
                    </td>
                </tr>
                <tr class="alternating">
                    <th>
                        用户姓名
                    </th>
                    <td>
                        <input type="text" class="form-input" id="username" name="username"/>
                        (密码会以短讯发出)
                    </td>
                </tr>
                <tr>
                    <th>
                        海通国际客户
                    </th>
                    <td>
                        <p>
                            <label for="rbYes">
                                <input type="radio" name="isClient" id="rbYes" value="true"/>是</label>, 客户号码:<input type="text"
                                    class="form-input" id="clientNo" name="clientNo"/></p>
                        <p>
                            <label for="rbNo">
                                <input type="radio" name="isClient" id="rbNo" value="false"/>否</label></p>
                    </td>
                </tr>
                <tr class="alternating">
                    <th>
                    </th>
                    <td>
                        <input type="button" class="form-button" value="送出" onclick="checkBeforeSubmit(true)"/>
                        <input type="button" class="form-button" value="返回" onclick="location.href='${pageContext.request.contextPath}/nadmin/userList.do'" />
                    </td>
                </tr>
            </table>
            </form>
        </div>
    </div>
</body>
</html>
<script>
var ContextPath = "${pageContext.request.contextPath}";
</script>