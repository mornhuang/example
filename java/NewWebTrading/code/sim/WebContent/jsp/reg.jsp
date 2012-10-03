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
<script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="../Script/jquery-ui.custom.min.js" type="text/javascript"></script>
<script src="../Script/jquery.cookie.js" type="text/javascript"></script>
<script src="../Script/taifook.layout.js" type="text/javascript"></script>
<script src="../Script/jselect.js" type="text/javascript"></script>
<script type="text/javascript" src="../Script/shieldingMouse.js"></script>
</head>

<script type="text/javascript">
 	function logout(){
            if(window.confirm('<bean:message key="topic.logout"/>')){
                window.location="${pageContext.request.contextPath}/logout.do?CLV=${sessionScope.CLV}";
            }
        }
    $(function(){
	    $(".header-lang .${sessionScope['org.apache.struts.action.LOCALE']}").addClass("active");
		$(".register-demo").scrollTop=10;
    });
</script>
<body class="body" onload="onPageLoad()">

<div class="warp">
<div id="header">
            <ul class="header-lang">
              <li><a href="javascript:;" class="logout disabled"><bean:message key="label.menu.logoff"/></a></li>
                <li><a href="../changeLang.do?CLV=WE25S&page=regPage" class="en_US"><bean:message key="lang.en"/></a></li>
                <li><a href="../changeLang.do?CLV=WG25S&page=regPage" class="zh_CN"><bean:message key="lang.gb"/></a></li>
                <li><a href="../changeLang.do?CLV=WT25S&page=regPage" class="zh_TW"><bean:message key="lang.tw"/></a></li>
            	<li><a href="javascript:openCommonDialog('<bean:message key="label.bottom.help"/>','FAQ.jsp');" class="help"></a></li>
            </ul>
</div>
<div id="container" style="overflow:auto">
	<div class="ui-center">
	<div class="ui-center-content">
	<h1 class="page-title"><span class="shaddow"><span><bean:message key="label.reg.reg"/></span><b></b></span>
	</h1>
	<div class="page-content">
	<div class="register-demo">
	<form action="../reg.do?CLV=${sessionScope.CLV}" method="post" id="registerForm" name="registerForm">
	<table class="form-table ui-corner-all">
	
		<tr>
			<td colspan="2" class="title"><bean:message key="label.reg.reg"/></td>
		</tr>
		<tr class="form-table-first">
			<th><bean:message key="label.reg.login_id"/></th>
			<td><input id="loginId" name="loginId" type="text"
				class="form-input" onblur="isUserExist()" /></td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="label.reg.password"/></th>
			<td><input id="passWord" name="passWord" type="password"
				class="form-input" /></td>
		</tr>
		<tr>
			<th><bean:message key="label.reg.addno"/></th>
			<td><select class="jquery-select" id="addNo" name="addNo">
				<option>86</option>
				<option selected="selected">852</option>
				<option>853</option>
			</select><bean:message key="label.reg.addnoEx"/> </td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="label.reg.telephone"/></th>
			<td><input type="text" class="form-input" id="telephone"
				name="telephone" /></td>
		</tr>
		<tr>
			<th><bean:message key="label.reg.email"/></th>
			<td><input type="text" class="form-input" id="email" name="email" /></td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="label.reg.username"/></th>
			<td><input type="text" class="form-input" id="username"
				name="username" /></td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="label.reg.client"/></th>
			<td>
			<p><label for="rbYes"> <input type="radio" name="isClient"
				id="rbYes" value="true"/> <bean:message key="lable.quote.wordyes"/></label> , <bean:message key="label.reg.clientNo"/>: <input type="text" class="form-input"
				id="clientNo" name="clientNo" /></p>
			<p><label for="rbNo"> <input type="radio" name="isClient"
				id="rbNo" value="false"/> <bean:message key="lable.quote.wordno"/></label></p>
			</td>
		</tr>
		<tr class="alternating">
			<th colspan="2">
			<bean:message key="label.bottom.disclaimer"/>
			<hr />
			<DIV style="overflow:auto;height:70px;">
			 <br />
			 <bean:message key="label.reg.message1"/><br />
			 <bean:message key="label.reg.message2"/><br />
			 <bean:message key="label.reg.message3"/><br />
			 <bean:message key="label.reg.message4"/><br />
			</DIV>
			<hr />
			<input type="checkbox" name="chkAgree" id="chkAgree" /> <bean:message key="label.reg.agreement"/>
			</th>
		</tr>
		<tr>
			<th></th>
			<td><input type="button" class="form-button" value="<bean:message key="submit"/>"
				onclick="checkBeforeSubmit()" /> <input type="button"
				class="form-button" value="<bean:message key="button.common.back"/>" onclick="location.href='${pageContext.request.contextPath}/index.do?CLV=${sessionScope.CLV}'" /></td>
		</tr>
	</table>
	</form>
	</div>
	</div>
	</div>
	</div>
