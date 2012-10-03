<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
response.addHeader("Progma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
response.addDateHeader("Expires",1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}" />
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
    <script src="../Script/until.js" type="text/javascript"></script>
    <script src="../Script/ajax.js" type="text/javascript"></script>
    <script src="../Script/main.js" type="text/javascript"></script>
    <script src="../Script/jquery.fmatter-min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
</head>
<c:if test="${!empty pwdfailure}">
<script type="text/javascript">
	alert('<bean:message key="${pwdfailure}"/>');
</script>
</c:if>
<script type="text/javascript">
function ValidatorForm(form){
	if(form.fromAccountId.selectedIndex == -1){
		alert('<bean:message key="label.fundTransferForm.fromAccountNoNull" />');
		return false;
	}
	if(form.toAccountId.selectedIndex == -1){
		alert('<bean:message key="label.fundTransferForm.toAccountNoNull" />');
		return false;
	}
	if($("#money").val()==""||$("#money").val()==null){
		alert('<bean:message key="label.fundTransferForm.AmountNoNull" />!');
		$("#money").focus();
		return false;
	}
	if($("#pwd").val()==""){
		alert('<bean:message key="label.fundTransferForm.vaPwd" />!');
		$("#pwd").focus();
		return false;
	}
	if($("#money").val()>1000000000 || $("#money").val()<1){
		alert('<bean:message key="label.fundTransferForm.vaAmount2" />');
		$("#money").focus();
		return false;
	}
	document.getElementById("Submit").disabled = true;
	return true;
	
}
function NumberCheck(num) {
	  var re=/^[1-9]+\d*(\.\d{1,2})?$/;
	  return re.exec(num) != null;
}
function amtKeyUp(el) {
	if (!/^\d+\.?\d{0,2}$/.test(el.value)) {
		var v = el.value.match(/^\d+\.?\d{0,2}/);
		el.value = v ? v : "";
	}
}
function formatPrice(){
	$("#summary").text("$"+fmtNumber($("#summary").text().split("$")[1],2));
	//$(".form-inline-notice").text("$"+fmtNumber($(".form-inline-notice").text());
}
function showMoney(){
	$.post("../webAccountSummary.do",{accountId:$("#fromAccountId").val()},
		function(data){
			$("#summary").text("$"+data.misAccountSummaryResponse.cashHoldingCol[0].availableBalance);
			formatPrice();
		},
		'json');
}

$(document).ready(function () {
	showMoney();
});
</script>
<body onload="formatPrice();">
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.fundTransferForm.head1"/></span><b></b></span> 
        <span class="position"><bean:message key="label.fundTransferForm.head2"/> > <bean:message key="label.fundTransferForm.head3"/></span>
    </h1>
    <form name="form1" action="../webFundTransfer.do" method="post" onsubmit="return ValidatorForm(this);">
    <div class="page-content">
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    <bean:message key="label.fundTransferForm.head3"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="label.fundTransferForm.fromAC"/>
                </th>
                <td>
                    <select id="fromAccountId" class="jquery-select form-select" name="fromAccountId" onchange="javascript:showMoney();">
                    	<c:choose>
                    		<c:when test="${fn:length(resultFromAccounts)<=0}">
                    			<option><bean:message key="label.fundTransferForm.NoAccount"/></option>
                    		</c:when>
                    		<c:otherwise>
                   				<c:forEach var="result" items="${resultFromAccounts}">
                        			<option value="${result.accountId }">${result.accountId }(<bean:message key="label.fundTransferForm.acType.${result.accountType}"/>)</option>
                        		</c:forEach>
                    		</c:otherwise>
                    	</c:choose>
                    </select>
                    <span class="form-inline-notice"><strong id="summary">$
                    <c:choose>
                    		<c:when test="${fn:length(resultFromAccounts)<=0}">
                    			
                    		</c:when>
                    		<c:otherwise>
                   				${resultCashHoldingSummary.availableBalance }
                    		</c:otherwise>
                    	</c:choose>
                    </strong><bean:message key="label.fundTransferForm.availableBalance"/> </span>
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="label.fundTransferForm.toAC"/>
                </th>
                <td>
                    <select id="toAccountId" class="jquery-select form-select" name="toAccountId">
               			<c:forEach var="result" items="${resultToAccounts}">
                   			<option value="${result.accountId }">
                   			<c:if test="${result.accountStatus == null}">
                   				${result.bankShortName }
                   			</c:if>${result.accountId }(<bean:message key="label.fundTransferForm.acType.${result.accountType}"/>)</option>
                   		</c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="label.currency"/>
                </th>
                <td>
                    <input type="text" class="form-input" name="currency" id="currency" value="HKD" readonly="true" />
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="label.fundTransferForm.transferAmount"/>
                </th>
                <td>
                    <input type="text" class="form-input" name="amount" id="money" onkeyup="amtKeyUp(this)" onkeypress="return priceKeyPress(event)" />
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="label.fundTransferForm.transPassword"/>
                </th>
                <td>
                    <input type="password" class="form-input" name="password" id="pwd" />
                </td>
            </tr>
            <tr class="alternating">
                <th>
                </th>
                <td>
                	<c:choose>
                   		<c:when test="${fn:length(resultFromAccounts)<=0}">
                    		<input id="Submit" type="submit" class="form-button" value="<bean:message key="label.fundTransferForm.submit"/>" disabled="true" />
                   		</c:when>
                   		<c:otherwise>
                    		<input id="Submit" type="submit" class="form-button" value="<bean:message key="label.fundTransferForm.submit"/>" />
                   		</c:otherwise>
                   	</c:choose>
                </td>
            </tr>
        </table>
        <p class="form-notice">
            <strong><bean:message key="label.fundTransferForm.confirmremarks1"/><bean:message key="label.fundTransferForm.confirmremarks2"/></strong>
        </p>
         
        <p>
            <b><bean:message key="label.fundTransferForm.remarks1"/></b>
          </p>
        <p>
	        <b><bean:message key="label.fundTransferForm.remarks2"/>
            </b>
        </p>
    </div>
    </form>
</body>
</html>
