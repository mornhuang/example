<!--
//----------------------------------------------------------------------------
//  这是WalkingPoison根据梅花雨的日历控件修改发展出来的一个日历 Javascript 页面脚本控件，适用于微软的 IE （5.0以上）浏览器
//  主调用函数是 wpCalendar(this,[object])wpCalendar(this)，[object]是控件输出的控件名，举两个例子：
//  一、<input name=txt><input type=button value="Show Calendar" onclick="showCalendar(this,document.all.txt)">
//  二、<input onfocus="showCalendar(this)">
//  本日历的年份限制是（1000 - 9999）
//  按ESC键关闭该控件
//  在年和月的显示地方点击时会分别出年与月的下拉框
//  控件外任意点击一点即可关闭该控件
/* 以下为WalkingPoison的修改说明
WalkingPoison联系方式：walkingpoison@hotmail.com

Ver 2.59
修改日期：2008-6-17
1.*修改对Firefox的兼容性，使其可兼容多浏览器（注意：在FF下运行需要将包含js文件的代码放在<body>中）。此版本尚未完善，完善后升级为2.60版
2.存在问题：样式在两个浏览器还不一致；

Ver 2.55
修改日期：2007-6-26
1.年份的下拉框增加了可选项，用于决定年份的滚动是通过点击还是mouseover来触发

Ver 2.54
修改日期：2006-4-30
1.*将日历单元格的宽度和高度改为变量定义，可以自由定义日历的大小（注意：较大的单元格会使日历响应速度变慢）
2.删除了无用的SELECT样式定义

Ver 2.53
修改日期：2005-11-25
1.增加了拖动出iframe的处理，使得拖动的时候更加不容易停下来（但是在出iframe的同时也出了IE窗口的情况会使得日历移动到左上角）
2.增加了日历中翻年月按钮的双击处理，使得点击的响应更快速
3.在日历对象内使用了self变量来访问自身，而不用依赖于外部创建的wpCalendar实例

Ver 2.52
修改日期：2004-10-9
1.优化了writeCalendar函数及其它的部分代码（增加了抽象的日期对象）
2.显示的提示改为与输出格式完全一致

Ver 2.51
修改日期：2004-9-23, 2004-9-29
1.增加了setYearMonth、setTheme、setYear和setMonth方法，以便外部修改年月的时候调用
2.*重新写了年月的下拉列表，不再使用select控件来选择年月，使得选择年月的操作变得简单
3.增加了Greenbrier主题，删除了无用的Custom主题

Ver 2.5
修改日期：2004-7-23
1.调整了属性和方法的名称
2.增加了themename属性，使得使用代码来修改主题更为方便

Ver 2.5 beta 4
修改日期：2004-4-16, 2004-5-18
1.*增加了调用的方法，最后一个参数允许是一段代码，用于在点击日历以后可以执行需要的代码。
2.修改了一点注释的笔误以及改进了一点代码效率

Ver 2.5 beta 3
1.增加主题可以设置的参数，并在日历版本信息中同时显示主题的版本信息
2.将日历的一些方法集成在日历对象内部，以免与外部函数定义形成冲突
3.增加了DiabloII主题，希望各位喜欢^^
4.优化部分代码

Ver 2.5 beta 2
1.增加鼠标停止在日期上的渐变效果。需要ie5.5以上支持。
2.增加参数设置对以上效果进行自动判断和测试。
3.解决方法一选择日期后产生的焦点小问题。

Ver 2.5 beta
修改日期：2003-10-27
1.*修改日历的初始化方法，可以对多种参数进行设置。
2.*引入主题的概念，便于用户选择或者自由定制主题。
3.*控件已经内置了几个主题，用户可根据需要进行选用。

Ver 2.1+
1.继续修正根据目标在窗口的位置自动调整位置，调整了在窗口右方时的显示问题。

Ver 2.1
1.修正选择月份后下一月的提示内容错误
2.日历根据输出目标在窗口的位置自动调整显示位置。()
3.*自定义输入输出格式，并修改使得输入和输出的格式统一。
4.增加自定义的样式，可以使用"M"和"d"，并且日可以空缺。

Ver	2.0
修改日期：2002-12-13
1.*全新修改使用iframe作为日历的载体，不再被select和flash等控件挡住。
2.修正了移植到iframe后移动日历控件的问题。

Ver	1.5
修改日期：2002-12-4
1.选中的日期显示为凹下去的样式
2.修改了关闭层的方法，使得失去焦点的时候能够关闭日历。
3.修改按键处理，使得Tab切换焦点的时候可以关闭控件
4.*可以自定义日历是否可以拖动

Ver 1.4
修改日期：2002-12-3
1.修正选中年/月份下拉框后按Esc键导致年/月份不显示的问题
2.修正使用下拉框选择月份造成的日期错误（字符串转化为数字的问题）
3.*外观样式的改进，使得控件从丑小鸭变成了美丽的天鹅，从灰姑娘变成了高贵的公主，从……（读者可以自己进行恰当的比喻）
4.再次增大年/月份的点击空间，并对下拉框的位置稍作调整

Ver 1.3
修改日期：2002-11-29
1.*空白部分用灰色显示上个月的最后几天和下个月的前几天
2.*每个日期上面加上鼠标提示
3.修改使得当前日期和当前选择的日期的背景色在灰色日期部分也能正常显示

Ver 1.2
修改日期：2002-11-28
1.*修改年和月的点击都把中文包含在内，增大点击的空间
2.当前选择的日期在列表中显示不同的背景色
3.修正了点击单元格之间的分隔线导致控件关闭的问题

Ver 1.1
修改日期：2002-11-15
1.修正了方法二按Esc键关闭以后再次点击不会显示日历的问题
2.点击today直接选中当前的日期并关闭控件
3.*如果调用控件的输入框含有合法日期，则自动显示输入框的日期部分。
4.修改程序统一使用关闭的函数closeCalendar()来关闭日历控件，这样可以通过自定义关闭函数来完成用户自定义的功能。

注：*号表示比较关键的改动
*/

document.writeln('<iframe id=wpCalendarLayer name=wpCalendarLayer frameborder=0 onmouseout="wpCalendar.correctPosition()" style="position: absolute; width: 153; height: 208; z-index: 9998; display: none"></iframe>');

var wpConsts = new function(){
	this._isIE = (navigator.userAgent.indexOf("MSIE")>0);
	this.MouseButton = this._isIE?{
		LEFTBUTTON	: 1,
		RIGHTBUTTON	: 0,
		MIDDLEBUTTON: 4
	}:
	{
		LEFTBUTTON	: 0,
		RIGHTBUTTON	: 2,
		MIDDLEBUTTON: 1
	};
};

