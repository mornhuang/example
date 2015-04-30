/*
javascript for script-centric info display using eval and xmlhttp
*/

var offset=8;
function showPopup(name,description){
  var win=new ScriptIframePopup(name,description,offset,offset,320,320);
  offset+=32;
}

function ScriptIframePopup(name,description,x,y,w,h){

  var bod=document.createElement("div");
  document.body.appendChild(bod);

  this.contentDiv=document.createElement("div");
  this.contentDiv.className="winContents";
  this.contentDiv.innerHTML=description;
  bod.appendChild(this.contentDiv);

  this.win=new windows.Window(bod,name,x,y,w,h);
}

function showInfo(event){
  var planet=this.id;
  var scriptUrl="script_"+planet+".js";
  var loader=new net.ContentLoader(scriptUrl,evalScript);
}

/* callback for net.ContentHandler */
function evalScript(){
  var script=this.req.responseText;
  eval(script);
}
