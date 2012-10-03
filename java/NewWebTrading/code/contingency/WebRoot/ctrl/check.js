var whitespace = " \t\n\r";
function isEmpty(s)
{
	return ((s == null) || (s.length == 0) || myTrim(s)=="");
}


function isWhitespace(s)
{
	var i;

	if (isEmpty(s)) return true;
	for (i = 0; i < s.length; i++)
	{
		var c = s.charAt(i);
		if (whitespace.indexOf(c) == -1) return false;
	}

	return true;
}

function checkEmpty(theObject,message)
{
	var s = theObject.value
	if ((s == null) || (s.length == 0 ))
	{
		alert(message);
		theObject.focus();
		return false;
	}else{
		if(isWhitespace(s)){
			alert(message);
			theObject.focus();
			return false;
		}
	}
	return true;
}
function myTrim(str)
{
	var end = false;
	var ch;
	while(!end){
		if (str.length == 0) break;
		ch = str.charAt(0);
		if (ch == ' ')	str = str.substring(1,str.length);
		else end = true;
	}
	end = false;
	while(!end){
		if (str.length == 0) break;
		ch = str.charAt(str.length-1);
		if (ch == ' ')	str = str.substring(0,str.length-1);
		else end = true;
	}
	return str;
}