<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
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
</head>

<c:if test="${!empty pwdfailure}">
<script type="text/javascript">
	alert('<bean:message key="${pwdfailure}"/>');
</script>
</c:if>
<script type="text/javascript">
function validatorForm(){
	var emailReg = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var telReg = /^\d{7,13}$/;
	if($("#password").val()==""){
		alert('<bean:message key="message.ipo7.password"/>');
		$("#password").focus();
		return false;
	}
	if($("#email").val()!=""){
		if(!emailReg.test($("#email").val())){
			alert('<bean:message key="message.ipo7.email"/>');
			$("#email").focus();
			return false;
		}
	}
	if($("#telephone").val()!=""){
		if(!telReg.test($("#telephone").val())){
			alert('<bean:message key="message.ipo7.telephone"/>');
			$("#telephone").focus();
			return false;
		}
	}
	return true;
}
</script>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.ipo.heading1"/></span><b></b></span> <span class="position"><bean:message key="label.ipo.heading1"/> >
            <bean:message key="label.ipo.heading2"/></span>
    </h1>
    <form action="../webIPORequestAdd.do" method="post" onsubmit="return validatorForm();">
    <div class="page-content">
        <table class="form-table ui-corner-all">
            <tr>
                <td class="title" colspan="2">
                    <bean:message key="label.ipo.heading1"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="message.ipo7.row1"/>
                </th>
                <td>
                    ${result.ipoRecord.ipoName_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo7.row2"/>
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
                    <bean:message key="message.ipo7.row4"/>
                </th>
                <td>
                    1%
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo7.row5"/>
                </th>
                <td>
                    0.003%
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo7.row6"/>
                </th>
                <td>
                    0.000%
                </td>
            </tr>
            <tr>
                <th>
                   <bean:message key="message.ipo7.row7"/>
                </th>
                <td>
                    0.005%
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo7.row8"/>
                </th>
                <td>
                    ${result.ipoRecord.deadLine_dsply }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo7.row9"/>
                </th>
                <td>
                    ${result.ipoRecord.depositDate_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo7.row9a"/>
                </th>
                <td>
                    ${result.ipoRecord.debitDate_dsply }
                </td>
            </tr>
            <tr>
                <th>
                    &nbsp;
                </th>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo7.row10"/>
                </th>
                <td>
                    $${result.ipoRecord.price_dsply }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo7.row11"/>
                </th>
                <td>
                    ${result.ipoRequest.applyQuantity_dsply}
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo7.row12"/>
                </th>
                <td>
                    $${result.ipoRecord.fee}
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo7.row13"/>
                </th>
                <td>
                    
                    $${result.ipoRequest.dsptAmount_dsply}
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo7.row16"/>
                </th>
                <td>
                    <input type="password" class="form-input" name="password" id="password" />
                </td>
            </tr>
        </table>
        <h2>
            <bean:message key="message.ipo7.foot"/>
        </h2>
        <p>
        <bean:message key="message.ipo7.content"/>
            </p>
        </ul>
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    <bean:message key="message.ipo7.channels"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="message.ipo7.channel1"/>
                </th>
                <td>
                    <bean:message key="message.ipo7.channela1"/>
                    <input id="email" type="text" class="form-input" name="email" />
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo7.channel2"/>
                </th>
                <td>
                    <bean:message key="message.ipo7.channela2"/>
                    <input id="telephone" type="text" class="form-input" name="telephone" />
                </td>
            </tr>
        </table>
        <p>                            
        	<input type="hidden" name="ipoMasterId" value="${result.ipoRecord.ipoMasterId}"/>
        	<input type="hidden" name="dsptAmount" value="${result.ipoRequest.dsptAmount }"/>
        	<input type="hidden" name="applyQuantity" value="${result.ipoRequest.applyQuantity }"/>
        	<input type="hidden" name="stockCode" value="${result.ipoRecord.stockCode }"/>
        	<input type="hidden" name="isAccept" value="All"/>
        	<input type="hidden" name="deadLine" value="${result.ipoRecord.deadLine }"/>
            <input type="submit" class="yellow-btn" value="<bean:message key="message.ipo7.buttuon1"/>" />
            <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo7.buttuon2"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webIOPQueryList.do'" />
            <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo7.buttuon3"/>" onclick="javascript:window.print()" />
        </p>
    </div>
    </form>
</body>
</html>