/**
* Taifook Warrants & CBBCs.
* @requires jQuery v1.2 or above
*
* Copyright (c) 2010 Achievo Corporation (http://www.achievo.com)
*
* Version: 1.0.0
*
* Achievo Corporation
* Created by Creator Liu at 2010-12-3
*
*/
$(function () {
    var src = getUrlVars()["url"];
    var activeNo = parseInt(getUrlVars()["no"]);
    var height = getUrlVars()["height"];
    if (activeNo == 7) {
        changeTopTenSrc(src, height);
    }
    else {
        changeInnerSrc(src, height);
    }
    activeNo = activeNo - 1;
    $(".warrants-cbbcs-nav a:eq(" + activeNo + ")").addClass("active");
    $(".warrants-cbbcs-nav a").click(function () {
        $(".warrants-cbbcs-nav a").removeClass("active");
        $(this).addClass("active");
    });
});

function changeInnerSrc(src, height) {
    $("#center-frame-1").height(height).attr("src", src);
    $("#center-frame-2, #center-frame-3").height(0);
    $(".warrants-cbbcs-top-ten-title").hide();
}

function changeTopTenSrc(src, height) {
    $("#center-frame-1").height(height).attr("src", src.split(';')[0]);
    $("#center-frame-2").height(height).attr("src", src.split(';')[1]);
    $("#center-frame-3").height(height).attr("src", src.split(';')[2]);
    $(".warrants-cbbcs-top-ten-title").show();
}