
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def" %>
<%@ include file="js/checkurl.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
	<html:base />
  <head>

    
    <title>My JSP 'managerchannelserver.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
 	<script language="JavaScript" src="js/cs_request.js"></script>
	<script language="JavaScript" src="js/ajax_engine.js"></script>    
	<script language="JavaScript" src="js/checker.js"></script>    	
	<script language="JavaScript"  src="js/Common.js"></script>	  
	<script language="JavaScript" src="js/process_mgrsrv.js"></script> 
    <!--  read system roles from the role's proerty file//-->  	  	
	<link href="./css/link.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="./js/commutil.js"></script> 
	
	<STYLE>
		 body{
			font-size:6pt;
   			margin:0px;
			padding:0px;
			text-align:center;
			background:#E8EDF1;
			color:#000000;
			font-family: Arial, sans-serif, Verdana, Helvetica;
		}
	</STYLE>					    
  </head>
  
  <body onload="javascript:setFocus('startStopIpPort');">
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="80%" height="307" border="0" cellpadding="0" cellspacing="0" classid="123">
  <tr>
    <td width="6%">&nbsp;</td>
    <td width="89%"><table width="784" border="1" cellspacing="0">
        <tr bgcolor="#A5B7C5"> 
          <td colspan="3"><strong>Start/Stop<a href="helper.jsp#channelserver_helper" target="_blank"><img src="images/helper.jpg" width="43" height="18" border="0" align="right"></a></strong></td>
        </tr>
        <tr> 
          <td width="6%" height="51">Server Status:</td>
          <td width="85%">
          <iframe name='startStop_hidden_frame' scrolling=none id="startStop_hidden_frame" onreadystatechange="startOrStop_handler(document.frames('startStop_hidden_frame'),0);"></iframe>
          <form action="" name="startStopForm" method="GET" target="startStop_hidden_frame" onsubmit="process_startStop();">
              <table width="650" border="1" cellspacing="0" cellpadding="0">
                <tr> 
                  <td><font size="-1">Server IP and Port:</font></td>
                  <td colspan="2"><input id="startStopIpPort" name="startStopIpPort" type="text">
                    <font color="#FF0000">*</font><FONT size="-1">192.168.0.1:8080</FONT></td>
                </tr>
                <tr> 
                  <td width="25%"><font size="-1">Context Path:</font></td>
                  <td colspan="2"><input id="serverPath" name="path" type="text" value="" size="24">
                    <font color="#FF0000">*</font></td>
                </tr>
                <tr> 
                  <td height="25"> <input id="startServer" name="operatCmd" type="radio" value="start" checked>
                    start</td>
                  <td width="37%"><input id="stopServer" type="radio" name="operatCmd" value="stop">
                    stop</td>
                  <td width="38%"><input name="operat" type="submit" id="operat" value="operat"></td>
                </tr>
              </table>
            </form></td>
          <td width="9%">&nbsp; </td>
        </tr>
      </table></td>
    <td width="5%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="211">&nbsp;</td>
    <td><table width="100%" border="1" cellspacing="0">       
        <tr bgcolor="#A5B7C5"> 
          <td colspan="3"><strong>Deploy WEB Application:</strong></td>
        </tr>
        <tr> 
          <td width="1%" height="52">&nbsp;</td>
          <td width="90%">
          	<iframe name='upload_hidden_frame' id="hideFrame" style='display:none' onreadystatechange="upload_handler(document.frames('upload_hidden_frame'),document.uploadForm.deployWar.value);"></iframe>
          	<form action="" method="POST" enctype="multipart/form-data" name="uploadForm" target="upload_hidden_frame" onSubmit="submitUpload()">
          	 <!--<form action="" method="post" enctype="multipart/form-data" name="uploadForm" onSubmit="process_upLoad();return false;">//-->
              <table width="100%" border="1" cellpadding="0" cellspacing="0">
                <tr> 
	              <td><font size="-1">Server IP and Port:</font></td>
	              <td><input id="uploadIpPort" name="uploadIpPort" type="text">
                    <FONT color="#FF0000" size="-1">*</FONT><FONT size="-1">192.168.0.1:8080</FONT></td>
	            </tr>                
                <tr> 
                  <td><font size="-1">WAR file:</font></td>
                  <td><input id="deployWar" name="deployWar" type="file">
                    <font color="#FF0000">*</font></td>
                </tr>              
                <tr> 
                  <td>&nbsp;</td>
                  <td><input name="upload" type="submit" id="upload" value="upload" ></td>
                </tr>
              </table>
            </form></td>
          <td width="9%">&nbsp;</td>
        </tr>

        <tr bgcolor="#A5B7C5"> 
          <td colspan="3"><strong>UnDeploy WEB Application:</strong></td>
        </tr>
        <tr> 
          <td height="52">&nbsp;</td>
          <td>
          	<iframe name='unDeploy_hidden_frame' id="hideFrame" style='display:none' onreadystatechange="unDeploy_handler(document.frames('unDeploy_hidden_frame'),0);"></iframe>
          	 <form action="" method="GET" enctype="multipart/form-data" name="unDeployForm" target="unDeploy_hidden_frame" onSubmit="process_unDeploy();">
              <table width="100%" border="1" cellpadding="0" cellspacing="0">
                <tr> 
	              <td width="235"><font size="-1">Server IP and Port:</font></td>
	              <td width="532"><input id="unDeployIpPort" name="unDeployIpPort" type="text">
                    <FONT color="#FF0000" size="-1">*</FONT><FONT size="-1">192.168.0.1:8080</FONT></td>
	            </tr>                
                <tr> 
                  <td><font size="-1">Context Path:</font></td>
                  <td><input id="unDeployPath" name="path" type="text">
                    <font color="#FF0000">*</font></td>
                </tr>              
                <tr> 
                  <td>&nbsp;</td>
                  <td><input name="unDeploy" type="submit" id="unDeploy" value="unDeploy"></td>
                </tr>
              </table>
            </form></td>
          <td>&nbsp;</td>
        </tr>
                
      </table></td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html:html>
