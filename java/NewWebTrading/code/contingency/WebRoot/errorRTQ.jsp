
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.itsz.common.Constants" %>
<%@ page import="com.itsz.util.CommonUtil" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String loginId = (String)session.getAttribute(Constants.LOGIN_ID);
String Language = (String)session.getAttribute(Constants.LANGUAGE);
if (loginId==null || loginId.trim().equals("")) {
	if (Language==null || Language.trim().equals("")) {
		//response.sendRedirect("index.jsp");
		out.println("<script>top.location='index.jsp'</script>");
	}
	else{
		//response.sendRedirect("login.jsp?Language="+Language);
		out.println("<script>top.location='login.jsp?Language='"+Language+"'</script>");
	}
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <TITLE><bean:message key="label.company"/></TITLE>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  </head>
  
  <body>
    <H3><FONT color="red"><bean:message key="error.notfoundRTQ.msg"/></FONT></H3> <br>
  </body>
</html>