var wpEvents = new function(){
	var self = this;
    this.arrEvents = [];
	this.addListener = function(obj, eventName, oCallback){
	    var ret;
		if(wpConsts._isIE){
			ret = obj.attachEvent("on" + eventName, oCallback);
		}
		else{
			ret = obj.addEventListener(eventName, oCallback, false);
		}
		if(ret)this.arrEvents.push({"obj":obj, "eventName": eventName, "oCallback":oCallback});
		return ret;
	};
	
	this.clearListener = function(obj, eventName){
	    for(var i=0;i<this.arrEvents.length;i++){
	        if(this.arrEvents[i].obj == obj && this.arrEvents[i].eventName == eventName){
	            this.removeListener(obj, eventName, this.arrEvents[i].oCallback);
	        }
	    }
	};
	
	this.removeListener = function(obj, eventName, oCallback){
	    if(wpConsts._isIE){
			obj.detachEvent("on" + eventName, oCallback);
		}
		else{
			obj.removeEventListener(eventName, oCallback, true);
		}
	};

	this.initWinEvents = function(oWin){
		if(!oWin)return;
		__firefox(oWin);
	};

	/*使得firefox兼容IE的event*/
	function __firefox(oWin){
		if(!oWin)oWin = window;
		HTMLElement.prototype.__defineGetter__("runtimeStyle", self.__element_style);
		oWin.constructor.prototype.__defineGetter__("event", function(){return self.__window_event(oWin);});
		Event.prototype.__defineGetter__("srcElement", self.__event_srcElement);
	}
	this.__element_style = function(){
		return this.style;
	}
	this.__window_event = function(oWin){
		return __window_event_constructor();
	}
	this.__event_srcElement = function(){
		return this.target;
	} 
	function __window_event_constructor(oWin){
		if(!oWin)oWin = window;
		if(document.all){
			return oWin.event;
		}
		var _caller = __window_event_constructor.caller;
		while(_caller!=null){
			var _argument = _caller.arguments[0];
			if(_argument){
				var _temp = _argument.constructor;
				if(_temp.toString().indexOf("Event")!=-1){
					return _argument;
				}
			}
			_caller = _caller.caller;
		}
		return null;
	}
	if(window.addEventListener){
		__firefox();
	}
	/*end firefox*/ 
};


