<html>
<head>
<title>查看昵称</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body topmargin="0" leftmargin="0" bottommargin="0">
<table width="150" height="80" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr> 
    <td height="60">
	<div align="center">
	<?php
	 $nc=trim($_GET[nc]);
	 if($nc=="")
	  {
	   echo "请输入用户昵称!";
	  }
	 else
	  { 
	   include_once("conn/conn.php");
	   $sql=mysql_query("select usernc from tb_user where usernc='".$nc."'",$conn);
	   $info=mysql_fetch_array($sql);
       if($info==false)
	    {
		 echo "恭喜您,该昵称未被占用!";
		}
		else
		{
		 echo "对不起,该昵称已被占用!";
		}
		mysql_close($conn);
	 }	
	?>
   </div></td>
  </tr>
  <tr>
    <td height="20"><div align="center"><input type="button" onClick="window.close()" value="关闭"></div></td>
  </tr>
</table>
</body>
</html>
