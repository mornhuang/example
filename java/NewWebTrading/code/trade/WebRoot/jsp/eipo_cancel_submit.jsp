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
<script language="JavaScript">
	function CheckForm(theForm)
	{
		var pwd = document.getElementById("password");
		if(pwd.value==""){
            alert('<bean:message key="message.eipo.password"/>');
            pwd.focus();
			return false;
		} else{   
		  if (window.confirm('<bean:message key="message.eipo.status.cancel"/>')){
			  theForm.confirm.disabled=true;
		      return true;
		  } else {
			  return false;
		  }
		}
	}
	<c:if test="${!empty error}">
		alert('<bean:message key="${error}"/>');
	</c:if>
</script>
</head>
<logic:present name="cancelMaster" scope="session">
    <bean:define id="ipoMaster" name="cancelMaster" type="com.itsz.sht.struts.eipo.dao.EIPOMasterEntry" />
</logic:present>
<logic:present name="cancelSubscription" scope="session">
   <bean:define id="ipoSubscription" name="cancelSubscription" type="com.itsz.sht.struts.eipo.dao.EIPOSubscriptionEntry" />
 </logic:present>
<%
	String subId = request.getParameter("subId");
	if (subId == null || subId == "") {
		subId = request.getAttribute("subId").toString();
	}
%>

<body>
<h1 class="page-title">
	<span class="shaddow"><span><bean:message key="message.ipo.status.buttuon1" /> </span></b></span>
	<span class="position"><bean:message key="label.ipo1.heading" />> <bean:message key="message.ipo.status.buttuon1" /></span>
</h1>
<div class="page-content">
<table border=0 width=90%>
<tr>
	<td>
    <br>
    <form action="<%=request.getContextPath()%>/cancelIPOSubscriptionSubmit.do?CLV=${sessionScope.CLV}" method="post" onsubmit="return CheckForm(this)">
		&nbsp;&nbsp;<bean:message key="message.ipo.status.ac1"/>
        <bean:write name="ipoMaster" property="ipoName"/>(<bean:write name="ipoMaster" property="instrDsplyCode"/>)
		<bean:message key="message.ipo.status.ac3"/>
		<br>
		<br>
		<table class="form-table ui-corner-all" style="width:500px">
		<tr class="alternating">
			<th><bean:message key="message.ipo.status.row1"/></th>
			<td><bean:write name="ipoMaster" property="ipoName"/>&nbsp;</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.status.row2"/></th>
			<td><bean:write name="ipoMaster" property="instrDsplyCode"/>&nbsp;</td>
		</tr>
		<tr class="alternating">
			<td colspan=2>&nbsp;</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.status.row10"/></th>
			<td><bean:write name="ipoMaster" property="formatOfferPrice"/>&nbsp;</td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="message.ipo.status.row11"/></th>
			<td><bean:write name="ipoMaster" property="formatAppliedShare"/>&nbsp;</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.status.row12"/></th>
			<td><bean:write name="ipoMaster" property="formatHandlingCharge"/>&nbsp;</td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="message.ipo.status.row13"/></th>
			<td><bean:write name="ipoMaster" property="tradeAmount"/>&nbsp;</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.status.row14"/></th>
			<td>
			<logic:equal name="ipoSubscription" property="email" value="true">
				<input type="checkbox" checked="checked" id="email" disabled="disabled"/>
			</logic:equal>
			<logic:notEqual name="ipoSubscription" property="email" value="true">
				<input type="checkbox" id="email" disabled="disabled"/>
			</logic:notEqual>
			</td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="message.ipo.status.row15"/></th>
			<td>
			<logic:equal name="ipoSubscription" property="photo" value="true">
				<input type="checkbox" checked="checked" id="email" disabled="disabled"/>
			</logic:equal>
			<logic:notEqual name="ipoSubscription" property="photo" value="true">
				<input type="checkbox" id="email" disabled="disabled"/>
			</logic:notEqual>
			</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.status.row16"/></th>
			<td><%=subId%>
			<input type="hidden" id="subscriptionId" name="subscriptionId" value="<%=subId%>" />
			</td>
		</tr>
		<tr class="alternating">
			<th><bean:message key="message.ipo.status.row17"/></th>
			<td><strong><bean:write name="ipoMaster" property="subscriptionStatus"/></strong>&nbsp;</td>
		</tr>
		<tr>
			<th><bean:message key="message.ipo.status.row19"/></th>
			<td><INPUT TYPE="password" id="password" NAME="password" maxlength="8"></td>
		</tr>
		</table>
		<INPUT TYPE="submit" class="prospectus-btn" name="confirm"  value='<bean:message key="message.ipo.status.buttuon1"/>'>
		<INPUT TYPE="button" class="graw-short-btn" onclick="window.location='<%=request.getContextPath()%>/webIOPQueryList.do?CLV=${sessionScope.CLV}'" value="<bean:message key="message.ipo.status.buttuon2"/>">
		<INPUT TYPE="button" class="graw-short-btn" onClick="window.print()" value="<bean:message key="message.ipo.status.buttuon3"/>">
       <form>
	</td>
</tr>
</table>
</body>
</html>
