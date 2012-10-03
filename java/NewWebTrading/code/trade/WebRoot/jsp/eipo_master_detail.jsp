<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
   <base href="${pageContext.request.requestURL}">
   <title><bean:message key="company.name"/></title>
   <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
   <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
   <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
   <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
   <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
   <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
   <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
   <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>
   <script type="text/javascript" src="../Script/taifook.layout.js"></script>
   <script type="text/javascript" src="../Script/jselect.js"></script>
   <script type="text/javascript">
	<!--
		<c:if test="${!empty error}">
			alert('<bean:message key="${error}"/>');
		</c:if>
	//-->
	</script>
</head>
	
	<logic:present name="masterResult" scope="request">
    	<bean:define id="masterDtail" name="masterResult" type="com.itsz.sht.struts.eipo.dao.EIPOMasterEntry" />
  	</logic:present>
<body>
<div class="page-content">
	<table class="form-table-center" border="0" cellpadding="5" cellspacing="2" style="width:350px;" align="center">
    <tr>
        <td class="title" colspan="2"><bean:message key="label.eipo.master.details.masterdetails"/></td>
    </tr>
    <tr>
      <td width="180"><bean:message key="label.eipo.master.details.iponame" /></td>
      <td width="170"><bean:write name="masterDtail" property="ipoName"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.master.details.stockcode" /></td>
      <td><bean:write name="masterDtail" property="instrDsplyCode"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.master.details.market" /></td>
      <td><bean:write name="masterDtail" property="mrktCode"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.master.details.currency" /></td>
      <td><bean:write name="masterDtail" property="ccyCode"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.master.details.offerprice" /></td>
      <td><bean:write name="masterDtail" property="formatOfferPrice"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.master.details.brokeragerate" /></td>
      <td><bean:write name="masterDtail" property="formatCommissionRate"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.master.details.sfctransactionlevy" /></td>
      <td><bean:write name="masterDtail" property="formatTransactionLevy"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.master.details.investorcompensationlevy" /></td>
      <td><bean:write name="masterDtail" property="formatCompensationFee"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.master.details.stockexchangetradingfee" /></td>
      <td><bean:write name="masterDtail" property="formatTradingFee"/></td>
    </tr>
	<tr class="alternating">
      <td><bean:message key="label.eipo.master.details.miscfee" /></td>
      <td><bean:write name="masterDtail" property="formatMiscFee"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.master.details.applicationdeadline" /></td>
      <td><bean:write name="masterDtail" property="endDateTime"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.master.details.paymentdeadline" /></td>
      <td><bean:write name="masterDtail" property="paymentDeadlineDateTime"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.master.details.paymentdebitdate" /></td>
      <td><bean:write name="masterDtail" property="depositDate"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.master.details.issuerrefunddate" /></td>
      <td><bean:write name="masterDtail" property="refundDate"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.master.details.sharecollectiondate" /></td>
      <td><bean:write name="masterDtail" property="collectionDate"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.master.details.tradedate" /></td>
      <td><bean:write name="masterDtail" property="listingDate"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.master.details.lotsize" /></td>
      <td><bean:write name="masterDtail" property="formatLotSize"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.master.details.status" /></td>
      <td><bean:write name="masterDtail" property="formatIpoStatus"/></td>
    </tr>      
    <tr><td colspan="2"></td></tr>
  </table>
</div>
</body>
</html>

