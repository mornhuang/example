function draw(id)
{  
    var canvas = document.getElementById(id);  
    if (canvas == null)  
        return false; 
    var context = canvas.getContext('2d');
    var image = new Image(); 
    image.src = "tyl.jpg"; 
    image.onload = function () 
    {
       context.drawImage(image, 0, 0);
       var imagedata = context.getImageData(0,0,image.width,image.height);        
	for (var i = 0, n = imagedata.data.length; i < n; i += 4) 
      {
	       imagedata.data[i+0] = 255 - imagedata.data[i+0]; // red
	       imagedata.data[i+1] = 255 - imagedata.data[i+2]; // green
	       imagedata.data[i+2] = 255 - imagedata.data[i+1]; // blue
      }
      context.putImageData(imagedata, 0, 0);
    };      
}
