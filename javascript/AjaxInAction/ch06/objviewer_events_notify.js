/**
library for automatically displaying a javascript object in a tabular form
for viewing and/or editing

Dave Crane
*/

//namespace
var objviewer=new Object();

/**
constructor function for ObjectViewer
obj - object being viewed
div - DOM element to place the viewer in
isInline - whether complex members are rendered as a popout (false) or inline (true), defaults to popout if this arg omitted
addNew - function called to create an object to add to this objectViewer dynamically,
typically for adding to arrays. Defaults to null

*/
objviewer.ObjectViewer=function(obj,div,isInline,addNew){
  styling.removeAllChildren(div);
  this.object=obj;
  this.spec=objviewer.getSpec(obj);
  this.mainDiv=div;
  this.mainDiv.viewer=this;
  this.isInline=isInline;
  this.addNew=addNew;
  var table=document.createElement("table");
  this.tbod=document.createElement("tbody");
  table.appendChild(this.tbod);
  this.fields=new Array();
  this.children=new Array();

  for (var i=0;i<this.spec.length;i++){
    this.fields[i]=new objviewer.PropertyViewer(this,this.spec[i]);
  }

  if (addNew){
    var addButton=document.createElement("div");
    addButton.className='objViewButton right';
    addButton.viewer=this;
    addButton.addNew=addNew;
    addButton.onclick=objviewer.ObjectViewer.addClick;
    var addTxt=document.createTextNode("+");
    addButton.appendChild(addTxt);
    /*
    var tr=document.createElement("tr");
    var td=document.createElement("td");
    td.appendChild(addButton);
    tr.appendChild(td);
    this.tbod.appendChild(tr);
    this.addButton=addButton;
    */
    this.mainDiv.appendChild(addButton);
  }

  this.mainDiv.appendChild(table);
}

objviewer.ObjectViewer.addClick=function(e){
  var viewer=this.viewer;
  var addNew=this.addNew;
  if (viewer && addNew){
    viewer.appendField(addNew);
  }
}

objviewer.ObjectViewer.prototype.appendField=function(addNew){
  var newObj=addNew.call(this.object);
  var spec=this.spec[this.spec.length-1];
  this.fields.append(new objviewer.PropertyViewer(this,spec,true));
}

objviewer.ObjectViewer.prototype.hide=function(){
  this.mainDiv.style.display='none';
  if (this.children){
    for (var i=0;i<this.children.length;i++){
      this.children[i].hide();
    }
  }
}


objviewer.TYPE_SIMPLE="simple";
objviewer.TYPE_ARRAY="array";
objviewer.TYPE_FUNCTION="function";
objviewer.TYPE_IMAGE="image";
objviewer.TYPE_IMAGE_URL="image url";
objviewer.TYPE_OBJECT="object";
objviewer.TYPE_INLINE_OBJECT="inline object";


objviewer.getSpec=function (obj){
  return (obj.objViewSpec) ? obj.objViewSpec : objviewer.autoSpec(obj);
}

/** define display properties for an object  */
objviewer.autoSpec=function(obj){
  var members=new Array();
  for (name in obj){
    var value=obj[name];
    var type=objviewer.autoType(value);
    var spec={name:name,type:type};
    members.append(spec);
  }
  //special case for numerically-indexed arrays too
  if (this.obj && this.obj.length>0){
    for(var i=0;i<this.object.length;i++){
      var name="array ["+i+"]";
      var value=this.obj[i];
      var type=objviewer.ObjectViewer.autoType(value);
      var spec={name:name,type:type};
      members.append(spec);
    }
  }
  return members;
}

objviewer.autoType=function(value){
  var type=objviewer.TYPE_SIMPLE;
  if ((value instanceof Array)){
    type=objviewer.TYPE_ARRAY;
  }else if (value instanceof Function){
    type=objviewer.TYPE_FUNCTION;
  }else if (value instanceof Image){
    type=objviewer.TYPE_IMAGE;
  }else if (value instanceof Object){
    type=objviewer.TYPE_OBJECT;
  }
  return type;
}



objviewer.ObjectViewer.prototype.notifyChange=function(propViewer){
  if (this.onchangeRouter){
    this.onchangeRouter.notify(propViewer);
  }
  if (this.parentObjViewer){
    this.parentObjViewer.notifyChange(propViewer);
  }
}

objviewer.ObjectViewer.prototype.addChangeListener=function(lsnr){
  if (!this.onchangeRouter){
    this.onchangeRouter=new jsEvent.EventRouter(this,"onchange");
  }
  this.onchangeRouter.addListener(lsnr);
}

