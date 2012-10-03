<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
	<script type="text/javascript">
	<!--
		function OpenMasterDetails (ipoMasterId) {
			parent.openCommonDialog2("<bean:message key='label.eipo.master.enquiry.title'/>","<%=request.getContextPath()%>/ipoMasterDetails.do?CLV=${sessionScope.CLV}&ipoMasterId=" + ipoMasterId);
		}		
		function OpenIPODenominationDetails () {
			parent.openCommonDialog5("<bean:message key='message.ipo6.details.tableButton'/>","<%=request.getContextPath()%>/jsp/tbmult.jsp?CLV=${sessionScope.CLV}");
		}
		function disableButton(form) {
			disableIt(form.confirm);
			return true;
		}
		
	//-->
	</script>
	<script type="text/javascript">
		function OpenProDetails (url) {
			parent.openCommonDialog4("<bean:message key='message.ipo6.details.tableButton'/>","<%=request.getContextPath()%>/jsp/"+url);
		}
	</script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.ipo.heading1"/></span><b></b></span> <span class="position"><bean:message key="label.ipo.heading1"/> >
            <bean:message key="label.ipo.heading2"/></span>
    </h1>
<div class="page-content">
	<table border="0" width="90%">
	<tr>
	<td><br>
	<logic:present name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" scope="session">
    	<bean:define id="ipoMaster" name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" type="com.itsz.sht.struts.eipo.dao.EIPOMasterEntry" />
  	</logic:present>
  	<table>
  		<tr>
			<td colspan=2 align="center">
		<input type="button" class="yellow-btn" onclick="javascript:OpenMasterDetails('<bean:write name="ipoMaster" property="ipoId"/>');" id="btn-profile" value="<bean:message key="message.ipo6.details.button1" />" />			
		<logic:empty name="ipoMaster" property="displayUrl">
			<input type="button" class="yellow-btn-long"  onclick="javascript:OpenProDetails('prospectus.jsp?CLV=${sessionScope.CLV}&urlName=<bean:write name="ipoMaster" property="displayUrl"/>')" class="prospectus-btn" id="btn-prospectus" value="<bean:message key="message.ipo2.prospetus" />"/>
		</logic:empty>
		<logic:notEmpty name="ipoMaster" property="displayUrl">
			<input type="button" class="yellow-btn-long"  onclick="javascript:OpenProDetails('bfprop.jsp?CLV=${sessionScope.CLV}&urlName=<bean:write name="ipoMaster" property="displayUrl"/>')" class="prospectus-btn" id="btn-prospectus" value="<bean:message key="message.ipo2.prospetus" />"/>
		</logic:notEmpty>
        <input type="button" class="yellow-btn-long" onclick="javascript:OpenIPODenominationDetails();" id="btn-detail" value="<bean:message key="message.ipo6.details.button3" />" />
		</tr>
	</table>
	<html:form action="/addIPOSubscription.do?CLV=${sessionScope.CLV}" method="post" onsubmit="disableButton(this);">
		<table class="form-table ui-corner-all" style="width:400px">
		<tr  class="title">
			<th colspan="2"><bean:message key="label.ipo.heading1"/></th>
		</tr>
		<tr class="form-table-first">
			<th><bean:message key="label.ipo.rider5.title1"/></th>
			<td><bean:write name="ipoMaster" property="ipoName"/></td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="label.ipo.rider5.title2"/></th>
			<td>
		       <html:select name="addIpoSubscriptionForm" property="appliedShare">
 				<logic:notEmpty name="ipoMaster" property="ipoDenominationList">
 				<logic:iterate id="master" name="ipoMaster" property="ipoDenominationList" type="com.itsz.sht.struts.eipo.dao.EIPODenominationEntry">
 					<html:option value="<%=master.getAppliedShare().toString()%>"><%=master.getFormatAppliedShare()%></html:option>
 				</logic:iterate>
 				</logic:notEmpty>
 				</html:select>
			</td>
		</tr>
		<tr>
			<td colspan=2>
			<INPUT TYPE="submit" class="graw-short-btn" name="confirm" value="<bean:message key="message.ipo.rider5.button1"/>" >&nbsp;&nbsp;&nbsp;
			<INPUT TYPE="button" class="graw-short-btn" onclick="window.location='<%=request.getContextPath()%>/webIOPQueryList.do?CLV=${sessionScope.CLV}'" value="<bean:message key="message.ipo.rider5.button2"/>" >
			</td>
		</tr>
		</table>
	</html:form>
	
	</td>
	</tr>
</table>
</BODY>
</HTML>


