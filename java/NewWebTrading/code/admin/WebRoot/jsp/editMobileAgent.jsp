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
    	function submitform(actionid){
    		if(actionid=='update'){
    			if(!vlidate()){
    				return false;
    			}
    			document.mobileAgentForm.action="../updateMobileAgent.do";    			
    		}else if(actionid=='delete'){
    			if(!confirm("are you sure delete it!")){
    				return false;
    			}   
	    		document.mobileAgentForm.action="../delMobileAgent.do";  
    		}
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

  		CsSetParameter theParameter=new CsSetParameter();
  		try{
  			 Page testpage=(Page)session.getAttribute(Constants.RESULT);
      		 List result=(List)testpage.getThisPageElements();
   			 String paramName=request.getParameter("paramName");
   			 String paramValue=request.getParameter("paramValue");
   			for(Iterator it=result.iterator();it.hasNext();)
    		 {
       			CsSetParameter temp=(CsSetParameter)it.next();
      			if(temp.getId().getParamName().equalsIgnoreCase(paramName) && temp.getId().getParamValue().equalsIgnoreCase(paramValue)){
         			theParameter=temp;
         			theParameter.setId(temp.getId());
         			break;
       			}
    		 } 
   	}catch(java.lang.Exception es){}
   	pageContext.setAttribute("theParameter",theParameter);
  %>
</head>  

<body onload="javascript:setFocus('description');">
 <form  name="mobileAgentForm" method="post" action="" onReset="javascript:setFocus('${focusItem}');">
  <table width="80%" border="1" cellspacing="0" cellpadding="0">
    <tr bgcolor="#A5B7C5"> 
      <td colspan="2"><div align="center"><strong>Modify Mobile Agent</strong><a href="javascript:;" onclick="javascript:OpenHelperBrowser('zhugan/Parameters/Parameters.htm');return false;"><img src="images/helper.jpg" width="43" height="18" border="0" align="right"></a></div></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">AgentType:</td>
      <td>
        <input type="hidden" name="paramValue" id="paramValue" value="<c:out value='${theParameter.id.paramValue}' default=""/>">
        <input type="hidden" name="paramName" id="paramName" value="<c:out value='${theParameter.id.paramName}' default=""/>">
      	<input name=paramName type=text id=paramName size=60 disabled value="<c:out value='${theParameter.id.paramName}' default=""/>"> <font color="#FF0000">*</font> </td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">AgentValue:</td>
      <td>
        <textarea name=paramValue id=paramValue cols=60 rows=5 disabled><c:out value="${theParameter.id.paramValue}" default=""/></textarea>
        <font color="#FF0000">*</font>
      </td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Description:</td>
      <td><textarea name=description id=description cols=60 rows=12><c:out value="${theParameter.description}" default=""/></textarea> 
      </td>
    </tr>
    <tr> 
      <td align="right">&nbsp; </td>
      <td> <input type="button" name="update" value="  save   " onclick="submitform('update');"> 
        <input type="button" name="delete" value="delete" onclick="submitform('delete');"> 
        <input type="reset" name="Submit" value="cancel"></td>
    </tr>
  </table>
</form>
</body>
</html:html>
