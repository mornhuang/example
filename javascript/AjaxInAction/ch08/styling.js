/*
javascript library

helper functions for styling dom nodes
*/

var styling=new Object();

/* set the opacity of a DOM node to a given value, applying the css3 standard
styling plus mozilla and IE bespoke stylings
@param opacity a value between 0 and 1
*/
styling.opacity=function(ele,opacity){
  ele.style.opacity=opacity;
  ele.style.mozOpacity=opacity;
  ele.style.filter="alpha(opacity="+(opacity*100)+")";
}

/*promote an element in the DOM tree by attaching it directly to its grandparent, and
adjusting the position accordingly
*/
styling.promote=function(ele){
  var par=ele.parent;
  if (par){
    var gpar=par.parent;
    if (gpar){
      par.removeChild(ele);
      gpar.appendChild(ele);
    }
  }
}

/*wrap the argument as a dom element if necessary. If it's already a DOM element, leave it alone
used to ensure that an argument is ready to be passed to functions requiring a viewable element*/
styling.toDOMElement=function(obj,wrapperType){
  var result=null;
  if (obj instanceof Element){
    result=obj;
  }else{
    var txtNode=document.createTextNode(""+obj);
    if (wrapperType){
      var wrapper=document.createElement('div');
      wrapper.appendChild(txtNode);
      result=wrapper;
    }else{
      result=txtNode;
    }
  }
  return result;
}

/*remove all child nodes from the specified node*/
styling.removeAllChildren=function(ele){
  if (ele){
    while(ele.firstChild){
      ele.removeChild(ele.firstChild);
    }
  }
}

styling.insertAtTop=function(parent,child){
  if (parent.firstChild){
    parent.insertBefore(child,parent.firstChild);
  }else{
    parent.appendChild(child);
  }
}