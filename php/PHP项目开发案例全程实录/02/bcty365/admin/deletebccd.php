<?php
$id=$_GET[id];
include("../conn/conn.php");
$sql=mysql_query("select * from tb_bccd where id='".$id."'",$conn);
$info=mysql_fetch_array($sql);
if(mysql_query("delete from tb_bccd where id='".$id."'",$conn))
{
 @unlink(".".substr($info[imageaddress],7,strlen($info[imageaddress])-7));
 
  echo "<script>window.location.href='default.php?htgl=±à¼­±à³Ì´Êµä';</script>";

}
else
{
 echo "<script>alert('ÊÓÆµÉ¾³ýÊ§°Ü!');history.back();</script>";
}
?>