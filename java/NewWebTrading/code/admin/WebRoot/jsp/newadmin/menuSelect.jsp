<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<s:url value='/css/01.css'/>" rel="stylesheet"
	type="text/css">
	<script type="text/javascript">
	function checkParametersAll(){
         var a=document.getElementById("Parameters_Maintenance_menu");
         var a1=document.getElementById("Add_Parameter_menu");
         var a2=document.getElementById("Web_Parameter_menu");
         a1.checked=a.checked;
         a2.checked=a.checked;
		}
	function checkEipoAll(){
        var a=document.getElementById("Eipo_Manager_menu");
        var a1=document.getElementById("Eipo_Master_File_menu");
        var a2=document.getElementById("Eipo_Criteria_File_menu");
        var a3=document.getElementById("Eipo_Request_Maintenance_menu");
        var a4=document.getElementById("Eipo_Result_Maintenance_menu");
        var a5=document.getElementById("Eipo_View_menu");
        var a6=document.getElementById("Eipo_Apply_View_menu");
        var a7=document.getElementById("Eipo_List_menu");
        var a8=document.getElementById("Eipo_Application_Status_menu");
        a1.checked=a.checked;
        a2.checked=a.checked;
        a3.checked=a.checked;
        a4.checked=a.checked;
        a5.checked=a.checked;
        a6.checked=a.checked;
        a7.checked=a.checked;
        a8.checked=a.checked;
		}

	function checkOnlineUserAll(){
        var a=document.getElementById("Online_User_menu");
        var a1=document.getElementById("Online_User_Chart_menu");
        var a2=document.getElementById("Online_User_Log_menu");
        a1.checked=a.checked;
        a2.checked=a.checked;
		}
	
	function checkAdministratorAll(){
        var a=document.getElementById("Administrator_Manager_menu");
        var a1=document.getElementById("Acl_UserProfile_menu");
        var a2=document.getElementById("Acl_Role_menu");
        var a3=document.getElementById("Change_Password_menu");
        var a4=document.getElementById("Reset_Password_menu");
        a1.checked=a.checked;
        a2.checked=a.checked;
        a3.checked=a.checked;
        a4.checked=a.checked;
		}

	function checkUserMaintenanceAll(){
        var a=document.getElementById("User_Maintenance_menu");
        var a1=document.getElementById("User_Profile_Maintenance_menu");
        a1.checked=a.checked;
		}

	function checkRtqAll(){

      var a=document.getElementById("Rtq_Maintenance_menu");
      var a1=document.getElementById("Rtq_App_Maintenance_menu");   
      var a2=document.getElementById("Rtq_Acc_Maintenance_menu");   
       a1.checked=a.checked;
       a2.checked=a.checked;
		}
	
	function checkProductAll(){
        var a=document.getElementById("Service_Product_menu");
        var a1=document.getElementById("Service_Product_Maintenance_menu"); 
        a1.checked=a.checked;
		}

	function checkAutoProcessAll(){
        var a=document.getElementById("Auto_Process_menu");
        var a1=document.getElementById("Auto_Process_Purchase_menu"); 
        var a2=document.getElementById("Auto_Purchase_Report_menu");
        var a3=document.getElementById("RTQ_Memo_Debit_menu");  
        var a4=document.getElementById("Day_End_Process_menu"); 
        a1.checked=a.checked;
        a2.checked=a.checked;
        if(a3 != null){
            a3.checked=a.checked;
        }
        a4.checked=a.checked;
		}

	function checkReportAll(){
        var a=document.getElementById("Report_menu");
        var a1=document.getElementById("Monthly_Purchase_menu"); 
        var a2=document.getElementById("Reserve_Renewal_menu"); 
        a1.checked=a.checked;
        a2.checked=a.checked;
		}
	
	function checkNoDateExport(){
        var a=document.getElementById("NO_Date_Export_menu");
        var a1=document.getElementById("NO_Date_Export_Maintenance_menu");
        a1.checked=a.checked;
       
		}
		
	 function checkData(ob,father){
		  var a=document.getElementById(father);
		  if(ob.checked==true){
            a.checked=true;
		  }else{
			if(father=="Parameters_Maintenance_menu" && document.getElementById("Add_Parameter_menu").checked==false 
					&& document.getElementById("Web_Parameter_menu").checked==false){
				document.getElementById("Parameters_Maintenance_menu").checked = false; 
			}else if(father=="Administrator_Manager_menu" 
					&& document.getElementById("Acl_UserProfile_menu").checked==false 
					&& document.getElementById("Acl_Role_menu").checked==false 
					&& document.getElementById("Change_Password_menu").checked==false
					&& document.getElementById("Reset_Password_menu").checked==false
					){
				document.getElementById("Administrator_Manager_menu").checked = false;
			}else if(father=="User_Maintenance_menu" && document.getElementById("User_Profile_Maintenance_menu").checked==false){ 
				document.getElementById("User_Maintenance_menu").checked = false;
			}else if(father=="Rtq_Maintenance_menu" && document.getElementById("Rtq_App_Maintenance_menu").checked==false 
					&& document.getElementById("Rtq_Acc_Maintenance_menu").checked==false){
				document.getElementById("Rtq_Maintenance_menu").checked = false;
			}else if(father=="Service_Product_menu" && document.getElementById("Service_Product_Maintenance_menu").checked==false){
				document.getElementById("Service_Product_menu").checked = false;
			}else if(father=="Auto_Process_menu" 
				&& document.getElementById("Auto_Process_Purchase_menu").checked==false
				&& document.getElementById("Auto_Purchase_Report_menu").checked==false
				&& (document.getElementById("RTQ_Memo_Debit_menu")!=null && document.getElementById("RTQ_Memo_Debit_menu").checked==false)
				&& document.getElementById("Day_End_Process_menu").checked==false
					){
				document.getElementById("Auto_Process_menu").checked = false;
			}else if(father=="Report_menu" && document.getElementById("Monthly_Purchase_menu").checked==false
					 && document.getElementById("Reserve_Renewal_menu").checked==false){
				document.getElementById("Report_menu").checked = false;
			}else if(father=="NO_Date_Export_menu" && document.getElementById("NO_Date_Export_Maintenance_menu").checked==false){
				document.getElementById("NO_Date_Export_menu").checked = false;
			}
		  }
	 }
	</script>
