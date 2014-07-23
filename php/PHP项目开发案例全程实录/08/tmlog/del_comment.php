<?php       
	session_start();  
	include "Conn/conn.php";
    $sql="delete from tb_filecomment where id=".$comment_id;
    $result=mysql_query($sql);
	if($result){
		echo "<script>alert('É¾³ý³É¹¦!');location='$_SERVER[HTTP_REFERER]';</script>";
	}
	else{	
		echo "<script>alert('É¾³ý²Ù×÷Ê§°Ü!');history.go(-1);</script>";
	}	
?> 



