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
<script language="JavaScript">
   function checkSubmit(){
	   if(document.productForm.prodId.value == ""){
		   alert("Product ID不能为空！");
		   return false;
	   }if(document.productForm.priceInHkd.value == ""){
		   alert("Price in HKD不能为空！");
		   return false;
	   }else{
          document.productForm.action="addProduct.do?method=addProduct";
          document.productForm.submit();
	   }
   }
   
   function textCounter(field, countfield, maxlimit) { 
	   if (field.value.length > maxlimit) 
	   field.value = field.value.substring(0,maxlimit); 
	   else 
	   countfield.value = maxlimit - field.value.length; 
	   }
 //输入非负整数
	function feiFuZhengShu(s){
		var patrn=/^\d+$/;
		  if (!patrn.test(s.value)) 
		  {
			  alert("请输入非负整数！");
			  s.value="0";
			  return false ;  
		  }			 
		   return true ;	
	}

    function setNoEmail(s){
        
    	 var priceInHkd=  document.getElementById("priceInHkd");
    	 var prodStatus=document.getElementById("prodStatus");
    	 var svcMode=document.getElementById("svcMode");
    	 var valtOfSvc=document.getElementById("valtOfSvc");
    	 var billType=document.getElementById("billType");
           if(s.value=="NO_EMAIL"){
            priceInHkd.value=0;
            prodStatus.value="AVAIL";
            svcMode.value="PER_CLICK";
            valtOfSvc.value="NO_EXPIRY";
            billType.value="FREE";
            priceInHkd.readOnly=true;
            prodStatus.disabled=true;
            svcMode.disabled=true;
            valtOfSvc.disabled=true;
            billType.disabled=true;
            }
           else{
            	priceInHkd.readOnly=false;
            	prodStatus.disabled=false;
                svcMode.disabled=false;
                valtOfSvc.disabled=false;
                billType.disabled=false
                }
           
        }
</script>
</head>
<body>
<form action="addProduct.do?method=addProduct" name="productForm" method="post">
<table align="left">
<th><STRONG>Create Product </STRONG></th>
<tr>
<td colspan="2"><font color="red">${returnMessage}</font></td>
</tr>
	<tr>
		<td bgcolor="#A5B7C5">Product ID</td>
		<td><html:select property="prodId" name="productForm" onblur="setNoEmail(this);">
			<html:optionsCollection name="productList"/>
		</html:select><font color="red">*</font></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5">Product status</td>
		<td><html:select property="prodStatus" name="productForm" >
			<html:option value="AVAIL">AVAIL</html:option>
			<html:option value="UNAVAIL">UNAVAIL</html:option>
		</html:select><font color="red">*</font></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5">Quota</td>
		<td><html:text property="alltQuota" name="productForm" size="5" maxlength="4" value="0" readonly="true"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5">Service Mode</td>
		<td><html:select property="svcMode" name="productForm">
		    <html:option value="PER_CLICK">PER_CLICK</html:option>
			<html:option value="MONTHLY">MONTHLY</html:option>
		</html:select></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5">Validity of Service</td>
		<td><html:select property="valtOfSvc" name="productForm">
			<html:option value="ONE_MONTH-END">ONE_MONTH-END</html:option>
			<html:option value="NO_EXPIRY">NO_EXPIRY</html:option>
		</html:select></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5">Access Unit</td>
		<td><html:text property="acesUnit" name="productForm" value="0" size="2" readonly="true"></html:text></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5">Billing Type</td>
		<td><html:select property="billType" name="productForm">
			<html:option value="CHRG">CHRG</html:option>
			<html:option value="FREE">FREE</html:option>
		</html:select></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5">Price in HKD</td>
		<td><html:text property="priceInHkd" name="productForm" size="5" maxlength="4" onblur="feiFuZhengShu(this);"></html:text><font color="red">*</font></td>
	</tr>
	<tr>
		<td bgcolor="#A5B7C5" width="15%">Remark</td>
		<td><html:textarea property="remarks" cols="50" rows="10"  onkeypress="textCounter(this.form.remarks,this.form.remLen,333);"
			value="${product.remarks}"></html:textarea>
			<br> <font color="red">共可输入333字，还剩<input readonly type=text name=remLen size=3 maxlength=3 value="333">字.<br></font>
			</td>
	</tr>
	<tr>
		<td><input type="button" value="Submit" onclick="checkSubmit();"></td>
		<td align="center"><input type="button" value="GoBack"
			onClick="history.go(-1)" /></td>
	</tr>
</table>
</form>
</body>
</html>

