<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.*"%>
<%@ page import="com.itsz.Contingency.ClientMain"%>
<%@ page import="com.itsz.util.*"%>

<%
String password = request.getParameter("password");
String userid = request.getParameter("userid");
String authPass = null;
String authUser = null;
%>
<%
	ProcessStatus pStatus = ProcessStatus.getStatus("ImportDataSync");

	if (!pStatus.isReseted()) {
    %>
	<jsp:forward page="process_status.jsp?password=<%=password%>&userid=<%=userid%>"></jsp:forward>
	<%}
	%>

<%
if (password==null||userid==null){

	response.sendRedirect("login.jsp");
}

String status ="";
String availSelected = "" ;
String unavlSelected = "" ;

String fts_status ="";
String fts_availSelected = "" ;
String fts_unavlSelected = "" ;

String eSer_status = "";
String eSer_availSelected = "";
String eSer_unavlSelected = "";
//String file = application.getRealPath("config.properties");
//	try {

 // 	Properties props =new Properties();
//	FileInputStream inStream = new FileInputStream(file);

//	props.load(inStream);
    	status = ClientMain.secStatus;
    	fts_status = ClientMain.futStatus;
    	eSer_status = ClientMain.eSerStatus;
	authPass = ClientMain.authPass;
	authUser = ClientMain.authUser;
	//inStream.close() ;

	if (status.equalsIgnoreCase("A")){
	  availSelected = "selected";
	}
	if (status.equalsIgnoreCase("I")){
	  unavlSelected = "selected" ;
	}

	if (fts_status.equalsIgnoreCase("A")){
	  fts_availSelected = "selected";
	}
	if (fts_status.equalsIgnoreCase("I")){
	  fts_unavlSelected = "selected" ;
	}
	if(eSer_status.equalsIgnoreCase("A")){
	  eSer_availSelected = "selected";
	}
	if(eSer_status.equalsIgnoreCase("I")){
	  eSer_unavlSelected = "selected";
	}

 //}


 if (authPass!=null&&authPass.equals(password)&&authUser!=null&&authUser.equals(userid)){

 }
 else {
 %>
 <SCRIPT LANGUAGE="javascript">
	 alert("Login authenticate failure!");
     document.location.href="login.jsp";
 </SCRIPT>
 <%
 }%>
<html>
<head>
<title>
Contingency RTQ Admin
</title>
</head>
<SCRIPT LANGUAGE="javascript">
<!--
   function update_click(){
   		frm.submit();
    }

   function check_upload() {
	 // Added by Simon (2004/01/29) - Implement Javascript function to check the user must select a file to upload and alert error ("Please select a file to upload").
	 if (document.form1.upfilepath.value == '') {
		alert("Please select a file to upload.");
		return false;
	 } else {
		// Added by Simon (2004/02/11) - Check upload filename is the correct file type selected.
		var filename = document.form1.upfilepath.value;
		var arr_filename = filename.split("\\");
		var filetype = document.form1.upfiletype.value;
		if (arr_filename.length > 0) {
			filename = arr_filename[arr_filename.length-1];
			if (filetype == "broker" && filename != "rtq_user_ae_info.dat"){
				alert("You have selected the wrong file to upload.The file must be 'rtq_user_ae_info.dat'");
				return false;
			}
			else if (filetype == "quote" && filename != "sec_rtq_user_info.dat") {
				alert("You have selected the wrong file to upload.The file must be 'sec_rtq_user_info.dat'");
				return false;
			}
			else if (filetype == "futquote" && filename != "fut_rtq_user_info.dat") {
				alert("You have selected the wrong file to upload.The file must be 'fut_rtq_user_info.dat'");
				return false;
			}else if(filetype == "futbroker" && filename != "Futures_rtq_user_ae_info.dat"){
                                alert("You have selected the wrong file to upload.The file must be 'Futures_rtq_user_ae_info.dat'");
				return false;
                        }
		} else {
			alert('Invalid file format.');
			return false;
		}

   		return true;
	 }
    }
    function fts_update(){
   		fts_frm.submit();
    }
    
    //new for eService
    function es_update(){
        eSer_frm.submit();
    }
//-->
  </SCRIPT>
  <style>
	A:link		{font-family: "Verdana" , "Tahoma" , "Arial" "細明體"; font-size: 10pt; color:005bac; text-decoration: underline; cursor: hand;}
	A:visited	{font-family: "Verdana" , "Tahoma" , "Arial" "細明體"; font-size: 10pt; color:005bac; text-decoration: underline;}
	A:active	{font-family: "Verdana" , "Tahoma" , "Arial" "細明體"; font-size: 10pt; color:f39600; text-decoration: none;}
	A:hover		{font-family: "Verdana" , "Tahoma" , "Arial" "細明體"; font-size: 10pt; color:f39600; text-decoration: none;}
	</style>
<body bgcolor="#ffffff">
<h1>
	Contingency RTQ Admin Site
</h1>
<table width="100%">
<tr><td align="right">[<a href="index.jsp">Logout</a>]</td></tr>
</table>
Host name:<%=request.getServerName()%>
<!--<form name="form1" method="post" enctype="MULTIPART/FORM-DATA" action="process.jsp" onSubmit="javascript:return check_upload();">-->
<!--<input type="hidden" name="password" value="<%=password%>">-->
<!--<input type="hidden" name="userid" value="<%=userid%>">-->
<!--File Upload: <INPUT TYPE="file" NAME="upfilepath"  id="upfilepath" style="width:320px"><br>-->
<!--Data File Type: <select name="upfiletype">-->
<!--  <option value="broker">Online Securities - Account Executive Info</option>-->
<!--  <option value="quote">Online Securities - User Quote Info</option>-->
<!--  <option value="futbroker">Online Futures - Account Executive Info</option>-->
<!--  <option value="futquote">Online Futures - User Quote Info</option>-->
<!--</select> <input type="submit" name="Submit" value="Upload File">-->
<!--</form>-->
<br></br>
<form name="frm" method="post"  action="updatestatus.jsp">
 Securities Trading System Status :
<select name="status">
    <option value="A" <%=availSelected%>>Available</option>
    <option value="I" <%=unavlSelected%>>UnAvailable</option>
</select>
<input type="button" name="Submit1" onclick="update_click();" value="Update Status">
<input type="hidden" name="password" value="<%=password%>">
<input type="hidden" name="userid" value="<%=userid%>">
</form>

<!--<form name="fts_frm" method="post"  action="updates_fts_tatus.jsp">-->
<!-- Futures Trading System Status :-->
<!--<select name="fts_status">-->
<!--    <option value="A" <%=fts_availSelected%>>Available</option>-->
<!--    <option value="I" <%=fts_unavlSelected%>>UnAvailable</option>-->
<!--</select>-->
<!--<input type="button" name="Submit2" onclick="fts_update();" value="Update Status">-->
<!--<input type="hidden" name="password" value="<%=password%>">-->
<!--<input type="hidden" name="userid" value="<%=userid%>">-->
<!--</form>-->

<!--<form name="eSer_frm" method="post"  action="updates_es_status.jsp">-->
<!--EService System Status :-->
<!--<select name="eSer_status">-->
<!--    <option value="A" <%=eSer_availSelected%>>Available</option>-->
<!--    <option value="I" <%=eSer_unavlSelected%>>UnAvailable</option>-->
<!--</select>-->
<!--<input type="button" name="Submit3" onclick="es_update();" value="Update Status">-->
<!--<input type="hidden" name="password" value="<%=password%>">-->
<!--<input type="hidden" name="userid" value="<%=userid%>">-->
<!--</form>-->
</body>
</html>
