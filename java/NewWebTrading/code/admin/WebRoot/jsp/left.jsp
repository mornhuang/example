<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="./js/checkurl.jsp"%>
<%@ include file="./js/openhelper.jsp"%>
<%@ page import="com.itsz.sht.dao.hibernate.model.AclUserProfile,com.itsz.sht.common.Consts" %>
<html>
<head>
<title></title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<s:url value='/css/01.css'/>" rel="stylesheet"
	type="text/css">
<script type="text/javascript">
	function ChangeParameters() {

		if (document.all.idParameters.style.display == "none") {
			document.all.treeflagParameters.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idParameters.style.display = "";
			return;
		}
		if (document.all.idParameters.style.display == "") {
			document.all.treeflagParameters.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idParameters.style.display = "none";
			return;
		}

	}
		function ChangeEipo() {

			if (document.all.idEipo.style.display == "none") {
				document.all.treeflagEipo.src = "<%=basePath%>/jsp/images/tridown.gif";
				document.all.idEipo.style.display = "";
				return;
			}
			if (document.all.idEipo.style.display == "") {
				document.all.treeflagEipo.src = "<%=basePath%>/jsp/images/trileft.gif";
				document.all.idEipo.style.display = "none";
				return;
			}
	}
		function ChangeOnlineUser() {

			if (document.all.idOnlineUser.style.display == "none") {
				document.all.treeflagOnlineUser.src = "<%=basePath%>/jsp/images/tridown.gif";
				document.all.idOnlineUser.style.display = "";
				return;
			}
			if (document.all.idOnlineUser.style.display == "") {
				document.all.treeflagOnlineUser.src = "<%=basePath%>/jsp/images/trileft.gif";
				document.all.idOnlineUser.style.display = "none";
				return;
			}
	}

		function ChangeUserAction() {

			if (document.all.idUserAction.style.display == "none") {
				document.all.treeflagUserAction.src = "<%=basePath%>/jsp/images/tridown.gif";
				document.all.idUserAction.style.display = "";
				return;
			}
			if (document.all.idUserAction.style.display == "") {
				document.all.treeflagUserAction.src = "<%=basePath%>/jsp/images/trileft.gif";
				document.all.idUserAction.style.display = "none";
				return;
			}
		}
	function ChangeAdministrator() {

		if (document.all.idAdministrator.style.display == "none") {
			document.all.treeflagAdministrator.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idAdministrator.style.display = "";
			return;
		}
		if (document.all.idAdministrator.style.display == "") {
			document.all.treeflagAdministrator.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idAdministrator.style.display = "none";
			return;
		}
	}
	function ChangeUser() {

		if (document.all.idUser.style.display == "none") {
			document.all.treeflagUser.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idUser.style.display = "";
			return;
		}
		if (document.all.idUser.style.display == "") {
			document.all.treeflagUser.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idUser.style.display = "none";
			return;
		}
	}

	function ChangeRtqApp() {

		if (document.all.idRtqApp.style.display == "none") {
			document.all.treeflagRtqApp.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idRtqApp.style.display = "";
			return;
		}
		if (document.all.idRtqApp.style.display == "") {
			document.all.treeflagRtqApp.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idRtqApp.style.display = "none";
			return;
		}
	}

	function ChangeServiceProduct() {
		if (document.all.idServiceProduct.style.display == "none") {
			document.all.treeflagServiceProduct.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idServiceProduct.style.display = "";
			return;
		}
		if (document.all.idServiceProduct.style.display == "") {
			document.all.treeflagServiceProduct.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idServiceProduct.style.display = "none";
			return;
		}
	}
	function ChangeAutoProcess() {
		if (document.all.idAutoProcess.style.display == "none") {
			document.all.treeflagAutoProcess.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idAutoProcess.style.display = "";
			return;
		}
		if (document.all.idAutoProcess.style.display == "") {
			document.all.treeflagAutoProcess.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idAutoProcess.style.display = "none";
			return;
		}
	}

	function ChangeReport() {
		if (document.all.idReport.style.display == "none") {
			document.all.treeflagReport.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idReport.style.display = "";
			return;
		}
		if (document.all.idReport.style.display == "") {
			document.all.treeflagReport.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idReport.style.display = "none";
			return;
		}
	}
	function ChangeNODataExport() {
		if (document.all.idNODataExport.style.display == "none") {
			document.all.treeflagNODataExport.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idNODataExport.style.display = "";
			return;
		}
		if (document.all.idNODataExport.style.display == "") {
			document.all.treeflagNODataExport.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idNODataExport.style.display = "none";
			return;
		}
	}

	function ChangeOtherOper() {
		if (document.all.idOtherOper.style.display == "none") {
			document.all.treeflagOtherOper.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idOtherOper.style.display = "";
			return;
		}
		if (document.all.idOtherOper.style.display == "") {
			document.all.treeflagOtherOper.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idOtherOper.style.display = "none";
			return;
		}
	}
	
	function ChangeOtherOper() {
		if (document.all.idOtherOper.style.display == "none") {
			document.all.treeflagOtherOper.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idOtherOper.style.display = "";
			return;
		}
		if (document.all.idOtherOper.style.display == "") {
			document.all.treeflagOtherOper.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idOtherOper.style.display = "none";
			return;
		}
	}

	function ChangeGoExport() {
		if (document.all.idGoExport.style.display == "none") {
			document.all.treeflagGoExport.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idGoExport.style.display = "";
			return;
		}
		if (document.all.idGoExport.style.display == "") {
			document.all.treeflagGoExport.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idGoExport.style.display = "none";
			return;
		}
	}
	

	function ChangeLogout() {
		if (document.all.idLogout.style.display == "none") {
			document.all.treeflagLogout.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idLogout.style.display = "";
			return;
		}
		if (document.all.idLogout.style.display == "") {
			document.all.treeflagLogout.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idLogout.style.display = "none";
			return;
		}
	}
