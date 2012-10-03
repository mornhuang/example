function switchAllCheckBox(itemName,checkFlagId)
{	
  var checkFlagItem=document.getElementById(checkFlagId);	
  var item = document.getElementsByName(itemName);  
  if(checkFlagItem.checked==true)	{  	 
  	  for (var i=0; i<item.length; i++){
   		 item[i].checked =true;
  	  }
   }else{
  	  	  for (var i=0; i<item.length; i++){
   		 	item[i].checked =false;
  	     }
   }   
}

function selCheckBoxAll(itemName,checkFlagId)
{	
  var checkFlagItem=document.getElementById(checkFlagId);	
  var item = document.getElementsByName(itemName);  	 
  for (var i=0; i<item.length; i++){
   		item[i].checked =true;
  }
  checkFlagItem.checked=true;
  checkFlagItem.disabled=false;
}


function selCheckBox(itemName,checkFlagId)
{
    var selCount=0;	
    var item = document.getElementsByName(itemName);
  	for (var i=0; i<item.length; i++){
   		if(item[i].checked){   	 	
  			selCount++			
  		}
  	}
  	setCheckFlagStatus(checkFlagId,selCount,item.length);
}

function revselCheckBoxAll(itemName,checkFlagId)
{
  	var item = document.getElementsByName(itemName);
  	var selCount=0;
 	for (var i=0; i<item.length; i++){
    	if(item[i].checked){
   	 		item[i].checked=false;
  		}else{
  			item[i].checked=true  
  			selCount++			
  		}
	}
	setCheckFlagStatus(checkFlagId,selCount,item.length);
}

function setCheckFlagStatus(checkFlagId,selCount,itemCount){		
	var checkFlagItem=document.getElementById(checkFlagId);		
	if(selCount<=0){
		checkFlagItem.checked=false;
		checkFlagItem.disabled=false;
	}else if(selCount>=itemCount){
		checkFlagItem.checked=true;
		checkFlagItem.disabled=false;	
	}else{
		checkFlagItem.checked=true;
		checkFlagItem.disabled=true;		
	}	
}

function selCheckBoxUrlParam(itemName){
	var item = document.getElementsByName(itemName);
	var req= '';
 	for (var i=0; i<item.length; i++){ 		 		
    	if(item[i].checked){
    		if(req==''){
   	 			req=itemName+'='+item[i].value;
    		}else{
    			req=req+'&'+itemName+'='+item[i].value;
    		}
  		}  		
	}	
	return req;
}

	function deleteSel(formName,itemName){
		var check=false;
		var item=document.getElementsByName(itemName);
		for (var i=0; i<item.length; i++){ 		 		
    		if(item[i].checked){
    			check=true;
    			break;
  			}  		
		}		
		if(!check){
			return false;
		}	
		
		if(!confirm('Are you sure?')){
			return false;
		}
		formName.submit();
		return true;
	}
	
	function deleteAll(formName){					
		if(!confirm('Are you sure?')){
			return false;
		}
		formName.action=formName.action+'?'+'deleteAll=true'
		formName.submit();
		return true;
	}	
	
	function setFocus(itemId){		
		var item=document.getElementById(itemId);
		if(!item.disabled){
			item.focus();
		}
	}		
	
	function formatDate2FullStr(dateV){
		//format:yyyy-mm-dd HH:mm:ss
		var year=dateV.getFullYear();
		var month=dateV.getMonth()+1;
		var day=dateV.getDate();
		
		var hour=dateV.getHours();
		var minute=dateV.getMinutes(); 
		var second=dateV.getSeconds(); 
	
		if(month<10){
			month="0"+month;
		}
	
		if(day<10){
			day="0"+day;
		}
	
		if(hour<10){
			hour="0"+hour;
		}
	
		if(minute<10){
			minute="0"+minute;
		}

		if(second<10){
			second="0"+second;
		}
	
		return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	}
	
	function formatFullDateStr(strV){
		//format:yyyy-mm-dd HH:mm:ss
		var yearIndex=strV.indexOf("-");
		var year=strV.substring(0,yearIndex);
		
		var monthIndex=strV.indexOf("-",yearIndex+1);
		var month=strV.substring(yearIndex+1,monthIndex);
		
		var dayIndex=strV.indexOf(" ",monthIndex+1);
		var day=strV.substring(monthIndex+1,dayIndex);	
		
		var hourIndex=strV.indexOf(":");
		var hour=strV.substring(dayIndex+1,hourIndex);
		
		var minuteIndex=strV.indexOf(":",hourIndex+1);
		var minute=strV.substring(hourIndex+1,minuteIndex);

		var second=strV.substring(minuteIndex+1,strV.length);	

		if(month.length<2){
			month="0"+month;
		}

		if(day.length<2){
			day="0"+day;
		}

		if(hour.length<2){
			hour="0"+hour;
		}

		if(minute.length<2){
			minute="0"+minute;
		}

		if(second.length<2){
			second="0"+second;
		}
	
		return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;								
	}	

	function getListCount(objInput){
		var objCount = 0;
		if (objInput == null) return 0
		if (typeof(objInput.length)=="undefined")
		{
			return 1 ;
		}
		else {
			return objInput.length;
		}
	}
	
	function showTbs(tbList,isShow){
		var len = getListCount(tbList);
		var strStyle = isShow? "":"none";

		if (typeof(tbList.style)!="undefined"){
			tbList.style.display = strStyle;
			return;
		}

		for (var i=0; i<len; i++){
			tbList[i].style.display = strStyle;
		}
	}
	
	//输入非负整数
	function feiFuZhengShu(s){
		var patrn=/^\\d+$/;
		  if (!patrn.exec(s)) 
		  {
			  alert("请输入非负整数！");
			  return false ;  
		  }			 
		   return true ;	
	}
	