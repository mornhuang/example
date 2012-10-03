function process_startStop(){ 
	var ipPort=Trim(document.getElementById('startStopIpPort').value);
	var start =document.getElementById('startServer');	
	var stop =document.getElementById('stopServer');	
	var path=Trim(document.getElementById('serverPath').value);
	var url='';	
	var user_data='';
	
	if(!check_IP(ipPort)){
		return false;
	}	
	
	if(path==''){
		alert("Context path is required");
		return false;
	}

	if(start.checked){		
		url="http://"+ipPort+"/manager/start";		
		user_data='start';
	}else if(stop.checked){
		url="http://"+ipPort+"/manager/stop";
		user_data='stop'; 
	}		
	alert(url);
	document.getElementById('operat').disabled=true;
	//document.getElementsById('operatCmd').disabled=true;
	
	startStopForm.action=url;	
	startStopForm.submit();		
	return true;	
}

function process_unDeploy(){
	var ipPort=Trim(document.getElementById('unDeployIpPort').value);
	var path=Trim(document.getElementById('unDeployPath').value);			
	
	if(!check_IP(ipPort)){		
		return false;
	}	
	
	if(path==null || path==''){
		alert("undeploy path is required");
		return false;
	}
	
	if(!confirm('Are you sure?')){
			return false;
	}
	document.getElementById('unDeploy').disabled=true;
		
	var url="http://"+ipPort+"/manager/undeploy";
	unDeployForm.action=url;	
	unDeployForm.submit();
	return true;
}

		
function submitUpload(){
	var ipPort=Trim(document.getElementById('uploadIpPort').value);
	var deployWar=Trim(document.getElementById('deployWar').value);
	
	if(!check_IP(ipPort)){		
		return false;
	}
	
	if(deployWar==null || deployWar==''){
		alert("deploy war file is required");
		return false;
	}
	document.getElementById('upload').disabled=true;
	uploadForm.action="http://"+ipPort+"/manager/html/upload";	
	uploadForm.submit();
	
	return true;
}		

function startOrStop_handler(request,user_data){
	try{							
		var doc=request.document;		
		if(doc.readyState=='complete'){
			if(doc.URL.indexOf("/manager/start")>-1 || doc.URL.indexOf("/manager/stop")>-1){				
				var msg = new String(doc.body.innerHTML);
				var index1=msg.toUpperCase().indexOf("<PRE>");
				var index2=msg.toUpperCase().indexOf("</PRE>")
				if(index1>-1 && index2>-1){
					msg=msg.substr(index1+5,index2-index1-5);
				}
				alert(msg.toString());
			}
			document.getElementById('operat').disabled=false;
			//document.getElementsById('operatCmd')[0].disabled=false;			
			//document.getElementsById('operatCmd')[1].disabled=false;
		}
	}catch(e){
		alert("request url error!");
		document.getElementById('operat').disabled=false;
		return false;
	}
}

function upload_handler(request,user_data){
	try{		
		var doc=request.document;	
		if(doc.readyState=='complete'){					
			if(doc.URL.indexOf("/manager/html/upload")>-1){			
				var filePath=new String(user_data);
				//alert("user_data:"+filePath.toString()+" length:"+filePath.length);
				var index1=filePath.lastIndexOf("\\");
				var index2=filePath.lastIndexOf(".war");
				//alert("index1:"+index1+" index2:"+index2);
				fileName=filePath.substring(index1+1,filePath.length);
				//alert("fileName:"+fileName);
				var contextPath=filePath.substring(index1+1,index2);
				//alert("contextPath:/"+contextPath);
				var successMsg="OK - Deployed application at context path /"+contextPath;
				//alert("successMsg:"+successMsg);
				var failMsg="FAIL - War file \""+fileName+"\" already exists on server";
				//alert("failMsg:"+failMsg);
				if(doc.body.innerHTML.indexOf( successMsg,0)>-1){
					alert(successMsg);				
				}else if(doc.body.innerHTML.indexOf(failMsg,0)>-1){
					alert(failMsg);
				}else{
					alert("deploy upload \""+filePath+ " fail");
				}
			}
			document.getElementById('upload').disabled=false;
		}
	}catch(e){
		alert("request url error!");
		document.getElementById('upload').disabled=false;
		return false;
	}
}
	

function unDeploy_handler(request,user_data){
	try{
		var doc=request.document;	
		if(doc.readyState=='complete'){		
			if(doc.URL.indexOf("/manager/undeploy")>-1){
				var msg = new String(doc.body.innerHTML);
				var index1=msg.toUpperCase().indexOf("<PRE>");
				var index2=msg.toUpperCase().indexOf("</PRE>")
				if(index1>-1 && index2>-1){
					msg=msg.substr(index1+5,index2-index1-5);
				}
				alert(msg.toString());
			}
			document.getElementById('unDeploy').disabled=false;
		}
	}catch(e){
		alert("request url error!");
		document.getElementById('unDeploy').disabled=false;
		return false;
	}
	}