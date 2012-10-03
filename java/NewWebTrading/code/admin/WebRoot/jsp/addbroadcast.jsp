
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="js/checkurl.jsp"%>
<%@ include file="js/openhelper.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<head>
<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<link href="./css/link.css" rel="stylesheet" type="text/css">
<link href="./css/global2.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="js/checker.js"></script>
<script language="JavaScript" src="js/Common.js"></script>
<script language="JavaScript" src="js/commutil.js"></script>  
	
<STYLE>
 	table.style100 {width:580pt;background:#E8EDF1;}
</STYLE>
<jsp:include page="date.jsp"/>

	<SCRIPT language="javascript">
		function vlidate(){
			var startTime=document.addBroadcastForm.starttime.value;
			var endTime=document.addBroadcastForm.endtime.value;
			
			var startTimeStr=formatFullDateStr(startTime);
			var endTimeStr=formatFullDateStr(endTime);
			var curTimeStr=formatDate2FullStr(new Date());
			
			//alert(startTimeStr);					
			//alert(endTimeStr);
			//alert("curr:"+curTimeStr);						
			if(Trim(startTime)==''){
				alert("the startTime is required!");
				setFocus('startTime');
			    return false;
			}
				
			if(Trim(endTime)==''){
				alert("the endTime is required!");
				setFocus('endTime');
			    return false;
			}
									
			if(!check_DateTime(startTime)){
			   	alert("illagel start Datetime! eg:(yyyy-mm-dd hh:mm:ss)");
			   	setFocus('startTime');
			    return false;
			}				
			if(!check_DateTime(endTime)){
				alert("illagel end Datetime! eg:(yyyy-mm-dd hh:mm:ss)");
				setFocus('endTime');
			    return false;
			}
			
			if(endTimeStr<startTimeStr){
				alert("illagel end Datetime! the end Datetime is less than start Datetime!");
				setFocus('endTime');
			    return false;			
			}
						
			if(endTimeStr<curTimeStr){
				alert("illagel end Datetime! the end Datetime is less than current Datetime!");
				setFocus('endTime');
			    return false;			
			}
						
			if(Trim(selCheckBoxUrlParam('channels'))==''){
				alert("the channels is required!");
				setFocus('channels');
			    return false;
			}			
			
			if(document.getElementById('contentZhTw').value=='' && document.getElementById('contentEnUs').value=='' && document.getElementById('contentZhCn').value==''){
				alert("the Content is required!");
				setFocus('contentZhTw');
			    return false;
			}
	<%--						
			if(!check_length(document.getElementById('contentZhTw'),255,'broadcast taiwan chinese content',null)){					
				setFocus('contentZhTw');
				return false;
			}
			
			if(!check_length(document.getElementById('contentEnUs'),255,'broadcast english content',null)){
				setFocus('contentEnUs');
				return false;
			}
					
			if(!check_length(document.getElementById('contentZhCn'),255,'broadcast simple chinese content',null)){
				setFocus('contentZhCn');
				return false;
			}
	--%>												
			return true;
		}		
	</SCRIPT>
</head>

<body onload="javascript:setFocus('starttime');">

<form name="addBroadcastForm" method="post" action="../addbroadcast.do"  onsubmit="return vlidate();" onReset="javascript:setFocus('starttime');">
  <table width="781" border="1" cellspacing="0" cellpadding="0" class=style100>
    <tr bgcolor="#A5B7C5"> 
      <td colspan="4"><div align="center"><strong>Add new Broadcast<a href="javascript:;" onclick="javascript:OpenHelperBrowser('zhugan/Broadcast/Broadcast.htm');return false;"><img src="images/helper.jpg" width="43" height="18" border="0" align="right"></a></strong></div></td>
    </tr>
    <tr> 
      <td width="23%" bgcolor="#A5B7C5">StartTime:</td>
      <td colspan="3"><input type="text" name="starttime" id="starttime"> <font color="#FF0000">*</font> 
        <input type="button" name="change2" value="&nbsp;..&nbsp;"  onClick="show_cele_date(change2,'','',starttime)"> 
      </td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">EndTime:</td>
      <td colspan="3"><input type="text" name="endtime" id="endtime"> <font color="#FF0000">*</font> 
        <input type="button" name="change2" value="&nbsp;..&nbsp;"  onClick="show_cele_date(change2,'','',endtime)"></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Level:</td>
      <td colspan="3"> 
        <%       
        //out.print("<input type=radio name=level value="+Constants.LEVEL_HIGH+">");  //high level
        //out.println(Constants.LEVEL_HIGH);
        
        out.print("<input type=radio id=broadcastLevel name=broadcastLevel value="+Constants.LEVEL_COMMON+" checked>");  //normal levle
        out.println(Constants.LEVEL_COMMON);
        
        out.print("<input type=radio id=broadcastLevel name=broadcastLevel value="+Constants.LEVEL_URGENT+">");  //low level
        out.println(Constants.LEVEL_URGENT);
         %>
        <font color="#FF0000">*</font> </td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">ContentType</td>
      <td colspan="3"> 
        <% 
        out.print("<input type=radio id=contentType name=contentType value="+Constants.TYPE_MARKET+">");
        out.println(Constants.TYPE_MARKET);
      
        out.print("<input type=radio id=contentType name=contentType value="+Constants.TYPE_MAINTENANCE+" checked>");
        out.println(Constants.TYPE_MAINTENANCE);
        
        out.print("<input type=radio id=contentType name=contentType value="+Constants.TYPE_PROMOTION+">");
        out.print(Constants.TYPE_PROMOTION);
         %>
        <font color="#FF0000">*</font> </td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Channels:</td>
      <td colspan="3"> 
        <% 
        out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_WEB+" checked>");
        %>
        Web 
        <%
        out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_WMT+" checked>");
        %>   
        Wmt      
        <%
        out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_STT+" checked>");
        %>
        STT 
        <%
        out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_3G+" checked>");
        %>
        H3G <font color="#FF0000"> *</font></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Language:</td>
      <td width="21%"><div align="center">Big5</div></td>
      <td width="28%"><div align="center">English</div></td>
      <td width="28%" ><div align="center">Chinese</div></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5" >Content:<font color="#FF0000">*</font></td>
      <td><textarea name="contentZhTw" id="contentZhTw" rows="12%" cols="25%"></textarea></td>
      <td><textarea name="contentEnUs" id="contentEnUs" rows="12%" cols="25%"></textarea></td>
      <td><textarea name="contentZhCn" id="contentZhCn" rows="12%" cols="25%"></textarea> 
      </td>
    </tr>
    <tr> 
      <td colspan="2"><div align="right"> 
          <input name="submit" type="submit"  value="   Ok  ">
        </div></td>
      <td colspan="2"><input name="reset" type="reset"  value="Reset"></td>
    </tr>
  </table>
</form>
</body>
 
 
</html:html>
