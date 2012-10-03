//Shielding the button of mouse
$(document).bind("contextmenu",function(e){return false;});
/*
$(function(){
	document.onkeydown = check;
	document.onkeypress = check;
});
function check(eve) {
	ev = window.event || eve;
	code = ev.keyCode || ev.which;
	src = ev.srcElement || ev.target;
	type = src.type;
	if (code == 8) {
		if (src.readOnly) return false;
		else if (type != "text" && type != "textarea" && type != "password") {
			return false;
		}
	} else if (code == 37 || code == 39) {
		return false;
	}
	return true;
}
*/