function wpCalendar(){
	//==================================================== 参数设定部分 =======================================================
	this.bmoveable	= true;			//设置日历是否可以拖动
	this.datestyle	= "yyyy-M-d";		//added in Ver 2.1
	this.cellwidth	= 21;			//设置单个单元格的宽度
	this.cellheight	= 20;			//设置单元格的高度
	this.versioninfo= "wpCanlendar Version:2.59&#13;作者:WalkingPoison";	//版本信息
	this.tgtObject	= null;
	this.srcButton	= null;		//点击的按钮
	this.outerDate	= "";		//存放对象的日期
	this.oCalendar	= window.frames.wpCalendarLayer.document;			//存放日历对象
	this.Style		= document.getElementById("wpCalendarLayer").style;	//存放日历层的style
	this.MonthinMM	= this.datestyle.indexOf("MM")>=0?true:false;		//added in Ver 2.1
	this.Dateindd	= this.datestyle.indexOf("dd")>=0?true:false;
	this.themename	= "Icicle";						//主题设置，决定日历的外观
	this.theme		= null;						//根据主题设置得到对应的主题，在redraw函数中根据themename自动设置
	this.ScrollYearOnClick	= false;			//设置年的下拉选择框内的年份是通过点击还是mouseover来滚动

	try{this.IE6	= (parseFloat(window.navigator.appVersion.match(/MSIE (\d+\.\d+)/)[1])>=5.5)}
	catch(e){this.IE6 = false}
	this.testspeed	= false;					//测试机器速度，以便自动决定是否使用Filter。如果设置为true，则下面的playfilter参数设置无效。
	this.testtimeout= 120;						//测试标准，单位是毫秒，小于这个数值的就认为机器速度足够快
	this.playfilter	= this.IE6;					//设置是否使用Filter

	this.year		= new Date().getFullYear();	//定义年的变量的初始值
	this.month		= new Date().getMonth()+1;	//定义月的变量的初始值
	this.date		= new Date().getDate();		//定义日的变量的初始值
	this.Days		= new Array(39);			//定义写日期的数组
	this.DateCell	= new Array(39);			//存放日期单元格
	
	this.followcodes= "";						//存放执行完点击日历以后需要执行的操作代码

	this.tmpTdClass = "";
	this.redraw		= function (){				//定义redraw方法，用于重绘整个日历
		//==================================================== WEB 页面显示部分 =====================================================
		if(this.cellwidth<21)this.cellwidth=21;
		if(this.cellheight<20)this.cellheight=20;
		this.theme = typeof theme[this.themename] == "object"? theme[this.themename] : theme["Classic"];
		var strFrame;		//存放日历层的HTML代码
		strFrame='<style>';
		with(this.theme){
			strFrame+='BODY{font-family:'+fontFamily+';}';
			strFrame+='INPUT{color:'+buttonFontColor+';border-right:'+buttonBorderColor+' 1px solid;border-top:'+buttonBorderColor+' 1px solid;border-left:'+buttonBorderColor+' 1px solid;';
			strFrame+='border-bottom:'+buttonBorderColor+' 1px solid;background-color:'+buttonColor+';font-family:'+fontFamily+';}';
			strFrame+='table.main{border:1px solid '+borderColor+';width:' + (this.getCalendarWidth()-2) + 'px;height:' + this.getMainHeight() + 'px;background-color:'+bgColorMain+';}';
			strFrame+='table.dragbar{border-top:1px solid '+dragBarColor+';border-left:1px solid '+dragBarColor+';border-right:1px solid '+dragBarColorDark+';border-bottom:1px solid '+dragBarColorDark+';}';
			strFrame+='table.head{background-color:'+headBgColor+';}';
			strFrame+='td.head{background-color:'+headBgColor+';}';
			strFrame+='span.year{z-index: 9999;position:absolute;top:3px;left:20px;border: 1px solid '+borderColor+'}';
			strFrame+='table.year{width:' + (this.getYearWidth()-4) + 'px;height:140px;background-color:'+bgColorMain+';cursor:default;}';
			strFrame+='span.month{z-index: 9999;position: absolute;top: 3px;left: ' + (this.getYearWidth() + 19) + 'px;border:'+borderColor+' 1px solid}';
			strFrame+='table.month{width:' + (this.getMonthWidth()-4) + 'px;height:168px;background-color:'+bgColorMain+';cursor:default;}';
			strFrame+='TD{font-size: 12px;}';
			strFrame+='a{color:' + normalDayFontColor + ';}';
			strFrame+='table.datecell{border-top:1px solid '+dragBarColor+';border-left:1px solid '+dragBarColor+';border-right:1px solid '+dragBarColorDark+';border-bottom:1px solid '+dragBarColorDark+';}';
			strFrame+='TD.calendarhead{color: '+headFontColor+'; background-color: '+headBgColor+';border:1px solid '+headBgColor+';}';
			strFrame+='TD.mouseoverYM{text-align:center;color: '+mouseOverFontColor+'; background-color: '+mouseOverColor+';}';
			strFrame+='td.normalYM{text-align:center;color:'+normalDayFontColor+';background-color:'+normalDayColor+';}';
			strFrame+='td.selectedYM{text-align:center;color:'+selectedDayFontColor+';background-color:'+selectedDayColor+';}';
			strFrame+='TD.dragbar{font-size:12px;color:'+dragBarFontColor+';width:21px;border-bottom:1px solid '+dragBarColor+';border-right:1px solid '+dragBarColor+';border-left:1px solid '+dragBarColorDark+';border-top:1px solid '+dragBarColorDark+';}';
			strFrame+='TD.mouseover{color: '+mouseOverFontColor+'; background-color: '+mouseOverColor+';border:1px solid '+borderColor+';}';
			strFrame+='TD.normalday,TD.grayday,TD.today,TD.graytoday{border-left:1px solid '+borderColorDark+';border-top:1px solid '+borderColorDark+';border-right:1px solid '+borderColor+';border-bottom:1px solid '+borderColor+';}';
			strFrame+='TD.selectedday,TD.selectedgrayday{border-left:1px solid '+borderColor+';border-top:1px solid '+borderColor+';border-right:1px solid '+borderColorDark+';border-bottom:1px solid '+borderColorDark+';}';
			strFrame+='TD.normalday{color:'+normalDayFontColor+';background-color:'+normalDayColor+';}';
			strFrame+='TD.grayday{color:'+grayDayFontColor+';background-color:'+grayDayColor+';}';
			strFrame+='TD.today{color:'+todayFontColor+';background-color:'+todayColor+';}';
			strFrame+='TD.selectedday{color:'+selectedDayFontColor+';background-color:'+selectedDayColor+';}';
			strFrame+='TD.selectedgrayday{color:'+grayDayFontColor+';background-color:'+selectedDayColor+';}';
			//	'border-left-color:'+borderColor+';border-top-color:'+borderColor+';border-right-color:'+borderColorDark+';border-bottom-color:'+borderColorDark+';}';
			strFrame+='TD.graytoday{color:'+grayDayFontColor+';background-color:'+todayColor+';}';
			//	'border-left-color:'+borderColor+';border-top-color:'+borderColor+';border-right-color:'+borderColorDark+';border-bottom-color:'+borderColorDark+';}';
		}
		strFrame+='</style>';
		strFrame+='<scr' + 'ipt>';
		strFrame+='if(window.addEventListener){parent.wpEvents.initWinEvents(window);}	/*解决兼容ff事件的问题*/';
		strFrame+='var datelayerx,datelayery;	/*存放日历控件的鼠标位置*/';
		strFrame+='var bDrag=false;	/*标记是否开始拖动*/';
		strFrame+='var DateLayer=parent.wpCalendar.Style;';
		strFrame+='document.onmousemove=function()	/*在鼠标移动事件中，如果开始拖动日历，则移动日历*/';
		strFrame+='{if(bDrag){';
		strFrame+='		DateLayer.left = (parseInt(DateLayer.left)+window.event.clientX-datelayerx)+"px";/*由于每次移动以后鼠标位置都恢复为初始的位置，因此写法与div中不同*/';
		strFrame+='		DateLayer.top = (parseInt(DateLayer.top)+window.event.clientY-datelayery)+"px";}};';
		strFrame+='function DragStart(){		/*开始日历拖动*/';
		strFrame+='	if(window.event.button==parent.wpConsts.MouseButton.LEFTBUTTON){';
		strFrame+='		datelayerx=window.event.clientX;';
		strFrame+='		datelayery=window.event.clientY;';
		strFrame+='		bDrag=true;}}';
		strFrame+='function DragEnd(){		/*结束日历拖动*/';
		strFrame+='	bDrag=false;}';
		strFrame+='document.onmousedown=function(){	/*点击的时候关闭年份和月份的选择列表*/';
		strFrame+='	var obj=document.getElementById("tmpSelectYearLayer");if(!bFromYear&&event.srcElement!=document.getElementById("CalendarYearHead")&&obj.style.display!="none"){obj.style.display="none";bYearListOpening=true}';
		strFrame+='	obj=document.getElementById("tmpSelectMonthLayer");if(event.srcElement!=document.getElementById("CalendarMonthHead")&&obj.style.display!="none")obj.style.display="none";};';
		strFrame+='var strTempClass="";';
		strFrame+='document.oncontextmenu=function(){if(!event.ctrlKey)return false;};	/*禁止出现右键菜单*/';
		strFrame+='function mOver(obj){strTempClass=obj.className;obj.className="mouseoverYM";bYearListOpening=false;/*为了显示年份列表的时候向上滚动功能的正常使用*/}/*年/月选择列表的mouseover处理*/';
		strFrame+='function mOut(obj){obj.className=strTempClass;}/*年/月选择列表的mouseout处理*/';
		strFrame+='function setMonth(iMonth){parent.wpCalendar.setMonth(iMonth)}/*下拉选择月份的处理*/';
		strFrame+='function setYear(iYear){parent.wpCalendar.setYear(iYear)}/*下拉选择年份的处理*/';
		strFrame+='var bScrolling=false;var bYearListOpening=true;/*这个变量用于控制在刚刚显示年份列表的时候，鼠标处于向上滚动条位置的情况下不要滚动*/';
		strFrame+='var iScroll=null;';
		strFrame+='var bFromYear=false;	/*标志为从年份下拉列表处进行了点击，以便不要关闭年份列表*/';
		strFrame+='function scrollYear(bUp){';
		strFrame+='	var oTable=document.getElementById("tmpSelectYearLayer").childNodes[0];';
		strFrame+='	if(bUp){';
		strFrame+='		var iMin=parseInt(oTable.rows[1].cells[0].innerHTML.replace(/\\D/g,\'\'));';
		strFrame+='		if(iMin>1000){oTable.deleteRow(oTable.rows.length-2);var oTd=oTable.insertRow(1).insertCell(0);';
		strFrame+='			oTd.innerHTML=(iMin-1).toString()+" 年";oTd.onmouseover=Function("mOver(this)");oTd.onmouseout=Function("mOut(this)");';
		strFrame+='			oTd.onmousedown=Function("setYear("+(iMin-1).toString()+")");oTd.className=(iMin-1==parent.wpCalendar.year?"selectedYM":"normalYM");';
		strFrame+='			if(bScrolling)window.setTimeout("scrollYear(true)", 100);}}';
		strFrame+='	else{';
		strFrame+='		var iMax=parseInt(oTable.rows[oTable.rows.length-2].cells[0].innerHTML.replace(/\\D/g,\'\'));';
		strFrame+='		if(iMax<=9999){var oTd=oTable.insertRow(oTable.rows.length-1).insertCell(0);oTable.deleteRow(1);';
		strFrame+='			oTd.innerHTML=(iMax+1).toString()+" 年";oTd.onmouseover=Function("mOver(this)");oTd.onmouseout=Function("mOut(this)");';
		strFrame+='			oTd.onmousedown=Function("setYear("+(iMax+1).toString()+")");oTd.className=(iMax+1==parent.wpCalendar.year?"selectedYM":"normalYM");';
		strFrame+='			if(bScrolling)window.setTimeout("scrollYear(false)", 100);}}';
		strFrame+='}';
		strFrame+='function startScroll(oTd,bUp){bFromYear=true;if(!bYearListOpening){bScrolling=true;scrollYear(bUp);}}';
		strFrame+='function stopScroll(){bYearListOpening=false;bFromYear=false;bScrolling=false;if(iScroll)clearTimeout(iScroll);}';
		strFrame+='</scr' + 'ipt>';
		with(this){
			strFrame+='<div style="z-index:9999;position: absolute; left:0; top:0;" onselectstart="return false">';
			strFrame+='<span id=tmpSelectYearLayer class="year" style="display: none;"></span>';
			strFrame+='<span id=tmpSelectMonthLayer class="month" style="display: none;"></span>';
			strFrame+='<table border=1 cellspacing=0 cellpadding=0 bordercolor="' + theme.borderColor + '" class="main">';
			strFrame+='  <tr><td width=' + (getCalendarWidth()-2) + ' height=' + (cellheight) + ' class="head"><table border=0 cellspacing=1 cellpadding=0 width=' + (getCalendarWidth()-4) + ' height=' + cellheight + '>';
			strFrame+='      <tr align=center><td width=16><input title="向前翻 1 月" type=button ';
			strFrame+='             value="<" onclick="parent.wpCalendar.goPrevMonth()" ondblclick="parent.wpCalendar.goPrevMonth()" onfocus="this.blur()" style="font-size: 12px; width: 16px; height: ' + (cellheight-2) + 'px">';
			strFrame+='        </td><td width=' + getYearWidth() + ' align=center class=calendarhead style="font-size:12px;cursor:default" ';
			strFrame+='onmouseover="className=\'mouseover\'" onmouseout="className=\'calendarhead\'" ';
			strFrame+='onclick="parent.wpCalendar.selectYear(this.innerHTML.substring(0,4))" title="点击这里选择年份" id=CalendarYearHead></td>';
			strFrame+='<td width=' + getMonthWidth() + ' align=center class=calendarhead style="font-size:12px;cursor:default" onmouseover="className=\'mouseover\'" ';
			strFrame+=' onmouseout="className=\'calendarhead\'" onclick="parent.wpCalendar.selectMonth(this.innerHTML.replace(/\\D/g,\'\'))"';
			strFrame+='        title="点击这里选择月份" id=CalendarMonthHead></td>';
			strFrame+='		<td width=16><input type=button value=">" onclick="parent.wpCalendar.goNextMonth()" ondblclick="parent.wpCalendar.goNextMonth()" ';
			strFrame+='             onfocus="this.blur()" title="向后翻 1 月" style="font-size: 12px; width:16px; height: ' + (cellheight-2) + 'px"></td></tr>';
			strFrame+='    </table></td></tr>';
			strFrame+='  <tr><td width=' + (getCalendarWidth()-4) + ' height=' + (cellheight) + ' align=center>';
			strFrame+='<table align=center border=1 cellspacing=0 cellpadding=0 bgcolor='+theme.bgColorMain+' ' + (bmoveable? 'onmousedown="DragStart()" onmouseup="DragEnd()"':'');
			strFrame+=' class="dragbar" width=' + (getCalendarWidth()-6) + ' height=' + (cellheight) + ' style="cursor:' + (bmoveable ? 'move':'default') + '">';
			strFrame+='<tr align=center valign=bottom><td class=dragbar>日</td>';
			strFrame+='<td class=dragbar>一</td><td class=dragbar>二</td>';
			strFrame+='<td class=dragbar>三</td><td class=dragbar>四</td>';
			strFrame+='<td class=dragbar>五</td><td class=dragbar>六</td></tr>';
			strFrame+='</table></td></tr>';
			strFrame+='  <tr><td width=' + (getCalendarWidth()-2) + ' height=' + getBodyHeight() + ' align=center>';
			strFrame+='    <table align=center border=1 cellspacing=2 cellpadding=0 class="datecell" bordercolor='+theme.borderColor+' bgcolor='+theme.bgColorLight+' width=' + (getCalendarWidth()-4) + ' height=' + getBodyHeight() + '>';
			var n=0; for (j=0;j<5;j++){ strFrame+= ' <tr align=center>'; for (i=0;i<7;i++){
			strFrame+='<td width=' + (cellwidth) + ' height=' + (cellheight) + ' id=wpDateCell'+n+'></td>';n++;}
			strFrame+='</tr>';}
			strFrame+='      <tr align=center>';
			for (i=35;i<39;i++)strFrame+='<td width=' + (cellwidth) + ' height=' + (cellheight) + ' id=wpDateCell'+i+'></td>';
			strFrame+='        <td colspan=3 align=right class=calendarhead onclick=parent.wpCalendar.close() style="font-size:12px;cursor: pointer;padding-right:5px;" title="' + versioninfo + '&#13;' + theme.versioninfo + '">';
			strFrame+='        <a href="javascript:void(0);">关闭</a></td></tr>';
			strFrame+='    </table></td></tr><tr><td>';
			strFrame+='        <table border=0 cellspacing=1 cellpadding=0 width=100% class="head">';
			strFrame+='          <tr><td align=left><table border=0 cellspacing=0 cellpadding=0><tr><td><input type=button value="<<" title="向前翻 1 年" onclick="parent.wpCalendar.goPrevYear()" ';
			strFrame+='             ondblclick="parent.wpCalendar.goPrevYear()" onfocus="this.blur()" style="width:20px;font-size: 12px; height: ' + (cellheight) + 'px"><td width="1"></td><td><input title="向前翻 1 月" type=button ';
			strFrame+='             value="<" onclick="parent.wpCalendar.goPrevMonth()" ondblclick="parent.wpCalendar.goPrevMonth()" onfocus="this.blur()" style="width:16px;font-size: 12px; height: ' + (cellheight) + 'px"></td></tr></table></td><td ';
			strFrame+='             align=center><input type=button value=Today onclick="parent.wpCalendar.selectToday()" ';
			strFrame+='             onfocus="this.blur()" title="当前日期" style="font-size: 12px; height: ' + (cellheight) + 'px; cursor:pointer"></td><td ';
			strFrame+='             align=right><table border=0 cellspacing=0 cellpadding=0><tr><td><input type=button value=">" onclick="parent.wpCalendar.goNextMonth()" ';
			strFrame+='             ondblclick="parent.wpCalendar.goNextMonth()" onfocus="this.blur()" title="向后翻 1 月" style="width:16px;font-size: 12px; height: ' + (cellheight) + 'px"><td width="1"></td><td><input ';
			strFrame+='             type=button value=">>" title="向后翻 1 年" onclick="parent.wpCalendar.goNextYear()" ondblclick="parent.wpCalendar.goNextYear()"';
			strFrame+='             onfocus="this.blur()" style="width:20px;font-size: 12px; height: ' + (cellheight) + 'px"></td></tr></table></td>';
			strFrame+='</tr></table></td></tr></table></div>';
		}

		this.oCalendar.writeln(strFrame);
		this.oCalendar.close();		//解决ie进度条不结束的问题
		this.Style.width	= this.getCalendarWidth();		//设置外部iframe的宽度
		this.Style.height	= this.getCalendarHeight();		//设置外部iframe的高度
		
		this.MonthinMM= this.datestyle.indexOf("MM")>=0?true:false;
		this.Dateindd	= this.datestyle.indexOf("dd")>=0?true:false;
		//测试速度
		if(this.testspeed){
			var t=new Date();var s="";
			for (var i=1;i<10000;i++){s=(s.length>=500?" ":s+" ");}
			if(new Date()-t<=this.testtimeout)this.playfilter = this.IE6
			else this.playfilter = false;
			delete t;delete s;
		}

		for (i=0;i<39;i++)
		{
			this.DateCell[i] = this.oCalendar.getElementById('wpDateCell'+i);
			var da = this.DateCell[i];
			da.style.cursor	= "pointer";
			da.style.filter	= "progid:DXImageTransform.Microsoft.Fade(duration=0.5,overlap=0.5)";
			da.style.fontWeight = "bold";
			da.onmouseover	= wpMouseOver;
			da.onmouseout	= wpMouseOut;
			da.onclick		= Function("wpCalendar.dateCellClick("+i+")");		//给td赋予onclick事件的处理
		}

		//==================================================== WEB 页面显示部分 ======================================================
	}
	this.Redraw = this.redraw;

	//设置了自定义的日历单元格宽度，以下函数用于根据单元格宽度获得日历宽度
	this.getCalendarWidth = function(){
		return parseInt(this.cellwidth*7+6);
	};
	this.getYearWidth = function(){				//获取显示年份部分的宽度
		return parseInt(((this.cellwidth*7+2)-32)*0.53);
	};
	this.getMonthWidth = function(){			//获取显示月份部分的宽度
		return parseInt(((this.cellwidth*7+2)-32)*0.47);
	};
	this.getCalendarHeight = function(){
		return (this.cellheight+2)*9 + 10;
	};
	this.getMainHeight = function(){
		return this.cellheight*8;
	};
	this.getBodyHeight = function(){
		return this.cellheight*6;
	};
	this.selectMonth = function (strMonth){		//显示月份选择列表
		if (strMonth.match(/\D/)!=null)return;
		var m = (strMonth) ? strMonth : new Date().getMonth() + 1;
		var s = '';
		s += '<table border=0 cellspacing=0 cellpadding=0 class="month">';
		for(var i=1;i<=12;i++){
			s += '<tr><td class=' + (i==parseInt(strMonth)?'selectedYM':'normalYM') + ' onmouseover="mOver(this)" onmouseout="mOut(this)" onmousedown="setMonth('+i+')">' + i + ' 月' + '</td></tr>';
		}
		s += '</table>';
		this.oCalendar.getElementById("tmpSelectMonthLayer").innerHTML = s;
		this.oCalendar.getElementById("tmpSelectMonthLayer").style.display="";
	};
	this.selectYear = function (strYear){		//显示年份选择列表，并且年份列表可以滚动
		if (strYear.match(/\D/)!=null)return;
		var m = (strYear) ? strYear : new Date().getFullYear();
		if (m < 1000 || m > 9999)return;
		var n = m - 5;
		if (n < 1000) n = 1000;
		if (n + 100 > 9999) n = 9900;
		var s = '';
		with(this.theme){
			s += '<table border=0 cellspacing=0 cellpadding=0 class="year">';
			if(this.ScrollYearOnClick){
				s += '<tr><td height=5 bgcolor='+bgColorLight+' onmouseover="this.bgColor=\''+bgColorMain+'\'" onmouseout="stopScroll();this.bgColor=\''+bgColorLight+'\'"' +
					'onclick="return false" onmousedown="startScroll(this,true)" onmouseup="stopScroll()" title="向上滚动年份"></td></tr>';
			}
			else{
				s += '<tr><td height=5 bgcolor='+bgColorLight+' onmouseover="startScroll(this,true);this.bgColor=\''+bgColorMain+'\'" onmouseout="stopScroll();this.bgColor=\''+bgColorLight+'\'"' +
					'onclick="return false" title="向上滚动年份"></td></tr>';
			}
			for(var i=n;i<n+12;i++){
				s += '<tr><td class=' + (i==parseInt(strYear)?'selectedYM':'normalYM') + ' onmouseover="mOver(this)" onmouseout="mOut(this)" onmousedown="setYear('+i+')">' + i + ' 年' + '</td></tr>';
			}
			if(this.ScrollYearOnClick){
				s += '<tr><td height=5 bgcolor=' + this.theme.bgColorLight + ' onmouseover="this.bgColor=\''+bgColorMain+'\'" onmouseout="stopScroll();this.bgColor=\''+bgColorLight+'\'"' +
					'onclick="return false" onmousedown="startScroll(this,false)" onmouseup="stopScroll()" title="向下滚动年份"></td></tr>';
			}
			else{
				s += '<tr><td height=5 bgcolor=' + this.theme.bgColorLight + ' onmouseover="startScroll(this,false);this.bgColor=\''+bgColorMain+'\'" onmouseout="stopScroll();this.bgColor=\''+bgColorLight+'\'"' +
					'onclick="return false" title="向下滚动年份"></td></tr>';
			}
			s += '</table>';
		}
		this.oCalendar.getElementById("tmpSelectYearLayer").innerHTML = s;
		this.oCalendar.getElementById("tmpSelectYearLayer").style.display="";
	};

	this.close = function(){			//关闭日历控件
		this.oCalendar.getElementById("tmpSelectYearLayer").style.display="none";
		this.oCalendar.getElementById("tmpSelectMonthLayer").style.display="none";
		this.Style.display="none";
	};

	function getDaysOfMonth(year,month){	//得到某年某月的天数
		return((new Date(year,month,0)).getDate());
	}

	this.goPrevYear=function (){	//往前翻 year
		if(this.year > 1000 && this.year <10000){this.year--;
		this.writeCalendar();}
	};
	this.goNextYear=function (){	//往后翻 year
		if(this.year > 999 && this.year <9999){this.year++;
		this.writeCalendar();}
	};
	this.selectToday=function (){	//Today Button
		var today;
		this.year = new Date().getFullYear();
		this.month = new Date().getMonth()+1;
		today=new Date().getDate();
		if(this.month<10 && this.MonthinMM)this.month="0" + this.month;	//added in Ver 2.1
		if(today<10 && this.Dateindd)today="0" + today;
		//WriteCalendar(wpCalendar.year,wpCalendar.month);
		if(this.tgtObject){
			this.tgtObject.value=this.datestyle.replace((this.Dateindd?"dd":"d"), today).replace("yyyy", this.year).replace((this.MonthinMM?"MM":"M"), this.month);
		}
		this.close();
		if(this.followcodes!="")eval(this.followcodes);
	};
	this.goPrevMonth=function (){		//往前翻月份
		if(this.month>1){this.month--}else{this.year--;this.month=12;}
		this.writeCalendar();
	};
	this.goNextMonth=function (){		//往后翻月份
		if(this.month==12){this.year++;this.month=1}else{this.month++}
		this.writeCalendar();
	};
	this.setDateStyle=function (datestyle){	//设置日期格式的函数
		this.datestyle=datestyle;
		this.redraw();
	};
	this.setTheme=function (themename){	//设置主题为对应的名字
		this.themename=themename;
		this.redraw();
	};

	this.setMonth=function (i){			//设置日历的月份为month的值，其范围为1~12。
		if(isNaN(i)||i<=0||i>12)return;
		this.month=parseInt(i);
		this.writeCalendar();
	};
	this.setYear=function (i){			//设置日历的年份为year的值，其范围为1000~9999。
		if(isNaN(i)||i<=999||i>9999)return;
		this.year=parseInt(i);
		this.writeCalendar();
	};
	this.setYearMonth=function (year, month){	//设置日历的年月分别为year和month，年月的范围同上。
		if(isNaN(year)||year<=999||year>9999)return;
		if(isNaN(month)||month<=0||month>12)return;
		this.year=parseInt(year);
		this.month=parseInt(month);
		this.writeCalendar();
	};

	this.writeCalendar=function (){	//主要的写程序**********
		var yy = this.year;
		var mm = this.month;
		//写入头部的年月
		this.oCalendar.getElementById("CalendarYearHead").innerHTML	= yy + " 年";
		this.oCalendar.getElementById("CalendarMonthHead").innerHTML	= mm + " 月";
	  
		for (var i = 0; i < 39; i++){	//初始化边框
		//	this.DateCell[i].borderColorLight=this.theme.borderColor;
		//	this.DateCell[i].borderColorDark=this.theme.borderColorDark;
		}
		var day1 = 1,day2=1,firstday = new Date(yy,mm-1,1).getDay();  //某月第一天的星期几
		var year, month;
		for (i=0;i<firstday;i++){	//上个月的部分
			year = mm==1?yy-1:yy;
			month= mm==1?12:mm-1;
			this.Days[i]=new wpDay(year, month, getDaysOfMonth(year,month)-firstday+i+1);	//上个月的最后几天
		}
		for (i = firstday; day1 < getDaysOfMonth(yy,mm)+1; i++){	//本月的部分
			this.Days[i]=new wpDay(yy, mm, day1);day1++;
		}
		for (i=firstday+getDaysOfMonth(yy,mm);i<39;i++){	//下个月的部分
			year = mm==12?yy+1:yy;
			month= mm==12?1:mm+1;
			this.Days[i]=new wpDay(year, month, day2);day2++;
		}
		for (i=0; i<39; i++)
		{
			var da = this.DateCell[i];
			if(this.outerDate && this.Days[i].equals(this.outerDate)){
				if(i<8 && this.Days[i].day>20 || i>=28 && this.Days[i].day<12)da.className="selectedgrayday"
				else da.className="selectedday";
				//da.borderColorLight=this.theme.borderColorDark;
				//da.borderColorDark=this.theme.borderColor;
			}
			else if(this.Days[i].equals(new Date())){
				if(i<8 && this.Days[i].day>20 || i>=28 && this.Days[i].day<12)da.className="graytoday"
				else da.className="today";
			}
			else{
				if(i<8 && this.Days[i].day>20 || i>=28 && this.Days[i].day<12)da.className="grayday"
				else da.className="normalday";
			}
			da.innerHTML=this.Days[i].day;
			da.title=this.Days[i].date;
		}
	};

	this.dateCellClick=function (n){  //点击显示框选取日期，主输入函数*************
		if (this.tgtObject){
			this.tgtObject.value= this.Days[n].date;	//modified in Ver 2.51
			this.close(); this.tgtObject.blur();
		}
		else {this.close(); alert("您所要输出的控件对象并不存在！");}
		if(this.followcodes!="")eval(this.followcodes);
	};

	//当鼠标移动出iframe的时候纠正日历位置的函数，使得日历更加不容易停下来
	//*但是当鼠标移动太快，并且在外面产生了mousemove的事件以后日历仍然会停下来
	this.correctPosition = function(){
		var CalendarWindow = window.frames.wpCalendarLayer;
		if(CalendarWindow.bDrag){
			var x = event.clientX - CalendarWindow.datelayerx;
			var y = event.clientY - CalendarWindow.datelayery;
			this.Style.left	= (x<0?0:x) + "px";
			this.Style.top	= (y<0?0:y) + "px";
		}
	};

	var self = this;	//用于下面的内部函数可以访问到wpCalendar对象本身
	function wpMouseOver() {
		if(self.playfilter)this.filters[0].Apply();
		// After you set Apply, changes to the oDiv object 
		//  are not displayed until Play is called.
		self.tmpTdClass	= this.className;
		this.className			= "mouseover";
		if(self.playfilter)this.filters[0].Play();
	}
	function wpMouseOut(){
		if(self.playfilter)this.filters[0].Apply();
		this.className	= self.tmpTdClass;
		if(self.playfilter)this.filters[0].Play();
	}

	//抽象的日期对象，初始化接受年月日的参数，月的范围是1到12
	function wpDay(year, month, day){
		this.year	= year;
		this.month	= month;
		this.day	= day;
		var mm=month;var n=day;
		if (month < 10 && self.MonthinMM)mm = "0" + mm;
		if (day < 10 && self.Dateindd)n = "0" + n;
		//提示文字变为与输出格式完全一致，好不好有待实践证明
		this.date	= self.datestyle.replace((self.Dateindd?"dd":"d"), n).replace("yyyy", year).replace((self.MonthinMM?"MM":"M"), mm);

		//判断是否与另一个日期相等的函数，接受的参数是日期对象
		this.equals = function(eDate){
			try{return this.year==eDate.getFullYear() && this.month==eDate.getMonth()+1 && this.day==eDate.getDate();}
			catch(e){return false}
		}
	}
}

