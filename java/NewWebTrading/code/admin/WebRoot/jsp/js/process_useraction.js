function process_toExcelUserAction(){	
	document.queryUserActionForm.action="../exportExcelUserActionDetail.do";
	document.queryUserActionForm.submit();	
}

function toExcelUserAction_handler(request,user_data){
	try{
		if(request.readyState==4){
			if(request.status==200)	{
			}
		}
	}catch(e){
		alert(e.toString());
	}
	
}