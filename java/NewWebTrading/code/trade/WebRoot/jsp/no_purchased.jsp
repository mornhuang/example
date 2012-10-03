<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html>
<head>
<html:base/>
<title><bean:message key="company.name"/></title>	

    <script language="Javascript">
	<!--
	function CloseWindows(){
		top.close();
	}
	function popeService(desURL) {
		var top = (screen.height - 600)/2;
		var left = (screen.width - 800)/2;
		window.close();
		window.open(desURL, "", "");
   	} 
	-->
</script>
</head>
<body bgcolor=white background="../Style/blue/images/QuotationBG.gif">
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
            <font class="odesc1"><BR><bean:message bundle="STT" key="link.streamrtq.es1"/><A href="javascript:popeService('forwardIweb.do?CLV=${sessionScope.CLV}');"><bean:message bundle="STT" key="lable.quotation.delaySeviceInroduce2"/></A><bean:message bundle="STT" key="link.streamrtq.es2"/><BR><BR><input type=button value="<bean:message bundle="STT" key="label.bocTransferForm.close"/>" onclick="CloseWindows()"><BR><BR></font>
            </td>
        </tr>			
    </table>

</body>
</html>