</div>
<div id="footer"><span><bean:message key="copyright"/> <bean:message key="copyright.year"/> <bean:message key="company.name"/>。
<bean:message key="label.bottom.all"/></span> <a href="javascript:openCommonDialog('<bean:message key="label.bottom.disclaimer"/>','${pageContext.request.contextPath}/html/${sessionScope['org.apache.struts.action.LOCALE']}/Disclaimers.html');">
<bean:message key="label.bottom.disclaimer"/></a> | <a
	href="javascript:openCommonDialog('<bean:message key="label.bottom.dpp"/>','${pageContext.request.contextPath}/html/${sessionScope['org.apache.struts.action.LOCALE']}/Data-Privacy-Policy.html');">
<bean:message key="label.bottom.dpp"/></a></div>
</div>

<div id="dialog" class="hide">
     <iframe src="" frameborder="0"></iframe>
</div>
</body>
</html>

<script type="text/javascript">
var err="";
var xmlHttp;
var resText;
function createXMLHttpRequest() 
{
	try 
	{
  		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	} 
	catch (e) 
	{
  		try 
  		{
    		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  		} 
  		catch (e2) 
  		{
    		xmlHttp = false;
  		}
	}

	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') 
	{
  		xmlHttp = new XMLHttpRequest();
  		xmlHttp.overrideMimeType('text/html');
	}
    
    if(!xmlHttp)
	{
       alert("<bean:message key='label.reg.ajaxError'/>");
	   return false;
    }
}
function doRequestUsingGET(url) 
{
	createXMLHttpRequest();
	var btype=getOs();
	xmlHttp.onreadyStateChange = (btype=="MSIE")?(handleChange):(handleChange());//回调函数
	xmlHttp.open("GET", url, false);//设置连接信息(请求方式，请求的url,true表示异步方式交互)
	xmlHttp.send(null);
	xmlHttp.onreadyStateChange = (btype=="MSIE")?(handleChange):(handleChange());//回调函数
	/*
	使用POST方式请求，需要手动设置http的请求头
    xmlhttp.setRequestHeader("Content-Type","aplication/x-www-form-urlencoded");
    xmlhttp.send("name=" + username);
	*/
}
/*
readyState有五中状态：
readyState = 0 未初始化
readyState = 1 表示open方法成功调用
readyState = 2 服务器已经应答客户端请求 
readyState = 3 交互中。Http头信息已经接收，响应数据尚未接收。
readyState = 4 数据接收完成。
*/
function handleChange() 
{
	switch(xmlHttp.readyState)
	{
		case 4:
			if(xmlHttp.status == 200) 
			{
				//获取服务器端返回的数据（文本）
         		resText = xmlHttp.responseText;
				resText=resText.replace("\n","");
				resText=resText.replace("\r","");
				if(resText==""||resText==null){
					alert("<bean:message key='label.reg.DBError'/>");
				}
				if(resText=="true")
				{
					alert("<bean:message key='label.reg.loginIdError'/>");
				}
			}
			break;
	}
}

function getOs()   
{   
   if(navigator.userAgent.indexOf("IE")>0) {   
        return "MSIE";       //IE浏览器
   }
   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){   
        return "Firefox";     //Firefox浏览器
   }
   if(isChrome=navigator.userAgent.indexOf("Chrome")>0) {   
        return "Chrome";      //Safan浏览器
   } 
} 


