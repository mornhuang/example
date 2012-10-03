
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
	<html:base /> 
	<title>fail.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<base href="${pageContext.request.requestURL}"/>  
    
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
<div id="Layer1" style="position:absolute; width:517px; height:328px; z-index:1; left: 240px; top: 84px;">
  <table width="75%" border="0">
    <tr>
      <td><img src="images/error.jpg" width="344" height="173" border="0" usemap="#Map"></td>
    </tr>
    <tr> 
      <td><font color="#FF0000">       
      <c:choose>
        <c:when test="${requestScope.GlobalError!=null}">
    		<br>
    		<p><c:out value="${requestScope.GlobalError}" default=""></c:out></p>    		
    	</c:when>    	
    	<c:when test="${requestScope['org.apache.struts.action.ERROR']==null}">
    		<br>
    		<p>it may possibly occur WEB Server error or Database Server error</p>    		
    	</c:when>
    	<c:otherwise>
    		<html:errors/>
    	</c:otherwise>
    </c:choose>             
      </font></td>
    </tr>
  </table>
</div>
<map name="Map"  id="backMap">
  <c:choose>
  	  <c:when test="${requestScope.ReturnBackUrl==null}">
		  	<area shape="circle" coords="254,66,16" href="<%=request.getContextPath()%>/jsp/login.jsp></area>">
	  </c:when>
	  <c:when test="${requestScope.ReturnBackUrl eq 'LOGOUT'}">
	  		<area shape="circle" coords="254,66,16" href="<%=request.getContextPath()%>/logout.do" target="_parent">
	  </c:when>
	  <c:otherwise>
	  		<area shape="circle" coords="254,66,16" href="${requestScope.ReturnBackUrl}">
	  </c:otherwise>
  </c:choose>
</map>
</body>
</html:html>
