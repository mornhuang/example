function drag(target, event)
{
	// 定义开始拖动时的鼠标位置（相对window座标）
	var startX = event.clientX;
	var startY = event.clientY;
	//定义将要被拖动元素的位置（相对于document座标）
	//因为该target的position为absolutely，所以我们认为它的基于的座标系是document
	var origX = target.offsetLeft;
	var origY = target.offsetTop;
	//因为后面根据event的clientX、clientY来获取鼠标位置时，只能获取windows座标系
	//的位置，所以需要计算window座标系和document座标系的偏移。
	//计算windows座标系和document座标系之间的偏移
	var deltaX = startX - origX;
	var deltaY = startY - origY;
	//为被拖动对象的鼠标移动（mousemove）和鼠标松开（mouseup）注册事件处理器
	if (document.addEventListener)
	{
		//DOM 2事件模型
		//在事件捕获阶段绑定事件处理器
		document.addEventListener("mousemove", moveHandler, true);
		document.addEventListener("mouseup", upHandler, true);
	}
	else if (document.attachEvent) 
	{
		//IE 事件模型
		//设置该元素直接捕获该事件
		target.setCapture();
		//为该元素鼠标移动时绑定事件处理器
		target.attachEvent("onmousemove", moveHandler);
		//为鼠标松开时绑定事件处理器
		target.attachEvent("onmouseup", upHandler);
		//将失去捕获事件当成鼠标松开处理。
		target.attachEvent("onlosecapture", upHandler);
	}
	//阻止事件传播
	stopProp(event);
	//取消事件默认行为
	if (event.preventDefault)
	{
		//DOM 2事件模型
		event.preventDefault( ); 
	}
	else
	{
		//IE事件模型
		event.returnValue = false;
	}
	//鼠标移动的事件处理器
	function moveHandler(evt)
	{
		//对于IE事件模型，获取事件对象
		if (!evt) evt = window.event; 
		//将被拖动元素的位置移动到当前鼠标位置。
		//先将window座标系位置转换成document座标系位置，再修改目标对象的CSS位置。
		target.style.left = (evt.clientX - deltaX) + "px";
		target.style.top = (evt.clientY - deltaY) + "px";
		//阻止事件传播
		stopProp(evt);
	}
	//鼠标松开的事件处理器
	function upHandler(evt) 
	{
		//对于IE事件模型，获取事件对象
		if (!evt) evt = window.event; 
		//取消被拖动对象的鼠标移动（mousemove）和鼠标松开（mouseup）的事件处理器
		if (document.removeEventListener)
		{
			//DOM 2事件模型
			//取消在事件捕获阶段的事件处理器
			document.removeEventListener("mouseup", upHandler, true);
			document.removeEventListener("mousemove", moveHandler, true);
		}
		else if (document.detachEvent) 
		{
			target.detachEvent("onlosecapture", upHandler);
			target.detachEvent("onmouseup", upHandler);
			target.detachEvent("onmousemove", moveHandler);
			target.releaseCapture( );
		}
		//阻止事件传播
		stopProp(evt);
	}
	//阻止事件传播(该函数可以跨浏览器)
	function stopProp(evt)
	{
		//DOM 2事件模型
		if (evt.stopPropagation)
		{
			evt.stopPropagation( );
		}
		else 
		{
			//事件模型
			evt.cancelBubble = true;    
		}
	}
}