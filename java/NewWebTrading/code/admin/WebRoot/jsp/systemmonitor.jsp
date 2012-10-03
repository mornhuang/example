<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def" %>
<%@ page import="java.util.Iterator,java.util.List" %>
<%@ page import="com.taifook.adminportal.common.util.page.Page,
                 com.taifook.adminportal.common.Constants,com.taifook.adminportal.model.CsServicemonitor"%>
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
	<script language="JavaScript" src="js/commutil.js"></script>
	<script language="JavaScript" src="js/cs_request.js"></script>   
    
	<link href="./css/link.css" rel="stylesheet" type="text/css">
	<link href="./css/global.css" rel="stylesheet" type="text/css"> 
	<link href="./css/otherClass.css" rel="stylesheet" type="text/css">  
	<script language="JavaScript" src="./js/commutil.js"></script>  
  </head>
  
  <body onload="javascript:setFocus('ip');">
<%  
  Page testpage=null;
  List result=null;
  try{
    testpage=(Page)session.getAttribute(Constants.RESULT);
    result=(List)testpage.getThisPageElements();
    pageContext.setAttribute("result",result);    
   }catch(Exception e){}
      
  %>
<table width="80%" align="left">
  <tr>
    <td align="left"> <form  method="post" action="../systemmonitor.do">
        <table width="760" border="1" align="left" cellspacing="0" cellpadding="0">
          <tr bgcolor="#A5B7C5"> 
            <td width="104">IP: 
              <input name="ip" type="text" id="ip" size="12" value="<c:out value="${filter.ip}" default=""/>">
            </td>          
            <td width="112">ServiceName: 
              <select name="servicename" id="servicename" class=style_search>
                <c:choose> <c:when test="${filter.servicename==''}"> 
                <option value="" selected></option>
                </c:when> <c:otherwise> 
                <option value=""></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.servicename=='MCS'}"> 
                <option value="<%=Constants.MCS_SERVICE%>" selected><%=Constants.MCS_SERVICE%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.MCS_SERVICE%>"><%=Constants.MCS_SERVICE%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.servicename=='ES'}"> 
                <option value="<%=Constants.ES_SERVICE%>" selected><%=Constants.ES_SERVICE%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.ES_SERVICE%>"><%=Constants.ES_SERVICE%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.servicename=='QS'}"> 
                <option value="<%=Constants.QS_SERVICE%>" selected><%=Constants.QS_SERVICE%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.QS_SERVICE%>"><%=Constants.QS_SERVICE%></option>
                </c:otherwise> </c:choose> </select></td>
            <td width="107">ActionName: 
              <select name="actionId" id="actionId" class=style_search>
                <c:choose> <c:when test="${filter.actionId==''}"> 
                <option value="" selected></option>
                </c:when> <c:otherwise> 
                <option value=""></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionId=='ChangePassword'}"> 
                <option value="<%=Constants.CHANGE_PASSWORD_ACTION%>" selected><%=Constants.CHANGE_PASSWORD_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.CHANGE_PASSWORD_ACTION%>"><%=Constants.CHANGE_PASSWORD_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionId=='DelayQuotation'}"> 
                <option value="<%=Constants.DELAY_QUOTATION_ACTION%>" selected><%=Constants.DELAY_QUOTATION_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.DELAY_QUOTATION_ACTION%>"><%=Constants.DELAY_QUOTATION_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionId=='InputOrder'}"> 
                <option value="<%=Constants.INPUT_ORDER_ACTION %>" selected><%=Constants.INPUT_ORDER_ACTION %></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.INPUT_ORDER_ACTION %>"><%=Constants.INPUT_ORDER_ACTION %></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionId=='Login'}"> 
                <option value="<%=Constants.LOGIN_ACTION%>" selected><%=Constants.LOGIN_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.LOGIN_ACTION%>"><%=Constants.LOGIN_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionId=='Logout'}"> 
                <option value="<%=Constants.LOGOUT_ACTION%>" selected><%=Constants.LOGOUT_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.LOGOUT_ACTION%>"><%=Constants.LOGOUT_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionId=='QueryOrder'}"> 
                <option value="<%=Constants.QUERY_ORDER_ACTION%>" selected><%=Constants.QUERY_ORDER_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.QUERY_ORDER_ACTION%>"><%=Constants.QUERY_ORDER_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionId=='QueryPrice'}"> 
                <option value="<%=Constants.QUERY_PRICE_ACTION%>" selected><%=Constants.QUERY_PRICE_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.QUERY_PRICE_ACTION%>"><%=Constants.QUERY_PRICE_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionId=='RealQuotation'}"> 
                <option value="<%=Constants.REALTIME_QUOTATION_ACTION%>"  selected><%=Constants.REALTIME_QUOTATION_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.REALTIME_QUOTATION_ACTION%>"><%=Constants.REALTIME_QUOTATION_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionId=='Test'}"> 
                <option value="<%=Constants.TEST_ACTION%>" selected><%=Constants.TEST_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.TEST_ACTION%>"><%=Constants.TEST_ACTION%></option>
                </c:otherwise> </c:choose> </select></td>
            <td width="107">Status: 
              <select name="status" id="status" class=style_search>
                <c:choose> <c:when test="${filter.status==''}"> 
                <option value="" selected></option>
                </c:when> <c:otherwise> 
                <option value=""></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.status=='Error'}"> 
                <option value="<%=Constants.ERROR_STATUS%>" selected><%=Constants.ERROR_STATUS%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.ERROR_STATUS%>"><%=Constants.ERROR_STATUS%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.status=='Warn'}"> 
                <option value="<%=Constants.WARNING_STATUS%>" selected><%=Constants.WARNING_STATUS%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.WARNING_STATUS%>"><%=Constants.WARNING_STATUS%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.status=='Normal'}"> 
                <option value="<%=Constants.NORMAL_STATUS%>" selected><%=Constants.NORMAL_STATUS%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.NORMAL_STATUS%>"><%=Constants.NORMAL_STATUS%></option>
                </c:otherwise> </c:choose> </select></td>
            <td width="110">ExpendTime: 
              <input name="expendtime" type="text" id="expendtime" size="12" value="<c:out value="${filter.expendtime}" default=""/>">
            </td>
            <td width="105">AccessTime: 
              <input name="accesstime" type="text" id="accesstime" size="12" value="<c:out value="${filter.accesstime}" default=""/>">
            </td>
            <td width="56"><input type="submit" name="Submit" value="Search"></td>
            <td width="56"><a href="helper.jsp#channelserver_helper" target="_blank"><img src="images/helper.jpg" width="43" height="18" border="0" align="middle"></a></td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>

