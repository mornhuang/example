<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
<head>
<html:base/>
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="../Script/shieldingMouse.js" type="text/javascript"></script>
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
            <%if(request.getAttribute("productId")!=null && !request.getAttribute("productId").equals("")) {%>
            	<bean:define id="arg0">
				  <bean:message key="ProductMSG_${productId}"/>
				</bean:define>
            <%} %>
            <font class="odesc1"><BR><bean:message key="${error}" arg0="${arg0}"/><BR><BR><input type=button class="graw-short-btn" value="<bean:message  key="button.common.ok"/>"  onclick="javascript:window.close();"><BR><BR></font>
            </td>
        </tr>			
    </table>

</body>
</html>
