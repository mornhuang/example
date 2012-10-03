<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="com.itsz.util.CommonUtil" %>
<%@ page import="com.itsz.common.Constants" %>
<%
String link = request.getParameter("link");
%>
<html>
<head>
<TITLE><bean:message key="label.company"/></TITLE>

</head>
<frameset rows="0,*" frameborder="NO" border="0" framespacing="0">
  <frame src="popup.html" name="topFrame" scrolling="NO" noresize >
  <frame src="<%=link%>" name="mainFrame">
</frameset>
<noframes><body>
You must use a browser that can display frames to see this page. 
</body></noframes>
</html>
