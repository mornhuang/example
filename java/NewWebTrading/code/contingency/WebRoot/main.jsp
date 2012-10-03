<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ page import="java.sql.*"%>
<%@ page import="com.itsz.util.*"%>
<%@ page import="com.itsz.util.database.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.*"%>
<%@ page import="com.itsz.Contingency.ClientMain"%>
<%@ page import="com.itsz.web.rtq.util.RTQInfo"%>
<%@ page import="com.itsz.common.Constants" %>
<%@ page import="com.itsz.util.LangUtil" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.itsz.parameter.util.ParameterManager"%>
<%
String loginId = request.getParameter("loginID");
String Language = request.getParameter("Language");
LangUtil.saveLocale2Session(session,Language);
//session.setAttribute(Constants.LOGIN_ID,loginId);
if (loginId==null) {
	if (Language==null) {
		response.sendRedirect("index.jsp");
	}
	else{
		response.sendRedirect("login.jsp?Language="+Language);
	}
	return;
}
//delay quote url changed by Danny on March 28,2006,provided by Online for eServices embed
String delayQuoteUrl;
if ("C".equals(Language)) {
	delayQuoteUrl = "http://www.htisec.com/tc/eservices/quote_eservices.jsp";
}
else if ("GB".equals(Language)){
	delayQuoteUrl = "http://gb.htisec.com/gb/www.htisec.com/tc/eservices/quote_eservices.jsp" ;
}
else {
	delayQuoteUrl = "http://www.htisec.com/en/eservices/quote_eservices.jsp" ;
}

String custCode = (String)request.getAttribute("custCode");

String rtqChnlUsrID = request.getAttribute("RTQChnlUsrID").toString();
String rtqChnlUsrPwd = (String)request.getAttribute("RTQChnlUsrPwd");
String quoteUrl = (String)request.getAttribute("quoteUrl");
String rtqChnlName = (String)request.getAttribute("RTQChnlName");
String RTQPrimalURL = (String)request.getAttribute("RTQPrimalURL");
String RTQStatus = (String)request.getAttribute("RTQStatus");
String Status = (String)request.getAttribute("Status");
String ProdStatus = (String)request.getAttribute("ProdStatus");
String RTQProdName = (String)request.getAttribute("RTQProdName");
String[] quoteInfo = new String[] {rtqChnlUsrID,rtqChnlUsrPwd,quoteUrl,rtqChnlName,RTQPrimalURL,RTQStatus,Status,ProdStatus,RTQProdName};

//String aeCode = request.getParameter("aeCode");
//String aeName = request.getParameter("aeName");
//String aePhone = request.getParameter("aePhone");
//String[] aeInfo = new String[] { aeCode, aeName, aePhone };
String refresh_min = ClientMain.refreshMin;
String refresh_max = ClientMain.refreshMax;
String status =ClientMain.secStatus;

String pageTitle     = "Haitong International Securities Company Limited" ;
String imageLogo     = "./images/en_US/top_logo.gif";
String imageLogout   = "<a href=\"javascript:logout('logout.do')\">Logout</a>";
String languageChange= "<a href=\"javascript:chgLang('C')\"><font color=ffffff>繁體</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href=\"javascript:chgLang('GB')\"><font color=ffffff>简体</a>";
String availAlert    = "Iweb trading System is available, please click here to access>>";
String availAlertUrl = ParameterManager.getValue("TradeLoginUrl_E");
//String availAlertUrl = "https://trade.htisec.com/web/login.do?lang=en_US" ;

String inavilAlert   = "Iweb trading service is currently under maintenance, please wait......" ;

String contactInfo   = "<p class=\"contactinfo\">&nbsp;&nbsp;&nbsp;&nbsp;"
+ "Iweb trading service is currently under maintenance, "
+ "system is temporarily suspended. "
+ "Please contact Customer Service Dept at 3588 7688."
+ "<p class=\"midfont\">&nbsp;&nbsp;&nbsp;&nbsp;Sorry for any inconvenience caused.";
String copyrightInfo = "&nbsp;&nbsp;Copyright&copy;2011 Haitong International Securities Company Limited. All Rights Reserved." ;
String discclaimers  = "<a href='http://www.htisec.com/en/disclaimer.jsp' target=_blank><u>Disclaimers</u></a> and <a href='http://www.htisec.com/en/dpp.jsp' target=_blank><u> Data Privacy Policy</u></a>" ;

