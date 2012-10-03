<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<HTML>
<HEAD>
<TITLE></TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=big5">
	<link href="../css/link.css" rel="stylesheet" type="text/css">
	<link href="../css/global.css" rel="stylesheet" type="text/css"> 
</HEAD>
<BODY BGCOLOR="#FFFFFF">
<center>
<table border=0 width=720 cellpadding=5 cellspacing=0>
<tr bgcolor=#FFCC66>
	<Td width="710" align=center><font size=2><B></td>
</tr>
<tr bgcolor=#EBEBEB>
	<Td align=center>
		<table border=1 cellpadding=4 cellspacing=0 width=585>
        <tr bgcolor="#A5B7C5"> 
          <Td  width=20%><font size=2>RejectCode
          </td>
          <Td  width=80%><font size=2>Reject Reason
          </td>
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>90001<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Loan facilities is only available for margin account. Cash account cannot apply loan facilities for IPO application. <font size=2>
		  </td> 
        </tr>  
		<tr>
		  <Td bgcolor=#EBEBEB width=20%>90002<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Account is not found<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>90003<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Invalid lot Size<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>90004<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Loan amount cannot be smaller than the minimum requirement [Min. Amount]<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>90005<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Loan Application is not available<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>90006<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Tables of quantity multiples is not found<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>90007<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>No IPO is open for application<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>90008<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Account is not authorized for IPO application<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>90009<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Account is not authorized to apply for GEM IPO<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>90010<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Account is closed<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>90011<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Loan amount cannot be smaller than the minimum requirement [Min. Amount]<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>80001<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Quantity have to greater than zero<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>80002<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>The applied quantity cannot exceed the limit<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>80003<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Insufficient initial deposit amount<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>80004<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Application money cannot exceed [Transaction Amount]<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>80005<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Apply quantity cannot be less than zero<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>80006<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Apply quantity cannot be larger than issued quantity<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>80008<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Insuffiicient available balance<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>70001<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Fail to read the application information<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>70002<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>System Error. Failed to get issue quantity<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>70003<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Invalid apply quantity<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>70004<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>System Error. Failed to get IPO master<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>70005<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>IPO application has been stopped<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>70006<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>Duplicated IPO Application rejected<font size=2>
		  </td> 
        </tr>
        <tr>
		  <Td bgcolor=#EBEBEB width=20%>70009<font size=2>
		  </td> 
		  <Td bgcolor=#EBEBEB width=80%>System Error<font size=2>
		  </td> 
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
