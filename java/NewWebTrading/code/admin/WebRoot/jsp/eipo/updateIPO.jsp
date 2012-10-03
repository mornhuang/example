<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
<title>Edit eIPOName</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<link href="../jsp/css/link.css" rel="stylesheet" type="text/css">
<%@ include file="../js/checkurl.jsp"%>
<body bgcolor="E8EDF1"> 
<form name="form1" method="post" action="IPOUpdateMngAction.do" onsubmit="return CheckForm(this)" >
  <table width="80%" border="1">
    <tr> 
      <td width="41%" bgcolor="#A5B7C5">eIPO code</td>
      <td width="46%"> <bean:write name="currentIpoReord" property="ipoCode" /> 
      </td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">English name</td>
      <td><input type="text" size="38" name="ipoName" value="<bean:write name="currentIpoReord" property="ipoName" />" ></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">GB name</td>
      <td><input type="text" size="38" name="ipoName_gb" value="<bean:write name="currentIpoReord" property="ipoName_gb"/>" ></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">BIG5 name</td>
      <td><input type="text" size="38" name="ipoName_big" value="<bean:write name="currentIpoReord" property="ipoName_big"/> "></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Application DeadLine</td>
      <td><input type="text" name="deadLine_dsply" value="<bean:write name="currentIpoReord" property="deadLine_dsply" />"></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Payment deadline</td>
      <td><input type="text" name="depositDate_dsply" value="<bean:write name="currentIpoReord" property="depositDate_dsply" />"></td>
    </tr>
    <tr>
      <td bgcolor="#A5B7C5">Payment Debit Date</td>
      <td><input type="text" name="debitDate_dsply_mng" value="<bean:write name="currentIpoReord" property="debitDate_dsply_mng" />"></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Prospectus English URL</td>
      <td><input type="text" name="prop_url_en" value="<bean:write name="currentIpoReord" property="prop_url_en"/>" ></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Prospectus Chinese URL</td>
      <td><input type="text" name="prop_url_cn" value="<bean:write name="currentIpoReord" property="prop_url_cn"/>" ></td>
    </tr>
    <tr> 
      <td bgcolor="#A5B7C5">Prospectus Cantonese URL</td>
      <td><input type="text" name="prop_url_tw" value="<bean:write name="currentIpoReord" property="prop_url_tw"/>" ></td>
    </tr>
    <tr> 
      <td> </td>
      <td> <input type="submit" name="Submit" value="Update"> <input type="hidden" name="ipoMasterId" value=<bean:write name="currentIpoReord" property="ipoMasterId" />> 
        </td>
    </tr>
  </table>
  <p>Note:1)The date field format should be the same as:YYYY-MM-DD hh:mm:ss</p>
  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2)All of the URL no need input "http://". </p>
</form>
<p>&nbsp;</p><p>&nbsp;</p></body>
<script language="JavaScript">
<!--
	function CheckForm(theForm)
	{	
		if(theForm.depositDate_dsply.value==""||theForm.debitDate_dsply_mng.value=="")
		{
            alert('Any date field cannot be blank,pls fill in.');
		    theForm.depositDate_dsply.focus();
			return false;
		}
        return true;
	}
	

//-->
</script>
</html>