</head>
<body bgcolor="#F4F7FB" text="#000000" leftmargin="0" topmargin="0">
<div align="center"></div>
<div align="center"></div>
<table width="100%">
	<tr>
		<td align="center"></td>
	</tr>
</table>
<div align="center">
<table width="100%" border="0" align="left" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="45">&nbsp;</td>
		<td height="45"><input type="checkbox" id="checkAllId" onclick="CheckAll();" />&nbsp;&nbsp;<strong><font color="#996600"><em>Pls
		Select Function</em></font></strong></td>
	</tr>
	<tr>
		<td width="13%">
		<div align="right"><input type="checkbox" name="functionId" id="Parameters_Maintenance_menu"
			value="Parameters_Maintenance_menu"  onclick="checkParametersAll();"/> <img
			src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10"
			name="treeflagParameters" /></div>
		</td>
		<td width="87%">
		<p onclick=ChangeParameters(); style="cursor: hand">
		<font color="#336699">&nbsp;System Configuration</font>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<div id="idParameters" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="87%"><input type="checkbox" name="functionId" id="Add_Parameter_menu"
					value="Add_Parameter_menu" onclick='checkData(this,"Parameters_Maintenance_menu")'/>&nbsp;&nbsp;Add New Configuration</td>
			</tr>
			<tr>
				<td width="87%"><input type="checkbox" name="functionId" id="Web_Parameter_menu"
					value="Web_Parameter_menu" onclick='checkData(this,"Parameters_Maintenance_menu")'/>&nbsp;&nbsp;View Configuration</td>
			</tr>
		</table>
		</div>
		</td>
	</tr>
	
	<tr>
		<td width="13%">
		<div align="right"><input type="checkbox" name="functionId" id="Administrator_Manager_menu"
			value="Administrator_Manager_menu"  onclick="checkAdministratorAll();"/> <img
			src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10"
			name="treeflagAdministrator" /></div>
		</td>
		<td width="87%">
		<p onclick=ChangeAdministrator(); style="cursor: hand">
		<font color="#336699">&nbsp;Account Access Control</font>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<div id="idAdministrator" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="87%"><input type="checkbox" name="functionId" id="Acl_UserProfile_menu"
					value="Acl_UserProfile_menu"  onclick='checkData(this,"Administrator_Manager_menu")'/>&nbsp;&nbsp;Account Maintenance</td>
			</tr>
			<tr>
				<td width="87%"><input type="checkbox" name="functionId" id="Acl_Role_menu"
					value="Acl_Role_menu" onclick='checkData(this,"Administrator_Manager_menu")'/>&nbsp;&nbsp;Role Maintenance</td>
			</tr>
			<tr>
				<td width="87%"><input type="checkbox" name="functionId" id="Change_Password_menu"
					value="Change_Password_menu" onclick='checkData(this,"Administrator_Manager_menu")'/>&nbsp;&nbsp;Change Password</td>
			</tr>
			<tr>
				<td width="87%"><input type="checkbox" name="functionId" id="Reset_Password_menu"
					value="Reset_Password_menu" onclick='checkData(this,"Administrator_Manager_menu")'/>&nbsp;&nbsp;Reset Password</td>
			</tr>
		</table>
		</div>
		</td>
	</tr>
	
	
	<tr>
		<td width="13%">
		<div align="right"><input type="checkbox" name="functionId" id="User_Maintenance_menu"
			value="User_Maintenance_menu"  onclick="checkUserMaintenanceAll();"/> <img
			src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10"
			name="treeflagUser" /></div>
		</td>
		<td width="87%">
		<p onclick=ChangeUser(); style="cursor: hand">
		<font color="#336699">&nbsp;User Maintenance</font>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<div id="idUser" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="87%"><input type="checkbox" name="functionId" id="User_Profile_Maintenance_menu"
					value="User_Profile_Maintenance_menu"  onclick='checkData(this,"User_Maintenance_menu")'/>&nbsp;&nbsp;User Profile</td>
			</tr>
		</table>
		</div>
		</td>
	</tr>
	

	<tr>
		<td width="13%">
		<div align="right"><input type="checkbox" name="functionId"  id="Rtq_Maintenance_menu"
			value="Rtq_Maintenance_menu" onclick="checkRtqAll();"/> <img
			src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10"
			name="treeflagRtqApp" /></div>
		</td>
		<td width="87%">
		<p onclick=ChangeRtqApp(); style="cursor: hand">
		<font color="#336699">&nbsp;RTQ Maintenance</font>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<div id="idRtqApp" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="87%"><input type="checkbox" name="functionId" id="Rtq_App_Maintenance_menu"
					value="Rtq_App_Maintenance_menu" onclick='checkData(this,"Rtq_Maintenance_menu")'/>&nbsp;&nbsp;RTQ App Maintenance</td>
			</tr>
			<tr>
				<td width="87%"><input type="checkbox" name="functionId" id="Rtq_Acc_Maintenance_menu"
					value="Rtq_Acc_Maintenance_menu" onclick='checkData(this,"Rtq_Maintenance_menu")'/>&nbsp;&nbsp;RTQ Acc Maintenance</td>
			</tr>
		</table>
		</div>
		</td>
	</tr>

	<tr>
		<td width="13%">
		<div align="right"><input type="checkbox" name="functionId" id="Service_Product_menu"
			value="Service_Product_menu" onclick="checkProductAll();"/> <img
			src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10"
			name="treeflagServiceProduct"></div>
		</td>
		<td width="87%">
		<p onclick= ChangeServiceProduct(); style="cursor: hand">
		<font color="#336699">&nbsp;Service Product</font>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<div id="idServiceProduct" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%"><input type="checkbox" name="functionId" id="Service_Product_Maintenance_menu"
					value="Service_Product_Maintenance_menu" onclick='checkData(this,"Service_Product_menu")'/>&nbsp;&nbsp; Service Product Maintenance</td>
			</tr>
			
		</table>
		</div>
		</td>
	</tr>
	<tr>
		<td width="13%">
		<div align="right"><input type="checkbox" name="functionId" id="Auto_Process_menu"
			value="Auto_Process_menu" onclick="checkAutoProcessAll();"/> <img
			src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10"
			name="treeflagAutoProcess"></div>
		</td>
		<td width="87%">
		<p onclick= ChangeAutoProcess(); style="cursor: hand">
		<font color="#336699">&nbsp;Auto Process</font>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<div id="idAutoProcess" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%"><input type="checkbox" name="functionId" id="Auto_Process_Purchase_menu"
					value="Auto_Process_Purchase_menu" onclick='checkData(this,"Auto_Process_menu")'/>&nbsp;&nbsp; Process Auto Purchase</td>
			</tr>
			<tr>
				<td width="100%"><input type="checkbox" name="functionId" id="Auto_Purchase_Report_menu"
					value="Auto_Purchase_Report_menu" onclick='checkData(this,"Auto_Process_menu")'/>&nbsp;&nbsp; Auto Purchase Report</td>
			</tr>
		<c:if test="${applicationScope.memoDebitSystem eq 'MANUAL' }">
			<tr>
				<td width="100%"><input type="checkbox" name="functionId" id="RTQ_Memo_Debit_menu"
					value="RTQ_Memo_Debit_menu" onclick='checkData(this,"RTQ_Memo_Debit_menu")'/>&nbsp;&nbsp; RTQ Memo Debit(MANUAL)</td>
			</tr>
		</c:if>	
			<tr>
				<td width="100%"><input type="checkbox" name="functionId" id="Day_End_Process_menu"
					value="Day_End_Process_menu" onclick='checkData(this,"Auto_Process_menu")'/>&nbsp;&nbsp; Day End Processing Stauts</td>
			</tr>
		</table>
		</div>
		</td>
	</tr>
	<tr>
		<td width="13%">
		<div align="right"><input type="checkbox" name="functionId" id="Report_menu"
			value="Report_menu" onclick="checkReportAll();"/> <img
			src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10"
			name="treeflagReport"></div>
		</td>
		<td width="87%">
		<p onclick= ChangeReport(); style="cursor: hand">
		<font color="#336699">&nbsp;Report</font>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<div id="idReport" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%"><input type="checkbox" name="functionId" id="Monthly_Purchase_menu"
					value="Monthly_Purchase_menu" onclick='checkData(this,"Report_menu")'/>&nbsp;&nbsp;Monthly Purchase Enquiry</td>
			</tr>
			<tr>
				<td width="100%"><input type="checkbox" name="functionId" id="Reserve_Renewal_menu"
					value="Reserve_Renewal_menu" onclick='checkData(this,"Report_menu")'/>&nbsp;&nbsp;Reserve And Renewal Enquiry</td>
			</tr>
			
		</table>
		</div>
		</td>
	</tr>
	
	<tr>
		<td width="13%">
		<div align="right"><input type="checkbox" name="functionId" id="NO_Date_Export_menu"
			value="NO_Date_Export_menu" onclick="checkNoDateExport();"/> <img
			src="<%=basePath%>/jsp/images/trileft.gif" width="10" height="10"
			name="treeflagNoDataExport"></div>
		</td>
		<td width="87%">
		<p onclick= ChangeNoDataExport(); style="cursor: hand">
		<font color="#336699">&nbsp;User Notification</font>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<div id="idNoDataExport" style="display: none;">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%"><input type="checkbox" name="functionId" id="NO_Date_Export_Maintenance_menu"
					value="NO_Date_Export_Maintenance_menu" onclick='checkData(this,"NO_Date_Export_menu")'/>&nbsp;&nbsp;Export User Notification Data</td>
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
	function ChangeNoDataExport() {
		if (document.all.idNoDataExport.style.display == "none") {
			document.all.treeflagNoDataExport.src = "<%=basePath%>/jsp/images/tridown.gif";
			document.all.idNoDataExport.style.display = "";
			return;
		}
		if (document.all.idNoDataExport.style.display == "") {
			document.all.treeflagNoDataExport.src = "<%=basePath%>/jsp/images/trileft.gif";
			document.all.idNoDataExport.style.display = "none";
			return;
		}
	}

</script>
