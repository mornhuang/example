<?php
include_once("../conn/conn.php");
if(mysql_query("delete from tb_xlh where id='".$_GET[id]."'",$conn)){
  echo "<script>alert('序列号删除成功！');history.back();</script>";
}else{
   echo "<script>alert('序列号删除失败！');history.back();</script>";
}
?>