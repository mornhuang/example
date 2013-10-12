function draw(id) 
{  
    var canvas = document.getElementById(id);  
    if (canvas == null)  
        return false;  
    var context = canvas.getContext('2d');  
    context.fillStyle = "#EEEEFF";  
    context.fillRect(0, 0, 400, 300);  
    // 图形绘制 
    context.translate(200,50);  
    context.fillStyle = 'rgba(255,0,0,0.25)';  
    for(var i = 0;i < 50;i++)
    {  
        context.translate(25,25);  
        context.scale(0.95,0.95);  
        context.rotate(Math.PI / 10);  
        context.fillRect(0,0,100,50);  
    }  
}
