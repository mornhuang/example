<?php
	session_start();
	include "../inc/chec.php";
	include "../conn/conn.php";
	include "../inc/func.php";
	$sqlstr = "delete from tb_register";
	$result = mysql_query($sqlstr,$conn);
	re_message($result,"manage_note.php");
?>