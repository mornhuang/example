var LOCALE = null;
var Token = null;
var NetAmount = null;
var UNABLEMODFIY = ["REJECTED", "CANCELLED", "PARTIALLY_FILLED_COMPLETED", "COMPLETELY_FILLED"];
var User = {};
var CalMOS = null;
var CurTab = null;
var RTQInfos = new Map();
var TradeMap = new Map();
var PAGESIZE = 7
var ShareHoldingInfoCol = null;
var FilterFlag = false;
var FilterCode = null;
var OrderBookIndex = 1;
var HandlerContral = null;
var CURRENCY = '--';

/**
 * Make a map like java. You can use this map like this : var myMap = new Map();
 * myMap.put("key","value"); var key = myMap.get("key");
 */
function Map() {
	this.elements = new Array();
	this.size = function() {
		return this.elements.length;
	};

	this.isEmpty = function() {
		return (this.elements.length < 1);
	};

	this.clear = function() {
		this.elements = new Array();
	};

	this.put = function(_key, _value) {
		this.elements.push({key:_key, value:_value});
	};

	this.remove = function(_key) {
		var bln = false;
	
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch(e) {
			bln = false;
		}
		return bln;
	};

	this.get = function(_key) {
		try{ 
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					return this.elements[i].value;
				}
			}
		} catch(e) {
			return null;
		}
	};

	this.element = function(_index) {
		if (_index < 0 || _index >= this.elements.length) {
			return null;
		}
		return this.elements[_index];
	};

	this.containsKey = function(_key) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					bln = true;
				}
			}
		} catch(e) {
			bln = false;
		}
		return bln;
	};

	this.containsValue = function(_value) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value){
					bln = true;
				}
			}
		} catch(e) {
			bln = false;
		}
		return bln;
	};

	this.values = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].value);
		}
		return arr;
	};

	this.keys = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].key);
		}
		return arr;
	};
}

function refreshStockPosition() {
	postEnquireStockPosition({fromIdx: 1}, cbStockPosition);
}

function showPositionTab() {
	showTab('ui-south-content-stock-pos');
	refreshStockPosition();
}

function cbStockPosition(response) {
	ShareHoldingInfoCol = response.shareHoldingInfoCol.sort(function(a, b) {return parseInt(a.instrCode) > parseInt(b.instrCode) ? 1 : -1;});
	enquiryStockPostion(1);
}

function enquiryStockPostion(index) {
	var templateRow = $("#stockPos .hide:first");
	$("#stockPos > tr").remove(":not(.hide)");
	var start = (index - 1) * PAGESIZE;
	var end = index * PAGESIZE;
	if (end > ShareHoldingInfoCol.length) {
		end = ShareHoldingInfoCol.length;
	}
	$.each(ShareHoldingInfoCol.slice(start, end), function(i, pos) {
		var row = templateRow.clone();
		row.find("td:eq(0) > a").click(function() {
			sellPosition(start + i);
		});
		row.find("td:eq(1)").text(assembInstrCodeName(pos.instrCode, pos.instrName));
		row.find("td:eq(2)").text(chkNull(pos.currency));
		row.find("td:eq(3)").text(fmtNumber(pos.previousClosingPrice, 3));
		row.find("td:eq(4)").text(fmtNumber(pos.maxSellableQuantity, 0));
		row.find("td:eq(5)").text(fmtNumber(pos.previousClosingValue, 2));
		row.insertAfter($("#stockPos > tr:last")).removeClass("hide");
	});
	var totalPage = Math.ceil(ShareHoldingInfoCol.length / PAGESIZE);
	var currentPage = index;
	var nextPage = totalPage > currentPage ? currentPage + 1 : currentPage; 
	var res = {
		currentPage: currentPage,
		nextPage: nextPage,
		totalPage: totalPage
	};
	splitPageInfo(res, enquiryStockPostion);
	$("#stockPos > tr:odd").attr("className", "buy");
}

function sellPosition(index) {
	showTradingForm();
	var stockCode = fmtStockCode(ShareHoldingInfoCol[index].instrCode);
	resetPlaceOrder({instrCode: stockCode, orderPrice:fmtNumber(ShareHoldingInfoCol[index].previousClosingPrice, 3), orderQuantity: ShareHoldingInfoCol[index].maxSellableQuantity});
	enquiryLotSize(stockCode);
}

function filterOrderBook() {
	if (checkStockCode($("#filterCode"))) {
		FilterFlag = true;
		FilterCode = $("#filterCode").val();
		postListOrder({marketCode: "AMS3", pageSize: PAGESIZE, instrCode: FilterCode}, cbListOrder);
	}
}

function showOrderBookTab() {
	showTab('ui-south-content-order-status');
	refreshOrderBook();
}

function showOrderTicketForm() {
	showTradingForm();
	$(".ui-north-pannel-buy, .ui-north-pannel-sell").hide();
}

