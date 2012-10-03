<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def" %>
<%@ page import="java.util.Iterator,java.util.List"%>
<%@ page import="com.taifook.adminportal.common.util.page.Page"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>
<html>
<head>
<title>userProfileList.jsp</title>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">   
<script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/commutil.js"></script>
<script type="text/javascript">
function searchKeydowm(e){
    dkey = e.keyCode;
    if(dkey=="13"){
    	if(document.rtqAccountAssignmentForm.productId.value==""){
            alert("ProductId must be input!");
            setFocus('productId');
            return false;
        }
        searchA();
    }  
}
function searchA(){
    if(document.rtqAccountAssignmentForm.productId.value==""){
        alert("ProductId must be input!");
        setFocus('productId');
        return false;
    }

}

function showDetail(productId,rtqLoginId,clientId){
	alert("后台方法没有，暂不实现！");
	return false;
	document.rtqAccountAssignmentForm.action="listRtqAccountAssignment.do?method=listRtqAccountAssignment&productId"+productId+"&rtqLoginId="+rtqLoginId+"&clientId="+clientId;
	document.rtqAccountAssignmentForm.submit();
	
}
</script>
</head>
<body>
<table align="left" width="50%" border="0" cellpadding="0" cellspacing="0">
			 <tr>
				<td >
				|&nbsp;<html:link action="listRTQAccountView.do?method=listRTQAccountView"><font color="blue">RTQ Account Usage Enquiry</font></html:link>
				&nbsp;|&nbsp;<html:link action="listRtqAccountAssignment.do?method=listRtqAccountAssignment"><font color="blue">RTQ Account Assignment Enquiry</font></html:link>
				&nbsp;|&nbsp;<html:link action="listRtqAccount.do?method=listRtqAccount"><font color="blue">RTQ Account Maintenance</font></html:link>
				</td>
			 </tr>
	</table>
<p>&nbsp;</p>
<form method="post" name="rtqAccountAssignmentForm">
<table width="50%" align="left">
	<tr bgcolor="#A5B7C5">
		<td>Product ID:<html:text property="prodId"  name="rtqAccountAssignmentForm" size="15" onkeypress="return searchKeydowm(event);"></html:text> 
			</td>
		<td><input type="submit" name="Submit" value="Search"
			onclick="searchA();"></td>
	</tr>
</table>
</form>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="40%" border="0" align="left" cellpadding="0" cellspacing="0" >
	<tr bgcolor="#A5B7C5">
		<td>&nbsp;&nbsp;&nbsp;&nbsp; </td>
		<td>Product ID</td>
		<td>RTQ Login ID</td>
		<td>Clinet ID</td>
	</tr>
	<%
  try{
    	Page testpage=(Page)request.getAttribute("page");
   		List result=(List)testpage.getThisPageElements();
   		pageContext.setAttribute("result",result);
       }catch(java.lang.Exception es){}
    
%>
	<c:forEach var="rtqAccAss" items="${result}" varStatus="status">
		<c:choose>
			<c:when test="${status.index%2==0}">  
				<tr bgcolor="#ffffff">
			</c:when>
			<c:otherwise><tr></c:otherwise>
		</c:choose>
			<td>${status.index+1 }</td>
			<td>${rtqAccAss.id.prodId }</td>
			<td>${rtqAccAss.id.rtqLognId }</td>
			<td>${rtqAccAss.id.clntId }</td>
		</tr>
	</c:forEach>
	<tr> 
    <td colspan="4">
    	<div align="center">
    	<c:catch>
			<%Page testpage=(Page)request.getAttribute("page");%>
		    <def:ymshowPageSplit currentPage="<%=testpage.getThisPageNumber()%>" maxPage="<%=testpage.getLastPageNumber()%>" totalSize="<%=testpage.getTotalNumberOfElements()%>" url="<%=request.getContextPath()+"/listRtqAccountAssignment.do?method=queryRtqAccountAssignment"%>" parameters="${URLParam}" currentPageName="currentpage" />
	    </c:catch>
	    </div>
	</td>
  </tr>
<!--  <tr>-->
<!--		<td align="left" colspan="4"><input type="button" value="GoBack"-->
<!--			onClick="history.go(-1)" /></td>-->
<!--	</tr>-->
</table>

</body>
</html>