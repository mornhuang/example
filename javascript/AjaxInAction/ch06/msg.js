/*
TODO
hide tooltip on mouseout? or on click on document? think-bubble stalk?

messages and notifications UI library

high priority messages are shown as modal dialog
default priority messages are shown as non-modal dialog
low priority messages as icons on toolbar with tooltip popup

dc
*/

var msg=new Object();

msg.PRIORITY_LOW=    { id:1, lifetime:30, icon:"img/msg_lo.png" };
msg.PRIORITY_DEFAULT={ id:2, lifetime:60, icon:"img/msg_def.png" };
msg.PRIORITY_HIGH=   { id:3, lifetime:-1, icon:"img/msg_hi.png" };

msg.messages=new Array();

msg.Message=function(id,message,priority,lifetime,icon){
  this.id=id;
  msg.messages[id]=this;
  this.message=message;
  this.priority=(priority) ? priority : msg.PRIORITY_DEFAULT.id;
  this.lifetime=(lifetime) ? lifetime : this.defaultLifetime();
  this.icon=(icon) ? icon : this.defaultIcon();
  if (this.lifetime>0){
    this.fader=setTimeout(
      "msg.messages['"+this.id+"'].clear()",
      this.lifetime*1000
    );
  }
  if (!msg.suppressRender){
    this.attachToBar();
  }
}

msg.Message.prototype.attachToBar=function(){
  if (!msg.msgbarDiv){
    msg.render();
  }else if (this.priority==msg.PRIORITY_LOW.id){
    this.render(msg.msgbarDiv);
  }else{
    if (!msg.dialog){
      msg.dialog=msg.createDialog(
        msg.msgbarDiv.id+"_dialog",
        msg.msgbarDiv,
        (this.priority==msg.PRIORITY_HIGH.id)
      );
    }
    this.render(msg.dialog.tbod);
    msg.showDialog();
  }
}

msg.Message.prototype.clear=function(){
  if (this.pinned){
    this.expired=true;
  }else{
    this.unrender();
  }
}  
msg.Message.prototype.unrender=function(){
  msg.messages[this.id]=null;
  if (this.row){
    this.row.style.display='none';
    this.row.messageObj=null;
    this.row=null;
  }
  if (this.icoTd){
    this.icoTd.style.display='none';
    this.icoTd.messageObj=null;
    this.icoTd=null;
  }
}

msg.Message.prototype.defaultLifetime=function(){
  if (this.priority<=msg.PRIORITY_LOW.id){
    return msg.PRIORITY_LOW.lifetime;
  }else if (this.priority==msg.PRIORITY_DEFAULT.id){
    return msg.PRIORITY_DEFAULT.lifetime;
  }else if (this.priority>=msg.PRIORITY_HIGH.id){
    return msg.PRIORITY_HIGH.lifetime;
  }
}

msg.Message.prototype.defaultIcon=function(){
  if (this.priority<=msg.PRIORITY_LOW.id){
    return msg.PRIORITY_LOW.icon;
  }else if (this.priority==msg.PRIORITY_DEFAULT.id){
    return msg.PRIORITY_DEFAULT.icon;
  }else if (this.priority>=msg.PRIORITY_HIGH.id){
    return msg.PRIORITY_HIGH.icon;
  }
}

msg.Message.prototype.render=function(el){
  if (this.priority<=msg.PRIORITY_LOW.id){
    this.renderSmall(el);
  }else if (this.priority>=msg.PRIORITY_DEFAULT.id){
    this.renderFull(el);
  }
}

msg.Message.prototype.renderSmall=function(el){
  this.icoTd=document.createElement("div");
  var ico=document.createElement("img");
  ico.src=this.icon;
  ico.className="msg_small_icon";
  this.icoTd.appendChild(ico);
  this.icoTd.messageObj=this;
  this.icoTd.onmouseover=msg.moverIconTooltip;
  this.icoTd.onmouseout=msg.moutIconTooltip;
  this.icoTd.onclick=msg.clickIconTooltip;

  el.appendChild(this.icoTd);
}

msg.moverIconTooltip=function(e){
  var event=e || window.event;
  var message=this.messageObj;
  var popped=message.popped;
  if (!popped){
    message.showPopup(event,false);
  }
}
msg.moutIconTooltip=function(e){
  var message=this.messageObj;
  var popped=message.popped;
  var pinned=message.pinned;
  if (popped && !pinned){
    message.hidePopup();
  }
}
msg.clickIconTooltip=function(e){
  var event=e || window.event;
  var message=this.messageObj;
  var popped=message.popped;
  var pinned=message.pinned;
  var expired=message.expired;
  if (popped && pinned){
    message.hidePopup();
    if (expired){
      message.unrender();
    }  
  }else{
    message.showPopup(event,true);
  }
}
msg.Message.prototype.showPopup=function(event,pinned){
  this.pinned=pinned;
  if (!this.popup){
    this.popup=document.createElement("div");
    this.popup.className='popup';
    this.renderFull(this.popup);
    document.body.appendChild(this.popup);
  }
  this.popup.style.display='block';
  var popX=event.clientX;
  var popY=event.clientY-xHeight(this.popup)-12;
  xMoveTo(this.popup,popX,popY);
  if (msg.popper && msg.popper!=this){
    msg.popper.hidePopup();
  }
  this.popped=true;
  msg.popper=this;
}
msg.Message.prototype.hidePopup=function(){
  if (this.popped){
    if (this.popup){
      this.popup.style.display='none';
    }
    this.popped=false;
  }
}

