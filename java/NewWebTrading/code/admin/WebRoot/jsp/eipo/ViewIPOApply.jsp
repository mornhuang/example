<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.taifook.adminportal.web.ipo.form.IPORequestForm"%>
<%@ page import="com.taifook.adminportal.web.ipo.delegate.IPOMaintenanceDelegate"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<form name="form1" method="post" >
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
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
  <p>
    <input type="radio" name="applyType" value="apply" checked>
    <font size="2">Apply</font> 
    <input type="radio" name="applyType" value="applyRslt" <%if("applyRslt".equals(applyType)){out.print("checked");}%>>
    <font size="2">Apply Result</font></p>
  <p>
    <input type="checkbox" onclick="activeButton()" name="andIPOCode" value="Y" <%if("Y".equals(andIPOCode)){out.print("checked");}%>>
    <font size="2">eIPO Code:</font> 
    <select name="ipoMasterId" size="1"><font size="2">
      <logic:present name="ipoRecordList"><logic:iterate id="ipoRecord" name="ipoRecordList" type="com.taifook.adminportal.web.ipo.dto.IPORecord"> 
	  <%haveRecord=true;%>
      <option value=<bean:write name="ipoRecord" property="ipoMasterId"/> <%if(ipoMasterId==ipoRecord.getIpoMasterId().longValue()){out.print("selected");}%>><bean:write name="ipoRecord" property="ipoCode"/>(<bean:write name="ipoRecord" property="ipoName"/>)</option> 
      </logic:iterate> </logic:present> 
	  </font>
    </select>
  </p>
  <p>
    <input type="checkbox" onclick="activeButton()" name="andAccount" value="Y" <%if("Y".equals(andAccount)){out.print("checked");}%>>
    <font size="2">Client A/C</font>
    <input type="text" name="accountId" value='<bean:write name="IPORequestForm" property="accountId"/>'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onClick="searchAction()" name="search" value="Search" disabled> <p>
	The format of client code should be the same as "02-XXXXXXX-30" or "02-XXXXXXX-33".
	
	<table><logic:present name="ipoResultStatusList"><logic:iterate id="ipoRsltSts" name="ipoResultStatusList" >
        <tr> 
          <Td><font size=2><bean:write name="ipoRsltSts" property="resultStatus"/>:
          </td>
          <Td><font size=2><bean:write name="ipoRsltSts" property="recordCount"/>
          </td>
        </tr></logic:iterate></logic:present>
		<logic:present name="ipoRquestList">
		<tr>
		<td><font size=2>Total:</td>
		<td><font size=2><bean:size id="sizeOfThis"  name="ipoRquestList"/><bean:write name="sizeOfThis"/></td>
		</tr></logic:present>
	</table>
  </p>   
  <hr>
  <%if("applyRslt".equals(applyType)){%>
 <logic:present name="ipoResultList"> 
  <table width="98%" border="1" cellspacing="0" >
    <tr bgcolor="#A5B7C5"> 
	  <td align=center width="2%"><font size="2">Select</font></td>
      <td align=center width="5%"><font size="2">Refno</font></td>
	  <td align=center width="6%"><font size="2">eIPOCode</font></td>
      <td align=center width="6%"><font size="2">ApplyQty</font></td>
      <td align=center width="7%"><font size="2">Amount</font></td>
      <td align=center width="6%"><font size="2">ACNo</font></td>
	  <td align=center width="5%"><font size="2">MIS_No</font></td>
      <td align=center width="11%"><font size="2">Application Status</font></td>
	  <td align=center width="5%"><font size="2"><a href="../jsp/eipo/rejectcode.jsp" target="_blank">rejectCode</a></font></td>
    </tr>
	<logic:iterate id="ipoResult" name="ipoResultList" type="com.taifook.adminportal.web.ipo.dto.IPOResult">
    <tr> 
	  <td><font size="2"><%if("0".equals(ipoResult.getPrgStatus())||"3".equals(ipoResult.getPrgStatus())){%><input type="checkbox" name="applyCodeStr"  value="<bean:write name="ipoResult" property="applyId"/>"><%}%>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoResult" property="applyId"/>&nbsp;</font></td>
	  <td><font size="2"><bean:write name="ipoResult" property="ipoCode"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoResult" property="applyQuantity_dsply"/>&nbsp;</font></td>
      <td><font size="2">$<bean:write name="ipoResult" property="dsptAmount_dsply"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoResult" property="accountId"/>&nbsp;</font></td>
	  <td><font size="2"><bean:write name="ipoResult" property="misRefCode"/>&nbsp;</font></td>
	  <td><font size="2">
	  <%if("0".equals(ipoResult.getPrgStatus())){out.print("Application in Progress");}
        else{
			if("3".equals(ipoResult.getPrgStatus())){out.print("Allotment Result is available");}
			  else{
				  if("2".equals(ipoResult.getPrgStatus())){out.print("MIS cancelled");}
				  else{out.print("MIS rejected");}
				  }
		   }%>
	  &nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoResult" property="rejectCode"/>&nbsp;</font></td>
    </tr>
	</logic:iterate> 
	  <tr> 
        <td colspan="12">
		<table width="100%" border="0">
        <tr align=center> 
          <td width="39%" align=left> Page <%=pCurr%> Of <%=nPage%>&nbsp;&nbsp;&nbsp;
              <input name="goPage" type="text" size="1">
            <input name="button" class="button1" type="button"  value="GoToPage" onclick="searchPage(form1.goPage.value,(form1.goPage.value-1)*20)"></td>
          <td width="61%" align=center> &nbsp; 
            <%if (pCurr>1){%>
            <input name="button" class="button1" type="button"  value="PrePage" onclick="searchPage(<%=pCurr-1%>,<%=(pCurr-2)*20%>)"> 
            <%}%>
            <%if (pCurr<nPage){%>
            <input name="button" class="button1" type="button"  value="NextPage" onclick="searchPage(<%=pCurr+1%>,<%=(pCurr)*20%>)"> 
            <%}%>
            <input type="hidden" name="firstResult"> <input type="hidden" name="currPage" > 
          </td>
        </tr>
        <tr > 
          <td align=left> <font size="2">Select All(This Page)</font> <input type="checkbox" name="selectAll"  onclick="selectAllapp()"> 
          </td>
          <td colspan="2" align=center> 
		    <input name="button" type="button" onClick="toAvlAction()"  value="To Avl"> 
            <input name="button" type="button" onClick="toPrgAction()"  value="To Prg"> 
			<%if(!"Y".equals(andAccount)){%>
            <input name="button" type="button" onClick="allAvlAction()"  value="SetAllToAvl"> 
            <input name="button" type="button" onClick="allPrgAction()"  value="SetAllToPrg">
			<%}%>
          </td>
        </tr>
        <tr> 
          <td align=left colspan="2" > 
		    1)Press "To Avl" button set the apply result 
            record to "Allotment Result is available". <br>
            2)Press "To Prg" button set the apply result record to "Application 
            in Progress". </td>
        </tr>
      </table>  

		  </td>
      </tr>
  </table>
