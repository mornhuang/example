<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.itsz.common.Constants" %>
<%@ page import="com.itsz.util.LangUtil" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
    response.addHeader("Progma", "No-cache");
    response.addHeader("Cache-Control", "no-cache");
    response.addDateHeader("Expires",1);

    String Language = (String)request.getAttribute("language");

    if (Language==null || "".equals(Language)){
		Language = "C" ;		
    } 
    LangUtil.saveLocale2Session(session,Language);
  
	String lbltopbg = "<img src='./images/top_bg.gif'>";
	String lbldownbg = "<img src='./images/down_bg.gif'>";
	String lblHeading = "<img src='./images/login_heading.gif' border=0>";
	String lblMetaCharset = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>";
	
	String lblFontSize ,logo ,logoSec, lblCompanyName ,lblOnline ,lblMemberLogin ,lblLogon ,lblUserID ,lblUserIDImg ,lblPassword ,lblPasswordImg ,lblSubmitBtn ,lblResetBtn ,notesImg ,lblNote1 ,lblNote2 ,lblNote3 ,lblCaution ,lblPwdNote ,lblEnterAccount ,lblInvalidAccount ,lblEnterPassword ,lblCompRegStatus ;
	
	if (Language.equals("C")) {
	  lblFontSize = "2";
	  logo = "./images/zh_TW/logo.gif";
	  logoSec = "./images/zh_TW/sec_h.gif";
	  lblCompanyName = "海通國際證券有限公司";
	  lblOnline = "自主網交易系統";
	  lblMemberLogin = "./images/zh_TW/login_h.gif";
	  lblLogon = "客戶登入";
	  lblUserID = "登入名稱";
	  lblUserIDImg="./images/zh_TW/login_name.gif";
	  lblPassword = "密碼";
	  lblPasswordImg="./images/zh_TW/pwd.gif";
	  lblSubmitBtn = "確 定";
	  lblResetBtn = "清 除";
	  notesImg="./images/zh_TW/notes.gif";
	  lblNote1 = "若 閣 下 對 自 主 網 交 易 系 統 有 任 何 疑 問 , 煩 請 聯 絡 客 戶 服 務 部 或 閣 下 的 經 紀 人。";
	  lblNote2 = "客戶服務熱線";
	  lblNote3 = "電 郵 : <a href=mailto:csdept@htisec.com>csdept@htisec.com</a><br>電 話 : (852) 3583 3388 <br>傳 真 : (852) 2530 1689 <br>";
	  lblCaution = "注 意： 為 免 無 法 使 用 部 分 功 能， 登 入 前 請 先 暫 時 關 閉 電 腦 內 已 裝 置 彈 出 型 視 窗 攔 截 功 能(如： WinXP SP2 IE 內					快 顯 封 鎖 程 式、Yahoo 工 具 列、Google 工 具 列、MSN 工 具 列 等)。";
	  lblPwdNote = "<font size=-1>(英 文 大 小 楷 必 需 相 符)</font>";
	  //lblMetaCharset = "<meta http-equiv='Content-Type' content='text/html; charset=BIG5'>";
	  
	  lblCompRegStatus = "海通國際證券有限公司，為一間核准從事證券及期貨條例（香港法例第五七一章）中第一類（證券交易）及第三類（槓桿式外匯交易）受規管活動之持牌法團（中央編號:AAF806）及香港聯合交易所有限公司的交易所參與者。" ;
	
	} else if (Language.equals("GB")) {
	  lblFontSize = "2";
	  logo = "./images/zh_CN/logo.gif";
	  logoSec = "./images/zh_CN/sec_h.gif";
	  lblCompanyName = "海通国际证券有限公司";
	  lblOnline = "自主网交易系统";
	  lblMemberLogin = "./images/zh_CN/login_h.gif";
	  lblLogon = "客户登入";
	  lblUserID = "登入名称";
	  lblUserIDImg="./images/zh_CN/login_name.gif";
	  lblPassword = "密码";
	  lblPasswordImg="./images/zh_CN/pwd.gif";
	  lblSubmitBtn = "确 定";
	  lblResetBtn = "清 除";
	  notesImg="./images/zh_CN/notes.gif";
	  lblNote1 = "若 阁 下 对 自 主 网 交 易 系 统 有 任 何 疑 问 , 烦 请 联 络 客 户 服 务 部 或 阁 下 的 经 纪 人。";
	  lblNote2 = "客户服务热线";
	  lblNote3 = "电 邮 : <a href=mailto:csdept@htisec.com>csdept@htisec.com</a><br>电 话 : (852) 3583 3388 <br>传 真 : (852) 2530 1689 <br>";
	  lblCaution = "注 意： 为 免 无 法 使 用 部 分 功 能， 登 入 前 请 先 暂 时 关 闭 计 算 机 内 已 装 置 弹 出 型 窗 口 拦 截 功 能 (如： WinXP SP2 IE 内 快 显 封 锁 程 序、Yahoo 工 具 列、Google 工 具 列、MSN 工 具 列 等)。";
	  lblPwdNote = "<font size=-1>(英 文 大 小 楷 必 需 相 符)</font>";
	  lblCompRegStatus = "海通国际证券有限公司，为一家核准从事证券及期货条例（香港法例第五七一章）中第一类（证券较易）及第三类（杠杆式外汇较易） 受规管活动之持牌法团（中央编号：AAF806）及香港联合交易所有限公司的交易参与者。" ;
	
	} else {
		lblFontSize = "1";
		logo = "./images/zh_TW/logo.gif";
		logoSec = "./images/en_US/sec_h.gif";
		lblCompanyName = "Haitong International Securities Company Limited";
		lblOnline = "Iweb Trading System";
		lblMemberLogin = "./images/en_US/login_h.gif";
		lblLogon = "Customer Login";
		lblUserID = "Login ID";
		lblUserIDImg="./images/en_US/login_name.gif";
		lblPassword = "Password";
		lblPasswordImg="./images/en_US/pwd.gif";
		lblSubmitBtn = "Submit";
		lblResetBtn = "Reset";
		notesImg="./images/en_US/notes.gif";
		lblNote1 = "If you have any enquiries, please kindly contact our Customer Service or your Account Executive. ";
		lblNote2 = "Customer Services Hotline";
		lblNote3 = "Email : <a href=mailto:csdept@htisec.com>csdept@htisec.com</a> <br> Phone : (852) 3583 3388 <br> Fax : (852) 2530 1689 <br>";
		lblCaution = "Caution: Please temporarily disable all installed 'Pop-up Blocker' (eg. WinXP SP2 IE, Yahoo Toolbar, Google Toolbar, MSN Toolbar, etc) before login, otherwise some functions may not be able to work properly.";
		lblPwdNote ="<font size=-1>(Note: Password is case sensitive)</font>";
		//lblMetaCharset = "<meta http-equiv='Content-Type' content='text/html; charset=ISO8859-1'>";
		
		lblCompRegStatus = "Haitong International Securities Company Limited is a licensed corporation to carry on Type 1 (dealing in securities) and Type 3 (leveraged foreign exchange trading) regulated activities for the purposes of the Securities and Futures Ordinance (Cap.571) (CE No.: AAF806) and an Exchange Participant of The Stock Exchange of Hong Kong Limited. " ;
	
	}
	
