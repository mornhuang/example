<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.taifook.adminportal.web.ipo.form.IPORequestForm"%>
<%@ page import="com.taifook.adminportal.web.ipo.delegate.IPOMaintenanceDelegate"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="../js/checkurl.jsp"%>
<form name="form1" >
<% 
   String applyType = "apply";
   long ipoMasterId=0;
   String accountId="",andIPOCode="",andAccount="";
   IPORequestForm form=(IPORequestForm)request.getAttribute("IPORequestForm");
   if(form!=null)
	 {
		 applyType=form.getApplyType();
		 ipoMasterId=form.getIpoMasterId();
         accountId=form.getAccountId();
		 andIPOCode = form.getAndIPOCode();
         andAccount = form.getAndAccount();

      }
  %>
  <input type="hidden" name="applyType" value="<%=applyType%>">
  <input type="hidden" name="ipoMasterId" value="<%=ipoMasterId%>">
  <input type="hidden" name="accountId" value="<%=accountId%>">
  <input type="hidden" name="andIPOCode" value="<%=andIPOCode%>">
  <input type="hidden" name="andAccount" value="<%=andAccount%>">
  	  <script language="JavaScript">
      <!--
			alert("Update apply result success!");  
			form1.action="IPOListApplyAction.do"; 
			form1.submit();
			//window.location="IPOListApplyAction.do";
     //-->
     </script>
	 </form>