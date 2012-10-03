


<SCRIPT LANGUAGE="JavaScript">
<!--


var date_start,date_end,g_object
var today = new Date();
var separator="-";
var inover=false;


function change_date(temp,mode)
{
  var t_month,t_year
    if (mode){
        if(mode==1)
        t_month=parseInt(cele_date_month.value,10)+parseInt(temp,10);
        else
        t_month=parseInt(temp)
        if (t_month<cele_date_month.options(0).text) {
            cele_date_month.value=cele_date_month.options(cele_date_month.length-1).text;
            change_date(parseInt(cele_date_year.value,10)-1,0);
            }
        else{
            if (t_month>cele_date_month.options(cele_date_month.length-1).text){
                cele_date_month.value=cele_date_month.options(0).text;
                change_date(parseInt(cele_date_year.value,10)+1,0);
                }            
            else
                {cele_date_month.value=t_month;
                 set_cele_date(cele_date_year.value,cele_date_month.value);                
                }
        }
    }  
    else{
        t_year=parseInt(temp,10);
        
        if (t_year<cele_date_year.options(0).text) {
            cele_date_year.value=cele_date_year.options(0).text;
            set_cele_date(cele_date_year.value,1);                
            }
        else{
            if (parseInt(t_year,10)>parseInt(cele_date_year.options(cele_date_year.length-1).text,10)){
                cele_date_year.value=cele_date_year.options(cele_date_year.length-1).text;
                set_cele_date(cele_date_year.value,12);                
                }            
            else
                {cele_date_year.value=t_year;
                 set_cele_date(cele_date_year.value,cele_date_month.value);                
                }
        }
    }
    /*********2002-02-01 MODIFY BY WING **************/
    window.cele_date.focus();
    /****************MODIFY END***********************/
}


