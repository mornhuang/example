<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>

<html>
<head>
<title>Create Product</title>
<script language="JavaScript" src="<%=request.getContextPath()%>/jsp/js/commutil.js"></script>
<script type="text/javascript">
function checkData(type){
	var rtqLoginId=document.getElementById("rtqLognId").value;
	//去掉后面的0
	var idForm=document.getElementById("idFrom").value.replace(/\b(0+)/gi,"");
	var idTo=document.getElementById("idTo").value.replace(/\b(0+)/gi,"");
	var rtqLognPwd=document.getElementById("rtqLognPwd").value;
	var lastN=document.getElementById("lastN").value;
	//非负整数
	var feifu=/^\d+$/;
	//字母
	var zimu=/^[A-Za-z]+$/;
	var dx=parseInt(idForm)<=parseInt(idTo);
	if(type=="addBatch"){
         if(rtqLoginId==""||idForm==""||idTo==""||rtqLognPwd==""){
                 alert("RTQ Login ID,From,To,RTQ Login PassWord must input!");
                 return false;
          }
		
		if((!feifu.exec(idForm))||(!feifu.exec(idTo))||(parseInt(idForm)>parseInt(idTo))){
	          alert("From和To应该为非负整数，并且From小于等于To");
	          return false;
			}
		
		}

	if(type=="updateBatch"){
		 if(rtqLoginId==""||idForm==""||idTo==""||rtqLognPwd==""){
             alert("RTQ Login ID,From,To,RTQ Login PassWord must input!");
             return false;
      }
	
	if((!feifu.exec(idForm))||(!feifu.exec(idTo))||(parseInt(idForm)>parseInt(idTo))){
          alert("From和To应该为非负整数，并且From小于等于To");
          return false;
		}
	
	}
	
	if(type=="deleteBatch"){
		 if(rtqLoginId==""||idForm==""||idTo==""){
             alert("RTQ Login ID,From,To must input!");
             return false;
      }
	
	if((!feifu.exec(idForm))||(!feifu.exec(idTo))||(parseInt(idForm)>parseInt(idTo))){
          alert("From和To应该为非负整数，并且From小于等于To");
          return false;
		}
	
	}


	if(type=="deleteLastNBatch"){
           if(lastN==""||(!feifu.exec(lastN))){
               alert("Delete Last N 为非负整数");
                return false;
               }
	}
	return true;
}

function addBatch(){

	if(checkData("addBatch")){
		document.rtqAccountForm.action="saveRTQAccountBatch.do?method=saveRTQAccountBatch";
		document.rtqAccountForm.submit();
	}
}

function updateBatch(){

	if(checkData("updateBatch")){
		document.rtqAccountForm.action="updateRTQAccountBatch.do?method=updateRTQAccountBatch";
		document.rtqAccountForm.submit();
	}

}

function deleteBatch(){

	if(checkData("deleteBatch")){
		document.rtqAccountForm.action="deleteRTQAccountBatch.do?method=deleteRTQAccountBatch";
		document.rtqAccountForm.submit();
	}

}

function deleteLastNBatch(){
	if(checkData("deleteLastNBatch")){
		document.rtqAccountForm.action="deleteRTQAccountLastN.do?method=deleteRTQAccountLastN";
		document.rtqAccountForm.submit();
	}
}

function getListCount(objInput){
	var objCount = 0;
	if (objInput == null) return 0
	if (typeof(objInput.length)=="undefined")
	{
		return 1 ;
	}
	else {
		return objInput.length;
	}
}

function showTbs(tbList,isShow){
	var len = getListCount(tbList);
	var strStyle = isShow? "":"none";

	if (typeof(tbList.style)!="undefined"){
		tbList.style.display = strStyle;
		return;
	}

	for (var i=0; i<len; i++){
		tbList[i].style.display = strStyle;
	}
}

function switchMode(mode){
	var f = document.rtqAccountForm;
	if (mode == "add"){
		maintainTitle.innerHTML = "Add New RTQ Accounts";
		showTbs(prodIdTr, true);
		showTbs(rtqLognIdTr, true);
		showTbs(idFromTr, true);
		showTbs(rtqLognPwdTr, true);
		showTbs(lastNTr,false);
		showTbs(addNewAccountsTr, true);
		showTbs(deleteAccountsTr, false);
		showTbs(changePwdTr, false);
		showTbs(deleteLastNTr, false);
	}else if(mode == 'del'){
		maintainTitle.innerHTML = "Delete RTQ Accounts";
		showTbs(prodIdTr, true);
		showTbs(rtqLognIdTr, true);
		showTbs(idFromTr, true);
		showTbs(rtqLognPwdTr, false);
		showTbs(lastNTr,false);
		showTbs(addNewAccountsTr, false);
		showTbs(deleteAccountsTr, true);
		showTbs(changePwdTr, false);
		showTbs(deleteLastNTr, false);
	}else if(mode == 'changePsd'){
		maintainTitle.innerHTML = "Change Password of RTQ Accounts";
		showTbs(prodIdTr, true);
		showTbs(rtqLognIdTr, true);
		showTbs(idFromTr, true);
		showTbs(rtqLognPwdTr, true);
		showTbs(lastNTr,false);
		showTbs(addNewAccountsTr, false);
		showTbs(deleteAccountsTr, false);
		showTbs(changePwdTr, true);
		showTbs(deleteLastNTr, false);
	}
	else if(mode == 'delLastN'){
		maintainTitle.innerHTML = "Delete Last N of RTQ Accounts";
		showTbs(prodIdTr, true);
		showTbs(rtqLognIdTr, false);
		showTbs(idFromTr, false);
		showTbs(rtqLognPwdTr, false);
		showTbs(lastNTr,true);
		showTbs(addNewAccountsTr, false);
		showTbs(deleteAccountsTr, false);
		showTbs(changePwdTr, false);
		showTbs(deleteLastNTr, true);
	}
}

