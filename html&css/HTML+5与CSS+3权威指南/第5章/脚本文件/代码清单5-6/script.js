function draw(id) 
{  
    var canvas = document.getElementById(id);  
    if (canvas == null)  
        return false;  
    var context = canvas.getContext('2d');  
    context.fillStyle = "#EEEEFF";  
    context.fillRect(0, 0, 400, 300);  
    var n = 0;  
    var dx = 150;  
    var dy = 150;  
    var s = 100;  
    context.beginPath();  
    context.globalCompositeOperation ='and';  
    context.fillStyle = 'rgb(100,255,100)';  
    var x = Math.sin(0);  
    var y = Math.cos(0);  
    var dig = Math.PI / 15 * 11;  
    context.moveTo(dx,dy);  
    for(var i = 0; i < 30; i++) 
    {  
        var x = Math.sin(i * dig);  
        var y = Math.cos(i * dig);  
        context.bezierCurveTo(dx + x * s,dy + y * s - 100,dx + x * s + 100,dy + y * s,dx + x * s,dy + y * s);  
    }     
    context.closePath();  
    context.fill();  
    context.stroke();  
}




