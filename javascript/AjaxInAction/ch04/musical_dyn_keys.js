var notes=new Array("do","re","mi","fa","so","la","ti","do");

/*
build the keys and then assign event handlers to the musical keys programmatically.
*/
function assignKeys(){
  var candidates=document.getElementsByTagName("div");
  if (candidates){
    for(var i=0;i<candidates.length;i++){
      var candidate=candidates[i];
      if (candidate.className.indexOf('musicalKeys')>=0){
        makeKeyboard(candidate);
      }
    }
  }
}

function makeKeyboard(el){
  for(var i=0;i<notes.length;i++){
    var key=document.createElement("div");
    key.className=notes[i]+" musicalButton";
    key.note=notes[i];
    key.onmouseover=playNote;
    el.appendChild(key);
  }
}

function playNote(event){
  var note=this.note;
  var console=document.getElementById('console');
  if (note && console){
    console.innerHTML+=note+" . ";
  }
}