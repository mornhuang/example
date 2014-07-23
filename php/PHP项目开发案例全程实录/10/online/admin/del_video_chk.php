<?php
	session_start();
	include "conn/conn.php";
	include "inc/chec.php";
	$file_path = "../upfiles/video/";
	$s_sqlstr = "select * from tb_video where id = ".$_GET[id];
	$s_rst = $conn->execute($s_sqlstr);
	if(!($s_rst == false)){	
		if(file_exists($file_path.$s_rst->fields[16])){
			if(unlink($file_path.$s_rst->fields[16]) and unlink($file_path.$s_rst->fields[2])){
				$d_sqlstr = "delete from tb_video where id = ".$_GET[id];
				$d_rst = $conn->execute($d_sqlstr);
				if(!($d_rst == false)){
					echo "<script>alert('É¾³ý³É¹¦');location='main.php?action=video';</script>";
					exit();
				}else{
					echo "<script>alert('É¾³ýÊ§°Ü');history.go(-1);</script>";
					exit();
				}
			}
		}else{
			$d_sqlstr = "delete from tb_video where id = ".$_GET[id];
			$d_rst = $conn->execute($d_sqlstr);
			if(!($d_rst == false)){
				echo "<script>alert('´ËÎÄ¼þÒÑÉ¾³ý~');location='main.php?action=video';</script>";
				exit();
			}else{
				echo "<script>alert('É¾³ýÊ§°Ü');history.go(-1);</script>";
				exit();
			}
		}
	}
	else
		echo "<script>alert('É¾³ýÊ§°Ü');history.go(-1);</script>";
?>