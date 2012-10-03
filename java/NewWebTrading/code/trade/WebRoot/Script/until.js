function isInputNum(k) {
	return k >= 48 && k <= 57;
}

function isInputCtl(k) {
	return k < 32 || k >= 127;
}

function isCharacter(k) {
	return (k > 64 && k < 91) || (k > 96 && k < 123);
}

function isPointKey(k) {
	return k == 46;
}

function browserKey(event) {
	var code;
	if ($.browser.msie) {
		code = event.keyCode;
	} else {
		code = event.which;
	}
	return code;
}

function numKeyPress(event){
	var key = browserKey(event);
	event.returnValue = isInputNum(key) || isInputCtl(key);
	return event.returnValue;
}

function pswKeyPress(event) {
	var key = browserKey(event);
	event.returnValue = isCharacter(key) || isInputNum(key) || isInputCtl(key);
	return event.returnValue;
}

function priceKeyPress(event) {
	var key = browserKey(event);
	event.returnValue = isInputNum(key) || isInputCtl(key) || isPointKey(key);
	return event.returnValue;
}

function filterCodeKeyPress(event) {
	var key = browserKey(event);
	if (key == 13) {
		filterOrderBook();
		return true;
	} else {
		event.returnValue = isInputNum(key) || isInputCtl(key) || isPointKey(key);
	}
	return event.returnValue;
}

function priceKeyUp(el) {
	if (!/^\d+\.?\d{0,3}$/.test(el.value)) {
		var v = el.value.match(/^\d+\.?\d{0,3}/);
		el.value = v ? v : "";
	}
}

function checkSpread(price) {
	price = parseFloat(price);
	var spread = calSpread(price);
	var tmpPrice = Math.round(price * Math.pow(10, 4));
	var tmpSpread = Math.round(spread * Math.pow(10, 4));
	return tmpPrice % tmpSpread == 0;
}

function checkStockCode(stockCode) {
	var code = $(stockCode).val();
	if (code == "") {
		alert($.sht.msg["SHT012"]);
		$(stockCode).select()[0].focus();
		return false;
	} else if (!/\d{1,5}/.test(code) || code == 0) {
		alert($.sht.msg["SHT001"]);
		$(stockCode).select()[0].focus();
		return false;
	}
	return true;
}

function checkPrice(price) {
	var priceVal = $(price).val();
	if (priceVal == "") {
		alert($.sht.msg["SHT013"]);
		$(price).select()[0].focus();
		return false;
	} else if (!/^\d+\.?\d{0,3}$/.test(priceVal)) {
		alert($.sht.msg["SHT002"]);
		$(price).select()[0].focus();
		return false;
	} else if (priceVal <= 0 || !checkSpread(priceVal)) {
		alert($.sht.msg["SHT004"]);
		$(price).select()[0].focus();
		return false;
	}
	return true;
}

function checkQty(qty, stockCode) {
	var qtyVal = $(qty).val();
	if (qtyVal == "") {
		alert($.sht.msg["SHT014"]);
		$(qty).select()[0].focus();
		return false;
	} else if (!/\d+/.test(qtyVal)) {
		alert($.sht.msg["SHT003"]);
		$(qty).select()[0].focus();
		return false;
	} else {
		var lotsize = enquiryLotSize(stockCode);
		if (qtyVal <= 0 || (lotsize && qtyVal % lotsize != 0)) {
			alert($.sht.msg["sys018"]);
			$(qty).select()[0].focus();
			return false;
		} else if (lotsize && qtyVal > 3000 * lotsize) {
			alert($.sht.msg["MCS00311"]);
			$(qty).select()[0].focus();
			return false;
		}
	}
	return true;
}

function checkModfiyQty(newQty, initQty, filledQty, changedQty) {
	var newQtyVal = $(newQty).val();
	if (newQtyVal == "") {
		alert($.sht.msg["SHT014"]);
		return false;
	} else if (newQtyVal < filledQty || newQtyVal > initQty + changedQty) {
		alert($.sht.msg["SHT003"]);
		return false;
	}
	return true;
}

function checkPsw(psw) {
	var pswVal = $(psw).val();
	if (pswVal == "") {
		alert($.sht.msg["BLANKPASS"]);
		$(psw).select()[0].focus();
		return false;
	} else if (!/^[A-Za-z0-9]{6,8}$/.test(pswVal)) {
		alert($.sht.msg["INVIDPASS"]);
		$(psw).select()[0].focus();
		return false;
	}
	return true;
}

