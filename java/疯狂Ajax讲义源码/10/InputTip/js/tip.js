var tip = "";
function searchBrand()
{
	//发送请求的服务器URL
	var url = 'getBrands.do';
	//生成请求参数
	var params = "prefix=" + $F('brand');
	//创建Ajax.Request对象，用于发送请求
	new Ajax.Request(
	url,
	{
		method:'post',
		//发送参数
		parameters: params,
		//指定回调函数。
		onComplete: showResponse,
	});
}

function searchModel()
{
	//发送请求的URL
	var url = 'getModels.do';
	var params = "brand=" + $F('brand');
	//创建Ajax.Request对象，发送异步请求
	new Ajax.Request(
	url,
	{
		method:'post',
		//设置请求参数
		parameters: params,
		//指定回调函数
		onComplete: showModel,
	});
}

function getDetail()
{
	//发送请求的URL
	var url = 'getDetail.do';
	var params = "model=" + $F('model');
	//创建Ajax.Updater对象，发送异步请求
	new Ajax.Updater(
	//指定更新modelDetail元素
	'modelDetail',
	url,
	{
		method: 'post',
		parameters: params,
		//使用匿名函数作为回调函数，该函数控制详细信息<div.../>元素的出现
		onComplete:function()
		{
			$('modelDetail').show();
		}
	});
}

function showResponse(request)
{
	//获取服务器响应，并将响应字符串以$符作为分隔符，分解成字符串数组
	var brandList = request.responseText.split("$");
	//获取品牌提示元素
	var bt = $("brandTip");
	//清空品牌提示元素的内容
	bt.innerHTML = "";
	//如果字符串数组的长度大于1
	if ( brandList.length > 1)
	{
		//遍历品牌名，将每个品牌添加到brandTip元素中
		for(var i = 0 ; i < brandList.length - 1 ; i++)
		{
			var a = document.createElement("div");
			a.innerHTML = brandList[i];
			bt.appendChild(a);
		}
		//选中第一个品牌所在<div.../>元素
		bt.firstChild.className = "selectTip";
		//显示brandTip元素
		if( tip != $("brand").value)
		{
			bt.show();
		}
	}
	else
	{
		//隐藏brandTip元素
		bt.hide();
	}
}

function showModel(request)
{
	//将服务器响应字符串以$作为分隔符分解成字符串数组
	var modelList = request.responseText.split("$");
	//获取型号提示的<div.../>元素
	var mt = $("modelTip");
	//清空型号提示的<div.../>的内容
	mt.innerHTML = "";
	//如果字符串数组长度大于1
	if ( modelList.length > 1)
	{
		//遍历服务器提示的所有型号
		for(var i = 0 ; i < modelList.length - 1 ; i++)
		{
			//对每个型号创建<div.../>元素
			var a = document.createElement("div");
			//使用<div.../>显示当前型号的型号名
			a.innerHTML = modelList[i];
			//添加当前型号提示
			mt.appendChild(a);
		}
		//选中第一项
		mt.firstChild.className = "selectTip";
		//显示型号提示元素
		if( tip != mt.value)
		{
			mt.show();
		}
	}
	else
	{
		//隐藏型号提示元素
		mt.hide();
	}
}

function move(event)
{
	//获取用户单击事件的事件源：只有brand或model两个元素
	var srcEl = Event.element(event);
	//获取brandTip或modelTip元素，即获取品牌或模型提示所用的<div.../>元素
	var tipEl = $(srcEl.id + "Tip");
	//如果提示DIV元素处于显示状态才需要处理
	//如果提示DIV元素都没有出现，用户单击向上、向下键都不会有任何反应。
	if (tipEl.style.display == "" )
	{
		//向下键
		if(event.keyCode == 40 )
		{
			//如果提示框中有提示的品牌或提示型号
			if (tipEl.childNodes.length > 1)
			{
				//如果已经选中了最后一条
				if(tipEl.lastChild.className == "selectTip")
				{
					//改变为选中第一条
					tipEl.firstChild.className = "selectTip";
					//让最后一条不再处于选中状态
					tipEl.lastChild.className = "null";
					return ;
				}
				//获取提示框的提示行数组
				var bList = tipEl.childNodes;
				//遍历提示<div.../>元素中的品牌或型号
				for (var i = 0 ; i < bList.length - 1 ; i ++)
				{
					//如果第i个品牌或型号被选中
					if (bList[i].className == "selectTip")
					{
						//将的i+1个品牌或型号选中，即向下移动
						bList[i + 1].className = "selectTip";
						//将原来的第i个品牌或型号改为不选中
						bList[i].className = "null";
						return ;
					}
				}
			}

		}
		//向上键
		else if(event.keyCode == 38)
		{
			//如果提示框中有提示的品牌或提示型号
			if (tipEl.childNodes.length > 1)
			{
				//如果已经选中了第一条
				if(tipEl.firstChild.className == "selectTip")
				{
					//改为选中最后一条
					tipEl.lastChild.className = "selectTip";
					//将第一条改为不选中
					tipEl.firstChild.className = "null";
					return ;
				}
				//获取提示框的提示行数组
				var bList = tipEl.childNodes;
				//遍历所有的品牌或型号
				for (var i = 1 ; i < bList.length ; i ++)
				{
					//如果第i个品牌或型号为选中状态
					if (bList[i].className == "selectTip")
					{
						//将i-1个品牌或型号改为选中状态，即向上移动
						bList[i - 1].className = "selectTip";
						//将原来的i个品牌或型号改为不选中
						bList[i].className = "null";
						return ;
					}
				}
			}
		}
		//回车键
		else if(event.keyCode == 13)
		{
			//获取提示<div.../>元素的所有子元素
			var bList = tipEl.childNodes;
			//遍历提示<div.../>元素的全部子元素
			for (var i = 0 ; i < bList.length ; i ++)
			{
				//如果第i个元素当前处于选中状态
				if (bList[i].className == "selectTip")
				{
					//将tip值、输入框的值改为选中状态下的品牌或型号。
					tip = srcEl.value = bList[i].innerHTML;	
					//隐藏提示
					Element.hide(tipEl);
					//如果是brand元素上发生了单击回车事件
					if (srcEl.id == "brand")
					{
						//将model元素获得焦点
						$("model").focus();
						//调用searchModel()，发送Ajax请求：显示当前品牌的全部型号
						searchModel();
					}
					//如果model元素发生了单击回车事件
					if (srcEl.id == "model")
					{
						//调用getDetail()，发送Ajax请求：显示当前品牌的详细信息
						getDetail();
					}
					return ;
				}
			}
		}
	}
}

//监听brand表单控件里value的改变
new Form.Element.Observer("brand", 1, searchBrand);
//为brand、model元素绑定keydown事件的处理器为move，
//false表示该事件处理器在冒泡阶段激发。
Event.observe("brand", "keydown", move, false);
Event.observe("model", "keydown", move, false);

var myGlobalHandlers = 
{
	//当创建XMLHttpRequest对象时，触发该匿名函数
	onCreate: function()
	{
		//显示loading元素
		$('loading').show();;
	},
	//如果服务器响应出错时触发该匿名函数
	onFailure:function()
	{
		alert('对不起!\n页面加载出错!');
	},
	//当Ajax交互完成时，触发该匿名函数
	onComplete: function()
	{
		//如果没有正在交互的Ajax请求
		if(Ajax.activeRequestCount == 0)
		{
			//隐藏loading元素
			$('loading').hide();
		}
	}
};
//ajax事件绑定
Ajax.Responders.register(myGlobalHandlers);