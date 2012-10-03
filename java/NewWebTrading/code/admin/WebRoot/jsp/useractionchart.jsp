
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.Iterator,java.util.List" %>
<%@ page import="java.io.File"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>

<%@ include file="js/checkurl.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>useractionchart.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
	<link href="./css/link.css" rel="stylesheet" type="text/css">
	<link href="./css/global2.css" rel="stylesheet" type="text/css">
	<STYLE>
		 table.style100 {width:580pt;background:#E8EDF1;}
	</STYLE>      
  </head>
  <%@ include file="date.jsp" %>
  <body>
<form name="useractiontimeform" method="post" action="../useractionchart.do;">
  	<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0"  class=style100>	
		<% 
			File graphFile=(File)session.getAttribute("USER_ACTION_CHART_FILE");
			String graphURL=request.getContextPath()+Constants.TEMP_FILE_PATH+session.getId()+"/"+ graphFile.getName();
  		%>
  		<html:img src="<%=graphURL%>"/>
  </table>
  <br>
   <table width="526" border="0" cellspacing="0">
    <tr>
      <td width="10%">&nbsp;</td>
      <td width="14%">SelectTime:</td>
      <td width="37%" colspan="1"><input type="text" name="accesstime"  value="${accesstime}" readonly="true"> <input type="button" name="change2" value="&nbsp;..&nbsp;"  onClick="show_cele_date(change2,'','',accesstime)"> 
      </td>
      <td width="11%"><a href="javascript:;" onclick="javascirpt:document.useractiontimeform.submit();return false;">Go</a></td>
      <td width="28%">&nbsp;</td>
    </tr>
  </table>
</form>
</body>
</html:html>