function checkTriggerPrice(triggerPrice, orderPrice, orderSide, conditionType) {
	var triggerPriceVal = parseFloat($(triggerPrice).val());
	if (triggerPriceVal == "") {
		alert($.sht.msg["SHT015"]);
		$(triggerPrice).select()[0].focus();
		return false;
	} else if (!/^\d+\.?\d{0,3}$/.test(triggerPriceVal)) {
		alert($.sht.msg["SHT005"]);
		$(triggerPrice).select()[0].focus();
		return false;
	} else if (triggerPriceVal <= 0 || !checkSpread(triggerPriceVal)) {
		alert($.sht.msg["SHT006"]);
		$(triggerPrice).select()[0].focus();
		return false;
	}
	var spread = calSpread(orderPrice);
	if (orderSide == "B") {
		if (triggerPriceVal > parseFloat((orderPrice + 15 * spread).toFixed(3)) ||
			triggerPriceVal < parseFloat((orderPrice - 2 * spread).toFixed(3))) {
			alert($.sht.msg["SHT005"]);
			$(triggerPrice).select()[0].focus();
			return false;
		}
	} else if (triggerPriceVal > parseFloat((orderPrice + 2 * spread).toFixed(3)) ||
			   triggerPriceVal < parseFloat((orderPrice - 15 * spread).toFixed(3))) {
		alert($.sht.msg["SHT005"]);
		$(triggerPrice).select()[0].focus();
		return false;
	}
	if (conditionType == "≥" || conditionType == "GTE") {
		if (orderPrice >= triggerPriceVal) {
			return confirm($.sht.msg["SHT007"]);
		}
	} else if (orderPrice <= triggerPriceVal) {
		return confirm($.sht.msg["SHT007"]);
	}
	return true;
}

function checkTradeFlag() {
	if (User.allowTradeStatusFlag != "Y") {
		alert($.sht.msg["sys011"]);
		return false;
	}
	return true;
}

function checkBuyAll(orderSide) {
	if (orderSide == "S" && $("#chkBuyAll").is(":checked")) {
		alert($.sht.msg["SHT009"]);
		return false;
	}
	return true;
}

function fmtNumber(cellval, decimalPlaces) {
	return $.fmatter.util.NumberFormat(cellval, {thousandsSeparator: ',', decimalPlaces: decimalPlaces});
}

function fmtStockCode(stockcode) {
	return "00000".substr(0, 5-stockcode.length).concat(stockcode);
}

function fmtOrderData(info) {
	info.orderPrice = info.orderType != "AT_AUCTION" ? fmtNumber(info.orderPrice, 3) : $.sht.msg.NA;
	info.initialQuantity = fmtNumber(info.initialQuantity, 0);
	info.changedQty = fmtNumber(info.changedQty, 0);
	info.filledQty = fmtNumber(info.filledQty, 0);
	info.outstandingQuantity = fmtNumber(info.outstandingQuantity, 0);
	return info;
}

function fillElements(form, data) {
	for (var prop in data) {
		$(form).find("#" + prop + ":not(input)").text(data[prop]);
	}
}

function fillElementsValue(form, data) {
	for (var prop in data) {
		$(form).find("#" + prop).val(data[prop]);
	}
}

function calSpread(price) {
	var spread = 0;
	if (price >= 0.01 && price <= 0.25)
		spread = 0.001;
	else if (price > 0.25 && price <= 0.50)
		spread = 0.005;
	else if (price > 0.50 && price <= 10.00)
		spread = 0.010;
	else if (price > 10.00 && price <= 20.00)
		spread = 0.020;
	else if (price > 20.00 && price<= 100.00)
		spread = 0.050;
	else if (price > 100.00 && price<= 200.00)
		spread = 0.100;
	else if (price > 200.00 && price<= 500.00)
		spread = 0.200;
	else if (price > 500.00 && price<= 1000.00)
		spread = 0.500;
	else if (price > 1000.00 && price<= 2000.00)
		spread = 1.000;
	else if (price > 2000.00 && price<= 5000.00)
		spread = 2.000;
	else
		spread = 5.000;

	return spread;
}

function getToday() {
	var d = new Date();
	var s = "";
	s += d.getFullYear() + "-";
	var month = d.getMonth() + 1;
	if (month < 10) {
		month = "0" + month;
	}
	s += month + "-";
	var day = d.getDate();
	if (day < 10) {
		day = "0" + day;
	}
	s += day;
	return s;
}

function getTodayStart() {
	return getToday() + " 00:00:00";
}

function getTodayEnd() {
	return getToday() + " 23:59:59";
}

function assembInstrCodeName(code, name) {
	return code + " " + $.trim(name).substr(0, 8);
}

function assembOrderRemark(orderRemark) {
	if (orderRemark.lastIndexOf("Success") != -1) {
		return "*";
	} else if (orderRemark.lastIndexOf("Failed") != -1) {
		return "#";
	} else if (orderRemark.lastIndexOf("Progress") != -1) {
		return "@";
	} else {
		return "";
	}
}