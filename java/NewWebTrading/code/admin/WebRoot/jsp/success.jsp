
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="com.taifook.adminportal.common.Constants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
<html:base /> 
<title>success.jsp</title>
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
<script language="JavaScript" type="text/JavaScript">
    
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
//-->
</script>
</head>
  
  <body>
<div id="Layer1" style="position:absolute; width:356px; height:181px; z-index:1; left: 240px; top: 84px;">
  <table width="75%" border="0">
    <tr>
      <td><img src="images/success.jpg" width="348" height="173" border="0" usemap="#Map"></td>
    </tr>
    <tr> 
      <td><font color="#FF0000">
          <html:messages id="messages" message="true"></html:messages>
      </font></td>
    </tr>    
  </table>
</div>

<map name="Map" id="backMap">
  <c:choose>
  	  <c:when test="${requestScope.ReturnBackUrl==null}">
  			<area shape="circle" coords="257,67,18" href="javascript:history.back(-1)">
	  </c:when>
	  <c:otherwise>
	  		<area shape="circle" coords="257,67,18" href="${requestScope.ReturnBackUrl}">
	  </c:otherwise>
  </c:choose>	    			
</map>
</body>
</html:html>
