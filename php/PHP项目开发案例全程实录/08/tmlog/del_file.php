<?php   
	session_start();      
	include "check_login.php";
	include "Conn/conn.php";
    $sql="delete from tb_article where id=".$file_id;
    $result=mysql_query($sql,$link);
	if($result){
		$sql1 = "delete from tb_filecomment where fileid = ".$file_id;
		$rst1 = mysql_query($sql1,$link);
		if($rst1)
			echo "<script>alert('╡╘©мндубря╠╩и╬ЁЩ!');location='$_SERVER[HTTP_REFERER]';</script>";
		else
			echo "<script>alert('и╬ЁЩй╖╟э!');history.go(-1);</script>";
	}
	else{	
		echo "<script>alert('╡╘©мндуби╬ЁЩ╡ывВй╖╟э!');history.go(-1);</script>";
	}	
?> 



