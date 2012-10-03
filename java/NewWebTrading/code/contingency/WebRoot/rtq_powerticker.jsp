<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<SCRIPT LANGUAGE="JavaScript" SRC="./ctrl/DisableRightClick.js"></SCRIPT>


<html>
<head>
<title></title>
</head>
<body bgcolor="#ffffff">


  <form name="form1" id="form1" method="post" action=<bean:write name="rtq" property="quoteurl" /> >
              <input type="hidden" name="uid" size="20" value=<bean:write name="rtq" property="uid" /> >
              <input type="hidden" name="pwd" size="20" value=<bean:write name="rtq" property="pwd" /> >
              <input type="hidden" name="lang" size="20" value=<bean:write name="rtq" property="lang" /> >
              <input type="hidden" name="stype" size="20" value="1">
              <input type="hidden" name="order_lang" size="20" value=<bean:write name="rtq" property="order_lang" /> >
              <input type="hidden" name="id" size="20" value=<bean:write name="rtq" property="id" /> >
              <input type="hidden" name="language" size="20" value=<bean:write name="rtq" property="language" />>          
              <input type="hidden" name="tradePlatform" size="20" value="c">
              <input type="hidden" name="group" size="20" value="tf">
              <input type="hidden" name="connectionType" size="20" value="http80">
              <input type="hidden" name="userPasswd" size="20" value=<bean:write name="rtq" property="userPasswd" /> >
              <input type="hidden" name="userLogin" size="20" value=<bean:write name="rtq" property="userLogin" /> >
              <input type="hidden" name="userLang" size="20" value="big5">
              <input type="hidden" name="version" size="20" value="i">   
                        
  </form>
  <script language="javascript">
 	 document.forms[0].submit();
  </script>

</body>
</html>