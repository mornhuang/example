<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<HTML>
<HEAD>
<TITLE></TITLE>	
</HEAD>
<link href="../jsp/css/link.css" rel="stylesheet" type="text/css">
<body bgcolor="E8EDF1"> 
<center>
<table border=0 width=720 cellpadding=5 cellspacing=0>
<tr bgcolor=#FFCC66>
	<Td width="710" align=center><font size=2><B></td>
</tr>
<tr bgcolor=#EBEBEB>
	<Td align=center>
		<table border=1 cellpadding=4 cellspacing=0 width=585>
        <tr> 
          <Td bgcolor=#EBEBEB width=20%>Status</td>
          <Td bgcolor=#EBEBEB width=80%><font size=2>Status Description</td>
        </tr>
        <tr> 
          <Td bgcolor=#EBEBEB width=20%>Received</td>
          <Td bgcolor=#EBEBEB width=80%>System received the application request from client and waiting for credit checking on the date of application deadline.<font size=2> 
          </td>
        </tr>
        <tr> 
          <Td bgcolor=#EBEBEB width=20%>MIS cancelled </td>
          <Td bgcolor=#EBEBEB width=80%>MIS cancelled</td>
        </tr>
        <tr> 
          <Td bgcolor=#EBEBEB width=20%>Application in progress<font size=2> </td>
          <Td bgcolor=#EBEBEB width=80%>Application has passed the credit checking 
            and validation. This IPO application will be delivered to issuer.</td>
        </tr>
        <tr> 
          <Td bgcolor=#EBEBEB width=20%>Allotment Result is Available<font size=2> 
          </td>
          <Td bgcolor=#EBEBEB width=80%>Allotment result is ready now. Client 
            must check the result by viewing their stock position. AE should check 
            the result at MIS. </td>
        </tr>
        <tr> 
          <Td bgcolor=#EBEBEB width=20%>Rejected</td>
          <Td bgcolor=#EBEBEB width=80%> Application has been rejected due to 
            various reason. For detail, please click on the hyperlink of "reject 
            reason".<font size=2> </td>
        </tr>
      </table>
		<Br>
	</td>
</tr>
<tr>
	<Td align=center></td>
</tr>
</table>
<Br><Br>
<center></a></center>
</BODY>
</HTML>