%>
<HTML>
<HEAD>
<TITLE><%=lblCompanyName%></TITLE>
	<meta http-equiv="Content-Type" content="text/html; charset=big5">
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
	</style>

	<SCRIPT LANGUAGE="JavaScript" SRC="./ctrl/DisableRightClick.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript">
		function checkform(form) {
			var sURL = "./showErrorMsg.jsp";
			var sFeatures = "dialogWidth:200px; dialogHeight:150px;status:no;resizable:yes" ;
			var vArguments = null ;
			
			if (form.userid.value == "") {				
				sURL = sURL + "?Language=<%=Language%>&ErrMsg=EnterAccount" ;
				var vReturnValue = window.showModalDialog(sURL , vArguments , sFeatures) ;
				form.userid.focus();
				return false;
			} 
			if (form.password.value == "") {				
				sURL = sURL + "?Language=<%=Language%>&ErrMsg=EnterPasswd" ;
				var vReturnValue = window.showModalDialog(sURL , vArguments , sFeatures) ;
				form.password.focus();
				return false;
			}
			if(!validatorUserId(form.userid.value)){
				sURL = sURL + "?Language=<%=Language%>&ErrMsg=InvalidAccount";
				var vReturnValue = window.showModalDialog(sURL , vArguments , sFeatures) ;
				form.userid.focus();
				return false;
			}
			if (!isAlphaNumeric(form.password.value) || form.password.value.length<6 || form.password.value.length>8){
				sURL = sURL + "?Language=<%=Language%>&ErrMsg=InvalidPassword" ;
				var vReturnValue = window.showModalDialog(sURL , vArguments , sFeatures) ;
				form.password.focus();
				return false;			
			}
			form.password.value = "";
			return true;
		}
	</SCRIPT>
