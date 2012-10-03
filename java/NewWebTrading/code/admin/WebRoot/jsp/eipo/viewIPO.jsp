<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.taifook.adminportal.common.util.StringUtil"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<SCRIPT LANGUAGE=javascript src="../jsp/js/Common.js"></script>
</head>
<%@ include file="../js/checkurl.jsp"%>
<link href="../jsp/css/link.css" rel="stylesheet" type="text/css">
<body bgcolor="E8EDF1"> 
<%  
  boolean haveRecord = false;%>
<logic:present name="ipoRecordList">
<%
  int nPage =0;
   try
  {
  String pageStr = (String)request.getAttribute("pageNum");
  nPage = Integer.parseInt(pageStr);
 }
  catch(Exception e)
  {
  }

  int currPage = 1;
  try
  {
    String pageNo = (String)request.getAttribute("pageNo");
    currPage = Integer.parseInt(pageNo);
  }
  catch(Exception e)
  {
  }
  int nBgn = 0;
  String sBgn = "0";

  Date applyDate = new Date();
  String nowTime = new Timestamp(applyDate.getTime()).toString();


  for (int p=1;p<nPage+1;p++)
  {
      nBgn = (p-1)*20;
      sBgn = Integer.toString(nBgn);
    %>
   <table width="100%" cellspacing="0" border="1" id="menu<%=p%>" style="position:absolute;top:0;visibility: <%if (p==currPage){%>visible;<%}else{%>hidden;<%}%>" >
    <tr bgcolor="#A5B7C5"> 
      <td align=center><font size="2">eIPOCode</font></td>
      <td align=center><font size="2">eIPOName</font></td>
	  <td align=center><font size="2">GBName</font></td>
	  <td align=center><font size="2">BIGName</font></td>
      <td align=center><font size="2">StockCode</font></td>
      <td align=center><font size="2">Price</font> </td>
      <td align=center><font size="2">AppDeadLine</font></td>
      <td align=center><font size="2">PayDeadline</font></td>
	  <td align=center><font size="2">DebitDate</font></td>
      <td align=center><font size="2">P.URl.EN</font></td>
      <td align=center><font size="2">P.URl.CN</font></td>
	  <td align=center><font size="2">P.URl.TW</font></td>
      <td align=center><font size="2">LotSize</font></td>
	  <td align=center><font size="2">Status</font></td>
      <td align=center><font size="2">Action</font></td>
    </tr>
	<logic:iterate id="ipoRecord" name="ipoRecordList" type="com.taifook.adminportal.web.ipo.dto.IPORecord" offset="<%=sBgn%>" length="20"> 
    <tr> 
	<%haveRecord=true;%>
      <td><font size="2"><bean:write name="ipoRecord" property="ipoCode"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="ipoName"/>&nbsp;</font></td>
	  <td><font size="2"><bean:write name="ipoRecord" property="ipoName_gb"/>&nbsp;</font></td>
	  <td><font size="2"><bean:write name="ipoRecord" property="ipoName_big"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="stockCode"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="price"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="deadLine"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="depositDate"/>&nbsp;</font></td>
	  <td><font size="2"><bean:write name="ipoRecord" property="debitDate"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="prop_url_en"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="prop_url_cn"/>&nbsp;</font></td>
	  <td><font size="2"><bean:write name="ipoRecord" property="prop_url_tw"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="quantity"/>&nbsp;</font></td>
	  <td><font size="2"><%if(nowTime.compareTo(ipoRecord.getDeadLine().toString())<0&&"OPEN".equals(ipoRecord.getStatus())){out.write("OPEN");}else{out.write("CLOSE");}%>&nbsp;</font></td>      
    <td><font size="2"><a href='javascript:openwindow("600","440","IPOQueryQtyAmtAction.do?ipoMasterId=<%=ipoRecord.getIpoMasterId()%>","yes")'>Criteria</a>
	/<%if(nowTime.compareTo(ipoRecord.getDeadLine().toString())<0){%><a href="javascript:if (window.confirm('Do you really open IPOName <%=StringUtil.delQuotationMark(ipoRecord.getIpoName())%>?')){window.location='IPOStatusMngAction.do?ipoMasterId=<%=ipoRecord.getIpoMasterId()%>&status=OPEN&pageNo=<%=p%>';}">Open</a>/
	<a href="javascript:if (window.confirm('Do you really close IPOName <%=StringUtil.delQuotationMark(ipoRecord.getIpoName())%>?')){window.location='IPOStatusMngAction.do?ipoMasterId=<%=ipoRecord.getIpoMasterId()%>&status=CLOSE&pageNo=<%=p%>';}">Close</a>/<%}%><a href="IPOQueryCodeMngAction.do?ipoMasterId=<%=ipoRecord.getIpoMasterId()%>"">Edit</a>/<a href="javascript:if (window.confirm('IPOName is <%=StringUtil.delQuotationMark(ipoRecord.getIpoName())%>,Do you really delete this IPO?the deleted info can not be resume')){window.location='IPODelMngAction.do?ipoMasterId=<%=ipoRecord.getIpoMasterId()%>&pageNo=<%=p%>';}">Del</a>
	   </font></td>
    </tr>
	</logic:iterate> 
      <tr> 
        <td colspan="16">
		<table width="100%" border="0">
            <tr align=center> 
              <td width="33%" >&nbsp;
                <%if (p>1){%>
                <input name="button" class="button1" type="button" onClick="menu<%=p-1%>.style.visibility='visible';menu<%=p%>.style.visibility='hidden';menu<%=p-1%>.focus()"  value="PrePage"> 
                <%}%>
                <%if (p<nPage){%>
                <input name="button" class="button1" type="button" onClick="menu<%=p+1%>.style.visibility='visible';menu<%=p%>.style.visibility='hidden';menu<%=p+1%>.focus()"  value="NextPage"> 
                <%}%>
				</td>
            </tr>
          </table>   
		  </td>
      </tr>
    </tr>
  </table>
<%
 }
%>
</logic:present><br>
<%if(!haveRecord){%>
   <table width="100%" cellspacing="0" bgcolor="#F8F8F8" border="1">
    <tr> 
      <td align=center><font size="2">eIPOCode</font></td>
      <td align=center><font size="2">eIPOName</font></td>
	  <td align=center><font size="2">GBName</font></td>
	  <td align=center><font size="2">BIGName</font></td>
      <td align=center><font size="2">StockCode</font></td>
      <td align=center><font size="2">Price</font> </td>
      <td align=center><font size="2">AppDeadLine</font></td>
      <td align=center><font size="2">PayDeadline</font></td>
	  <td align=center><font size="2">DebitDate</font></td>
      <td align=center><font size="2">P.URl.EN</font></td>
      <td align=center><font size="2">P.URl.CN</font></td>
	  <td align=center><font size="2">P.URl.TW</font></td>
      <td align=center><font size="2">LotSize</font></td>
	  <td align=center><font size="2">Status</font></td>
      <td align=center><font size="2">Action</font></td>
    </tr>
</table>
<%}%>
</body>
</html>
