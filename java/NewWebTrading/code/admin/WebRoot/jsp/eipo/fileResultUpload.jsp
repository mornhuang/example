<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.io.File"%>
<%@ page import="com.jspsmart.upload.*"%>
<%@ page import="com.taifook.adminportal.common.Constants" %>
	<%
	SmartUpload  fileupload = new SmartUpload();
	fileupload.initialize(pageContext);
	fileupload.upload();

	com.jspsmart.upload.File myFile1 = fileupload.getFiles().getFile(0);
	String strFileName1 = myFile1.getFileName();	
	String filepath = (String)session.getAttribute(Constants.TEMP_FILE_PATH).toString();
	File dirfile = new File(filepath);
	if(!dirfile.exists())
	{
       dirfile.mkdir();
	}
	filepath = filepath+"/"+strFileName1;

	myFile1.saveAs(filepath, fileupload.SAVE_PHYSICAL );

	response.sendRedirect("IPOImportResultAction.do?ipoResultFilePath="+filepath);	
%>