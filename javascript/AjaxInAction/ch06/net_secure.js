/*
url-loading object and a request queue built on top of it
*/

/* namespacing object */
var net=new Object();

net.READY_STATE_UNINITIALIZED=0;
net.READY_STATE_LOADING=1;
net.READY_STATE_LOADED=2;
net.READY_STATE_INTERACTIVE=3;
net.READY_STATE_COMPLETE=4;


/*--- content loader object for cross-browser requests ---*/
net.ContentLoader=function(url,onload,onerror,method,params,contentType,headers,secure){
  this.req=null;
  this.url=url;
  this.onload=onload;
  this.onerror=(onerror) ? onerror : this.defaultError;
  this.secure=secure;
  this.loadXMLDoc(url,method,params,contentType,headers);
}

net.ContentLoader.prototype={
 loadXMLDoc:function(url,method,params,contentType,headers){
  if (!method){
    method="GET";
  }
  if (!contentType && method=="POST"){
    contentType='application/x-www-form-urlencoded';
  }
  if (window.XMLHttpRequest){
    this.req=new XMLHttpRequest();
  } else if (window.ActiveXObject){
    this.req=new ActiveXObject("Microsoft.XMLHTTP");
  }
  this.debug("got request object");
  if (this.req){
    try{
      try{
        if (this.secure && netscape && netscape.security.PrivilegeManager.enablePrivilege) {
          netscape.security.PrivilegeManager.enablePrivilege('UniversalBrowserRead');
        }
      }catch (err){}
      this.req.open(method,url,true);
      this.debug("opened");
      if (contentType){
        this.req.setRequestHeader('Content-Type', contentType);
      }
      if (headers){
        for (var h in headers){
          this.req.setRequestHeader(h,headers[h]);
        }
      }
      var loader=this;
      this.req.onreadystatechange=function(){
        loader.onReadyState.call(loader);
      }
      this.req.send(params);
      this.debug("sent");
    }catch (err){
      this.onerror.call(this);
    }
  }
 },

 onReadyState:function(){
  var req=this.req;
  var ready=req.readyState;
  if (ready==net.READY_STATE_COMPLETE){
    var httpStatus=req.status;
    if (httpStatus==200 || httpStatus==0){
      try{
        if (this.secure && netscape && netscape.security.PrivilegeManager.enablePrivilege) {
          netscape.security.PrivilegeManager.enablePrivilege('UniversalBrowserRead');
        }
      }catch (err){}
      this.onload.call(this);
    }else{
      this.onerror.call(this);
    }
  }
 },

 defaultError:function(){
  alert("error fetching data!"
    +"\n\nreadyState:"+this.req.readyState
    +"\nstatus: "+this.req.status
    +"\nheaders: "+this.req.getAllResponseHeaders());
 },

 debug:function(str){
   logger.append("["+this.url+"] "+str);
 }
}


