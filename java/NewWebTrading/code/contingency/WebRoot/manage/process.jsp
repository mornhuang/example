<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.itsz.util.*"%>

<%@ page import="com.itsz.Contingency.DataImptAsync" %>
<%
  	ProcessStatus status = ProcessStatus.getStatus("ImportDataSync");
    if ("release".equals(request.getParameter("action"))) {
		status.reset();
	}
    Counters counters = status.getCounters();
    int total = status.getTotalRecord(), insertTotal = 0, insertFail = 0, registTotal = 0, registFail = 0 , 
	blankTotal = 0 ;
    
  
  	Collection upFileList ;/*up file content*/
  	Dictionary inputs ;/*other HTML input module*/
  
  	String result="";

  	UploadFile up = new UploadFile(request);

    upFileList = up.getRowList() ;
    inputs = up.getInputs() ;

	String userid= (String)inputs.get("userid");
	String password = (String)inputs.get("password");

	String uploadType = (String)inputs.get("upfiletype")  ;
	
	DataImptAsync async = new DataImptAsync() ;
	
	async.processUpload(upFileList,inputs,uploadType) ;
	
	response.sendRedirect("process_status.jsp?password="+password+"&userid=" + userid);


%>

