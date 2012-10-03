<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def" %>
<%@ page import="java.util.Iterator,java.util.List" %>
<%@ page import="com.taifook.adminportal.common.util.page.Page,
                 com.taifook.adminportal.common.Constants,
                 com.taifook.adminportal.common.util.DataFormatUtil,com.taifook.adminportal.model.CsParameter" %>             
<%@ include file="js/checkurl.jsp"%>   
<%@ include file="js/openhelper.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>queryparameter.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script language="JavaScript" src="jsp/js/commutil.js"></script>     
    
	<link href="./css/link.css" rel="stylesheet" type="text/css">
	<link href="./css/global.css" rel="stylesheet" type="text/css">   
	<script language="JavaScript" src="js/checker.js"></script>
	<script language="JavaScript" src="js/Common.js"></script>
	<script language="JavaScript" src="js/commutil.js"></script> 	
	
  </head>
  
  <body onload="javascript:setFocus('key');"> 
  	<c:choose>
	  <c:when test="${filter.classid==0}">
	  	<c:set var="paraTile" value="View Channel Parameter"/>
	  </c:when>   
	  <c:when test="${filter.classid==1}">
	  	<c:set var="paraTile" value="View 3G Parameter"/>
	  </c:when>
	  <c:when test="${filter.classid==2}">
	  	<c:set var="paraTile" value="View Time Parameter"/>
	  </c:when>
	  <c:when test="${filter.classid==3}">
	  	<c:set var="paraTile" value="View Warrant Parameter"/>
	  </c:when>
	  <c:when test="${filter.classid==4}">
	  	<c:set var="paraTile" value="View Ps Parameter"/>
	  </c:when>
	  <c:when test="${filter.classid==5}">
	  	<c:set var="paraTile" value="View Web Parameter"/>
	  </c:when>
	  <c:when test="${filter.classid==6}">
	  	<c:set var="paraTile" value="View Else Parameter"/>
	  </c:when>
	  <c:when test="${filter.classid==7}">
	  	<c:set var="paraTile" value="View Channel Code"/>
	  </c:when>
	  <c:when test="${filter.classid==8}">
	  	<c:set var="paraTile" value="View Wmt Parameter"/>
	  </c:when>
	  <c:otherwise>
	  	<c:set var="paraTile" value="View Query Parameter"/>
	  </c:otherwise>  	  	  	  	  	  	  
	</c:choose>
<table width="80%" align="left">
<tr><td align="left">
  <form method="post" action="../queryparameter.do?classid=<c:out value="${filter.classid}" default=""/>">
        <table width="89%" border="1" align="left" cellspacing="0" cellpadding="0">
        <tr bgcolor="#A5B7C5"> 
          <td width="194">Key: 
            <input name="key" type="text" id="key" size="15" value="<c:out value="${filter.key}" default=""/>"></td>
          <td width="190">Value: 
            <input name="value" type="text" id="value" size="15" value="<c:out value="${filter.value}" default=""/>"></td>
          <td width="224">UpdateTime: 
            <input name="updatetime" type="text" id="updatetime" size="15" value="<c:out value="${filter.updatetime}" default=""/>"></td>
          <td><input type="submit" name="Submit" value="Search"></td>
        </tr>
      </table>
</form>
</td>
</tr>
</table>

<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="80%" align="left">
  <tr> 
    <td align="left"> <table width="80%" border="1" cellpadding="0" cellspacing="0">
        <tr bgcolor="#A5B7C5"> 
          <td colspan="5"><div align="center"><strong>${paraTile}</strong></div></td>
        </tr>
        <tr bgcolor="#A5B7C5"> 
          <td>Key</td>
          <td>Value</td>
<!--          <td>ClassID</td>-->
          <td>Description</td>
          <td>UpdateTime</td>
        </tr>
        <%  
        Page testpage=null;
  try{
    	testpage=(Page)session.getAttribute(Constants.RESULT);
    	testpage.getPageSize();
    	testpage.getTotalNumberOfElements();    	
    
   		List result=(List)testpage.getThisPageElements();
		pageContext.setAttribute("result",result);		
       }catch(java.lang.Exception es){}
  %>
        <c:forEach var="parameter" items="${result}"> 
        <tr> 
          <td > <a href=../editparameter.do?key=<def:out value="${parameter.key}" datatype="url" dataformat="UTF-8" defaultValue="" />><c:out value='${parameter.key}' default=""/></a>&nbsp; 
          </td>
          <td> 
          	<def:out value="${parameter.value}" length="15"  defaultValue=""/>&nbsp; 
          </td>
<!--          <td> <c:out value="${parameter.classid}" default=""/>&nbsp; </td>-->
          <td > <def:out value="${parameter.description}" length="8" defaultValue=""/>&nbsp; 
          </td>
          <td> <def:out value="${parameter.updateTime}" length="20" datatype="date" dataformat="yyyy-MM-dd HH:mm:ss" defaultValue=""/>&nbsp; 
          </td>
        </tr>
        </c:forEach> </table></td>
  </tr>
  <tr> 
    <td align="left"> <div align="center"> 
        <table width="650" border="0">
          <tr> 
            <td>&nbsp;</td>
            <td width="650"> <div align="center"> <c:catch> <def:showPageSplit currentPage="<%=testpage.getThisPageNumber()%>" maxPage="<%=testpage.getLastPageNumber()%>" totalSize="<%=testpage.getTotalNumberOfElements()%>" url="<%=request.getContextPath()+"/queryparameter.do"%>" parameters="${URLParam}" currentPageName="currentpage" /> 
                </c:catch> </div></td>
            <td>&nbsp;</td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
  </body>
</html:html>
