<%@ page import="java.util.Properties"%>
<%@ page import="java.io.*"%>
<%@ page import="com.itsz.Contingency.ClientMain"%>
<%

String status = request.getParameter("fts_status");

String file = application.getRealPath("config.properties");

if(status!=null){
	try {
  	Properties props =new Properties();
	FileInputStream inStream = new FileInputStream(file);

	props.load(inStream);
	FileOutputStream outStream = new FileOutputStream(file);

	props.put("futures_trade_system_status",status);
        props.store(outStream, null);
        inStream.close();
        outStream.close();
        ClientMain.futStatus = status;
 }
 catch (Exception ex) {
	ex.printStackTrace();
 }

}
%>
<html>
<head>
<title>
process
</title>
</head>
<body bgcolor="#ffffff">
<h3>

</h3>
<form action="manage.jsp" method="POST" name="tf">
<input type="submit" name="Submit1" value="Back">
<input type="hidden" name="password" value="<%=request.getParameter("password")%>">
<input type="hidden" name="userid" value="<%=request.getParameter("userid")%>">
</body>
</html>