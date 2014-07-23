<?
	session_start();
	if(!isset($_SESSION[id]) or !isset($_SESSION[name])){
		echo "<script>alert('您没有登录或超时');window.close();</script>";
		exit();
	}
?>