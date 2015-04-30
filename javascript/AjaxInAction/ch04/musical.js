/*
assign event handlers to the musical keys programmatically.

*/
function assignKeys(){
  var keyboard=document.getElementById('keyboard');
  var keys=keyboard.getElementsByTagName("div");
  if (keys){
    for(var i=0;i<keys.length;i++){
      var key=keys[i];
      var classes=(key.className).split(" ");
      if (classes && classes.length>=2 && classes[1]=='musicalButton'){
        var note=classes[0];
        key.note=note;
        key.onmouseover=playNote;
      }
    }
  }
}

function playNote(event){
  var note=this.note;
  var console=document.getElementById('console');
  if (note && console){
    console.innerHTML+=note+" . ";
  }
}