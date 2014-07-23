<?php
include("../conn/conn.php");
$id=$_GET[id];
$flag=$_GET[flag];
$sql=mysql_query("update tb_advertising set flag=1 where id=$id");
if($sql){
	echo "<script>alert('该信息已被推荐！');window.location.href='find_gg.php?flag=$flag';</script>";
}
else{
	echo "<script>alert('该信息推荐操作失败！');history.back();</script>";
}
?>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
