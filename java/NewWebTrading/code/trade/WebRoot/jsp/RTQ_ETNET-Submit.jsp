<%@ page contentType="text/html;charset=utf-8" %>
<%@page import="java.io.PrintWriter"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%
	response.addHeader("Progma", "No-cache");
	response.addHeader("Cache-Control", "no-cache");
	response.addDateHeader("Expires", 1);	
	String etnet_enabled = (String)request.getAttribute("etnet_enabled");
	if("1".equals(etnet_enabled)){
		String result = (String)request.getAttribute("result");	
		PrintWriter write = response.getWriter();
	    try {
	    	write.println(result);
	    	write.flush();
	    } catch (Exception e) {
	    } finally {
	        if (write != null)
	        	write.close();
	    }		
	}
%>
<c:choose>
	<c:when test="${etnet_enabled=='0'}">
		<html>
		<head>
			<meta http-equiv=pragma content=no-cache>
		</head>
		<body bgcolor="#ffffff" background="images/QuotationBG.gif">
		<bean:define id="arg0">
		  <bean:message key="Product_SSTR_IQS"/>
		</bean:define>
		<br><FONT face="Arial" color="#663300"><bean:message key="lable.quotation.popup" arg0="<%=arg0%>" /></FONT>
		
		<form name="defaultForm" action="<bean:write name='etnetAppletRTQRequest' property='url'/>" method="post">
		    <html:hidden name="etnetAppletRTQRequest" property="uid"/>
		    <html:hidden name="etnetAppletRTQRequest" property="theme"/>
		    <html:hidden name="etnetAppletRTQRequest" property="lang"/>
		    <html:hidden name="etnetAppletRTQRequest" property="request"/>
		    <html:hidden name="etnetAppletRTQRequest" property="passport"/>
		</form>
		
		</body>
		</html>
		<script language="javascript">
		    document.defaultForm.submit();
		</script>
	</c:when>
</c:choose>

