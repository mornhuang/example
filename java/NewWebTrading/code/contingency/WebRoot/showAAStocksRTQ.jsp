<%@ page import="java.util.Properties"%>
<%@ page import="java.io.*"%>
<%@ page import="com.itsz.parameter.util.ParameterManager" %>
<%@ page import="com.itsz.web.rtq.util.LangConvert" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<SCRIPT LANGUAGE="JavaScript" SRC="./ctrl/DisableRightClick.js"></SCRIPT>

<%
	String aastock_switch = ParameterManager.getValue("aastock_switch");
	boolean postLang = "0".equals(aastock_switch)?false:true;
	String lang = LangConvert.aastockConvert(session.getAttribute("language").toString());
%>
<html>
<head>
<title></title>
</head>
<body bgcolor="#ffffff">
  <form name="form1" id="form1" method="post" action=<bean:write name="rtq" property="quoteurl" /> >
	<input type="hidden" name="uname" value=<bean:write name="rtq" property="uid" />>
	<input type="hidden" name="password" value=<bean:write name="rtq" property="pwd" />>
	<% if (postLang) { %>
	<input type="hidden" name="Lang" value="<%=lang%>"/>
	<%} %>
	<input type="hidden" name="broker" value="taifook">
	<input type="hidden" name="ver" value="1">
  </form>
  <script language="javascript">
	 document.forms[0].submit();
  </script>

</body>
</html>