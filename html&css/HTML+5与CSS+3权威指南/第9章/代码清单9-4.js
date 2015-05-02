<script>
var host="ws://localhost:8005/socket";
var webSocket=new WebSocket(host);
var userName;
var userAge;
var successFlag;
var currentTime;
webSocket.onmessage=function(event)
{
    var DataBase=JSON.parse(event.data);
    userName= DataBase.userName;
    userAge= DataBase.userAge;
    successFlag=false;
    if(DataBase.DataType=="SQLServer")
    { 
        //在SQL Server数据库中插入数据
        successFlag=InsertSQLData();
    }
    else if(DataBase.DataType=="ORACLE")
    {
        //在ORACLE数据库中插入数据
        successFlag=InsertORACLEData();
    }
    currentTime= new Date();
    webSocket.send(JSON.stringify({
        result:successFlag,
        time: currentTime}
    ));
}
</script>
