
<SCRIPT LANGUAGE="JavaScript">
function OpenBrowserWindow(theURL,WinName,Features) {
		window.open(theURL,WinName,Features);
}

function OpenHelperBrowser(relativeUrl){
	var manual_url_home="${applicationScope["MANUAL_URL_HOME"]}";
	if(manual_url_home==null || manual_url_home==''){
		manual_url_home=getDefaultManual();
	}
	
	var url=manual_url_home+"/"+relativeUrl;
	//alert(url);
	OpenBrowserWindow(url,"",'toolbar=0,location=0,directories=0,status=1,menubar=0,scrollbars=1,resizable=1,width=700,height=450,left=140,top=0');
}

function getDefaultManual(){
	<%
	String appPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	%>
	return '<%=appPath%><bean:message key="path.manual"/>';
}
</SCRIPT> 