<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.itsz.util.*"%>


<%
  	ProcessStatus status = ProcessStatus.getStatus("ImportDataSync");
  	
  	
    if ("release".equals(request.getParameter("action"))) {
		status.reset();
	}
    Counters counters = status.getCounters();
    int total = status.getTotalRecord();
  
  
  
  	String password = request.getParameter("password");
  	String userid = request.getParameter("userid");



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
<p>
<%
   if (status.isReseted() ) //result reseted
   {
	%>
	<p>
	The latest process result has been cleared.
	<p>

	<input type="button" name="Return"
			onclick="window.location.href='manage.jsp?password=<%=password%>&userid=<%=userid%>';"
			value="Return">

	<%
    } 
	else { //processing or result cashed
    %>
    	<p>Progress :<%= counters.getValue("TOTAL") + " of " + total %>
    	<p>Start Time :<%= status.getStartTime().toString() %>
    <%
    
    %>
    <%
 		if (!status.isProcessing() ) {
    %>
    	<p>End Time :<%= status.getEndTime().toString() %>
    <%
        }
        else {
    %>
	    <p>Processing ..................
	<%
    	}
    %>
    <%
		if (! status.getErrors().isEmpty()) {
	%>
		<p>Errors:</p>
	<%
    		for (Iterator itr = status.getErrors().values().iterator();itr.hasNext();){
  				out.print(itr.next());
  	    		out.print("<br>");    
  			}    	
    	}
    %>
    <%
 		if (status.isProcessing() ) {
    %>	
    	<p><input type="button" name="Refresh"
			onclick="window.location.href = 'process_status.jsp?password=<%=password%>&userid=<%=userid%>'" value="Refresh">  
	<%
        }
		else{
    %>
	<%
			if (status.isReseted()) {
	%>
		<p><input type="button" name="Return"
			onclick="window.location.href='manage.jsp';" value="Return">&nbsp;&nbsp;&nbsp;
	<% 		} %>
		<p><input type="button" name="Release"
			onclick="window.location.href='process_status.jsp?password=<%=password%>&userid=<%=userid%>&action=release';"
			value="Release Result">
		
	
	<%}
	}
%>



</body>
</html>
