
<%@ page language="java" pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Iterator,java.util.List"%>
<%@ page import="com.taifook.adminportal.model.CsParameter,
                 com.taifook.adminportal.common.util.page.Page,
                 com.taifook.adminportal.common.Constants" %>
<%@ include file="js/checkurl.jsp"%>
<%@ include file="js/openhelper.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>channelswitch.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
	<link href="./css/link.css" rel="stylesheet" type="text/css">
	<link href="./css/global.css" rel="stylesheet" type="text/css">       
	<SCRIPT language="javascript">
		function submitAll(){
			var forms=document.forms;
			for (var i=0; i<forms.length; i++){
				forms[i].submit();
			}
		}
		function updateConfirm(frm) {
		    var de = frm.value;
		    for(var i=0; i<de.length; i++){
		        if(de[i].checked){
		            var va = de[i].value;
		            if(va=="started") return confirm('Do you want Open this channel?');
		            if(va=="stopped") return confirm('Do you want Close this channel?');
		            if(va=="stoppedAndClear") return confirm('Do you want Close and Clean all session of this channel?');
		        }
		    }
		}
	</SCRIPT>
  </head>
  
  <body>
 <%
   List result=null;
  try{
   result=(List)session.getAttribute(Constants.RESULT);
   pageContext.setAttribute("result",result);
   }catch(Exception e){}
  %>
    
  <br><br><br>

  
<table width="74%" border="1" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" align="center" bgcolor="#A5B7C5"> <span class="style3"> <STRONG> 
      <FONT size="5"></FONT> Channels Status List<FONT size="5"> </FONT> <a href="javascript:;" onclick="javascript:OpenHelperBrowser('zhugan/Channelserver/channelserver.htm');return false;" target="_blank"><img src="images/helper.jpg" width="43" height="18" border="0" align="right"></a>
      </STRONG> </span> </td>
  </tr>
  <tr> 
    <td width="20%" bgcolor="#A5B7C5">ChannelCode</td>
    <td width="20%" bgcolor="#A5B7C5">CurrentStatus</td>
    <td width="60%" bgcolor="#A5B7C5">SelectedStatus</td>
  </tr>
  <c:catch>
  <c:forEach var="channel" items="${result}">   
  <form method="post" action="../channelsswitchupdate.do">
    <tr> 
      <td><input name="key" type="hidden" id="key" value="${channel.key}">      	 
          <input name="dataType" type="hidden" id="dataType" value="${channel.dataType}">
      	  <input name="classid" type="hidden" id="classid" value="${channel.classid}">
      	  <input name="description" type="hidden" id="description" value="${channel.description}">
        ${channel.key}</td>
      <td>
          <c:choose>
      		<c:when test="${channel.value=='started'}">
      			${channel.value}
			</c:when>
			<c:when test="${channel.value=='stopped'}">
      			stopped
			</c:when> 		
      		<c:when test="${channel.value=='stoppedAndClear'}">
      			stoppedAndClear
      		</c:when>      		
      	</c:choose>
      </td>
      <td> 
        <c:choose> 
	        <c:when test="${channel.value == 'started'}"> 
		        <input type="radio" name="value" value="started" checked>
		        <label>Open</label>
		        &nbsp; 
		        <input type="radio" name="value" value="stopped">
		        <label>Close</label>
		        &nbsp;
		        <input type="radio" name="value" value="stoppedAndClear">
		        <label>Close and Clean all session</label>
		        &nbsp;&nbsp;&nbsp;<input name="Submit" type="submit" value="update" onclick="return updateConfirm(this.form);"> 
	        </c:when>
	        <c:when test="${channel.value == 'stopped'}"> 
		        <input type="radio" name="value" value="started" >
		        <label>Open</label>
		        &nbsp; 
		        <input type="radio" name="value" value="stopped" checked>
		        <label>Close</label>
		        &nbsp;
		        <input type="radio" name="value" value="stoppedAndClear">
		        <label>Close and Clean all session</label>
		        &nbsp;&nbsp;&nbsp;<input name="Submit" type="submit" value="update" onclick="return updateConfirm(this.form);"> 
	        </c:when>	        
	        <c:when test="${channel.value == 'stoppedAndClear'}">
		        <input type="radio" name="value" value="started" >
		        <label>Open</label>
		        &nbsp; 
		        <input type="radio" name="value" value="stopped">
		        <label>Close</label>
		        &nbsp;
		        <input type="radio" name="value" value="stoppedAndClear" checked>
		        <label>Close and Clean all session</label>
		        &nbsp;&nbsp;&nbsp;<input name="Submit" type="submit" value="update" onclick="return updateConfirm(this.form);"> 
	        </c:when> 
        </c:choose>
      </td>
    </tr>
  </form>
  </c:forEach> 
  </c:catch>
  <tr> 
    <td colspan="3" align="right"><table width="40" border="0" cellspacing="0">
        <tr> 
          <td width="205">&nbsp;</td>
          <td width="259" align="center">
          </td>
          <td width="287">&nbsp;</td>
        </tr>
      </table></td>
  </tr>
</table>
  </body>
</html:html>