function CalendarTheme(){
	this.versioninfo		= "";	//主题的版本信息
	this.fontFamily			= "";	//字体
	this.borderColor		= "";	//边框颜色
	this.borderColorDark	= "";	//边框暗部颜色
	this.bgColorMain		= "";	//日历主背景色
	this.bgColorLight		= "";	//日历的浅背景色
	this.headBgColor		= "";	//日历头部背景色
	this.headFontColor		= "";	//日历头部字体颜色
	this.mouseOverColor		= "";	//鼠标移动的背景色
	this.mouseOverFontColor = "";	//鼠标移动的字体颜色
	this.buttonBorderColor	= "";	//按钮边框颜色
	this.buttonColor		= "";	//按钮的主色调
	this.buttonFontColor	= "";	//按钮文字颜色
	this.dragBarFontColor	= "";	//拖动条的文字颜色
	this.dragBarColor		= "";	//拖动条的边框颜色
	this.dragBarColorDark	= "";	//拖动条的边框暗部颜色
	this.normalDayColor		= "";	//日期的背景颜色
	this.normalDayFontColor	= "";	//日期的字体颜色
	this.grayDayColor		= "";	//非本月日期的背景颜色
	this.grayDayFontColor	= "";	//非本月日期的字体颜色
	this.todayColor			= "";	//当前日期的背景颜色
	this.todayFontColor		= "";	//当前日期的字体颜色
	this.selectedDayColor	= "";	//选中日期的背景颜色
	this.selectedDayFontColor="";	//选中日期的字体颜色
}
setday=showCalendar;	//保留老的调用方式
function showCalendar() //主调函数
{
	var obj;
	if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
	var tt=arguments[0];
	wpCalendar.followcodes="";	//每次调用的时候都初始化followCodes的内容
	if(arguments.length==2){
		if(typeof(arguments[1])=="object")obj=arguments[1]
		else if(typeof(arguments[1])=="string")wpCalendar.followcodes=arguments[1];
	}else if(arguments.length==3){
		obj=arguments[1];
		wpCalendar.followcodes=arguments[1];
	}
	
	var dads  = wpCalendar.Style;
	var th = tt;
	var ttop  = tt.offsetTop;		//TT控件的定位点高
	var thei  = tt.clientHeight;	//TT控件本身的高
	var twid  = tt.clientWidth;		//TT控件本身的宽	//added in Ver 2.2
	var tleft = tt.offsetLeft;		//TT控件的定位点宽
	var ttyp  = tt.type;			//TT控件的类型
	while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
	//同时满足1.总高度足以放得下日历控件；2.下方剩余高度不足日历控件高度；3.上方高度大于日历控件高度 这3个条件的时候日历将会显示在上方
	if(document.body.clientHeight>211 + thei && document.body.clientHeight-(ttop-document.body.scrollTop)<211+thei && ttop-document.body.scrollTop>211)	//added in Ver 2.1
		dads.top = ttop-211
	else dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
	var cwidth = wpCalendar.getCalendarWidth();
	if(document.body.clientWidth-(tleft-document.body.scrollLeft)<cwidth && tleft-document.body.scrollLeft>cwidth)	//added in Ver 2.2
		dads.left = tleft + twid - cwidth + ((ttyp=="image")? 0:4)
	else dads.left = tleft;
	wpCalendar.tgtObject = !obj ? th : obj;
	wpCalendar.srcButton = !obj ? null : th;	//设定外部点击的按钮
	//根据当前输入框的日期显示日历的年月
	var reg = new RegExp("^" + wpCalendar.datestyle.replace((wpCalendar.Dateindd?"dd":"d"),"(\\d{1,2})").replace("yyyy","(\\d{4})").replace((wpCalendar.MonthinMM?"MM":"M"), "(\\d{1,2})")+"$");	//added in Ver 2.1
	var r = wpCalendar.tgtObject.value.match(reg); 
	if(r!=null){
		var t=getIndex();
		r[t[2]]=r[t[2]]-1; 
		if(wpCalendar.datestyle.indexOf("d")<0)r[t[3]]=1;			//使用一个"d"就可以包括两种情况	 //added in Ver 2.1
		var d= new Date(r[t[1]], r[t[2]],r[t[3]]);
		if(d.getFullYear()==r[t[1]] && d.getMonth()==r[t[2]] && d.getDate()==r[t[3]]){
			wpCalendar.outerDate=d;		//保存外部传入的日期
		}
		else wpCalendar.outerDate="";
		wpCalendar.year	=parseInt(r[t[1]]);
		wpCalendar.month=parseInt(r[t[2]])+1;
	}
	else{
		wpCalendar.outerDate="";
		wpCalendar.year	=new Date().getFullYear();
		wpCalendar.month=new Date().getMonth() + 1;
	}
	wpCalendar.writeCalendar();
	dads.display = '';

	event.returnValue=false;

	function getIndex(){	//根据日期格式设置返回年月日分别所在的位置 //added in Ver 2.1
		var i,j;
		var m=eval("new Array(wpCalendar.datestyle.indexOf(\"yyyy\"), wpCalendar.datestyle.indexOf(\"M\")" + (wpCalendar.datestyle.indexOf("d")>=0?", wpCalendar.datestyle.indexOf(\"d\")":"") + ")");	//使用一个"M"和一个"d"就可以包括两种情况
		var t=new Array();
		for(i=1;i<=m.length;i++){
			t[i]=1;
			for(j=1;j<=m.length;j++)if(i!=j && m[i-1]>m[j-1])t[i]++;
		}
		if(m.length<3)t[3]=3;
		return t;
	}
}