if ("C".equals(Language)){
	pageTitle     = "海通國際證劵有限公司" ;
	imageLogo     = "./images/zh_TW/top_logo.gif";
	imageLogout   = "<a href=\"javascript:logout('logout.do')\">登出</a>";
	languageChange= "<a href=\"javascript:chgLang('E')\"><font color=ffffff>English</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href=\"javascript:chgLang('GB')\"><font color=ffffff>简体</a>";
	availAlert    = "自主網交易系統已經可以使用，請點擊此處登入>>";
	availAlertUrl = ParameterManager.getValue("TradeLoginUrl_C");;

	inavilAlert   = "由於自主網交易系統現正進行緊急維護 ，請稍候······";

	contactInfo   = "<p class=\"contactinfo\">&nbsp;&nbsp;&nbsp;&nbsp;"
	+ "由於自主網交易系統現正進行緊急維護，暫時未能提供網上證券買賣服務，即時及稍延報價則服務正常，如需下單，請致電3588 7688與客戶服務部聯絡。"
	+ "<p class=\"midfont\">&nbsp;&nbsp;&nbsp;&nbsp;"
	+ "不便之處，敬請原諒。";
	copyrightInfo = "&nbsp;&nbsp;Copyright&copy;2011海通國際證劵有限公司.版權所有" ;
	discclaimers  = "<a href='http://www.htisec.com/tc/disclaimer.jsp' target=_blank><u>&#20813;&#36012;&#32882;&#26126;</u></a>&#21450;<a href='http://www.htisec.com/tc/dpp.jsp' target=_blank><u>&#20491;&#20154;&#36039;&#26009;&#31169;&#38577;&#25919;&#31574;</u></a>" ;

}
else if ("GB".equals(Language)){
	pageTitle     = "海通国际证劵有限公司" ;
	imageLogo     = "./images/zh_CN/top_logo.gif";
    imageLogout   = "<a href=\"javascript:logout('logout.do')\">登出</a>";
	languageChange= "<a href=\"javascript:chgLang('C')\"><font color=ffffff>繁體</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href=\"javascript:chgLang('E')\"><font color=ffffff>English</a>";
	availAlert    = "自主网交易系统已经可以使用，请点击此处登入>>";
	availAlertUrl = ParameterManager.getValue("TradeLoginUrl_GB"); ;

	inavilAlert   = "由于自主网交易系统现正进行紧急维护，请稍候······ " ;

	contactInfo   = "<p class=\"contactinfo\">&nbsp;&nbsp;&nbsp;&nbsp;"
	+ "由于自主网交易系统现正进行紧急维护，暂时未能提供网上证券买卖服务，即时及稍延报价则服务正常，如需下单，请致电3588 7688与客户服务部联络。"
	+ "<p class=\"midfont\">&nbsp;&nbsp;&nbsp;&nbsp;"
	+ "不便之处，敬请原谅。";
	copyrightInfo = "&nbsp;&nbsp;Copyright&copy;2011海通国际证劵有限公司.版权所有" ;
	discclaimers  = "<a href='http://gb.htisec.com/gb/www.htisec.com/tc/disclaimer.jsp?type=1' target=_blank><u>&#20813;&#36131;&#22768;&#26126;</u></a>&#21450;<a href='http://gb.htisec.com/gb/www.htisec.com/tc/dpp.jsp?type=1' target=_blank><u>&#20010;&#20154;&#36164;&#26009;&#31169;&#38544;&#25919;&#31574;</u></a>" ;

}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title><%=pageTitle %></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<!-- Get styles sheet -->
	<style>
	A:link		{font-family: "Verdana" , "Tahoma" , "Arial" "細明體"; font-size: 10pt; color:005bac; text-decoration: underline; cursor: hand;}
	A:visited	{font-family: "Verdana" , "Tahoma" , "Arial" "細明體"; font-size: 10pt; color:005bac; text-decoration: underline;}
	A:active	{font-family: "Verdana" , "Tahoma" , "Arial" "細明體"; font-size: 10pt; color:f39600; text-decoration: none;}
	A:hover		{font-family: "Verdana" , "Tahoma" , "Arial" "細明體"; font-size: 10pt; color:f39600; text-decoration: none;}

	body, td
	{
	font-family: "Verdana" , "Tahoma" , "Arial" "細明體";
	font-size: 10pt;
	}
	.warp {   
	  position: absolute;
	  width:800px;
	  border: solid 1px red;   
	}   
	</style>

	<script type="text/javascript" src="./ctrl/DisableRightClick.js"></script>
