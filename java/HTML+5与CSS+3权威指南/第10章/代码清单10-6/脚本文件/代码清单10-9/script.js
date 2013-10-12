onmessage=function(event){
    var worker;
    //创建发送数据的子线程
    worker=new Worker("worker1.js");
    worker.postMessage("");     
    worker.onmessage = function(event) {
        //接收子线程中数据，本示例中为创建好的随机数组
        var data=event.data;
        //创建接收数据子线程
        worker=new Worker("worker2.js");
       //把从发送数据子线程中发回消息传递给接收数据的子线程
        worker.postMessage(data);
	worker.onmessage = function(event) {
             //获取接收数据子线程中传回数据，本示例中为挑选结果
	    var data=event.data;
            //把挑选结果发送回主页面
            postMessage(data);
	}
    }
}
