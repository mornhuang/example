<?php
  $page=intval($_POST[page_id]);
  include("conn/conn.php");
  while(list($value,$name)=each($_POST))
   {  
     mysql_query("delete from tb_dingdan where id='".$value."'",$conn);
   }
 header("location:lookdd.php?page=".$page."");
?>