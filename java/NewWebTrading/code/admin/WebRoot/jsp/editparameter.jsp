<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.taifook.adminportal.dto.User,com.taifook.adminportal.common.Constants" %>
<%@ page import="java.util.Iterator,java.util.List"%>
<%@ page import="com.taifook.adminportal.model.CsParameter,
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
			if(!check_underlineOrCharOrNumOrDot(document.getElementById('key'))){
				alert("invalid key, key is character or number or underline Or Dot");
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
							
			if(document.getElementById('dataType').value=='PositiveNumeric'){
				if(!check_isNumeric(document.getElementById('value').value,'+',true)){
					alert("invalid value, it's must Positive Numeric type, eg:0.001");
					setFocus('value');
					return false;
				}
			}else if(document.getElementById('dataType').value=='NegativeNumeric'){
				if(!check_isNumeric(document.getElementById('value').value,'-',true)){
					alert("invalid value, it's must Negative Numeric type,eg:-0.001");
					setFocus('value');
					return false;
				}
			}	
	<%--							
			if(!check_length(document.getElementById('key'),100,'parameter key',null)){	
				setFocus('key');				
				return false;
			}
			
			if(!check_length(document.getElementById('value'),800,'parameter value',null)){
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
    	function submitform(actionid){
    		if(actionid=='update'){
    			if(!vlidate()){
    				return false;
    			}
    			document.parameterForm.action="../updateparameter.do";    			
    		}else if(actionid=='delete'){
    			if(!confirm("are you sure!")){
    				return false;
    			}   
	    		document.parameterForm.action="../delparameter.do";  
    		}
    		document.parameterForm.submit();
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
  		CsParameter theParameter=new CsParameter();
  		try{
  			 Page testpage=(Page)session.getAttribute(Constants.RESULT);
      		 List result=(List)testpage.getThisPageElements();
   			 String key=request.getParameter("key");

   			for(Iterator it=result.iterator();it.hasNext();)
    		 {
       			CsParameter temp=(CsParameter)it.next();
      			if(temp.getKey().equalsIgnoreCase(key)){
         			theParameter=temp;
         			theParameter.setKey(temp.getKey());
         			break;
       			}
    		 } 
   	}catch(java.lang.Exception es){}
   	pageContext.setAttribute("theParameter",theParameter);
  %>
</head>  
<c:catch>

<c:choose> 
	<c:when test="${theParameter.readonly}"> 	
		<c:set var="disabled" value="disabled"/>
		<body onload="javascript:setFocus('description');">
		<c:set var="focusItem" value="description"></c:set>
	</c:when>	
	<c:otherwise>
		<body onload="javascript:setFocus('value');">
		<c:set var="focusItem" value="value"></c:set>
	</c:otherwise>
</c:choose>

 <form  name="parameterForm" method="post" action="" onReset="javascript:setFocus('${focusItem}');">
  <table width="80%" border="1" cellspacing="0" cellpadding="0" align="left">
    <tr bgcolor="#A5B7C5"> 
      <td colspan="2"><div align="center"><strong>Modify ${theParameter.key} Parameter</strong></div></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Key:</td>
      <td>
        <input type="hidden" name="key"  id="key" value="<c:out value='${theParameter.key}' default=""/>">
      	<input name=key type=text id=key size=60 disabled value="<c:out value='${theParameter.key}' default=""/>"> <font color="#FF0000">*</font> </td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">ReadOnly:</td>
      <td> 
         <c:choose>
       		<c:when test="${theParameter.readonly}">
       			<input type="hidden" name="readonly" id="readonly" value="<c:out value='${theParameter.readonly}' default=""/>">
       		</c:when>
       	  </c:choose>  
          <select name=readonly ${disabled}>     	  
          <c:choose> <c:when test="${theParameter.readonly}"> 
          <option value='true' selected>&nbsp;yes&nbsp;</option>
          </c:when> <c:otherwise> 
          <option value='false' selected>&nbsp;no&nbsp;</option>
          </c:otherwise> </c:choose> </select> <font color="#FF0000">*</font> 
      </td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">DataType:</td>
      <td>
          <c:choose>
       			<c:when test="${theParameter.readonly}">
       				<input type="hidden" name="dataType" id="dataType" value="<c:out value='${theParameter.dataType}' default=""/>">
       			</c:when>
       	  </c:choose>       	  
          <select name="dataType" id="dataType" ${disabled}>       	  
          <c:choose> <c:when test="${theParameter.dataType=='Character'}"> 
          <option value="Character" selected>Character</option>
          </c:when> <c:otherwise> 
          <option value="Character">Character</option>
          </c:otherwise> </c:choose> <c:choose> <c:when test="${theParameter.dataType=='PositiveNumeric'}"> 
          <option value="PositiveNumeric"  selected>Positive Numeric</option>
          </c:when> <c:otherwise> 
          <option value="PositiveNumeric">Positive Numeric</option>
          </c:otherwise> </c:choose> <c:choose> <c:when test="${theParameter.dataType=='NegativeNumeric'}"> 
          <option value="NegativeNumeric" selected>Negative Numeric</option>
          </c:when> <c:otherwise> 
          <option value="NegativeNumeric">Negative Numeric</option>
          </c:otherwise> </c:choose> </select><font color="#FF0000">*</font></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Value:</td>
      <td>
        <c:choose>
       		<c:when test="${theParameter.readonly}">
       			<input type="hidden" name="value" id="value" value="<c:out value='${theParameter.value}' default=""/>">
       		</c:when>
       	</c:choose>
       	  
      	<c:choose>
			<c:when test="${theParameter.key=='FUNDTRANSFER_STATUS'}">
      			<select name="value" id="value" ${disabled}>       	  
          		<c:choose> <c:when test="${theParameter.value=='ACTIVE'}"> 
          		<option value="ACTIVE" selected>ACTIVE</option>
          		</c:when> <c:otherwise> 
          		<option value="ACTIVE">ACTIVE</option>
          		</c:otherwise> </c:choose> <c:choose> <c:when test="${theParameter.value=='INACTIVE'}"> 
          		<option value="INACTIVE"  selected>INACTIVE</option>
          		</c:when> <c:otherwise> 
          		<option value="INACTIVE">INACTIVE</option>
          		</c:otherwise> </c:choose> <c:choose> <c:when test="${theParameter.value=='SUSPEND'}"> 
          		<option value="SUSPEND" selected>SUSPEND</option>
          		</c:when> <c:otherwise> 
          		<option value="SUSPEND">SUSPEND</option>
          		</c:otherwise> </c:choose> </select>
      		</c:when>
      		<c:otherwise>
        		<textarea name=value id=value cols=60 rows=5 ${disabled}><c:out value="${theParameter.value}" default=""/></textarea>
        	</c:otherwise>
        </c:choose>
        <font color="#FF0000">*</font>
      </td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">ClassID:</td>
      <td>
          <c:choose>
       		<c:when test="${theParameter.readonly}">
       			<input type="hidden" name="classid" id="classid" value="<c:out value='${theParameter.classid}' default=""/>">
       		</c:when>
       	  </c:choose>
       	  <select name="classid" size="1" id="classid">
          <option value="5">5--web parameter</option>
          </select>
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
      <td> <input type="submit" name="update" value="  save   " onclick="return submitform('update')"> 
        <input type="submit" name="delete" value="delete" onclick="return submitform('delete')"> 
       <input type="button" value="goBack" onClick="history.go(-1)" />
       </td>
    </tr>
  </table>
</form>
</c:catch>
</body>
</html:html>
