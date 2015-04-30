//--- the container object
function Container(id){
  this.id=id;
  this.body=document.getElementById(id);
  this.boxes=new Array();
}

Container.prototype.add=function(box){
  this.boxes[this.boxes.length]=box;
  this.body.appendChild(box.body);
}

Container.prototype.clear=function(){
  for(var i=0;i<this.boxes.length;i++){
    this.boxes[i].hide();
  }
  this.boxes=new Array();
  report("clear");
  newDOMs=0;
  reusedDOMs=0;
}

//--- define a simple object that links a backing object to an interactive visual UI
//NB: it doesn't keep a reference to the container that holds it
function ClickBox(container){
  this.size=40;
  this.x=5+Math.floor(Math.random()*370);
  this.y=5+Math.floor(Math.random()*370);
  this.id="box"+container.boxes.length;
  this.state=0;
  this.render();
  container.add(this);
}

//try to reuse dead DOM nodes if appropriate
ClickBox.prototype.render=function(){
  this.body=null;
  if (reuseDOM){
    this.body=document.getElementById(this.id);
  }
  if (this.body==null){
    this.body=document.createElement("div");
    this.body.id=this.id;
    newDOMs++;
  }else{
    reusedDOMs++;
  }
  this.body.backingObj=this;
  this.body.className='box1';
  this.body.style.left=this.x+"px";
  this.body.style.top=this.y+"px";
  this.body.onclick=function(){
    var clickbox=this.backingObj;
    clickbox.incrementState();
  }
}

ClickBox.prototype.incrementState=function(){
  if (this.state==0){
    this.body.className='box2';
  }else if (this.state==1){
    this.body.className='box3';
  }
  this.state++;
}

ClickBox.prototype.hide=function(){
  var bod=this.body;
  bod.className='box3';
  if (unlinkOnHide){
    bod.parentNode.removeChild(bod);
  }
  if (breakCyclics){
    bod.backingObj=null;
    this.body=null;
  }
}

//--- add some boxes to the container
function populate(count){
  var box=null;
  for(var i=0;i<count;i++){
    box=new ClickBox(container);
  }
  report("populate");
  newDOMs=0;
  reusedDOMs=0;
}

function submitForm(){
    var count=document.myform.elements['boxCount'].value;
    if (count<1){
      alert("positive numbers only, please");
    }else{
      populate(count);
    }
}

function debug(msg){
  debugPane.innerHTML+="<br>"+msg;
}

function report(title){
  debug("<b>"+title+"</b>");
  debug("new DOM elements created : "+newDOMs);
  debug("DOM elements reused : "+reusedDOMs);
  debug("children of container : "+container.body.childNodes.length);
}

var container=null;  //global  reference to container
var debugPane=null;
window.onload=function(){
  container=new Container('container');
  debugPane=document.getElementById('debug');
}

var reuseDOM=false;    //if true, then try to reuse DOM elements before creating a new box
var unlinkOnHide=false;//if true, then unlink the DOM node from parent when closing a box
var breakCyclics=false; // if true, explicitly break the cyclic reference between DOM and non-DOM nodes when removing a box

function setReuseDOM(){ reuseDOM=document.myform.elements['reuseDOM'].checked; debug("reuse DOM ? "+reuseDOM); }
function setUnlinkOnHide(){ unlinkOnHide=document.myform.elements['unlinkOnHide'].checked; debug("unlink on hide ? "+unlinkOnHide);  }
function setBreakCyclics(){ breakCyclics=document.myform.elements['breakCyclics'].checked; debug("break cyclics ? "+breakCyclics); }

var newDOMs=0;
var reusedDOMs=0;

function stressTest(){
  for (var i=0;i<240;i++){
     populate(100);
     populate(100);
     container.clear();
  }
  alert("done");
}