</logic:present>
  <%
}
else
{
  %>
  <logic:present name="ipoRquestList">	
  <%  
  for (int p=1;p<nPage+1;p++)
  {
      nBgn = (p-1)*20;
      sBgn = Integer.toString(nBgn);
	  %>
  <table width="98%" border="1" cellspacing="0"  id="menu<%=p%>" style="position:absolute;visibility: <%if (p==1){%>visible;<%}else{%>hidden;<%}%>">
    <tr> 
      <td align=center width="5%"><font size="2">Refno</font></td>
	  <td align=center width="6%"><font size="2">eIPOCode</font></td>
      <td align=center width="6%"><font size="2">StockCode</font></td>
      <td align=center width="6%"><font size="2">ApplyQty</font></td>
      <td align=center width="8%"><font size="2">Amount</font></td>
      <td align=center width="7%"><font size="2">Mobile No</font></td>
      <td align=center width="10%"><font size="2">Email address</font></td>
      <td align=center width="11%"><font size="2">ACNo</font></td>
      <td align=center width="15%"><font size="2">ApplyTime</font></td>
      <td align=center width="5%"><font size="2">Application status</font></td>
    </tr>
	<logic:iterate id="ipoRequest" name="ipoRquestList" offset="<%=sBgn%>" length="20" type="com.taifook.adminportal.web.ipo.dto.IPORequest">
    <tr> 
	<%	  nIpoMasterId = ipoRequest.getIpoMasterId().longValue();%>
      <td><font size="2"><bean:write name="ipoRequest" property="applyId"/>&nbsp;</font></td>
	  <td><font size="2"><%=ipoMaintenanceDelegate.getIPORecord(nIpoMasterId).getIpoCode()%>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRequest" property="stockCode"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRequest" property="applyQuantity_dsply"/>&nbsp;</font></td>
      <td><font size="2">$<bean:write name="ipoRequest" property="dsptAmount_dsply"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRequest" property="telephone"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRequest" property="email"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRequest" property="accountId"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRequest" property="applyDate"/>&nbsp;</font></td>
      <td><font size="2"><bean:write name="ipoRequest" property="status"/>&nbsp;</font></td>
    </tr>
	</logic:iterate> 
	  <tr> 
        <td colspan="13">
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
<%}%>
</form>
<p>&nbsp; </p>
</body>
<script language="JavaScript">
<!--
if(<%=haveRecord%>==false)
{
   form1.andIPOCode.disabled=true;
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
		
	 form1.action="IPOListApplyAction.do";
	 document.form1.submit();
	}

	function searchPage(currpage,firstRslt)
	{ 
        if(!isInteger(currpage)||(currpage><%=nPage%>)||(currpage==<%=pCurr%>)||currpage<1)
		{
           alert("please fill in correct pageNo !");
	       return false;
		}
		form1.currPage.value=currpage;
		form1.firstResult.value=firstRslt;
		form1.action="IPOListApplyAction.do"; 
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
			
//-->
</script>
</html>
