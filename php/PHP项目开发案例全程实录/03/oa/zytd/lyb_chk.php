<?php
	include "../conn/conn.php";
	include "../inc/chec.php";
	include "../inc/func.php";
if(isset($_POST[l_title]) and isset($_POST[l_content])){
		$l_sql = "insert into tb_lyb values('','".$_POST[l_title]."','".$_POST[l_content]."',now(),'','')";
		$l_rst = mysql_query($l_sql,$conn);
		re_message($l_rst,"lyb.php?u_id=24");
	}
	else
		echo "<script>alert('非法操作，请重新登录！');location='../index.php';</script>";
?>