<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.itsz.util.CommonUtil" %>
<%@ page import="com.itsz.common.Constants" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String link = "EtnetForward.do";
String t = link;
try{
	link = URLEncoder.encode(t,"UTF-8");
}catch(UnsupportedEncodingException e){

}
%>
<html>

<head>
<TITLE><bean:message key="label.company"/></TITLE>
</head>

<body bgcolor="#ffffff">
<script language="javascript">
var link = "popup_forward.jsp?link=<%=link%>";
openwin();
function openwin(){
	window.open(link, "ETNet_RTQ", "toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=1,width=800,height=530,left=140,top=0"); 
}
</script> 
</body>
</html>
