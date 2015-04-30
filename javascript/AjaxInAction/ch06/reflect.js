Object.prototype.implementsProp=function(propName){
  return (this[propName]!=null);
}
Object.prototype.implementsFunc=function(funcName){
  return this[funcName] && this[funcName] instanceof Function;
}
Object.prototype.simpleXmlify=function(tagname){
  var xml="<"+tagname;
  for (i in this){
    if (!this[i] instanceof Function){
      xml+=" "+i+"=\""+this[i]+"\"";
    }
  }
  xml+="/>";
  return xml;
}