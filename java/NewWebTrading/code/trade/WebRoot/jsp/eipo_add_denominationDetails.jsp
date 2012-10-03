<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/webchannels.tld" prefix="taifook"%>
<%@page import="com.itsz.sht.struts.eipo.util.EIPOConstants"%>

<script language="JavaScript" src="/js/FormChek.js"></script>
<script language="JavaScript" src="/js/general.js"></script>
<script language="JavaScript" src="/js/DisableRightClick.js"></script>  
<link rel="StyleSheet" HREF="/css/other.css" TYPE="text/css">
<link rel="StyleSheet" HREF="/css/orderinput.css" TYPE="text/css">

<taifook:errors methodName="popupErrors" />

<style type="text/css">
<!--
.title {
    font-family: Tahoma;
    color: red;
    font-size: 12px;
    font-style: normal;
    text-decoration: none;
    font-weight: bold;
}
-->
</style>
<HTML>
	<HEAD>
	</HEAD>
	
	<logic:present name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" scope="session">
    	<bean:define id="ipoMaster" name="<%=EIPOConstants.SESSION_IPO_SUBSCRIPTION%>" type="com.itsz.sht.struts.eipo.dao.EIPOMasterEntry" />
  	</logic:present>
  
	<BODY bgcolor="white" leftMargin="0" topmargin="0" onLoad="popupErrors();">
	<taifook:errors methodName="popupErrors"/>
	
	&nbsp;股数一览表:<br/><br/>
	<table width="95%" align="center" border="0" cellspacing="1" cellpadding="3" bgcolor="#999999">
	  <tr>
	    <td align="left" bgcolor="#FFFFFF" width="50%"><font class="lat"><strong>数量</strong></font></td>
	    <td align="left" bgcolor="#FFFFFF" width="50%"><font class="lat"><strong>价格</strong></font></td>
	  </tr>
	  <logic:notEmpty name="ipoMaster">
	  	<logic:notEmpty name="ipoMaster" property="ipoDenominationList">
	 	<logic:iterate id="master" name="ipoMaster" property="ipoDenominationList" type="com.itsz.sht.struts.eipo.dao.IPODenominationEntry">
	 		<tr>
	    		<td align="left" bgcolor="#FFFFFF"><font class="lat"><%=master.getFormatAppliedShare()%></font></td>
	    		<td align="left" bgcolor="#FFFFFF"><font class="lat"><%=master.getFormatAmountPayable()%></font></td>
	  		</tr>
	 	</logic:iterate>
	 	</logic:notEmpty>
	  </logic:notEmpty>
	</table>
	
	<br/>
	<table width="95%" align="center" border="0" cellspacing="0" cellpadding="0">
	  <tr>
      <td  align=center bgcolor="#FFFFFF">
        <a href="#" onclick="javascript:window.close();">IMAGE_RESOURCE_KEY</a>
      </td>
      </tr>
	</table>

	
	</BODY>
</HTML>

