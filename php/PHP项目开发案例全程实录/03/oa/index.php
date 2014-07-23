<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>职员登录</title>
<link href="css/style.css" rel="stylesheet" />
<script src="js/client_js.js"></script>
<? include "conn/conn.php"; ?>
</head>
<body onload="document.login.username.focus();">
<center>
<form id="login" name="login" method="post" action="index_ok.php">
<table width="100%" height="620" border="0" cellpadding="0" cellspacing="0" background="images/login_bg.jpg">
 	<tr>
		<td colspan="3" height="150">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td width="315" height="199" align="center" valign="middle">
		<table width="315" height="199" border="0" cellpadding="0" cellspacing="0" background="images/login_center.jpg">
      		<tr>
        		<td height="74" colspan="2" align="center" valign="middle">&nbsp;</td>
       		</tr>
      		<tr>
      		  <td width="140" height="25" align="right" valign="middle"><span id="lg">用户名称：</span></td>
   		      <td width="175" align="left" valign="middle"><input name="username" type="text" id="username" size="15" /></td>
      		</tr>
      		<tr>
       			<td height="25" align="right" valign="middle"><span id="lg">用户密码：</span></td>
      			<td align="left" valign="middle"><input name="pwd" type="password" id="pwd" size="15" /></td>
      		</tr>
	  		<tr>
				<td height="25" align="right" valign="middle"><span id="lg">用户组：</span></td>
        		<td align="left" valign="middle">
				<select name="u_group" style=" width:115px;">
				<?php
					$sqlstr = "select * from tb_group";
					$result = mysql_query($sqlstr,$conn);
					while($rows = mysql_fetch_row($result)){
						echo "<option value=".$rows[0].">".$rows[1]."</option>";
					}
				?>
				</select>				</td>
      		</tr>
      		<tr>
        		<td height="50" colspan="2" align="center" valign="middle">
				  <input type="hidden" name="action" value="login" />
				<input type="submit" name="login" id="login" value="" onclick="return chk_lg();"/>&nbsp;&nbsp;&nbsp; 
       		  <input type="reset" name="reset" id="reset" value="" /></td>
      		</tr>
    	</table>
	</td>
	<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3" height="155">&nbsp;</td>
	</tr>
 </table>
    
  </form>
</center>
</body>
</html>
