
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def" %>
<%@ page import="java.util.Iterator,java.util.List" %>
<%@ page import="com.taifook.adminportal.common.util.page.Page,
                 com.taifook.adminportal.common.Constants,com.taifook.adminportal.model.CsUseractionlog"%>
<%@ include file="js/checkurl.jsp"%>
<%@ include file="js/openhelper.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><head>
<html:base /> 
<title>useractiondetail.jsp</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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

<link href="./css/link.css" rel="stylesheet" type="text/css">
<link href="./css/global.css" rel="stylesheet" type="text/css">
<link href="./css/otherClass.css" rel="stylesheet" type="text/css"> 
<html:html lang="true">
<script language="JavaScript" src="js/commutil.js"></script>
<script language="JavaScript" src="js/Common.js"></script>
<script language="JavaScript" src="js/cs_request.js"></script>
<script language="JavaScript" src="js/process_useraction.js"></script>
<script language="JavaScript" src="js/ajax_engine.js"></script>
<script language="javaScript">
	function process_userActionFormSubmit(action){
		document.queryUserActionForm.action=action;
		document.queryUserActionForm.submit();
	}
</script>
	
</head>
  
  <body onload="javascript:setFocus('userid');">
<table width="816" align="left">
  <tr>
    <td width="828" align="left"> <form  method="post" name="queryUserActionForm" action="">
        <table width="747" border="1" align="left" cellspacing="0" cellpadding="0">
          <tr bgcolor="#A5B7C5"> 
            <td width="140">UserID: 
              <input name="userid" type="text" id="userid" size="12" value="<c:out value="${filter.userid}" default=""/>"> </td>
            <td width="139">ActionID: 
              <select name="actionid" id="actionid" class=style_search>
                <c:choose> <c:when test="${filter.actionid==''}"> 
                <option value="" selected></option>
                </c:when> <c:otherwise> 
                <option value=""></option>
                </c:otherwise> </c:choose> <%--c:choose> <c:when test="${filter.actionid=='ChangePassword'}"> 
                <option value="<%=Constants.CHANGE_PASSWORD_ACTION%>" selected><%=Constants.CHANGE_PASSWORD_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.CHANGE_PASSWORD_ACTION%>"><%=Constants.CHANGE_PASSWORD_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionid=='DelayQuotation'}"> 
                <option value="<%=Constants.DELAY_QUOTATION_ACTION%>" selected><%=Constants.DELAY_QUOTATION_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.DELAY_QUOTATION_ACTION%>"><%=Constants.DELAY_QUOTATION_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionid=='InputOrder'}"> 
                <option value="<%=Constants.INPUT_ORDER_ACTION %>" selected><%=Constants.INPUT_ORDER_ACTION %></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.INPUT_ORDER_ACTION %>"><%=Constants.INPUT_ORDER_ACTION %></option>
                </c:otherwise> </c:choose--%> <c:choose> <c:when test="${filter.actionid=='PW_Login'}"> 
                <option value="<%=Constants.PW_LOGIN_ACTION%>" selected><%=Constants.PW_LOGIN_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.PW_LOGIN_ACTION%>"><%=Constants.PW_LOGIN_ACTION%></option>
                </c:otherwise> </c:choose><c:choose> <c:when test="${filter.actionid=='EC_Login'}"> 
                <option value="<%=Constants.EC_LOGIN_ACTION%>" selected><%=Constants.EC_LOGIN_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.EC_LOGIN_ACTION%>"><%=Constants.EC_LOGIN_ACTION%></option>
                </c:otherwise> </c:choose><c:choose> <c:when test="${filter.actionid=='EC_Reg'}"> 
                <option value="<%=Constants.EC_REG_ACTION%>" selected><%=Constants.EC_REG_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.EC_REG_ACTION%>"><%=Constants.EC_REG_ACTION%></option>
                </c:otherwise> </c:choose> <%--c:choose> <c:when test="${filter.actionid=='Logout'}"> 
                <option value="<%=Constants.LOGOUT_ACTION%>" selected><%=Constants.LOGOUT_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.LOGOUT_ACTION%>"><%=Constants.LOGOUT_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionid=='QueryOrder'}"> 
                <option value="<%=Constants.QUERY_ORDER_ACTION%>" selected><%=Constants.QUERY_ORDER_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.QUERY_ORDER_ACTION%>"><%=Constants.QUERY_ORDER_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionid=='QueryPrice'}"> 
                <option value="<%=Constants.QUERY_PRICE_ACTION%>" selected><%=Constants.QUERY_PRICE_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.QUERY_PRICE_ACTION%>"><%=Constants.QUERY_PRICE_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionid=='RealQuotation'}"> 
                <option value="<%=Constants.REALTIME_QUOTATION_ACTION%>"  selected><%=Constants.REALTIME_QUOTATION_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.REALTIME_QUOTATION_ACTION%>"><%=Constants.REALTIME_QUOTATION_ACTION%></option>
                </c:otherwise> </c:choose> <c:choose> <c:when test="${filter.actionid=='Test'}"> 
                <option value="<%=Constants.TEST_ACTION%>" selected><%=Constants.TEST_ACTION%></option>
                </c:when> <c:otherwise> 
                <option value="<%=Constants.TEST_ACTION%>"><%=Constants.TEST_ACTION%></option>
                </c:otherwise> </c:choose--%> </select> </td>
            <td width="128">channelType:
                <input name="channelType" type="text" id="channelType" size="12" value="<c:out value="${filter.channelType}" default=""/>">
            </td>
            <td width="128">IP: 
                <input name="ip" type="text" id="ip" size="12" value="<c:out value="${filter.ip}" default=""/>"></td>
            <td width="145">AccessTime: 
              <input name="operationtime" type="text" id="operationtime" size="12" value="<c:out value="${filter.operationtime}" default=""/>"></td>
            <td width="100"><input type="button" name="Submit2" value="to Excel" onclick="javascript:process_userActionFormSubmit('../exportExcelUserActionDetail.do');"></td>
            <td><input type="button" name="Submit" value="Search" onclick="javascript:process_userActionFormSubmit('../useractiondetail.do');"></td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>

