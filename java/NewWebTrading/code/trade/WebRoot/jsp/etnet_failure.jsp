<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.itsz.sht.common.Constants"%><html>
<%
String result = (String)request.getAttribute(Constants.IQ_RESULT);
String key = "etnet_errorcode_9001";
if(result!=null && !"".equals(result.trim())){
	result = result.trim(); 
	if(result.equals("2") || result.equals("3") || result.equals("9107")){
		key = "etnet_errorcode_" + result;
	}
}
%>

<head>
<html:base/>
<title><bean:message key="company.name"/></title>	
</head>
<script language="Javascript">

function CloseWindows(){top.close();}
</script>

<body bgcolor=white background="images/QuotationBG.gif">
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
            <font class="odesc1"><BR><bean:message key="<%=key%>"/><BR><BR><input type=button value="<bean:message  key="button.common.ok"/>" class="go"  onclick="javascript:CloseWindows()"><BR><BR></font>
            </td>
        </tr>
    </table>

</body>
</html>
