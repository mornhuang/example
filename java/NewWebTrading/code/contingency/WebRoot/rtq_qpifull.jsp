<%@ page import="java.util.Properties"%>
<%@ page import="java.io.*"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<SCRIPT LANGUAGE="JavaScript" SRC="./ctrl/DisableRightClick.js"></SCRIPT>

<html>
<head>
<title></title>
</head>
<body bgcolor="#ffffff">
  <form name="form1" id="form1" method="post" action=<bean:write name="rtq" property="quoteurl" /> >
	<input type="hidden" name="uid" value=<bean:write name="rtq" property="uid" />>
	<input type="hidden" name="pwd" value=<bean:write name="rtq" property="pwd" />>
	<input type="hidden" name="broker" value="taifook">
	<input type="hidden" name="lang" value=<bean:write name="rtq" property="lang" />>
  </form>
  <script language="javascript">
	 document.forms[0].submit();
  </script>

</body>
</html>