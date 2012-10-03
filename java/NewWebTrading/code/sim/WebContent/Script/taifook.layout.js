/**
* Taifook Layout.
* @requires jQuery v1.2 or above
*
* Copyright (c) 2010 Achievo Corporation (http://www.achievo.com)
*
* Version: 1.0.0
*
* Achievo Corporation
* Created by Creator Liu at 2010-11-12
*
*/
var uiContainerHeight = 695;
var uiSouthMini = 20;
var uiSouthMax = 180;
var uiPannelWidth = 200;
var uiPannelMiniHeight = 56;

var setContainerPosition = function () {
    var clientHeight = document.documentElement.clientHeight;
    var marginTop = clientHeight > uiContainerHeight ? (clientHeight - uiContainerHeight) / 2 + "px" : "0";
    $(".warp").css("margin-top", marginTop);
};
//Get center height
var getCenterHeight = function () {
    return $("#container").height() - $(".ui-south").outerHeight() - $(".ui-north").outerHeight() - 7;
}
//Set center height
var setCenterHeight = function () {
    $(".ui-center-main").height(getCenterHeight());
    $(".ui-center-content").height(getCenterHeight() - 10);
}
//Set trading form height
var setOpPannelHeight = function () {
    $(".ui-north-pannel").height(getCenterHeight() + uiPannelMiniHeight - 3);
}
var setSouthHeight = function () {
    if ($(".ui-south").height() == uiSouthMax) {
        hideSouthPannel();
    }
    else {
        showSouthPannel();
    }
};
var southOpen = "ui-toggler-south-open";
var southClose = "ui-toggler-south-close";
var showSouthPannel = function () {
    $(".ui-toggler-south").removeClass(southClose).addClass(southOpen);
    $(".ui-south").height(uiSouthMax).effect("slide", { direction: "down" }, 100);
    $(".ui-south-tab-content").show();
    setCenterHeight();
}
var hideSouthPannel = function () {
    $(".ui-toggler-south").removeClass(southOpen).addClass(southClose);
    $(".ui-south").height(uiSouthMini).effect("slide", { direction: "up" }, 100);
    $(".ui-south-tab-content").hide();
    setCenterHeight();
}
//Set center width when open trading form
var centerWidth = $(".ui-center-main").width();
var showTradingForm = function() {
    setOpPannelHeight();
    $(".ui-north-pannel").slideDown(100);
    $(".ui-center-main").width(centerWidth - uiPannelWidth).css("margin-left", uiPannelWidth);
}
var hideTradingForm = function() {
    $(".ui-north-pannel").slideUp(100);
    $(".ui-center-main").width(centerWidth).css("margin-left", 0);
}
$(document).ready(function () {
    /*UI layout*/
    //UI south close and open
    $(".ui-toggler-south").click(function () {
        setSouthHeight();
        setOpPannelHeight();
    });
    $(".ui-south-tab li").click(function () {
        $(".ui-south-tab li").each(function () { $(this).removeClass("active"); });
        $(this).addClass("active");
        if ($(".ui-south").height() == uiSouthMax)
            return;
        setSouthHeight();
        setOpPannelHeight();
    });

    var initModules = function () {
        setContainerPosition();
        setCenterHeight();
    }
    initModules();
    $(window).resize(function () {
        setContainerPosition();
    });

    //Pop trading form when click
    $(".home-btn-buy-sell").click(function () {
        showTradingPannel();
    });
    //Close trading form when click
    $(".ui-north-pannel-close").click(function () {
        hideTradingForm();
    });

    /*Header menu*/
    $(".header-menu-trading, .header-menu-account-service").mousemove(function () {
        $(this).find("a").first().addClass("active")
        $(this).find("div").first().show();
    }).mouseout(function () {
        $(this).find("a").first().removeClass("active")
        $(this).find("div").first().hide();
    });
    $(".header-menu-trading a, .header-menu-account-service a").click(function () {
        $(".header-menu-trading-div, .header-menu-account-service-div").hide();
    });

    /*Init top menu*/
    if ($(".menu-container")[0] != undefined) {
        $(".menu-container").jCarouselLite({
            btnNext: ".menu-next",
            btnPrev: ".menu-prev",
            btnMini: ".ui-menu-style-mini",
            btnNormal: ".ui-menu-style-normal",
            circular: false,
            speed: 300,
            mouseWheel: true
        });
        //Menu sort
        $(".menu-container ul").sortable({
            update: function (event, ui) {
                //TODO: if you want to remeber sorted manu, save these DOM.
            }
        });
    }
    //Set active status when click menu item
    $(".menu-container a").click(function () {
        $(".menu-container a").removeClass("active");
        $(this).addClass("active");
    });
    //Set menu as mini or max style
    $(".ui-north-right").hover(function () {
        $(".ui-menu-style").show().effect("slide", { direction: "up" }, 200);
    }, function () {
        $(".ui-menu-style").hide();
    });
    $(".ui-menu-style-normal").click(function () {
        $(".ui-menu-style-mini").removeClass("ui-menu-style-mini-active");
        $(this).addClass("ui-menu-style-normal-active");
        $(".menu-container").removeClass("menu-mini");
    });
    $(".ui-menu-style-mini").click(function () {
        $(".ui-menu-style-normal").removeClass("ui-menu-style-normal-active");
        $(this).addClass("ui-menu-style-mini-active");
        $(".menu-container").addClass("menu-mini");
    });

    /*Change font size*/
    function changeFontSize(fontSize) {
        $.cookie("font-size", fontSize);
        $("body").css("font-size", fontSize);
        if ($("#center-frame")[0] != undefined) {
            var src = $("#center-frame")[0].contentWindow.document.location;
            $("#center-frame").attr("src", src);
        }
    }

    var cookieFontSize = $.cookie("font-size");
    if (cookieFontSize != null) {
        changeFontSize(cookieFontSize);
        $(".font-size a").removeClass("active");
        if (cookieFontSize == "70%" || cookieFontSize == "75%")
            $(".font-size-en-N, .font-size-N").addClass("active");
        if (cookieFontSize == "80%" || cookieFontSize == "85%")
            $(".font-size-en-B, .font-size-B").addClass("active");
        if (cookieFontSize == "90%" || cookieFontSize == "95%")
            $(".font-size-en-L, .font-size-L").addClass("active");
    }

    $(".font-size a").click(function () {
        $(".font-size a").removeClass("active");
        $(this).addClass("active");
    });

    $(".font-size-en-N").click(function () { changeFontSize("70%"); });
    $(".font-size-en-B").click(function () { changeFontSize("80%"); });
    $(".font-size-en-L").click(function () { changeFontSize("90%"); });
    $(".font-size-N").click(function () { changeFontSize("75%"); });
    $(".font-size-B").click(function () { changeFontSize("85%"); });
    $(".font-size-L").click(function () { changeFontSize("95%"); });

    /*Login Page Script*/
    $(".login-form div label").click(function () {
        $(this).next().focus();
    });
    $(".login-form div input").focus(function () {
        $(this).prev().hide();
    }).blur(function () {
        if ($(this).val() == "")
            $(this).prev().show();
    });
    $(".login-clear").click(function () {
        $(this).prev().val("").focus();
    });

    /*Init pop dialog*/
    if ($("#dialog")[0] != undefined) {
        $("#dialog").dialog({
            autoOpen: false,
            modal: true,
            height: 550,
            width: 800
        });
    }
});

