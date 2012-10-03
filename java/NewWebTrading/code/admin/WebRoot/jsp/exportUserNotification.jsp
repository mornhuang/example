<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.Iterator,java.util.List" %>
<%@ page import="java.io.File"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ page import="com.itsz.sht.common.model.common.LoggerFactory" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date" %>
<%@ include file="js/checkurl.jsp"%>
<%@ include file="js/openhelper.jsp"%>

<%
	String queryType = request.getParameter("queryType");
	byte[] ba = (byte[]) request.getAttribute("ba");
	if (queryType != null && ba!= null){
    	response.reset();
		response.setContentType("application/form-data");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowTime = sdf.format(new Date());
		String fs = "OTCON".equals(queryType)? "CUST_SUBS_FULL_"+ nowTime+".DAT":"Futures_rtq_user_quote_info.DAT";
		String s = "attachment; filename="+fs;
		response.setHeader("Content-Disposition", s);
		ServletOutputStream sout = null;
		try{
        	sout = response.getOutputStream();
			sout.write(ba);			
		}catch(Exception ex){
			LoggerFactory.getInstance().getMcsInfo().error("Generate Notification Data Exception.", ex);
		}finally{
			if(sout != null){
				sout.close();				
			}
			out.clear();
			out = pageContext.pushBody();
		}
	}else{

%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>Real Time Quote User Info Download</title>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css"> 
</head>
<BODY BGCOLOR="#FFFFFF" TOPMARGIN="0" LEFTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">
<form name="frm" method="post">

	<table border=0 width=100% align="left" cellspacing=0 cellpadding=2>
	  <tr>
	  	<td height="23" bgcolor="#A5B7C5">
	 		<font color="#ffffff"><b>Notification Data Report</b></font>
		</td>
	  </tr>	
	  <tr>
	  	<td align=center><br><br><br>
	  		<INPUT TYPE="button" name="submit1" value="Generate Notification Data" onclick="frm.action='<%=request.getContextPath() %>/exportNotificationMedia.do?method=export&queryType=OTCON';frm.submit();" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	</td>
	  </tr>
	</table>
</form>
</body>
</html>
<% } %>