function init(d_start,d_end)
{
     var temp_str;
     var i=0
     var j=0
     date_start=new Date(2000,7,1)
     date_end=new Date(2004,8,1)
     
    
     /*************************2002-02-01 MODIFY BY WING *********************************************************************************/
     document.writeln("<div name=\"cele_date\"  id=\"cele_date\"  style=\"display:none\"    style=\"LEFT: 69px; POSITION: absolute; TOP: 159px;Z-INDEX:99\" onClick=\"event.cancelBubble=true;\" onBlur=\"hilayer()\" onMouseout=\"lostlayerfocus()\">? </div>");
     /*******************************************MODIFY END*******************************************************************************/
     window.cele_date.innerHTML="";
     temp_str="<table class=none border=\"1\" bgcolor=\"#A5B7C5\" bordercolor=\"white\"><tr><td colspan=7 onmouseover=\"overcolor(this)\">";
     temp_str+="<input type=\"Button\" value=\"<<\" onclick=\"change_date(-1,1)\" onmouseover=\"getlayerfocus()\" style=\"color: #FFFFFF; background-color: #5d7790; cursor: hand\">?";//?????

     /**************************2002-02-01 MODIFY BY WING ********************************************************************************/
     /*temp_str+="<input type=\"Button\" value=\"<<\" onclick=\"change_date(-1,1)\" >?";//?????                                */
     /************************************************************************************************************************************/
     
     temp_str+=""
     temp_str+="<select name=\"cele_date_year\" id=\"cele_date_year\" language=\"javascript\" onchange=\"change_date(this.value,0)\" onmouseover=\"getlayerfocus()\" onblur=\"getlayerfocus()\" style=\"font-size: 9pt; border: 1px #666666 outset; background-color: #F4F8FB\">"

     /**************************2002-02-01 MODIFY BY WING ********************************************************************************/
     /*temp_str+="<select name=\"cele_date_year\" id=\"cele_date_year\" language=\"javascript\" onchange=\"change_date(this.value,0)\">" */
     /************************************************************************************************************************************/

     for (i=2006;i<=2012;i++)
     {
     temp_str+="<OPTION value=\""+i.toString()+"\">"+i.toString()+"</OPTION>";
     }
     temp_str+="</select>Month:";
     temp_str+=""//?
     temp_str+="<select name=\"cele_date_month\" id=\"cele_date_month\" language=\"javascript\" onchange=\"change_date(this.value,2)\" onmouseover=\"getlayerfocus()\" onblur=\"getlayerfocus()\" style=\"font-size: 9pt; border: 1px #666666 outset; background-color: #F4F8FB\">"

     /**************************2002-02-01 MODIFY BY WING **********************************************************************************/
     /*temp_str+="<select name=\"cele_date_month\" id=\"cele_date_month\" language=\"javascript\" onchange=\"change_date(this.value,2)\">" */
     /**************************************************************************************************************************************/

     for (i=1;i<=12;i++)
     {
     temp_str+="<OPTION value=\""+i.toString()+"\">"+i.toString()+"</OPTION>";
     }
     temp_str+="</select>";
     temp_str+=""//???
     temp_str+="<input type=\"Button\" value=\">>\" onclick=\"change_date(1,1)\" onmouseover=\"getlayerfocus()\"  style=\"color: #FFFFFF; background-color: #5d7790; cursor: hand\">";

     /**************************2002-02-01 MODIFY BY WING ********************************************************************************/
     /*temp_str+="<input type=\"Button\" value=\">>\" onclick=\"change_date(1,1)\">";                                                    */
     /************************************************************************************************************************************/

     temp_str+="</td></tr><tr><td onmouseover=\"overcolor(this)\">"
     temp_str+="<font color=red>Su</font></td><td>";temp_str+="Mo</td><td>"; temp_str+="Tu</td><td>"; temp_str+="We</td><td>"
     temp_str+="Th</td><td>";temp_str+="Fr</td><td>"; temp_str+="Sa</td></tr>";
     for (i=1 ;i<=6 ;i++)
     {
     temp_str+="<tr>";
        for(j=1;j<=7;j++){
            temp_str+="<td name=\"c"+i+"_"+j+"\"id=\"c"+i+"_"+j+"\" style=\"CURSOR: hand\" style=\"COLOR:#000000\" language=\"javascript\" onmouseover=\"overcolor(this)\" onmouseout=\"outcolor(this)\" onclick=\"td_click(this)\">?</td>"
            }
     temp_str+="</tr>"        
     }
     temp_str+="</td></tr></table>";
     window.cele_date.innerHTML=temp_str;
}
function set_cele_date(year,month)
{
   var i,j,p,k
   var nd=new Date(year,month-1,1);
   event.cancelBubble=true;
   cele_date_year.value=year;
   cele_date_month.value=month;   
   k=nd.getDay()-1
   var temp;
   for (i=1;i<=6;i++)
      for(j=1;j<=7;j++)
      {
      eval("c"+i+"_"+j+".innerHTML=\"\"");
      eval("c"+i+"_"+j+".bgColor=\"#A5B7C5\"");
      eval("c"+i+"_"+j+".style.cursor=\"hand\"");
      }
   while(month-1==nd.getMonth())
    { j=(nd.getDay() +1);
      p=parseInt((nd.getDate()+k) / 7)+1;
      eval("c"+p+"_"+j+".innerHTML="+"\""+nd.getDate()+"\"");
      if ((nd.getDate()==today.getDate())&&(cele_date_month.value==today.getMonth()+1)&&(cele_date_year.value==today.getYear())){
      eval("c"+p+"_"+j+".bgColor=\"#CDD995\"");
      }
      if (nd>date_end || nd<date_start)
      {
      eval("c"+p+"_"+j+".bgColor=\"#FFB9B9\"");
      eval("c"+p+"_"+j+".style.cursor=\"text\"");
      }
      nd=new Date(nd.valueOf() + 86400000)
    }
}