function initDisplay(){
	var a= '<%=request.getAttribute("dispalyState")%>';
	if(a!=null)
	{
		switchMode(a);
	}else{
		switchMode("add");
		}
	}
</script>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">  
</head>
<body onload="initDisplay();">
<form action="saveRTQAccountBatch.do?method=saveRTQAccountBatch" name="rtqAccountForm" method="post">
<table align="left" width="50%" border="0" cellpadding="0" cellspacing="0">
<logic:present name="returnMessage">
<tr>
<td colspan="2"><font color="red">${returnMessage}</font></td>
</tr>
</logic:present>
    <tr>
		<td colspan="2" bgcolor="#A5B7C5" align="center"><STRONG>RTQ Account Operation</STRONG> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:switchMode('add')" style="color:#ffffff;">Add</a>
            | <a href="javascript:switchMode('del')"  style="color:#ffffff;">Del</a>
            | <a href="javascript:switchMode('changePsd')"  style="color:#ffffff;">Change Pwd</a>
             | <a href="javascript:switchMode('delLastN')"  style="color:#ffffff;">Del Last N</a>
		</td>
	</tr>
	<tr>
	<td colspan="2"><b><div id="maintainTitle">Add RTQ Account</div></b></td>
	</tr>
    <tr id="prodIdTr">
		<td bgcolor="#A5B7C5" width="25%">ProductID </td>
		<td><html:select property="prodId" value="" style="width:150">
		     <html:optionsCollection name="productList"/>
		</html:select></td>
	</tr>
	<tr id="rtqLognIdTr">
		<td bgcolor="#A5B7C5" width="25%">RTQ Login ID </td>
		<td><html:text property="rtqLognId" value="" maxlength="20" style="width:150"></html:text></td>
	</tr>
	<tr id="idFromTr">
		<td bgcolor="#A5B7C5" >From&nbsp;&nbsp;<html:text property="idFrom" value="" size="10" maxlength="20" style="width:125"></html:text>&nbsp;&nbsp;To&nbsp;&nbsp;</td>
		<td><html:text property="idTo" value="" size="10" maxlength="20" style="width:150"></html:text></td>
		
	</tr>
	<tr id="rtqLognPwdTr">
		<td bgcolor="#A5B7C5" width="25%">RTQ Login Password</td>
		<td><html:text property="rtqLognPwd" value="" maxlength="20" style="width:150"></html:text></td>
	</tr>
	<tr id="lastNTr" style="display:none">
		<td bgcolor="#A5B7C5" width="25%">Delete Last N</td>
		<td><html:text property="lastN" value=""  size="5" maxlength="4" style="width:150"></html:text></td>
	</tr>
    <tr id="addNewAccountsTr" >
		<td colspan="2"><input type="button"  name="addNewAccounts" value="Add New Accounts" onclick="return addBatch();" >&nbsp;&nbsp;
	</tr>
	<tr id="deleteAccountsTr" style="display:none">
		<td colspan="2"><input type="button"  name="deleteAccounts" value="Delete Accounts" onclick="return deleteBatch();">&nbsp;&nbsp;
	</tr>
	<tr id="changePwdTr" style="display:none">
		<td colspan="2"><input type="button"  name="changePwd" value="Change Pwd"  onclick="return updateBatch();">&nbsp;&nbsp;
	</tr>
	<tr id="deleteLastNTr" style="display:none">
		<td colspan="2"><input type="button"  name="deleteLastN" value="Delete Last N"  onclick="return deleteLastNBatch();">&nbsp;&nbsp;
	</tr>
	<tr>
	<td colspan="2"><p><font color="blue">批量操作：如新增99个RTQ账户APP001到APP099，则在RTQ Login ID处输入“APP”，From处输入001，To处输入099；
	 <br>删除，修改密码类似；“Delete Last N”是根据productId删除后面N条记录。
	</font></p></td>
	</tr>
</table>
</form>
</body>