<script type="text/javascript">
<%
	String result = (String)request.getAttribute("result");
	if(result != null){
%>
	alert('<bean:message key="message.general.sessionKickedOff" />');
<%}%>
</script>
</HEAD>
<BODY BGCOLOR="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table border=0 width=100% cellspacing=0 cellpadding=0>
  <tr>
    <td align="left" width="170"><img src="<%=logo%>" alt="<%=lblCompanyName%>"></td>
    <td width="*">
      <table width=100% border=0 cellspacing=0 cellpadding=0>
        <tr bgcolor=ffffff>
          <td height=73 width=*>&nbsp;</td>
        </tr>
        <tr bgcolor=005ca5>
          <td height=5></td>
        </tr>
      </table>
    </td>
    <td align="right" width="545"><img src="<%=logoSec%>" alt="<%=lblOnline%>"></td>
  </tr>
</table>
<Br>
<center>
<table border=0 cellpadding=0 cellspacing=0>
<tr>
	<Td><img src="<%=lblMemberLogin%>" name=login alt="<%=lblLogon%>" usemap="#login_h" border="0"></td>
</tr>
<tr>
	<Td background="./images/zh_TW/login_bg.gif" valign=center align=center width=469 height=94>
      <FORM method="post" action="risk_disclosure.jsp?Language=<%=Language%>" onsubmit="return checkform(this);">
		<table border=0 cellpadding=4>
		<tr>
			<Td align=right><img src="<%=lblUserIDImg%>" alt="<%=lblUserID%>"></td>
			<td><INPUT TYPE="text" name="userid" size=12></td>
			<td></td>
		</tr>
		<tr>
			<Td align=right><img src="<%=lblPasswordImg%>" alt="<%=lblPassword%>"></td>
			<td><INPUT TYPE="password" name="password" size=12></td>
			<td><INPUT TYPE="submit" value="<%=lblSubmitBtn%>">&nbsp;&nbsp;&nbsp;<INPUT TYPE="reset" value="<%=lblResetBtn%>"></td>
		</tr>
		</table>
      </FORM>
	</td>
</tr>
</table>
<Br>
<table border=0 width=469>
<tr>
	<Td><%=lblNote1%><Br><Br>
<%=lblNote2%><BR>
<%=lblNote3%>

<!-- If you have any enquiries, please kindly contact our Customer Service or your Account Executive. <Br><Br>
Customer Service Dept<BR>
Email : <a href="mailto:csdept@htisec.com">csdept@htisec.com</a><BR>
Tel :  (852) 3583 3388 <BR>
Fax :  (852) 2530 1689 <BR> -->
	</td>
</tr>
</table>
<Br>
<table border=0 width=469 cellspacing=0 cellpadding=2>
<tr>
	<Td><img src="<%=notesImg%>"></td>
</tr>
<tr bgcolor=FFFFD2>
	<Td><%=lblCaution%></td>
</tr>
<tr>
	<td><BR><BR>
	<hr>
	<%=lblCompRegStatus%>
	</td>
</tr>
</table>
<Br>
</BODY>
</HTML>

