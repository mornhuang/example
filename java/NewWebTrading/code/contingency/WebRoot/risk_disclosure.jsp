<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.itsz.Contingency.ClientMain"%>
<%@ page import="java.util.*;"  %>
<%
response.addHeader("Progma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
response.addDateHeader("Expires",1);

String Language = request.getParameter("Language");
String userID = request.getParameter("userid");

if (userID==null) {
	if (Language==null) {
		response.sendRedirect("index.jsp");
	}
	else{
		response.sendRedirect("login.jsp?Language="+Language);
	}
}

ClientMain clientMain = new ClientMain();
String	custCode = clientMain.secUserValidate(userID);

//For Securities RTQ ONLY, if customers input WebChannelLoginID which CANNOT be found in SEC 
//contingency database, please ALLOW them go login and display DELAY QUOTE. 
if (custCode==null||"".equals(custCode.trim())){
	custCode = "0000000" ;
}	
//session.setAttribute("custCode",custCode);
String quoteLang = "";
if ("C".equals(Language)) {
	quoteLang = "tw";
}
else if ("GB".equals(Language)){
	quoteLang = "cn" ;
}
else {
	quoteLang = "en" ;
}

String[] quoteInfo ;
String[] aeInfo;
//For Securities RTQ ONLY, if customers input WebChannelLoginID which CANNOT be found in SEC 
//contingency database, please ALLOW them go login and display DELAY QUOTE.
if ("0000000".equals(custCode)) {
	quoteInfo = new String[]{"","","","","","","","",""} ;
	aeInfo = new String[]{"","",""};
}
else {
	Map map=clientMain.getQuoteInfo1(custCode,quoteLang);
	String uname = "";
	String pwd = "";
	String url = "";
	String provider = "";
	String rtqurl = "";
	String rtqstatus = "";
	String status = "";
	String prodstatus = "";
	String rtqProdName = "";
	if(map.get("provider") != null){
		uname=(String)map.get("username");
		pwd=(String)map.get("password");
		url=(String)map.get("quoteurl");
	    provider=(String)map.get("provider");
	    rtqurl=(String)map.get("rtqurl");
	    rtqstatus = (String)map.get("rtqstatus");
	    status = (String)map.get("status");
	    prodstatus = (String)map.get("prodstatus");
	    rtqProdName = (String)map.get("rtqProdName");
	}
	session.setAttribute("rtq",map);
    quoteInfo=new String[]{uname,pwd,url,provider,rtqurl,rtqstatus,status,prodstatus,rtqProdName};
    //quoteInfo=new String[]{"etnet00353","tfiq1228",url,provider,rtqurl};
		
	//aeInfo = clientMain.getAEInfo(custCode,ClientMain.SEC_COMPANY) ;
    aeInfo = new String[]{"","",""};
}


String formAction = "./main.jsp";
// Display Return Message
String lblCompanyName = "Haitong International Securities Company Limited";
String lblOnline = "Online Securities Trading System";
String lblMetaCharset = "<meta http-equiv='Content-Type' content='text/html; charset=ISO8859-1'>";
String lblFontSize = "2";
String logo = "./images/en_US/sec_h.gif";
String lblNext = "Next";
String lblTitle = "Risk Disclosure Statement";
String lblNote1 = "The account holder is the only authorised user of the Electronic Services.  You, as the account holder, shall be wholly and solely responsible for the confidentiality, security and use of the Access Codes issued to you by our Company.  Our Company shall not be liable for any loss or damage you may suffer as a result of unauthorised using or attempting to use the Electronic Services.";
String lblNote2 = "If you undertake transactions on an electronic trading system, you will be exposed to risks associated with the system including the failure of hardware and software.  The result of any system failure may be that your order is either not executed according to your instruction or is not executed at all.";
String lblNote3 = "Electronic trading facilities are supported by computer-based component system for the order-routing, execution, matching, registration or clearing of trades.  As with all facilities and systems, they are vulnerable to temporary disruption or failure.  Your ability to recover certain losses may be subject to limits on liability imposed by the system provider, the market, the clearing house and or participant firms.  Such limits may vary.";
String lblNote4 = "Due to unpredictable traffic congestion and other reasons, electronic transmission may not be a reliable medium of communication.  Transactions conducted via electronic means are subject to delays in transmission and receipt of your instruction or other information, delays in execution or execution of your instructions at prices different from those prevailing at the time your instructions were given, transmission interruption or blackout.  There are risks of misunderstanding or errors in communication.";
String lblNote5 = "You are strongly advised to review every instruction before entering it as it may not be possible to cancel your instruction once given.";
String lblNote6 = "Our Company does not guarantee the timeliness, sequence, accuracy or completeness of market data or any market information including any information provided to you through the electronic trading system.  Our Company shall not be liable in any way for any loss arising from or caused by (1) any inaccuracy, error in or omission from any such data, information or message; (2) any delay in the transmission or delivery thereof; (3) any suspension or congestion in communication; (4) any unavailability or interruption of any such data, message or information whether due to any act of our Company; or (5) by any forces beyond the control of our Company.";
String lblNote7 = "Disclaimer for Real Time Service";
String lblNote8 = "Haitong International Securities Company Limited, Haitong International On-line Services Limited, the HKEx Information  Services Limited, the Stock Exchange of Hong Kong  Limited, Hong Kong Futures Exchange Limited and their content providers endeavour to ensure the accuracy and reliability of the information provided but do not guarantee its accuracy or reliability and accepts no liability (whether in tort or contract or otherwise) for any loss or damage arising from inaccuracies or omissions.";

String lblNextStep = "Next Step";
String lblPreMktService = "Taifook Pre-opening Trading Service";
String urlPreMktService = "https://www1.taifook.com/english/pre-mkt.html";
String urlOrderType = "https://www1.taifook.com/chi/OrderType.asp?lang=E";
String lblOrderTypeDef = "Definitions of Order Types";

String lblInvalidAccount = "Invalid Login ID";

if ("C".equals(Language)) {

	lblCompanyName = "海通國際證券有限公司";
	lblOnline = "證劵網上交易系統";
	lblMetaCharset = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>";
	logo = "./images/zh_TW/sec_h.gif";
	lblFontSize = "2";
	lblNext = "下一步";
	lblTitle = "&#39080; &#38570; &#25259; &#38706; &#32882; &#26126;";
	lblNote1 = "&#38307; &#19979; &#20316; &#28858; &#36076; &#25142; &#25345; &#26377; &#20154; &#28858; &#38651; &#23376; &#26381; &#21209; &#20043; &#21807; &#19968; &#25480; &#27402; &#21033; &#20351; &#29992; &#32773;&#65292; &#23559; &#26371; &#23565; &#26412; &#20844; &#21496; &#30332; &#32102; &#30340; &#20132; &#26131; &#23494; &#30908; &#20043; &#20445; &#23494;&#12289; &#23433; &#20840; &#21644; &#20351; &#29992; &#33258; &#34892; &#25215; &#25812; &#20840; &#37096; &#36012; &#20219;&#12290; &#26412; &#20844; &#21496; &#19981; &#26371; &#23601; &#38307; &#19979; &#22240; &#20854; &#20182; &#20154; &#20181; &#26410; &#32147; &#25480; &#27402; &#20351; &#29992; &#25110; &#22039; &#35430; &#20351; &#29992; &#38651; &#23376; &#26381; &#21209; &#21487; &#33021; &#36973; &#21463; &#30340; &#20219; &#20309; &#25613; &#22833; &#25110; &#25613; &#23475; &#25215; &#25812; &#36012; &#20219; &#12290;";
	lblNote2 = "&#20504; &#33509; &#38307; &#19979; &#36879; &#36942; &#38651; &#23376; &#20132; &#26131; &#31995; &#32113; &#36914; &#34892; &#20132; &#26131;&#65292;&#38307; &#19979; &#23559; &#26371; &#25215; &#21463; &#31995; &#32113; &#30456; &#38364; &#30340; &#39080; &#38570;&#65292; &#21253; &#25324; &#30828; &#20214; &#21644; &#36575; &#20214; &#30332; &#29983; &#25925; &#38556; &#30340; &#39080; &#38570;&#12290; &#20219; &#20309; &#31995; &#32113; &#30332; &#29983; &#25925; &#38556; &#30340; &#24460; &#26524; &#21487; &#33021; &#20351; &#38307; &#19979; &#30340; &#25351; &#31034; &#19981; &#33021; &#25353; &#20854; &#25351; &#20196; &#22519; &#34892; &#25110; &#32773; &#26681; &#26412; &#27794; &#26377; &#34987; &#22519; &#34892;&#12290;";
	lblNote3 = "&#38651; &#23376; &#20132; &#26131; &#30340; &#35373; &#26045; &#26159; &#20197; &#38651; &#33126; &#32068; &#25104; &#31995; &#32113; &#20358; &#36914; &#34892; &#20132; &#26131; &#25351; &#31034; &#20659; &#36958;&#12289;&#22519; &#34892;&#12289;&#37197; &#23565;&#12289;&#30331; &#35352; &#25110; &#20132; &#26131; &#32080; &#31639;&#12290;&#28982; &#32780;&#65292;&#25152; &#26377; &#35373; &#26045; &#21450; &#31995; &#32113; &#22343; &#26377; &#21487; &#33021; &#26371; &#26283; &#26178; &#20013; &#26039; &#25110; &#22833; &#38728;&#65292;&#32780; &#38307; &#19979; &#23601; &#27492; &#25152; &#33021; &#29554; &#24471; &#30340; &#36064; &#20767; &#25110; &#26371; &#21463; &#21046; &#26044; &#31995; &#32113; &#20379; &#25033; &#21830;&#12289;&#24066; &#22580;&#12289;&#32080; &#31639; &#20844; &#21496; &#21450;&#65295;&#25110; &#21443; &#33287; &#32773; &#21830; &#34399; &#23601; &#20854; &#25152; &#25215; &#25812; &#30340; &#36012; &#20219; &#25152; &#26045; &#21152; &#30340; &#38480; &#21046;&#12290;&#36889; &#20123; &#36012; &#20219; &#38480; &#21046; &#21487; &#20197; &#21508; &#26377; &#19981; &#21516;&#12290;";
	lblNote4 = "&#30001; &#26044; &#28961; &#27861; &#38928; &#35336; &#30340; &#36890; &#35338; &#38459; &#22622; &#25110; &#20854; &#20182; &#21407; &#22240;&#65292; &#38651; &#23376; &#20659; &#36865; &#19981; &#19968; &#23450; &#26159; &#19968; &#31278; &#21487; &#38752; &#30340; &#36890; &#35338; &#26041; &#27861;&#12290; &#36890; &#36942; &#38651; &#23376; &#24037; &#20855; &#36914; &#34892; &#30340; &#20132; &#26131;&#65292; &#22312; &#20659; &#36865; &#21644; &#25509; &#25910; &#38307; &#19979; &#25351; &#31034; &#25110; &#20854; &#20182; &#36039; &#26009; &#26178; &#26371; &#20986; &#29694; &#24310; &#36978;&#65292; &#22312; &#22519; &#34892; &#38307; &#19979; &#25351; &#31034; &#26178; &#26371; &#20986; &#29694; &#24310; &#36978; &#25110; &#20197; &#19981; &#21516; &#26044; &#38307; &#19979; &#30332; &#20986; &#25351; &#31034; &#26178; &#30340; &#20729; &#26684; &#22519; &#34892; &#38307; &#19979; &#30340; &#25351; &#31034;&#65292; &#36890; &#35338; &#35373; &#26045; &#20134; &#26371; &#20986; &#29694; &#25925; &#38556; &#25110; &#20013; &#26039;&#12290; &#38651; &#23376; &#20659; &#36865; &#23384; &#22312; &#36890; &#35338; &#20013; &#20043; &#35492; &#35299; &#25110; &#37679; &#35492; &#30340; &#39080; &#38570;&#12290;";
	lblNote5 = "&#26412; &#20844; &#21496; &#24910; &#37325; &#24314; &#35696; &#38307; &#19979; &#22312; &#36664; &#20837; &#27599; &#20491; &#25351; &#31034; &#20043; &#21069; &#26371; &#21152; &#20197; &#35206; &#26680;&#65292; &#22240; &#28858; &#25351; &#31034; &#19968; &#32147; &#20316; &#20986;&#65292; &#20415; &#21487; &#33021; &#28961; &#27861; &#21462; &#28040;&#12290;";
	lblNote6 = "&#26412; &#20844; &#21496; &#19981; &#26371; &#20445; &#35657; &#24066; &#22580; &#25976; &#25818; &#25110; &#20219; &#20309; &#24066; &#22580; &#36039; &#26009;( &#21253; &#25324; &#36879; &#36942; &#38651; &#23376; &#26381; &#21209; &#25552; &#20379; &#32102; &#38307; &#19979; &#30340; &#20219; &#20309; &#36039; &#26009;) &#30340; &#21450; &#26178; &#24615;&#12289; &#27425; &#24207;&#12289; &#20934; &#30906; &#24615; &#25110; &#23436; &#25972; &#24615;&#12290;&#26412; &#20844; &#21496; &#23565; &#19979; &#36848; &#20107; &#38917; &#25152; &#24341; &#36215; &#25110; &#36896; &#25104; &#20043; &#20219; &#20309; &#25613; &#22833; &#27010; &#19981; &#25215; &#25812; &#20219; &#20309; &#36012; :(1) &#20219; &#20309; &#19978; &#36848; &#25976; &#25818;&#12289; &#36039; &#26009; &#25110; &#20449; &#24687; &#30340; &#19981; &#20934; &#30906; &#24615;&#12289; &#37679; &#35492; &#25110; &#36986; &#28431;&#65307;(2) &#19978; &#36848; &#25976; &#25818;&#12289; &#36039; &#26009; &#25110; &#20449; &#24687; &#20043; &#20659; &#36865; &#25110; &#20132; &#20184; &#24310; &#35492;&#65307;(3) &#36890; &#35338; &#20013; &#26039; &#25110; &#38459; &#22622;&#65307;(4) &#19981; &#35542; &#26159; &#21542; &#30001; &#26044; &#26412; &#20844; &#21496; &#30340; &#34892; &#28858; &#25152; &#33268; &#20043; &#35442; &#31561; &#25976; &#25818;&#12289; &#36039; &#26009; &#25110; &#20449; &#24687; &#30340; &#28961; &#27861; &#25552; &#20379; &#25110; &#20013; &#26039;&#65307; &#25110; (5) &#26412; &#20844; &#21496; &#28961; &#27861; &#25511; &#21046; &#30340; &#22806; &#21147;&#12290;";
	lblNote7 = "即 時 報 價 服 務 之 免 責 聲 明";
	lblNote8 = "海 通 國 際 證 券 有 限 公 司 、 海 通 國 際 電 子 網 上 服 務 有 限 公 司、香 港 交 易 所 資 訊 服 務 有 限 公 司、香 港 聯 合 交 易 所 有 限 公 司 及 香 港 期 貨 交 易 所 有 限 公 司 及 其 資 料 供 應 商 盡 力 確 保 所 提 供 之 資 料 準 確 及 可 靠， 但 不 保 證 該 等 資 料 之 準 確 性 或 可 靠 性， 亦 不 對 任 何 因 資 料 不 確 或 遺 漏 所 引 致 之 損 失 或 損 害 承 擔 任 何 責 任( 不 論 是 民 事 侵 權 行 為 責 任 或 合 約 責 任 或 其 他 責 任)。";
	lblNextStep = "&#19979;&#19968;&#27493;";
	lblPreMktService = "&#22823;&#31119;&#35657;&#21048;&#38283;&#24066;&#21069;&#26178;&#27573;&#30332;&#30436;&#26381;&#21209;";
	urlPreMktService = "https://www1.taifook.com/gb/pre-mkt.html";
	urlOrderType = "https://www1.taifook.com/chi/OrderType.asp?lang=GB";
	lblOrderTypeDef = "&#30332;&#30436;&#31278;&#39006;&#21450;&#23450;&#32681;";

    lblInvalidAccount =  "登入名稱不正確！";
    
} 
else if ("GB".equals(Language)) {

	lblCompanyName = "海通国际证劵有限公司";
	lblOnline = "证劵网上交易系统";
	lblMetaCharset = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>";
	logo = "./images/zh_CN/sec_h.gif";
	lblFontSize = "2";
	lblNext = "下一步";
	lblTitle = "&#39118; &#38505; &#25259; &#38706; &#22768; &#26126;";
	lblNote1 = "&#38401; &#19979; &#20316; &#20026; &#36134; &#25143; &#25345; &#26377; &#20154; &#20026; &#30005; &#23376; &#26381; &#21153; &#20043; &#21807; &#19968; &#25480; &#26435; &#21033; &#20351; &#29992; &#32773;&#65292; &#23558; &#20250; &#23545; &#26412; &#20844; &#21496; &#21457; &#32473; &#30340; &#20132; &#26131; &#23494; &#30721; &#20043; &#20445; &#23494;&#12289; &#23433; &#20840; &#21644; &#20351; &#29992; &#33258; &#34892; &#25215; &#25285; &#20840; &#37096; &#36131; &#20219;&#12290; &#26412; &#20844; &#21496; &#19981; &#20250; &#23601; &#38401; &#19979; &#22240; &#20854; &#20182; &#20154; &#20181; &#26410; &#32463; &#25480; &#26435; &#20351; &#29992; &#25110; &#23581; &#35797; &#20351; &#29992; &#30005; &#23376; &#26381; &#21153; &#21487; &#33021; &#36973; &#21463; &#30340; &#20219; &#20309; &#25439; &#22833; &#25110; &#25439; &#23475; &#25215; &#25285; &#36131; &#20219; &#12290;";
	lblNote2 = "&#20504; &#33509; &#38401; &#19979; &#36879; &#36807; &#30005; &#23376; &#20132; &#26131; &#31995; &#32479; &#36827; &#34892; &#20132; &#26131;&#65292;&#38401; &#19979; &#23558; &#20250; &#25215; &#21463; &#31995; &#32479; &#30456; &#20851; &#30340; &#39118; &#38505;&#65292; &#21253; &#25324; &#30828; &#20214; &#21644; &#36719; &#20214; &#21457; &#29983; &#25925; &#38556; &#30340; &#39118; &#38505;&#12290; &#20219; &#20309; &#31995; &#32479; &#21457; &#29983; &#25925; &#38556; &#30340; &#21518; &#26524; &#21487; &#33021; &#20351; &#38401; &#19979; &#30340; &#25351; &#31034; &#19981; &#33021; &#25353; &#20854; &#25351; &#20196; &#25191; &#34892; &#25110; &#32773; &#26681; &#26412; &#27809; &#26377; &#34987; &#25191; &#34892;&#12290;";
	lblNote3 = "&#30005; &#23376; &#20132; &#26131; &#30340; &#35774; &#26045; &#26159; &#20197; &#30005; &#33041; &#32452; &#25104; &#31995; &#32479; &#26469; &#36827; &#34892; &#20132; &#26131; &#25351; &#31034; &#20256; &#36882;&#12289;&#25191; &#34892;&#12289;&#37197; &#23545;&#12289;&#30331; &#35760; &#25110; &#20132; &#26131; &#32467; &#31639;&#12290;&#28982; &#32780;&#65292;&#25152; &#26377; &#35774; &#26045; &#21450; &#31995; &#32479; &#22343; &#26377; &#21487; &#33021; &#20250; &#26242; &#26102; &#20013; &#26029; &#25110; &#22833; &#28789;&#65292;&#32780; &#38401; &#19979; &#23601; &#27492; &#25152; &#33021; &#33719; &#24471; &#30340; &#36180; &#20607; &#25110; &#20250; &#21463; &#21046; &#20110; &#31995; &#32479; &#20379; &#24212; &#21830;&#12289;&#24066; &#22330;&#12289;&#32467; &#31639; &#20844; &#21496; &#21450;&#65295;&#25110; &#21442; &#19982; &#32773; &#21830; &#21495; &#23601; &#20854; &#25152; &#25215; &#25285; &#30340; &#36131; &#20219; &#25152; &#26045; &#21152; &#30340; &#38480; &#21046;&#12290;&#36825; &#20123; &#36131; &#20219; &#38480; &#21046; &#21487; &#20197; &#21508; &#26377; &#19981; &#21516;&#12290;";
	lblNote4 = "&#30001; &#20110; &#26080; &#27861; &#39044; &#35745; &#30340; &#36890; &#35759; &#38459; &#22622; &#25110; &#20854; &#20182; &#21407; &#22240;&#65292; &#30005; &#23376; &#20256; &#36865; &#19981; &#19968; &#23450; &#26159; &#19968; &#31181; &#21487; &#38752; &#30340; &#36890; &#35759; &#26041; &#27861;&#12290; &#36890; &#36807; &#30005; &#23376; &#24037; &#20855; &#36827; &#34892; &#30340; &#20132; &#26131;&#65292; &#22312; &#20256; &#36865; &#21644; &#25509; &#25910; &#38401; &#19979; &#25351; &#31034; &#25110; &#20854; &#20182; &#36164; &#26009; &#26102; &#20250; &#20986; &#29616; &#24310; &#36831;&#65292; &#22312; &#25191; &#34892; &#38401; &#19979; &#25351; &#31034; &#26102; &#20250; &#20986; &#29616; &#24310; &#36831; &#25110; &#20197; &#19981; &#21516; &#20110; &#38401; &#19979; &#21457; &#20986; &#25351; &#31034; &#26102; &#30340; &#20215; &#26684; &#25191; &#34892; &#38401; &#19979; &#30340; &#25351; &#31034;&#65292; &#36890; &#35759; &#35774; &#26045; &#20134; &#20250; &#20986; &#29616; &#25925; &#38556; &#25110; &#20013; &#26029;&#12290; &#30005; &#23376; &#20256; &#36865; &#23384; &#22312; &#36890; &#35759; &#20013; &#20043; &#35823; &#35299; &#25110; &#38169; &#35823; &#30340; &#39118; &#38505;&#12290;";
	lblNote5 = "&#26412; &#20844; &#21496; &#24910; &#37325; &#24314; &#35758; &#38401; &#19979; &#22312; &#36755; &#20837; &#27599; &#20010; &#25351; &#31034; &#20043; &#21069; &#20250; &#21152; &#20197; &#35206; &#26680;&#65292; &#22240; &#20026; &#25351; &#31034; &#19968; &#32463; &#20316; &#20986;&#65292; &#20415; &#21487; &#33021; &#26080; &#27861; &#21462; &#28040;&#12290;";
	lblNote6 = "&#26412; &#20844; &#21496; &#19981; &#20250; &#20445; &#35777; &#24066; &#22330; &#25968; &#25454; &#25110; &#20219; &#20309; &#24066; &#22330; &#36164; &#26009;( &#21253; &#25324; &#36879; &#36807; &#30005; &#23376; &#26381; &#21153; &#25552; &#20379; &#32473; &#38401; &#19979; &#30340; &#20219; &#20309; &#36164; &#26009;) &#30340; &#21450; &#26102; &#24615;&#12289; &#27425; &#24207;&#12289; &#20934; &#30830; &#24615; &#25110; &#23436; &#25972; &#24615;&#12290;&#26412; &#20844; &#21496; &#23545; &#19979; &#36848; &#20107; &#39033; &#25152; &#24341; &#36215; &#25110; &#36896; &#25104; &#20043; &#20219; &#20309; &#25439; &#22833; &#27010; &#19981; &#25215; &#25285; &#20219; &#20309; &#36131; :(1) &#20219; &#20309; &#19978; &#36848; &#25968; &#25454;&#12289; &#36164; &#26009; &#25110; &#20449; &#24687; &#30340; &#19981; &#20934; &#30830; &#24615;&#12289; &#38169; &#35823; &#25110; &#36951; &#28431;&#65307;(2) &#19978; &#36848; &#25968; &#25454;&#12289; &#36164; &#26009; &#25110; &#20449; &#24687; &#20043; &#20256; &#36865; &#25110; &#20132; &#20184; &#24310; &#35823;&#65307;(3) &#36890; &#35759; &#20013; &#26029; &#25110; &#38459; &#22622;&#65307;(4) &#19981; &#35770; &#26159; &#21542; &#30001; &#20110; &#26412; &#20844; &#21496; &#30340; &#34892; &#20026; &#25152; &#33268; &#20043; &#35813; &#31561; &#25968; &#25454;&#12289; &#36164; &#26009; &#25110; &#20449; &#24687; &#30340; &#26080; &#27861; &#25552; &#20379; &#25110; &#20013; &#26029;&#65307; &#25110; (5) &#26412; &#20844; &#21496; &#26080; &#27861; &#25511; &#21046; &#30340; &#22806; &#21147;&#12290;";
	lblNote7 = "即 时 报 价 服 务 之 免 责 声 明";
	lblNote8 = "海 通 国 际 证 劵 有 限 公 司 、 海 通 国 际 电 子 网 上 服 务 有 限 公 司、香 港 交 易 所 信 息 服 务 有 限 公 司、香 港 联 合 交 易 所 有 限 公 司 及 香 港 期 货 交 易 所 有 限 公司 及 其 资 料 供 货 商 尽 力 确 保 所 提 供 之 资 料 准 确 及 可 靠，但 不 保 证 该 等 资 料 之 准 确 性 或 可 靠 性，亦 不 对 任 何 因 资 料 不 确 或 遗 漏 所 引 致 之 损 失 或 损 害 承 担 任 何 责 任 (不 论 是 民 事 侵 权 行 为 责 任 或 合 约 责 任 或 其 它 责 任)。";
	lblNextStep = "&#19979;&#19968;&#27493;";
	lblPreMktService = "&#22823;&#31119;&#35777;&#21048;&#24320;&#24066;&#21069;&#26102;&#27573;&#21457;&#30424;&#26381;&#21153;";
	urlPreMktService = "https://www1.taifook.com/gb/pre-mkt.html";
	urlOrderType = "https://www1.taifook.com/chi/OrderType.asp?lang=GB";
	lblOrderTypeDef = "&#21457;&#30424;&#31181;&#31867;&#21450;&#23450;&#20041;";

    lblInvalidAccount = "登入名称不正确！";

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
<!--Popup Window-->
<SCRIPT LANGUAGE="JavaScript">
<!--//
//var ADPopup1 = window.open('./promo/popup1.jsp?lang=<%=Language%>', "ADPopup1", "width=200,height=120,location=0,toolbar=0,resizable=0,scrollbars=0,status=0,top=90,left=120");

function OpenBrowserWindow(theURL,WinName,Features) {
	window.open(theURL,WinName,Features);
}
//-->
</script>
<!--Popup Window-->
</HEAD>
<BODY BGCOLOR="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="javascript:document.getElementById('next').focus();">
<table border=0 width=100% cellspacing=0 cellpadding=0>
  <tr>
    <td align="left" width="170"><img src="./images/zh_TW/logo.gif" alt="<%=lblCompanyName%>"></td>
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
    <td align="right" width="545"><img src="<%=logo%>" alt="<%=lblOnline%>"></td>
  </tr>
</table>
<Br>
<center>
<table border=0 cellspacing=0 cellpadding=5 width=700>
<form name="form1" method="post" action="<%=request.getContextPath()%>/login.do" >
	<input type="hidden" name="loginID" value="<%=userID%>">
	<input type="hidden" name="Language" value="<%=Language%>">						  
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
	<tr>
	<Td><font color=005bac size=3><b><%=lblTitle%>  </td><Td align=right><INPUT id="next" TYPE="submit" VALUE="<%=lblNext %>"></td>
</tr>
</form>
<tr>
	<td colspan=2>
	<ol>
<p align=justify>
<li><%=lblNote1%></li>
</p>
<p align=justify>
<li><%=lblNote2%></li>
</p>
<p align=justify>
<li><%=lblNote3%></li>
</p>
<p align=justify>
<li><%=lblNote4%></li>
</p>
<p align=justify>
<li><%=lblNote5%></li>
</p>
<p align=justify>
<li><%=lblNote6%></li>
</p>
</ol>
<B><%=lblNote7 %> </B>
<p align=justify>
<%=lblNote8 %>
</p>

	</td>
</tr>
</table>
<Br>
<Br>
</BODY>
</HTML>
