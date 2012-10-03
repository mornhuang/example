
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def" %>
<%@ page import="java.util.List,java.util.Iterator" %>
<%@ page import="com.taifook.adminportal.model.CsBroadcast,
                 com.taifook.adminportal.common.util.page.Page,
                 com.taifook.adminportal.common.Constants,
                 java.text.SimpleDateFormat,java.util.Locale" %>
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
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">    
<link href="jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="jsp/css/global2.css" rel="stylesheet" type="text/css">
	<STYLE>
 		table.style100 {width:580pt;background:#E8EDF1;}
	</STYLE>	
<jsp:include page="date.jsp"/>	

<script language="JavaScript" src="jsp/js/checker.js"></script>
<script language="JavaScript" src="jsp/js/Common.js"></script>
<script language="JavaScript" src="jsp/js/commutil.js"></script>  

	<SCRIPT language="javascript">
		function vlidate(){
			//alert("test");
			var startTime=document.getElementById('starttime').value;
			var endTime=document.getElementById('endtime').value;

			var startTimeStr=formatFullDateStr(startTime);
			var endTimeStr=formatFullDateStr(endTime);
			var curTimeStr=formatDate2FullStr(new Date());
						
			if(Trim(startTime)==''){
				alert("the startTime is required!");
				setFocus('starttime');
			    return false;
			}
				
			if(Trim(endTime)==''){
				alert("the endTime is required!");
				setFocus('endTime');
			    return false;
			}
									
			if(!check_DateTime(startTime)){
			   	alert("illagel start Datetime! eg:(yyyy-mm-dd hh:mm:ss)");
			   	setFocus('starttime');
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

<%
   CsBroadcast broadcast=new CsBroadcast();
  try{
   Page testpage=(Page)session.getAttribute(Constants.RESULT);
   List result=(List)testpage.getThisPageElements();
   String key=request.getParameter("key").toLowerCase(); 
   for(Iterator it=result.iterator();it.hasNext();)
     {
       CsBroadcast temp=(CsBroadcast)it.next();
       if(temp.getSeqno().toString().equalsIgnoreCase(key)){
         broadcast=temp;
         break;
       }
     }
     
     pageContext.setAttribute("broadcast",broadcast); 
   }catch(java.lang.Exception es){}
  %>
 
<form  name="form1" method="post" action="updatebroadcast.do" onsubmit="return vlidate();" onReset="javascript:setFocus('starttime');">
<%
  if(request.getParameter("key")!=null)
   out.print("<input type=hidden name=seqno value="+request.getParameter("key")+" >");
%>
<table width="90%" border="1" cellspacing="0" cellpadding="0" class=style100>
  <tr bgcolor="#A5B7C5">
    <td colspan="4"><div align="center" ><strong>Edit new Broadcast <a href="javascript:;" onclick="javascript:OpenHelperBrowser('zhugan/Broadcast/Broadcast.htm');return false;" target="_blank"><img src="jsp/images/helper.jpg" width="43" height="18" border="0" align="right"></a></strong></div></td>
    </tr>
  <tr>
    <td width="16%" bgcolor="#A5B7C5">StartTime:</td>
    <td colspan="3">  
    	<font color="#FF0000">  	
	    <input type=text name='starttime' id='starttime' value="<def:out value="${broadcast.starttime}" length="20" datatype="date" dataformat="yyyy-MM-dd HH:mm:ss" defaultValue=""/>"><font color="#FF0000">*</font> 
        <input type="button" name="change2" value="&nbsp;..&nbsp;"  onClick="show_cele_date(change2,'','',starttime)">
     </td>
  </tr>
  <tr>
    <td bgcolor="#A5B7C5">EndTime:</td>
    <td colspan="3">
    	<input type=text name='endtime' id='endtime' value="<def:out value="${broadcast.endtime}" length="20" datatype="date" dataformat="yyyy-MM-dd HH:mm:ss" defaultValue=""/>"><font color="#FF0000">*</font> 
        <input type="button" name="change2" value="&nbsp;..&nbsp;"  onClick="show_cele_date(change2,'','',endtime)"></td>
  </tr>
  <tr>
    <td bgcolor="#A5B7C5">Level:</td>
      <td colspan="3"> 
        <% 
        //if(broadcast.getBroadcastLevel().equalsIgnoreCase(Constants.LEVEL_HIGH))
        //   out.print("<input type=radio name=level value="+Constants.LEVEL_HIGH+" checked>"+Constants.LEVEL_HIGH);  //high level
        //else
        //   out.print("<input type=radio name=level value="+Constants.LEVEL_HIGH+">"+Constants.LEVEL_HIGH);   
           
        if(broadcast.getBroadcastLevel().equalsIgnoreCase(Constants.LEVEL_COMMON))
           out.print("<input type=radio id=broadcastLevel name=broadcastLevel value="+Constants.LEVEL_COMMON+" checked>"+Constants.LEVEL_COMMON);  //normal levle
        else
           out.print("<input type=radio id=broadcastLevel name=broadcastLevel value="+Constants.LEVEL_COMMON+" >"+Constants.LEVEL_COMMON); 
           
        if(broadcast.getBroadcastLevel().equalsIgnoreCase(Constants.LEVEL_URGENT))
          out.print("<input type=radio id=broadcastLevel name=broadcastLevel value="+Constants.LEVEL_URGENT+" checked>"+Constants.LEVEL_URGENT);  //low level
       else
          out.print("<input type=radio id=broadcastLevel name=broadcastLevel value="+Constants.LEVEL_URGENT+" >"+Constants.LEVEL_URGENT);
         %>
        <font color="#FF0000">*</font> </td>
  </tr>
   <tr>
    <td bgcolor="#A5B7C5">ContentType:</td>
      <td colspan="3"> 
        <% 
        if(broadcast.getContentType().equalsIgnoreCase(Constants.TYPE_MARKET))
          out.print("<input type=radio id=contentType name=contentType value="+Constants.TYPE_MARKET+" checked>"+Constants.TYPE_MARKET);
        else
          out.print("<input type=radio id=contentType name=contentType value="+Constants.TYPE_MARKET+" >"+Constants.TYPE_MARKET);
      
       if(broadcast.getContentType().equalsIgnoreCase(Constants.TYPE_MAINTENANCE))
         out.print("<input type=radio id=contentType name=contentType value="+Constants.TYPE_MAINTENANCE+" checked>"+Constants.TYPE_MAINTENANCE);
       else
          out.print("<input type=radio id=contentType name=contentType value="+Constants.TYPE_MAINTENANCE+" >"+Constants.TYPE_MAINTENANCE);
       if(broadcast.getContentType().equalsIgnoreCase(Constants.TYPE_PROMOTION)) 
        out.print("<input type=radio id=contentType name=contentType value="+Constants.TYPE_PROMOTION+" checked>"+Constants.TYPE_PROMOTION);
       else
        out.print("<input type=radio id=contentType name=contentType value="+Constants.TYPE_PROMOTION+">"+Constants.TYPE_PROMOTION);
         %>
        <font color="#FF0000">*</font> </td>
  </tr>
  <tr>
    <td bgcolor="#A5B7C5">Channels:</td>
      <td colspan="3"> 
        <%  
      if(broadcast.getChannels().indexOf(Constants.CHANNEL_NAME_WEB)!=-1)
         out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_WEB+" checked>Web");
      else
         out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_WEB+" >Web");
      
      if(broadcast.getChannels().indexOf("WMT")!=-1)
         out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_WMT+" checked>Wmt");
       else 
         out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_WMT+">Wmt");
             
      if(broadcast.getChannels().indexOf(Constants.CHANNEL_NAME_STT)!=-1)
        out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_STT+" checked>STT");
      else
        out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_STT+" >STT");
   
      if(broadcast.getChannels().indexOf(Constants.CHANNEL_NAME_3G)!=-1) 
        out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_3G+" checked>H3G");
      else
        out.print("<input type=checkbox id=channels name=channels value="+Constants.CHANNEL_NAME_3G+" >H3G");
        %>
        <font color="#FF0000">*</font> </td>
  </tr>
  <tr>
    <td bgcolor="#A5B7C5">Language:</td>
    <td>Big5</td>
    <td>English</td>
    <td >Chinese</td>
  </tr>
  <tr>
      <td bgcolor="#A5B7C5" >Content:<font color="#FF0000">*</font></td>
    <td><textarea name="contentZhTw" id="contentZhTw"  rows="12%" cols="25%"><c:out value='${broadcast.contentZhTw}' default=""/></textarea></td>
    <td><textarea name="contentEnUs" id="contentEnUs" rows="12%" cols="25%"><c:out value='${broadcast.contentEnUs}' default=""/></textarea></td>
    <td><textarea name="contentZhCn" id="contentZhCn" rows="12%" cols="25%"><c:out value='${broadcast.contentZhCn}' default=""/></textarea></td>
  </tr>
  <tr>
    <td colspan="2"><div align="right"> 
      <input type="submit"  value="   Ok  ">
    </div></td>
    <td colspan="2"><input type="reset"  value="Reset"></td>
  </tr>
</table>
</form>
</body>
 
 
</html:html>