</script>
  <%
  	AclUserProfile sessionUser=(AclUserProfile)session.getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
  	pageContext.setAttribute("roles",sessionUser.getAclRole().getFunctionPrmisnMap());
  	pageContext.setAttribute("sessionUser",sessionUser);
  	%>
</head>
<body bgcolor="#F4F7FB" text="#000000" leftmargin="0" topmargin="0">
<div align="center"></div>
<div align="center"></div>
<table width="100%">
	<tr><td align="left"><FONT color="#7E91BE">&nbsp;&nbsp;USERID:&nbsp;<strong>${sessionUser.lognId}</strong></FONT></td></tr>
</table>
<div align="center">
<table width="100%" border="0" align="left" cellpadding="0"
	cellspacing="0">
	<c:if test="${roles.Parameters_Maintenance_menu!=null}">
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagParameters" /></div>
		</td>
		<td width="87%">
		<p onclick=ChangeParameters(); style="cursor: hand"><font color="#336699">&nbsp;System Configuration</font>
		</td>
	</tr>
	</c:if>
	<tr>
		<td></td>
		<td>
		<div id="idParameters" style="display: none;">
		<table cellpadding="0" cellspacing="0">
		<c:if test="${roles.Add_Parameter_menu!=null}">
			<tr>
				<td width="87%"><html:link action="addparameterinit.do" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Add New Configuration</html:link></td>
			</tr>
			</c:if>
			<c:if test="${roles.Web_Parameter_menu!=null}">
			<tr>
				<td width="87%"><html:link action="queryparameter.do?classid=5" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;View Configuration</html:link></td>
			</tr>
			</c:if>
		</table>
		</div>
		</td>
	</tr>

