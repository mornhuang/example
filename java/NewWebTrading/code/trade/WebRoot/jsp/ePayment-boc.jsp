<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
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
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
    <script src="../Script/until.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
function onSubmit(){
		if($("#payAmount").val()==""||$("#payAmount").val()==null){
			alert('<bean:message key="label.epayment.transfer.amountNoNull"/>');
			$("#payAmount").focus();
			return false;
		}
		if($("#payAmount").val()>100000 || $("#payAmount").val()<1){
			alert('<bean:message key="label.epayment.transfer.amountRange"/>');
			$("#payAmount").focus();
			return false;
		}
		return true;
}
function NumberCheck(num) {
		var re=/^[1-9]+\d*(\.\d{1,2})?$/;
		return re.exec(num) != null;
}
function amtKeyUp(el) {
	if (!/^\d+\.?\d{0,2}$/.test(el.value)) {
		var v = el.value.match(/^\d+\.?\d{0,2}/);
		el.value = v ? v : "";
	}
}
</script>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.epayment.boc.title"/></span><b></b></span> <span class="position"><bean:message key="label.epayment.head"/></span>
    </h1>
    <form action="../webBOCTransfer.do" method="post" onsubmit="return onSubmit();">
    <div class="page-content">
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    <bean:message key="label.epayment.boc.title"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="label.epayment.boc.row1"/>
                </th>
                <td>
                    <bean:message key="label.epayment.boc.row2"/>
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="label.epayment.boc.row3"/>
                </th>
                <td>
                    <select class="jquery-select form-select" name="accountId">
                    	<c:forEach var="account" items="${resultList}">
								<option value="${account.accountId}">${account.accountId}</option>
						</c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
            <tr>
                <th>
                    <bean:message key="label.epayment.boc.row4"/>
                </th>
                <td>
                    <input type="text" id="payAmount" name="payAmount" class="form-input" onkeypress="return priceKeyPress(event);" onkeyup="amtKeyUp(this);" />
                </td>
            </tr>
            <tr>
                <th>
                </th>
                <td>
                    <input type="submit" class="form-button" value="<bean:message key="label.epayment.boc.button1"/>" />
                    <input type="button" class="form-button" value="<bean:message key="label.epayment.boc.button2"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webEPaymentEntrance.do'" />
                </td>
            </tr>
        </table>
        <h2>
            <bean:message key="label.epayment.boc.row5"/></h2>
        <p>
            <bean:message key="label.epayment.boc.row6"/><a href="javascript:parent.openCommonDialog('<bean:message key="label.epayment.boc.row7"/>','https://its.bochk.com/');"><bean:message key="label.epayment.boc.row8"/></a><bean:message key="label.epayment.boc.row9"/>
    </div>
    </form>
</body>
</html>
