<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="com.taifook.adminportal.web.ipo.form.IPORequestForm"%>
<%@ page import="com.taifook.adminportal.web.ipo.delegate.IPOMaintenanceDelegate"%> 
<%@ page import="com.taifook.adminportal.web.ipo.dto.IPOReqResOrder"%> 
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<form name="form1" method="post" >
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<SCRIPT LANGUAGE=javascript src="../jsp/js/Common.js"></script>
</head>
<link href="../jsp/css/link.css" rel="stylesheet" type="text/css">
<%@ include file="../js/checkurl.jsp"%>
<body bgcolor="E8EDF1"> 
<%
   boolean haveRecord = false;
   String applyType = "apply";
   long ipoMasterId=0;
   long nIpoMasterId=0;
   String accountId="",andIPOCode="",andAccount="";
   IPORequestForm form=(IPORequestForm)request.getAttribute("IPORequestForm");
   if(form!=null)
	 {
		 applyType=form.getApplyType();
		 ipoMasterId=form.getIpoMasterId();
         accountId=form.getAccountId();
		 andIPOCode = form.getAndIPOCode();
         andAccount = form.getAndAccount();
      }

  int nPage =1;
  String pageStr = (String)request.getAttribute("pageNum");
  try{
        nPage = Integer.parseInt(pageStr);
  }
  catch(Exception e)
  {
  }
  int pCurr=1;
  String currStr = (String)request.getAttribute("currPage");
  try{
        pCurr = Integer.parseInt(currStr);
  }
  catch(Exception e)
  {
  }
  int nBgn = 0;
  String sBgn = "0";
  IPOMaintenanceDelegate ipoMaintenanceDelegate = new IPOMaintenanceDelegate();
  //IPORecord ipoRecord = new IPORecord();
  //ipoMaintenanceDelegate.getIPORecord(ipoMasterId);
%>
  
<p align="center"> <font color="#0000FF" size="4"><strong>eIPO Application Status Enquiry </strong></font>
<p>
    <input type="hidden" name="applyType" value="apply">
    <input type="checkbox" onclick="activeButton()" name="andIPOCode" value="Y" <%if("Y".equals(andIPOCode)){out.print("checked");}%>>
    <font size="2">Stock Code:</font> 
    <select name="ipoMasterId" size="1"><font size="2">
      <logic:present name="ipoRecordList"><logic:iterate id="ipoRecord" name="ipoRecordList" type="com.taifook.adminportal.web.ipo.dto.IPORecord"> 
	  <%haveRecord=true;%>
      <option value=<bean:write name="ipoRecord" property="ipoMasterId"/> <%if(ipoMasterId==ipoRecord.getIpoMasterId().longValue()){out.print("selected");}%>><bean:write name="ipoRecord" property="stockCode"/></option> 
      </logic:iterate> </logic:present> 
	  </font>
    </select>
  </p>
  <p>
    <input type="checkbox" onclick="activeButton()" name="andAccount" value="Y" <%if("Y".equals(andAccount)){out.print("checked");}%>>
    <font size="2">Client A/C</font>
    <input type="text"  name="accountId" value='<bean:write name="IPORequestForm" property="accountId"/>'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onClick="searchAction()" name="search" value="Search" disabled> <p>
	The format of client code should be the same as "02-XXXXXXX-30" or "02-XXXXXXX-33".
	<table><logic:present name="ipoResultStatusList"><logic:iterate id="ipoRsltSts" name="ipoResultStatusList" >
        <tr> 
          <Td><font size=2><bean:write name="ipoRsltSts" property="resultStatus"/>:
          </td>
          <Td><font size=2><bean:write name="ipoRsltSts" property="recordCount"/>
          </td>
        </tr></logic:iterate></logic:present>
	</table>
  </p>