function refreshOrderBook() {
	FilterFlag = false;
	FilterCode = null;
	postListOrder({marketCode: "AMS3", pageSize: PAGESIZE}, cbListOrder);
}

function enquiryOrderBook(index) {
	if (index < 1) index = 1;
	OrderBookIndex = index;
	postListOrder({marketCode: "AMS3", pageSize: PAGESIZE, pageNo: index, instrCode: FilterFlag ? FilterCode : null}, cbListOrder);
}

function cbListOrder(response) {
	var templateRow = $("#orderBook .hide:first");
	$("#orderBook > tr").remove(":not(.hide)");
	$.each(response.orders, function(index, info) {
		var row = templateRow.clone();
		if ($.inArray(info.orderState, UNABLEMODFIY) == -1) {
			row.find("td:eq(0) > a").attr("href", "javascript: modfiyOrder('" + info.orderId + "')")
									.text($.sht.msg.modfiyAction);
			row.find("td:eq(1) > a").attr("href", "javascript: cancelOrder('" + info.orderId + "')")
									.text($.sht.msg.cancelAction);
		}
		var remark = assembOrderRemark(info.orderRemark);
		row.find("td:eq(2) > a").attr("href", "javascript: enquiryOrderDetail('" + info.orderId + "')")
								.text($.sht.msg[info.orderState] + remark);
		row.find("td:eq(3)").text($.sht.msg[info.orderSide]);
		row.find("td:eq(4)").text(info.orderId);
		row.find("td:eq(5)").text(assembInstrCodeName(info.instrCode, info.instrName));
		row.find("td:eq(6)").text(chkNull(info.currency));
		row.find("td:eq(7)").text(fmtNumber(info.orderPrice, 3));
		row.find("td:eq(8)").text(info.triggerPrice ? fmtNumber(info.triggerPrice, 3) : "NA");
		row.find("td:eq(9)").text(info.initialQuantity);
		row.find("td:eq(10)").text($.sht.msg[info.orderType]);
		row.find("td:eq(11)").text(info.filledQty);
		row.find("td:eq(12)").text(info.outstandingQuantity);
		row.find("td:eq(13)").text(info.tradeAvgPrice != 0 ? fmtNumber(info.tradeAvgPrice, 3) : "NA");
		row.find("td:eq(14)").text($.sht.msg[info.channelType]);
		row.insertAfter($("#orderBook > tr:last")).removeClass("hide");
	});
	$("#orderBook > tr:odd").attr("className", "buy");
	splitPageInfo(response, enquiryOrderBook);
}

function showDcyTrade() {
	showTab('ui-south-content-dcy-trade');
	postTradeList({fromDate: getTodayStart(), toDate: getTodayEnd()}, cbTradeList);
//	postTradeList({fromDate: "2010-08-20 00:00:00", toDate: "2010-08-20 23:59:59"}, cbTradeList);
}

function cbTradeList(response) {
	TradeMap.clear();
	$.each(response.tradeListInfos.sort(function(a, b) {return parseInt(a.instrCode) > parseInt(b.instrCode) ? 1 : -1}), function(index, info) {
		if (TradeMap.containsKey($.trim(info.instrCode))) {
			var trade = TradeMap.get($.trim(info.instrCode));
			if (info.tradeSide == "B") {
				trade.buyQty += parseInt(info.executedQty);
				trade.buyAmount += parseFloat(info.executedQty * info.executedPrice);
			} else {
				trade.sellQty += parseInt(info.executedQty);
				trade.sellAmount += parseFloat(info.executedQty * info.executedPrice);
			}
		} else {
			TradeMap.put($.trim(info.instrCode), {
				instrCode: info.instrCode,
				instrName: info.instrName,
				currency: info.currency,
				buyQty: info.tradeSide == "B" ? parseInt(info.executedQty) : 0,
				buyAmount: info.tradeSide == "B" ? parseFloat(info.executedQty * info.executedPrice) : 0,
				sellQty: info.tradeSide == "S" ? parseInt(info.executedQty) : 0,
				sellAmount: info.tradeSide == "S" ? parseFloat(info.executedQty * info.executedPrice) : 0
			});
		}
	});
	enquiryDcTrade(1);
}

