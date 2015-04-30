/*
javascript for data-centric pattern using XML
*/

var offset=8;
function showPopup(name,description){
  var win=new DataPopup(name,description,offset,offset,320,320);
  offset+=32;
}

function DataPopup(name,description,x,y,w,h){

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
  var scriptUrl=planet+".json";
  var loader=new net.ContentLoader(scriptUrl,parseJSON);
}

/* callback for net.ContentHandler */
function parseJSON(){
  var name="";
  var descrip="";
  var jsonTxt=this.req.responseText;
  var jsonObj=eval('('+jsonTxt+')');
  name=jsonObj.planet.name
  var ptype=jsonObj.planet.type;
  if (ptype){
    descrip+="<h2>"+ptype+"</h2>";
  }

  var infos=jsonObj.planet.info;
  descrip+="<ul>";
  for(i in infos){
    descrip+="<li>"+infos[i]+"</li>\n";
  }
  descrip+="</ul>";

  var win=top.showPopup(name,descrip);
}
