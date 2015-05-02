function draw(id)
{  
    var canvas = document.getElementById(id);  
    if (canvas == null)  
        return false;  
    var context = canvas.getContext('2d'); 
    context.fillStyle = "rgb(0, 0, 255)";
    context.fillRect(0, 0, canvas.width, canvas.height);
    context.fillStyle = "rgb(255, 255, 0)";
    context.fillRect(10, 20, 50, 50); 
    window.location =canvas.toDataURL("image/jpeg"); 	
}