$.extend($.fn, {
    tooltip: function (options) {
        var op = $.extend({
            top: 5,
            left: 5,
            width: "auto",
            zIndex: 9999,
            speed: 150
        }, options);

        var tipDiv = "<div id='" + op.tipID + "' class='tooltip ui-corner-all'>" +
                         "<div class='tooltipContent'>" +
                             "<span class='tooltipPointer'><span class='tooltipPointerInner'></span></span>" + $(this).attr("title") +
                         "</div>" +
                     "</div>";
        var tipid = '#' + op.tipID;
        $(this).attr("title", "");
        $(this).hover(function () {
            $(tipDiv).appendTo($("body"));
            var offset = $(this).offset();
            $(tipid).css({
                top: offset.top + $(this).outerHeight() + op.top + "px",
                left: offset.left + op.left + "px",
                width: op.width,
                zIndex: op.zIndex
            }).fadeIn(op.speed);
        }, function () {
            $(tipid).remove();
        });
    }
});

/*Get URL vars*/
function getUrlVars() {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

/*Tab control*/
function showTab(tabContainerName) {
    $(".ui-south-content-order-status, .ui-south-content-account-enquiry, .ui-south-content-stock-pos, .ui-south-content-dcy-trade").hide();
    $("." + tabContainerName).show();
    if (tabContainerName != "ui-south-content-order-status")
        $(".south-btn-export, .south-div-filter, .south-div-desc").hide();
    else
        $(".south-btn-export, .south-div-filter, .south-div-desc").show();
    if (tabContainerName == "ui-south-content-account-enquiry")
        $(".south-div-pager").hide();
    else
        $(".south-div-pager").show();
}

//Set center frame src
function changeCenterSrc(src) {
	hideSouthPannel();
	hideTradingForm();
    $("#center-frame").attr("src", src);
}

function changeCenterSrcAndShowPannel(src) {
    $("#center-frame").attr("src", src);
    showTradingPannel();
}

//Show trading form and history
function showTradingPannel() {
    showSouthPannel();
    showTradingForm();
}

//Set pop frame src
function openCommonDialog(title, src) {
    $("#dialog").unbind("dialogopen").unbind("dialogclose");
    $("#dialog").bind("dialogopen", function (event, ui) {
        $(this).find("iframe").attr("src", src);
    }).bind("dialogclose", function (event, ui) {
        $(this).find("iframe").attr("src", "");
    }).dialog("option", "title", title).dialog("open");
}

