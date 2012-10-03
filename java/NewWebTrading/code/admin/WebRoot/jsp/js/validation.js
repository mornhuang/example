//????????????
function check_instrcode(ed_ic)
{
	var regexp = /^\d{1,5}$/
	if(!regexp.test(ed_ic.value))
	{
		return false;
	}
	return true;
}
//??????
function check_price(ed_pr)
{
	var regexp = /^\s*\d{1,9}\.?\d{1,3}\s*$/
	if(!regexp.test(ed_pr.value))
	{
		return false;
	}
	return true;
}

function check_amount(ed_pr)
{
	var regexp = /^\s*\d{1,9}\.?\d{1,2}\s*$/
	if(!regexp.test(ed_pr.value))
	{
		return false;
	}
	return true;
}

//??????
function check_quantity(ed_qty)
{
	var regexp = /^\d{1,9}$/
	if(!regexp.test(ed_qty.value))
	{
		return false;
	}
	return true;
}
//?????????
function check_loginid(ed_id)
{
	var regexp = /^\s*[A-Za-z0-9]{6,20}\s*$/
	if(!regexp.test(ed_id.value))
	{
		alert("")
		return false;
	}
	return true;
}
//??????
function check_password(ed_pwd)
{
	var regexp = /^[A-Za-z0-9]{6,8}$/
	if(!regexp.test(ed_pwd.value))
	{
		return false;
	}
	return true;
}
//email
function check_email(ed_mail)
{
	var regexp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
	if(!regexp.test(ed_mail.value))
	{
		return false;
	}
	return true;
}

//check hkid

/*var SPECIALCHAR = "*~!@#$%^&*()_+-=[]{}\\|;:'\",./<>?";
function check_hkid(hkid) {
	orighkid = hkid.value;
    if (isEmpty(orighkid)) return false;
    if (orighkid.length < 6) return false;

    for (i = 0; i < orighkid.length; i++) {
        if (i == 0 && !isLetter(orighkid.charAt(i))) {
            return false;
        }
        else if (i == 1 && !isLetterOrDigit(orighkid.charAt(i))) {
            return false;
        }
        else if ((i > 1 && i < 7) && !isDigit(orighkid.charAt(i))) {
            return false;
        }
        else if (!SPECIALCHAR.indexOf(orighkid.charAt(i))) {
            return false;
        }
    }
    return true;
}

function isEmpty(s){
   return ((s == null) || (s.length == 0))
}

function isLetter (c){
   return ( ((c >= "a") && (c <= "z")) || ((c >= "A") && (c <= "Z")) )
}

function isDigit (c){
   return ((c >= "0") && (c <= "9"))
}

function isLetterOrDigit (c){
   return (isLetter(c) || isDigit(c))
}
*/

function check_hkid(hkid) {
	var regexp = /^([a-zA-Z]{1}[0-9]{5}|[a-zA-Z]{2}[0-9]{4})([0-9]{1}[a-zA-Z0-9]{0,})?$/
	if(!regexp.test(hkid.value))
	{
		return false;
	}
	return true;
}

function check_NotEmpty(ed_str){
    if(ed_str.value.Trim()==null || ed_str.value.Trim()==""){      	   
  	   return false;
    }	   
       return true;  
}
    
// whether the amount statisfied the small rule     
function check_amount_small(ed_am,val)	          
{
    var regexp = /^\s*\d{1,9}\.?\d{0,2}\s*$/
    var value1 = parseFloat(ed_am.value);
    var value2 = parseFloat(val);   
    
    //add isNaN method to check whether the return value of parseFloat or parseInt is not a numeric
    var result = isNaN(value1)&& isNaN(value2);
    if(!regexp.test(ed_am.value)||(value1<value2)||(result == true)){
   	    //ed_am.focus();   
       return false;   	    
    }
       return true;   
 } 
 
//whether the amount satisfied the big rule   
 function check_amount_big(ed_am,val)                    
 {
 	 var regexp = /^\s*\d{1,9}\.?\d{0,2}\s*$/
     var value1 = parseFloat(ed_am.value);
     var value2 = parseFloat(val); 
     var result = isNaN(value1)&& isNaN(value2);
    
     if(!regexp.test(ed_am.value)||(value1>value2)||(result == true)) {
    	 //ed_am.focus();
    	return false;
     }  
        return true;
 }
 
//additional method to judge whether the value is Captial or not  
function isCapital(ed_str)                             
{
	var regexp= /^[A-Z]+$/ ;
	//for when refered it has been the value so without ed_str.value present
	if(!regexp.test(ed_str)) {
	   return false;
	}
	return true;
}

//format accountId into standard present 
 function format_accountId(acId){
	  
	  if(acId.length==13){
	     str = acId.Trim().substring(3,5);
	  }
	  
	  if("00".equals(str)){   
	     var oriStr = acId.substring(3,10);
	     var midStr = oriStr.substring(2);
	     formatStr = acId.replace(oriStr,midStr);
	  }
	     return formatStr;			
}

//format the number into international presnet as 12340 to 12,340  
function  format_number_position(number)              
{ 
    number= number+""; 
    //number = parseFloat(number);
    var regexp=/(-?\d+)(\d{3})/ ;
    
    while(regexp.test(number)){ 
       number=number.replace(regexp,"$1,$2") 
    }     
    return  number; 

}

//format the number according to the dotNumber as given 1123.691 --- 1,123.70
function format_number_value(number,dot_nb)   
{
	var formatValue, nTen;
	number =""+number+"";
	numLen = number.length;
	dotPos = number.indexOf(".",0)
	  
	if(dotPos == -1) {
	   formatValue = number + ".";
	   for(var i=0 ; i<dot_nb; i++) {
	  		formatValue = formatValue + "0";
	   }
	   return format_number_position(formatValue);
	} else {
	  	if((numLen - dotPos - 1) >=dot_nb ){
	  	   nb = dotPos + dot_nb + 1;
	  	   nTen = 1;
	  	   for(var j=0 ; j<dot_nb ; j++) {
	  	  	 	 nTen = nTen *10;
	  	   }
	  	   formatValue = Math.round(parseFloat(number)*nTen)/nTen;
	  	   return format_number_position(formatValue); 
	  	}else {
	  	   formatValue = number;
	  	   for (var i=0 ; i<(dot_nb - numLen + dotPos +1) ; i++) {
	  	  	  formatValue = formatValue + "0";
	  	   }
	  	   return format_number_position(formatValue);
	  	 }
	}
}