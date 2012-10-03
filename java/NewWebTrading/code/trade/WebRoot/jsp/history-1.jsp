<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
function pageSubmit(){
	var url = "${pageContext.request.contextPath}/webOrderTradeList.do?pageNo="+$("#pageTradeSel").val();
	window.location = url;
}
function setPage(page){
	var url = "${pageContext.request.contextPath}/webOrderTradeList.do?pageNo="+page;
	window.location = url;
}
</script>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.history.title"/></span><b></b></span> <span class="position"><bean:message key="label.history.head"/></span>
    </h1>
    <div class="page-content">
        <table class="form-table ui-corner-all multi-column trading-history-table">
            <tr>
                <td class="title" colspan="9">
                    <bean:message key="label.history.title"/>
                </td>
            </tr>
            <c:choose>
            	<c:when test="${resultSize == '0'}">
            		<tr class="form-table-first">
            			<td colspan="9"><bean:message key="label.history1.row1"/></td>
            		</tr>
            	</c:when>
            	<c:otherwise>
		            <tr class="form-table-first">
		                <th>
		                    <bean:message key="label.history1.row2"/>
		                </th>
		                <th>
		                    <bean:message key="label.history1.row3"/>
		                </th>
		                <th>
		                    <bean:message key="label.history1.row4"/>
		                </th>
		                <th>
		                    <bean:message key="label.history1.row5"/>
		                </th>
		                <th>
		                    <bean:message key="label.history1.row6"/>
		                </th>
		                <th>
		                    <bean:message key="label.history1.row7"/>
		                </th>
		                <th>
		                    <bean:message key="label.history1.row8"/>
		                </th>
		                <th>
		                    <bean:message key="label.history1.row9"/>
		                </th>
		                <th>
		                    <bean:message key="label.history1.row10"/>
		                </th>
		            </tr>
		            <c:forEach var="result" items="${result.response.tradeListInfos}" varStatus="statu">
			            <c:choose>
			            	<c:when test="${statu.count%2 == 1}">
					            <tr class="alternating">
					                <td>
					                    ${result.businessDate }
					                </td>
					                <td>
					                    ${result.instrCode }
					                </td>
					                <td>
					                    ${result.instrName }
					                </td>
					                <td>
					                    ${result.tradeSide }
					                </td>
					                <td>
					                    ${result.executedQty }
					                </td>
					                <td>
					                    HK$ ${result.executedPrice }
					                </td>
					                <td>
					                    HK$ ${result.amount }
					                </td>
					                <td>
					                   <bean:message key="channel.type.${result.channelType}"/>
					                </td>
					                <td>
					                   ${result.mcsOrderId }
					                </td>
					            </tr>
			            	</c:when>
			            	<c:otherwise>
					            <tr>
					                <td>
					                    ${result.businessDate }
					                </td>
					                <td>
					                    ${result.instrCode }
					                </td>
					                <td>
					                    ${result.instrName }
					                </td>
					                <td>
					                    ${result.tradeSide }
					                </td>
					                <td>
					                    ${result.executedQty }
					                </td>
					                <td>
					                    HK$ ${result.executedPrice }
					                </td>
					                <td>
					                    HK$ ${result.amount }
					                </td>
					                <td>
					                   <bean:message key="channel.type.${result.channelType}"/>
					                </td>
					                <td>
					                   ${result.mcsOrderId }
					                </td>
					            </tr>
			            	</c:otherwise>
			            </c:choose>
		            </c:forEach>
		            <tr>
		                <td colspan="9">
		                    <table class="table-pop-pager">
		                        <tr>
		                            <td>
		                            	<c:if test="${result.pageNo == 1}">
		                                	<a href="javascript:void(0);" class="first disabled"></a><a href="javascript:void(0);" class="prev disabled"></a>
		                                </c:if>
		                                <c:if test="${result.pageNo != 1}">
		                                	<a href="javascript:setPage(1);" class="first"></a>
		                                	<a href="javascript:setPage(${result.prevPage });" class="prev"></a>
		                                </c:if>
		                                	<span class="pages"><bean:message key="label.history1.row11"/> ${result.pageNo } <bean:message key="label.history1.row12"/> ${result.pageAmount } <bean:message key="label.history1.row13"/></span>
		                                <c:if test="${result.pageNo == result.totalPage}"> 
		                                	<a href="javascript:void(0);" class="next disabled"></a><a href="javascript:void(0);" class="last disabled"></a>
		                                </c:if>
		                                <c:if test="${result.pageNo != result.totalPage}"> 
		                                	<a href="javascript:setPage(${result.nextPage });" class="next"></a><a href="javascript:setPage(${result.totalPage });" class="last"></a>
		                                </c:if>
		                                <select name="select" id="pageTradeSel" onchange="javascript:pageSubmit();">
		                                    <c:forEach var="pageNo" begin="1" end="${result.pageAmount}">
		                                    	<c:choose>
		                                    		<c:when test="${pageNo == result.pageNo}">
		                                    			<option selected="selected">${pageNo }</option>
		                                    		</c:when>
		                                    		<c:otherwise>
		                                    			<option>${pageNo }</option>
		                                    		</c:otherwise>
		                                    	</c:choose>
		                                    	
		                                    </c:forEach>
		                                </select>
		                            </td>
		                        </tr>
		                    </table>
		                </td>
		            </tr>
            	</c:otherwise>
            </c:choose>
        </table>
        <input type="button" class="short-button" value="<bean:message key="label.history1.button1"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webExcelExport.do?CLV=WT25S&type=tradelist'" />
        <input type="button" class="short-button" value="<bean:message key="label.history1.button2"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webOrderTransactionHistory.do'" />
    </div>
</body>
</html>

