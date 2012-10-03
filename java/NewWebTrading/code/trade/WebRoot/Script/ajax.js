function checkHeader(response) {
	if (response.errorCode != undefined) {
		if (Timer) {
			clearInterval(Timer)
		}
		alert($.sht.msg[response.errorCode] ? $.sht.msg[response.errorCode] : response.errorCode);
		window.location = ContextPath + "/index.do";
		return false;
	}
	if (response.returnCode != "NORMAL") {
		alert($.sht.msg[response.returnCode] ? $.sht.msg[response.returnCode] : response.returnCode);
		if (HandlerContral) {
			$(HandlerContral).removeAttr("disabled");
			HandlerContral = null;
		}
		return false;
	}
	return true;
}

function addCLV(data) {
	if (typeof(data) != "string") {
		$.extend(data, {CLV: $.sht.clv});
	} else {
		data = data + "&CLV=" + $.sht.clv;
	}
	return data;
}

function postData(data, url, fn) {
	$.post(
		url,
		addCLV(data),
		function(response) {
			if (checkHeader(response))
				fn(response);
		},
		"json"
	);
}

function advPostDate(data, url, fn, async) {
	$.ajax({
		type: "post",
		url: url,
		data: addCLV(data),
		async: async && true,
		dataType: "json",
		success: function(response) {
			if (checkHeader(response))
				fn(response);
		}
	});
}

function postPlaceOrder(request, fn) {
	advPostDate(request, "../webPlaceOrder.do", fn, false);
}

function postOrderFee(request, fn) {
	advPostDate(request, "../webOrderFee.do", fn, false);
}

function postCancelOrder(request, fn) {
	postData(request, "../webCancelOrder.do", fn);
}

function postModfiyOrder(request, fn) {
	postData(request, "../webModifyOrder.do", fn);
}

function postEnquireShortRTQ(request, fn) {
	advPostDate(request, "../webEnquireShortRTQ.do", fn, false);
}

function postEnquireDelayRTQ(request, fn) {
	postData(request, "../webEnquireDelayRTQ.do", fn);
}

function postOrderDetail(request, fn) {
	postData(request, "../webOrderDetail.do", fn);
}

function postModifyDetail(request, fn) {
	postData(request, "../webModifyDetail.do", fn);
}

function postEnquireStockPosition(request, fn) {
	postData(request, "../webEnquireStockPosition.do", fn);
}

function postEnquireAccount(request, fn) {
	postData(request, "../webEnquireAccount.do", fn);
}

function postListOrder(request, fn) {
	postData(request, "../webListOrder.do", fn);
}

function postTradeList(request, fn) {
	postData(request, "../webTradeList.do", fn);
}

function postCallMos(request, fn) {
	$.ajax({
		type: "post",
		url: "../webCallMos.do",
		data: addCLV(request),
		dataType: "json",
		global: false,
		success: function(response) {
			if (response.returnCode == "NORMAL")
				fn(response);
		}
	});
}

function postModfiyTP(request, fn) {
	postData(request, "../webTP.do", fn);
}

function postEMCCount(request, fn){
	postData(request, "../webEMCCount.do", fn);
}