function enquiryDcTrade(index) {
	var tradeList = TradeMap.values();
	var end = index * PAGESIZE;
	if (end > tradeList.length) {
		end = tradeList.length;
	}
	var templateRow = $("#dcyTrade > tr.hide:first");
	$("#dcyTrade > tr").remove(":not(.hide)");
	var ccy;
	$.each(tradeList.slice((index - 1) * PAGESIZE, end), function (i, value) {
		var row = templateRow.clone();
		row.find("td:eq(0)").text(assembInstrCodeName(value.instrCode, value.instrName));
		ccy = chkNull(value.currency);
		row.find("td:eq(1)").text(ccy);
		row.find("td:eq(2)").text(fmtNumber(value.buyQty, 0));
		var buyAvgPrice = (value.buyAmount / value.buyQty).toFixed(2);
		buyAvgPrice = isNaN(buyAvgPrice) ? "0.00" : buyAvgPrice;
		row.find("td:eq(3)").text(buyAvgPrice);
		row.find("td:eq(4)").text(fmtNumber(value.sellQty, 0));
		var sellAvgPrice = (value.sellAmount / value.sellQty).toFixed(2);
		sellAvgPrice = isNaN(sellAvgPrice) ? "0.00" : sellAvgPrice;
		row.find("td:eq(5)").text(sellAvgPrice);
		if (value.buyQty && value.sellQty && value.buyQty >= value.sellQty) {
			var dc = (parseFloat(sellAvgPrice) - parseFloat(buyAvgPrice)) * value.sellQty;
			var cls = "";
			if (dc < 0) {
				cls = "fall";
			}
			row.find("td:eq(6)").find("span:eq(0)").addClass(cls).text(dc.toFixed(2)).end()
								.find("span:eq(1)").text(fmtNumber(value.sellQty, 0));
		} else {
			row.find("td:eq(6)").text("NA");
		}
		row.insertAfter($("#dcyTrade > tr:last")).removeClass("hide");
	});
	var totalPage = Math.ceil(tradeList.length / PAGESIZE);
	var currentPage = index;
	var nextPage = totalPage > currentPage ? currentPage + 1 : currentPage; 
	var res = {
		currentPage: currentPage,
		nextPage: nextPage,
		totalPage: totalPage
	};
	splitPageInfo(res, enquiryDcTrade);
	$("#dcyTrade > tr:odd").attr("className", "buy");
}

function enquiryOrderDetail(orderId) {
	postOrderDetail({orderId: orderId, hasHisories: "Y"}, cbShowOrderDetail);
}

function assembOrderInfo(info) {
	if (info.orderState != "REJECTED") {
		info.rejectMessage = '';
	} else {
		info.rejectMessage = $.sht.msg[info.rejectMessage];
	}
	info.stockCode = assembInstrCodeName(info.instrCode, info.instrName);
	info.orderState = $.sht.msg[info.orderState] + assembOrderRemark(info.orderRemark);;
	info.orderSideS = $.sht.msg[info.orderSide];
	info.channelType = $.sht.msg[info.channelType];
	info.validityType = $.sht.msg[info.validityType];
	info.allOrNothingFlag = $.sht.msg[info.allOrNothingFlag];
	info.orderTypeS = $.sht.msg[info.orderType];
	return info;
}

function modfiyOrder(orderId) {
	Token = new Date().getTime();
	postOrderDetail({orderId: orderId, hasHisories: "Y", token: Token}, cbShowModfiyOrder);
}

function cbShowModfiyOrder(response) {
	if (!response.orderDTO) {
		return;
	}
	$("#amendDialog input[type!='button']").val("");
	var info = assembOrderInfo(response.orderDTO);
	if (info.orderType == "CONDITIONAL") {
		info.conditionType = $.sht.msg[info.conditionType];
		$("#amendDialog tr.condition").show();
	} else {
		$("#amendDialog tr.condition").hide();
	}
	if (User.transactionProtection == "Y") {
		$("#amendDialog tr.tpPassword").show();
	} else {
		$("#amendDialog tr.tpPassword").hide();
	}
	$("#amendDialog #triggerPrice").val(info.triggerPrice);
	$("#amendDialog #newOrderPrice").val(info.orderPrice);
	
	$("#amendDialog #oldOrderPrice").val(info.orderPrice);
	$("#amendDialog #O_newOrderQty").val(info.initialQuantity + info.changedQty);
	
	$("#amendDialog #newOrderQty").val(info.initialQuantity + info.changedQty);
	$("#amendDialog #ainstrCode").val(info.instrCode);
	$("#amendDialog #orderType").val(info.orderType);
	$("#amendDialog #MCSOrderID").val(info.orderId);
	$("#amendDialog #orderSide").val(info.orderSide);
	$("#amendDialog #initialQuantity").val(info.initialQuantity);
	$("#amendDialog #changedQty").val(info.changedQty, 0);
	$("#amendDialog #filledQty").val(info.filledQty, 0);

	info.orderPrice = fmtNumber(info.orderPrice, 3);
	info.initialQuantityS = fmtNumber(info.initialQuantity, 0);
	info.changedQtyS = fmtNumber(info.changedQty, 0);
	info.filledQtyS = fmtNumber(info.filledQty, 0);
	info.outstandingQuantity = fmtNumber(info.outstandingQuantity, 0);
	fillElements($("#amendDialog"), info);
	$("#amendDialog").dialog("open");
	$("#amendDialog tr:not(:hidden)").removeClass("alternating")
									 .filter(":even").addClass("alternating");
}

