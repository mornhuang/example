<%@ page contentType="text/html;charset=utf-8" %>
<%
	response.addHeader("Progma", "No-cache");
	response.addHeader("Cache-Control", "no-cache");
	response.addDateHeader("Expires", 1);
	String url = (String) request.getAttribute("url");
	String uid = (String) request.getAttribute("uid");
	String requestStr = (String) request.getAttribute("requestStr");
	String password = (String) request.getAttribute("password");
	Boolean demo = (Boolean) request.getAttribute("demo");demo=true;
	String lang = (String) request.getAttribute("lang");
%>
<html>
<head>
	<meta http-equiv=pragma content=no-cache>
</head>
<body bgcolor="#ffffff" background="images/QuotationBG.gif">
<form name="defaultForm" action="<%=url%>" method="post">
    <input type="hidden" name="UserID" value="<%=uid%>"/>
    <input type="hidden" name="passwordfield" value="<%=password%>"/>
    <input type="hidden" name="lang" value="<%=lang%>"/>
    <input type="hidden" name="request" value="<%=requestStr%>"/>
    <%if(demo){%>
    	<input type="hidden" name="demo" value="true"/> 
    <%} %>
</form>
</body>
</html>
<script language="javascript">
    document.defaultForm.submit();
</script>
