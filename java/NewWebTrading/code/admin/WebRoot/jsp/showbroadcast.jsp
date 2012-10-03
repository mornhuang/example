<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def" %>
<%@ page import="java.util.Iterator,java.util.List" %>
<%@ page import="com.taifook.adminportal.common.util.page.Page,
                 com.taifook.adminportal.common.Constants,com.taifook.adminportal.model.CsBroadcast,
                 com.taifook.adminportal.common.util.DataFormatUtil" %>
<%@ include file="js/checkurl.jsp"%>
<%@ include file="js/openhelper.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>showbroadcast.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

	<script language="JavaScript" src="js/commutil.js"></script>
	<script language="JavaScript" src="js/cs_request.js"></script> 
    	<script language="JavaScript" src="js/checker.js"></script>
    	
	<link href="./css/link.css" rel="stylesheet" type="text/css">
	<link href="./css/global.css" rel="stylesheet" type="text/css">     
	<link href="./css/otherClass.css" rel="stylesheet" type="text/css">   
  </head>
  
  <body onload="javascript:setFocus('starttime');">
<table width="80%" align="left">
  <tr>
    <td align="left"><form name="broadcastForm" method="post" action="../showbroadcastinit.do">
        <table width="89%" border="1" align="left" cellspacing="0" cellpadding="0">
          <tr bgcolor="#A5B7C5"> 
            <td width="126">StartTime: 
              <input name="starttime" type="text" id="starttime" size="12" value="<c:out value="${filter.starttime}" default=""/>"></td>
            <td width="102">EndTime: 
              <input name="endtime" type="text" id="endtime" size="12" value="<c:out value="${filter.endtime}" default=""/>"></td>
            <td width="108">Level: 
              <select name="broadcastLevel" id="broadcastLevel" class=style_search >
                <c:choose> <c:when test="${filter.broadcastLevel==''}"> 
                <option value="" selected></option>
                </c:when> <c:otherwise> 
                <option value=""></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.broadcastLevel=='common'}"> 
                <option value="<%=Constants.LEVEL_COMMON%>" selected><%=Constants.LEVEL_COMMON%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.LEVEL_COMMON%>"><%=Constants.LEVEL_COMMON%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.broadcastLevel=='urgent'}"> 
                <option value="<%=Constants.LEVEL_URGENT%>" selected><%=Constants.LEVEL_URGENT%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.LEVEL_URGENT%>"><%=Constants.LEVEL_URGENT%></option>
                </c:otherwise> </c:choose> </select></td>
            <td width="106">ContentType: 
              <select name="contentType" id="contentType" class=style_search>
                <c:choose> <c:when test="${filter.contentType==''}"> 
                <option value="" selected></option>
                </c:when> <c:otherwise> 
                <option value=""></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.contentType=='market'}"> 
                <option value="<%=Constants.TYPE_MARKET%>" selected><%=Constants.TYPE_MARKET%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.TYPE_MARKET%>"><%=Constants.TYPE_MARKET%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.contentType=='maintenance'}"> 
                <option value="<%=Constants.TYPE_MAINTENANCE%>" selected><%=Constants.TYPE_MAINTENANCE%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.TYPE_MAINTENANCE%>"><%=Constants.TYPE_MAINTENANCE%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.contentType=='promotion'}"> 
                <option value="<%=Constants.TYPE_PROMOTION%>" selected><%=Constants.TYPE_PROMOTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.TYPE_PROMOTION%>"><%=Constants.TYPE_PROMOTION%></option>
                </c:otherwise> </c:choose> </select></td>
            <%--td width="110">Channels: 
              <select name="channels" id="channels" class=style_search>
                <option value=""></option>
                <c:forEach var="channels" items="${filter.channels}" begin="0" end="0"> 
                <c:set var="filterchannels" value="${channels}"></c:set> </c:forEach> 
                <c:forEach var="channel" items="${requestScope.channels}"> <c:choose> 
                <c:when test="${filterchannels==channel.key}"> 
                <option value="${channel.key}" selected>${channel.key}</option>
                </c:when> <c:otherwise> 
                <option value="${channel.key}">${channel.key}</option>
                </c:otherwise> </c:choose> </c:forEach> </select></td--%>
            <td width="114">UpdateTime: 
              <input name="lastupdatetime" type="text" id="lastupdatetime" size="12" value="<c:out value="${filter.lastupdatetime}" default=""/>"></td>
            <td><input type="submit" name="Submit" value="Search"></td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>

