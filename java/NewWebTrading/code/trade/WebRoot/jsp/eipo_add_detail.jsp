<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
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
	<script language="JavaScript">
	function CheckForm(theForm){
		var pwd = document.getElementById("password");
		if(pwd.value==""){
            alert('<bean:message key="message.eipo.password"/>');
            pwd.focus();
			return false;
		}
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
	<html:form action="/addIpoSubscriptionSubmit.do?CLV=${sessionScope.CLV}" method="post" onsubmit="return CheckForm(this);">
		<table border="0" width="90%">
		<tr>
			<td>
			<logic:present name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" scope="session">
		    	<bean:define id="ipoMaster" name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" type="com.itsz.sht.struts.eipo.dao.EIPOMasterEntry" />
		  	</logic:present>
			<table class="form-table ui-corner-all" style="width:500px">
				<tr class="alternating">
					<th><bean:message key="message.ipo.rider6.row1"/></th>
					<td><b><bean:write name="ipoMaster" property="ipoName"/>&nbsp;</b></td>
				</tr>
				<tr>
					<th><bean:message key="message.ipo.rider6.row2"/></th>
					<td><bean:write name="ipoMaster" property="instrDsplyCode"/>&nbsp;</td>
				</tr>
				<tr class="alternating">
					<th><bean:message key="message.ipo.rider6.row4"/></th>
					<td><bean:write name="ipoMaster" property="formatCommissionRate"/>&nbsp;</td>
				</tr>
				<tr>
					<th><bean:message key="message.ipo.rider6.row5"/></th>
					<td><bean:write name="ipoMaster" property="formatTransactionLevy"/>&nbsp;</td>
				</tr>
				<tr class="alternating">
					<th><bean:message key="message.ipo.rider6.row6"/></th>
					<td><bean:write name="ipoMaster" property="formatCompensationFee"/>&nbsp;</td>
				</tr>
				<tr>
					<th><bean:message key="message.ipo.rider6.row7"/></th>
					<td><bean:write name="ipoMaster" property="formatTradingFee"/>&nbsp;</td>
				</tr>
			    <tr class="alternating">
			   	 	<th><bean:message key="label.eipo.master.details.miscfee"/></th>
					<td><bean:write name="ipoMaster" property="formatMiscFee"/>&nbsp;</td>
			    </tr>
				<tr>
					<th><bean:message key="message.ipo.rider6.row8"/></th>
					<td><bean:write name="ipoMaster" property="endDateTime"/>&nbsp;</td>
				</tr>
				<tr class="alternating">
					<th><bean:message key="message.ipo.rider6.row9"/></th>
					<td><bean:write name="ipoMaster" property="paymentDeadlineDateTime"/>&nbsp;</td>
				</tr>
				<tr>
					<th><bean:message key="message.ipo.rider6.row9a"/></th>
					<td><bean:write name="ipoMaster" property="depositDate"/>&nbsp;</td>
				</tr>
				<tr class="alternating">
					<td colspan=2>&nbsp;</td>
				</tr>
				<tr>
					<th><bean:message key="message.ipo.rider6.row10"/></th>
					<td><bean:write name="ipoMaster" property="formatOfferPrice"/>&nbsp;</td>
				</tr>
				<tr class="alternating">
					<th><bean:message key="message.ipo.rider6.row11"/></th>
					<td><bean:write name="ipoMaster" property="formatAppliedShare"/>&nbsp;</td>
				</tr>
				<tr>
					<th><bean:message key="message.ipo.rider6.row12"/></th>
					<td><bean:write name="ipoMaster" property="formatHandlingCharge"/>&nbsp;</td>
				</tr>
				<tr class="alternating">
					<th><bean:message key="message.ipo.rider6.row13"/></th>
					<td><bean:write name="ipoMaster" property="tradeAmount"/>&nbsp;</td>
				</tr>
				<tr>
					<th><bean:message key="message.ipo.rider6.row16"/></th>
					<td><INPUT TYPE="password" id="password" NAME="password" maxlength="8"></td>
				</tr>
			</table>
			</td>
		</tr>	
		<tr>
			<td width="92%" ><div align="left">
		    	<strong><bean:message key="message.ipo.rider6.foot"/>
		        </strong><br><br>
				<bean:message key="message.ipo.rider6.content"/>
				<br><br>
				</div>
		        <table class="form-table ui-corner-all" style="width:524px">
					<tr> 
		              <td colspan="3" class="title"><bean:message key="message.ipo.rider6.channels"/></td>
		            </tr>
					<tr>
					  <th><bean:message key="message.ipo.rider6.channel1"/></th>               
		              <td><bean:message key="message.ipo.rider6.row14"/></td>
		              <td><html:checkbox property="email"/></td>
		            </tr>
		            <tr class="alternating">
					  <th><bean:message key="message.ipo.rider6.channel2"/></th>               
		              <td><bean:message key="message.ipo.rider6.row15"/></td>
		              <td><html:checkbox property="phone"/></td>
		            </tr>
		        </table>
			</td>
		 </tr>
		<tr>
			<td>
				<INPUT TYPE="submit" name="confirm"  class="yellow-btn" value="<bean:message key="message.ipo.rider6.buttuon1"/>">&nbsp;&nbsp;&nbsp;
				<INPUT TYPE="button" class="graw-short-btn" onclick="window.location='<%=request.getContextPath()%>/webIOPQueryList.do?CLV=${sessionScope.CLV}'" value="<bean:message key="message.ipo.rider6.buttuon2"/>">&nbsp;&nbsp;&nbsp;
				<INPUT TYPE="button" class="graw-short-btn" onClick="window.print()" value="<bean:message key="message.ipo.rider6.buttuon3"/>">	
			</td>
		</tr>
		</table>
	</html:form>
</div>
</body>
</html>

