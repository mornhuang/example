var globalId;
var i=0;
function draw(id)
{
    globalId=id;
    setInterval(Composite,1000);
}
function Composite() 
{  
    var canvas = document.getElementById(globalId);  
    if (canvas == null)  
        return false;  
    var context = canvas.getContext('2d'); 
    var oprtns = new Array(
    "source-atop",
    "source-in",
    "source-out",
    "source-over",
    "destination-atop",
    "destination-in",
    "destination-out",
    "destination-over",
    "lighter",
    "copy",
    "xor"
    );
    if(i>10) i=0;
    context.clearRect(0,0,canvas.width,canvas.height);
    context.save();
    //绘制原有图形（蓝色长方形）
    context.fillStyle = "blue";
    context.fillRect(10, 10, 60, 60);
    //设置组合方式 
    context.globalCompositeOperation = oprtns[i];
    //设置新图形（红色圆形）
    context.beginPath();
    context.fillStyle = "red";
    context.arc(60, 60, 30, 0, Math.PI*2, false);
    context.fill();
    context.restore();
    i=i+1;
}