function modfiyOrderAction() {
	if (checkModfiyOrderInput()) {
		var order = $("#amendDialogForm").serialize() + "&token=" + Token;
		postModfiyOrder(order, function(response) {
			$("#amendDialog").dialog("close");
			refreshOrderBook();
		});
	}
}

function cancelOrder(orderId) {
	Token = new Date().getTime();
	postOrderDetail({orderId: orderId, hasHisories: "Y", token: Token}, cbShowCancelOrder);
}

function cbShowCancelOrder(response) {
	if (!response.orderDTO) {
		return;
	}
	$("#cancelDialog input[type!='button']").val("");
	var info = assembOrderInfo(response.orderDTO);
	if (info.orderType == "CONDITIONAL") {
		info.condition = $.sht.msg[info.conditionType] + " " + info.triggerPrice;
		$("#cancelDialog tr.condition").show();
	} else {
		$("#cancelDialog tr.condition").hide();
	}
	if (User.transactionProtection == "Y") {
		$("#cancelDialog tr.tpPassword").show();
	} else {
		$("#cancelDialog tr.tpPassword").hide();
	}
	info = fmtOrderData(info);
	fillElements($("#cancelDialog"), info);
	$("#cancelDialog #aorderId").val(info.orderId);
	$("#cancelDialog").dialog("open");
	$("#cancelDialog tr:not(:hidden)").removeClass("alternating")
									  .filter(":even").addClass("alternating");
}

function cancelOrderAction() {
	if (checkCancelOrderInput()) {
		var order = $("#cancelDialogForm").serialize() + "&token=" + Token;
		postCancelOrder(order, function(response) {
			$("#cancelDialog").dialog("close");
			refreshOrderBook();
		});
	}
}

function cbShowOrderDetail(response) {
	if (!response.orderDTO) {
		return;
	}
	info = response.orderDTO;
	
	if (info.orderState != "COMPLETELY_FILLED") {
		$(".traing-status-detail-link").unbind("click");
		$(".traing-status-detail-link2").unbind("click");
	}
	//$("#tradeHisRight > label:not(.title)").remove();
	if(info.orderState=="COMPLETELY_FILLED"){
		var QTY=info.filledQty;
		//$("#tradeHisRight > label:last").after($("<label>").text(fmtNumber(info.filledQty, 0)))
		//								.after($("<label>").text(fmtNumber(info.tradeAvgPrice, 3)));
	
		$(".traing-status-detail-link").unbind("click").click(function () {
			var templateRow = $("#statusDetailDialog #statusDetailGrid > tr.hide:first");
			$("#statusDetailDialog #statusDetailGrid > tr").remove(":not(.hide)");
			
			var row = templateRow.clone();
			row.find("td:eq(0)").text(fmtNumber(info.orderPrice, 3));
			row.find("td:eq(1)").text(fmtNumber(QTY, 0));
			row.find("td:eq(2)").text('000001');
			row.insertAfter($("#statusDetailDialog #statusDetailGrid > tr:last")).removeClass("hide");
			
			$("#statusDetailDialog #statusDetailGrid > tr:not(:hidden):odd").attr("className", "alternating");
	        $("#statusDetailDialog").dialog("open");
	        
	    });
		$(".traing-status-detail-link2").unbind("click").click(function () {
			var templateRow = $("#statusDetailDialog2 #statusDetailGrid > tr.hide:first");
			$("#statusDetailDialog2 #statusDetailGrid > tr").remove(":not(.hide)");
			
			var row = templateRow.clone();
			row.find("td:eq(0)").text(fmtNumber(info.orderPrice, 3));
			row.find("td:eq(1)").text(fmtNumber(QTY, 0));
			row.insertAfter($("#statusDetailDialog2 #statusDetailGrid > tr:last")).removeClass("hide");
			$("#statusDetailDialog2").dialog("open");
		});
	} 
	
	info = assembOrderInfo(info);
	if (info.orderType == "CONDITIONAL") {
		info.condition = $.sht.msg[info.conditionType] + " " + info.triggerPrice;
		$("#statusDialog tr.condition").show();
	} else {
		$("#statusDialog tr.condition").hide();
	}
	info = fmtOrderData(info);
	fillElements($("#statusDialog"), info);
	
	$("#statusDialog").dialog("open");
	$("#statusDialog tr:not(:hidden)").removeClass("alternating")
									  .filter(":even").addClass("alternating");
}

function showPortfolio() {
	showTab('ui-south-content-account-enquiry');
	enquireAccount();
}

function enquireAccount() {
	var indexId = $("#acclist").val();
	HandlerContral = $("#acConfirm").attr("disabled", "disabled");
	postEnquireAccount({indexId: indexId}, cbEnquireAccount);
}

