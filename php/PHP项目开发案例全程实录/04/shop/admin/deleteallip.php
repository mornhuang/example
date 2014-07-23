<?php
include("conn/conn.php");
mysql_query("delete from tb_ip",$conn);
echo "<script>alert('访客记录清除成功!');history.back();</script>";
?>