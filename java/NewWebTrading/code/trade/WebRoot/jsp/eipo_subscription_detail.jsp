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

	<logic:present name="subscriptionResult" scope="request">
    	<bean:define id="subscriptionDtail" name="subscriptionResult" type="com.itsz.sht.struts.eipo.dao.EIPOSubscriptionEntry" />
  	</logic:present>
  	
<body>
<div class="page-content">
   <table class="form-table-center" border="0" cellpadding="5" cellspacing="2" style="width:300px;" align="center">
    <tr>
        <td class="title" colspan="2"><bean:message key="label.eipo.subscription.details.title"/></td>
    </tr>
    <tr>
      <td width="150"><bean:message key="label.eipo.subscription.details.accountid" /></td>
      <td width="150"><bean:write name="subscriptionDtail" property="acId"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.subscription.details.orderref" /></td>
      <td><bean:write name="subscriptionDtail" property="subscriptionId"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.subscription.details.status" /></td>
      <td><bean:write name="subscriptionDtail" property="subscriptionStatus"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.subscription.details.market" /></td>
      <td><bean:write name="subscriptionDtail" property="mrktCode"/></td>
    </tr>
	<tr>
	<td><bean:message key="label.eipo.subscription.details.stockcode" /></td>
	<td><bean:write name="subscriptionDtail" property="instrDsplyCode"/></td>
	</tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.subscription.details.name" /></td>
      <td><bean:write name="subscriptionDtail" property="ipoName"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.subscription.details.offerprice" /></td>
      <td><bean:write name="subscriptionDtail" property="formatOfferPrice"/></td>
    </tr>
	<tr class="alternating">
	<td><bean:message key="label.eipo.subscription.details.applyquantity" /></td>
	<td><bean:write name="subscriptionDtail" property="formatAppliedQuantity"/></td>
	</tr>
    <tr>
      <td><bean:message key="label.eipo.subscription.details.applyamount" /></td>
      <td><bean:write name="subscriptionDtail" property="formatAppliedAmount"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.subscription.details.handlingcharges" /></td>
      <td><bean:write name="subscriptionDtail" property="formatHandlingCharge"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.subscription.details.miscfee" /></td>
      <td><bean:write name="subscriptionDtail" property="formatMiscFee"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.subscription.details.deposit" /></td>
      <td><bean:write name="subscriptionDtail" property="minDepositFee"/></td>
    </tr>
    <tr>
      <td><bean:message key="label.eipo.subscription.details.depositamount" /></td>
      <td><bean:write name="subscriptionDtail" property="formatMinDepositAmount"/></td>
    </tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.subscription.details.allottedquantity" /></td>
      <td><bean:write name="subscriptionDtail" property="allotmentQuantity"/></td>
    </tr>
    <tr>
	  <td><bean:message key="label.eipo.subscription.details.subscriptionrejectedreason" /></td>
	  <td><bean:write name="subscriptionDtail" property="remark"/></td>
	</tr>
    <tr class="alternating">
      <td><bean:message key="label.eipo.subscription.details.channel" /></td>
      <td><bean:write name="subscriptionDtail" property="channelCode"/></td>
    </tr>
    <tr><td colspan="2"></td></tr>
  </table>
</div>
</body>
</html>