function cbEnquireAccount(response) {
	$(HandlerContral).removeAttr("disabled");
	HandlerContral = null;
	var cashHolding = {};
	cashHolding.totalPurchasingPower = response.totalPurchasingPower;
	cashHolding.onHoldBalance = response.onHoldBalance;
	$.each(cashHolding, function(name, value) {
		cashHolding[name] = fmtNumber(value, 2);
	});
	cashHolding.buyOrderControlLimit = response.buyOrderControlLimit == 0 ? $.sht.msg.NA : fmtNumber(response.buyOrderControlLimit, 2);
	fillElements($("#portfolio"), cashHolding);
}

function cbPlaceConfirm(response) {
	var orderSide = response.orderSide == "B" ? "buy" : "sell";
	$("#" + orderSide + "instrCode").val(response.instrCode);
	$("#" + orderSide + "orderSide").val(response.orderSide);
	$("#" + orderSide + "orderQuantityS").val(fmtNumber(response.orderQuantity, 0));
	$("#" + orderSide + "orderQuantity").val(response.orderQuantity);
	$("#" + orderSide + "orderPriceS").val(response.orderType != "AT_AUCTION" ? fmtNumber(response.orderPrice, 2) : $.sht.msg.NA);
	$("#" + orderSide + "orderPrice").val(response.orderPrice);
	$("#" + orderSide + "orderType").val(response.orderType);
	$("#" + orderSide + "orderTypeS").val($.sht.msg[response.orderType]);
	$("#" + orderSide + "conditionType").val(response.conditionType);
	$("#" + orderSide + "conditionTypeS").val($.sht.msg[response.conditionType]);
	$("#" + orderSide + "triggerPrice").val(response.conditionType != "" ? response.triggerPrice : $.sht.msg.NA);
	$("#" + orderSide + "triggerPriceS").val(response.conditionType != "" ? fmtNumber(response.triggerPrice, 2) : $.sht.msg.NA);
	$("#" + orderSide + "stampCharge").val(response.orderType != "AT_AUCTION" ? fmtNumber(response.calOrderFeeResponse.commonCharges + 0, 2) : $.sht.msg.NA);
	$("#" + orderSide + "otherFee").val(response.orderType != "AT_AUCTION" ? fmtNumber(response.calOrderFeeResponse.ccassCharge +
										response.calOrderFeeResponse.levyCharge + response.calOrderFeeResponse.stampCharge +
										response.calOrderFeeResponse.tradingFee + 0, 2) : $.sht.msg.NA);
	NetAmount = response.orderType != "AT_AUCTION" ? fmtNumber(response.calOrderFeeResponse.netAmount, 2) : $.sht.msg.NA;
	$("#" + orderSide + "netAmount").val(NetAmount);
	$("#" + orderSide + "Currency").val(chkNull(response.currency));
	$("div.ui-north-pannel-" + orderSide).show();
}

function cbCallMos(response) {
	CalMOS = response.calMOSResponse;
}

function proByPass() {
	if (User.transactionProtection == "Y") {
    	$("#ticketOrder .tpPassword").show();
    } else {
    	$("#ticketOrder .tpPassword").hide();
    }
}

function cbPlaceOrder(response) {
	$("#confirmmcsOrderId").text(response.orderDTO.orderId);
	$("#confirmorderSide").text($.sht.msg[response.orderDTO.orderSide]);
	$("#confirminstrCode").text(response.orderDTO.instrCode);
	$("#confirmorderQuantity").text(fmtNumber(response.orderDTO.initialQuantity, 0));
	$("#confirmorderPrice").text(response.orderDTO.orderPrice ? fmtNumber(response.orderDTO.orderPrice, 3) : $.sht.msg.NA);
	$("#confirmnetAmount").text(NetAmount);
	$("#confirmnetCurrency").text(chkNull(response.orderDTO.currency));
	$(".ui-north-pannel-buy, .ui-north-pannel-sell").hide()
													.find("input.normal-area, input.value-area, input[type='hidden']").val("");
	$(HandlerContral).removeAttr("disabled");
	HandlerContral = null;
	resetPlaceOrder();
	$("#confirmRequestDialog").dialog("open");
	refreshOrderBook();
	postCallMos({}, cbCallMos);
}

function resetPlaceOrder(order) {
	var data = {
		instrCode: "",
		orderQuantity: "",
		orderPrice: "",
		password: "",
		orderType: "ENHANCED_LIMIT",
		conditionType: "GTE",
		triggerPrice: ""
	};
	$.extend(data, order);
	fillElementsValue($("#ticketOrder"), data);
	$("#chkBuyAll").removeAttr("checked");
	$("#ticketOrder #orderPrice").removeClass("inputDisabled").removeAttr("disabled");
	$("#ticketOrder #orderQuantity").removeClass("inputDisabled").removeAttr("readonly");
	//$("#ticketOrder #triggerPrice").addClass("inputDisabled").attr("disabled", "disabled");
	$("#ticketOrder #conditionType").attr("disabled", "disabled");
	$.jNice.SelectUpdate($("#ticketOrder #orderType"));
	$.jNice.SelectUpdate($("#ticketOrder #conditionType"));
}