<p>&nbsp;</p>
<p>&nbsp;</p>
<form name="monitorForm" method="POST" action="../deletesysmonitor.do" onsubmit="";>
  <table align="left" style="margin-left:-15px">
  <tr>
  <td align="left">  
  <table width="88%" border="1" align="center" cellspacing="0">
    <tr bgcolor="#A5B7C5"> 
      <td colspan="7" align="center"><strong>System Monitor List </strong></td>
    </tr>
    <tr bgcolor="#A5B7C5"> 
      <td width="1%" align="center">&nbsp;</td>
      <td width="14%"><div align="center">ServiceName</div></td>
      <td width="14%"> <div align="center">ActionName</div></td>
      <td width="13%"> <div align="center">Status</div></td>
      <td width="14%"> <div align="center">IP</div></td>
      <td width="17%"><div align="center">ExpendTime(ms)</div></td>
      <td width="27%"> <div align="center">AccessTime</div></td>
    </tr>
  
  	<c:forEach var="systemmonitor" items="${result}">
  		<tr>
  			<td>
  				<input type='checkbox' name='key' value="${systemmonitor.seqno}" onclick="selCheckBox('key','check');">
  			</td>
  			<td>
  				<c:out value="${systemmonitor.servicename}" default=""/>&nbsp;
  			</td>
			<td>
  				<c:out value="${systemmonitor.actionId}" default=""/>&nbsp;
  			</td>
			<td>
  				<def:out value="${systemmonitor.status}" length="10" defaultValue=""/>&nbsp;
  			</td>  
			<td>
  				<c:out value="${systemmonitor.ip}" default=""/>&nbsp;
  			</td>  		
			<td>
  				<c:out value="${systemmonitor.expendtime}" default=""/>&nbsp;
  			</td>  
			<td>
  				<def:out value="${systemmonitor.accesstime}" length="20" datatype="date" dataformat="yyyy-MM-dd HH:mm:ss" defaultValue=""/>&nbsp;
  			</td>    			  				
		</tr>  						  			
  	</c:forEach>
  	  
  </table>  
  </td>
	</tr>
	
<tr>
<td align="left">
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr> 
      <td width="5%" align="center"></td>
	  <td width="10%" align="center"><font size="-1"><input name="check" type="checkbox" id="check" value="true" onclick="switchAllCheckBox('key','check');"><a href="javascript:;" onclick="selCheckBoxAll('key','check');">[selAll]</a></font></td>      
      <td width="10%" align="center"><font size="-1"><a href="javascript:;" onclick="revselCheckBoxAll('key','check')">[revSel]</a></font></td>  
      <td width="10%" align="center"><font size="-1"><a href="javascript:;"  onclick="deleteSel(document.monitorForm,'key');return false;">[delSel]</a></font></td>
      <td width="10%" align="center"><font size="-1"><a href="javascript:;"  onclick="deleteAll(document.monitorForm);return false;">[delAll]</a></font></td>
    <td width="60%" align="center"><font size="-1"> &nbsp;</font></td>
  </tr>
</table>  
</td>
</tr>

<tr>
<td align="left">
 <table width="500" border="0">
  <tr> 
    <td>&nbsp;</td>
    <td width="700">
    	<div align="center">
    	<c:catch>    	
			<def:showPageSplit currentPage="<%=testpage.getThisPageNumber()%>" maxPage="<%=testpage.getLastPageNumber()%>" totalSize="<%=testpage.getTotalNumberOfElements()%>" url="<%=request.getContextPath()+"/systemmonitor.do"%>" parameters="${URLParam}" currentPageName="currentpage" />
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


