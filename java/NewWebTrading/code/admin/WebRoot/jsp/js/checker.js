function check_IP(ipPort){
	if(ipPort==null || Trim(ipPort)==''){
		alert("Server IP And Port is required");
		return false;
	}else{ 
		var pattern=/^((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[0-9]{2}|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[0-9]{2}|[0-9])(:[0-9]+){0,1}$/
		if(!pattern.test(ipPort)){
			alert("ip&port is invalid!");
			return false;
		}
	}
	return true;
}

function check_changePwd(oldPwd, newPwd, confirmPwd){
	if(oldPwd==null || Trim(oldPwd)==''){
		alert("old password is required!");
		return false;
	}else if(newPwd==null || Trim(newPwd)==''){
		alert("new password is required!");
		return false;
	}else if(confirmPwd!=newPwd){
		alert("new password and confirm password is not equal!");
		return false;
	}
		
	var newPwdStr=new String(newPwd);		
	if(newPwdStr.length<6 || newPwdStr.length>20){
		alert("password's length is invalid! please enter 6 to 20 characters");
		return false;
	}
	return true;
}

function check_DateTime(str){
	//alert("test date time");
	str=Trim(str);	
	if(str==null || str==''){
		alert('the Datetime is required!');
		return false;
	}
	//validate datetmime eg:(2006-06-12 15:08:10)
	//???2???regexp??		
	var date1=/^(([0-9]{2}([02468][048])|([13579][26]))(-)(2|02)(-)(([0]{0,1}[1-9])|([1-2][0-9])))$/;
	//????2???regexp??
	var date2=/^(([0-9]{2}([02468][1235679])|([13579][01345789]))(-)(2|02)(-)(([0]{0,1}[1-9])|([1][0-9])|([2][0-8])))$/;
	//?2??????regexp??
	var date3=/^(([0-9]{4})(-)(([0]{0,1}(1|3|5|7|8))|(10|12))(-)(([0]{0,1}[1-9])|([1-2][0-9])|30|31))$/;
	//?2??????regexp??
	var date4=/^(([0-9]{4})(-)(([0]{0,1}(4|6|9))|11)(-)(([0]{0,1}[1-9])|([1-2][0-9])|30))$/;		
	//??regexp??
	
	var time=/^((([0-1]{0,1}[0-9])|([2][0-3])):([0-5]{0,1}[0-9]):([0-5]{0,1}[0-9]))$/;
	//??????regexp??
	//var pattern=/^((([0-9]{2}([02468][048])|([13579][26]))(-)(2|02)(-)(([0]{0,1}[1-9])|([1-2][0-9])))|(([0-9]{2}([02468][1235679])|([13579][01345789]))(-)(2|02)(-)(([0]{0,1}[1-9])|([1][0-9])|([2][0-8])))|(([0-9]{4})(-)(([0]{0,1}(1|3|5|7|8))|(10|12))(-)(([0]{0,1}[1-9])|([1-2][0-9])|30|31))|(([0-9]{4})(-)(([0]{0,1}(4|6|9))|11)(-)(([0]{0,1}[1-9])|([1-2][0-9])|30))){1}( ((([0-1]{0,1}[0-9])|([2][0-3])):([0-5]{0,1}[0-9]):([0-5]{0,1}[0-9]))){0,1}$/;
	var pattern=/^((([0-9]{2}([02468][048])|([13579][26]))(-)(2|02)(-)(([0]{0,1}[1-9])|([1-2][0-9])))|(([0-9]{2}([02468][1235679])|([13579][01345789]))(-)(2|02)(-)(([0]{0,1}[1-9])|([1][0-9])|([2][0-8])))|(([0-9]{4})(-)(([0]{0,1}(1|3|5|7|8))|(10|12))(-)(([0]{0,1}[1-9])|([1-2][0-9])|30|31))|(([0-9]{4})(-)(([0]{0,1}(4|6|9))|11)(-)(([0]{0,1}[1-9])|([1-2][0-9])|30))){1}( ((([0-1]{0,1}[0-9])|([2][0-3])):([0-5]{0,1}[0-9]):([0-5]{0,1}[0-9]))){1}$/;
	
	if(!pattern.test(str)){
  	 	return false;  	   
  }else{
	  	return true;
  }
}

function check_loginid(ed_id)
{
	var regexp = /^\s*[A-Za-z0-9]{6,15}\s*$/
	if(!regexp.test(ed_id.value))
	{
		//alert("invalid loginId ,Please enter character or number,length is 6 to 20! ")
		return false;
	}
	return true;
}

function check_password(ed_pwd)
{
	var regexp = /^[A-Za-z0-9]{6,8}$/
	if(!regexp.test(ed_pwd.value))
	{
		//alert("invalid password,Please enter character or number,length is 6 to 8! ")
		return false;
	}
	return true;
}

function check_email(ed_email){
	var regexp=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/ 
	if(!regexp.test(ed_email.value))
	{
		return false;
	}
	return true;
}

function check_length(ed_id,len,fieldname,err_div)
{	
	if(ed_id.value.length>len){
		if(err_div==null){
			alert(fieldname +" length is too long, length max is "+len);		
		}else{
			err_div.innerHTML=fieldname +" length is too long, length max is "+len
		}
		return false;
	}
	return true;
}

function check_underlineOrCharOrNumOrDot(ed_id)
{
	var regexp = /^[A-Za-z0-9_\.]*$/
	if(!regexp.test(ed_id.value))
	{
		return false;
	}
	return true;
}

function check_isNumeric(ed_id,sign,decimal){
	//alert(ed_id+","+sign+","+decimal);
	var regexp = /^[+|-]{0,1}(\d)+((\.){0,1}(\d)+){0,1}$/;
	
	switch(sign){
		case "+":
			if(decimal==false){
				regexp=/^(\d)+$/;break;
			}else{
				regexp=/^(\d)+((\.){1}(\d)+){0,1}$/;break;
			}
		case "-":	
			if(decimal==false){
				regexp=/^-(\d)+$/;break;
			}else{
				regexp=/^-(\d)+((\.){1}(\d)+){0,1}$/;break;
			}
		default:{
			if(decimal==false){
				regexp=/^[+|-]{0,1}(\d)+$/;break;
			}else{
				regexp=/^[+|-]{0,1}(\d)+((\.){1}(\d)+){0,1}$/;break;
			}
		}	
	}	
	
	if(!regexp.test(ed_id))
	{
		return false;
	}
	return true;
}