<c:if test="${roles.Eipo_Manager_menu!=null}">
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagEipo"></div>
		</td>
		<td width="87%">
		<p onclick=ChangeEipo(); style="cursor: hand"><font color="#336699">&nbsp;Eipo Manager</font>
		</td>
	</tr>
	</c:if>
	<tr>
		<td></td>
		<td>
		<div id="idEipo" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<c:if test="${roles.Eipo_Master_File_menu!=null}">
			<tr>
				<td width="87%"><html:link action="jsp/eipo/fileInput.jsp" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Eipo Master File</html:link></td>
			</tr>
			</c:if>
			<c:if test="${roles.Eipo_Criteria_File_menu!=null}">
			<tr>
				<td width="87%"><html:link action="jsp/eipo/fileQtyAmtInput.jsp" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Eipo Criteria.File</html:link></td>
			</tr>
			</c:if>
			<c:if test="${roles.Eipo_Request_Maintenance_menu!=null}">
			<tr>
				<td width="87%"><html:link action="/eipo/IPOQueryListAction.do" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Eipo Request Maintenance</html:link></td>
			</tr>
			</c:if>
			<c:if test="${roles.Eipo_Result_Maintenance_menu!=null}">
			<tr>
				<td width="87%"><html:link action="jsp/eipo/fileResultInput.jsp" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Eipo Result Maintenance</html:link></td>
			</tr>
			</c:if>
			<c:if test="${roles.Eipo_View_menu!=null}">
			<tr>
				<td width="87%"><html:link action="/eipo/IPOQueryAllAction.do" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Eipo View</html:link></td>
			</tr>
			</c:if>
			<c:if test="${roles.Eipo_Apply_View_menu!=null}">
			<tr>
				<td width="87%"><html:link action="/eipo/IPOListApplyAction.do" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Eipo Apply View</html:link></td>
			</tr>
			</c:if>
			<c:if test="${roles.Eipo_List_menu!=null}">
			<tr>
				<td width="87%"><html:link action="/eipo/IPOAEQueryAllAction.do" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Eipo List</html:link></td>
			</tr>
			</c:if>
			<c:if test="${roles.Eipo_Application_Status_menu!=null}">
			<tr>
				<td width="87%"><html:link action="/eipo/IPOAEQueryAction.do" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Eipo Application Status</html:link></td>
			</tr>
			</c:if>
		</table>
		</div>
		</td>
	</tr>

<c:if test="${roles.Online_User_menu!=null}">
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagOnlineUser" /></div>
		</td>
		<td width="87%">
		<p onclick=ChangeOnlineUser(); style="cursor: hand"><font color="#336699">&nbsp;Online User</font>
		</td>
	</tr>
	</c:if>
	<tr>
		<td></td>
		<td>
		<div id="idOnlineUser" style="display: none;">
		<table cellpadding="0" cellspacing="0">
		<c:if test="${roles.Online_User_Chart_menu!=null}">
			<tr>
				<td width="87%"><html:link action="onlineuserchart.do" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Online User Chart</html:link></td>
			</tr>
			</c:if>
			<c:if test="${roles.Online_User_Log_menu!=null}">
			<tr>
				<td width="87%"><html:link action="onlineuserdetail.do" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Online User Log</html:link></td>
			</tr>
			</c:if>
		</table>
		</div>
		</td>
	</tr>

<c:if test="${roles.User_Action_menu!=null}">
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagUserAction" /></div>
		</td>
		<td width="87%">
		<p onclick=ChangeUserAction(); style="cursor: hand"><font color="#336699">&nbsp;User Action</font>
		</td>
	</tr>
	</c:if>
	<tr>
		<td></td>
		<td>
		<div id="idUserAction" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<c:if test="${roles.User_Action_Chart_menu!=null}">
			<tr>
				<td width="87%"><html:link action="useractionchart.do" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;User Action Chart</html:link></td>
			</tr>
			</c:if>
			<c:if test="${roles.User_Action_Detail_menu!=null}">
			<tr>
				<td width="87%"><html:link action="useractiondetail.do" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;User Action Detail</html:link></td>
			</tr>
			</c:if>
		</table>
		</div>
		</td>
	</tr>

   
	<c:if test="${roles.Administrator_Manager_menu!=null}">
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagAdministrator" /></div>
		</td>
		<td width="87%">
		<p onclick=ChangeAdministrator(); style="cursor: hand"><font color="#336699">&nbsp;Account Access Control</font>
		</td>
	</tr>
  </c:if>
	<tr>
		<td></td>
		<td>
		<div id="idAdministrator" style="display: none;">
		<table cellpadding="0" cellspacing="0">
		<c:if test="${roles.Acl_UserProfile_menu!=null}">
			<tr>
				<td width="87%"><html:link action="listAclUserProfile.do?method=listAclUserProfile" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Account Maintenance</html:link></td>
			</tr>
		 </c:if>
			<c:if test="${roles.Acl_Role_menu!=null}">
			<tr>
				<td width="87%"><html:link action="listAclRole.do?method=listAclRole" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Role Maintenance</html:link></td>
			</tr>
			</c:if>
		
			<c:if test="${roles.Change_Password_menu!=null}">
			<tr>
				<td width="87%"><html:link action="goChangePassWord.do?method=goChangePassWord" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Change Password</html:link></td>
			</tr>
			</c:if>
			
			
            <c:if test="${roles.Reset_Password_menu!=null}">
			<tr>
				<td width="87%"><html:link action="goResetPassWord.do?method=goResetPassWord" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Reset Password</html:link></td>
			</tr>
           </c:if>

			<c:if test="${roles.User_Seeion_Detail_menu!=null}">
			<tr>
				<td width="87%"><html:link action="jsp/usersessiondetail.jsp" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;User Seeion Detail</html:link></td>
			</tr>
			</c:if>
		</table>
		</div>
		</td>
	</tr>

