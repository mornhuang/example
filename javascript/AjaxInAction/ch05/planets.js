var planets=new Object();

/** singleton solar system */
planets.SolarSystem=function(){
  this.planets=new Array();
  this.objViewSpec=[
    {name:"planets",type:"array"}
  ];
  this.planets.objViewSpec=new Array();
}

/** render the solar system */
planets.SolarSystem.prototype.render=function(div){
  this.mainDiv=xGetElementById(div);
  for (var p in this.planets){
    var pl=this.planets[p];
    if (pl.render){
      pl.render(div);
    }
  }
}

/** add a planet to the solar system */
planets.SolarSystem.prototype.addPlanet=function(planet){
  if (planet && planet.id){
    this.planets[planet.id]=planet;
    this.planets.objViewSpec.append( {name:planet.id,type:"object",inline:true} );
  }
}
planets.SolarSystem.prototype.removePlanet=function(planet){
  if (planet && planet.id){
    this.planets[planet.id]=null;
    var spec=this.planets.objViewSpec;
    for(var i=0;i<spec.length;i++){
      var child=spec[i];
      if (child.name==planet.id){
        spec.removeAt(i);
      }
    }
  }
}

planets.solarSystem=new planets.SolarSystem();
planets.SolarSystem=null;


/** */
planets.Planet=function(id,system,name,distance,diameter,image){
  this.id=id;
  this.system=system;
  this.name=name;
  this.distance=distance;
  this.diameter=diameter;
  this.image=image;
  this.facts=new Array();
  this.facts.owner=this;
  this.objViewSpec=[
    {name:"name",     type:"simple"},
    {name:"distance", type:"simple", editable:true},
    {name:"diameter", type:"simple", editable:true},
    {name:"image",    type:"image url"},
    {name:"facts",    type:"array",  addNew:this.newFact, inline:true },
  ];
  this.facts.objViewSpec=new Array();
}

/** render the button for this planet in the host solar system's main div */
planets.Planet.prototype.render=function(){
  if (!this.mainDiv){
    //don't render if the host solar system hasn't
    if (this.system.mainDiv){
      var div=document.getElementById(this.id);
      if (!div){
        div=document.createElement("div");
        div.className="planetbutton";
        div.id=this.id;
        this.system.mainDiv.appendChild(div);
        var img=document.createElement("img");
        img.src=this.img;
        img.alt=this.name;
        div.appendChild(img);
        div.onclick=planets.Planet.clickPopup;
        div.planetModel=this;
      }
      this.mainDiv=div;
    }
  }
}

planets.Planet.prototype.newFact=function(){
  var fact=new planets.Fact(-1,this,"please enter a value","please enter a value",new Date());
  this.owner.addFact(fact);
  return fact;
}

planets.Planet.clickPopup=function(event){
  var planet=this.planetModel;
  if (planets.Planet.activeMenu){
    planets.Planet.activeMenu.hide();
  }
  planets.Planet.activeMenu=objviewer.PropertyViewer.showPopup(this,planet,false);
}

/** parse a planet object from the xml returned by server, creating a new object or
updating the existing one as necessary */
planets.Planet.parse=function(domEl){
  var sys=planets.solarSystem;
  var attrs=domEl.attributes;
  var id=attrs.getNamedItem("id").value;
  var name=attrs.getNamedItem("name").value;
  var img=attrs.getNamedItem("img").value;
  var distance=parseFloat(attrs.getNamedItem("distance").value);
  var diameter=parseFloat(attrs.getNamedItem("diameter").value);
  var planetObj=sys.planets[id];
  if (!planetObj){
    planetObj=new planets.Planet(id,sys,name,distance,diameter,img);
    sys.addPlanet(planetObj);
  }else{
    var changed=set(planetObj,'name',name)
      || set(planetObj,'img',img)
      || set(planetObj,'distance',distance);
    if (changed && planetObj.rendered){
      planetObj.rendered=false;
    }
  }
  var facts=domEl.getElementsByTagName('fact');
  for(var i=0;i<facts.length;i++){
    planets.Fact.parse(facts.item(i),planetObj);
  }
}

/** add a fact about this planet */
planets.Planet.prototype.addFact=function(fact){
  if (fact && fact.id){
    this.facts[fact.id]=fact;
    this.facts.objViewSpec.append( {name:fact.id,type:"object"} );
  }
}


/** constructor for fact about a planet */
planets.Fact=function(id,planet,text,author,date){
  this.id=id;
  this.planet=planet;
  this.text=text;
  this.author=author;
  this.date=date;
  this.rendered=false;
  this.objViewSpec=[
    {name:"text",   type:"simple", editable:true, editLarge:true },
    {name:"author", type:"simple", editable:true },
    {name:"date", type:"simple"}
  ];
}

/** render this fact into the given parent div */
planets.Fact.prototype.render=function(div){
  if (!this.mainDiv){
    var mdiv=document.createElement("div");
    mdiv.className='factMain';
    var bod=document.createElement("div");
    bod.className='factBod';
    mdiv.appendChild(bod);
    var bodTxt=document.createTextNode(this.text);
    bod.appendChild(bodTxt);
    var foot=document.createElement("div");
    foot.className='factFoot';
    mdiv.appendChild(foot);
    var footTxt=document.createTextNode('created by '+this.author+' on '+this.date);
    foot.appendChild(footTxt);
    this.mainDiv=mdiv;
  }
  var currentParent=this.mainDiv.parentNode;
  if (div!=currentParent){
    if (currentParent){
      currentParent.removeChild(this.mainDiv);
    }
    div.appendChild(this.mainDiv);
  }
  this.rendered=true;
}

/** parse a fact object from XML stream */
planets.Fact.parse=function(domEl,planet){
  var attrs=domEl.attributes;
  var id=attrs.getNamedItem("id").value;
  var planet=(planet) ? planet : planets.solarSystem.planets[attrs.getNamedItem("planetId").value];
  var author=attrs.getNamedItem("author").value;
  var date=new Date(attrs.getNamedItem("date").value);
  var text=domEl.firstChild.data;
  var factObj=planet.facts[id];
  if (!factObj){
    factObj=new planets.Fact(id,planet,text,author,date);
    planet.addFact(factObj);
  }else{
    var changed=set(factObj,'text',text)
      || set(factObj,'author',author)
      || set(factObj,'date',date);
    if (changed && factObj.rendered){
      factObj.rendered=false;
    }
  }

}


/**
helper method for changing a property, and returning true if the value changed
useful for intelligently marking changes as dirty
*/
function set(obj,key,val){
  var changed=false;
  if (obj){
    var oldval=obj[key];
    if (oldval!=val){
      obj[key]=val;
      changed=true;
    }
  }
  return changed;
}