<hr>

  <logic:present name="ipoRquestList">	
  <%  
  for (int p=1;p<nPage+1;p++)
  {
      nBgn = (p-1)*20;
      sBgn = Integer.toString(nBgn);
	  %>
  <table width="98%" border="1" cellspacing="0"  id="menu<%=p%>" style="position:absolute;visibility: <%if (p==1){%>visible;<%}else{%>hidden;<%}%>">
    <tr bgcolor="#A5B7C5"> 
      <td align=center width="5%"><font size="2">Refno</font></td>
	  <td align=center width="6%"><font size="2">StockCode</font></td>
	  <td align=center width="18%"><font size="2">eIPOName</font></td>      
	  <td align=center width="4%"><font size="2">Price</font></td>
      <td align=center width="6%"><font size="2">ApplyQty</font></td>
      <td align=center width="6%"><font size="2">Amount</font></td>
	  <td align=center width="12%"><font size="2">ACNo</font></td>    
      <td align=center width="9%"><font size="2">Mobile No</font></td>
      <td align=center width="10%"><font size="2">Email address</font></td>  
      <td align=center width="5%"><font size="2"><a href='javascript:openwindow("850","350","../jsp/eipo/status.jsp","no")'>Status</a></font></td>
	  <td align=center width="5%"><font size="2"><a href='javascript:openwindow("850","850","../jsp/eipo/rejectcode.jsp" ,"no")'>RejectReason</a></font></td>
	  <td align=center width="10%"><font size="2">ReceivedTime</font></td>	  
    </tr>
	<logic:iterate id="ipoReqResOrder" name="ipoRquestList" offset="<%=sBgn%>" length="20" type="com.taifook.adminportal.web.ipo.dto.IPOReqResOrder">
    <tr> 
	<% 
	    nIpoMasterId = ipoReqResOrder.getIpoMasterId();
        //System.out.println("nIpoMasterId="+nIpoMasterId);
       %>
      <td><font size="2"><bean:write name="ipoReqResOrder" property="applyId"/>&nbsp;</font></td>
	  <td><font size="2"><%=ipoMaintenanceDelegate.getIPORecord(nIpoMasterId).getStockCode()%>&nbsp;</font></td> 
	  <td><font size="2"><%=ipoMaintenanceDelegate.getIPORecord(nIpoMasterId).getIpoName()%>&nbsp;</font></td>
	  <td><font size="2"><%=ipoMaintenanceDelegate.getIPORecord(nIpoMasterId).getPrice()%>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoReqResOrder" property="needQuantity"/>&nbsp;</font></td>
      <td><font size="2">$<bean:write name="ipoReqResOrder" property="dsptAmount"/>&nbsp;</font></td>
	  <td><font size="2"><bean:write name="ipoReqResOrder" property="accountId"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoReqResOrder" property="telephone"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoReqResOrder" property="email"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoReqResOrder" property="applyStatus"/>&nbsp;</font></td>
	  <td><font size="2"><bean:write name="ipoReqResOrder" property="rejectCode"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoReqResOrder" property="applyTime"/>&nbsp;</font></td>
    </tr>
	</logic:iterate> 
	  <tr> 
        <td colspan="13">page <%=p%> of <%=nPage%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;GoTo
                    <input name="page<%=p%>" type="text" size="2" maxlength="4" >
                    Page 
                    <input name="subGo4" class="button"  type="button" value="Go"  onClick="javascript:gotopage(page<%=p%>.value,<%=p%>)"> 
		<table width="100%" border="0">
            <tr align=center> 
              <td width="33%" >
			  &nbsp;
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
  </table>
  <%
 }
%>
</logic:present>
<% List list = list=(List)request.getAttribute("ipoRquestList");  
  if(list==null||list.size()<1)
 {
	 out.println("Can not find any eIPO apply record!");
 }
 %>
</form>
<p>&nbsp; </p>
</body>
<script language="JavaScript">
<!--
if(<%=haveRecord%>==false)
{
   form1.andIPOCode.disabled=true;
}
if(form1.andAccount.checked==false)
{
	form1.accountId.disabled=true;
}

function isEmpty(s)
{   
	return ((s == null) || (s.length == 0))
}
function isDigit (c)
{   
	return ((c >= "0") && (c <= "9"))
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
	var applyStr="";
	function activeButton()
	{ 
	  var sum=0;
	  form1.search.disabled=true;
	  for (var i=0;i<form1.elements.length;i++)
        {
         var e = form1.elements[i];
         if (e.type == "checkbox"&&e.checked == true)
			{ 
                 sum=sum+1;
			}
		}
		if(sum>0)
		{
           form1.search.disabled=false;
		}
		
	   
	   if(form1.andAccount.checked==true)
		{
          form1.accountId.disabled=false;
		}
		else
		{
          form1.accountId.value="";
		  form1.accountId.disabled=true;
		}
	}

    var sum=0;
    form1.search.disabled=true;
    for (var i=0;i<form1.elements.length;i++)
     {
       var e = form1.elements[i];
       if (e.type == "checkbox"&&e.checked == true)
	    { 
          sum=sum+1;
         }
      }
     if(sum>0)
     {
       form1.search.disabled=false;
     }   

	function searchAction()
	{ 
        form1.action="IPOAEQueryApplyAction.do";
		document.form1.submit();
	}

	function searchPage(currpage,firstRslt)
	{ 
        if(!isInteger(currpage))
		{
           alert("please fill in correct pageNo !");
	       return false;
		}
		form1.currPage.value=currpage;
		form1.firstResult.value=firstRslt;
		form1.action="IPOAEQueryApplyAction.do"; 
		document.form1.submit();
	}

	function selectAllapp()
	{
	   for (var i=0;i<form1.elements.length;i++)
       {		
        var e = form1.elements[i];
        if (e.type == "checkbox"&&e.name=="applyCodeStr")	
           e.checked = form1.selectAll.checked;	
      }
	  
	}	

	function checkForm()
	{
	   var sltnum=0;
       for (var i=0;i<form1.elements.length;i++)
      {
        var e = form1.elements[i];
        if (e.checked == true&&e.name=="applyCodeStr")
		  {
		    sltnum=1;
			return true;
		  }
      }
      if (sltnum<1)
	  {
	   alert("sorry,you have not selected any apply result record! ");
	   return false;
      }
	}
	function toAvlAction()
	{ 
		if(checkForm())
		{
          document.form1.action="IPOResultUpdateAvlAction.do"; 
		  document.form1.submit();
		}
	}

	function toPrgAction()
	{ 
		if(checkForm())
		{
		   document.form1.action="IPOResultUpdatePrgAction.do";
           document.form1.submit();
		}
	}

	function allAvlAction()
	{ 
         if (window.confirm('Do you really set all record to Allotment Result is available?')){
          document.form1.action="IPOResultAllAvlAction.do"; 
		  document.form1.submit();
		 }
	}

	function allPrgAction()
	{ 
		if (window.confirm('Do you really set all record to Application in Progress?')){
		   document.form1.action="IPOResultAllPrgAction.do";
           document.form1.submit();
		}
	}
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
	 window.scroll(0,0);
	}			
//-->
</script>
</html>
