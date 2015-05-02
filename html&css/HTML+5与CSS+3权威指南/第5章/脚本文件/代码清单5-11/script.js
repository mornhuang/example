function draw(id) 
{  
    var canvas = document.getElementById(id);  
    if (canvas == null)  
        return false;  
    var context = canvas.getContext('2d');  
    /* 定义颜色 */
    var colors = ["red", "orange", "yellow", "green", "blue", "navy", "purple"];
    /* 定义线宽*/
    context.lineWidth = 10;
    context.transform(1, 0, 0, 1, 100,0)
    /*循环绘制圆弧*/
    for( var i=0; i<colors.length; i++ ) 
    {
        /* 定义每次向下移动10个像素的变换矩阵 */
        context.transform(1, 0, 0, 1, 0, 10);
        /* 设定颜色 */
        context.strokeStyle = colors[i];
        /* 绘制圆弧 */
        context.beginPath();
        context.arc(50, 100, 100, 0, Math.PI, true);
        context.stroke();
    } 
}
