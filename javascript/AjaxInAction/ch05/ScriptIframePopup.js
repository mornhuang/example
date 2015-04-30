/*
javascript for script-centric info display using a hidden iframe
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
  var scriptUrl="script_"+planet+".html";
  var dataframe=document.getElementById('dataframe');
  if (!dataframe){
    dataframe=document.createElement("iframe");
    dataframe.className='dataframe';
    dataframe.id='dataframe';
    dataframe.src=scriptUrl;
    document.body.appendChild(dataframe);
  }else{
    dataframe.src=scriptUrl;
  }
}

