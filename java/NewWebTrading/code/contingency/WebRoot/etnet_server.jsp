<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<SCRIPT LANGUAGE="JavaScript" SRC="./ctrl/DisableRightClick.js"></SCRIPT>

<%
	Object obj = request.getAttribute("etnet");
	System.out.println(obj);
%>
<html>
<head>
<title></title>
</head>
<body bgcolor="#ffffff">
  <form name="form1" id="form1" method="post" action=<bean:write name="etnet" property="quoteurl" /> >
              <input type="hidden" name="uid" size="20" value=<bean:write name="etnet" property="uid" />>
              <input type="hidden" name="lang" size="20" value=<bean:write name="etnet" property="lang" />>
              <input type="hidden" name="passport" size="20" value=<bean:write name="etnet" property="passport" />>                          
  </form>
  <script language="javascript">
 	 document.forms[0].submit();
  </script>

</body>
</html>