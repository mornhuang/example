/**
 * jCarouselLite - jQuery plugin to navigate images/any content in a carousel style widget.
 * @requires jQuery v1.2 or above
 *
 * http://gmarwaha.com/jquery/jcarousellite/
 *
 * Copyright (c) 2007 Ganeshji Marwaha (gmarwaha.com)
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 * Version: 1.0.1
 * Note: Requires jquery 1.2 or above from version 1.0.1
 *
 * Achievo Corporation
 * Updated by Creator Liu at 2010-11-11 for Taifook
 *
 */

(function ($) {                                          // Compliant with jquery.noConflict()
    $.fn.jCarouselLite = function (o) {
        o = $.extend({
            btnPrev: null,
            btnNext: null,
            btnGo: null,
            btnMini: null,
            mouseWheel: false,
            auto: null,

            speed: 200,
            easing: null,

            vertical: false,
            circular: true,
            start: 0,
            scroll: 1,

            beforeStart: null,
            afterEnd: null
        }, o || {});

        return this.each(function () {                           // Returns the element collection. Chainable.

            var running = false, animCss = o.vertical ? "top" : "left", sizeCss = o.vertical ? "height" : "width";
            var div = $(this), ul = $("ul", div), tLi = $("li", ul), tl = tLi.size();
            var v = Math.round(o.vertical ? height(div) / height(tLi) : width(div) / width(tLi));
            if (o.circular) {
                ul.prepend(tLi.slice(tl - v - 1 + 1).clone())
              .append(tLi.slice(0, v).clone());
                o.start += v;
            }

            var li = $("li", ul), itemLength = li.size(), curr = o.start;
            div.css("visibility", "visible");

            li.css({ overflow: "hidden", float: o.vertical ? "none" : "left" });
            ul.css({ margin: "0", padding: "0", position: "relative", "list-style-type": "none", "z-index": "1" });
            div.css({ overflow: "hidden", position: "relative", "z-index": "2", left: "0px" });

            var liSize = o.vertical ? height(li) : width(li);    // Full li size(incl margin)-Used for animation
            var ulSize = liSize * itemLength;                    // size of full ul(total length, not just for the visible items)
            var divSize = o.vertical ? height(div) : width(div); // size of entire div(total length for just the visible items)
            var visible = divSize / liSize;

            ul.css(sizeCss, ulSize + "px").css(animCss, -(curr * liSize));

            if (o.btnPrev)
                $(o.btnPrev).click(function () {
                    //alert(curr);
                    return go(curr - o.scroll);
                });

            if (o.btnNext)
                $(o.btnNext).click(function () {
                    //alert(curr);
                    return go(curr + o.scroll);
                });

            if (o.btnGo)
                $.each(o.btnGo, function (i, val) {
                    $(val).click(function () {
                        //return go(o.circular ? o.visible + i : i);
                        return go(o.circular ? visible + i : i);
                    });
                });

            if (o.btnMini)
                $(o.btnMini).click(function () {
                    go(0);
                });
            if (o.btnNormal)
                $(o.btnNormal).click(function () {
                    go(0);
                });

            if (o.mouseWheel && div.mousewheel)
                div.mousewheel(function (e, d) {
                    return d > 0 ? go(curr - o.scroll) : go(curr + o.scroll);
                });

            if (o.auto)
                setInterval(function () {
                    go(curr + o.scroll);
                }, o.auto + o.speed);

            function vis() {
                return li.slice(curr).slice(0, v);
            };

            function go(to) {
                if (!running) {
                    v = Math.round(o.vertical ? height(div) / height(tLi) : width(div) / width(tLi));
                    liSize = o.vertical ? height(li) : width(li);
                    if (o.beforeStart)
                        o.beforeStart.call(this, vis());

                    if (o.circular) {            // If circular we are in first or last, then goto the other end
                        if (to <= o.start - v - 1) {           // If first, then goto last
                            ul.css(animCss, -((itemLength - (v * 2)) * liSize) + "px");
                            // If "scroll" > 1, then the "to" might not be equal to the condition; it can be lesser depending on the number of elements.
                            curr = to == o.start - v - 1 ? itemLength - (v * 2) - 1 : itemLength - (v * 2) - o.scroll;
                        } else if (to >= itemLength - v + 1) { // If last, then goto first
                            ul.css(animCss, -((v) * liSize) + "px");
                            // If "scroll" > 1, then the "to" might not be equal to the condition; it can be greater depending on the number of elements.
                            curr = to == itemLength - v + 1 ? v + 1 : v + o.scroll;
                        } else curr = to;
                    } else {                    // If non-circular and to points to first or last, we just return.
                        if (to < 0 || to > itemLength - v) return;
                        else curr = to;
                    }                           // If neither overrides it, the curr will still be "to" and we can proceed.

                    running = true;
                    ul.animate(
                    animCss == "left" ? { left: -(curr * liSize)} : { top: -(curr * liSize) }, o.speed, o.easing,
                    function () {
                        if (o.afterEnd)
                            o.afterEnd.call(this, vis());
                        running = false;
                    }
                );
                    // Disable buttons when the carousel reaches the last/first, and enable when not
                    if (!o.circular) {
                        $(o.btnPrev + "," + o.btnNext).removeClass("disabled");
                        $((curr - o.scroll < 0 && o.btnPrev)
                        ||
                       (curr + o.scroll > itemLength - v && o.btnNext)
                        ||
                       []
                     ).addClass("disabled");
                    }

                }
                return false;
            };
        });
    };

    function css(el, prop) {
        return parseInt($.css(el[0], prop)) || 0;
    };
    function width(el) {
        return el[0].offsetWidth + css(el, 'marginLeft') + css(el, 'marginRight');
    };
    function height(el) {
        return el[0].offsetHeight + css(el, 'marginTop') + css(el, 'marginBottom');
    };

})(jQuery);