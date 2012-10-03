<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>Uploade eIPO Criteria data</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<link href="../jsp/css/link.css" rel="stylesheet" type="text/css">
<%@ include file="../js/checkurl.jsp"%>
<body bgcolor="E8EDF1"> 
<form name="form1" method="post" action="fileQtyAmtUpload.jsp" enctype="multipart/form-data" onsubmit="return CheckForm(this)">
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
                    eIPO Criteria INFo </font></b></td>
                </tr>
                <tr bgcolor="#FFFFFF"> 
                  <td colspan="3"><font color="#FF0000">Notice:1>The File include Quantity&amount Info.</font></td>
                </tr>
                <tr bgcolor="#FFFFFF"> 
                  <td height="16" colspan="3"><font color="#FF0000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    2>The eIPO Criteria Info file name must be "IPOMasterQtyCriteria.dat".</font></td>
                </tr>
                <tr bgcolor="#FFFFFF"> 
                  <td colspan="3"><font color="#FF0000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>
                </tr>
                <tr align="right" valign="middle" bgcolor="#FFFFFF">
                  <td colspan="3"> </td>
                </tr>
                <tr bgcolor="#999999"> 
                  <td colspan="3"  bgcolor="#A5B7C5">
				  Quantity&amount Info File:<input name="file2" type="file" size="50" ContentEditable="false"> </td>
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
		pos = theForm.file2.value.lastIndexOf("\\");
		fileName2 = theForm.file2.value.substr(pos+1);		
		if(fileName2!="IPOMasterQtyCriteria.dat")
		{
			alert("the Quantity&amount Info data file is wrong,please select again!");
		    theForm.file2.focus();
			return false;
		}
	}

//-->
</script>
</html>
