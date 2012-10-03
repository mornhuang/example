<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@page import="com.itsz.sht.struts.eipo.util.EIPOConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base href="${pageContext.request.requestURL}">
			<title><bean:message key="company.name" />
			</title>
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
			function OpenMasterDetails (ipoMasterId) {
				parent.openCommonDialog2("<bean:message key='label.eipo.master.enquiry.title'/>","<%=request.getContextPath()%>/ipoMasterDetails.do?CLV=${sessionScope.CLV}&ipoMasterId=" + ipoMasterId);
			}
			function OpenSubscriptionDetails (subscriptionId) {
				parent.openCommonDialog3("<bean:message key='label.eipo.master.enquiry.title'/>","<%=request.getContextPath()%>/ipoSubscriptionDetails.do?CLV=${sessionScope.CLV}&subscriptionId=" + subscriptionId);
			}
			<c:if test="${!empty error}">
				alert('<bean:message key="${error}"/>');
			</c:if>
		</script>
	</head>
	<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.ipo.heading1"/></span><b></b></span> <span class="position"><bean:message key="label.ipo.heading1"/> >
            <bean:message key="label.ipo.heading2"/></span>
    </h1>
		<div class="page-content">
			<table class="form-table ui-corner-all multi-column">
	            <tr>
	                <td class="title" colspan="5"><bean:message key="label.ipo.heading2" /></td>
	            </tr>
				<tr class="form-table-first">
					<th><bean:message key="label.eipo.master.enquiry.stockcode" /></th>
					<th><bean:message key="label.eipo.master.enquiry.stockname" /></th>
					<th><bean:message key="label.eipo.master.enquiry.type" /></th>
					<th><bean:message key="label.eipo.master.enquiry.status" /></th>
					<th><bean:message key="label.eipo.master.enquiry.action" /></th>
				</tr>
		
		<logic:present name="result">
		
		<logic:equal name="result" property="masterSize" value="0">
			<tr class="alternating">
			<td colspan="5"><bean:message key="label.marginratios.nosearch"/></td>
			</tr>
		</logic:equal>
		<logic:notEqual name="result" property="masterSize" value="0">
			<logic:iterate id="IPOMaster" name="result" property="masterList" type="com.itsz.sht.struts.eipo.dao.EIPOMasterEntry" indexId="index">
				
				<% if (index%2 != 0) { %>
				<tr>
				<% } else { %>
				<tr class="alternating">
				<% } %>
					<td><bean:write name="IPOMaster" property="instrDsplyCode"/></font></td>
					<td><a href="javascript:OpenMasterDetails('<bean:write name="IPOMaster" property="ipoId"/>');"><bean:write name="IPOMaster" property="ipoName"/></a></font></td>
					<td><font class="lat"><bean:write name="IPOMaster" property="subscriptionType"/></font></td>
					<td><font class="lat"><bean:write name="IPOMaster" property="formatIpoStatus"/></font></td>
					<td align="center">
						<logic:equal name="IPOMaster" property="ipoSubscriptionState" value="<%=EIPOConstants.IPO_MASTER_SUBSCRIPTION_STATE_SUB%>">
							<a href="<%=request.getContextPath()%>/ipoSubscription.do?CLV=${sessionScope.CLV}&id=<bean:write name="IPOMaster" property="ipoId"/>">|<bean:message key="label.eipo.master.enquiry.action.subscribe"/>|</a>
						</logic:equal>
						<logic:equal name="IPOMaster" property="ipoSubscriptionState" value="<%=EIPOConstants.IPO_MASTER_SUBSCRIPTION_STATE_CANCEL%>">
							<logic:equal name="IPOMaster" property="allowCancelState" value="true">
							<a href="<%=request.getContextPath()%>/cancelIPOSubscription.do?CLV=${sessionScope.CLV}&ipoMasterId=<bean:write name="IPOMaster" property="ipoId"/>&subscriptionId=<bean:write name="IPOMaster" property="ipoSubscriptionId"/>">|<bean:message key="label.eipo.master.enquiry.action.cancel"/>|</a>
							</logic:equal>
							<logic:notEqual name="IPOMaster" property="allowCancelState" value="true">
								<a><html:img bundle="IMAGE_RESOURCE_KEY" srcKey="images.eipo.cancel" border="0"/></a>
							</logic:notEqual>
						</logic:equal>
						<logic:equal name="IPOMaster" property="allowIpoSubscriptionState" value="true">
							<logic:equal name="IPOMaster" property="allowCancelState" value="true">
							<a href="javascript:OpenSubscriptionDetails('<bean:write name="IPOMaster" property="ipoSubscriptionId"/>');">|<bean:message key="label.eipo.master.enquiry.action.details"/>|</a>
							</logic:equal>
							<logic:notEqual name="IPOMaster" property="allowCancelState" value="true">
								<a><html:img bundle="IMAGE_RESOURCE_KEY" srcKey="images.eipo.details" border="0"/></a>
							</logic:notEqual>
						</logic:equal>
					</td>
				</tr>
	       </logic:iterate>
	     </logic:notEqual>
	     </logic:present>
			</table>
		</div>
	</body>
</html>