function checkBeforeSubmit()
{
	if(document.getElementById("loginId").value=='')
	{
		str="<bean:message key='label.reg.loginIdError2'/>";
		alert(str);
		return;
	}
	if(!validLogined())
	{
		return;
	}
	if(resText=="true")
	{
		str="<bean:message key='label.reg.loginIdError'/>";
		alert(str);
		return;
	}
	if(document.getElementById("passWord").value=='')
	{
		str="<bean:message key='label.reg.passwordError'/>";
		alert(str);
		return;
	}
	if(!validPassword())
	{
		return;
	}
	if(document.getElementById("telephone").value=='')
	{
		str="<bean:message key='label.reg.telephoneError'/>";
		alert(str);
		return;
	}
	if(!validMobil())
	{
		return;
	}
	if(document.getElementById("email").value=='')
	{
		str="<bean:message key='label.reg.emailError'/>";
		alert(str);
		return;
	}
	if(!validEmail())
	{
		return;
	}
	if(document.getElementById("username").value=='')
	{
		str="<bean:message key='label.reg.usernameError'/>";
		alert(str);
		return;
	}
	if(!validNickName())
	{
		return;
	}
	if(!document.getElementById("rbYes").checked&&!document.getElementById("rbNo").checked){
			str="<bean:message key='label.reg.isClient'/>"
			alert(str);
			return;
	}
	if(document.getElementById("rbYes").checked)
	{
		if(document.getElementById("clientNo").value=="")
		{
			str="<bean:message key='label.reg.clientError'/>";
			alert(str);
			return;
		}
		
		if(!validTFACCOUNT())
		{
			return;
		}
	}else{
		document.getElementById("clientNo").value="";
	}

	if(!document.getElementById("chkAgree").checked)
	{
		str="<bean:message key='label.reg.agreementError'/>";
		alert(str);
		return;
	}
	err='';
	resText='false';
	document.getElementById("registerForm").action="<%=request.getContextPath()%>/reg.do?CLV=${sessionScope.CLV}";
	document.getElementById("registerForm").submit();
}
function validLogined()
{
	var reg=/^[A-Za-z0-9]{6,8}$/;
	val=reg.test(document.getElementById('loginId').value);
	if(!val)
	{
		str="<bean:message key='label.reg.loginIdError3'/>";
		alert(str);
		return false;
	}
	return true;
}
function validNickName()
{
	var reg=/^[A-Za-z0-9]{1,8}$/;
	val=reg.test(document.getElementById('username').value);
	if(!val)
	{
		str="<bean:message key='label.reg.usernameError2'/>";
		alert(str);
		return false;
	}
	return true;	
}
function validPassword()
{
	var reg=/^[A-Za-z0-9]{6,8}$/;
	val=reg.test(document.getElementById('passWord').value);
	if(!val)
	{
		str="<bean:message key='label.reg.passwordError2'/>";
		alert(str);
		return false;
	}
	return true;
}
function validEmail()
{
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	val=reg.test(document.getElementById('email').value);
	if(!val)
	{
		str="<bean:message key='label.reg.emailError2'/>";
		alert(str);
		return false;
	}
	return true;
}
function validMobil()
{
	var reg=/^[0-9]{6,15}$/; 
	val=reg.test(document.getElementById('telephone').value);
	if(!val)
	{
		str="<bean:message key='label.reg.telephoneError2'/>";
		alert(str);
		return false;
	}
	return true;
}

function isUserExist()
{
	val=document.getElementById("loginId").value;
	if(val!='')
	{
		url="<%=request.getContextPath()%>/isUserExist.do?loginId="+val;
		doRequestUsingGET(url);
	}
}
function validTFACCOUNT()
{
	var reg=/^[0-9]{1,8}$/;
	val=reg.test(document.getElementById('clientNo').value);
	if(!val)
	{
		str="<bean:message key='label.reg.clientError2'/>";
		alert(str);
		return false;
	}
	return true;	
}

function onPageLoad(){
	var regStatus="${regStatus}";
	if(regStatus=="SUCCESS"){
		alert("<bean:message key='label.reg.regSuccess'/>");
		location.href = "${pageContext.request.contextPath}/index.do?CLV=${sessionScope.CLV}";
	}else if(regStatus=="FAILED"){
		alert("<bean:message key='label.reg.regFail'/>")
	}else if(regStatus=="WARN"){
		alert("<bean:message key='label.reg.DBError'/>");
	}
}
</script>