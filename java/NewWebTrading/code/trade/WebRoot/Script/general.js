function disableIt(ele) {
    ele.disabled = true;
}
function OpenBrowserWindow(theURL,WinName,Features) {
	var TfNewBrowserWindow=window.open(theURL,WinName,Features);
	
	if (TfNewBrowserWindow) {
		TfNewBrowserWindow.focus();
	}
} 
