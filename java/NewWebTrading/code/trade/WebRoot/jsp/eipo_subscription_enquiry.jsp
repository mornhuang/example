<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
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
	function OpenSubscriptionDetails(subscriptionId) {
		parent.openCommonDialog3("<bean:message key='label.eipo.master.enquiry.title'/>","<%=request.getContextPath()%>/ipoSubscriptionDetails.do?CLV=${sessionScope.CLV}&subscriptionId=" + subscriptionId);
	}
	<c:if test="${!empty error}">
		alert('<bean:message key="${error}"/>');
	</c:if>
//-->
</script>
</HEAD>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.eipo.subscription.enquiry.title"/></span><b></b></span>
        <span class="position"><bean:message key="label.eipo.master.enquiry.title"/>> <bean:message key="label.eipo.subscription.enquiry.title"/></span>
    </h1>
    <div class="page-content">
           <table class="form-table ui-corner-all multi-column">
            <tr>
                <td class="title" colspan="8"><bean:message key="label.eipo.subscription.enquiry.title"/></td>
            </tr>
			<tr class="form-table-first">
				<th><bean:message key="label.eipo.subscription.enquiry.stockcode"/></th>
				<th><bean:message key="label.eipo.subscription.enquiry.status"/></th>
				<th><bean:message key="label.eipo.subscription.enquiry.stockname"/></th>
				<th><bean:message key="label.eipo.subscription.enquiry.offerprice"/></th>
				<th><bean:message key="label.eipo.subscription.enquiry.applyquantity"/></th>
				<th><bean:message key="label.eipo.subscription.enquiry.allottedquantity"/></th>
				<th><bean:message key="label.eipo.subscription.enquiry.channel"/></th>
				<th><bean:message key="label.eipo.subscription.enquiry.orderref"/></th>
			</tr>
			
			<logic:present name="result">
			
			<logic:equal name="result" property="ipoSize" value="0">
				<tr class="alternating">
				<td colspan="8"><bean:message key="label.eipo.nosearch"/></td>
				</tr>
			</logic:equal>
			<logic:notEqual name="result" property="ipoSize" value="0">
				<logic:iterate id="IPOSusbscription" name="result" property="ipoList" type="com.itsz.sht.struts.eipo.dao.EIPOSubscriptionEntry" indexId="index">
					<% if (index%2 != 0) { %>
					<tr>
					<% } else { %>
					<tr class="alternating">
					<% } %>
						<td><bean:write name="IPOSusbscription" property="instrDsplyCode"/></td>
						<td><a href="javascript:OpenSubscriptionDetails('<bean:write name="IPOSusbscription" property="subscriptionId"/>');"><bean:write name="IPOSusbscription" property="subscriptionStatus"/></a></td>
						<td><bean:write name="IPOSusbscription" property="ipoName"/></td>
						<td><bean:write name="IPOSusbscription" property="formatOfferPrice"/></td>
						<td><bean:write name="IPOSusbscription" property="formatAppliedQuantity"/></td>
						<td><bean:write name="IPOSusbscription" property="formatAllotmentQuantity"/></td>
						<td><bean:write name="IPOSusbscription" property="channelCode"/></td>
						<td><bean:write name="IPOSusbscription" property="subscriptionId"/></td>
					</tr>
		       </logic:iterate>
		     </logic:notEqual>
		     
		     </logic:present>
		</table>
    </div>
</body>
</html>
