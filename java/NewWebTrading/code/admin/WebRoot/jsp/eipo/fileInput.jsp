<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>Uploade eIPO Basic data</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<link href="../jsp/css/link.css" rel="stylesheet" type="text/css">
<%@ include file="../js/checkurl.jsp"%>
<body bgcolor="E8EDF1"> 
<form name="form1" method="post" action="fileUpload.jsp" enctype="multipart/form-data" onsubmit="return CheckForm(this)">
  <p>&nbsp;</p>
  <table width="661" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr> 
    <td width="15" height="70" valign="top"> 
    </td>
    <td width="761" valign="top">
      <TABLE border="1" width="100%" cellspacing="0" cellpadding="1" align="center">
        <TR> 
            <TD height="98" align="center" valign="top" bordercolorlight="#DDEEFF" bordercolordark="#FFFFFF" bgcolor="#FFFFFF"> 
              <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="4a5293">
                <tr bgcolor="#FFFFFF"> 
                  <td colspan="3"  bgcolor="#A5B7C5"><b><font color="#000066">Import 
                    eIPO Basic INFo </font></b></td>
                </tr>
                <tr bgcolor="#FFFFFF"> 
                  <td colspan="3"><font color="#FF0000">Notice:1>The File include 
                    eIPO Basic Info .</font></td>
                </tr>
                <tr bgcolor="#FFFFFF"> 
                  <td height="16" colspan="3"><font color="#FF0000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    2>The eIPO Basic Info file name must be "MIS_IPOMasterFullList.dat".</font></td>
                </tr>
                <tr bgcolor="#FFFFFF"> 
                  <td colspan="3"><font color="#FF0000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>
                </tr>
                <tr bgcolor="#999999"> 
                  <td colspan="3"  bgcolor="#A5B7C5"> 
				  eIPO Basic Info File:<input name="file" type="file" size="50" ContentEditable="false"></td>
                </tr>
                <tr align="right" valign="middle" bgcolor="#FFFFFF">
                  <td colspan="3"> </td>
                </tr>
                <tr align="right" valign="middle" bgcolor="#FFFFFF"> 
                  <td width="65%" height="30"> <input name="Submit2" type="submit" value=" Upload "> 
                  </td>
                  <td width="11%" height="30"> <input name="Submit" type="reset" value=" Reset "> 
                  </td>
                  <td width="24%">&nbsp;</td>
                </tr>
              </table>
          </TD>
        </TR>
      </TABLE>
    </td>
  </tr>
</table></form>
</body>
<script language="JavaScript">
<!--
	function CheckForm(theForm)
	{

		pos = theForm.file.value.lastIndexOf("\\");
		fileName1 = theForm.file.value.substr(pos+1);
		//lastName = fileName.substr(6);		
		if(fileName1!="MIS_IPOMasterFullList.dat")
		{
			alert("the eIPO Basic Info data file is wrong,please select again!");
		    theForm.file.focus();
			return false;
		}
	}

//-->
</script>
</html>
