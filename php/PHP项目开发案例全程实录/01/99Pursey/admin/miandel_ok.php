<?php
include("../conn/conn.php");
$id=$_GET[id];
$type=$_GET[type];
$state=$_GET[state];
$sql=mysql_query("delete from tb_info where id=$id");
if($sql){
	echo "<script>alert('该信息已经删除！');window.location.href='find_mianfei.php?type=$type&state=$state';</script>";
}
else{
	echo "<script>alert('该信息删除操作失败！');history.back();</script>";
}
?>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
