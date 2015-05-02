onmessage = function(event) 
{
    var num = event.data;
    var result = 0;
    for (var i = 0; i <= num; i++) 
result += i;
    //向线程创建源送回消息
    postMessage(result);
}
