<%@ taglib prefix="ww" uri="webwork" %>
<%@ page import="com.redsaga.hibernatesample.step4.User"%>
<%@ page contentType="text/html;charset=utf-8"%>
<ww:i18n name="'messages'">
	<head>
	<link rel="stylesheet" href="style.css" type="text/css">
	</head>
<h1>RedSaga Forum Demo</h1>
        <ww:if test="#session['loginUser']==null">
<form action="login.action">
    <ww:text name="'login.username'"/><input name="model.name"/>
    <ww:text name="'login.password'"/><input type='password' name="model.pwd"/>
    <input type="submit" value="<ww:text name="'login.login'"/>"/>
    <ww:text name="'login.default'"/>
</form>
        </ww:if>
        <ww:else>
        <ww:text name="'login.currentUser'"/><ww:property value="#session['loginUser'].name"/>. &nbsp;
            [<a href="logout.action" ><ww:text name="'login.logout'"/></a>]
        </ww:else>
</ww:i18n>
