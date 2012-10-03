<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
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
		$(function(){
			$("#btnCloseDialog").click(function() {
				parent.$("#dialog2").dialog("close");
		    });
		});
	//-->
	</script>
</head>
	<logic:present name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" scope="session">
	    	<bean:define id="ipoMaster" name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" type="com.itsz.sht.struts.eipo.dao.EIPOMasterEntry" />
	</logic:present>
	
<body>
<div class="page-content">
   <table class="form-table-center" border="0" cellpadding="5" cellspacing="5" align="center" style="width:300px;">
   	<tr>
       	<td class="title" colspan="2"><bean:message key="message.ipo.tbmult.title"/></td>
   	</tr>
    <tr class="form-table-first">
      <th><bean:message key="message.ipo.tbmult.qty"/></th>
      <th><bean:message key="message.ipo.tbmult.amt"/></th>
    </tr>
	<logic:notEmpty name="ipoMaster" property="ipoDenominationList">
		<logic:iterate id="master" name="ipoMaster" property="ipoDenominationList" type="com.itsz.sht.struts.eipo.dao.EIPODenominationEntry" indexId="index">
			<% if (index%2 != 0) { %>
			<tr>
			<% } else { %>
			<tr class="alternating">
			<% } %>
				<td><%=master.getFormatAppliedShare()%></td>
				<td><%=master.getFormatAmountPayable()%></td>
			</tr>
		</logic:iterate>
	</logic:notEmpty>
    <tr><td colspan="2"></td></tr>
   </table>
</div>
</body>
</html>
