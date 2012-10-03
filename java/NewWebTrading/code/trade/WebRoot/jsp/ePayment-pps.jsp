<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
    <script src="../Script/until.js" type="text/javascript"></script>
<script type="text/javascript">
        $(function () {
            setCkdStatus();
            $("#pps").click(function () {
                setCkdStatus();
            });
            $("#ppsQuery").click(function () {
                $(".pps").hide();
                setCkdStatus();
            });
            function setCkdStatus() {
                if ($("#pps").attr("checked")) {
                    $(".pps").show();
                    $(".ppsQuery").hide();
                } else {
                    $(".pps").hide();
                    $(".ppsQuery").show();
                }
            }
        });
        function UpdateAmount(){
			$("#money").val($("#transferNum").val());			
        }
        function onSubmit(){
			if($("#pps").attr("checked")){
				if($("#tMoney").val()==""||$("#tMoney").val()==null){
					alert('<bean:message key="label.epayment.transfer.amountNoNull"/>');
					$("#tMoney").focus();
					return false;
				}
				if($("#tMoney").val()>100000 || $("#tMoney").val()<1){
					alert('<bean:message key="label.epayment.transfer.amountRange"/>');
					$("#tMoney").focus();
					return false;
				}
				document.forms.form1.submit();
			}else{
				document.forms.form2.submit();
			}
        }
        function amtKeyUp(el) {
        	if (!/^\d+\.?\d{0,2}$/.test(el.value)) {
        		var v = el.value.match(/^\d+\.?\d{0,2}/);
        		el.value = v ? v : "";
        	}
        }

        function Close()
        {
        	top.close();
        }
                

</script>
</head>
<body onload="UpdateAmount();">
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.epayment.pps.title"/></span><b></b></span> <span class="position"><bean:message key="label.epayment.head"/></span>
    </h1>
    <div class="page-content">
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    <bean:message key="label.epayment.pps.title"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th rowspan="2">
                    <bean:message key="label.epayment.pps.row1"/>
                </th>
                <td>
                    <input type="radio" name="type" id="pps" checked="checked" /><label for="pps"><bean:message key="label.epayment.pps.row2"/></label>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="type" id="ppsQuery" /><label for="ppsQuery"><bean:message key="label.epayment.pps.row3"/></label>
                </td>
            </tr>
            <form name="form1" action="../webPPSTransfer.do" method="post">
            <tr class="alternating pps">
                <th>
                    <bean:message key="label.epayment.pps.row4"/>
                </th>
                <td>
                    <select class="jquery-select form-select" name="accountId">
                    	<c:forEach var="account" items="${resultList}">
								<option value="${account.accountId}">${account.accountId}</option>
						</c:forEach>
                    </select>
                </td>
            </tr>
            <tr class="pps">
                <th>
                   <bean:message key="label.epayment.pps.row5"/>
                </th>
                <td>
                    <input type="text" class="form-input" name="tamount" id="tMoney" onkeyup="amtKeyUp(this)" onkeypress="return priceKeyPress(event)" />
                </td>
            </tr>
            </form>
            <form name="form2" action="https://pa.htisec.com/iPGClient/genenq.asp" method="post">
            <tr class="alternating ppsQuery ">
                <th>
                    <bean:message key="label.epayment.pps.row6"/>
                </th>
                <td>
                    <select class="jquery-select form-select-long" id="transferNum" onchange="UpdateAmount();">
                   		<c:choose>
                   			<c:when test="${fn:length(result)<=0}">
								<option value=""><bean:message key="label.epayment.pps.message" /></option>                    				
                   			</c:when>
                   			<c:otherwise>
                   				<c:forEach var="transfer" items="${result}" varStatus="status">
	                        		<option value="${transfer.txAmt }">${status.count }. ${transfer.txRef } (${transfer.txDate })</option>
	                        	</c:forEach>
                   			</c:otherwise>
                   		</c:choose>
                    </select>
                </td>
            </tr>
            <tr class="ppsQuery">
                <th>
                   <bean:message key="label.epayment.pps.row7"/>
                </th>
                <td>
                    <input type="text" name="amount" id="money" class="form-input" readonly="readonly" />
                </td>
            </tr>
      		<input type="hidden" name="OpCode" value="${resultModel.opCode }"/>
      		<input type="hidden" name="Locale" value="${resultModel.locale }"/>
      		<input type="hidden" name="lang" value="${resultModel.lang }"/>
            </form>
            <tr>
                <th>
                </th>
                <td>
                    <input id="Confirm" type="button" class="form-button" value="<bean:message key="label.epayment.pps.button1"/>" onclick="onSubmit();" />
                    <input type="button" class="form-button" value="<bean:message key="label.epayment.pps.button2"/>" onclick="Close();" />
                </td>
            </tr>
        </table>
        <h2>
            <bean:message key="label.epayment.pps.row8"/></h2>
        <p>
            <bean:message key="label.epayment.pps.row9"/><a href="javascript:parent.openCommonDialog('<bean:message key="label.epayment.pps.row11"/>','http://www.htisec.com/tc/cs/faq_table.jsp?table=faq_ipps#q19');"><bean:message key="label.epayment.pps.row10"/></a>。</p>
    </div>
</body>
</html>