<script type="text/javascript">
<!--

  var refresh_min = <%=refresh_min%>;
  var refresh_max = <%=refresh_max%>;
  var refresh = refresh_min + Math.round(Math.random()*(refresh_max-refresh_min));

  var timename=setInterval("checkstatus();",refresh*1000);
  function checkstatus(){
	iframe3.location.href = "./checkstatus.jsp";
  }
  function popDisclaimers(lang) {

	if (lang=="tw"){
		window.open("http://www.htisec.com/chi/aboutus/disclaimer.jsp", "Disclaimers");
	}
	else if (lang=="cn"){
		window.open("http://gb.htisec.com/gb/www.htisec.com/chi/aboutus/disclaimer.jsp", "Disclaimers");
	}
	else {
		window.open("http://www.htisec.com/english/aboutus/disclaimer.jsp", "Disclaimers");
	}
  }
  function popDPP(lang) {
  
    if (lang=="tw"){
		window.open("http://www.htisec.com/chi/aboutus/dpp.jsp", "DPP");
	}
	else if (lang=="cn"){
		window.open("http://gb.htisec.com/gb/www.htisec.com/chi/aboutus/dpp.jsp", "DPP");
	}
	else {
		window.open("http://www.htisec.com/english/aboutus/dpp.jsp", "DPP");
    }
  }
  function chgLang(lang){
	  theForm = document.form2 ;
	  theForm.Language.value = lang;
	  theForm.submit();
	  //window.location="changLang.do?lang="+lang;
  }
  function logout(url){
      if(window.confirm('<bean:message key="message.logout"/>')){
          window.location=url;
      }
  }
  //-->
</script>
</head>
<body bgcolor="#FFFFFF" topmargin="0" leftmargin="0" marginwidth="0" marginheight="0">
<table id="title" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td><img src="<%=imageLogo %>"></td>
	<td valign=center align=right><FONT COLOR="005bac"><B>[&nbsp;&nbsp;<%=imageLogout %>&nbsp;&nbsp;]&nbsp;&nbsp;</B></FONT></td>
</tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr bgcolor="0046ad" height="20">
	<td width="100%" align="right">
		<table border="0" cellpadding="2" cellspacing="0" bgcolor="0046ad">
		<tr>
			<td><font color="ffffff">&nbsp;&nbsp;|&nbsp;&nbsp;</font></td>
			<td><%=languageChange %></td>
			<td><font color="ffffff">&nbsp;&nbsp;|&nbsp;&nbsp;</font></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br/>
<table border="0" cellpadding="5" width="800" align="center">
<tr>
		  <td align="center">
		    <span id="avilalert" style="display:none">
			  <marquee scrolldelay="60" width="600" height="16" behavior="slide" scrollamount="12"><a href="<%=availAlertUrl%>"><font size=3 color=990000><b><%=availAlert%></b></font></a></marquee>
	        </span>
			<span id="inavilalert" style="display:none">
               <font size=3 color=990000><b><%=inavilAlert%></b></font>
	        </span>
		  </td>
        </tr>
<tr><td align="center">
<br/><br/><br/><br/>

