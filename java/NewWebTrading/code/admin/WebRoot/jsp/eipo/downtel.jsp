<%@page language="java" contentType="text/html"%><%@page import="com.jspsmart.upload.*"%><%

	String ipoExpTelPath=(String)request.getParameter("ipoExpTelPath");
	//System.out.println("hi,this jsp filepath="+ipoExpTelPath);
	
	SmartUpload su = new SmartUpload();
		// 初始化
	su.initialize(pageContext);
		// 设定contentDisposition为null以禁止浏览器自动打开文件，
		//浏览器将用acrobat打开。
	su.setContentDisposition(null);
		// 下载文件
	su.downloadFile(ipoExpTelPath);
%>