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
	<script src="${pageContext.request.contextPath}/prototype-1.6.1.js" 
	type="text/javascript">
	</script>
	<script type="text/javascript">
		function gotClick()
		{
			//请求的地址
			var url = 'JSONExample.action';
			//将favorite表单域的值转换为请求参数
			var params = Form.serialize('form1');
			//创建Ajax.Request对象，对应于发送请求
			var myAjax = new Ajax.Request(
			url,
			{
				//请求方式：POST
				method:'post',
				//请求参数
				parameters:params,
				//指定回调函数
				onComplete: processResponse,
				//是否异步发送请求
				asynchronous:true
			});
		}
		function processResponse(request)
		{
			//使用JSON对象将服务器响应解析成JSON对象
			var res = request.responseText.evalJSON();
			alert(res);
			//遍历JSON对象的每个属性
			for(var propName in res)
			{
				$("show").innerHTML += propName + " --> " 
					+ res[propName] + "<br/>";
			}		
		}	
	</script>
</head>
<body>
<s:form id="form1">
	<s:textfield name="field1" label="Field 1"/>
	<s:textfield name="field2" label="Field 2"/>
	<s:textfield name="field3" label="Field 3"/>
	<tr><td colspan="2">
	<input type="button" value="提交" onclick="gotClick();"/>
	</td></tr>
</s:form>
<div id="show">
</div>
</body>
</html>