msg.Message.prototype.renderFull=function(el){
  var inTable=(el.tagName=="TBODY");
  var topEl=null;
  this.row=document.createElement("tr");
  if (!inTable){
    topEl=document.createElement("table");
    topEl.style.border="solid red 2px";
    bod=document.createElement("tbody");
    topEl.appendChild(bod);
    bod.appendChild(this.row);
  }else{
    topEl=this.row;
  }

  var icoTd=document.createElement("td");
  icoTd.valign='center';
  this.row.appendChild(icoTd);
  var ico=document.createElement("img");
  ico.src=this.icon;
  icoTd.className="msg_large_icon";
  icoTd.appendChild(ico);

  var txtTd=document.createElement("td");
  txtTd.valign='top';
  txtTd.className="msg_text";
  this.row.appendChild(txtTd);
  txtTd.innerHTML=this.message;

  el.appendChild(topEl);
}

msg.render=function(msgbar){
  if (!msgbar){
    msgbar='msgbar';
  }
  msg.msgbarDiv=xGetElementById(msgbar);
  if (!msg.msgbarDiv){
    msg.msgbarDiv=msg.createBar(msgbar);
  }
  styling.removeAllChildren(msg.msgbarDiv);
  var lows=new Array();
  var meds=new Array();
  var highs=new Array();
  for (i in msg.messages){
    var message=msg.messages[i];
    if (message){
      if (message.priority<=msg.PRIORITY_LOW.id){
        lows.append(message);
      }else if (message.priority==msg.PRIORITY_DEFAULT.id){
        meds.append(message);
      }else if (message.priority>=msg.PRIORITY_HIGH.id){
        highs.append(message);
      }
    }
  }
  for (var i=0;i<lows.length;i++){
    lows[i].render(msg.msgbarDiv);
  }
  if (meds.length+highs.length>0){
    msg.dialog=xGetElementById(msgbar+"_dialog");
    if (!msg.dialog){
      msg.dialog=msg.createDialog( msgbar+"_dialog",msg.msgbarDiv, (highs.length>0) );
    }
    styling.removeAllChildren(msg.dialog.tbod);
    for (var i=0;i<highs.length;i++){
      highs[i].render(msg.dialog.tbod);
    }
    for (var i=0;i<meds.length;i++){
      meds[i].render(msg.dialog.tbod);
    }
    if (highs.length>0){
      msg.dialog.ico.src=msg.PRIORITY_HIGH.icon;
    }else if (meds.length>0){
      msg.dialog.ico.src=msg.PRIORITY_DEFAULT.icon;
    }
  }
}

msg.createBar=function(id){
  var msgbar=document.createElement("div");
  msgbar.className='msgbar';
  msgbar.id=id;
  var divEl=xGetElementById(div);
  var parentEl=(divEl) ? divEl : document.body;
  parentEl.append(msgbar);
  return msgbar;
}

msg.createDialog=function(id,bar,isModal){
  var dialog=document.createElement("div");
  dialog.className='dialog';
  dialog.id=id;
  var tbl=document.createElement("table");
  dialog.appendChild(tbl);
  dialog.tbod=document.createElement("tbody");
  tbl.appendChild(dialog.tbod);

  var closeButton=document.createElement("div");
  closeButton.className='button right';
  closeButton.dialog=dialog;
  closeButton.onclick=msg.hideDialog;
  var closeTxt=document.createTextNode("x");
  closeButton.appendChild(closeTxt);
  dialog.appendChild(closeButton);

  if (isModal){
    dialog.modalLayer=document.createElement("div");
    dialog.modalLayer.className='modal';
    dialog.modalLayer.appendChild(dialog);
    document.body.appendChild(dialog.modalLayer);
  }else{
    dialog.className+=' non-modal';
    document.body.appendChild(dialog);
  }

  dialog.ico=document.createElement("img");
  dialog.ico.className="msg_dialog_icon";
  dialog.ico.dialog=dialog;
  dialog.ico.onclick=msg.showDialog;
  bar.appendChild(dialog.ico);

  return dialog;
}

msg.hideDialog=function(e){
  var dialog=(this.dialog) ? this.dialog : msg.dialog;
  if (dialog){
    if (dialog.modalLayer){
      dialog.modalLayer.style.display='none';
    }else{
      dialog.style.display='none';
    }
  }
}

msg.showDialog=function(e){
  var dialog=(this.dialog) ? this.dialog : msg.dialog;
  if (dialog){
    if (dialog.modalLayer){
      dialog.modalLayer.style.display='block';
    }else{
      dialog.style.display='block';
    }
  }
}
