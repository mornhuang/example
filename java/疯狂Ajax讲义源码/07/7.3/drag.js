function drag(target, event)
{
	// 定义开始拖动时的鼠标位置（相对window座标）
	var startX = event.clientX;
	var startY = event.clientY;
	//定义将要被拖动元素的位置（相对于document座标）
	//因为该target的position为absolutely，所以认为它基于的座标系是document
	var origX = target.offsetLeft;
	var origY = target.offsetTop;
	//因为后面根据event的clientX、clientY来获取鼠标位置时，只能获取windows座标系
	//的位置，所以需要计算window座标系和document座标系的偏移。
	//计算windows座标系和document座标系之间的偏移
	var deltaX = startX - origX;
	var deltaY = startY - origY;
	//设置该元素直接捕获该事件
	target.setCapture();
	//为该元素鼠标移动时绑定事件处理器
	target.attachEvent("onmousemove", moveHandler);
	//为鼠标松开时绑定事件处理器
	target.attachEvent("onmouseup", upHandler);
	//将失去捕获事件当成鼠标松开处理。
	target.attachEvent("onlosecapture", upHandler);
	//阻止事件冒泡
	event.cancelBubble=true;
	event.returnValue = false;

	//鼠标移动的事件处理器
	function moveHandler()
	{
		//对于IE事件模型，获取事件对象
		var evt = window.event; 
		//将被拖动元素的位置移动到当前鼠标位置。
		//先将window座标系位置转换成document座标系位置，再修改目标对象的CSS位置。
		target.style.left = (evt.clientX - deltaX) + "px";
		target.style.top = (evt.clientY - deltaY) + "px";
		//阻止事件冒泡
		evt.cancelBubble=true;
	}
	//鼠标松开的事件处理器
	function upHandler() 
	{
		//对于IE事件模型，获取事件对象
		var evt = window.event; 
		//取消该对象上绑定的事件处理器
		target.detachEvent("onlosecapture", upHandler);
		target.detachEvent("onmouseup", upHandler);
		target.detachEvent("onmousemove", moveHandler);
		//释放该对象的“事件捕获”
		target.releaseCapture();
		//阻止事件冒泡
		evt.cancelBubble=true;
	}
}