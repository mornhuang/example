/**
simple javascript profiler

usage:

var sw=stopwatch.start("my category");
//do something
sw.stop();

dc
*/

var stopwatch=new Object();
stopwatch.watches=new Array();

stopwatch.getWatch=function(id,startNow){
  var watch=stopwatch.watches[id];
  if (!watch){
    watch=new stopwatch.StopWatch(id);
  }
  if (startNow){
    watch.start();
  }
  return watch;
}
stopwatch.report=function(div){
  var realDiv=xGetElementById(div);
  var reportObj=new Array();
  reportObj.objViewSpec=new Array();
  for (var i in stopwatch.watches){
    var child=stopwatch.watches[i];
    if (child instanceof stopwatch.StopWatch){
      reportObj[i]=child;
      reportObj.objViewSpec.append( {name: child.id, type: "object"} );
    }
  }
  var report=new objviewer.ObjectViewer(reportObj,realDiv,true,false);
}

stopwatch.StopWatch=function(id){
  this.id=id;
  stopwatch.watches[id]=this;
  this.events=new Array();
  this.count=0;
  this.total=0;
  this.objViewSpec=[
    {name: "count", type: "simple"},
    {name: "total", type: "simple"}
  ];
}
stopwatch.StopWatch.prototype.start=function(){
  this.current=new stopwatch.TimedEvent();
}
stopwatch.StopWatch.prototype.stop=function(){
  if (this.current){
    this.current.stop();
    this.events.append(this.current);
    this.count++;
    this.total+=this.current.duration;
    this.current=null;
  }
}

stopwatch.TimedEvent=function(){
  this.start=new Date();
  this.objViewSpec=[
    {name: "start", type: "simple"},
    {name: "duration", type: "simple"}
  ];
}
stopwatch.TimedEvent.prototype.stop=function(){
  var stop=new Date();
  this.duration=stop-this.start;
}