<p>&nbsp;</p>
<p>&nbsp;</p>
<form name="userActionForm" method="POST" action="../deleteuseractiondetail.do" onsubmit=""> 
<table width="1000" border="0"  align="left" cellspacing="0"  style="margin-left:-10px">
<tr>
<td width="1000"  align="left"> 
  <table width="1000" border="1" align="center" cellpadding="0" cellspacing="0">
          <tr bgcolor="#A5B7C5"> 
            <td colspan="6"> <div align="center"><STRONG> More User Action List 
                </STRONG><a href="javascript:;" onclick="javascript:OpenHelperBrowser('zhugan/UserAction/UserAction.htm');return false;"><img src="images/helper.jpg" width="43" height="18" border="0" align="right"></a></div></td>
          </tr>
          <tr bgcolor="#A5B7C5"> 
            <td width="4%" align="center">&nbsp;</td>
            <td width="20%" align="center">UserID</td>
            <td width="15%" align="center">ActionID</td>
            <td width="20%" align="center">ChannelType</td>
            <td width="15%" align="center">IP</td>
            <td width="30%" align="center">AccessTime</td>
          </tr>
          <%  
  Page testpage=null;
  List result=null;
  try{
    testpage=(Page)session.getAttribute(Constants.RESULT);
    result=(List)testpage.getThisPageElements();
    pageContext.setAttribute("result",result); 
   }catch(Exception e){}
  %>
          <c:forEach var="useraction" items="${result}"> 
          <tr> 
            <td> <input type='checkbox' name='seqno' value="${useraction.seqno}"  onclick="selCheckBox('seqno','check');"> 
            </td>
            <td><c:out value="${useraction.userid}" default=""/>&nbsp; </td>
            <td><c:out value="${useraction.actionid}" default=""/>&nbsp; </td>
            <td><c:out value="${useraction.channelType}" default=""/>&nbsp; </td>
            <td><c:out value="${useraction.ip}" default=""/>&nbsp; </td>
            <td><def:out value="${useraction.operationtime}" length="20" datatype="date" dataformat="yyyy-MM-dd HH:mm:ss" defaultValue=""/>&nbsp; 
            </td>
          </tr>
          </c:forEach> </table>
</td>
</tr>

<tr> 
<td align="left">
	<table border="0" cellspacing="0" align="left">
	  <tr>
	  <td width="5%"></td>
      <td width="10%" align="center"><font size="-1"><input name="check" type="checkbox" id="check" value="true" readonly='true' onclick="switchAllCheckBox('seqno','check');"><a href="javascript:;" onclick="selCheckBoxAll('seqno','check');">[selAll]</a></font></td>      
      <td width="10%" align="center"><font size="-1"><a href="javascript:;" onclick="revselCheckBoxAll('seqno','check')">[revSel]</a></font></td>  
      <td width="10%" align="center"><font size="-1"><a href="javascript:;"  onclick="deleteSel(document.userActionForm,'seqno');return false;">[delSel]</a></font></td>
      <td width="10%" align="center"><font size="-1"><a href="javascript:;"  onclick="deleteAll(document.userActionForm);return false;">[delAll]</a></font></td>
      <td width="50%" align="center"><font size="-1"> &nbsp;</font></td>
	  </tr>
	</table>	  	  
</td>
</tr>

<tr>
<td  align="left">
 <table width="600" border="0">
  <tr> 
    <td>&nbsp;</td>
    <td width="700">
    	<div align="center">
	<c:catch>    	
	    <def:showPageSplit currentPage="<%=testpage.getThisPageNumber()%>" maxPage="<%=testpage.getLastPageNumber()%>" totalSize="<%=testpage.getTotalNumberOfElements()%>" url="<%=request.getContextPath()+"/useractiondetail.do"%>" parameters="${URLParam}" currentPageName="currentpage" />
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
