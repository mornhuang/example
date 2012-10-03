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
                 com.taifook.adminportal.common.util.DataFormatUtil,com.taifook.adminportal.model.CsSetParameter" %>             
<%@ include file="js/checkurl.jsp"%>   
<%@ include file="js/openhelper.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>Query Mobile Agent</title>
    
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
  
<body onload="javascript:setFocus('paramValue');"> 
<table width="80%" align="left">
<tr><td align="left">
  <form method="post" action="../queryMobileAgent.do">
        <table width="89%" border="1" align="left" cellspacing="0" cellpadding="0">
        <tr bgcolor="#A5B7C5"> 
          <td width="228">AgentType: 
              <select name="paramName" id="paramName">
                <c:if test="${filter.paramName=='AgentBlackList'}">
	              	<option value="AgentBlackList" selected>AgentBlackList</option>
	              	<option value="AgentPcBlackList">AgentPcBlackList</option>
	              	<option value="AgentWhiteList">AgentWhiteList</option>
                </c:if>
                <c:if test="${filter.paramName=='AgentWhiteList'}">
                    <option value="AgentWhiteList" selected>AgentWhiteList</option>
	              	<option value="AgentBlackList">AgentBlackList</option>
	              	<option value="AgentPcBlackList">AgentPcBlackList</option>	              	
                </c:if>
                <c:if test="${filter.paramName=='AgentPcBlackList'}">
	              	<option value="AgentPcBlackList" selected>AgentPcBlackList</option>
	              	<option value="AgentBlackList">AgentBlackList</option>
	              	<option value="AgentWhiteList">AgentWhiteList</option>
                </c:if>
              </select>
          </td>
          <td width="228">AgentValue: 
            <input name="paramValue" type="text" id="paramValue" size="15" value="<c:out value="${filter.paramValue}" default=""/>"></td>
          <td width="224">UpdateTime: 
            <input name="updateTime" type="text" id="updaTetime" size="15" value="<c:out value="${filter.updateTime}" default=""/>"></td>
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
          <td colspan="5"><div align="center"><strong>View Mobile Agent --><c:out value="${filter.paramName}" default=""/></strong><a href="javascript:;" onclick="javascript:OpenHelperBrowser('zhugan/Parameters/Parameters.htm');return false;"><img src="images/helper.jpg" width="43" height="18" border="0" align="right"></a></div></td>
        </tr>
        <tr bgcolor="#A5B7C5"> 
          <td>AgentValue</td>
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
          <td> <a href="../editMobileAgent.do?paramName=<def:out value='${parameter.id.paramName}'/>&paramValue=<def:out value='${parameter.id.paramValue}' needEncode='true'/>"><c:out value='${parameter.id.paramValue}' default=''/></a>&nbsp; 
          </td>
          <td> <def:out value="${parameter.description}" length="20" defaultValue=""/>&nbsp; 
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
            <td width="650"> <div align="center"> <c:catch> <def:showPageSplit currentPage="<%=testpage.getThisPageNumber()%>" maxPage="<%=testpage.getLastPageNumber()%>" totalSize="<%=testpage.getTotalNumberOfElements()%>" url="<%=request.getContextPath()+"/queryMobileAgent.do"%>" parameters="${URLParam}" currentPageName="currentpage" /> 
                </c:catch> </div></td>
            <td>&nbsp;</td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
  </body>
</html:html>