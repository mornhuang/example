<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="${pageContext.request.contextPath}/Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="${pageContext.request.contextPath}/Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="${pageContext.request.contextPath}/Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/jselect.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/taifook.layout.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/jquery-ui.custom.min.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/shieldingMouse.js"></script>
    <script type="text/javascript">
    var SC = null;
	function enquiryDelayRTQ(flag) {
		var enquiryCode = $("#enquiryCode");
		if (parent.checkStockCode(enquiryCode)) {
			parent.postEnquireDelayRTQ({instrCode: enquiryCode.val()}, cbEnquireDelayRTQ);
			flag ? enquiryCode.val('') : enquiryCode.val('<bean:message key="label.index.code"/>');
		}
	}

	function cbEnquireDelayRTQ(response) {
		var rtqInfo = response.enquireRTQResponse[0];
		if (rtqInfo.status == 0) {
			rtqInfo.name = parent.assembInstrCodeName(rtqInfo.code, rtqInfo.name);
			SC = rtqInfo.code;
			parent.fillLabels($("#delayRTQ"), rtqInfo);
			parent.fillElements($("#delayRTQ"), rtqInfo);
			var chg = parseFloat(rtqInfo.change);
			var className = chg > 0 ? "rise" : chg == 0 ? "equal" : "fall";
			$("#change").attr("className", className);
			$("#pctChange").attr("className", className);
			if (!parent.RTQInfos.containsKey(rtqInfo.code)) {
				parent.RTQInfos.put(rtqInfo.code, parseInt(rtqInfo.lotSize));
			}
		} else if (rtqInfo.status == 1) {
			reset({name: parent.$.sht.msg.codeNotExist});
		} else if (rtqInfo.status == 2) {
			reset({name: parent.$.sht.msg.codeSuspend});
		} else {
			reset({name: parent.$.sht.msg.outOfSevice});
		}
	}

	function codeKeydown(event) {
		if (event.keyCode == 13) {
			enquiryDelayRTQ(true);
		}
	}

	function refreshDelayQuote() {
		if (SC) {
			parent.postEnquireDelayRTQ({instrCode: SC}, cbEnquireDelayRTQ);
		}
	}

	function brigePrice(el) {
		if (SC) {
			parent.showOrderTicketForm();
			parent.resetPlaceOrder({instrCode: SC, orderQuantity: parent.enquiryLotSize(SC), orderPrice: $(el).text()});
		}			
	}

	
     
	function reset(info) {
		var rtq = {
			PClose:"",
			bestAsk:"",
			bestBid:"",
			change:"",
			currency:"-",
			high:"",
			highSpread:"",
			latest_traded_price:"0",
			latest_traded_time:"--:--",
			latest_traded_vol:"0",
			lotSize:"",
			low:"",
			lowSpread:"",
			name:"--",
			open:"",
			pctChange:"",
			pe:"",
			price:"",
			turnover:"",
			volume:"",
			yield:"",
			time:"--:--"
		};
		rtq = $.extend(rtq, info);
		parent.fillLabels($("#delayRTQ"), rtqInfo);
		parent.fillElements($("#delayRTQ"), rtq);
		$("#change").attr("className", "equal");
		$("#pctChange").attr("className", "equal");
		$("#enquiryCode")[0].focus();
	}

	  $(function () { 
          //Init pop dialogs 
          $("#DisclaimermainDialog").dialog({
              autoOpen: false,
              modal: true,
              width: 410
          });

          //Bypass password pop dialog 
          $("#Disclaimerlink").click(function () { 
              $("#DisclaimermainDialog").dialog("open"); 
          }); 
           
			
      });
	</script>
