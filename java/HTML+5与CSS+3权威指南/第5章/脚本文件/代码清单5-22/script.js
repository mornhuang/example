function draw(id) 
{  
    var canvas = document.getElementById(id);  
    if (canvas == null)  
        return false;  
    var context=canvas.getContext('2d'); 
    context.fillStyle= '#00f';
    context.font= 'italic 30px sans-serif';
    context.textBaseline = 'top';
    //填充字符串
    context.fillText  ('示例文字', 0, 0);
    context.font='bold  30px sans-serif';
    //轮廓字符串
    context.strokeText('示例文字', 0, 50);
}
