<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.taifook.adminportal.web.ipo.dto.IPOResult"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
   IPOResult ipoResult=(IPOResult)request.getAttribute("currentIpoResult");
  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<link href="../jsp/css/link.css" rel="stylesheet" type="text/css">
<%@ include file="../js/checkurl.jsp"%>
<body bgcolor="E8EDF1"> 
	
  <table width="98%" border="1" cellspacing="0" >
    <tr bgcolor="#A5B7C5"> 
      <td align=center width="5%"><font size="2">Refno</font></td>
	  <td align=center width="6%"><font size="2">eIPOCode</font></td>
      <td align=center width="6%"><font size="2">ApplyQty</font></td>
      <td align=center width="7%"><font size="2">Amount</font></td>
      <td align=center width="6%"><font size="2">ACNo</font></td>
	  <td align=center width="5%"><font size="2">MIS_No</font></td>
      <td align=center width="11%"><font size="2">Application Status</font></td>
	  <td align=center width="5%"><font size="2"><a href="../jsp/eipo/rejectcode.jsp" target="_blank">rejectCode</a></font></td>
    </tr>
    <tr> 
      <td><font size="2"><bean:write name="currentIpoResult" property="applyId"/>&nbsp;</font></td>
	  <td><font size="2"><bean:write name="currentIpoResult" property="ipoCode"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="currentIpoResult" property="applyQuantity_dsply"/>&nbsp;</font></td>
      <td><font size="2">$<bean:write name="currentIpoResult" property="dsptAmount_dsply"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="currentIpoResult" property="accountId"/>&nbsp;</font></td>
	  <td><font size="2"><bean:write name="currentIpoResult" property="misRefCode"/>&nbsp;</font></td>
	  <td><font size="2">
	  <%
	  if("0".equals(ipoResult.getPrgStatus())){out.print("Application in Progress");}
        else{
			if("3".equals(ipoResult.getPrgStatus())){out.print("Allotment Result is available");}
			  else{
				  if("2".equals(ipoResult.getPrgStatus())){out.print("MIS cancelled");}
				  else{out.print("MIS rejected");}}}
				  %>
	  &nbsp;</font></td>
      <td><font size="2"><bean:write name="currentIpoResult" property="rejectCode"/>&nbsp;</font></td>
    </tr>
  </table>
</body>
</html>
