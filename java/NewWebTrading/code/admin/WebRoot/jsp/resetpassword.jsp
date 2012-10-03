
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
  
    <title>resetpassword.jsp</title>
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
       var loginId=document.getElementById("lognId").value;
       if(loginId==""){
            alert("loginId must input!");
            return false;
           }
	  }
  </script> 	 	 	 
  </head>
<body>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<form  action="resetPassWord.do?method=resetPassWord" name="aclUserProfileForm" onsubmit="return vlidate();"  method="post" >
<div id="UpdatePwdErrDiv" align="center"><font color="#FF0000" size="3"><html:errors/>&nbsp;<c:out value="${retrunMessage}" default=""/></font></div>
<table width="80%" border="1" cellspacing="0" cellpadding="0" align="left">
  <tr bgcolor="#A5B7C5">
      <td colspan="2" align="center"> <span class="style3"> <STRONG> Reset Password </STRONG> </span> </td>
  </tr>
  <tr>
    <td bgcolor="#A5B7C5">LoginId: </td>
    <td>
    <html:text property="lognId" name="aclUserProfileForm" ></html:text><font color="red">*</font>
    </td>
  </tr>
  <logic:present name="newPassWord" >
     <tr>
     <td bgcolor="#A5B7C5">New PassWord: </td>
     <td>${newPassWord}</td>
     </tr>
  </logic:present>
  <tr>
      <td align="right">&nbsp; </td>
    <td><input type="submit" name="tijiao" value="Reset PassWord">
    </td>
  </tr>
</table>
</form>

  </body>
</html>
