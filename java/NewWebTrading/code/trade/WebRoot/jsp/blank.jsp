<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
<head>
<html:base/>
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script type="text/javascript">
		$(function(){
			$("#btnCloseDialog").click(function() {
				parent.$("#dialog").dialog("close");
		    });
		});
    </script>
</head>
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
            <font class="odesc1"><BR><bean:message key="${error}"/><BR><BR>
            	<input type="button" id="btnCloseDialog" value="<bean:message key='button.common.return'/>" />
         	<BR><BR></font>
            </td>
        </tr>			
    </table>
</body>
</html>
