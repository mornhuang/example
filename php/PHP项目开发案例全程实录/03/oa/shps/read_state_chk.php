<?php
session_start();
include "../conn/conn.php";
$i_sql = "update tb_iss set i_state = ".$_POST[a_state]." where id = ".$_POST[id];
$i_rst = mysql_query($i_sql,$conn);
if($i_rst)
	echo "<script>alert('…Û∫À≥…π¶');window.close();</script>";
else
	echo "<script>alert('…Û∫À ß∞‹£¨…‘∫Û‘Ÿ ‘');history.go(-1);</script>";
?>