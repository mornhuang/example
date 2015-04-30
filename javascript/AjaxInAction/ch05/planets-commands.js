/**
command objects for planets.js example
- add or update a fact about a planet
- add a new planet
*/
planets.commands=new Object();


planets.commands.UpdateFactCommand=function(fact,field,oldValue,newValue){
  this.priority=net.CommandQueue.PRIORITY_NORMAL;
  this.id=this.fact.id; //what if it's -1?
  this.fact=fact;
  this.field=field;
  this.oldValue=oldValue;
  this.newValue=newValue;
}

planets.commands.UpdateFactCommand.toRequestString=function(){
  return {
    type:"updateFact",
    id:this.fact.id,
    planetId:this.fact.planet.id,
    field:this.field,
    oldValue:this.oldValue,
    newValue:this.newValue
  }.simpleXmlify("command");
}

planets.commands.UpdateFactCommand.parseResponse=function(docEl){
  var attrs=docEl.attributes;
  var status=attrs.getNamedItem("status").value;
  if (status=="ok"){
    //value was updated, nothing to do
  }else{
    //reset to old value
    this.fact[this.field]=this.oldValue;
  }
}


planets.commands.NewPlanetCommand=function(planet){
  this.priority=net.CommandQueue.PRIORITY_IMMEDIATE;
  this.id=this.planet.id; //what if it's -1?
}

planets.commands.NewPlanetCommand.toRequestString=function(){
  return {
    type:"newPlanet",
    id:this.planet.id,
    name:this.planet.name,
    distance:this.planet.distance,
    diameter:this.planet.diameter
  }.simpleXmlify("command");
}

planets.commands.NewPlanetCommand.parseResponse=function(docEl){
  var attrs=docEl.attributes;
  var status=attrs.getNamedItem("status").value;
  if (status=="ok"){
    if (this.id<0){
      this.planet.id=attrs.getNamedItem("id").value;
    }
    var newImg=attrs.getNamedItem("img").value;
    if (newImg && this.planet.image==null){
      this.planet.image=newImg;
    }
  }else{
    planets.solarSystem.removePlanet(this.planet);
  }
}




