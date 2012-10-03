
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="js/checkurl.jsp"%>
<%@ include file="js/openhelper.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>changepassword.jsp</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/checker.js"></script> 
   <script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/commutil.js"></script> 
   <script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/Common.js"></script> 
   <link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
  <link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">  
  <script language="javascript">
  	  function vlidate(){  	  	
  	  	var oldpwd=document.getElementById('oldPwdId');
  	  	var newpwd=document.getElementById('newPwdId');
  	  	var confirmpwd=document.getElementById('confirmPwdId');	
  	 	var parent = document.getElementById('parent');
	  	var div = document.getElementById('UpdatePwdErrDiv');
	  	if(parent!=null && div!=null){
	  		parent.removeChild(div);
	  	}
  	  	if(!check_password(oldpwd)){
  	  		alert("invalid old password,Please enter character or number,length is 6 to 8!");
  	  		setFocus('oldPwdId');
  	  		return false;
  	  	} else if(!check_password(newpwd)){
  	  		alert("invalid new password,Please enter character or number,length is 6 to 8!");
  	  		setFocus('newPwdId');
  	  		return false;
  	  	} else if(!check_password(confirmpwd)){
  	  		alert("invalid confirm password,Please enter character or number,length is 6 to 8!");
  	  		setFocus('confirmPwdId');
  	  		confirmpwd.value='';
  	  		return false;
  	  	}else if(newpwd.value!=confirmpwd.value){
  	  		alert("confirm password is not equal new password !");
  	  		setFocus('confirmPwdId');
  	  		confirmpwd.value='';
  	  		return false;
  	  	}	
  	  	return true;
  	  }
  </script> 	 	 	 
  </head>
<body onload="javascript:setFocus('oldPwdId');">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<form  action="changePassWord.do?method=changePassWord" name="aclUserProfileForm" onsubmit="return vlidate();"  method="post" onReset="javascript:setFocus('oldPwdId');">
<div id="parent"><div id="UpdatePwdErrDiv" align="center"><font color="#FF0000" size="4"><html:errors/>&nbsp;<c:out value="${retrunMessage}" default=""/></font></div></div>
<table width="80%" border="1" cellspacing="0" cellpadding="0" align="left">
  <tr bgcolor="#A5B7C5">
      <td colspan="2" align="center"> <span class="style3"> <STRONG> Change Password </STRONG> </span> </td>
  </tr>
  <tr>
    <td bgcolor="#A5B7C5">LoginId: </td>
    <td>
    <html:text property="lognId" name="aclUserProfileForm" readonly="true"></html:text>
    </td>
  </tr>
  <tr>
    <td width="22%" bgcolor="#A5B7C5">OldPassword: </td>
    <td width="78%"><input name="pwd" type="password" id="oldPwdId" size="30" maxlength="8"></td>
  </tr>
  <tr>
    <td width="22%" bgcolor="#A5B7C5">NewPassword: </td>
    <td width="78%"><input name="newpwd" type="password" id="newPwdId" size="30" maxlength="8"></td>
  </tr>
  <tr>
    <td width="22%" bgcolor="#A5B7C5">ConfirmPassword:</td>
    <td width="78%"><input name="confirmPwd" type="password" id="confirmPwdId" size="30" maxlength="8"></td>
  </tr>
  <tr>
      <td align="right">&nbsp; </td>
    <td><input type="submit" name="tijiao" value="change">
        <input type="reset" name="chongzhi" value="reset"></td>
  </tr>
</table>
</form>

  </body>
</html>
