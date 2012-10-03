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
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">     
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">   

<script type="text/javascript">
function userProductCancleReserved(clientId,productId){
	document.userProductForm.action="cancelReservedUserProduct.do?method=cancelReservedUserProduct&clientId="+clientId+"&productId="+productId;
	document.userProductForm.submit();	
}

function reserveProduct(){
	document.userProductForm.action="reserveUserProduct.do?method=reserveUserProduct";
	document.userProductForm.submit();	
}

function searchKeydowm(e){
    dkey = e.keyCode;
    if(dkey=="13"){
    	if(document.userProductForm.clntId.value=="" && document.userProductForm.clntLoginId.value==""){
            alert("ClinetID and ClientLoginID cannot empty in same time!");
            document.userProductForm.clntId.focus();
        }else{
       		searchA();
        }
    }
}
function searchA(){
	if(document.userProductForm.clntId.value=="" && document.userProductForm.clntLoginId.value==""){
        alert("ClinetID and ClientLoginID cannot empty in same time!");
        document.userProductForm.clntId.focus();
    }else{
	    document.userProductForm.action="listReserveProduct.do?method=listReserveProduct";
	    document.userProductForm.submit();
    }    
}
function resetAll(){
	document.userProductForm.clntId.value = "";
	document.userProductForm.clntLoginId.value = "";
}
</script>
</head>
<body>
<form  name="userProductForm"  method="post">
<table width="50%" align="left">
	<tr bgcolor="#A5B7C5">
		<td>Client ID:<html:text property="clntId" name="userProductForm"   size="15"   onkeypress="return searchKeydowm(event);" ></html:text></td>
	<td>Client Login ID:<html:text property="clntLoginId" name="userProductForm"   size="15"   onkeypress="return searchKeydowm(event);" ></html:text></td>
		<td>
			<input type="button" name="Submit" value="Search" onclick="searchA();">&nbsp;&nbsp;&nbsp;
			<input type="button" name="Reset" value="Reset" onclick="resetAll();">
		</td>
	</tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table align="left" width="50%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>Client ID:${clientId} &nbsp;&nbsp;|&nbsp;&nbsp; Client Login ID:${clientLoginId}</td>
	</tr>   
	<tr>
		<td >
			|&nbsp;&nbsp;<html:link action="listUserProductByClientId.do?method=listUserProductByClientId&clientIdss=${userProductForm.clntId}&clientLoginIdss=${userProductForm.clntLoginId}"><font color="blue">Change User Product Status</font></html:link>
			&nbsp;|&nbsp;&nbsp;<html:link action="listCancelUserProduct.do?method=listCancelUserProduct&clientIdss=${userProductForm.clntId}&clientLoginIdss=${userProductForm.clntLoginId}"><font color="blue">Cancel User Product</font></html:link>
			&nbsp;|&nbsp;&nbsp;<html:link action="listReserveProduct.do?method=listReserveProduct&clientIdss=${userProductForm.clntId}&clientLoginIdss=${userProductForm.clntLoginId}"><font color="blue">Reserve Product</font></html:link>
			&nbsp;|&nbsp;&nbsp;<html:link action="listUserProfile.do?method=listUserProfile&clientIdss=${userProductForm.clntId}&clientLoginIdss=${userProductForm.clntLoginId}"><font color="blue">Selected Service</font></html:link> 
		</td>
	</tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table align="left">
	<tr>
		<td>
			<table align="left" width="50%" border="0" cellpadding="0" cellspacing="0">
				<tr bgcolor="#A5B7C5">
					<td width="15%">Client ID</td>
					<td width="25%">Client Login ID</td>
					<td width="30%">Default Debit Account</td>
					<td width="30%">China Discount Flag</td>
				</tr>
				<logic:present name="userProfile" scope="request">
					<tr>
						<td><bean:write name="userProfile" property="clientId" /></td>
						<td><bean:write name="userProfile" property="clientLoginId" /></td>
						<td><bean:write name="userProfile" property="defaultDebitAccount" /></td>
						<td><bean:write name="userProfile" property="chinaDiscountFlag" /></td>
					</tr>
				</logic:present>
			</table>
		</td>
 	</tr>
	<tr>
  		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
			<table align="left" width="50%" border="0" cellspacing="0" cellpadding="0">
				<tr bgcolor="#A5B7C5">
					<td colspan="6" align="center"> <STRONG>Reserved User Product List </STRONG></td>
				</tr>
				<tr bgcolor="#A5B7C5">
					<td>Product ID</td>
					<td>Price In HK</td>
					<td>Allow Renewal</td>
					<td>Update By</td>
					<td>Update Date</td>
					<td>&nbsp;</td>
				</tr>
				<logic:present name="reservedUserProductList" scope="request">
					<logic:iterate indexId="ids" id="reservedUserProduct" name="reservedUserProductList">
						<tr>
							<td>${reservedUserProduct.productId}</td>
							<td>${reservedUserProduct.priceInHK}</td>
							<td>
								<c:choose>
									<c:when test="${reservedUserProduct.type eq 'RESERVE'}">N</c:when>
									<c:otherwise>Y</c:otherwise>
								</c:choose>
							</td>
							<td>${reservedUserProduct.updBy}</td>
							<td>${reservedUserProduct.updDate}</td>
							<td><input type="button" value="Cancel" onclick='userProductCancleReserved("${reservedUserProduct.clientId}","${reservedUserProduct.productId}");'></input></td>	
						</tr>
					</logic:iterate>
				</logic:present>
			</table>
		</td>
 	</tr>
<logic:present name="clientId" scope="request">
	<tr>
  		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
			<table align="left" width="50%" border="0" cellspacing="0" cellpadding="0">
			 <tr bgcolor="#A5B7C5">
			  <td> 
			  	<STRONG>Reserve Product:</STRONG> 
			  </td>
			 </tr>
			 <tr bgcolor="#A5B7C5">
			  <td>
			  	Product ID:&nbsp;
			  	<html:select property="prodId" name="userProductForm">
					<html:optionsCollection name="productList" />
				</html:select>&nbsp;&nbsp;
			  	Auto Renewal:&nbsp;
			  	<html:select property="allwRenl" name="userProductForm">
					<html:optionsCollection name="allowRenList" />
				</html:select>&nbsp;&nbsp;
				<input type="button" value="Reserve" onclick="reserveProduct()">
			  </td>
			</table>
		</td>
	</tr>
</logic:present>
</table>
</form>
</body>