<c:if test="${roles.User_Maintenance_menu!=null}">
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagUser" /></div>
		</td>
		<td width="87%">
		<p onclick=ChangeUser(); style="cursor: hand"><font color="#336699">&nbsp;User Maintenance</font>
		</td>
	</tr>
	</c:if>
	<tr>
		<td></td>
		<td>
		<div id="idUser" style="display: none;">
		<table cellpadding="0" cellspacing="0">
		<c:if test="${roles.User_Profile_Maintenance_menu!=null}">
			<tr>
				<td width="87%"><html:link action="listUserProfile.do?method=listUserProfile" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;User Profile</html:link></td>
			</tr>
			</c:if>
		</table>
		</div>
		</td>
	</tr>

<c:if test="${roles.Rtq_Maintenance_menu!=null}">
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagRtqApp" /></div>
		</td>
		<td width="87%">
		<p onclick=ChangeRtqApp(); style="cursor: hand"><font color="#336699">&nbsp;RTQ Maintenance</font>
		</td>
	</tr>
	</c:if>
	<tr>
		<td></td>
		<td>
		<div id="idRtqApp" style="display: none;">
		<table cellpadding="0" cellspacing="0">
		<c:if test="${roles.Rtq_App_Maintenance_menu!=null}">
			<tr>
				<td width="87%"><html:link action="listRtqApplication.do?method=listRtqApplication" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;RTQ App Maintenance</html:link></td>
			</tr>
       </c:if>
       <c:if test="${roles.Rtq_Acc_Maintenance_menu!=null}">
			<tr>
				<td width="100%"><html:link action="listRTQAccountView.do?method=listRTQAccountView" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;RTQ Acc Maintenance</html:link></td>
			</tr>
			</c:if>
		</table>
		</div>
		</td>
	</tr>