objviewer.ObjectViewer.prototype.removeChangeListener=function(lsnr){
  if (this.onchangeRouter){
    this.onchangeRouter.removeListener(lsnr);
  }
}



objviewer.PropertyViewer=function(viewer,memberSpec,appendAtTop){
  this.viewer=viewer;
  this.spec=memberSpec;
  this.name=this.spec.name;
  this.type=this.spec.type;
  this.value=viewer.object[this.name];
  this.rowTr=document.createElement("tr");
  this.rowTr.className='objViewRow';
  if ( !(
    (this.type==objviewer.TYPE_ARRAY ||this.type==objviewer.TYPE_OBJECT)
    && this.viewer.isInline
    )
  ){
    this.nameTd=this.renderSideHeader();
    this.rowTr.appendChild(this.nameTd);
  }

  this.valTd=document.createElement("td");
  this.valTd.className='objViewValue';
  this.valTd.viewer=this;
  this.rowTr.appendChild(this.valTd);

  if (this.type==objviewer.TYPE_ARRAY ||this.type==objviewer.TYPE_OBJECT){
    if (this.viewer.isInline){
      this.valTd.colSpan=2;
      var nameDiv=this.renderTopHeader();
      this.valTd.appendChild(nameDiv);

      var valDiv=this.renderInlineObject();
      this.valTd.appendChild(valDiv);
    }else{
      var valDiv=this.renderPopoutObject();
      this.valTd.appendChild(valDiv);
    }
  }else if (this.type==objviewer.TYPE_IMAGE_URL){
    var valImg=this.renderImage();
    this.valTd.appendChild(valImg);
  }else if (this.type==objviewer.TYPE_SIMPLE){
    var valTxt=this.renderSimple();
    this.valTd.appendChild(valTxt);
  }
  if (appendAtTop){
    styling.insertAtTop(viewer.tbod,this.rowTr);
  }else{
    viewer.tbod.appendChild(this.rowTr);
  }
}

objviewer.PropertyViewer.prototype.renderSideHeader=function(){
    var nameTd=document.createElement("td");
    nameTd.className='objViewName';
    var nameTxt=document.createTextNode(this.name);
    nameTd.appendChild(nameTxt);
    return nameTd;
}

objviewer.PropertyViewer.prototype.renderTopHeader=function(){
    var nameDiv=document.createElement("div");
    nameDiv.className='objViewName right emphasis';
    var nameTxt=document.createTextNode(this.name);
    nameDiv.appendChild(nameTxt);
    return nameDiv;
}

objviewer.PropertyViewer.prototype.renderInlineObject=function(){
    var valDiv=document.createElement("div");
    valDiv.className='objViewInnerBorder';
    var childViewer=new objviewer.ObjectViewer(this.value,valDiv,true);
    return valDiv;
}

objviewer.PropertyViewer.prototype.renderPopoutObject=function(){
    var valDiv=document.createElement("div");
    valDiv.className='objViewButton';
    var displayTxt=(this.value.length!=null)
      ? "["+this.value.length+"] >>>" : ">>>";
    var valTxt=document.createTextNode(displayTxt);
    valDiv.parentViewer=this;
    valDiv.childObj=this.value;
    valDiv.onclick=objviewer.PropertyViewer.launchChild;
    valDiv.appendChild(valTxt);
    return valDiv;
}

objviewer.PropertyViewer.prototype.renderImage=function(){
    var valImg=document.createElement("img");
    valImg.className='objViewThumbnail';
    valImg.src=this.value;
    return valImg;
}

objviewer.PropertyViewer.prototype.renderSimple=function(){
    var valDiv=document.createElement("div");
    var valTxt=document.createTextNode(this.value);
    valDiv.appendChild(valTxt);
    if (this.spec.editable){
      valDiv.className+=" editable";
      valDiv.viewer=this;
      valDiv.onclick=objviewer.PropertyViewer.editSimpleProperty;
    }
    return valDiv;
}
//-- launch a secondary viewer for member objects and arrays --------


objviewer.PropertyViewer.launchChild=function(e){
  var childObj=this.childObj;
  var parentRow=this.parentViewer;
  var parentObjViewer=parentRow.viewer;
  var parentViewerEl=parentObjViewer.mainDiv;
  if (childObj && parentObjViewer){
    var popupViewer=objviewer.PropertyViewer.showPopup
      (this,this.childObj,parentRow.spec,parentViewerEl);
    parentObjViewer.children.append(popupViewer,true);
  }
}

