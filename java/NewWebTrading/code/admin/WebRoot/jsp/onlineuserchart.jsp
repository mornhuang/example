<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.io.File,com.taifook.adminportal.common.Constants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="js/checkurl.jsp"%>

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
    
	<link href="./css/link.css" rel="stylesheet" type="text/css">
	<link href="./css/global.css" rel="stylesheet" type="text/css">       
  </head>
  
  <body>
  
<p>&nbsp;</p>
<p>&nbsp;</p>
<table  width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="20%">&nbsp;</td>
    <td width="80%">
	  	<%
    		File graphFile=(File)session.getAttribute("ONLINE_USER_DETAIL_CHART_FILE");
    		String graphURL=request.getContextPath()+Constants.TEMP_FILE_PATH+session.getId()+"/"+ graphFile.getName();
  		%>	
  	<html:img src="<%=graphURL%>"/>
	</td>
  </tr>
</table>
</body>

</html:html>