wpEvents.addListener(document,"click",function(){		//任意点击时关闭该控件
	with(window.event)
		if (srcElement != wpCalendar.tgtObject && srcElement != wpCalendar.srcButton)
		wpCalendar.close();
});

wpEvents.addListener(document,"keyup",function(){		//按Esc键关闭，切换焦点关闭
	if (window.event.keyCode==27){
		if(wpCalendar.tgtObject)wpCalendar.tgtObject.blur();
		wpCalendar.close();
	}
	else if(document.activeElement)
		if(document.activeElement != wpCalendar.tgtObject && document.activeElement != wpCalendar.srcButton)
			wpCalendar.close();
});

var theme=new Object();

	theme["Classic"]=new CalendarTheme();
	theme["Classic"].versioninfo		= "主题：Classic 作者：WalkingPoison";	//主题的版本信息
	theme["Classic"].fontFamily			= "宋体";		//字体
	theme["Classic"].borderColor		= "#FF9900";	//边框颜色
	theme["Classic"].borderColorDark	= "#FFFFFF";	//边框暗部颜色
	theme["Classic"].bgColorMain		= "#FF9900";	//日历主背景色
	theme["Classic"].bgColorLight		= "#FFF8EC";	//日历的浅背景色
	theme["Classic"].headBgColor		= "#FFFFFF";	//日历头部背景色
	theme["Classic"].headFontColor		= "#000000";	//日历头部字体颜色
	theme["Classic"].mouseOverColor		= "#FFD700";	//鼠标移动的背景色
	theme["Classic"].mouseOverFontColor = "#000000";	//鼠标移动的字体颜色
	theme["Classic"].buttonBorderColor	= "#FF9900";	//按钮边框颜色
	theme["Classic"].buttonColor		= "#FFF8EC";	//按钮的主色调
	theme["Classic"].buttonFontColor	= "#000000";	//按钮文字颜色
	theme["Classic"].dragBarFontColor	= "#FFFFFF";	//拖动条的文字颜色
	theme["Classic"].dragBarColor		= "#FF9900";	//拖动条的边框颜色
	theme["Classic"].dragBarColorDark	= "#FFFFFF";	//拖动条的边框暗部颜色
	theme["Classic"].normalDayColor		= "#E0E0E0";	//日期的背景颜色
	theme["Classic"].normalDayFontColor	= "#000000";	//日期的字体颜色
	theme["Classic"].grayDayColor		= "#E0E0E0";	//非本月日期的背景颜色
	theme["Classic"].grayDayFontColor	= "#808080";	//非本月日期的字体颜色
	theme["Classic"].todayColor			= "#FFD700";	//当前日期的背景颜色
	theme["Classic"].todayFontColor		= "#000000";	//当前日期的字体颜色
	theme["Classic"].selectedDayColor	= "#00FFFF";	//选中日期的背景颜色
	theme["Classic"].selectedDayFontColor="#000000";	//选中日期的字体颜色

	theme["NewAge"]=new CalendarTheme();
	theme["NewAge"].versioninfo			= "主题：NewAge 作者：WalkingPoison";	//主题的版本信息
	theme["NewAge"].fontFamily			= "宋体";		//字体
	theme["NewAge"].borderColor			= "#336699";	//边框颜色
	theme["NewAge"].borderColorDark		= "#FFFFFF";	//边框暗部颜色
	theme["NewAge"].bgColorMain			= "#336699";	//日历主背景色
	theme["NewAge"].bgColorLight		= "#F0F8FF";	//日历的浅背景色
	theme["NewAge"].headBgColor			= "#FFFFFF";	//日历头部背景色
	theme["NewAge"].headFontColor		= "#000000";	//日历头部字体颜色
	theme["NewAge"].mouseOverColor		= "#add8e6";	//鼠标移动的背景色
	theme["NewAge"].mouseOverFontColor	= "#000000";	//鼠标移动的字体颜色
	theme["NewAge"].buttonBorderColor	= "#336699";	//按钮边框颜色
	theme["NewAge"].buttonColor			= "#F0F8FF";	//按钮的主色调
	theme["NewAge"].buttonFontColor		= "#000000";	//按钮文字颜色
	theme["NewAge"].dragBarFontColor	= "#FFFFFF";	//拖动条的文字颜色
	theme["NewAge"].dragBarColor		= "#336699";	//拖动条的边框颜色
	theme["NewAge"].dragBarColorDark	= "#336699";	//拖动条的边框暗部颜色
	theme["NewAge"].normalDayColor		= "#fff0f5";	//日期的背景颜色
	theme["NewAge"].normalDayFontColor	= "#000000";	//日期的字体颜色
	theme["NewAge"].grayDayColor		= "#fff0f5";	//非本月日期的背景颜色
	theme["NewAge"].grayDayFontColor	= "#808080";	//非本月日期的字体颜色
	theme["NewAge"].todayColor			= "#add8e6";	//当前日期的背景颜色
	theme["NewAge"].todayFontColor		= "#000000";	//当前日期的字体颜色
	theme["NewAge"].selectedDayColor	= "#336699";	//选中日期的背景颜色
	theme["NewAge"].selectedDayFontColor= "#FFFFFF";	//选中日期的字体颜色

	theme["Nostalgia"]=new CalendarTheme();
	theme["Nostalgia"].versioninfo			= "主题：Nostalgia 作者：WalkingPoison";	//主题的版本信息
	theme["Nostalgia"].fontFamily			= "宋体";		//字体
	theme["Nostalgia"].borderColor			= "#CC3300";	//边框颜色
	theme["Nostalgia"].borderColorDark		= "#CC3300";	//边框暗部颜色
	theme["Nostalgia"].bgColorMain			= "#CC3300";	//日历主背景色
	theme["Nostalgia"].bgColorLight			= "#FFF8EC";	//日历的浅背景色
	theme["Nostalgia"].headBgColor			= "#FFFFFF";	//日历头部背景色
	theme["Nostalgia"].headFontColor		= "#000000";	//日历头部字体颜色
	theme["Nostalgia"].mouseOverColor		= "#FFD700";	//鼠标移动的背景色
	theme["Nostalgia"].mouseOverFontColor	= "#000000";	//鼠标移动的字体颜色
	theme["Nostalgia"].buttonBorderColor	= "#CC3300";	//按钮边框颜色
	theme["Nostalgia"].buttonColor			= "#FFF8EC";	//按钮的主色调
	theme["Nostalgia"].buttonFontColor		= "#000000";	//按钮文字颜色
	theme["Nostalgia"].dragBarFontColor		= "#FFFFFF";	//拖动条的文字颜色
	theme["Nostalgia"].dragBarColor			= "#CC3300";	//拖动条的边框颜色
	theme["Nostalgia"].dragBarColorDark		= "#FFFFFF";	//拖动条的边框暗部颜色
	theme["Nostalgia"].normalDayColor		= "#ffefd5";	//日期的背景颜色
	theme["Nostalgia"].normalDayFontColor	= "#000000";	//日期的字体颜色
	theme["Nostalgia"].grayDayColor			= "#ffefd5";	//非本月日期的背景颜色
	theme["Nostalgia"].grayDayFontColor		= "#808080";	//非本月日期的字体颜色
	theme["Nostalgia"].todayColor			= "#FFD700";	//当前日期的背景颜色
	theme["Nostalgia"].todayFontColor		= "#000000";	//当前日期的字体颜色
	theme["Nostalgia"].selectedDayColor		= "#CCFFFF";	//选中日期的背景颜色
	theme["Nostalgia"].selectedDayFontColor	= "#000000";	//选中日期的字体颜色

	theme["Icicle"]=new CalendarTheme();
	theme["Icicle"].versioninfo			= "主题：Icicle 作者：WalkingPoison";	//主题的版本信息
	theme["Icicle"].fontFamily			= "宋体";		//字体
	theme["Icicle"].borderColor			= "#00bfff";	//边框颜色
	theme["Icicle"].borderColorDark		= "#ffffff";	//边框暗部颜色
	theme["Icicle"].bgColorMain			= "#00bfff";	//日历主背景色
	theme["Icicle"].bgColorLight		= "#f0faff";	//日历的浅背景色
	theme["Icicle"].headBgColor			= "#FFFFFF";	//日历头部背景色
	theme["Icicle"].headFontColor		= "#000000";	//日历头部字体颜色
	theme["Icicle"].mouseOverColor		= "#e0e9ff";	//鼠标移动的背景色
	theme["Icicle"].mouseOverFontColor	= "#000000";	//鼠标移动的字体颜色
	theme["Icicle"].buttonBorderColor	= "#87cefa";	//按钮边框颜色
	theme["Icicle"].buttonColor			= "#FFF8EC";	//按钮的主色调
	theme["Icicle"].buttonFontColor		= "#000000";	//按钮文字颜色
	theme["Icicle"].dragBarFontColor	= "#336699";	//拖动条的文字颜色
	theme["Icicle"].dragBarColor		= "#00bfff";	//拖动条的边框颜色
	theme["Icicle"].dragBarColorDark	= "#FFFFFF";	//拖动条的边框暗部颜色
	theme["Icicle"].normalDayColor		= "#e0e9ff";	//日期的背景颜色
	theme["Icicle"].normalDayFontColor	= "#336699";	//日期的字体颜色
	theme["Icicle"].grayDayColor		= "#e0e9ff";	//非本月日期的背景颜色
	theme["Icicle"].grayDayFontColor	= "#808080";	//非本月日期的字体颜色
	theme["Icicle"].todayColor			= "#FFD700";	//当前日期的背景颜色
	theme["Icicle"].todayFontColor		= "#336699";	//当前日期的字体颜色
	theme["Icicle"].selectedDayColor	= "#CCFFFF";	//选中日期的背景颜色
	theme["Icicle"].selectedDayFontColor= "#336699";	//选中日期的字体颜色

	theme["Greenbrier"]=new CalendarTheme();
	theme["Greenbrier"].versioninfo			= "主题：Greenbrier 作者：liuliu";	//主题的版本信息
	theme["Greenbrier"].fontFamily			= "宋体";		//字体
	theme["Greenbrier"].borderColor			= "#32CD32";	//边框颜色
	theme["Greenbrier"].borderColorDark		= "#32CD32";	//边框暗部颜色
	theme["Greenbrier"].bgColorMain			= "#32CD32";	//日历主背景色
	theme["Greenbrier"].bgColorLight		= "#FFF8EC";	//日历的浅背景色
	theme["Greenbrier"].headBgColor			= "#FFFFFF";	//日历头部背景色
	theme["Greenbrier"].headFontColor		= "#000000";	//日历头部字体颜色
	theme["Greenbrier"].mouseOverColor		= "#FFD700";	//鼠标移动的背景色
	theme["Greenbrier"].mouseOverFontColor	= "#000000";	//鼠标移动的字体颜色
	theme["Greenbrier"].buttonBorderColor	= "#2E8B57";	//按钮边框颜色
	theme["Greenbrier"].buttonColor			= "#FFF8EC";	//按钮的主色调
	theme["Greenbrier"].buttonFontColor		= "#000000";	//按钮文字颜色
	theme["Greenbrier"].dragBarFontColor	= "#FFFFFF";	//拖动条的文字颜色
	theme["Greenbrier"].dragBarColor		= "#2E8B57";	//拖动条的边框颜色
	theme["Greenbrier"].dragBarColorDark	= "#FFFFFF";	//拖动条的边框暗部颜色
	theme["Greenbrier"].normalDayColor		= "#FFF8DC";	//日期的背景颜色
	theme["Greenbrier"].normalDayFontColor	= "#000000";	//日期的字体颜色
	theme["Greenbrier"].grayDayColor		= "#FFF8DC";	//非本月日期的背景颜色
	theme["Greenbrier"].grayDayFontColor	= "#808080";	//非本月日期的字体颜色
	theme["Greenbrier"].todayColor			= "#FFD700";	//当前日期的背景颜色
	theme["Greenbrier"].todayFontColor		= "#000000";	//当前日期的字体颜色
	theme["Greenbrier"].selectedDayColor	= "#00FFFF";	//选中日期的背景颜色
	theme["Greenbrier"].selectedDayFontColor= "#000000";	//选中日期的字体颜色

	theme["DiabloII"]=new CalendarTheme();
	theme["DiabloII"].versioninfo			= "主题：DiabloII 作者：WalkingPoison";	//主题的版本信息
	theme["DiabloII"].fontFamily			= "宋体";		//字体
	theme["DiabloII"].borderColor			= "#928a70";	//边框颜色
	theme["DiabloII"].borderColorDark		= "#928a70";	//边框暗部颜色
	theme["DiabloII"].bgColorMain			= "#333333";	//日历主背景色
	theme["DiabloII"].bgColorLight			= "#333333";	//日历的浅背景色
	theme["DiabloII"].headBgColor			= "#333333";	//日历头部背景色
	theme["DiabloII"].headFontColor			= "#928a70";	//日历头部字体颜色
	theme["DiabloII"].mouseOverColor		= "#666666";	//鼠标移动的背景色
	theme["DiabloII"].mouseOverFontColor	= "#afafaf";	//鼠标移动的字体颜色
	theme["DiabloII"].buttonBorderColor		= "#333333";	//按钮边框颜色
	theme["DiabloII"].buttonColor			= "#333333";	//按钮的主色调
	theme["DiabloII"].buttonFontColor		= "#928a70";	//按钮文字颜色
	theme["DiabloII"].dragBarFontColor		= "#928a70";	//拖动条的文字颜色
	theme["DiabloII"].dragBarColor			= "#333333";	//拖动条的边框颜色
	theme["DiabloII"].dragBarColorDark		= "#333333";	//拖动条的边框暗部颜色
	theme["DiabloII"].normalDayColor		= "#333333";	//日期的背景颜色
	theme["DiabloII"].normalDayFontColor	= "#928a70";	//日期的字体颜色
	theme["DiabloII"].grayDayColor			= "#333333";	//非本月日期的背景颜色
	theme["DiabloII"].grayDayFontColor		= "#afafaf";	//非本月日期的字体颜色
	theme["DiabloII"].todayColor			= "#333333";	//当前日期的背景颜色
	theme["DiabloII"].todayFontColor		= "#2e8b57";	//当前日期的字体颜色
	theme["DiabloII"].selectedDayColor		= "#666666";	//选中日期的背景颜色
	theme["DiabloII"].selectedDayFontColor	= "#ffcc33";	//选中日期的字体颜色

var wpCalendar=new wpCalendar();
wpCalendar.redraw();

// -->
