var m_tdActive = null;

function setDays(objMonth,objYear,objDay){
	var iHowManyDays=HowManyDays(objMonth.value,objYear.value);
       	objDay.options.length = 0;
	for(var i = 1; i <= iHowManyDays; i++) {
		objDay.appendChild(document.createElement("option"));
		objDay.options[i-1].text=i;
		objDay.options[i-1].value=i;
	}		
}
/*******************************************************
function HowManyDays()
purpose
  To know how many days in a month
  strMonth:which month
  strYear:which year
********************************************************/
function HowManyDays(strMonth,strYear)
{  
	var strDate1=strYear+"/"+strMonth+"/"+"01"
	strMonth=parseInt(strMonth,10)+1
	var strDate2=strYear+"/"+strMonth+"/"+"01"
	var date1=new Date(strDate1)
	var date2=new Date(strDate2)
	var days=(date2 - date1)/24/60/60/1000
	return days;
}
function checkDate(fYear,fMonth,fDay,tYear,tMonth,tDay){
		
	var startDate=new Date(fYear,fMonth-1,fDay);
	var endDate=new Date(tYear,tMonth-1,tDay);
	if(startDate>endDate) return false;
	else return true;
}
