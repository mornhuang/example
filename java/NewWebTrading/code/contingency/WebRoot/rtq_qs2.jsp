<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<SCRIPT LANGUAGE="JavaScript" SRC="./ctrl/DisableRightClick.js"></SCRIPT>

<html>
<head>
<title></title>
</head>
<body bgcolor="#ffffff">
   <form name="form1" id="form1" method="post" action=<bean:write name="qpjunior" property="quoteurl" /> >
              <input type="hidden" name="uid" size="20" value=<bean:write name="qpjunior" property="uid" />>
              <input type="hidden" name="token" size="20" value=<bean:write name="qpjunior" property="token" />>
              <input type="hidden" name="lang" size="20" value=<bean:write name="qpjunior" property="lang" />>
              <input type="hidden" name="eKey" size="20" value=<bean:write name="qpjunior" property="eKey" />>
              <input type="hidden" name="url" size="20" value=<bean:write name="qpjunior" property="url" />>      
                             
  </form>
  <script language="javascript">
 	 document.forms[0].submit();
  </script>

</body>
</html>