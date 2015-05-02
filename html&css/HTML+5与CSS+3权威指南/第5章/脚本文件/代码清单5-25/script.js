var context;
var width,height;
var i;
function draw(id)
{
    var canvas = document.getElementById(id);  
    if (canvas == null)  
        return false;  
    context = canvas.getContext('2d'); 
    width=canvas.width;
    height=canvas.height;
    i=0;
    setInterval(rotate,100);         //十分之一秒
}
function rotate()
{   
    context.clearRect(0,0,width,height);
    context.fillStyle = "red";
    context.fillRect(i, 0, 20, 20);
    i=i+20;
}
