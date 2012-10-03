<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/dformat-tags.tld" prefix="dfmt"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	response.addHeader("Progma", "No-cache");
	response.addHeader("Cache-Control", "no-cache");
	response.addDateHeader("Expires", 1);
%>
<style type="text/css">
<!--
.style1 {
	font-family: arial;
	font-size: 11pt;
	font-weight: bold;
}
.style2 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
}
.style3 {
	font-family: arial;
	font-size: 11pt;
}
.style6 {
	font-weight: bold;
}
-->
</style>

<html:html>
  <meta HTTP-EQUIV="content-type" CONTENT="text/html; charset=UTF-8">
  <head><title><bean:message key="label.trade_history.transactionshistory"/></title></head>

	<div align="left" class="style1"><bean:message key="label.trade_history.transactionshistory"/></div>
	<div align="left"><span class="style1"><bean:message key="label.trade_history.recordasat"/>&nbsp;&nbsp; <%=request.getAttribute("export_date")%></span></div>
	<br/>

<c:forEach var="tradeDetail" items="${tradeListResult.tradeDetailEnquiryResults}">
	<span class="style6 style3"><bean:message key="label.trade_history.accountno"/>:${tradeDetail.accountId}&nbsp;&nbsp;<bean:message key="label.fundTransferForm.acType.${tradeDetail.accountType}"/></span>
	<table width="100%" border="1">
		<tr class="style6 style3">
			<td><span class="style6"><bean:message key="label.trade_history.tradingdate"/></span></td>
		    <td><span class="style6"><bean:message key="label.trade_history.securitiescode"/></span></td>
		    <td><span class="style6"><bean:message key="label.trade_history.securitiesname"/></span></td>
		    <td><span class="style6"><bean:message key="label.trade_history.transactiontype"/></span></td>
		    <td><span class="style6"><bean:message key="label.trade_history.executedquantity"/></span></td>
		    <td><span class="style6"><bean:message key="label.trade_history.executedprice"/></span></td>
		    <td><span class="style6"><bean:message key="label.trade_history.amount"/></span></td>
		    <td><span class="style6"><bean:message key="label.trade_history.channel"/></span></td>
		    <td><span class="style6"><bean:message key="label.trade_history.onlineref"/></span></td>
		</tr>
		<c:choose>
			<c:when test="${tradeDetail.totalEntry==0}">
			  	<tr class="style3">
		   	 		<td height="34" colspan="9"><span class="style3"><bean:message key="label.trade_history.notransactionhistory"/></span></td>
		  	  	</tr>
			</c:when>
			<c:otherwise>
			    <c:forEach var="tradeInfo" items="${tradeDetail.tradeInfoList}">
					<tr class="style3">
						<td align="left">${tradeInfo.businessDate}</td>
						<td align="left">&nbsp;${tradeInfo.instrCode}&nbsp;</td>
						<td align="left">${tradeInfo.instrName}</td>
						<td align="left">
							<c:choose>
								<c:when test="${tradeInfo.tradeSide=='B'}">
									<bean:message key="label.general.buy"/>
									<c:choose>
									    <c:when test="${tradeInfo.status=='TRADE_REVERSED'}">
									    	<bean:message key="label.${tradeInfo.status}"/>
									    </c:when>
									</c:choose>
								</c:when>
								<c:otherwise>
									<bean:message key="label.general.sell"/>
									<c:choose>
									    <c:when test="${tradeInfo.status=='TRADE_REVERSED'}">
									    	<bean:message key="label.${tradeInfo.status}"/>
									    </c:when>
									</c:choose>
								</c:otherwise>
							</c:choose>
				        </td>
				        <td align="right"><dfmt:number value="${tradeInfo.executedQty}" bit="0"/></td>
				        <td align="right">${tradeInfo.ccy}$&nbsp;<dfmt:number value="${tradeInfo.executedPrice}" bit="3"/></td>
				        <td align="right">${tradeInfo.ccy}$&nbsp;<dfmt:number value="${tradeInfo.amount}" bit="3"/></td>
				        <td align="left"><bean:message key="channel.type.${tradeInfo.channelType}"/></td>
				        <td align="left">&nbsp;${tradeInfo.mcsOrderId}${tradeInfo.remark}</td>
				 	</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	<br/>
</c:forEach>

</html:html>
<%
out.flush();
%>