objviewer.PropertyViewer.showPopup=function(launcherDiv,childObj,parentSpec,parentDiv){
  if (!parentDiv){
    parentDiv=launcherDiv;
  }
  var popupViewer=launcherDiv.popupViewer;
  var popupDiv=(popupViewer) ? popupViewer.mainDiv : null;
  if (!popupViewer){
    var isInline=(parentSpec) ? parentSpec.inline : false;
    var addNewFunc=(parentSpec) ? parentSpec.addNew : null;
    popupDiv=document.createElement("div");
    popupDiv.className='objViewBorder';
    var parentDomEl=parentDiv.parentNode;
    parentDomEl.appendChild(popupDiv);
    popupViewer=new objviewer.ObjectViewer(childObj,popupDiv,isInline,addNewFunc);
    popupViewer.parentObjViewer=parentDiv.viewer;
    launcherDiv.popupViewer=popupViewer;

    var closeButton=document.createElement("div");
    closeButton.className='objViewButton right';
    closeButton.viewer=popupViewer;
    closeButton.onclick=objviewer.PropertyViewer.hideChild;
    var closeTxt=document.createTextNode("x");
    closeButton.appendChild(closeTxt);
    styling.insertAtTop(popupDiv,closeButton);

  }
  popupDiv.style.display='block';
  var lX=parentDiv.offsetLeft;
  var lW=xWidth(parentDiv)
  var lY=parentDiv.offsetTop;
  var pX=lX+lW-4;
  var pY=lY+4;
  xMoveTo(popupDiv,pX,pY);
  return popupViewer;
}

objviewer.PropertyViewer.hideChild=function(e){
  var viewer=this.viewer;
  if (viewer){
    viewer.hide();
  }
}

/*
objviewer.PropertyViewer.prototype.fqName=function(){
  var fqn=this.name;
  var parent=this.viewer;
  while (parent){
    fqn=parent.+fqn;
  }
}
*/

//-- edit field functionality for simple fields ---------------------

objviewer.PropertyViewer.editSimpleProperty=function(e){
  var viewer=this.viewer;
  if (viewer){
    viewer.edit();
  }
}

objviewer.PropertyViewer.prototype.edit=function(){
  if (this.type=objviewer.TYPE_SIMPLE && this.status!=objviewer.STATUS_PENDING){
    //create a text input or textarea and overlay the valTd
    var edittype=(this.spec.editLarge) ? "textarea" : "input";
    var editor=document.createElement(edittype);
    editor.value=this.value;
    document.body.appendChild(editor);
    var td=this.valTd;
    xLeft(editor,xLeft(td));
    xTop(editor,xTop(td));
    xWidth(editor,xWidth(td));
    xHeight(editor,xHeight(td));
    td.replaceChild(editor,td.firstChild);
    editor.onblur=objviewer.PropertyViewer.editBlur;
    editor.viewer=this;
    editor.focus();
  }
}

objviewer.PropertyViewer.editBlur=function(e){
  var viewer=this.viewer;
  if (viewer){
    viewer.commitEdit(this.value);
  }
}

objviewer.PropertyViewer.prototype.commitEdit=function(value){
  if (this.type=objviewer.TYPE_SIMPLE){
    this.value=value;
    var valDiv=this.renderSimple();
    var td=this.valTd;
    td.replaceChild(valDiv,td.firstChild);

    this.viewer.notifyChange(this);
    this.setStatus(objviewer.STATUS_NEW);
  }
}

objviewer.STATUS_NORMAL=1;
objviewer.STATUS_PENDING=2;
objviewer.STATUS_NEW=3;

objviewer.PropertyViewer.prototype.setStatus=function(status){
  this.status=status;
  if (this.fader){
    clearTimeout(this.fader);
  }
  if (status==objviewer.STATUS_NORMAL){
    this.valTd.className='objViewValue';
  }else if (status==objviewer.STATUS_PENDING){
    this.valTd.className='objViewValue pending';
  }else if (status==objviewer.STATUS_NEW){
    this.valTd.className='objViewValue new';
    var rnd="fade_"+new Date().getTime();
    this.valTd.id=rnd;
    this.valTd.fadee=this;
    this.fader=setTimeout("objviewer.age('"+rnd+"')",5000);
  }
}

objviewer.age=function(id){
  var el=document.getElementById(id);
  var viewer=el.fadee;
  viewer.setStatus(objviewer.STATUS_NORMAL);
  el.id="";
  el.fadee=null;
}