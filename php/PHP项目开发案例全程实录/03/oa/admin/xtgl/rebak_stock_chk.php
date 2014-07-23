<?php
	session_start();
	include "../inc/chec.php";
	include "../conn/conn.php";
	$mysqlstr = "D:\\\AppServ\\MySQL\\bin\\mysql -uroot -hlocalhost -proot db_office < ../bak/".$_POST[r_name];
	exec($mysqlstr);
	echo "<script>alert('»Ö¸´³É¹¦');location='data_stock.php'</script>";
?>
