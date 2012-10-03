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
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/jselect.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn-prospectus").click(function () {
                alert('<bean:message key="message.ipo2.alert" />');
                parent.openCommonDialog('<bean:message key="message.ipo2.prospetus" />', 'http://www.hkexnews.hk/listedco/listconews/sehk/20101213/LTN20101213065_C.HTM');
            });
        });
    </script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="message.ipo2.heading"/> </span><b></b></span> <span class="position"><bean:message key="message.ipo2.heading"/> >
            <bean:message key="label.ipo.heading2"/> </span>
    </h1>
    <div class="page-content">
    	<c:if test="${canApply == true}">
	        <input type="button" class="prospectus-btn" id="btn-prospectus" value="<bean:message key="message.ipo2.prospetus" />"/>
	        <p>
	        <bean:message key="message.ipo2.head0"/>
	        </p>
	        <p>
	        	<bean:message key="message.ipo2.head1"/>
	        </p>
        </c:if>
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                   <bean:message key="message.ipo2.heading"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="message.ipo2.row1"/>
                </th>
                <td>
                    ${result.ipoRecord.ipoName_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo2.row2"/>
                </th>
                <td>
                    ${result.ipoRecord.stockCode }
                </td>
            </tr>
             <tr>
                <th>
                    <bean:message key="label.currency"/>
                </th>
                <td>
                    HKD
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo2.row3"/>
                </th>
                <td>
                    $${result.ipoRecord.price_dsply }
                </td>
            </tr>
            <tr>
                <th>
                      <bean:message key="label.currency"/>
                </th>
                <td>
                    HKD
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo2.row4"/>
                </th>
                <td>
                    1%
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo2.row5"/>
                </th>
                <td>
                    0.003%
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo2.row6"/>
                </th>
                <td>
                    0.000%
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo2.row7"/>
                </th>
                <td>
                    0.005%
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo2.row8"/>
                </th>
                <td>
                    ${result.ipoRecord.deadLine_dsply }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo2.row9"/>
                </th>
                <td>
                    ${result.ipoRecord.depositDate_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo2.row9a"/>
                </th>
                <td>
                    ${result.ipoRecord.debitDate_dsply }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo2.row10"/>
                </th>
                <td>
                    ${result.ipoRecord.refundDate_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo2.row11"/>
                </th>
                <td>
                    ${result.ipoRecord.tradeDate_dsply }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo2.row12"/>
                </th>
                <td>
                    ${result.ipoRecord.quantity_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo2.row13"/>
                </th>
                <td>
                <c:choose>
                <c:when test="${canApply == true}">
                	<bean:message key="message.ipo2.row_acc"/>
                </c:when>
                <c:otherwise>
                	<bean:message key="message.ipo2.row_acc2"/>
                </c:otherwise>
                </c:choose>
                </td>
            </tr>
        </table>
        <c:choose>
            <c:when test="${canApply == true}">
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
        
                	<input type="button" class="yellow-btn" value="<bean:message key="message.ipo2.buttuon1"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/jsp/IPO-3.jsp'" />
        			<input type="button" class="graw-short-btn" value="<bean:message key="message.ipo2.buttuon2"/>" onclick="javascript:history.back()" />
       				<input type="button" class="graw-short-btn" value="<bean:message key="message.ipo2.buttuon3"/>" onclick="javascript:window.print()" />
             </c:when>
             <c:otherwise>
                	<input type="button" class="graw-short-btn" value="<bean:message key="message.ipo2.buttuon2"/>" onclick="javascript:history.back()" />
             </c:otherwise>
        </c:choose>
        
    </div>
</body>
</html>