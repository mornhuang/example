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
		<td>
		<logic:present name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" scope="session">
	    	<bean:define id="ipoMaster" name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" type="com.itsz.sht.struts.eipo.dao.EIPOMasterEntry" />
	  	</logic:present>

		<logic:empty name="ipoMaster" property="displayUrl">
			<input type="button"  onclick="javascript:OpenProDetails('prospectus.jsp?CLV=${sessionScope.CLV}&urlName=<bean:write name="ipoMaster" property="displayUrl"/>')" class="prospectus-btn" id="btn-prospectus" value="<bean:message key="message.ipo2.prospetus" />"/>
		</logic:empty>
		<logic:notEmpty name="ipoMaster" property="displayUrl">
			<input type="button"  onclick="javascript:OpenProDetails('bfprop.jsp?CLV=${sessionScope.CLV}&urlName=<bean:write name="ipoMaster" property="displayUrl"/>')" class="prospectus-btn" id="btn-prospectus" value="<bean:message key="message.ipo2.prospetus" />"/>
		</logic:notEmpty>
			<p>
	        	<bean:message key="message.ipo2.head0"/>
	        </p>
	        <p>
	        	<bean:message key="message.ipo2.head1"/>
	        </p>
		<table class="form-table ui-corner-all">
          <tr class="alternating"> 
            <th><bean:message key="message.ipo.rider1.row1"/></th>
            <td><bean:write name="ipoMaster" property="ipoName"/>&nbsp;</td>
          </tr>
          <tr> 
            <th><bean:message key="message.ipo.rider1.row2"/></th>
            <td><bean:write name="ipoMaster" property="instrDsplyCode"/>&nbsp;</td>
          </tr>
          <tr class="alternating"> 
            <th><bean:message key="message.ipo.rider1.row3"/></th>
            <td><bean:write name="ipoMaster" property="formatOfferPrice"/>&nbsp;</td>
          </tr>
          <tr> 
            <th><bean:message key="message.ipo.rider1.row4"/></th>
            <td><bean:write name="ipoMaster" property="formatCommissionRate"/>&nbsp;</td>
          </tr>
          <tr class="alternating"> 
            <th><bean:message key="message.ipo.rider1.row5"/></th>
            <td><bean:write name="ipoMaster" property="formatTransactionLevy"/>&nbsp;</td>
          </tr>
          <tr> 
            <th><bean:message key="message.ipo.rider1.row6"/></th>
            <td><bean:write name="ipoMaster" property="formatCompensationFee"/>&nbsp;</td>
          </tr>
          <tr class="alternating"> 
            <th><bean:message key="message.ipo.rider1.row7"/></th>
            <td><bean:write name="ipoMaster" property="formatTradingFee"/>&nbsp;</td>
          </tr>
	      <tr>
	      	<th><bean:message key="label.eipo.master.details.miscfee"/></th>
	      	<td><bean:write name="ipoMaster" property="formatMiscFee"/>&nbsp;</td>
	   	  </tr>
          <tr class="alternating"> 
            <th><bean:message key="message.ipo.rider1.row8"/></th>
            <td><bean:write name="ipoMaster" property="endDateTime"/>&nbsp;</td>
          </tr>
          <tr> 
            <th><bean:message key="message.ipo.rider1.row9"/></th>
            <td><bean:write name="ipoMaster" property="paymentDeadlineDateTime"/>&nbsp;</td>
          </tr>
		  <tr class="alternating"> 
            <th><bean:message key="message.ipo.rider1.row9a"/></th>
            <td><bean:write name="ipoMaster" property="depositDate"/>&nbsp;</td>
          </tr>
          <tr> 
            <th><bean:message key="message.ipo.rider1.row10"/></th>
            <td><bean:write name="ipoMaster" property="refundDate"/>&nbsp;</td>
          </tr>
          <tr class="alternating"> 
            <th><bean:message key="message.ipo.rider1.row11"/></th>
            <td><bean:write name="ipoMaster" property="listingDate"/>&nbsp;</td>
          </tr>
          <tr> 
            <th><bean:message key="message.ipo.rider1.row12"/></th>
            <td><bean:write name="ipoMaster" property="formatLotSize"/>&nbsp;</td>
          </tr>
          <tr class="alternating"> 
            <th><bean:message key="message.ipo.rider1.row13"/></th>
            <td><bean:write name="ipoMaster" property="formatIpoStatus"/></td>
          </tr>
		</table>		
		</td>
	</tr>
	</table>
		<h2>
		            <bean:message key="label.ipo2.give"/></h2>
		        <p>
		        <bean:message key="message.ipo2.give1"/></p>
		        <p>
		            <strong><bean:message key="message.ipo2.remarks"/></strong> <bean:message key="message.ipo2.give2"/></p>
		        <br />
		        <h2>
		            <bean:message key="label.ipo2.result"/></h2>
		        <p>
		            <bean:message key="message.ipo2.result"/></p>
		        <br />
		        <h2>
		            <bean:message key="label.ipo2.procedure"/></h2>
		        <p>
		            <bean:message key="message.ipo2.procedure"/></p>
		        <br />
		        <h2>
		            <bean:message key="label.ipo2.back"/></h2>
		        <ul class="form-number-ul">
		            <li><span class="number-yellow">1</span><bean:message key="label.ipo2.back1"/></li>
		            <li><bean:message key="message.ipo2.back1"/></li>
		            <li><span class="number-yellow">2</span><bean:message key="label.ipo2.back2"/></li>
		            <li><bean:message key="message.ipo2.back2"/> <bean:message key="message.ipo2.back3"/> </li>
		        </ul>
		<input type="button"  name="confirm" class="yellow-btn" value="<bean:message key="message.ipo2.buttuon1"/>" onclick="javascript:window.location='<%=request.getContextPath()%>/jsp/eipo_disclaimer1.jsp?CLV=${sessionScope.CLV}';">
        <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo2.buttuon2"/>" onclick="javascript:history.back()" />
       	<input type="button" class="graw-short-btn" value="<bean:message key="message.ipo2.buttuon3"/>" onclick="javascript:window.print()" />				

</div>
	</body>
</html>