</head>
<body>
    <div id="delayRTQ" class="trade-quote">
		<p class="stock-name" id="name">--</p>
        <div class="left">
            <table class="grid-left ui-corner-all">
                <tr class="alternating">
                    <th id="l_currency"><bean:message key="currency"/></th>
                    <td id="currency"></td>
                </tr>
                <tr>
                    <th id="l_high"><bean:message key="label.index.maxPrice"/>(-)</th>
                    <td id="high"></td>
                </tr>
                <tr class="alternating">
                    <th id="l_low"><bean:message key="label.index.minPrice"/>(-)</th>
                    <td id="low"></td>
                </tr>
                <tr>
                    <th id="l_open"><bean:message key="label.index.openPrice"/>(-)</th>
                    <td id="open"></td>
                </tr>
                <tr class="alternating">
                    <th id="l_PClose"><bean:message key="label.index.closePrice"/>(-)</th>
                    <td id="PClose"></td>
                </tr>
                <tr>
                    <th id="l_price" style="font-weight: bold;">
                        <bean:message key="label.index.price"/>(-)
                    </th>
                    <td id="price"></td>
                </tr>
                <tr class="alternating">
                    <th id="l_change"><bean:message key="label.index.change"/>(-)</th>
                    <td id="change" class="equal"></td>
                </tr>
                <tr>
                    <th id="l_pctChange"><bean:message key="label.index.change"/>(%)</th>
                    <td id="pctChange" class="equal"></td>
                </tr>
                <tr class="alternating">
                    <th id="l_volume"><bean:message key="label.index.volume"/></th>
                    <td id="volume"></td>
                </tr>
                <tr>
                    <th id="l_turnover"><bean:message key="label.index.turnover"/>(-)</th>
                    <td id="turnover"></td>
                </tr>
                <tr class="alternating">
                    <th id="l_lotSize"><bean:message key="label.index.lotsize"/></th>
                    <td id="lotSize"></td>
                </tr>
            </table>
        </div>
        <div class="center">
            <table class="grid-top ui-corner-all">
                <tr>
                    <td class="ask">
                       <p><span class="price-type"><bean:message key="label.index.pricetype"/></span> </p>
                       <p><span class="price-name"><bean:message key="label.index.pricename"/></span>
                       <span id="price" onclick="brigePrice(this)" class="price-size" style="cursor: pointer;"></span></p>
                    </td>
                </tr>
            </table>
            <span class="price-change"><bean:message key="label.index.RTQupdate"/>: <span id="time"></span></span>
            <table class="grid-bottom ui-corner-all">
                <tr class="alternating">
                    <td><bean:message key="label.index.pe"/></td>
                    <td id="pe"></td>
                </tr>
                <tr>
                    <td><bean:message key="label.index.spread"/></td>
                    <td>
                        <span id="lowSpread"></span>/<span id="highSpread"></span>
                    </td>
                </tr>
                <tr class="alternating">
                    <td><bean:message key="label.index.yield"/></td>
                    <td id="yield"></td>
                </tr>
            </table>
        </div>
        <div class="right">
            <input type="button" onclick="refreshDelayQuote()" class="refresh" value='<bean:message key="label.index.refresh"/>' />
            <div class="code-div">
                <input type="text" id="enquiryCode" maxlength="5" onkeypress="return parent.numKeyPress(event);" onkeydown="return codeKeydown(event);" onfocus="this.value='';" onblur="if(this.value==''){this.value='<bean:message key="label.history.row9"/>';}" class="code" value="<bean:message key="label.history.row9"/>" />
                <input type="button" onclick="enquiryDelayRTQ(false)" class="go" value='<bean:message key="button.common.go"/>' />
            </div>
            <div class="clear"></div>
            <p class="notice">
                <bean:message key="label.index.freeRealTimeQuotation"/>
            </p>
            <p style="text-align:center;"><a href="javascript:;" id="Disclaimerlink" class="blue"><bean:message key="label.index.delayDisclaimer"/></a></p>
        </div>
    </div>
    <div id="DisclaimermainDialog" class="bypass-password-pop ui-pop-padding" title="<bean:message key="label.index.DisclaimermainDialog.title"/>">
        <p><bean:message key="label.index.DisclaimermainDialog.content"/></p>
    </div>
</body>
</html>