function chgOrderType(el) {
	var obj = $("#ticketOrder #orderType");
	var TYPE = "ENHANCED_LIMIT"
	if (el.value != TYPE) {
		$("#CommonDialog").dialog("open");
	} 
}

function showBypassPassword() {
	if (User.transactionProtection == "Y") {
		$("#rdUsePassword").attr("checked", "checked");
		$(".bypass-password-pop-password").addClass("disabled").find("input").attr("disabled", "disabled").val("");
	} else {
		$("#rdBypassPassword").attr("checked", "checked");
		$(".bypass-password-pop-password").removeClass("disabled").find("input").attr("disabled", "");
	}
	$("#bypassPasswordDialog").dialog("open");
}

function modfiyTP() {
	if ($("#rdBypassPassword").is(":checked") && !checkPsw($("#tPassword"))) {
		return;
	}
	postModfiyTP($("#modfiyTPForm").serialize(), cbModfiyTP);
}

function cbModfiyTP(response) {
	User.transactionProtection = response.transactionProtectionStatus;
	alert($.sht.msg["WEB051099"]);
	if (User.transactionProtection == "Y") {
		$("#ticketOrder .tpPassword").val('').show();
	} else {
		$("#ticketOrder .tpPassword").hide();
		$("#ticketOrder .tpPassword #password").val('');
	}
	$("#bypassPasswordDialog").dialog("close");
}

function enquiryLotSize(stockcode) {
	stockcode = fmtStockCode(stockcode);
	if (!RTQInfos.containsKey(stockcode)) {
		postEnquireShortRTQ({instrCode: stockcode}, cbEnquireLotSize);
	}
	return RTQInfos.get(stockcode);
}

function cbEnquireLotSize(response) {
	var rtqInfo = response.enquireShortRTQResponse[0];
	if (rtqInfo != undefined && !isNaN(rtqInfo.code) && !isNaN(rtqInfo.lotSize)) {
		RTQInfos.put(rtqInfo.code, parseInt(rtqInfo.lotSize));
	}
}

function ableCalBuyAll() {
	if ($("#chkBuyAll").is(":checked") &&
		$("#ticketOrder #instrCode").val() != "" &&
		$("#ticketOrder #orderPrice").val() != "") {
		calBuyAll(enquiryLotSize($("#ticketOrder #instrCode").val()), parseFloat($("#ticketOrder #orderPrice").val()));
	}
}

function calFee(qty, price) {
	var transactionAmount = qty * price;
	var commonCharges = transactionAmount * 0.05 / 100;
	if (commonCharges < 25) {
		commonCharges = 25;
	}
	var stampCharge = transactionAmount * 0.1 / 100;
	if (stampCharge < 1) {
		stampCharge = 1;
	}
	var levyCharge = transactionAmount * 0.005 / 100;
	var tradingFee = transactionAmount * 0.003 / 100;
	var ccassCharge = transactionAmount * 0.002 / 100;
	if (ccassCharge > 100) {
		ccassCharge = 100;
	} else if (ccassCharge < 2) {
		ccassCharge = 2;
	}
	return commonCharges + stampCharge + levyCharge + tradingFee + ccassCharge;
}

function returnBuyAllQty(qty, price, lotsize) {
	var remain = CalMOS.totalPurchasingPower - qty * price;
	if (remain < calFee(qty, price)) {
		qty -= lotsize;
		if (qty > 0) {
			return returnBuyAllQty(qty, price, lotsize);
		} else {
			qty = 0;
		}
	}
	return qty;
}

function calBuyAll(lotsize, price) {
	var orderQuantity = 0
	if (price > 0 && lotsize > 0) {
		orderQuantity = Math.min(parseInt(CalMOS.totalPurchasingPower / price / lotsize) * lotsize, 3000 * lotsize);
		orderQuantity = returnBuyAllQty(orderQuantity, price, lotsize);
	}
	$("#ticketOrder #orderQuantity").val(orderQuantity);
}


function checkMOS(price, qty, orderSide) {
	if (orderSide == "B") {
		var remainder = CalMOS.totalPurchasingPower - price * qty ;//- calFee(qty, price);
		if (remainder < 0) {
			remainder = fmtNumber(Math.abs(remainder), 2);
//			if (confirm($.sht.msg["SHT010"].replace(/P0/, remainder))) {
//				changeCenterSrc('../webEPaymentEntrance.do');
//			}
			
			if (confirm($.sht.msg["SHT010"].replace(/P0/, remainder))) {
				 $("#CommonDialog").dialog("open");
			}
			//alert($.sht.msg["SHT010"].replace(/P0/, remainder));
			
			return false;
		}
	}
	return true;
}

