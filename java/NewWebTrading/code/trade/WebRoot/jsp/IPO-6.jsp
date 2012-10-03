<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script src="../Script/jselect.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $("#popProfile, #popDetail").dialog({
                autoOpen: false,
                modal: true,
                height: 410,
                width: 400
            });
            $("#btn-profile").click(function () {
                $("#popProfile").dialog("open");
            });
            $("#btn-prospectus").click(function () {
                alert('<bean:message key="label.ipo6.message" />');
                parent.openCommonDialog('<bean:message key="message.ipo6.details.button2" />', 'http://www.hkexnews.hk/listedco/listconews/sehk/20101213/LTN20101213065_C.HTM');
            });
            $("#btn-detail").click(function () {
                $("#popDetail").dialog("open");
            });
        });
    </script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.ipo.heading1"/></span><b></b></span> <span class="position"><bean:message key="label.ipo.heading1"/> >
            <bean:message key="label.ipo.heading2"/></span>
    </h1>
    <form action="../webIPOQueryAmt.do" method="post">
    <div class="page-content">
        <p>
            <input type="button" class="yellow-btn" id="btn-profile" value="<bean:message key="message.ipo6.details.button1" />" />
            <input type="button" class="yellow-btn" id="btn-prospectus" value="<bean:message key="message.ipo6.details.button2" />" />
            <input type="button" class="yellow-btn-long" id="btn-detail" value="<bean:message key="message.ipo6.details.button3" />" />
        </p>
        <table class="form-table ui-corner-all">
            <tr class="title">
                <td colspan="2">
                    <bean:message key="label.ipo.heading1"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="label.ipo6.title1"/>
                </th>
                <td>
                    ${result.ipoRecord.ipoName_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="label.ipo6.title2"/>
                </th>
                <td>
                    <select class="jquery-select" name="shareQty">
                    	<c:forEach var="ipo" items="${result.result}">
                        <option value="${ipo.id.shareQty }">${ipo.id.shareQty_dsply }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <p>
        	<input type="hidden" name="ipoMasterId" value="${ipoMasterId }"/>
            <input type="submit" class="short-button" value="<bean:message key="message.ipo6.button1"/>"/>
            <input type="button" id="btnSearch" class="short-button" value="<bean:message key="message.ipo6.button2"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webIOPQueryList.do'" />
        </p>
        <div id="popProfile" class="hide trading-status-pop ui-pop-padding" title="<bean:message key="message.ipo6.details.title"/>">
            <table class="ui-corner-all">
                <tr class="alternating">
                    <td>
                        <bean:message key="message.ipo6.details.row1"/>
                    </td>
                    <td>
                        ${result.ipoRecord.ipoName_dsply }
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:message key="message.ipo6.details.row2"/>
                    </td>
                    <td>
                        ${result.ipoRecord.stockCode }
                    </td>
                </tr>
                <tr class="alternating">
                    <td>
                        <bean:message key="message.ipo6.details.row3"/>
                    </td>
                    <td>
                        $${result.ipoRecord.price_dsply}
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:message key="message.ipo6.details.row4"/>
                    </td>
                    <td>
                        1%
                    </td>
                </tr>
                <tr class="alternating">
                    <td>
                        <bean:message key="message.ipo6.details.row5"/>
                    </td>
                    <td>
                        0.003%
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:message key="message.ipo6.details.row6"/>
                    </td>
                    <td>
                        0.000%
                    </td>
                </tr>
                <tr class="alternating">
                    <td>
                        <bean:message key="message.ipo6.details.row7"/>
                    </td>
                    <td>
                        0.005%
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:message key="message.ipo6.details.row8"/>
                    </td>
                    <td>
                        ${result.ipoRecord.deadLine_dsply }
                    </td>
                </tr>
                <tr class="alternating">
                    <td>
                        <bean:message key="message.ipo6.details.row9"/>
                    </td>
                    <td>
                        ${result.ipoRecord.depositDate_dsply }
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:message key="message.ipo6.details.row9a"/>
                    </td>
                    <td>
                        ${result.ipoRecord.debitDate_dsply }
                    </td>
                </tr>
                <tr class="alternating">
                    <td>
                        <bean:message key="message.ipo6.details.row10"/>
                    </td>
                    <td>
                        ${result.ipoRecord.refundDate_dsply }
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:message key="message.ipo6.details.row11"/>
                    </td>
                    <td>
                        ${result.ipoRecord.tradeDate_dsply }
                    </td>
                </tr>
                <tr class="alternating">
                    <td>
                        <bean:message key="message.ipo6.details.row12"/>
                    </td>
                    <td>
                        ${result.ipoRecord.quantity_dsply }
                    </td>
                </tr>
            </table>
        </div>
        <div id="popDetail" class="hide trading-status-pop ui-pop-padding" title="<bean:message key="message.ipo6.details.button3" />">
            <table class="ui-corner-all">
                <tr class="alternating">
                    <td>
                        <bean:message key="message.ipo6.tbmult.qty"/>
                    </td>
                    <td>
                        <bean:message key="message.ipo6.tbmult.amt"/>
                    </td>
                </tr>
                <c:forEach var="ipo" items="${result.result}" varStatus="status">
                	<c:choose>
                		<c:when test="${status.count%2==1}">
                			<tr>
			                    <td>
			                        ${ipo.id.shareQty_dsply }
			                    </td>
			                    <td>
			                        $ ${ipo.amount_dsply }
			                    </td>
			                </tr>
                		</c:when>
                		<c:otherwise>
                			<tr class="alternating">
			                    <td>
			                        ${ipo.id.shareQty_dsply }
			                    </td>
			                    <td>
			                        $ ${ipo.amount_dsply }
			                    </td>
			                </tr>
                		</c:otherwise>
                	</c:choose>
                
                </c:forEach>
            </table>
        </div>

    </div>
    </form>
</body>
</html>

