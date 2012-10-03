<%@ page contentType="text/html;charset=utf-8" %>
<%
	response.addHeader("Progma", "No-cache");
	response.addHeader("Cache-Control", "no-cache");
	response.addDateHeader("Expires", 1);
	String uid = (String) request.getAttribute("uid");
	String url = (String) request.getAttribute("quoteUrl");
	String lang = (String) request.getAttribute("lang");
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	String link = basePath+"stt/EtnetForward.do";
%>
<html>
<head>
	<meta http-equiv=pragma content=no-cache>
</head>
<body bgcolor="#ffffff" background="images/QuotationBG.gif">
<form name="defaultForm" action="<%=link%>" method="post">
    <input type="hidden" name="uid" value="<%=uid%>"/>
    <input type="hidden" name="url" value="<%=url%>"/>
    <input type="hidden" name="lang" value="<%=lang%>"/>
</form>
</body>
</html>
<script language="javascript">
    document.defaultForm.submit();
</script>
