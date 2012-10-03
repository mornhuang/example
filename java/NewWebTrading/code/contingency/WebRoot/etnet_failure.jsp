<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.itsz.util.CommonUtil" %>
<%@ page import="com.itsz.common.Constants" %>
<%
String result = (String)request.getAttribute(Constants.IQ_RESULT);
String key = "etnet_errorcode_9001";
if(result!=null && !"".equals(result.trim())){
	if(result.trim().equals("2") || result.trim().equals("3") || result.trim().equals("9107")){
		key = "etnet_errorcode_" + result;
	}
}
%>
<html>
<head>
<html:base/>
<TITLE><bean:message key="label.company"/></TITLE>
</head>

<script language="Javascript">
	<!--
	function CloseWindows(){
		top.close();
	}
	-->
</script>

<body bgcolor=white>
<center>
<br>
	<table align=center border=0>
			<tr>
				<td><br></td>
			</tr>		
	</table>
    <center>
    <table border="0" cellpadding="5" cellspacing="0" width=400>
        <tr align=center>
            <td colspan=3>
            <font class="odesc1"><BR><p align=left><bean:message key="<%=key%>"/></p><BR><BR><input type=button value="<bean:message key="label.colse"/>" onclick="CloseWindows()"><BR><BR></font>
            </td>
        </tr>			
    </table>

</body>
</html>
