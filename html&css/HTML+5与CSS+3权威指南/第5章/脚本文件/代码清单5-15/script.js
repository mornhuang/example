function draw(id) 
{  
    var canvas = document.getElementById(id);  
    if (canvas == null)  
        return false;  
    var context = canvas.getContext('2d');  
    context.fillStyle = "#EEEEFF";  
    context.fillRect(0, 0, 400, 300);  
    image = new Image(); 
    image.src = "tyl.jpg";  
    image.onload = function() 
    {  
        drawImg(context,image);  
    };       
}  
function drawImg(context,image)
{  
    for(var i = 0;i < 7;i++)  
       context.drawImage(image,0 + i * 50,0 + i * 25,100,100);  
}
