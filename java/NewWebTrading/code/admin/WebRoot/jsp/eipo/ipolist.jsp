<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
<title>eIPO List</title>
</head>	
<link href="../jsp/css/link.css" rel="stylesheet" type="text/css">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<%@ include file="../js/checkurl.jsp"%>
<%boolean haveRecord = false;%>
<body bgcolor="E8EDF1"> 
  <table width="98%">
    <tr>
      <td align="center" >
      <table width="764" border="0">
        <tr>
          <td width="482" align="center">
            <table width="741"  border="0">
              <tr> 
                <td  height="31"> 
                  <div align="right"></div>
                </td>
                <td  height="31"><font color="#333333">&nbsp;</font></td>
              </tr>
              <tr> 
                <td >&nbsp;</td>
                <td > 
                  <form name="form1" method="post" >
                    <table width="100%" border="0" height="51" bgcolor="#FFFFFF">
                      <tr bgcolor="#A5B7C5">
                        <td height="24"><div align="right">eIPOCode:</div></td>
                        <td height="24"> <select name="ipoMasterId" size="1">
                            <logic:present name="ipoRecordList"><logic:iterate id="ipoRecord" name="ipoRecordList" > 
							<%haveRecord=true;%>
                            <option value=<bean:write name="ipoRecord" property="ipoMasterId"/>> 
                            <bean:write name="ipoRecord" property="ipoCode"/></option> 
                            </logic:iterate> </logic:present> </select>
                          <input type="submit" name="button1" value="Export IPO Request" class="button1"  onClick="expRequest()">
						  <input type="submit" name="button2" value="Export IPO LinkInfo" class="button1" onClick="expLinkInfo()"></td>
                      </tr>
                      <tr>
					  <%String appPath=System.getProperty("user.dir");
					  //System.out.println("appPath="+appPath);%>
		              <input type="hidden" name="ipoRequestFilePath" value="<%=appPath%>">
                        <td width="22%" height="21">&nbsp;</td>
                        <td width="78%"> <div align="center"> </div></td>
                      </tr>

					  
                    </table>
                  </form>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table></td>
   </tr>
  </table>
</body>
<script language="JavaScript">
<!--
if(<%=haveRecord%>==false)
{
   form1.button1.disabled=true;
   form1.button2.disabled=true;
}
	function expRequest()
	{ 
      form1.action="IPOExportRequestAction.do"; 

	}

	function expLinkInfo()
	{ 
	  form1.action="IPOExportReqResAction.do"; 
	}
		
//-->
</script>
</html>

