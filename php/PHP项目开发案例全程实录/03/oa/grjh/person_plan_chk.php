<?php
	session_start();
	include "../inc/chec.php";
	include "../conn/conn.php";
	include "../inc/func.php";
if(($_POST[p_content] != "") and ($_POST[p_date] != "")){
	$a_sql = "insert into tb_plan values('','".$_POST[p_content]."',".$_POST[p_type].",".$_SESSION[id].",'".$_POST[p_date]."')";
	$a_rst = mysql_query($a_sql,$conn);
	if($a_rst)
		echo "<script>alert('添加成功');location='../main.php';</script>";
	else
		echo "<script>alert('系统繁忙，请稍后再试');history.go(-1);</script>";
	}
	else
		echo "<script>alert('内容和时间不允许为空');history.go(-1);</script>";
?>