<%@ page language="java" import="com.taifook.mcs.core.beans.msg.StockInfo"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/zh-HK/style.css" />
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/jselect.js"></script>
  	<script type="text/javascript" src="../Script/until.js"></script>

    <script type="text/javascript">
    	$(function () {
            $("#marginResult").dialog({
                autoOpen: false,
                modal: true,
                height: 320,
                width: 800
            });
            $("#btnSearch").click(function () {
                $("#marginResult").dialog("open");
            });
        });

    	function onlyInputNumber(el,ev){
    		reg=/\d{0,5}/;
    		var v = el.value.match(reg);
    		el.value = v ? v : "";
    	
        }
    </script>
</head>

<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="SearchStockMarginRatio"/></span><b></b></span> <span class="position"><bean:message key="Transactions"/> >
            <bean:message key="SearchStockMarginRatio"/></span>
    </h1>
    <div class="page-content">
        <form action="../marginRatios.do?CLV=${sessionScope.CLV}" method="post">
        <div class="form-table-center ui-corner-all">
            <div class="title">
                <span><bean:message key="SearchStockMarginFinRatio"/></div>
            <table>
                <tr>
                    <th>
                        &nbsp;
                    </th>
                    <td>
                       <bean:message key="stockCode"/>:
                    </td>
                </tr>
                <tr>
                    <th>
                        &nbsp;
                    </th>
                    <td>
                        <input class="form-input" type="text" name="instrumentCode" id="instrumentCode" onKeyUp="onlyInputNumber(this)" onkeypress="return priceKeyPress(event)" />
                    </td>
                </tr>
                <tr>
                    <th>
                    </th>
                    <td>
                        <input type="submit" id="btnSearch" class="form-button" value="<bean:message key="Search"/>"/>
                        <input type="reset" class="form-button" value="<bean:message key="reeset"/>" />
                    </td>
                </tr>
            </table>
        </div>
        </form>
        </body>
</html>