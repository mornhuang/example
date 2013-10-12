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
		var MSXML = ['MSXML2.XMLHTTP.5.0', 'MSXML2.XMLHTTP.4.0', 'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP'];
		for(var n = 0; n < MSXML.length; n ++)
		{
			try
			{
				objXMLHttp = new ActiveXObject(MSXML[n]);        
				break;
			}
			catch(e)
			{
				alert(e);
			}
		}
	}
}

createXMLHttpRequest();

function sendRequest()
{
	var url = "data";
	objXMLHttp.open("POST", url, true);
	objXMLHttp.onreadystatechange = show;//指定响应函数
	objXMLHttp.send(null);  // 发送请求
}

function show()
{
	if (objXMLHttp.readyState == 4 && (objXMLHttp.status == 200 || objXMLHttp.status == 304))
	{
		document.getElementById("show").innerHTML=objXMLHttp.responseText;
	}
}

document.getElementById("test").onclick=sendRequest;