<?php
$id=$_GET[id];
include_once("../conn/conn.php");
if(mysql_query("delete from tb_dd where id='".$id."'",$conn)){
  
  echo "<script>alert('¶©µ¥É¾³ý³É¹¦!');history.back();</script>";

}else{

  echo "<script>alert('¶©µ¥É¾³ýÊ§°Ü!');history.back();</script>";
}


?>