<c:if test="${roles.Service_Product_menu!=null}">
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagServiceProduct"></div>
		</td>
		<td width="87%">
		<p onclick=ChangeServiceProduct(); style="cursor: hand"><font color="#336699">&nbsp;Service Product</font>
		</td>
	</tr>
	</c:if>
	<tr>
		<td></td>
		<td>
		<div id="idServiceProduct" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<c:if test="${roles.Service_Product_Maintenance_menu!=null}">
			<tr>
				<td width="100%"><html:link action="listProduct.do?method=listProduct" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Service Product Maintenance</html:link></td>
			</tr>
          </c:if>
		</table>
		</div>
		</td>
	</tr>
	
	<c:if test="${roles.Auto_Process_menu!=null}">
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagAutoProcess"></div>
		</td>
		<td width="87%">
		<p onclick=ChangeAutoProcess(); style="cursor: hand"><font color="#336699">&nbsp;Auto Process</font>
		</td>
	</tr>
    </c:if>
	<tr>
		<td></td>
		<td>
		<div id="idAutoProcess" style="display: none;">
		<table cellpadding="0" cellspacing="0">
		<c:if test="${roles.Auto_Process_Purchase_menu!=null}">
			<tr>
				<td width="100%"><html:link action="showProcessAuto.do?method=showProcessAuto" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Process Auto Purchase</html:link></td>
			</tr>
		</c:if>
		<c:if test="${roles.Auto_Purchase_Report_menu!=null}">
			<tr>
				<td width="100%"><html:link action="autoPurchaseEnquiry.do?method=autoPurchaseEnquiry" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Auto Purchase Report</html:link></td>
			</tr>
		</c:if>
		<c:if test="${applicationScope.memoDebitSystem eq 'MANUAL' && roles.RTQ_Memo_Debit_menu!=null}">
			<tr>
				<td width="100%"><html:link action="rtqMemoDebitList.do?method=rtqMemoDebitList" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;RTQ Memo Debit(MANUAL)</html:link></td>
			</tr>
		</c:if>
		<c:if test="${roles.Day_End_Process_menu!=null}">
			<tr>
				<td width="100%"><html:link action="showMisDayEndProcessingFlag.do?method=showMisDayEndProcessingFlag" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Day End Processing Stauts</html:link></td>
			</tr>
			</c:if>
		</table>
		</div>
		</td>
	</tr>
	
	<c:if test="${roles.Report_menu!=null}">
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagReport"></div>
		</td>
		<td width="87%">
		<p onclick=ChangeReport(); style="cursor: hand"><font color="#336699">&nbsp;Report</font>
		</td>
	</tr>
    </c:if>
	<tr>
		<td></td>
		<td>
		<div id="idReport" style="display: none;">
		<table cellpadding="0" cellspacing="0">
		<c:if test="${roles.Monthly_Purchase_menu!=null}">
			<tr>
				<td width="100%"><html:link action="monthlyPurchaseEnquiry.do?method=monthlyPurchaseEnquiry" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Monthly Purchase Enquiry</html:link></td>
			</tr>
			</c:if>
		<c:if test="${roles.Reserve_Renewal_menu!=null}">
			<tr>
				<td width="100%"><html:link action="reserveAndRenewalEnquiry.do?method=reserveAndRenewalEnquiry" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Reserve And Renewal Enquiry</html:link></td>
			</tr>
			</c:if>
		</table>
		</div>
		</td>
	</tr>
	
<c:if test="${roles.Other_Operation_menu!=null}">
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagOtherOper"></div>
		</td>
		<td width="87%">
		<p onclick=ChangeOtherOper(); style="cursor: hand"><font color="#336699">&nbsp;Other Operation</font>
		</td>
	</tr>
	</c:if>
	<tr>
		<td></td>
		<td>
		<div id="idOtherOper" style="display: none;">
		<table cellpadding="0" cellspacing="0">
		<c:if test="${roles.Help_Manual_menu!=null}">
			<tr>
				<td width="100%"><html:link action="helper.jsp" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Help Manual</html:link></td>
			</tr>
			</c:if>
			<c:if test="${roles.Return_Home_menu!=null}">
			<tr>
				<td width="100%"><html:link href="jsp/index.html" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Return Home</html:link></td>
			</tr>
			</c:if>
		</table>
		</div>
		</td>
	</tr>

	<!-- zcy -->
   <c:if test="${roles.NO_Date_Export_menu!=null}">	
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagGoExport"></div>
		</td>
		<td width="87%">
		<p onclick=ChangeGoExport(); style="cursor: hand"><font color="#336699"> &nbsp;User Notification</font>
		</td>
	</tr>
  </c:if>
	<tr>
		<td></td>
		<td>
		<div id="idGoExport" style="display: none;">
		<table cellpadding="0" cellspacing="0">
		<c:if test="${roles.NO_Date_Export_Maintenance_menu!=null}">
			<tr>
				<td width="100%"><html:link href="jsp/exportUserNotification.jsp" target="right" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Export User Notification Data</html:link></td>
			</tr>
		</c:if>
		</table>
		</div>
		</td>
	</tr>

	
	<tr>
		<td width="13%">
		<div align="right"><img src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10" name="treeflagLogout"></div>
		</td>
		<td width="87%">
		<p onclick=ChangeLogout(); style="cursor: hand"><font color="#336699">&nbsp;Logout System</font>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<div id="idLogout" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%"><html:link action="logout.do" target="_parent" style="cursor: hand;TEXT-DECORATION:none">&nbsp;&nbsp;Logout System</html:link></td>
			</tr>
		</table>
		</div>
		</td>
	</tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
</div>
</body>
</html>