<%
			boolean iscontinue=true;
			if((rtqChnlUsrID==null && rtqChnlUsrPwd==null) ||
			("".equals(rtqChnlUsrID) && "".equals(rtqChnlUsrPwd))){
				iscontinue=false;
			}
			session.setAttribute("iscontinueRTQ",new Boolean(iscontinue));
			String url;
			if (quoteInfo[8].equals("aastock")){
				url = "./showAAStocksRTQ.jsp" ;
			}
			else{
				url = quoteInfo[2] ;
				
			}
			//display RTQ	
			if (iscontinue && url!=null && !"".equals(url)){		
					  
			
			  if("qpjunior".equals(quoteInfo[8]) || "powerticker".equals(quoteInfo[8])||"ETNet".equals((quoteInfo[8]))){			  
			  
			  	 url="SelectQuotation.do";
			  %>
			  <br>
			  <span id="delay_quote"></span>
			   <span id="etnetQs2"></span>			
			  <%
			  }else if (quoteInfo[8].equals("aastock")||quoteInfo[8].equals("Finet")||quoteInfo[8].equals("qpifull")){
				 		%>
				 		<span id="delay_quote"></span>
				 		<span id="etnetQs2"></span>
			  <%}
			//display delayed quote
			}else {
				if(quoteInfo[5].equals("UNAVAIL") || quoteInfo[6].equals("UNAVAIL") || quoteInfo[7].equals("UNAVAIL")){
				%>
				
				<SCRIPT language="javascript">
					var status = '<%=status%>';
					var rtqName = '<%=rtqChnlName%>'
					if(status!='A'){
						alert('<bean:message key="error.unavailable_rtq1" /> <bean:message key="<%=rtqChnlName%>" /> <bean:message key="error.unavailable_rtq2" />');
					}
				</SCRIPT>
					<span id="delay_quote">
	        	<iframe ID="iframe1" frameborder = "0" HEIGHT="400" width="800" NAME="iframe1" NORESIZE scrolling="no" SRC="<%=delayQuoteUrl%>"  >
				</iframe>
                 </span>
					<span id="etnetQs2"></span>
				<%    
				}else if(!quoteInfo[3].equals("") && quoteInfo[5].equals("AVAIL") && quoteInfo[6].equals("AVAIL") && quoteInfo[7].equals("AVAIL")){
					%>
					
					<SCRIPT language="javascript">
						var status = '<%=status%>';
						if(status!='A'){
							alert('<bean:message key="error.insufficient_rtq_account"/>');
						}
					</SCRIPT>
						<span id="delay_quote">
		        	<iframe ID="iframe1" frameborder = "0" HEIGHT="400" width="800" NAME="iframe1" NORESIZE scrolling="no" SRC="<%=delayQuoteUrl%>"  >
					</iframe>
	                 </span>
						<span id="etnetQs2"></span>
					<%
				}else{
			%>
            <span id="delay_quote">
	        	<iframe ID="iframe2" frameborder = "0" HEIGHT="400" width="800" NAME="iframe2" NORESIZE scrolling="no" SRC="<%=delayQuoteUrl%>"  >
				</iframe>
            </span>
            <span id="etnetQs2"></span>
			<%}}%></td></tr>
			<tr><td>
 <B><span id="contact_info"><%=contactInfo %></span> </B>
<Br><br>
<CENTER><%=copyrightInfo %><BR>
<%=discclaimers %></CENTER>
</td></tr>
</table>
<span id="check" style="display:none">
<iframe ID="iframe3" HEIGHT="570" width="320" NAME="iframe3" NORESIZE SRC="./checkstatus.jsp">
</iframe>
</span>
<form name="form2" id="form2" action="<%=request.getContextPath()%>/changLang.do" method="post">
	<input type="hidden" name="loginID" id="loginID" value="<%=loginId%>">
	<input type="hidden" name="Language" id="Language" value="">
	<input type="hidden" name="custCode" value="<%=custCode%>">
	<input type="hidden" name="RTQChnlUsrID" value="<%=quoteInfo[0]%>">
	<input type="hidden" name="RTQChnlUsrPwd" value="<%=quoteInfo[1]%>">
	<input type="hidden" name="quoteUrl" value="<%=quoteInfo[2]%>">
	<input type="hidden" name="RTQChnlName" value="<%=quoteInfo[3]%>">
	<input type="hidden" name="RTQPrimalURL" value="<%=quoteInfo[4]%>">
	<input type="hidden" name="RTQStatus" value="<%=quoteInfo[5]%>">
	<input type="hidden" name="Status" value="<%=quoteInfo[6]%>">
	<input type="hidden" name="ProdStatus" value="<%=quoteInfo[7]%>">
	<input type="hidden" name="RTQProdName" value="<%=quoteInfo[8]%>">
</form>
<!--form1 : -->
<form name="form1" action="" method="post" target="">
	<input type="hidden" name="RTQChnlUsrID" value="<%=quoteInfo[0]%>">
	<input type="hidden" name="RTQChnlUsrPwd" value="<%=quoteInfo[1]%>">
	<input type="hidden" name="QuoteURL" value="<%=quoteInfo[2]%>">
	<input type="hidden" name="RTQChnlName" value="<%=quoteInfo[3]%>">
	<input type="hidden" name="RTQPrimalURL" value="<%=quoteInfo[4]%>">
	<input type="hidden" name="RTQProdName" value="<%=quoteInfo[8]%>">
	<input type="hidden" name='broker' value="htisec">
	<input type="hidden" name='ver' value="1">
	<input type="hidden" name='status' value="">
</form>
</BODY>
</HTML>
