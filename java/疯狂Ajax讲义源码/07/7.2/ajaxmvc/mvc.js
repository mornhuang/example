var objXMLHttp;
function createXMLHttpRequest()
{
	//对于Mozilla、Firefox、Opera等浏览器
	if (window.XMLHttpRequest)
	{
		objXMLHttp = new XMLHttpRequest();
	}
	//对于Internet Explorer浏览器
	else
	{
		var MSXML = ['MSXML2.XMLHTTP.5.0', 'MSXML2.XMLHTTP.4.0',
			'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP'];
		for(var n = 0; n < MSXML.length; n ++)
		{
			try
			{
				//创建XMLHttpRequest对象
				objXMLHttp = new ActiveXObject(MSXML[n]);        
				break;
			}
			catch(e)
			{
			}
		}
	}
}
createXMLHttpRequest();
//为test按钮绑定事件处理函数
document.getElementById("test").onclick=sendRequest;
function sendRequest()
{
	var url = "data";
	objXMLHttp.open("POST", url, true);
	//指定响应函数
	objXMLHttp.onreadystatechange = process;
	//发送请求
	objXMLHttp.send(null);
}
//process()是控制器函数
function process()
{
	//如果服务器响应到来
	if (objXMLHttp.readyState == 4 && 
		(objXMLHttp.status == 200 || objXMLHttp.status == 304))
	{
		//调用视图函数来加载服务器响应
		show(objXMLHttp.responseText);
	}
}
function show(content)
{
	//加载模型返回的数据
	document.getElementById("show").innerHTML=content;
}