function show_cele_date(eP,d_start,d_end,t_object)
{
window.cele_date.style.display="";
window.cele_date.style.zIndex=99
var s,cur_d
var eT = eP.offsetTop;  
var eH = eP.offsetHeight+eT;  
var dH = window.cele_date.style.pixelHeight;  
var sT = document.body.scrollTop; 
var sL = document.body.scrollLeft; 
event.cancelBubble=true;
window.cele_date.style.posLeft = event.clientX-event.offsetX+sL-5;  
window.cele_date.style.posTop = event.clientY-event.offsetY+eH+sT-5;
if (window.cele_date.style.posLeft+window.cele_date.clientWidth>document.body.clientWidth) window.cele_date.style.posLeft+=eP.offsetWidth-window.cele_date.clientWidth;
//if (window.cele_date.style.posTop+window.cele_date.clientHeight>document.body.clientHeight) window.cele_date.style.posTop-=(eP.offsetHeight+window.cele_date.clientHeight+5);
if (d_start!=""){
    if (d_start=="today"){
        date_start=new Date(today.getYear(),today.getMonth(),today.getDate());
    }else{
        s=d_start.split(separator);
        date_start=new Date(s[0],s[1]-1,s[2]);
    }
}else{
    date_start=new Date(1900,1,1);
}

if (d_end!=""){
    s=d_end.split(separator);
    date_end=new Date(s[0],s[1]-1,s[2]);
}else{
    date_end=new Date(3000,1,1);
}

g_object=t_object

cur_d=new Date()
set_cele_date(cur_d.getYear(),cur_d.getMonth()+1);
window.cele_date.style.display="block";
/***************2002-02-01 MODIFY BY WING ***********/
window.cele_date.focus();
/****************MODIFY END**************************/
}
function td_click(t_object)
{
var t_d
if (parseInt(t_object.innerHTML,10)>=1 && parseInt(t_object.innerHTML,10)<=31 ) 
{ t_d=new Date(cele_date_year.value,cele_date_month.value-1,t_object.innerHTML)
if (t_d<=date_end && t_d>=date_start)
{
var year = cele_date_year.value;
var month = cele_date_month.value;
var day = t_object.innerHTML;
if (parseInt(month)<10) month = "0" + month;
if (parseInt(day)<10) day = "0" + day;

g_object.value=year+separator+month+separator+day;
window.cele_date.style.display="none";};
}

}
function h_cele_date()
{
window.cele_date.style.display="none";
}

function overcolor(obj)
{
  if (obj.style.cursor=="hand") obj.style.color = "#FFFFFF";
  /*********** 2002-02-01 MODIFY BY WING *****/
  inover=true;
  window.cele_date.focus();
  /************* MODIFY END ******************/
}

function outcolor(obj)
{
  obj.style.color = "#000000";
  /*********** 2002-02-01 MODIFY BY WING *****/
  inover=false;
  /************* MODIFY END ******************/
}


function getNow(o){
    var Stamp=new Date();
    var year = Stamp.getYear();
    var month = Stamp.getMonth()+1;
    var day = Stamp.getDate();
    if(month<10){
    month="0"+month;
    }
    if(day<10){
    day="0"+day;
    }
    o.value=year+separator+month+separator+day;
}
/*********** 2002-02-01 MODIFY BY WING **ADD THREE FUNCTION TO CONTROL THE DIV FOCUS***/
function hilayer()
{
  if (inover==false)
    {
       var lay=document.all.cele_date;
       lay.style.display="none";
    }
}
function getlayerfocus()
{
  inover=true;
}
function lostlayerfocus()
{
  inover=false;
}
/***************************MODIFY END************************************************/
//-->
</SCRIPT>
<script language="javascript">
   init();
</script>

<style type="text/css">
<!--
.style1 {
	color: #0000FF;
	font-weight: bold;
	font-size: 24px;
}
-->
</style>