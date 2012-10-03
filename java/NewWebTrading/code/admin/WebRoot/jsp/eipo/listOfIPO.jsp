<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
</head>
<link href="../jsp/css/link.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE=javascript src="../jsp/js/Common.js"></script>
<%@ include file="../js/checkurl.jsp"%>
<body bgcolor="E8EDF1"> 
<%  
  boolean haveRecord = false;  
  int nPage =0;%>
<logic:present name="ipoRecordList">
  <%
  String pageStr = (String)request.getAttribute("pageNum");
   try
  {
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
   <table width="100%" cellspacing="0" bgcolor="#F8F8F8" border="1" id="menu<%=p%>" style="position:absolute;top:0;visibility: <%if (p==currPage){%>visible;<%}else{%>hidden;<%}%>" >
    <tr bgcolor="#A5B7C5"> 
      <td align=center><font size="2">StockCode</font></td>
      <td align=center><font size="2">eIPOName</font></td>
	  <td align=center><font size="2">Prospectus</font></td>
      <td align=center><font size="2">Price</font> </td>
      <td align=center><font size="2">AppDeadLine</font></td>
      <td align=center><font size="2">PayDeadline</font></td>
	  <td align=center><font size="2">DebitDate</font></td>
      <td align=center><font size="2">LotSize</font></td>
	  <td align=center><font size="2">Status</font></td>
      <td align=center><font size="2">Criteria</font></td>

    </tr>
	<logic:iterate id="ipoRecord" name="ipoRecordList" type="com.taifook.adminportal.web.ipo.dto.IPORecord" offset="<%=sBgn%>" length="20"> 
    <tr> 
	<%haveRecord=true;%>
      <td><font size="2"><bean:write name="ipoRecord" property="stockCode"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="ipoName"/>&nbsp;</font></td>
	  <td><font size="2"><%if(ipoRecord.getProp_url_en()!=null){%><a href='javascript:openwindow("800","550","http://<bean:write name="ipoRecord" property="prop_url_en"/> ","yes")'>view</a><%}%>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="price"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="deadLine"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="depositDate"/>&nbsp;</font></td>
	  <td><font size="2"><bean:write name="ipoRecord" property="debitDate"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRecord" property="quantity"/>&nbsp;</font></td>
	  <td><font size="2"><%if(nowTime.compareTo(ipoRecord.getDeadLine().toString())<0&&"OPEN".equals(ipoRecord.getStatus())){out.write("OPEN");}else{out.write("CLOSE");}%>&nbsp;</font></td>      
    <td><font size="2"><a href='javascript:openwindow("600","440","IPOQueryQtyAmtAction.do?ipoMasterId=<%=ipoRecord.getIpoMasterId()%>","yes")'>View</a>
	   </font></td>
	  
    </tr>
	</logic:iterate> 
      <tr> 
        <td colspan="16">page <%=p%> of <%=nPage%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;GoTo
                    <input name="page<%=p%>" type="text" size="2" maxlength="4" >
                    Page 
                    <input name="subGo4" class="button"  type="button" value="Go"  onClick="javascript:gotopage(page<%=p%>.value,<%=p%>)"> 
		<table width="100%" border="0">
            <tr align=center> 
              <td width="33%" >
                <%if (p>1){%>
                <input name="button" class="button" type="button" onClick="menu<%=p-1%>.style.visibility='visible';menu<%=p%>.style.visibility='hidden';menu<%=p-1%>.focus()"  value="PrePage"> 
                <%}%>
                <%if (p<nPage){%>
                <input name="button" class="button" type="button" onClick="menu<%=p+1%>.style.visibility='visible';menu<%=p%>.style.visibility='hidden';menu<%=p+1%>.focus()"  value="NextPage"> 
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
      <td align=center><font size="2">StockCode</font></td>
      <td align=center><font size="2">eIPOName</font></td>
	  <td align=center><font size="2">Prospectus</font></td>
      <td align=center><font size="2">Price</font> </td>
      <td align=center><font size="2">AppDeadLine</font></td>
      <td align=center><font size="2">PayDeadline</font></td>
	  <td align=center><font size="2">DebitDate</font></td>
      <td align=center><font size="2">LotSize</font></td>
	  <td align=center><font size="2">Status</font></td>
      <td align=center><font size="2">Criteria</font></td>
    </tr>
</table>
<%}%>
<script language="JavaScript">
<!--
function isEmpty(s)
{   return ((s == null) || (s.length == 0))
}
function isDigit (c)
{   return ((c >= "0") && (c <= "9"))
}
function isInteger(s)
{   
	var i;
    if (isEmpty(s))
       if (isInteger.arguments.length == 1) return false;
       else return (isInteger.arguments[1] == true);

    for (i = 0; i < s.length; i++)
    {
        // Check that current character is number.
        var c = s.charAt(i);

        if (!isDigit(c)) return false;
    }
    return true;
}
	function gotopage(dd,n)
	{
	  if(!isInteger(dd)||(dd><%=nPage%>)||(dd==n)||dd<1)
		{
           alert("please fill in correct pageNo !");
	       return false;
		}
	 eval("menu"+dd+".style.visibility='visible'");
	 eval("menu"+n+".style.visibility='hidden'");
	}
//-->
</script>
</body>

</html>
