<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<html:base/>
    <title><bean:message key="company.name"/></title>	
</head>
<script type="text/javascript">
function load(id){
	if(id=='SSTR_AAST' || id=='SSTR_AAST_CN'){
		document.frm.submit();
	} else {
		parent.popupRtqApp('<bean:message key="label.menu.rtqServiceApply"/>','${pageContext.request.contextPath}/webAccessRTQ.do?productId=${result.id.prodId}&CLV=${sessionScope.CLV}');
	}
}

</script>
<body  bgcolor=white background="images/QuotationBG.gif" onload="load('${result.id.prodId}');">
<form name="frm" action="${pageContext.request.contextPath}/webAccessRTQ.do?productId=${result.id.prodId}&CLV=${sessionScope.CLV}" method="post">
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
            <font class="odesc1"><BR><bean:message key="message.rtq.status"/></font>
            </td>
        </tr>			
    </table>
</form>
</body>
</html>