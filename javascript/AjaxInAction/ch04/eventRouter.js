/*
event router object for robust cross-browser implementation of Observer pattern
for the 'classic' javascript event model (the newer addEventListener() and attachEvent()
does a similar job, but is somewhat flaky - see Quirskmode for a discussion [http://www.quirksmode.org/js/events_advanced.html].

requires extras-array.js

Dave Crane 2005
*/

/* namespacing object */
var jsEvent=new Array();

/*
constructor function, specifying DOM element (or other object) to listen to,
and event type, which should be a valid browser event e.g. 'onmouseover' 'onclick' for DOM
elements
*/
jsEvent.EventRouter=function(el,eventType){
  this.lsnrs=new Array();
  this.el=el;
  el.eventRouter=this;
  el[eventType]=jsEvent.EventRouter.callback;
}

/*
convenience method for adding a listener
*/
jsEvent.EventRouter.prototype.addListener=function(lsnr){
  this.lsnrs.append(lsnr,true);
}

/*
convenience method for removing a listener
*/
jsEvent.EventRouter.prototype.removeListener=function(lsnr){
  this.lsnrs.remove(lsnr);
}

/*
notify all listeners of an event - this is called by the callback, don't need
to invoke it yourself for DOM nodes, but if using bespoke events it is the
easiest way in
*/
jsEvent.EventRouter.prototype.notify=function(e){
  var lsnrs=this.lsnrs;
  for(var i=0;i<lsnrs.length;i++){
    var lsnr=lsnrs[i];
    lsnr.call(this,e);
  }
}

/*
'static' callback event handler, for which 'this' will be the DOM element. Simply
locate the reference to the router object, and notify() it
*/
jsEvent.EventRouter.callback=function(event){
  var e=event || window.event;
  var router=this.eventRouter;
  router.notify(e)
}

