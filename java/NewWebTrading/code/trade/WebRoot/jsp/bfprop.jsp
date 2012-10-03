<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<% 
	   String urlName = (String)request.getParameter("urlName");
	   String url = "http://"+urlName;
	%>
<script language="JavaScript">
<!--
	alert("<bean:message key="message.ipo.prospectus.clickhbexprop"/>");
	window.location='<%=url%>';
   //-->
 </script>
