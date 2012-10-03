<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}" />
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
    <script src="../Script/until.js" type="text/javascript"></script>
    <script src="../Script/jquery.fmatter-min.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
function formatPrice(){
	$("td").each(function(){
		if($(this).text().indexOf("$") != -1){
			$(this).text("$ "+fmtNumber($(this).text().split("$")[1],2));
		}
		if(!isNaN($(this).text())){
			$(this).text(fmtNumber($(this).text(),0));
		}
	});
}

</script>
<body onload="formatPrice();">
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.accountEnquiryForm.head1_2" /></span><b></b></span> <span class="position"><bean:message key="label.accountEnquiryForm.head2" /></span>
    </h1>
    <div class="page-content">
        <table class="form-table ui-corner-all" style="width:690px;">
            <tr>
                <td colspan="4" class="title">
                    <bean:message key="label.accountEnquiryForm.head1a" />
                </td>
            </tr>
            <tr class="form-table-first">
                <th colspan="3">
                     <bean:message key="label.accountEnquiryForm.accountNo2" />${result.accResp.misAccountSummaryResponse.accountId }
                </th>
                <th style="text-align:right;">
                   <bean:message key="label.currency"/>:HKD
                </th>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="label.accountEnquiryForm.ledgerBalance" />
                </th>
                <td style="width:20%">
                    $ ${result.misCashHoldingSummary.ledgerBalance }
                </td>
                <th>
                    <bean:message key="label.accountEnquiryForm.availableBalance" />
                </th>
                <td style="width:32%">
                    $ ${result.misCashHoldingSummary.availableBalance }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="label.accountEnquiryForm.holdBalance" />
                </th>
                <td>
                    $ ${result.misCashHoldingSummary.onHoldBalance }
                </td>
                <th>
                    <bean:message key="label.accountEnquiryForm.cashBalance" />
                </th>
                <td>
                    $ ${result.misCashHoldingSummary.netCashBalance }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="label.accountEnquiryForm.pendingSettlementD1" />
                </th>
                <td>
                    $ ${result.misCashHoldingSummary.pendingSettlementDay1 }
                </td>
                <th>
                   <bean:message key="label.accountEnquiryForm.stockMarketBalance" />**
                </th>
                <td>
                    $ ${result.misCashHoldingSummary.stockMarketBalance }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="label.accountEnquiryForm.pendingSettlementD2" />
                </th>
                <td>
                    $ ${result.misCashHoldingSummary.pendingSettlementDay2 }
                </td>
                <th>
                    <bean:message key="label.accountEnquiryForm.discountedValue" />**
                </th>
                <td>
                    $ ${result.misCashHoldingSummary.discountedValue }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="label.accountEnquiryForm.clearingCheque1" />
                </th>
                <td>
                    $ ${result.misCashHoldingSummary.clearingCheque1 }
                </td>
                <th>
                    <bean:message key="label.accountEnquiryForm.buyOrderControlLimit" />
                </th>
                <td>
                <c:choose>
                	<c:when test="${result.accResp.misAccountSummaryResponse.buyOrderControlLimit != '0'}">
                    	$ ${result.accResp.misAccountSummaryResponse.buyOrderControlLimit }
                    </c:when>
                    <c:otherwise>
                    	<bean:message key="label.general.na" />
                    </c:otherwise>
                </c:choose>
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="label.accountEnquiryForm.clearingCheque2" />
                </th>
                <td>
                    $ ${result.misCashHoldingSummary.clearingCheque2 }
                </td>
                <th>
                    <bean:message key="label.accountEnquiryForm.maximumOrderSize" />
                </th>
                <td>
                    $ ${result.accResp.misAccountSummaryResponse.totalPurchasingPower }
                </td>
            </tr>
        </table>
        <p class="form-notice">
            <strong>**<bean:message key="message.accountEnquiryForm.note" /></strong>
        </p>
        <c:if test="${result.havePosition == true}">
        <table class="form-table ui-corner-all four-column" style="width:690px;">
            <tr>
                <td colspan="4" class="title">
                    <bean:message key="label.accountEnquiryForm.head1b_2" />
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="label.accountEnquiryForm.equity" />
                </th>
                <th>
                    <bean:message key="label.accountEnquiryForm.prevClosingPrice" />
                </th>
                <th>
                    <bean:message key="label.accountEnquiryForm.availableQuantity" />
                </th>
                <th>
                    <bean:message key="label.accountEnquiryForm.prevClosingValue" />
                </th>
            </tr>
            <c:forEach var="result" items="${result.positionResp.mtsSShareHoldingResponse.shareHoldingInfoCol}" varStatus="statu">
            <c:choose>
            	<c:when test="${statu.count%2 == 1}">
            		<tr class="alternating">
		                <td>
		                    ${result.instrCode } ${result.instrName }
		                </td>
		                <td class="digital">
		                    $${result.previousClosingPrice }
		                </td>
		                <td class="digital">
		                    ${result.maxSellableQuantity }
		                </td>
		                <td class="digital">
		                    $${result.previousClosingValue }
		                </td>
		            </tr>
            	</c:when>
            	<c:otherwise>
            		<tr>
		                <td>
		                    ${result.instrCode } ${result.instrName }
		                </td>
		                <td class="digital">
		                    $${result.previousClosingPrice }
		                </td>
		                <td class="digital">
		                    ${result.maxSellableQuantity }
		                </td>
		                <td class="digital">
		                    $${result.previousClosingValue }
		                </td>
		            </tr>
            	</c:otherwise>
            </c:choose>
            
            </c:forEach>
        </table>
        </c:if>
        <p>
            <input type="button" class="graw-short-btn" value="<bean:message key="label.accountEnquiryForm.button1" />" onclick="window.print()" />
            <input type="button" class="graw-short-btn" value="<bean:message key="label.accountEnquiryForm.button2" />" onclick="javascript:location.href='${pageContext.request.contextPath}/webAccountEnquiryInit.do'" />
        </p>
    </div>
</body>
</html>
