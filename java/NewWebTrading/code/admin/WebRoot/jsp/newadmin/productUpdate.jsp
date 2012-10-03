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
<title>Update Product</title>
<script language="JavaScript" src="../js/commutil.js"></script>
<script language="JavaScript">
   function checkSubmit(){
	   var parent = document.getElementById("parent");
	   var div = document.getElementById("errDiv");
	  	if(parent!=null && div!=null){
	    	parent.removeChild(div);
	  	}
          document.productForm.action="updateProduct.do?method=updateProduct";
          document.productForm.submit();
	   }
   
   function textCounter(field, countfield, maxlimit) { 
	   if (field.value.length > maxlimit) 
	   field.value = field.value.substring(0,maxlimit); 
	   else 
	   countfield.value = maxlimit - field.value.length; 
	   }
   
 //输入非负整数
	function feiFuZhengShu(s){
		var parent = document.getElementById("parent");
	   var div = document.getElementById("errDiv");
	  	if(parent!=null && div!=null){
	    	parent.removeChild(div);
	  	}
		var patrn=/^\d+$/;
		  if (!patrn.test(s.value)) 
		  {
			  alert("请输入非负整数！");
			  s.value="0";
			  return false ;  
		  }			 
		   return true ;	
	}
</script>
</head>
<body>
<form action="updateProduct.do?method=updateProduct"  name="productForm" method="post">
<table align="left">
	<tr bgcolor="#A5B7C5">
		<td colspan="2" align="center"><STRONG>Update Service
		Product</STRONG></td>
	</tr>
	<tr> 
    	<td colspan="2" align="left">
	    	<div id="parent"><div id="errDiv" align="center"><font color="#FF0000">
	    	<c:choose>
	    	<c:when test="${productMessage == 'ADMINPROTAL070026'}">
	    		<bean:message key="${productMessage}" bundle="admin"/>
	    	</c:when>
	    	<c:otherwise>
	    		<c:out value="${productMessage}" default=""/>
	    	</c:otherwise>
	    	</c:choose>
	    	</font></div></div>
	    </td>
    </tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Product ID</td>
		<td><html:text property="prodId" value="${product.prodId}"
			readonly="true"></html:text></td>
	</tr>
	<tr>

		<td bgcolor="#A5B7C5" width="15%">Product status</td>
		<td><c:choose>
			<c:when test="${isNoEmail}">
				<html:select property="prodStatus" value="${product.prodStatus}"
					disabled="true">
					<html:option value="AVAIL"></html:option>
					<html:option value="UNAVAIL"></html:option>
				</html:select>
			</c:when>
			<c:otherwise>
				<html:select property="prodStatus" value="${product.prodStatus}">
					<html:option value="AVAIL"></html:option>
					<html:option value="UNAVAIL"></html:option>
				</html:select>
			</c:otherwise>
		</c:choose></td>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Quota</td>
		<td>
			<c:choose>
			<c:when test="${isNoEmail}">
				<html:text property="quota"
			value="${product.quota}" size="5" maxlength="4" disabled="true"></html:text>
			</c:when>
			<c:otherwise>  
				<html:text property="quota"
			value="${product.quota}" size="5" maxlength="4" onchange="feiFuZhengShu(this);" disabled="true"></html:text>
			</c:otherwise>
		</c:choose>
			
			</td>
	</tr>

	<tr>
		<td bgcolor="#A5B7C5" width="15%">Service Mode</td>
		<td><html:select property="svcMode" value="${product.svcMode}"
			disabled="true">
			<html:option value="MONTHLY"></html:option>
			<html:option value="PER_CLICK"></html:option>
		</html:select></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Validity of Service</td>
		<td><html:select property="valtOfSvc"
			value="${product.valtOfSvc}" disabled="true">
			<html:option value="ONE_MONTH-END"></html:option>
			<html:option value="NO-EXPIRY"></html:option>
		</html:select></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Discount Type</td>
		<td><html:select property="discType" value="${product.discType}"
			disabled="true">
			<html:option value="PRC"></html:option>
			<html:option value="NONE"></html:option>
		</html:select></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Price in HKD</td>
		<td>
			<c:choose>
			<c:when test="${isNoEmail}">
				<html:text property="priceInHkd"
			value="${product.priceInHkd}" disabled="true"></html:text>
			</c:when>
			<c:otherwise>
				<html:text property="priceInHkd"
			value="${product.priceInHkd}" size="10" maxlength="8" onchange="feiFuZhengShu(this);"></html:text>
			</c:otherwise>
		</c:choose>
			</td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Remark</td>
		<td><html:textarea property="remarks" cols="50" rows="10"  onkeydown="textCounter(this.form.remarks,this.form.remLen,333);"
			value="${product.remarks}"></html:textarea>
			<br> <font color="red">共可输入333字，还剩<input readonly type=text name=remLen size=3 maxlength=3 value="333">字.<br></font>
			</td>
	</tr>
	<tr>
		<td><input type="button" value="submit"
			onclick="return checkSubmit();"></td>
		<td align="center"><input type="button" value="GoBack"
			onClick="history.go(-1)" /></td>
	</tr>
</table>
</form>
</body>