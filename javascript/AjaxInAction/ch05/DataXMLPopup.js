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
  var scriptUrl=planet+".xml";
  var loader=new net.ContentLoader(scriptUrl,parseXML);
}

/* callback for net.ContentHandler */
function parseXML(){
  var name="";
  var descrip="";
  var xmlDoc=this.req.responseXML;
  var elDocRoot=xmlDoc.getElementsByTagName("planet")[0];
  if (elDocRoot){
    attrs=elDocRoot.attributes;
    name=attrs.getNamedItem("name").value;
    var ptype=attrs.getNamedItem("type").value;
    if (ptype){
      descrip+="<h2>"+ptype+"</h2>";
    }

    descrip+="<ul>";
    for(i=0;i<elDocRoot.childNodes.length;i++){
      elChild=elDocRoot.childNodes[i];
      if (elChild.nodeType==1 && elChild.tagName=="info"){
        descrip+="<li>"+elChild.firstChild.nodeValue+"</li>\n";
      }
    }
    descrip+="</ul>";
  }else{
    alert("no document");
  }
  var win=top.showPopup(name,descrip);
}
