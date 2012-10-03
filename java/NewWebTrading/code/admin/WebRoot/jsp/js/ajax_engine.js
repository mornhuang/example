/*
 * AJAX engine
*/

/*LoadXML(url, handler, user_data)
 url: the url to load
 handler: callback function which will be call where load complete or error occoured.
 user_data: user define data
*/

function LoadXML(url, method, asyn, isValid, handler, user_data)
{	
	var xmlObj=getRequestObj();
	if(xmlObj){
		xmlObj.onreadystatechange = function()
		{
			handler(xmlObj, user_data);
		}
		
		openRequest(xmlObj, url, method, asyn, isValid, handler, user_data);
		
		if(method.toUpperCase()=='GET'){
			xmlObj.setRequestHeader("If-Modified-Since","0");
		}else if(method.toUpperCase()=='POST'){
			xmlObj.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		}	
	
		xmlObj.send(null);
		return true;
	}else{
		return false;
	}

}	


function getRequestObj()
{		
	var xmlObj = false;
	if(window.XMLHttpRequest)	//Mozilla
	{
		xmlObj = new XMLHttpRequest();
		if(xmlObj.overrideMimeType)
		{
			xmlObj.overrideMimeType("text/html");
		}
	}
	else if(window.ActiveXObject)	//Internet Explorer
	{
		try
		{
			xmlObj = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch(e)
		{
			try
			{
				xmlObj = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e){}
		}
	}
	if(!xmlObj)
	{
		window.alert("can not create XMLHttpRequest instance.");
		return false;
	}
	return xmlObj;
}

function openRequest(xmlObj, url, method, asyn, isValid, handler, user_data){
	
	if(xmlObj){
		xmlObj.onreadystatechange = function()
		{
			handler(xmlObj, user_data);
		}

		if(isValid){				
			//alert("ajax_engine>>ajax_user:"+ajax_user+"	ajax_pwd:"+ajax_pwd);							
			//xmlObj.open(method,url,asyn,ajax_user,ajax_pwd);
			xmlObj.open(method,url,asyn);
			//xmlObj.open("GET",url,asyn);
		}else{
			xmlObj.open(method,url,asyn);
		}
	}
}	