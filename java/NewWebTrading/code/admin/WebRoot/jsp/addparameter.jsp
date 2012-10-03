
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.taifook.adminportal.dto.User,com.taifook.adminportal.common.Constants" %>
<%@ include file="js/checkurl.jsp"%>
<%@ include file="js/openhelper.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>addparameter.jsp</title>
    
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
			if(Trim(document.getElementById('key').value)==""){
				alert("parameter key is required");
				setFocus('key');
				return false;
			}
			
			if(Trim(document.getElementById('value').value)==""){
				alert("parameter value is required");
				setFocus('value');
				return false;
			}
			
			if(Trim(document.getElementById('dataType').value)==""){
				alert("parameter dataType is required");
				setFocus('dataType');
				return false;
			}
			
			if(!check_underlineOrCharOrNumOrDot(document.getElementById('key'))){
				alert("invalid key, key is character or number or underline Or Dot");
				setFocus('key');
				return false;
			}
			
			if(document.getElementById('dataType').value=='PositiveNumeric'){
				if(!check_isNumeric(document.getElementById('value').value,'+',true)){
					alert("invalid value, it's must Positive Numeric type eg:0.001");
					setFocus('value');
					return false;
				}
			}else if(document.getElementById('dataType').value=='NegativeNumeric'){
				if(!check_isNumeric(document.getElementById('value').value,'-',true)){
					alert("invalid value, it's must Negative Numeric type eg:-0.001");
					setFocus('value');
					return false;
				}
			}			
	<%--					
			if(!check_length(document.getElementById('key'),100,'parameter key',null)){
				setFocus('key');					
				return false;
			}
			
			if(!check_length(document.getElementById('value'),800,'parameter english',null)){
				setFocus('value');
				return false;
			}
					
			if(!check_length(document.getElementById('description'),255,'parameter description',null)){
				setFocus('description');
				return false;
			}
	--%>			
			if(document.getElementById('classid').value=='7'){
				var value=Trim(document.getElementById('value').value);
				if(value!='started' && value!='stopped'){
					alert("invalid value! the value must is started or stopped !");
					setFocus('value');
					return false;
				}
				
				var readonly=Trim(document.getElementById('readonly').value);
				if(readonly!='true'){
					alert("invalid readonly! the readonly must selected yes !");
					setFocus('readonly');
					return false;
				}
			}
												
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
			document.addParameterForm.submit();			
		}	
	} 
	/*document.onkeydown = keyDown**/
  </SCRIPT>
   
  </head>
  
  <body onload="javascript:setFocus('key');">
 <form name="addParameterForm" method="post" action="../addparameter.do" onsubmit="javascript:return vlidate();" onReset="javascript:setFocus('key');">
  <table width="80%" border="1" cellspacing="0" cellpadding="0" align="left">
    <tr>
    <td  bgcolor="#A5B7C5" colspan="2"><strong>Add New Parameter</strong></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Key:</td>
      <td>
        <input name="key" type="text" id='key' size=77 ><font color="#FF0000"> *</font></td>
    </tr>
    <tr>
    <td bgcolor="#A5B7C5">ReadOnly:</td>
    <td><select name="readonly" id="readonly" size="1">
        <option value="false">&nbsp;no&nbsp;</option>
        <option value="true">&nbsp;yes&nbsp;</option>
      </select> <font color="#FF0000"> *</font></td>
    <tr> 
      <td bgcolor="#A5B7C5">DataType:</td>
      <td><select name="dataType" id="dataType" id="select">
          <option value="Character">Character</option>
          <option value="PositiveNumeric">Positive Numeric</option>
          <option value="NegativeNumeric">Negative Numeric</option>
        </select><font color="#FF0000">*</font> </td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Value:</td>
      <td><textarea name=value id=value  cols=60 rows=5></textarea> <font color="#FF0000">*</font></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">ClassID:</td>
      <td><select name="classid" size="1" id="classid">
          <option value="5">5--web parameter</option>
          </select> <font color="#FF0000"> *</font></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Description:</td>
      <td><textarea name="description" id="description"  cols="60" rows="12" width></textarea> 
      </td>
    </tr>
    <tr> 
      <td align="right">&nbsp; </td>
      <td> <input type="submit" name="Submit2" value="   ok   " onKeyPress="javascript:keyDown();"> 
        <input type="reset" name="Submit" value="cancel"></td>
    </tr>
  </table>
</form>
</body>
</html:html>
