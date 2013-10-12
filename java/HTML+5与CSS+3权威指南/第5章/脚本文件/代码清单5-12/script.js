function draw(id) 
{  
    var canvas = document.getElementById(id);  
    if (canvas == null)  
        return false;  
    var context = canvas.getContext('2d');  
    /* ----------------------------------------------
    * 绘制红色长方形
    * -------------------------------------------- */
    context.strokeStyle = "red";
    context.strokeRect(30, 10, 60, 20);
    /* ----------------------------------------------
    * 绘制顺时针旋转45°后的蓝色长方形
    * -------------------------------------------- */
    /*绘制45°圆弧 */
    var rad = 45 * Math.PI / 180;
    /*定义顺时针旋转45°的变换矩阵*/
    context.setTransform(Math.cos(rad), Math.sin(rad), -Math.sin(rad), 
Math.cos(rad), 0, 0 );
    /* 绘制图形 */
    context.strokeStyle = "blue";
    context.strokeRect(30, 10, 60, 20);
    /* ----------------------------------------------
    * 绘制放大2.5倍后的绿色长方形
    * -------------------------------------------- */
    /* 定义放大2.5倍的变换矩阵 */
    context.setTransform(2.5, 0, 0, 2.5, 0, 0);
    /* 绘制图形 */
    context.strokeStyle = "green";
    context.strokeRect(30, 10, 60, 20);
    /* ----------------------------------------------
    * 将坐标原点向右移动40像素，向下移动80像素后绘制灰色长方形
    * -------------------------------------------- */
    /* 定义将坐标原点向右移动40像素，向下移动80像素的矩阵 */
    context.setTransform(1, 0, 0, 1, 40, 80);
    /* 绘制图形 */
    context.strokeStyle = "gray";
    context.strokeRect(30, 10, 60, 20);
}
