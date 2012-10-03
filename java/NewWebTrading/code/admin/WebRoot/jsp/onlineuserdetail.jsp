
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def" %>
<%@ page import="java.util.Iterator,java.util.List" %>
<%@ page import="com.taifook.adminportal.common.util.page.Page,
                 com.taifook.adminportal.common.Constants,com.taifook.adminportal.model.CsOnlineuser"%>
<%@ include file="js/checkurl.jsp"%>
<%@ include file="js/openhelper.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>onlineuserdetail.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
	<link href="./css/link.css" rel="stylesheet" type="text/css">
	<link href="./css/global.css" rel="stylesheet" type="text/css">     
	<script language="JavaScript" src="./js/commutil.js"></script> 
  </head>
  
  <body onload="javascript:setFocus('userid');">

<table width="783" border="0" align="left">
  <tr>
    <td width="777" align="left"> <form  method="post" action="../onlineuserdetail.do">
        <table width="741" border="1" align="left" cellspacing="0" cellpadding="0">
          <tr bgcolor="#A5B7C5"> 
            <td width="197">UserID: 
              <input name="userid" type="text" id="userid" size="15" value="<c:out value="${filter.userid}" default=""/>"> </td>
            <td width="228">ChannelType: 
              <input name="channelcode" type="text" id="channelcode" size="15" value="<c:out value="${filter.channelcode}" default=""/>"></td>
            <td width="221">LoginTime: 
              <input name="logintime" type="text" id="logintime" size="15" value="<c:out value="${filter.logintime}" default=""/>"></td>
            <td><input type="submit" name="Submit" value="Search"> </td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>

<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" align="left"  style="margin-left:-15px">
<tr>
<td align="left">
<table width="80%" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr bgcolor="#A5B7C5"> 
    <td colspan="4"><div align="center"><STRONG> Online User List </STRONG><a href="javascript:;" onclick="javascript:OpenHelperBrowser('zhugan/onlineuser/onlineuser.htm');return false;"><img src="images/helper.jpg" width="43" height="18" border="0" align="right"></a></div></td>
  </tr>
  <tr bgcolor="#A5B7C5"> 
    <td align="center">UserID</td>
    <td align="center">SessionID</td>
    <td align="center">channelType</td>
    <td align="center">LoginTime</td>
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
    <c:forEach var="onlineuser" items="${result}">
  		<tr>
  			<td>
  				<c:out value="${onlineuser.id.userid}" default=""/>&nbsp;
  			</td>
			<td>
  				<c:out value="${onlineuser.sessionid}" default=""/>&nbsp;
  			</td>
			<td>
  				<c:out value="${onlineuser.id.channelcode}" default=""/>&nbsp;
  			</td>  
			<td>
  				<def:out value="${onlineuser.logintime}" length="20" datatype="date" dataformat="yyyy-MM-dd HH:mm:ss" defaultValue=""/>&nbsp;
  			</td>  			
		</tr>  						  			
  	</c:forEach>
  	
</table>
</td> 
</tr>

<tr>
<td align="left">
 <table width="600" border="0">
  <tr> 
    <td>&nbsp;</td>
    <td width="700">
    	<div align="center">
    	<c:catch>
		    <def:showPageSplit currentPage="<%=testpage.getThisPageNumber()%>" maxPage="<%=testpage.getLastPageNumber()%>" totalSize="<%=testpage.getTotalNumberOfElements()%>" url="<%=request.getContextPath()+"/onlineuserdetail.do"%>" parameters="${URLParam}" currentPageName="currentpage" />
		</c:catch>		    
	    </div>
	</td>
    <td>&nbsp;</td>
  </tr>
</table>
</td>
</tr>
</table>
  </body>
</html:html>
