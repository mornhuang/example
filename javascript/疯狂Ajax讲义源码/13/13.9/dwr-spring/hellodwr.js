function sendMessage()
{
	//调用远程的hello方法，使用了dwr.util的getValue方法获取HTML元素的值
	hello.hello(dwr.util.getValue('name') , cb);
}
//回调方法
function cb(data)
{
	//使用dwr.util的setValue方法设置HTML元素的值
	dwr.util.setValue('show' ,data);
}