function checkOrderInput(orderSide) {
	$("#ticketOrder #orderType").val("ENHANCED_LIMIT");
	$.jNice.SelectUpdate($("#ticketOrder #orderType"));
	var result = checkTradeFlag() &&
				 checkBuyAll(orderSide) &&
				 checkStockCode($("#ticketOrder #instrCode")) &&
				 checkQty($("#ticketOrder #orderQuantity"), $("#ticketOrder #instrCode").val()) &&
				 ($("#ticketOrder #orderType").val() == "AT_AUCTION" || (checkPrice($("#ticketOrder #orderPrice")) && checkMOS(parseFloat($("#ticketOrder #orderPrice").val()), parseInt($("#ticketOrder #orderQuantity").val()), orderSide))) &&
				 (User.transactionProtection == "N" || checkPsw($("#ticketOrder #password"))) &&
				 ($("#ticketOrder #orderType").val() != "CONDITIONAL" || checkTriggerPrice($("#ticketOrder #triggerPrice"), parseFloat($("#ticketOrder #orderPrice").val()), orderSide, $("#ticketOrder #conditionType").val()));
	return result;
}

function checkModfiyOrderInput() {
	var result = checkTradeFlag() &&
				 checkQty($("#amendDialog #newOrderQty"), $("#amendDialog #ainstrCode").val()) &&
				 checkedNochanged($("#amendDialog #newOrderQty"),$("#amendDialog #O_newOrderQty"),$("#amendDialog #newOrderPrice"),$("#amendDialog #oldOrderPrice"))&&
				 checkModfiyQty($("#amendDialog #newOrderQty"), parseInt($("#amendDialog #initialQuantity").val()), parseInt($("#amendDialog #filledQty").val()), parseInt($("#amendDialog #changedQty").val())) &&
				 ($("#amendDialog #orderType").val() == "AT_AUCTION" || checkPrice($("#amendDialog #newOrderPrice"))) &&
				 (User.transactionProtection == "N" || checkPsw($("#amendDialog #tp_password"))) &&
				 ($("#amendDialog #orderType").val() != "CONDITIONAL" || checkTriggerPrice($("#amendDialog #triggerPrice"), parseFloat($("#amendDialog #newOrderPrice").val()), $("#amendDialog #orderSide").val(), $("#amendDialog #conditionType").text()));
	return result;
}

function checkCancelOrderInput() {
	return checkTradeFlag() && (User.transactionProtection == "N" || checkPsw($("#cancelDialog #c_password")));
}

function splitPageInfo(response, fn) {
	$("div.south-div-pager a.first").unbind("click").click(function() {
		fn(1);
	});
	if (response.currentPage == 1) {
		$("div.south-div-pager a.prev").addClass("disabled").unbind("click");
	} else {
		$("div.south-div-pager a.prev").removeClass("disabled").unbind("click").click(function() {
			var index = response.currentPage > 1 ? response.currentPage - 1 : 1;
			fn(index);
		});
	}
	if (response.currentPage == response.totalPage) {
		$("div.south-div-pager a.next").addClass("disabled").unbind("click");
	} else {
		$("div.south-div-pager a.next").removeClass("disabled").unbind("click").click(function() {
			fn(response.nextPage);
		});
	}
	$("div.south-div-pager a.last").unbind("click").click(function() {
		fn(response.totalPage);
	});
	$("div.south-div-pager span.pages").text($.sht.msg["pageInfo"].replace(/P0/, response.currentPage).replace(/P1/, response.totalPage));
}

