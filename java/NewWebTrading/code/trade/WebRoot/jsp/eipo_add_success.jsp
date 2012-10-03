<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.itsz.sht.struts.eipo.util.EIPOConstants"%>
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
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.ipo.heading1"/></span><b></b></span> <span class="position"><bean:message key="label.ipo.heading1"/> >
            <bean:message key="label.ipo.heading2"/></span>
    </h1>
<div class="page-content">
	<table border="0" width="90%">
	<tr>
		<td>
		<logic:present name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" scope="session">
	    	<bean:define id="ipoMaster" name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" type="com.itsz.sht.struts.eipo.dao.EIPOMasterEntry" />
	  	</logic:present>
	  	
	  	<logic:present name="subscriptionResult" scope="request">
    		<bean:define id="ipoSubscription" name="subscriptionResult" type="com.itsz.sht.struts.eipo.dao.EIPOSubscriptionEntry" />
  		</logic:present>

		<b><bean:message key="label.ipo.rider7.title1"/>
		<br><br>
		<bean:message key="label.ipo.rider7.title2"/>:
		<logic:notEmpty name="ipoSubscription">
			<bean:write name="ipoSubscription" property="subscriptionId"/>&nbsp;
		</logic:notEmpty>
		</b>
		<br><br>
		<bean:message key="label.ipo.rider7.title21"/>
        <bean:write name="ipoMaster" property="ipoName"/>(<bean:write name="ipoMaster" property="instrDsplyCode"/>)
		<bean:message key="label.ipo.rider7.title22"/>
		<Br>
		<p class="form-notice">
            <strong><b><bean:message key="label.ipo.rider7.title3"/></b>				
				<bean:message key="label.ipo.rider7.title4a"/>
				<bean:write name="ipoMaster" property="paymentDeadlineDateTime"/>
				<bean:message key="label.ipo.rider7.title4b"/>
				<bean:write name="ipoMaster" property="depositDate"/>
	            <bean:message key="label.ipo.rider7.title4c"/>
				<bean:write name="ipoMaster" property="paymentDeadlineDateTime"/>
	            <bean:message key="label.ipo.rider7.title4d"/></strong> 
	            </p>
		<Br>
		<table class="form-table ui-corner-all" style="width:500px">
		<tr class="alternating">
			<th><bean:message key="message.ipo.rider7.row1"/></th>
			<td><b><bean:write name="ipoMaster" property="ipoName"/>&nbsp;</b></td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.rider7.row2"/></th>
			<td><bean:write name="ipoMaster" property="instrDsplyCode"/>&nbsp;</td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="message.ipo.rider7.row4"/></th>
			<td><bean:write name="ipoMaster" property="formatCommissionRate"/>&nbsp;</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.rider7.row5"/></th>
			<td><bean:write name="ipoMaster" property="formatTransactionLevy"/>&nbsp;</td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="message.ipo.rider7.row6"/></th>
			<td><bean:write name="ipoMaster" property="formatCompensationFee"/>&nbsp;</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.rider7.row7"/></th>
			<td><bean:write name="ipoMaster" property="formatTradingFee"/>&nbsp;</td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="message.ipo.rider7.row8"/></th>
			<td><bean:write name="ipoMaster" property="endDateTime"/>&nbsp;</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.rider7.row9"/></th>
			<td><bean:write name="ipoMaster" property="paymentDeadlineDateTime"/>&nbsp;</td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="message.ipo.rider6.row9a"/></th>
			<td><font size=2 ><bean:write name="ipoMaster" property="depositDate"/>&nbsp;</td>
		</tr>
		<tr>
			<td colspan=2>&nbsp;</td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="message.ipo.rider7.row10"/></th>
			<td><bean:write name="ipoMaster" property="formatOfferPrice"/>&nbsp;</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.rider7.row11"/></th>
			<td><bean:write name="ipoMaster" property="formatAppliedShare"/>&nbsp;</td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="message.ipo.rider7.row12"/></th>
			<td><bean:write name="ipoMaster" property="formatHandlingCharge"/>&nbsp;</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.rider7.row13"/></th>
			<td><bean:write name="ipoMaster" property="tradeAmount"/>&nbsp;</td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="message.ipo.rider7.row14"/>&nbsp;</th>
			<td>
				<logic:equal name="ipoSubscription" property="email" value="true">
					<input type="checkbox" checked="checked" id="email" disabled="disabled"/>
				</logic:equal>
				<logic:notEqual name="ipoSubscription" property="email" value="true">
					<input type="checkbox" id="email" disabled="disabled"/>
				</logic:notEqual>
			</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.rider7.row15"/>&nbsp;</th>
			<td>
				<logic:equal name="ipoSubscription" property="photo" value="true">
					<input type="checkbox" checked="checked" id="email" disabled="disabled"/>
				</logic:equal>
				<logic:notEqual name="ipoSubscription" property="photo" value="true">
					<input type="checkbox" id="email" disabled="disabled"/>
				</logic:notEqual>
			</td>
		</tr>
		</table>
		<INPUT TYPE="button" class="graw-short-btn" onclick="window.location='<%=request.getContextPath()%>/ipoSubscriptionEnquiry.do?CLV=${sessionScope.CLV}'" value="<bean:message key="message.ipo.rider7.buttuon1"/>">&nbsp;&nbsp;&nbsp;
		<INPUT TYPE="button" class="graw-short-btn" onClick="window.print()" value="<bean:message key="message.ipo.rider7.buttuon2"/>">
		<Br><Br>
		</td>
	</tr>
	</table>
</div>
</body>
</HTML>
