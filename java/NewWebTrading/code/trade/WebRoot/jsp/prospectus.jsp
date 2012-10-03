<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.itsz.sht.struts.eipo.util.EIPOConstants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base href="${pageContext.request.requestURL}">
		<title><bean:message key="company.name" />
		</title>
		<link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
		<link rel="Stylesheet" type="text/css"
			href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
		<script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
		<script src="../Script/shieldingMouse.js" type="text/javascript"></script>
		<script type="text/javascript" src="../Script/jquery.cookie.js"></script>
		<script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
		<script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
		<script type="text/javascript"
			src="../Script/jquery.mousewheel.min.js"></script>
		<script type="text/javascript" src="../Script/taifook.layout.js"></script>
		<script type="text/javascript" src="../Script/jselect.js"></script>
		<script type="text/javascript">
	$(function() {
		$("#btnCloseDialog").click(function() {
			parent.$("#dialog").dialog("close");
		});
	});
	//-->
</script>
	</head>
	<script language="JavaScript" src="/js/DisableRightClick.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=big5">
	</HEAD>
	<logic:present name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" scope="session">
   		<bean:define id="ipoMaster" name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" type="com.itsz.sht.struts.eipo.dao.EIPOMasterEntry" />
	</logic:present>
	<BODY>
		<div class="page-content">
			<table class="form-table-center" border="0" align="center" style="width: 380px;">
				<tr>
					<Td class="title" colspan="2">
						<b><bean:message key="message.ipo.prospectus.heading" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="message.ipo.prospectus.title1" />
						<bean:write name="ipoMaster" property="ipoName"/>
						<bean:message key="message.ipo.prospectus.title2" />
					</td>
				</tr>
				<tr>
					<td>
						<li>
							<bean:message key="message.ipo.prospectus.row1" />
							<ol type=a>
								<li>
									<bean:message key="message.ipo.prospectus.row1a" />
									<a href="bfhex.jsp?CLV=${sessionScope.CLV}" target=_blank><bean:message
											key="message.ipo.prospectus.hkex" />
									</a>
									<li>
										<bean:message key="message.ipo.prospectus.row1b" />
										<li>
											<bean:message key="message.ipo.prospectus.row1cf" />
											<bean:write name="ipoMaster" property="instrDsplyCode"/>
											<bean:message key="message.ipo.prospectus.row1cb" />
							</ol>
							<li>
								<bean:message key="message.ipo.prospectus.row2" />
								</ol>
								<strong><bean:message key="message.ipo.prospectus.warn" />
								</strong>
								<bean:message key="message.ipo.prospectus.warncontent" />
					<Br><BR>
				    <bean:message key="message.ipo.prospectus.note"/><a href="http://www.adobe.com/products/acrobat/readstep2.html"><html:img srcKey="images.ipo.acrb" border="0"/></a>
					</td>
				</tr>
			</table>
		</div>
	</BODY>
</HTML>
