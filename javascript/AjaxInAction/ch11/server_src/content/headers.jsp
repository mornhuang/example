<%@ page import="java.util.*" %>

<b>HTTP Headers</b>
<hr>
<table align="left" width="100%" border="0" cellpadding="0" cellspacing="4">
<%
Enumeration eHttpHdrs = request.getHeaderNames();

while (eHttpHdrs.hasMoreElements()) {
    String sName     = (String)eHttpHdrs.nextElement();
    String sValue    = request.getHeader(sName);
%>
<tr valign='top'>
<td width='320'><%=sName%></td>
<td><%=sValue%></td>
</tr>
<%
}
%>
</table>
<iframe  width="0" height="0"></iframe>
