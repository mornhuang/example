<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<HTML>
<HEAD>
<TITLE></TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=big5">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="0">
<%
response.setHeader("Pragma","No-Cache");
response.setHeader("Cache-Control","No-Cache");
response.setDateHeader("Expires", 0);
%>
</HEAD>
	<link href="../css/link.css" rel="stylesheet" type="text/css">
	<link href="../css/global.css" rel="stylesheet" type="text/css"> 
<BODY BGCOLOR="#FFFFFF">
<center>
<table border=0 width=500 cellpadding=5 cellspacing=0>
<tr bgcolor=#FFCC66>
	<Td align=center><font size=2><B></td>
</tr>
<tr bgcolor=#EBEBEB>
	<Td align=center>
		<table border=1 cellpadding=4 cellspacing=0 width=450>
        <tr bgcolor="#A5B7C5"> 
          <Td  width=20%><font size=2>Qty
          </td>
          <Td  width=20%><font size=2>Amt
          </td>
        </tr>
		
		                    <logic:present name="listIpoQtyAmt">
							<logic:iterate id="ipoQtyAmt" name="listIpoQtyAmt">
							<tr>
							<Td bgcolor=#EBEBEB width=20%><font size=2>
                            <bean:write name="ipoQtyAmt" property="id.shareQty_dsply"/>
							</td> 
							<Td bgcolor=#EBEBEB width=20%><font size=2>
							$<bean:write name="ipoQtyAmt" property="amount_dsply"/>
							</td> 
                 			</tr> 
                            </logic:iterate> 
							</logic:present>
          
          
          
          
        
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