function init() {
    //Init tooltips
    $(".font-size").tooltip();
    $(".title-tooltip").tooltip({ left: 0, width: 160 });
    $(".symbol-1").tooltip({ left: -3 });
    $(".symbol-2").tooltip({ left: -3 });
    $(".symbol-3").tooltip({ left: -3 });

    //Trading confirm button click
    $(".btn-buy").click(function () {
    	if (checkOrderInput("B")) {
            Token = new Date().getTime();
            var order = $("#ticketOrder").serialize();
            order = order + "&orderSide=B&token=" + Token;
        	postOrderFee(order, cbPlaceConfirm);
    	}
    });
    $(".btn-sell").click(function () {
    	if (checkOrderInput("S")) {
        	Token = new Date().getTime();
        	var order = $("#ticketOrder").serialize();
            order = order + "&orderSide=S&token=" + Token;
        	postOrderFee(order, cbPlaceConfirm);
    	}
    });
    $(".btn-cancel").click(function () {
        $(".ui-north-pannel-buy, .ui-north-pannel-sell").hide();
    });
    $("#ticketOrder #instrCode").blur(function () {
        if ($(this).val() != "") {
        	$(this).val(fmtStockCode($(this).val()));
			enquiryLotSize($(this).val());
			ableCalBuyAll();
        }
    });
    $("#ticketOrder #orderPrice").blur(ableCalBuyAll);
    $("#chkBuyAll").change(function() {
        if ($(this).is(":checked")) {
        	if ($("#ticketOrder #orderType").val() == "AT_AUCTION") {
        		alert($.sht.msg["SHT008"]);
        		$(this).removeAttr("checked");
        		return;
        	}
        	$("#ticketOrder #orderQuantity").addClass("inputDisabled").attr("readonly", "readonly").val("");
        	ableCalBuyAll();
        } else {
        	$("#ticketOrder #orderQuantity").removeClass("inputDisabled").removeAttr("readonly");
        }
    });
  //Init pop dialogs
    $("#confirmRequestDialog, #noticeDialog, #bypassPasswordDialog,#AllbuyinDialog, #feesAndChargesDialog, #CommonDialog").dialog({
        autoOpen: false,
        modal: true,
        width: 400
    });
    $("#amendDialog, #cancelDialog, #statusDialog, #statusDetailDialog, #statusDetailDialog2").dialog({
        autoOpen: false,
        modal: true,
        width: 400,
        height: 590
    });
  //Bypass password pop dialog
    $("#lkBypassPassword").click(showBypassPassword);
    $("#btnCancelBypassPassword").click(function () {
        $("#bypassPasswordDialog").dialog("close");
    });

    $("#rdUsePassword").change(function () {
        $(".bypass-password-pop-password").addClass("disabled").find("input").attr("disabled", "disabled").val("");
    });
    $("#rdBypassPassword").change(function () {
        $(".bypass-password-pop-password").removeClass("disabled").find("input").attr("disabled", "");
    });

    //Fees & Charges pop dialog
    $(".lk-fees-and-charges").click(function () {
        $("#feesAndChargesDialog").dialog("open");
    });

    //Trading pop confirm dialog
    $(".btn-confirm").click(function () {
        var order = $(this).parents("form").serialize() + "&token=" + Token;
        HandlerContral = $(this).attr("disabled", "disabled");
        postPlaceOrder(order, cbPlaceOrder);
    });
    //All Buy In
    $("#lkallbuyin").click(function () { 
        $("#AllbuyinDialog").dialog("open"); 
    }); 
    $("#btnCancellkallbuyin").click(function () { 
        $("#AllbuyinDialog").dialog("close"); 
    }); 

    $("#rdUsePassword").change(function () { 
        $(".bypass-password-pop-password").addClass("disabled").find("input").attr("disabled", "disabled").val(""); 
    }); 
    $("#rdBypassPassword").change(function () { 
        $(".bypass-password-pop-password").removeClass("disabled").find("input").attr("disabled", ""); 
    }); 
    $(".ui-south-grid").tablesorter().bind("sortEnd",function() { 
		$(".ui-south-grid tbody tr").removeClass("alternating");
		$(".ui-south-grid tbody tr:odd").addClass("alternating");
	});
  //South grid pop dialog
    $(".trading-amend-link").click(function () {
        $("#amendDialog").dialog("open");
    });
    $("#btnBackAmend").click(function () {
        $("#amendDialog").dialog("close");
    });
    $(".trading-cancel-link").click(function () {
        $("#cancelDialog").dialog("open");
    });
    $("#btnBackCancel").click(function () {
        $("#cancelDialog").dialog("close");
    });
    $(".trading-status-link").click(function () {
        $("#statusDialog").dialog("open");
    });
	$(".traing-status-detail-link").click(function () {
        $("#statusDetailDialog").dialog("open");
    });
    $(".amend-cancel-link").click(function () {
        $("#noticeDialog").dialog("open");
    });
    //---------------------------------
    //Common Dialog
    $("a[class*='forbidOp']").click(function () {
        $("#CommonDialog").dialog("open");
    });
    $("#btnCloseDialog").click(function () {
        $("#CommonDialog").dialog("close");
    });
    //----------------------------------
	$(".header-lang ." + LOCALE).addClass("active").ajaxError(function(event, XMLHttpRequest, ajaxOptions, thrownError) {
		alert($.sht.msg.SHT011);
		//alert($.sht.msg["SHT008"]);
		if (HandlerContral) {
			$(HandlerContral).removeAttr("disabled");
			HandlerContral = null;
		}
	//location.href = "../index.do";
	});
	refreshOrderBook();
	postCallMos({}, cbCallMos);
	proByPass();
	resetPlaceOrder();
	setInterval(function () {
		enquiryOrderBook(OrderBookIndex);
		postCallMos({}, cbCallMos);
	}, 300000);
	//refreshEMCCount();
//setInterval(function(){refreshEMCCount()}, 10000);
}

function refreshEMCCount(){
	postEMCCount({}, showEMCCount);
}
function showEMCCount(response){
	$("#EMCCount").text(response.msgCounts);
}

chkNull = function(o) {
	if (o == null || o == '') {
		o = CURRENCY;
	}
	return o
}
