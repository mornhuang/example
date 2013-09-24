<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2012, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>使用JSON插件</title>
	<script src="${pageContext.request.contextPath}/jquery-1.4.4.min.js" 
	type="text/javascript">
	</script>
</head>
<body>
<s:form id="loginForm">
	<s:textfield name="user" label="用户名"/>
	<s:textfield name="pass" label="密码"/>
	<tr><td colspan="2">
	<input id="loginBn" type="button" value="提交"/>
	</td></tr>
</s:form>
<div id="show" style="display:none;">
</div>
<script type="text/javascript">
	//为id为loginBn的按钮绑定事件处理函数
	$("#loginBn").click(function()
	{
		//指定向loginPro发送请求，以id为loginForm表单里各表单控件作为请求参数
		$.get("loginPro" , $("#loginForm").serializeArray() , 
			//指定回调函数
			function(data , statusText)
			{
				$("#show").height(80)
					.width(300)
					.css("border" , "1px solid black")
					.css("background-color" , "#efef99")
					.css("color" , "#ff0000")
					.css("padding" , "20px")
					.empty();
				$("#show").append("登录结果：" + data + "<br />");
				$("#show").show(2000);
			},
			//指定服务器响应为html
			"html");
	});
</script>
</body>
</html>
