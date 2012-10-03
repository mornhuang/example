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
    <script src="../Script/jselect.js" type="text/javascript"></script>
    <script src="../Script/until.js" type="text/javascript"></script>
    <script type="text/javascript" src="../Script/sht.locale-${sessionScope['org.apache.struts.action.LOCALE']}.js"></script>
    <script type="text/javascript" src="../Script/until.js"></script>
</head>
<script type="text/javascript">
	function validateBefDate(){
		var str1 = $("#fromDate").val();
		var str2 = $("#toDate").val();
		var befDate = new Date(str1.replace(/-/g, "/"));
		var endDate = new Date(str2.replace(/-/g, "/"));
		if (Date.parse(endDate) - Date.parse(befDate)<0){
			$("#toDate").val(str1);
			$.jNice.SelectUpdate($("#toDate"));
		}
	}
	
	function validateEndDate(){
		var str1 = $("#fromDate").val();
		var str2 = $("#toDate").val();
		var befDate = new Date(str1.replace(/-/g, "/"));
		var endDate = new Date(str2.replace(/-/g, "/"));
		if (Date.parse(endDate) - Date.parse(befDate)<0){
			$("#fromDate").val(str2);
			$.jNice.SelectUpdate($("#fromDate"));
		}
	}
	function periodFocus(selected) {
		var objs = document.getElementsByName("radioDate");    
		if (selected){
			objs[0].checked = true;
			objs[1].checked = false;
		} else {
			objs[0].checked = false;
			objs[1].checked = true;
		}
   }
	function codeFoces(selected){
    	var objs = document.getElementsByName("radioCode"); 
    	if (selected){
    		objs[1].checked = selected;
    	}
    }
	function clearCode(){
    	$("#instrCode").val("");
    }
    function ValidateForm(){
        if(document.getElementsByName("radioCode")[1].checked==true){
	        if($("#instrCode").val()==""){
	            alert('<bean:message key="label.history.stockNoNull"/>');
	            return false;
	        }
	        if(!parent.checkStockCode($("#instrCode"))){
		        $("#instrCode").focus();
		        return false;
	        }
        }
        return true;
    }
    <c:if test="${!empty returnCode}">
		alert('<bean:message key="${returnCode}"/>');
	</c:if>
</script>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.history.title"/></span><b></b></span> <span class="position"><bean:message key="label.history.head"/></span>
    </h1>
    <div class="page-content">
        <form action="../webOrderTradeList.do?pageNo=1&type=find" method="post" onsubmit="return ValidateForm();">
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    <bean:message key="label.history.row1"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="label.history.row2"/>
                </th>
                <td>
                    ${loginUserInfo.custName }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="label.history.row3"/>
                </th>
                <td>
                    <select class="jquery-select" name="accountId">
                    	<c:forEach var="result" items="${result.accountDetailCol }">
                        	<option value="${result.accountId }">${result.accountId }(<bean:message key="label.fundTransferForm.acType.${result.accountType}"/>)</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    <bean:message key="label.history.row4"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th rowspan="2">
                    <bean:message key="label.history.row5"/>
                </th>
                <td>
                    <label class="float-left"><input type="radio" name="radioDate" value="true" checked="checked" /></label>
                    <select name="day" class="jquery-select" onfocus="periodFocus(true);">
                    	<c:forEach var="result" items="${result.periodList}">
                        	<option value="${result }"><bean:message key="label.history.row6"/>${result }<bean:message key="label.history.row7"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="float-left"><input type="radio" name="radioDate" value="false" /></label>
                    <select id="fromDate" name="fromDate" class="jquery-select" onchange="validateBefDate();" onfocus="periodFocus(false);">
                    	<c:forEach var="result" items="${result.befDateList}">
                        	<option value="${result }">${result }</option>
                        </c:forEach>
                    </select>
                    <label class="float-left"><bean:message key="label.history.row8"/></label>
                    <select id="toDate" name="toDate" class="jquery-select" onchange="validateEndDate();" onfocus="periodFocus(false);">
                        <c:forEach var="result" items="${result.endDateList}">
                        	<option value="${result }">${result }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="label.history.row9"/>
                </th>
                <td>
                    <input type="radio" name="radioCode" value="true" checked="checked" onclick="clearCode();" />
                    <bean:message key="label.history.row10"/>
                    <input type="radio" name="radioCode" value="false" />
                    <bean:message key="label.history.row11"/>
                    <input type="text" class="form-input" id="instrCode" name="instrCode" onfocus="codeFoces(true);" onkeypress="return numKeyPress(event);"/>
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="label.history.row12"/>
                </th>
                <td>
                    <select class="jquery-select" name="orderSide">
                        <option value=""><bean:message key="label.history.row13"/></option>
                        <option value="B"><bean:message key="label.history.row14"/></option>
                        <option value="S"><bean:message key="label.history.row15"/></option>
                    </select>
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="label.history.ordertype"/>
                </th>
                <td>
                    <select class="jquery-select" name="channel">
                        <option value=""><bean:message key="label.history.ordertype.row"/></option>
                        <option value="IWIN"><bean:message key="label.history.ordertype.row8"/></option>
                        <option value="IWEB"><bean:message key="label.history.ordertype.row9"/></option>
                        <option value="OTHERS"><bean:message key="label.history.ordertype.row10"/></option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>
                </th>
                <td>
                    <input type="submit" class="form-button" value="<bean:message key="label.history.button1"/>" />
                    <input type="reset" class="form-button" value="<bean:message key="label.history.button2"/>" />
                </td>
            </tr>
        </table>
        </form>
        <h2><bean:message key="label.history.remark"/></h2>
        <ul class="form-number-ul">
            <li><span class="number">1</span><bean:message key="label.history.remark.message1"/></li>
            <li><span class="number">2</span><bean:message key="label.history.remark.message2"/></li>
            <li><span class="number">3</span><bean:message key="label.history.remark.message3"/></li>
        </ul>
    </div>
</body>
</html>
