<%@ page contentType="text/html;charset=UTF-8" %>
<%
response.addHeader("Progma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
response.addDateHeader("Expires",1);

String Language = "E";

String LOGIN = "FALSE";
Cookie cookies[] = request.getCookies();

if (LOGIN != null && !(LOGIN.equals("TRUE"))) {

	String lblFontSize = "1";
	String logo = "../images/zh_TW/logo.gif";
	String lbltopbg = "<img src='../images/top_bg.gif'>";
	String lbldownbg = "<img src='../images/down_bg.gif'>";
	String lblHeading = "<img src='../images/login_heading.gif' border=0>";
	String lblCompanyName = "Haitong International Securities Company Limited";
	String lblMemberLogin = "<img src='../images/member_login.gif' border=0>";
	String lblUserID = "Login ID";
	String lblPassword = "Password";
	String lblSubmitBtn = "Submit";
	String lblResetBtn = "Reset";
	String lblNote1 = "If you have not established an <font color=blue>On-line Trading Account</font> with us yet, please contact :";
	String lblNote2 = "Customer Services Dept.";
	String lblNote3 = "Email : <a href=mailto:csdept@taifook.com>csdept@taifook.com</a> <br> Phone : 2213 8888 <br> Fax : 2530 1689 <br> Address : 25/floor, New World Tower I, 18 Queen's Road Central, Central, Hong Kong.";
	String lblPwdNote ="<font size=-1>(Note: Password is case sensitive)</font>";
	String lblMetaCharset = "<meta http-equiv='Content-Type' content='text/html; charset=ISO8859-1'>";
	String lblEnterAccount = "Please Enter Login ID";
	String lblInvalidAccount = "Invalid Login ID";
	String lblEnterPassword = "Please Enter Password";
	// Added on 20021112 (SL): Company Registration Status in Securities Login Page
	String lblCompRegStatus = "Haitong International Securities Company Limited is a licensed corporation to carry on Type 1 (dealing in securities) and Type 3 (leveraged foreign exchange trading) regulated activities for the purposes of the Securities and Futures Ordinance (Cap.571) (CE No.: AAF806) and an Exchange Participant of The Stock Exchange of Hong Kong Limited. ";

	if (Language.equals("C")) {
	  lblFontSize = "2";
	  logo = "../images/zh_TW/logo.gif";
	  lblCompanyName = "海通國際證券有限公司";
	  lblUserID = "&#30331;&#20837;&#21517;&#31281;";
	  lblPassword = "&#23494;&#30908;";
	  lblSubmitBtn = "&#30906; &#23450;";
	  lblResetBtn = "&#28165; &#38500;";
	  lblNote1 = "&#33509; &#38307; &#19979; &#23578; &#26410; &#22312; &#26412; &#20844; &#21496; &#38283; &#35373; &#32178; &#19978; &#20132; &#26131; &#25142; &#21475;, &#29033; &#35531; &#32879; &#32097; :";
	  lblNote2 = " &#23458; &#25142; &#26381; &#21209; &#37096;";
	  lblNote3 = "&#38651; &#37109; : <a href=mailto:csdept@taifook.com>csdept@taifook.com</a> <br> &#38651; &#35441; : 2213 8888 <br> &#20659; &#30495; : 2530 1689 <br> &#39321; &#28207; &#20013; &#29872; &#30343; &#24460; &#22823; &#36947; &#20013; &#21313; &#20845; &#34399; &#33267; &#21313; &#20843; &#34399; &#26032; &#19990; &#30028; &#22823; &#24264; &#20108; &#21313; &#20116; &#27155; ";
	  lblPwdNote = "<font size=-1>(&#33521; &#25991; &#22823; &#23567; &#26999; &#24517; &#38656; &#30456; &#31526;)</font>";
	  lblMetaCharset = "<meta http-equiv='Content-Type' content='text/html; charset=BIG5'>";
	  lblEnterAccount = "&#35531;&#36664;&#20837;&#30331;&#20837;&#21517;&#31281;";
	  lblInvalidAccount = "&#30331;&#20837;&#21517;&#31281;&#19981;&#27491;&#30906;";
	  lblEnterPassword = "&#35531;&#36664;&#20837;&#23494;&#30908;";
	  lblCompRegStatus = "海通國際證券有限公司，為一間核准從事證券及期貨條例（香港法例第五七一章）中第一類（證券交易）及第三類（槓桿式外匯交易）受規管活動之持牌法團（中央編號:AAF806）及香港聯合交易所有限公司的交易所參與者。";
	} else if (Language.equals("GB")) {
	  lblFontSize = "2";
	  logo = "../images/zh_CN/logo.gif";
	  lblCompanyName = "海通国际证券有限公司";
	  lblUserID = "&#30331;&#20837;&#21517;&#31216;";
	  lblPassword = "&#23494;&#30721;";
	  lblSubmitBtn = "&#30830; &#23450;";
	  lblResetBtn = "&#28165; &#38500;";
	  lblNote1 = "&#33509; &#38401; &#19979; &#23578; &#26410; &#22312; &#26412; &#20844; &#21496; &#24320; &#35774; &#32593; &#19978; &#20132; &#26131; &#25143; &#21475;, &#28902; &#35831; &#32852; &#32476; :";
	  lblNote2 = " &#23458; &#25143; &#26381; &#21153; &#37096;";
	  lblNote3 = "&#30005; &#37038; : <a href=mailto:csdept@taifook.com>csdept@taifook.com</a> <br> &#30005; &#35805; : 2213 8888 <br> &#20256; &#30495; : 2530 1689 <br> &#39321; &#28207; &#20013; &#29615; &#30343; &#21518; &#22823; &#36947; &#20013; &#21313; &#20845; &#21495; &#33267; &#21313; &#20843; &#21495; &#26032; &#19990; &#30028; &#22823; &#21414; &#20108; &#21313; &#20116; &#27004; ";
	  lblPwdNote = "<font size=-1>(&#33521; &#25991; &#22823; &#23567; &#26999; &#24517; &#38656; &#30456; &#31526;)</font>";
	  lblMetaCharset = "<meta http-equiv='Content-Type' content='text/html; charset=GB2312'>";
	  lblEnterAccount = "&#35831;&#36755;&#20837;&#30331;&#20837;&#21517;&#31216;";
	  lblInvalidAccount = "&#30331;&#20837;&#21517;&#31216;&#19981;&#27491;&#30830;";
	  lblEnterPassword = "&#35831;&#36755;&#20837;&#23494;&#30721;";
	  lblCompRegStatus = "海通国际证券有限公司，为一家核准从事证券及期货条例（香港法例第五七一章）中第一类（证券较易）及第三类（杠杆式外汇较易） 受规管活动之持牌法团（中央编号：AAF806）及香港联合交易所有限公司的交易参与者。";
	}
	%>
	<html>
	<head>
	<%=lblMetaCharset%>
	<title><%=lblCompanyName%></title>
	</head>
	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="javascript:document.tf.userid.focus();">
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
  </tr>
</table>
	<BR><BR>
	<table width="540" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
		  <td width="23" valign="top">&nbsp;</td>
		  <td width="1" bgcolor="#000000"><font size="1">&nbsp;</td>
		  <td width="16">&nbsp;</td>
		  <td width="500">
			<form action="manage.jsp" method="POST" name="tf">
			   <table width="415" border="0" cellspacing="0" cellpadding="0">
				<tr>
				  <td width="415"><%=lbltopbg%></td>
				</tr>
				<tr align="center">
				  <td width="415">
					<table width="410" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td width="130"  align="right"><font size="2"><%=lblUserID%></font>&nbsp;</td>
						<td width="78"><font size="2"><input type="text" name="userid" size="10" maxlength=7></font></td>
						<td width="202">&nbsp;</td>
					  </tr>
					  <tr>
						<td width="130" align="right"><font size="2"><%=lblPassword%></font>&nbsp;</td>
						<td width="78"><input type="password" name="password" size="10"></td>
						<td width="202">&nbsp;</td>
					  </tr>
					  <tr>
						<td colspan=2 align="center">
						  <input type="submit" name="submit1" value="<%=lblSubmitBtn%>">
						  <input type="reset" name="reset1" value="<%=lblResetBtn%>">
						</td>
						<td width="202">&nbsp;</td>
					  </tr>
					</table>
				  </td>
				</tr>
				<tr>
				  <td width="415" height="20"><font size="2"></font></td>
				</tr>

				<tr>
				  <td width="415"><%=lbldownbg%></td>
				</tr>
			  </table>
			</form>
		  </td>
		</tr>
		<%-- Company Registration Status Footnote Information --%>
		<tr>
			<td valign=top colspan=4>
				<table width="520" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr>
						<td><font size="2">
							<P>&nbsp;</P>
							<HR>
							<%=lblCompRegStatus%>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</body>
	</html>

<% } else { %>

	<jsp:forward page="./index.jsp"></jsp:forward>

<% } %>
