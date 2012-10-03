<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}"/>
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>

    <script src="../Script/jselect.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script> 
    
    
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
			parent.fillElements($("#delayRTQ"), rtqInfo);
			var className = parseFloat(rtqInfo.change) > 0 ? "rise" : "fall";
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
			currency:"",
			time:"--:--"
		};
		rtq = $.extend(rtq, info);
		parent.fillElements($("#delayRTQ"), rtq);
		$("#change").attr("className", "rise");
		$("#pctChange").attr("className", "rise");
		$("#enquiryCode")[0].focus();
	}
	</script>
	<script type="text/javascript"> 
        /*Dilog demo*/ 
        $(function () { 
            //Init pop dialogs 
            $("#DisclaimermainDialog").dialog({
                autoOpen: false,
                modal: true,
                width: 400
            });
 
            //Bypass password pop dialog 
            $("#Disclaimerlink").click(function () { 
                $("#DisclaimermainDialog").dialog("open"); 
            }); 
            $("#Disclaimerlink").click(function () { 
                $("#bypassPasswordDialog").dialog("close"); 
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
                    <th><bean:message key="label.currency"/></th>
                    <td id="currency"></td>
                </tr>
                <tr>
                    <th><bean:message key="label.index.maxPrice"/>($)</th>
                    <td id="high"></td>
                </tr>
                <tr class="alternating">
                    <th><bean:message key="label.index.minPrice"/>($)</th>
                    <td id="low"></td>
                </tr>
                <tr>
                    <th><bean:message key="label.index.openPrice"/>($)</th>
                    <td id="open"></td>
                </tr>
                <tr class="alternating">
                    <th><bean:message key="label.index.closePrice"/>($)</th>
                    <td id="PClose"></td>
                </tr>
                <tr>
                    <th>
                        <strong><bean:message key="label.index.price"/>($)</strong>
                    </th>
                    <td id="price"></td>
                </tr>
                <tr class="alternating now">
                    <th><bean:message key="label.index.change"/>($)</th>
                    <td id="change" class="rise"></td>
                </tr>
                <tr>
                    <th><bean:message key="label.index.change"/>(%)</th>
                    <td id="pctChange" class="fall"></td>
                </tr>
                <tr class="alternating">
                    <th><bean:message key="label.index.volume"/></th>
                    <td id="volume"></td>
                </tr>
                <tr>
                    <th><bean:message key="label.index.turnover"/>($)</th>
                    <td id="turnover"></td>
                </tr>
                <tr class="alternating">
                    <th><bean:message key="label.index.lotsize"/></th>
                    <td id="lotSize"></td>
                </tr>
            </table>
        </div>
        <div class="center">
           <!-- <table class="grid-top ui-corner-all">
                <tr>
                    <td class="bid">
                        <bean:message key="label.index.bid"/>
                        <p id="bestBid" onclick="brigePrice(this)" style="cursor: pointer;"></p>
                    </td>
                    <td class="ask">
                        <bean:message key="label.index.ask"/>
                        <p id="bestAsk" onclick="brigePrice(this)" style="cursor: pointer;"></p>
                    </td>
                </tr>
            </table>
             --> 
            <table class="grid-top ui-corner-all">
                <tr>
                    <td class="ask">
                       <p><span class="price-type"><bean:message key="span.bmp.real.time"/></span> </p>
                       <p> <span class="price-name"><bean:message key="span.bmp.real.time.price"/></span><span class="price-size"  id="price" onclick="brigePrice(this)" style="cursor: pointer;"></span></p>
                    </td>
                </tr>
            </table>            
            <span class="price-change"><bean:message key="span.bmp.real.time.price.updated"/>:  <span id="time"></span></span>
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
                <input type="text" id="enquiryCode" maxlength="5" onkeypress="return parent.numKeyPress(event);" onkeydown="return codeKeydown(event);" onfocus="this.value='';" onblur="if(this.value==''){this.value='<bean:message key="label.index.code"/>';}" class="code" value='<bean:message key="label.index.code"/>' />
                <input type="button" onclick="enquiryDelayRTQ(false)" class="go" value='<bean:message key="button.common.go"/>' />
            </div>
            <div class="clear"></div>
            <p class="notice">
                <bean:message key="label.index.realQuotation"/>
            </p>
            <p style="text-align:center;"><a href="javascript:;" id="Disclaimerlink" ><bean:message key="label.bottom.disclaimer"/></a></p>           
        </div>
    </div>
    <div id="DisclaimermainDialog" class="bypass-password-pop ui-pop-padding" title='<bean:message key="label.index.realBaseQuot"/>'>
            <p><bean:message key="label.index.disclaimer"/></p>
        </div>
    
</body>
</html>
