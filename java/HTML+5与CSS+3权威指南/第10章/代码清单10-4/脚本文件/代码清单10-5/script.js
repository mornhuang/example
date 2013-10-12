onmessage = function(event) {
    var data = event.data;
    var returnStr; //将3的倍数拼接成字符串并返回	
    var intArray=data.split(";"); //返回字符串中数字分隔符为;
    returnStr="";
    for(var i=0;i<intArray.length;i++)
    {
        if(parseInt(intArray[i])%3==0)  //能否被3整除
        {
            if(returnStr!="")
                returnStr+=";";
            returnStr+=intArray[i];
        }
    }
    postMessage(returnStr); //返回3的倍数拼接成的字符串
}
