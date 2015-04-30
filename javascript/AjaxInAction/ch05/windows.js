/*
javascript library

generic window object
*/

var windows=new Object();

/*global z-order indices*/
windows.Z_UNFOCUS_FOLDER=1;
windows.Z_UNFOCUS_EXTRAS=2;
windows.Z_FOCUS_FOLDER=3;
windows.Z_FOCUS_EXTRAS=4;
windows.focused=null;
windows.windows=new Array();

/* constructor for window object*/
windows.Window=function(winEl,displayStr,x,y,w,h){
  windows.windows[windows.windows.length]=this;
  this.displayStr=displayStr;

  this.body=xGetElementById(winEl);
  if (!this.body){
    this.body=document.createElement("div");
    document.body.appendChild(this.body);
  }
  if (!this.body.className){
    this.body.className="window";
  }
  if (x && y){
    xMoveTo(this.body,x,y);
  }
  if (w && h){
    xResizeTo(this.body,w,h);
  }

  this.body.windowModel=this;
  xAddEventListener(this.body,'click',this.bodyOnClick,false);

  this.dragbar=document.createElement("div");
  this.body.parentNode.appendChild(this.dragbar);
  this.dragbar.className='dragbar';
  this.dragbar.windowModel=this;
  xEnableDrag(this.dragbar,null,this.barOnDrag,null);

  this.titlebar=document.createElement("div");
  this.body.parentNode.appendChild(this.titlebar);
  this.titlebar.className='titlebar';
  this.titlebar.windowModel=this;
  this.titlebar.appendChild(document.createTextNode(displayStr));
  xEnableDrag(this.titlebar,null,this.titleOnDrag,null);

  this.resize();
  this.bringToFront();

}

/*event handler: move window when titlebar is dragged*/
windows.Window.prototype.titleOnDrag=function(ele, mdx, mdy){
  var windowModel=ele.windowModel;
  if (windowModel){
    windowModel.bringToFront();
    var body=windowModel.body;
    if (body){
      xMoveTo(body, xLeft(body) + mdx, xTop(body) + mdy);
      windowModel.resize();
    }
  }
}

/*event handler: resize window when drag handle is dragged*/
windows.Window.prototype.barOnDrag=function(ele, mdx, mdy){
  var windowModel=ele.windowModel;
  if (windowModel){
    windowModel.bringToFront();
    var body=windowModel.body;
    if (body){
      xResizeTo(body, xWidth(body) + mdx, xHeight(body) + mdy);
      windowModel.resize();
    }
  }
}

/*event handler: onclick handler for entire window widget, implementing a click-to-raise policy*/
windows.Window.prototype.bodyOnClick=function(e){
  var event=new xEvent(e);
  var ele=event.target;
  var windowModel=ele.windowModel;
  if (windowModel){
    windowModel.bringToFront();
  }
}

/*resize a folder and programmatically reposition those elements that are too complicated to do using CSS*/
windows.Window.prototype.resize=function(){
  var bod=this.body;
  if (this.dragbar){
    var db=this.dragbar;
    xMoveTo(db, xLeft(bod)+xWidth(bod)-(xWidth(db)/2), xTop(bod)+xHeight(bod)-(xHeight(db)/2));
  }
  if (this.titlebar){
    var tb=this.titlebar;
    xMoveTo(tb, xLeft(bod)+12, xTop(bod));
    xResizeTo(tb,xWidth(bod)-30,16)
  }
  //leave the opportunity for the contents to be resized in specific windows
  if (this.resizeContents){
    this.resizeContents(this);
  }
}

/*default resize contents fits first child into body, assuming its the only child*/
windows.Window.prototype.resizeContents=function(){
  var bod=this.body;
  var content=bod.firstChild;
  while(content && content.nodeType!=1 && content.nextSibling){
    content=content.nextSibling;
  }
  if (content){
    xMoveTo(content,6,6);
    xResizeTo(content,xWidth(bod)-12,xHeight(bod)-12);
  }
}

/*bring a folder to the front of the z-order stack and push its predecessor back.
This will be called frequently during drag, so note the early-escape clause
in the first line*/
windows.Window.prototype.bringToFront=function(){
  if (this==windows.focused){
    return;
  }
  if (windows.focused){
    windows.focused.unfocus();
  }
  windows.focused=this;
  this.focus();
}

/*focus a folder*/
windows.Window.prototype.focus=function(){
  xZIndex(this.body,windows.Z_FOCUS_FOLDER);
  xZIndex(this.dragbar,windows.Z_FOCUS_EXTRAS);
  xZIndex(this.titlebar,windows.Z_FOCUS_EXTRAS);
}

/*unfocus a folder*/
windows.Window.prototype.unfocus=function(){
  xZIndex(this.body,windows.Z_UNFOCUS_FOLDER);
  xZIndex(this.dragbar,windows.Z_UNFOCUS_EXTRAS);
  xZIndex(this.titlebar,windows.Z_UNFOCUS_EXTRAS);
}

