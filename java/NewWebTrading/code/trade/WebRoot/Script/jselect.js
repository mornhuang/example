/*
 * jNice
 * version: 1.0 (11.26.08)
 * by Sean Mooney (sean@whitespace-creative.com) 
 * Examples at: http://www.whitespace-creative.com/jquery/jnice/
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 *
 * And apply the jNice class to the form you want to style
 *
 ********************************************
 * Taifook JSelect.
 * @requires jQuery v1.2 or above
 *
 * Copyright (c) 2010 Achievo Corporation (http://www.achievo.com)
 *
 * Version: 1.0.0
 *
 * Achievo Corporation
 * Created by Creator Liu at 2010-12-14
 *
 *
 ******************************************** */
(function ($) {
    $.fn.jNice = function (options) {
        var self = this;
        var safari = $.browser.safari; /* We need to check for safari to fix the input:text problem */
        /* Apply document listener */
        $(document).mousedown(checkExternalClick);
        /* each form */
        return this.each(function (index) {
            { SelectAdd(this, index); }
        });
    }; /* End the Plugin */

    /* Hide all open selects */
    var SelectHide = function () {
        $('.jNiceSelectWrapper ul:visible').hide();
    };
    /* Check for an external click */
    var checkExternalClick = function (event) {
        if ($(event.target).parents('.jNiceSelectWrapper').length === 0) { SelectHide(); }
    };
    var SelectAdd = function (element, index) {
        var $select = $(element);
        index = index || $select.css('zIndex') * 1;
        index = (index) ? index : 0;
        /* First thing we do is Wrap it */
        $select.wrap($('<div class="jNiceWrapper"></div>').css({ zIndex: 100 - index }));
        var width = $select.width();
        var browserName = navigator.userAgent.toLowerCase();
        mybrowser = {
            version: (browserName.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [0, '0'])[1],
            safari: /webkit/i.test(browserName) && !(/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName)),
            opera: /opera/i.test(browserName),
            firefox: /firefox/i.test(browserName),
            msie: /msie/i.test(browserName) && !/opera/.test(browserName),
            mozilla: /mozilla/i.test(browserName) && !/(compatible|webkit)/.test(browserName) && !this.chrome,
            chrome: /chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName)
        }
        if (mybrowser.safari)
            width = width + 25;
        $select.addClass('jNiceHidden').after('<div class="jNiceSelectWrapper"><div><span class="jNiceSelectText"></span></div><ul class="jNiceSelectUl"></ul></div>');
        var $wrapper = $(element).siblings('.jNiceSelectWrapper').width(width);
        $wrapper.parent().width(width);
        $('.jNiceSelectText, .jNiceSelectUl', $wrapper).width(width);
        /* Now we add the options */
        SelectUpdate(element);
        /* Apply the click handler to the Open */
        $('div', $wrapper).click(function () {
            var $ul = $(this).siblings('ul');
            if ($ul.css('display') == 'none') { SelectHide(); } /* Check if box is already open to still allow toggle, but close all other selects */
            $ul.toggle();
            return false;
        });
        /* Add the key listener */
        $select.keydown(function (e) {
            var selectedIndex = this.selectedIndex;
            switch (e.keyCode) {
                case 40: /* Down */
                    if (selectedIndex < this.options.length - 1) { selectedIndex += 1; }
                    break;
                case 38: /* Up */
                    if (selectedIndex > 0) { selectedIndex -= 1; }
                    break;
                default:
                    return;
                    break;
            }
            $('ul li', $wrapper).removeClass('selected').eq(selectedIndex).addClass('selected');
            $('span:eq(0)', $wrapper).html($('option:eq(' + selectedIndex + ')', $select).attr('selected', 'selected').text());
            return false;
        }).focus(function () { $wrapper.addClass('jNiceFocus'); }).blur(function () { $wrapper.removeClass('jNiceFocus'); });
    };
    var SelectUpdate = function (element) {
        var $select = $(element);
        var $wrapper = $select.siblings('.jNiceSelectWrapper');
        var $ul = $wrapper.find('ul').find('li').remove().end().hide();
        $('option', $select).each(function (i) {
            $ul.append('<li index="' + i + '">' + this.text + '</li>');
        });
        $ul.find('li').hover(function () { $(this).addClass("hover") }, function () { $(this).removeClass("hover") });
        /* Add click handler to the a */
        $ul.find('li').click(function () {
            $('li.selected', $wrapper).removeClass('selected');
            $(this).addClass('selected');
            /* Fire the onchange event */
            if ($select[0].selectedIndex != $(this).attr('index') && $select[0].onchange) { $select[0].selectedIndex = $(this).attr('index'); $select[0].onchange(); }
            $select[0].selectedIndex = $(this).attr('index');
            $('span:eq(0)', $wrapper).html($(this).html());
            $ul.hide();
            return false;
        });
        /* Set the defalut */
        $('li:eq(' + $select[0].selectedIndex + ')', $ul).click();
        var maxheight = $(document).height() - 200;
        $ul.css({ height: "auto" });
        maxheight = $ul.height() > maxheight ? maxheight : "auto";
        $ul.css({ height: maxheight });
    };
    var SelectRemove = function (element) {
        var zIndex = $(element).siblings('.jNiceSelectWrapper').css('zIndex');
        $(element).css({ zIndex: zIndex }).removeClass('jNiceHidden');
        $(element).siblings('.jNiceSelectWrapper').remove();
    };
    /* Utilities */
    $.jNice = {
        SelectAdd: function (element, index) { SelectAdd(element, index); },
        SelectRemove: function (element) { SelectRemove(element); },
        SelectUpdate: function (element) { SelectUpdate(element); }
    }; /* End Utilities */
    /* Automatically apply to any forms with class jNice */
    $(function () { $('.jquery-select').jNice(); });
    //$(function () { $('form.jNice').jNice(); });
})(jQuery);