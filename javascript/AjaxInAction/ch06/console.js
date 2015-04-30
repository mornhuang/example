/*
javascript library

a text output console that can be attached to any DOM element

no explicit backing model, as the dom elements closely model the console contents
*/

var console=new Object();

console.Consoles=new Array();

/* constructor function */
console.Console=function(el){
  this.el=xGetElementById(el);
  this.el.className='console';
  this.el.consoleModel=this;
  this.clear();
}

/*append a text node or DOM element to the console*/
console.Console.prototype.append=function(obj,style){
  var domEl=styling.toDOMElement(obj,'div');
  if (style){
    domEl.className=style;
  }
  this.el.appendChild(domEl);
}

/*clear the console*/
console.Console.prototype.clear=function(){
  styling.removeAllChildren(this.el);
}

/*create a console window with a given name*/
console.getConsoleWindow=function(id){
  //if no id is passed in bail out : will investigate exception throwing later as a better solution
  if (!id){ return null; }
  var cons=console.Consoles[id];
  if (cons==null){
    var winEl=document.createElement('div');
    winEl.className='consoleWindow';
    winEl.id=id;
    document.body.appendChild(winEl);
    var win=new windows.Window(winEl,id);
    var consEl=document.createElement('div');
    winEl.appendChild(consEl);
    cons=new console.Console(consEl);
    console.Consoles[id]=cons;
    win.console=cons;
    win.resizeContents=console.resizeConsoleWindowContents;
    win.resize();
  }
  //we may have created a new window here to which we have no direct reference, but that's OK, because
  //we return the console, and can retrieve the window with cons.el.parent.windowModel
  return cons;
}

console.resizeConsoleWindowContents=function(win){
  var cons=win.console;
  if (cons){
    var winEl=win.body;
    var consEl=cons.el;
    if (winEl && consEl){
      xResizeTo(consEl,xWidth(winEl)-16,xHeight(winEl)-16);
      xMoveTo(consEl,8,8);
    }
  }
}
