<?php
include("conn/conn.php");
include("JS/function.php");
?>
<link href="css/style.css" rel="stylesheet">
<table width="679" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="443" valign="top" background="Images/eject.gif"><br>
      <br>
      <br>
      <br>
      <br>      <table width="530"  border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FFA200">
      <?php
		     $id=$_GET[id];
			 $sql=mysql_query("select * from tb_advertising where id='".$id."'",$conn);
			 $info=mysql_fetch_array($sql);
		   ?>
      <tr>
        <td width="72" height="35" bgcolor="#FFFFFF"><div align="center">广告主题：</div></td>
        <td width="256" height="35" bgcolor="#FFFFFF"><div align="left">&nbsp;<?php echo unhtml($info[title]);?></div></td>
        <td width="73" height="35" bgcolor="#FFFFFF"><div align="center">发布时间：</div></td>
        <td width="124" height="35" bgcolor="#FFFFFF"><div align="left">&nbsp;<?php echo $info[fdate];?></div></td>
      </tr>
      <tr>
        <td height="125" bgcolor="#FFFFFF"><div align="center">广告内容：</div></td>
        <td height="220" colspan="3" valign="top" bgcolor="#FFFFFF"><div align="left">
          <p><br>
            &nbsp;&nbsp;&nbsp;&nbsp;<?php echo unhtml($info[content]);?></p>
          </div></td>
      </tr>
    </table></td>
  </tr>
</table>
