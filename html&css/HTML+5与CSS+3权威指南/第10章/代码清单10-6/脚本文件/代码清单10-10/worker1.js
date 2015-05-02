onmessage = function(event) {
var intArray=new Array(100);    
for(var i=0;i<100;i++)
    intArray[i]=parseInt(Math.random()*100);
postMessage(JSON.stringify(intArray));
close();
}

