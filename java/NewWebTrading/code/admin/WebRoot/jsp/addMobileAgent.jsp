<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.taifook.adminportal.dto.User,com.taifook.adminportal.common.Constants" %>
<%@ page import="java.util.Iterator,java.util.List"%>
<%@ page import="com.taifook.adminportal.model.CsSetParameter,
                 com.taifook.adminportal.common.util.page.Page,
                 com.taifook.adminportal.common.Constants" %>
<%@ page import="java.net.URLDecoder" %>
<%@ include file="js/checkurl.jsp"%>       
<%@ include file="js/openhelper.jsp"%>          

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>editparameter.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
	<link href="./css/link.css" rel="stylesheet" type="text/css">
	<link href="./css/global.css" rel="stylesheet" type="text/css">     
	<script language="JavaScript" src="js/checker.js"></script>
	<script language="JavaScript" src="js/Common.js"></script>
	<script language="JavaScript" src="js/commutil.js"></script>  

	<SCRIPT language="javascript">
		function vlidate(){		
			if(Trim(document.getElementById('paramValue').value)==""){
				alert("Agent Value is required");
				setFocus('paramValue');
				return false;
			}										
			return true;
		}		
	</SCRIPT>
		  
    <SCRIPT language="javascript">
    	function submitform(){
   			if(!vlidate()){
   				return false;
   			}
   			document.mobileAgentForm.action="../addMobileAgent.do";    			
    		document.mobileAgentForm.submit();
    		return true;
    	}
    	    	
    </SCRIPT>
    
  <SCRIPT language="javascript">
  		/*在页面按回车提交form*/
    　　 function keyDown(e) { 
  		var keycode;
  		if(e==null){
  			keycode=event.keyCode;
  		}else{
  　	　　 	keycode=e.keyCode; 
	  	}
		if(keycode==13){
			//document.parameterForm.submit();
			submitform('update');
		}	
	  } 
	/*document.onkeydown = keyDown */
  </SCRIPT>
    <%
  		User sessionUser=(User)session.getAttribute(Constants.SESSION_USER);
  		pageContext.setAttribute("roles",sessionUser.getRightsManager().getRightsMap());
  		String paramName=request.getParameter("paramName");
   	    pageContext.setAttribute("theParameter",paramName);
  %>
</head>  

<body onload="javascript:setFocus('paramValue');">
 <form  name="mobileAgentForm" method="post" action="" onReset="javascript:setFocus('${focusItem}');">
  <table width="80%" border="1" cellspacing="0" cellpadding="0">
    <tr bgcolor="#A5B7C5"> 
      <td colspan="2"><div align="center"><strong>Add Mobile Agent</strong><a href="javascript:;" onclick="javascript:OpenHelperBrowser('zhugan/Parameters/Parameters.htm');return false;"><img src="images/helper.jpg" width="43" height="18" border="0" align="right"></a></div></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">AgentType:</td>
      <td>
         <select name="paramName" id="paramName">
          	<option value="AgentBlackList" selected>AgentBlackList</option>
          	<option value="AgentPcBlackList">AgentPcBlackList</option>
          	<option value="AgentWhiteList">AgentWhiteList</option>          	
         </select>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">AgentValue:</td>
      <td>
        <textarea name=paramValue id=paramValue cols=60 rows=5></textarea>
        <font color="#FF0000">*</font>
      </td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Description:</td>
      <td><textarea name=description id=description cols=60 rows=12></textarea> 
      </td>
    </tr>
    <tr> 
      <td align="right">&nbsp; </td>
      <td> <input type="button" name="update" value="  save   " onclick="submitform()"> 
        <input type="reset" name="Submit" value="cancel"></td>
    </tr>
  </table>
</form>
</body>
</html:html>
