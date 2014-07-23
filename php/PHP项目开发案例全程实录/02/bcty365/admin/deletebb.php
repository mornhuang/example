<?php
include_once("../conn/conn.php");
$id=$_GET[id];
if(mysql_query("delete from tb_bb where id='".$id."'",$conn)){
  echo "<script>alert('±à³Ì´Êµä°æ±¾É¾³ý³É¹¦£¡');history.back();</script>";
}else{
 echo "<script>alert('±à³Ì´Êµä°æ±¾É¾³ýÊ§°Ü');history.back();</script>";
}


?>