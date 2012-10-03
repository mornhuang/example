
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Admin Portal V1.0</title>
  <meta http-equiv="Expires" CONTENT="0">    
  <meta http-equiv="Cache-Control" CONTENT="no-cache">  
  <base href="${pageContext.request.requestURL}"/>  
  <meta http-equiv="Pragma" CONTENT="no-cache">      
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/checker.js"></script> 
  <script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/commutil.js"></script>

  <script language="javascript">
  	  function aclUserProfileFormSubmit(){
  	  	var loginid=document.getElementById('lognId');
  	  	var pwd=document.getElementById('pwd');
  	  	var parent = document.getElementById("parent");
  	  	var div = document.getElementById("loginErrDiv");
  	  	if(parent!=null && div!=null){
  	    	parent.removeChild(div);
  	  	}
  	  	if(!check_loginid(loginid)){
  	  		alert("invalid loginId ,Please enter character or number,length is 6 to 15!");
  	  		return false;
  	  	} else if(!check_password(pwd)){
  	  		alert("invalid pwd,Please enter character or number,length is 6 to 8!");
  	  		return false;
  	  	}else{
  	  		document.aclUserProfileForm.submit();
  	  	}
  	  	document.aclUserProfileForm.submit();
  	  }
  	  
  	  function aclUserProfileFormReset(){
  	  	document.aclUserProfileForm.reset();
  	  	document.getElementById('loginErrDiv').innerHTML="";
  	  }	

  	//在页面按回车提交form
    function keyDown(e) { 
 		var keycode;
 		if(e==null){
 			keycode=event.keyCode;
 		 }
 		else{
			keycode=e.keyCode; 
	     	}
		if(keycode==13)
			{
			aclUserProfileFormSubmit();
		   }	
	  } 
	document.onkeydown = keyDown ;
  </script>
  
  <SCRIPT language="javascript">
  	
  </SCRIPT>
  	
  </head>
<body bgcolor="#E8EDF1" onload="javascript:setFocus('lognId');">
<br><br><br>
<h2 align="center">&nbsp;</h2>
<h2 align="center">&nbsp;</h2>
<table width="84%" border="0">
  <tr>
    <td>&nbsp;</td>
    <td><table width="85%" border="0" cellspacing="0">
        <tr>
          <td width="24%">&nbsp;</td>
          <td width="76%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/jsp/images/01.jpg" width="450" height="56"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
    <td width="20%">&nbsp;</td>
    <td width="80%"> <form  name="aclUserProfileForm" method="post" action="<%=request.getContextPath()%>/loginAclUserProfile.do?method=loginAclUserProfile" onReset="javascript:setFocus('lognId');">
        <table width="300" height="122" align="center" cellspacing="10">
          <tr> 
            <td height="24" colspan="2" align="center" valign="top"><div id="parent"><div id="loginErrDiv" align="center"><font color="#FF0000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:errors/>&nbsp;<c:out value="${loginMessage}" default=""/></font></div></div></td>
          </tr>
          <tr> 
            <td width="100"><div align="right"><img src="<%=request.getContextPath()%>/jsp/images/02.jpg" width="100" height="30"></div></td>
            <td width="200"><input type="text" name="lognId" id='lognId' style="width:160"></td>
          </tr>
          <tr> 
            <td width="100"><img src="<%=request.getContextPath()%>/jsp/images/03.jpg" width="100" height="30"></td>
            <td width="200"><input type="password" name="pwd" id='pwd' style="width:160"></td>
          </tr>
          <tr> 
            <td align="right">&nbsp;</td>
            <td align="left"> <table width="98%" border="0">
                <tr> 
                  <td width="45%"><a href="javascript:;"><img src="<%=request.getContextPath()%>/jsp/images/04.jpg" width="69" height="24" border="0" onClick="javascript:aclUserProfileFormSubmit();return false;"></a></td>
                  <td width="15%">&nbsp;</td>
                  <td width="40%"><a href="javascript:;"><img src="<%=request.getContextPath()%>/jsp/images/05.jpg" width="69" height="24" border="0" onClick="javascript:aclUserProfileFormReset();return false;"></a></td>
                </tr>
              </table></td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>

</body>
</html>