<p>&nbsp;</p>
<p>&nbsp;</p>
<form name="broadCastForm" method="POST" action="../deletebroadcast.do" onsubmit="">
  <table  align="left" >
  <tr>
  <td  align="left">
  <table width="100%" border="1" cellspacing="0" cellpadding="0">
    <tr bgcolor="#A5B7C5"> 
      <td colspan="11" align="center"><b>Show Broadcast List</b><a href="javascript:;" onclick="javascript:OpenHelperBrowser('zhugan/Broadcast/Broadcast.htm');return false;"><img src="images/helper.jpg" width="43" height="18" border="0" align="right"></a></td>
    </tr>
    <tr bgcolor="#A5B7C5"> 
      <td>&nbsp;</td>
      <td>ID</td>
      <td>StartTime</td>
      <td>EndTime</td>
      <td>Level</td>
      <td>Type</td>
      <td>Channels</td>
      <td>ContentTW</td>
      <td>ContentEN</td>
      <td>ContentCN</td>
      <td>LastUpdateTime</td>
    </tr>
    <%
  
  try{
    	Page testpage=(Page)session.getAttribute(Constants.RESULT);
    
   		List result=(List)testpage.getThisPageElements();
   		pageContext.setAttribute("result",result);
       }catch(java.lang.Exception es){}
    
  %>
  	<c:forEach var="broadcast" items="${result}">
  		<tr>
  			<td>
  				<input type='checkbox' name='key' value="${broadcast.seqno}" onclick="selCheckBox('key','check');">
  			</td>
  			
  			<td>
  				<a href=../editbroadcast.do?key=<c:out value='${broadcast.seqno}' default=""/>><c:out value='${broadcast.seqno}' default=""/></a>&nbsp;
  			</td>
  			<td>
  				<def:out value="${broadcast.starttime}" length="20" datatype="date" dataformat="yyyy-MM-dd HH:mm:ss" defaultValue=""/>&nbsp;
  			</td>
			<td>
				<def:out value="${broadcast.endtime}" length="20" datatype="date" dataformat="yyyy-MM-dd HH:mm:ss" defaultValue=""/>&nbsp;
  			</td>
			<td>
  				<c:out value="${broadcast.broadcastLevel}" default=""/>&nbsp;
  			</td>  
			<td>
  				<c:out value="${broadcast.contentType}" default=""/>&nbsp;
  			</td>  		
			<td>
  				<c:out value="${broadcast.channels}" default=""/>&nbsp;
  			</td>  
			<td>
  				<def:out value="${broadcast.contentZhTw}" length="10" defaultValue=""/>&nbsp;
  			</td>  
			<td>
  				<def:out value="${broadcast.contentEnUs}" length="10" defaultValue=""/>&nbsp;
  			</td>  
			<td>
  				<def:out value="${broadcast.contentZhCn}" length="10" defaultValue=""/>&nbsp;
  			</td>    			  			
			<td>
  				<def:out value="${broadcast.lastupdatetime}" length="20" datatype="date" dataformat="yyyy-MM-dd HH:mm:ss" defaultValue=""/>&nbsp;
  			</td>    			  			  				
		</tr>  						  			
  	</c:forEach> 
  </table>
</td>
</tr>

<tr>
<td  align="left">
  <table width="90%" border="0" align="left" cellpadding="0" cellspacing="0">
  <tr> 
    <td width="0%" align="center"></td>
	  <td width="13%" align="center"><font size="-1"><input name="check" type="checkbox" id="check" value="true" onclick="switchAllCheckBox('key','check');"><a href="javascript:;" onclick="selCheckBoxAll('key','check');">[selAll]</a></font></td>      
      <td width="13%" align="center"><font size="-1"><a href="javascript:;" onclick="revselCheckBoxAll('key','check')">[revSel]</a></font></td>  
      <td width="13%" align="center"><font size="-1"><a href="javascript:;" onclick="deleteSel(document.broadCastForm,'key');return false;">[delSel]</a></font></td>
      <td width="61%" align="center"><font size="-1"> &nbsp;</font></td>
  </tr>
</table>
</td>
</tr>

<tr>
<td  align="left">
 <table width="600" border="0">
  <tr> 
    <td>&nbsp;</td>
    <td width="600">
    	<div align="center">
    	<c:catch>
			<%Page testpage=(Page)session.getAttribute(Constants.RESULT);%>
		    <def:showPageSplit currentPage="<%=testpage.getThisPageNumber()%>" maxPage="<%=testpage.getLastPageNumber()%>" totalSize="<%=testpage.getTotalNumberOfElements()%>" url="<%=request.getContextPath()+"/showbroadcastinit.do"%>" parameters="${URLParam}" currentPageName="currentpage" />
	    </c:catch>
	    </div>
	</td>
    <td>&nbsp;</td>
  </tr>
</table>
</td>
</tr>
</table>
</form>
  </body>
</html:html>
