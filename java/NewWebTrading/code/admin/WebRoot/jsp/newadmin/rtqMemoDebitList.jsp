<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ page import="com.itsz.sht.common.auto.ProcessStatus"%>
<%@ page import="java.util.Iterator,java.util.List" %>
<%@ page import="com.taifook.adminportal.common.util.page.Page"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>
<%@ taglib uri="/WEB-INF/deftag-1.0.tld" prefix="def"%>
<html>
<head>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/wpCalendar.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/jquery-1.4.4.js"></script>
<script type="text/javascript">
	function doSubmit(){
		if(($("#startDate").val()!="" && formatDates($("#startDate").val())>comDate()) || ($("#endDate").val()!="" && formatDates($("#endDate").val())>comDate())){
		    alert("不能选择未来的时间!");
		    return false;
        }else if($("#startDate").val()!="" && $("#endDate").val()!="" && formatDates($("#startDate").val())>formatDates($("#endDate").val())){
            alert("结束时间不能小于开始时间!");
            return false;
        }
		document.userProductPaymentForm.btnSubmit.disabled=true;
		document.userProductPaymentForm.debit.disabled=true;
		document.userProductPaymentForm.btnReset.disabled=true;
		document.userProductPaymentForm.action="rtqMemoDebitList.do?method=rtqMemoDebitList";
		document.userProductPaymentForm.submit();
	}

   function comDate(){
	    var year=new Date().getFullYear();
	    var month=new Date().getMonth()+1;
	    var day=new Date().getDate();
	   return year+"-"+(month>9?month:("0"+month))+"-"+(day>9?day:("0"+day));
	   }

   function formatDates(dates){
	   var dates=dates.split("-");
	   var year=dates[0];
	   var month=dates[1];
	   var day=dates[2];
	   return year+"-"+(month>9?month:("0"+month))+"-"+(day>9?day:("0"+day));
	   }
	
	function clearDate(){
		$("#startDate").val("");
		$("#endDate").val("");
		}
	
	function selectsAll(){
		if($("#selectAll").attr("checked")==true){
			$("input[name='check']").attr("checked", true); 
			}else {
				$("input[name='check']").attr("checked", false); 
				}
		}
	
	function memoDebit(){
		var check = $("input:checked");//得到所有被选中的checkbox)
		if(check.length==0||(check.length==1&&$("#selectAll").attr("checked")==true)){
            alert("请选择需要确认的数据!");
		}else if(check.length!=0&&!(check.length==1&&$("#selectAll").attr("checked")==true)){
		    if(confirm("确认成功扣款吗？")){
		    	document.userProductPaymentForm.btnSubmit.disabled=true;
				document.userProductPaymentForm.debit.disabled=true;
				document.userProductPaymentForm.btnReset.disabled=true;
			  	document.userProductPaymentForm.action="rtqMemoDebitUpdate.do?method=rtqMemoDebitUpdate";
		       	document.userProductPaymentForm.submit();
		   }
		}
	}
</script>
</head>

<body bgcolor="#ffffff" topmargin="0" leftmargin="0" marginwidth="0"
	marginheight="0">
<form name="userProductPaymentForm" method="POST">
<table border=0 width=100% cellspacing=0 cellpadding=2 align="left">
	<tr>
		<td height="23" bgcolor="#A5B7C5"><font color="#ffffff"><b>RTQMemoDebit(MANUAL)</b></font><font color="red"> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<b>${message}</b></font></td>
	</tr>
	<tr>
		<td><!--************************************** main **************************************-->
		<table width="100%" align="center" cellspacing=2>
			<tr align="left">
				<td>
					Period From:<input type="text" id="startDate"  name="startDate" value="${userProductPaymentForm.startDate}" onFocus="showCalendar(this);" readonly="readonly"  style="width:90" />
				  &nbsp;&nbsp;&nbsp; To: <input type="text" id="endDate"  onFocus="showCalendar(this)" name="endDate" value="${userProductPaymentForm.endDate}" readonly="readonly"   style="width:90" />
				 &nbsp;&nbsp;&nbsp; <input TYPE="button" id="btnSubmit" name="btnSubmit" value="Submit" onclick="return doSubmit();"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<INPUT TYPE="button" id="btnReset" name="btnReset" value="Reset" onclick="clearDate();">
				</td>
			</tr>
		</table>
		</td>
	</tr>
	
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="1" 
			 bgcolor="#A5B7C5" align="left">
				<tr bgcolor="#A5B7C5">
				    <td width="5%" nowrap><input name="selectAll"  id="selectAll" type="checkbox"  onclick="selectsAll();"/></td>
					<td width="5%" nowrap>
					<div align="center"><font color="#FFFFFF">UserProductPayID</font></div>
					</td>
					<td width="10%" nowrap>
					<div align="center"><font color="#FFFFFF">ClientID</font></div>
					</td>
					<td width="10%" nowrap>
					<div align="center"><font color="#FFFFFF">ProductID</font></div>
					</td>
					<td width="10%" nowrap>
					<div align="center"><font color="#FFFFFF">PriceHkd</font></div>
					</td>
					<td width="10%" nowrap>
					<div align="center"><font color="#FFFFFF">DefDebtAcc</font></div>
					</td>
					<td width="20%" nowrap>
					<div align="center"><font color="#FFFFFF">InitDate</font></div>
					</td>
				</tr>
				<%
  try{
    	Page testpage=(Page)request.getAttribute("page");
   		List result=(List)testpage.getThisPageElements();
   		pageContext.setAttribute("result",result);
       }catch(java.lang.Exception es){}
    
%>
			<c:forEach var="memoDebit" items="${result}" varStatus="var">
				<c:choose>
					<c:when test="${var.index%2==0}">  
						<tr bgcolor="#ffffff">
					</c:when>
					<c:otherwise><tr></c:otherwise>
				</c:choose>
				    <td><input id="check" name="check" type="checkbox" value=${memoDebit.usrProdPayId}/></td>
				    <td>&nbsp;${memoDebit.usrProdPayId}</td>
			        <td>&nbsp;${memoDebit.clntId}</td>
			        <td>&nbsp;${memoDebit.prodId}</td>
					<td>&nbsp;${memoDebit.priceHkd}</td>
					<td>&nbsp;${memoDebit.defDebtAcc}</td>
					<td>&nbsp;${memoDebit.initDate}</td>
				 </tr>
			</c:forEach>
			 
			 	<tr>
		<td colspan="1"><input type=button id ="debit" name="debit" value="Confirm" onclick="memoDebit();"/></td>
		<td colspan="6">
		<div align="center"><c:catch>
			<%Page testpage=(Page)request.getAttribute("page");%>
			<def:ymshowPageSplit currentPage="<%=testpage.getThisPageNumber()%>"
				maxPage="<%=testpage.getLastPageNumber()%>"
				totalSize="<%=testpage.getTotalNumberOfElements()%>"
				url="<%=request.getContextPath()+"/rtqMemoDebitList.do?method=rtqMemoDebitList"%>"
				parameters="${URLParam}" currentPageName="currentpage" />
		</c:catch></div>
		</td>
	</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
