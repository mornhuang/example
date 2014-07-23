<?php
$id=$_GET[id];
include_once("../conn/conn.php");
if(mysql_query("delete from tb_bbqb where id='".$id."'",$conn)){
  echo "<script>alert('版本信息删除成功！');history.back();</script>";
}else{
  echo "<script>alert('版本信息删除失败！');history.back();</script>";
}
?>