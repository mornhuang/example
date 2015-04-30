/*
javascript for content-centric info display
*/

function ContentPopup(url,winEl,displayStr,x,y,w,h){

  var bod=document.createElement("div");
  document.body.appendChild(bod);

  this.iframe=document.createElement("iframe");
  this.iframe.className="winContents";
  this.iframe.src=url;
  bod.appendChild(this.iframe);

  this.win=new windows.Window(bod,displayStr,x,y,w,h);
}

var offset=8;
function showInfo(event){
  var planet=this.id;
  var infoWin=new ContentPopup("info_"+planet+".html",planet+"Popup",planet,offset,offset,320,320);
  offset+=32;
}

