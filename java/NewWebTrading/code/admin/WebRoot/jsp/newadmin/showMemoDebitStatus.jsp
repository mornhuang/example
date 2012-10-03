<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.taifook.adminportal.common.Constants"%>
<%@ page import="com.itsz.sht.common.auto.ProcessStatus"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="../js/checkurl.jsp"%>
<%@ include file="../js/openhelper.jsp"%>
<html>
<head>
<link href="<%=request.getContextPath()%>/jsp/css/link.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jsp/css/otherClass.css" rel="stylesheet" type="text/css">
</head>
<body>
<table border=0 width=95% cellspacing=0 cellpadding=2 align="left">
		<tr>
			<td><strong>MemoDebit For Auto-Enrollment</strong></td>
		</tr>
		<c:choose>
			<c:when test="${isReseted==true}">
				<tr>
					<td><br>
					The latest process result has been cleared.</td>
				</tr>
				<tr>
					<td align="center"><br>
					<input type="button" name="Return"
						onclick="window.location.href='<%=request.getContextPath()%>/showProcessAuto.do?method=showProcessAuto';"
						value="Return"></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td><br>
					<table width="85%" border="0">
						<tr>
							<td bgcolor="#A5B7C5" colspan="2">&nbsp;${counterTotail} of &nbsp;${pStatus.totalRecord} processed.</td>
						</tr>
						<tr>
							<td width="35%" bgcolor="#A5B7C5" align="right">Start Time :</td>
							<td width="71%">${startTime}</td>
						</tr>
						<c:if test="${isProcessing!=true}">
							<tr>
								<td bgcolor="#A5B7C5" align="right">End Time :</td>
								<td>${endTime}</td>
							</tr>
						</c:if>
						<tr>
							<td bgcolor="#A5B7C5" align="right">Total/Avg Time :</td>
							<td>&nbsp;${elapseString}/&nbsp;${averageTime }ms</td>
						</tr>
						<tr>
							<td width="35%" bgcolor="#A5B7C5" align="right">Total
							Records :</td>
							<td width="71%">${total}</td>
						</tr>
						<tr>
							<td bgcolor="#A5B7C5" align="right">Success :</td>
							<td><font color="blue">${success}</font></td>
						</tr>
<!--						<tr>-->
<!--							<td bgcolor="#A5B7C5" align="right">Not Enough Money :</td>-->
<!--							<td>${lackMoney}</td>-->
<!--						</tr>-->
						<tr>
							<td bgcolor="#A5B7C5" align="right">Sys Error :</td>
							<td>
								<c:if test="${sysError == 0}">${sysError}</c:if>
								<c:if test="${sysError ne 0}"><font color="red">${sysError}</font></c:if>	
							</td>
						</tr>

                       <c:if test="${isProcessing==true}">
                       <tr>
							<td bgcolor="#4f2396" colspan="2"><font color="#ffffff">Processing
							..................</font></td>
						</tr>
						<tr>
							<td bgcolor="#A5B7C5" align="right">Not processed :</td>
							<td>${total-counterTotail}</td>
						</tr>
                       </c:if>

					</table>
					</td>
				</tr>
				<c:choose>
					<c:when test="${isProcessing==true}">
						<tr>
							<td>
							<center><br>
							<input type="button" name="Refresh"
								onclick="window.location.href = '<%=request.getContextPath()%>/showMemoDebitStatus.do?method=showMemoDebitStatus';"
								value="Refresh"></center>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td align="center"><br>
							<input type="button" name="Release"
								onclick="window.location.href='<%=request.getContextPath()%>/showMemoDebitStatus.do?method=showMemoDebitStatus&action=release';"
								value="Release Result"></td>
						</tr>
					</c:otherwise>
				</c:choose>
				<logic:present name="autoPurchaseErrorList">
					<tr>
						<td><br>
						<table width="85%" border="0">
							<tr>
								<th bgcolor="#A5B7C5" colspan=2>Sys Error Details :</th>
							</tr>
							<tr bgcolor="#A5B7C5">
								<th width="35%">MemoDebit ID</th>
								<th width="65%">Exception Detail</th>
							</tr>
							<logic:iterate id="autoPurchaseError" name="autoPurchaseErrorList">
								<tr>
								<c:choose>
									<c:when test="${fn:startsWith(autoPurchaseError.errorId, 'MIS Error')}">
										<td>&nbsp;<font color="red">${autoPurchaseError.errorId}</font></td>
										<td>&nbsp;<font color="red">${autoPurchaseError.errorValue}</font></td>
									</c:when>
									<c:otherwise>
										<td>&nbsp;${autoPurchaseError.errorId}</td>
										<td>&nbsp;${autoPurchaseError.errorValue}</td>
									</c:otherwise>
								</c:choose>
								</tr>
							</logic:iterate>
						</table>
						</td>
					</tr>
				</logic:present>
			</c:otherwise>

		</c:choose>
</table>

</body>

</html>