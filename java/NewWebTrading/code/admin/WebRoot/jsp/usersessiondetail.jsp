<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def" %>
<%@ page import="java.util.Iterator,java.util.List" %>
<%@ page import="com.taifook.adminportal.common.util.page.Page,
                 com.taifook.adminportal.common.Constants,com.taifook.adminportal.model.CsOnlineuser"%>
<%@ include file="js/checkurl.jsp"%>
<%@ include file="js/openhelper.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<style type="text/css">
<!--
.red {color:red}
-->
</style>
<head>
    <html:base />
    
    <title>usersessiondetail.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
	<link href="./css/link.css" rel="stylesheet" type="text/css">
	<link href="./css/global.css" rel="stylesheet" type="text/css">     
	<script language="JavaScript" src="./js/commutil.js"></script> 
<script language="javascript">
    function InvalidA(loginId,channelType){
        if(loginId=""){
            alert("UserID must be input!");
            return;
        }
        if(channelType=""){
            alert("ChannelType must be input!");
            return;
        }
        if(confirm("Please confirm to kill session of user:"+document.list.loginId.value+" in "+document.list.channelType.value+" system")){
            document.list.submit();
        }        
    }
    function searchKeydowm(e){
        dkey = e.keyCode;
        if(dkey=="13"){
        	if(document.query.userid.value==""){
	            alert("UserID must be input!");
	            setFocus('userid');
	            return false;
	        }
            searchA();
        }
    }
    function searchA(){
        if(document.query.userid.value==""){
            alert("UserID must be input!");
            setFocus('userid');
            return;
        }
        document.query.action="../queryusersessiondetail.do";
        document.query.submit();
    }
   </script> 
</head>
<body onload="javascript:setFocus('userid');">

<table width="783" border="0" align="left">
  <tr>
    <td width="777" align="left"> <form name="query" id="query" method="post">
        <table width="741" border="1" align="left" cellspacing="0" cellpadding="0">
          <tr bgcolor="#A5B7C5"> 
            <td width="197">UserID: 
              <input name="userid" type="text" id="userid" size="15" value="<c:out value="${filter.userid}" default=""/>" onkeypress="return searchKeydowm(event);"/> </td>
            <td width="228">ChannelType: 
              <select name="channelcode" id="channelcode">
              	<option value="WMT" selected>WMT</option>
              	<!-- <option value="STT">STT</option>
              	<option value="H3G">H3G</option>
              	<option value="WEB">WEB</option> -->
              </select>
            </td>
            <td><input type="button" name="search" value="Search" onclick="searchA();"/> </td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>

<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" align="left" width="783">
<tr>
<td width="777" align="left">
<table width="741" border="1" align="left" cellspacing="0" cellpadding="0">
<form name="list" id ="list" method="post" action="../invalidloginuser.do">
  <tr bgcolor="#A5B7C5"> 
    <td colspan="5"><div align="center"><STRONG> User Session Detail </STRONG><a href="javascript:;" onclick="javascript:OpenHelperBrowser('zhugan/onlineuser/onlineuser.htm');return false;"><img src="images/helper.jpg" width="43" height="18" border="0" align="right"></a></div></td>
  </tr>
  <tr bgcolor="#A5B7C5">
    <td align="center">Operate</td>
    <td align="center">UserID</td>
    <td align="center">channelType</td>
    <td align="center">LoginTime</td>
  </tr>  
  <tr>
	  <c:if test="${userinfo.loginId!=null && userinfo.loginId!=''}">
		<td align="center">
		   	<input type=hidden name=loginId value="${userinfo.loginId}"/>
		   	<input type=hidden name=channelType value="${userinfo.channelCode}"/>
		    <input type="button" name="Invalid" value="Kill" onclick="InvalidA('${userinfo.loginId}','${userinfo.channelCode}');"/>
		</td>
	  	<td align="center">
	  		<c:out value="${userinfo.loginId}" default=""/>&nbsp;
	  	</td>
		<td align="center">
	  		<c:out value="${userinfo.channelCode}" default=""/>&nbsp;
	  	</td>
		<td align="center">
	  		<def:out value="${userinfo.logintime}" length="20" datatype="date" dataformat="yyyy-MM-dd HH:mm:ss" defaultValue=""/>&nbsp;
	  	</td>	
	  </c:if>
  </tr>
</form>
</table>
</td> 
</tr>
  <tr>
    <td>
      <font class=red>
		<c:if test="${GlobalError!=null && GlobalError!=''}">
		    <c:out value="${GlobalError}" default=""/>&nbsp; 
	    </c:if>
	 </font>
    </td>
  </tr>
</table>
</body>
</html:html>
