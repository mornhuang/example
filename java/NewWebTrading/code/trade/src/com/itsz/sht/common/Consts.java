package com.itsz.sht.common;

import java.util.Locale;


/**
 * $Id: Consts.java,v 1.27 2011/04/19 02:35:29 xli Exp $
 * 
 * @Project:portal.head
 * @File:Consts.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public interface Consts {

	/**
	 * 
	 * @Description:
	 * @Author:Cimenon Saint
	 * @Date:2007-5-24
	 */
	public interface Provider {
		final String GROUP = "TAIFOOK";
		final String FIRM = "ITSZ";
	}
	
	public interface ShowMsg{
		final String MSG_KEY = "msgKey";
		final String FORWARD = "forward";
	}
	
	public interface Resource_Path{
	    public static String WEB_RESOURCES_PATH = "com/itsz/web/properties/ApplicationResourcesWEB";
	    public static String WMT_RESOURCES_PATH = "com/itsz/web/properties/ApplicationResourcesWMT";
	    public static String STT_RESOURCES_PATH = "com/itsz/web/properties/ApplicationResourcesSTT";
	    public static String H3G_RESOURCES_PATH = "com/itsz/web/properties/ApplicationResources";
	}

	/**
	 * 
	 * @Description:
	 * @Author:Cimenon Saint
	 * @Date:2007-5-21
	 */
	public interface Global {

		public interface Stock{
			final int DEFAULTSTOCKLENGTH = 4;
			final String CODELENGTHKEY = "STOCK_CODE_LENGTH";
		}
		//boolean value
		public interface Boolean {
			final boolean FALSE = false;
			final boolean TRUE = true;
		}
		//yes and no flag
		public interface Flag{
			final String POSITIVE = "Y";
			final String NEGATIVE = "N";
			final String AGREE = "YES_AGREE";
			final String NO = "NO";
			final String NOTAPPLICABLE = "N/A";
			final String RTQ_NULL_PRICE = "--";
			final String RE_FLAG = "*";
		}
		//gerneral use number
		public interface Number{
			final String SZERO = "0";
			final String SONE = "1";
			final String STWO = "2";
			final String STHREE = "3";
			final int IZERO = 0;
			final int IONE = 1;
			final int ITWO = 2;
			final double NONE = -1;
		}
		
		public interface Password{
			final String PASSWORD_REGEX = "^[A-Za-z0-9]{6,8}$";
		}
		
		public interface Forward {
			final String SUCCESS = "success";
			final String FAILURE = "failure";
			final String ILLEGALCLIENT = "illegalClient";
			final String EXCEPTION = "exception";
		}
		
		public interface Sync {
			final String SYNC = "S";
			final String ASYNC = "A";
		}

		public interface Status{
			final String NORMAL = "NORMAL";
			final String WARN = "WARN";
			final String FATAL="FATAL";
			final String FAILED = "FAILED";
			final String SUCCESS = "SUCCESS";
		}

		public interface Common {
			final String CHANNEL_ID = ".ChannelID";
			final String RESPONSE_RESULT = "message";
			final String defaultLocaleAttributeName = "org.apache.struts.action.LOCALE";
			static final String EXCEPTION_KEY = "org.apache.struts.action.EXCEPTION";
			final String RTQ_PROFILE="RTQAppLoginInfoDTO";
			final String TAIFOOK_SECURITIES = "02";
			final String CHANNEL_CLV = "CHANNEL_CLV";
			final int COOKIE_MAX_AGE=60*60*24*30;
			final String COOKIE_LANGUAGE = "language";
			final String CLV_NAME="CLV";
			final String CHANGELANG_PAGE_PARAM = "page";
			final String LOGIN_PAGE = "loginPage";
			final String MAIN_PAGE = "mainPage";
			final String RISK_PAGE = "riskPage";
			
			final String TEL_HK = "HK_Service_Hotline_Tel";
			final String TEL_MAINLAND = "Mainland_Service_Hotline_Tel";
			
			final String SESSION_AGENT_ID = "agent_id";
			
			final String FORM_PARAM_SEPARATOR_CHAR = ",";
			final String CONFIG_PARAM_ALL = "ALL";
			public static String FUND_DEPOSIT_FILEPATH = "Fund_Deposit_FilePath";
		}
		// language
		public interface Language {
			/**
			 * 
			 * @see PatternB#ENGLISH
			 */
			final String ENG = "ENG";
			/**
			 * 
			 * @see PatternB#CHINESE_SIMPLIFIED
			 */
			final String GB = "GB";
			/**
			 * @see PatternB#CHINESE_TRADITIONAL
			 */
			final String BIG5 = "BIG5";
			final String ABBR = "lang";
			public interface PatternA {
				final String ENGLISH = "EN";
				final String CHINESE_SIMPLIFIED = "GB";
				final String CHINESE_TRADITIONAL = "B5";
			}
			/**
			 * @Description:"BIG5", "ENG", "GB"
			 * @Author:Cimenon Saint
			 * @Date:2008-3-3
			 */
			public interface PatternB {
				final String ENGLISH = "ENG";
				final String CHINESE_SIMPLIFIED = "GB";
				final String CHINESE_TRADITIONAL = "BIG5";
			}
			/**
			 * @Description:"zh_TW","en_US", "zh_CN"
			 * @Author:Cimenon Saint
			 * @Date:2008-3-3
			 */
			public interface PatternC {
				final String ENGLISH = "en_US";
				final String CHINESE_SIMPLIFIED = "zh_CN";
				final String CHINESE_TRADITIONAL = "zh_TW";
			}
		}

	}
	
	public interface Boc {
		//public static final String BOC_URL = "https://its.bochk.com/servlet/Its_s_pg_handler";
		final String MERCH_ID = "P002";
		final String LOCALE_ENG = "ENG";
		final String LOCALE_CHI = "CHI";
		final String RETURN_URL = "http://www.taifook.com";
		final String TIMESTAMP_FORMAT = "yyyyMMddHHmmss";
		final String REQUEST_TOKEN_KEY = "org.apache.struts.taglib.html.TOKEN";
		final String CURRENT_TOKEN_KEY = "com.taifook.mtss.etrade.common.ext.WebSyncAction.CURRENT_TOKEN";
		final String FORM_KEY = "com.taifook.mtss.etrade.common.ext.WebSyncAction.FORM";
		final String FORWARD_KEY = "com.taifook.mtss.etrade.common.ext.WebSyncAction.FORWARD";
		final String ERRORS_KEY = "com.taifook.mtss.etrade.common.ext.WebSyncAction.ERRORS";
	}
	
	public interface Channel{
		//HEXIN STT WEB IVRS J2ME ESERVICE MANGO
		final String WEB = "WEB";
		final String WMT = "WMT";
		final String H3G = "H3G";
		final String J2ME = "J2ME";
		final String STT ="STT";
		final String ZZB ="ZZB";
		final String TDX ="TDX";
		final String IVRS="IVRS";
		final String ESERVICE="ESERVICE";
		final String MANGO="MANGO";
		final String TERMINAL="TERMINAL";
		final String GROUP="WEBGRP";
		final String PS ="PS";
		final String NWEB = "IWEB";
		final String NPS ="IWIN";
		// channel id
		public interface Id{
			final String WEB = "IWEB01";
			final String WMT = "WMT01";
			final String H3G = "H3G001";
			final String KDRAW = "H3G001";
			final String STT = "STT025";
			final String ZZB ="ZZB25";
			final String TDX ="TDX25";
			final String NWEB ="IWEB01";
			final String NPS ="IWIN01";
		}
		public interface SessionControlMode {
			static final String WMTSessionControlMode = "WMT.SessionControlMode";
			static final String STTSessionControlMode = "STT.SessionControlMode";
			static final String H3GSessionControlMode = "H3G.SessionControlMode";
			static final String SECONDKICKOUTFIRST = "1";
			static final String SECONDDISABLEDLOGIN = "2";
		}
		/*
		 * 
		 */
		public interface Sap{
			final String URL = "SAP_URL";
			final String DEFURALURL = "http://202.22.244.84/index.jsp";
		}
	}

	/**
	 * @Description:
	 * @Date:2007-5-22
	 */
	public interface Login {

		/**
		 * @Description:
		 * @Author:swLiu
		 * @Date:2007-5-24
		 */
		public interface Status {
			final String LONGIN_STATUS_NORMAL = "NORMAL";
			final String LONGIN_STATUS_CHGPASS = "CHGPASS";
			final String LONGIN_STATUS_GRACELOGIN = "GRACELOGIN";
			final String LONGIN_STATUS_GRACECNT = "GRACECNT";
			final String LONGIN_STATUS_ISSUED = "ISSUED";
			final String LOGIN_STATUS_INVIDPASS="INVIDPASS";
			final String LOGIN_STATUS_INVIDLOGID = "INVIDLOGID";
		}
		/*
		 * return the info to action or page when use facade
		 */
		public interface ReturnCode {
			final int NORMAL = 0; 				//0--normal
			final int MCS_LOGIN_FAILED = 1; 	//1--mcs login false
			final int RTQ_GET_FAILED = 2; 		//2--get rtq info failed
		}
		
		public interface ReturnForward {
			final int NORMAL = 0; 				//0--normal
			final int MCS_LOGIN_FAILED = 1; 	//1--mcs login false
			final int RTQ_GET_FAILED = 2; 		//2--get rtq info failed
		}

		public interface MessageKey {
			final String CHGPASS = "CHGPASS";
			final String GRACELOGIN = "GRACELOGIN";
			final String GRACECNT = "GRACECNT";
			final String ISSUED = "ISSUED";
			final String LOCKED = "LOCKED";
			final String INVIDPASS = "INVIDPASS";
			final String INVIDLOGID = "INVIDLOGID";
			final String SUSPENDED = "SUSPENDED";
			final String RETRYCNT = "RETRYCNT";
			final String CHAADNORMAL = "CHAADNORMAL";
			final String CHASUSP = "CHASUSP";
			final String INVECERT = "INVECERT";
			final String INVDOCID = "INVDOCID";
		}
	}

	/**
	 * 
	 * @Project:portal.head
	 * @File:Consts.java
	 * @Description:
	 * @Author:Cimenon Saint
	 * @Date:2007-6-6
	 */
	public interface Ecert{
		//auth server
		public interface AuthServer{
			final String ECERT_AUTH_HOST = "ecertAuthHost";
			final String ECERT_AUTH_PORT = "ecertAuthPort";
			final String ECERT_AUTH_URL = "ecertAuthUrl";
		}

		public interface Reason {
			final String EXPIRED_OR_REVOKED = "EXPIRED OR REVOKED";
			final String UNSUPPORTED_CA = "UNSUPPORTED CA";
		}

		public interface SessionKey {
			final String ACCOUNT_ID_KEY = "ECERT_SESSION_ATTR_ACCOUNT_ID_KEY";
			final String PASSWORD_KEY = "ECERT_SESSION_ATTR_PASSWORD_KEY";
			final String HKID_KEY = "ECERT_SESSION_ATTR_HKID_KEY";
			final String ECERT_OBJ_KEY = "ECERT_SESSION_ATTR_ECERT_OBJ_KEY";
		}
	}

	/**
	 * @Description:
	 * @Date:2007-5-21
	 */
	public interface Error {
		//error code -- resource file key
		public interface Code {
			final String ERRORCODE_SYS = "sys001";
			final String SESSION_OVERTIME = "sys002";
			final String CONNECT_LIMITED = "sys003";
			final String CONNECT_MCS = "sys004";
			final String ERRORCODE_CONNECT_RTQ = "sys005";
			final String PARAMETER_MCS = "sys006";
			final String PARAMETER_RTQ = "sys007";
			final String PARAMETER_ESERVICE = "sys008";
			final String ERRORCODE_CONNECT_ESERVICE = "sys009";
			final String CHECK_TRADING_ACCOUNT = "sys010";
			final String ERRORCODE_CHECK_ALLOW_TRADE_FLAG="sys011";
			final String PARAMETER_EMESSAGE = "sys012";
			final String ERRORCODE_CHANNEL_STOPPED = "sys013";
			final String ERRORCODE_TOKEN_INVALID = "sys014";
			final String ERRORCODE_UNKNOWN_EXCEPTION = "sys015";
			final String ITSZ_EXCEPTION = "itszException";
			final String ECERT_EXPIRED_OR_REVOKED = "ecertExpiredOrRevoked";
			final String ECERT_UNSUPPORTED_CA = "ecertUnsupportedCA";
			final String ECERT_AUTHENTICATION = "ecertAuthentication";
			final String LOTSIZENOTMATCH = "sys018";
			final String AONINVALIDQTY = "sys019";
			final String CONNECT_IPO = "sys020";
			final String IPO_TIMEOVER= "sys030";
			final String ISNOT_POCKETIE_CLIENT = "sys021";
			final String WEBSITE_TIMEOUT= "sys022";
			final String STREAM_INFO_UNAVAILABLE = "message.stream_info_unavailable";
			final String SECONDDISABLEDLOGIN = "DISABLEDLOGIN";
			final String SAMECLIENT = "SAMECLIENT";
			//WMT
			final static String  NORMAL = "NORMAL";
			final static String  SUCCESS = "SUCCESS";
			final static String  WARNING = "WARNING";
			final static String  FAILURE = "FAILURE";
			final static String  RELOGIN = "RELOGIN";
			final static String  SHOWMSG = "SHOWMSG";
			final static String  RELOGIN_PARAM_NAME = "RELOGIN_PARAM_NAME";
			final static String  FIRSTLOGIN = "FIRSTLOGIN";
			//login
			final String LOGIN_INVALID_LOGINID = "WEB010001";
			final String LOGIN_INVALID_LOGINPASS = "WEB010002";
			final String NONANNOUNCE = "nonannounce";
			final String ANNOUNCE = "announce";
			//new subcode
			final static String  INVECERT = "INVECERT";
			final static String  INVDOCID = "INVDOCID";
			final static String  SUSPEND = "SUSPEND";
			final static String  GRACECNT = "GRACECNT";
			final static String  SUSPENDED = "SUSPENDED";
			final static String  LOCKED = "LOCKED";
			final static String  ISSUED = "ISSUED";
			final static String  INVIDLOGID = "INVIDLOGID";
			final static String  INVIDPASS = "INVIDPASS";
			final static String  CHGPASS = "CHGPASS";
			final static String  CHASUSP = "CHASUSP";
			final static String  CHAADNORMAL = "CHAADNORMAL";
			final static String  RETRYCNT = "RETRYCNT";
			final static String  GRACELOGIN = "GRACELOGIN";
			//trading
			final String TRAD_PLACE_SUCCESS = "WEB020000";
			final String TRAD_NULL_STOCKCODE = "WEB021001";
			final String TRAD_NULL_PLACEQTY = "WEB021002";
			final String TRAD_NULL_PLACEPRICE = "WEB021003";
			final String TRAD_INVALID_STOCKCODE = "WEB021004";
			final String TRAD_INVALID_PLACEQTY = "WEB021005";
			final String TRAD_INVALID_PLACEPRICE = "WEB021006";
			final String TRAD_INVALID_LOTSIZE = "WEB021007";
			final String TRAD_ERROR_QTYMAXSIZE = "WEB021008";
			final String TRAD_ERROR_NOAO = "WEB021009";
			final String TRAD_ERROR_WRONGPASS = "WEB021010";
			final String TRAD_ERROR_SYSTEM = "WEB021011";
			final String TRAD_PLCAE_EXCEPTION01 = "WEB021012";
			final String TRAD_WARNING_SEPORDER = "WEB021013";
			final String TRAD_NULL_NOTAOPLACE = "WEB021014";
			final String TRAD_INVALID_NOTAOPLACE = "WEB021015";
			final String TRAD_NULL_MODIFYQTY = "WEB021016";
			final String TRAD_INVALID_MODIFYQTY = "WEB021017";
			final String TRAD_TOOMUCH_MODIFYQTY = "WEB021018";
			final String TRAD_ERROR_MODIFYPASS = "WEB021019";
			final String TRAD_MODIFY_ERROR_SYS = "WEB021020";
			final String TRAD_MODIFY_EXCEPTION01 = "WEB021021";
			final String TRAD_MODIFY_SUCCESS = "WEB021022";
			final String TRAD_CANCEL_WRONGPASS = "WEB021023";
			final String TRAD_CANCEL_ERROR_SYS = "WEB021024";
			final String TRAD_CANCEL_EXCEPTION01 = "WEB021025";
			final String TRAD_CANCEL_SUCCESS = "WEB021026";
			final String TRAD_NOALLOW = "WEB021027";
			final String TRAD_ERROR_INSTRCODE = "WEB021028";
			final String TRAD_MODIFY_NEWPRICE = "WEB021029";
			final String TRAD_TOOLESS_MODIFYQTY = "WEB021030";
			final String TRAD_MODIFY_NOTMODIFY = "WEB021031";
			final String TRAD_PASSWORD_NOTMACHRULE = "WEB021032";
			final String TRAD_NO_ALLOWTRADE = "WEB021033";
			final String TRAD_NO_ACCTRADE = "WEB021034";
			final String TRAD_DOUBLE= "WEB080001";
			//query trade 鏌ュ崟
			final String ORDERLIST_ERROR_SYS = "WEB031001";
			final String ORDERLIST_ERROR_EXCEPTION01 = "WEB031002";
			final String ORDERDETAIL_ERROR_SYS = "WEB031003";
			final String ORDERDETAIL_ERROR_EXCEPTION01 = "WEB031004";
			//position 缁撳瓨
			final String SPOSITION_ERROR_SYS = "WEB041001";
			final String SPOSITION_ERROR_EXCEPTION01 = "WEB041002";
			final String APOSITION_ERROR_SYS = "WEB041003";
			final String APOSITION_ERROR_EXCEPTION01 = "WEB041004";
			final String SPOSITION_NORMAL = "WEB041005";
			final String APOSITION_NORMAL = "WEB041006";
			//setting 鍠滃ソ			
			final String CHANGEPASS_SUCCESS = "WEB051000";
			final String TRSPTD_SUCCESS = "WEB051099";
			final String TRSPTD_FAILURE = "WEB051090";
			final String CHANGEPASS_NULL_NEWPASS1 = "WEB051001";
			final String CHANGEPASS_NULL_OLDPASS = "WEB051002";
			final String CHANGEPASS_NULL_NEWPASS2 = "WEB051003";
			final String CHANGEPASS_INVALID_NEWPASS1 = "WEB051004";
			final String CHANGEPASS_INVALID_OLDPASS = "WEB051005";
			final String CHANGEPASS_INVALID_NEWPASS2 = "WEB051006";
			final String CHANGEPASS_ERROR_NEWANDOLDPASS = "WEB051007";
			final String CHANGEPASS_ERROR_PASS = "WEB051008";
			final String CHANGEPASS_ERROR_PASS1 = "WEB051009";
			final String CHANGEPASS_INVALID_OLDPASS_N = "INV_N_PASS:";
			final String CHANGEPASS_INVALID_OLDPASS_N_V = "INV_N_PASS_N";
			final String CHANGEPASS_ERROR_SYS = "WEB051010";
			final String CHANGEPASS_ERROR_EXCEPTION01 = "WEB051011";
			final String TRSPTD_INVALID_PASS = "WEB051012";
			final String TRSPTD_ERROR_PASS = "WEB051013";
			final String TRSPTDS_ERROR_SYS = "WEB051014";
			final String TRSPTD_ERROR_EXCEPTION01 = "WEB051015";
			final String TRSPTD_ERROR_INV_PASS = "INV_PASS";
			final String TRSPTD_ERROR_INV_N_PASS = "INV_N_PASS";
			//rtq
			final String RTQ_NULL_STOCKCODE = "WEB060001";
			final String RTQ_INVALID_STOCKCODE = "WEB060002";
			//mcs
			final String MCS00403 = "MCS00403";	
		}		
	}

	/**
	 * @Description:
	 * @Author:Cimenon Saint
	 * @Date:2007-5-21
	 */
	public interface Log {
		/*
		 * info
		 */
		public interface Info {
			final String COMMON = "commonInfo";
			final String MCS = "mcsInfo";
			final String QS = "qsInfo";
			final String ESERVICE = "esInfo";
			final String IDP = "esInfo";
			final String AGENT = "agentInfo";
		}
		/*
		 * debug
		 */
		public interface Debug {
			final String COMMON = "commonDebug";
			final String MCS = "mcsDebug";
			final String QS = "qsDebug";
			final String STAT = "statDebug";
			final String ESERVICE = "esDebug";
			final String AGENT = "agentInfo";
		}
		/*
		 * error
		 */
		public interface Error {
			final String COMMON = "commonError";
			final String MCS = "mcsError";
			final String QS = "qsError";
			final String STAT = "statError";
			final String ESERVICE = "esError";
			final String AGENT = "agentInfo";
		}
	}

	/**
	 * mcs constants
	 * @Description:
	 * @Author:Cimenon Saint
	 * @Date:2007-5-28
	 */
	public interface Mcs{
		// version id
		public interface VersionId{
			final String VER100 = "Ver1_00";
		}
		// market code
		public interface MarketCode{
			final String AMS3 = "AMS3";
		}
		// Login response info format
		public interface LoginInfoFormat{
			final String FULL = "FULL";
			final String SIMPLE = "SIMPLE";
		}
		//account type
		public interface AccountType {
			final String OFFLINE = "OFFLINE";
			final String BANK = "BANK";
			final String FUTURE = "FUTURE";
			final String CASH_ONLINE = "C,ON";
			final String CASH_OFFLINE = "C,OFF";
			final String MARGIN_ONLINE = "M,ON";
			final String MARGIN_OFFLINE = "M,OFF";
			final String FUTURES_ONLINE = "F,ON";
			final String FUTURES_OFFLINE = "F,OFF";
			final String SHORT_SELL_ONLINE = "S,ON";
			final String SHORT_SELL_OFFLINE = "S,OFF";
			final String IPO_ONLINE = "I,ON";
			final String IPO_OFFLINE = "I,OFF";
			final String SECURITIES_OPTION_ONLINE = "O,ON";
			final String SECURITIES_OPTION_OFFLINE = "O,OFF";
			final String SECURITIES_OPTION = "M,C,I";
			final String MARGIN_OPTION = "M";
		}
		// message id
		public interface MsgId{
			final String Login = "QLOI";
			final String ModifyLogin = "QCID";
			final String ModifyPassword = "QCPA";
			final String EmailFaxUpate = "QEFU";
			final String AccountEnquery = "QACE";
			final String AccountDetail = "QACD";
			final String AccountList = "QALT";
			final String InputOrder = "QOIN";
			final String ModifyOrder = "QOMO";
			final String CancelOrder = "QOCL";
			final String OrderHistory = "QOHI";
			final String OrderRequestInfo = "ORIN";
			final String CalOrderFee = "QCOF";
			final String OrderList = "QORD";
			final String OrderInfo = "OINF";
			final String CalMos = "QCOA";
			final String fundTransfer = "QFXF";
			final String InstrumentSearch = "QINS";
			final String VerifyPassword = "QVPA";
			final String TradeHistory = "QTHI";
			final String InstrumentEnquiry = "QINE";
			final String TransactionProtection = "QXPR";
			final String AccountMovement = "QAMV";
			final String RTQAccess = "QRAC";
			final String WithDraw = "QWIT";
			final String Points = "RPON";
			final String Disclaimer = "QDIS";
			final String OrderFilter = "QOFT";
			final String MOrderFilter = "MROFT";
			final String MCalOrderFee = "MRCOF";
			final String BulkCancelOrder = "QBCL";
			final String PreInputOrder = "QPIN";
			final String StreamRTQ = "RSTR";
			final String QueryPortfolio = "RPFL";
			final String Quote = "RQUT";
			final String AFXNews = "RANU";
			final String Broadcast = "RBRC";
			final String AcDetailWithCashHoldings = "QACC";
			final String TermsAndConditionsForCO = "QCTR";
			final String ProfitAndLossUpdate = "QPLU";
			final String ProfitAndLossEnquiry = "QPLQ";
			final String AccountSummary = "QASU";
			final String StockHoldings = "QSHD";
			final String ProfitAndLossEnquirySP = "RPLS";
			final String EcertRegister = "QECU";
			final String EcertLogin = "QELI";
			final String ExchangeRate = "QEXR";
			final String RTQLoginInfo = "ARTQ";
			final String MLogin = "MRLOI";
			final String MECertLogin = "MRELI";
			final String MAcSummary = "MQASU";
			final String MCashDetail = "MQACC";
			final String ChangeTAndC = "QTER";
			final String ListBasket = "QBKL";
			final String BasketDetail = "QBKQ";
			final String BasketNameUpdate = "QBNU";
			final String BasketSaveAndSend = "QBSS";
			final String ShortRtq = "RSQT";
			final String TradeList = "QTRL";
			final String Margin_Financing_List = "QMFL";
			final String BOC_Transfer = "QBOT";
			final String PPS_Enquiry = "QPPT";
		}

	}

	/**
	 * 
	 * @Description:
	 * @Author:Cimenon Saint
	 * @Date:2007-5-31
	 */
	public interface Qs{
		/*
		 * delay time
		 */
		static final int DELAY_TIME = 15;
		static final int REAL_TIME = 0;
		// max lot
		static final int MAX_LOT = 3000;
		//鏈�澶ф墜鏁�
		static final int MAX_HAND = 30000;
		// sub type
		static final String FREE_QUOT_REAL_TIME="SRFP";   //简单实时报价
		static final String DETAIL_QUOT_DELAY="SDDP";
		static final String S_TYPE_DELAY = "SDSP";//
		static final String S_TYPE_REAL = "SRSP"; 
		static final String S_TYPE_DETAIL_QUOT = "SRDP"; 
		static final String S_TYPE_DETAIL_QUOT_DELAY = "SDDP";
		static final String S_TYPE_MARKET = "SRMD";	
		static final String IMAGE_TYPE = "CHAR";
		static final String SEQN = "3";
		static final String SEQN_DETAIL = "1008";
		static final String QUOT_TYPE = "QUOT";
		static final String ENQ_TYPE = "ENQ";
		static final String ST_STOCK_FREE="2";//
		static final String ST_INDEX_FREE="3";//
		static final String ST_FUTURE_FREE="4";//
		static final String ST_STOCK_MONTH="0"; //
		static final String ST_STOCK_NOMONTH="1";//
		static final String ST_STOCK_HSI=".HSI";
		static final String ST_STOCK_HSIC="HSIc1";
		static final String FOR_HSI="hsiResp";
		static final String FOR_HSIC="hsicResp";
		
		static final String CHART_STOCK_HSI="HSI";
		static final String CHART_STOCK_HHI="HHI";
		static final String CHART_STOCK_CEI="CEI";
		static final String BatchSize_QueryRTQ = "BatchSize_QueryRTQ";
		static final String RTQ_CUSTOMERID="Rtq_CustomerId";
	}

	/**	
	 * 
	 * @Description:
	 * @Author:Cimenon Saint
	 * @Date:2007-5-30
	 */
	public interface Order{
		/*
		 * 
		 */
		public interface State{
			final String TRANSMITTING = "TRANSMITTING";
			final String RECEIVED = "RECEIVED";
			final String QUEUING = "QUEUING";
			final String CANCELLED = "CANCELLED";
			final String REJECTED = "REJECTED";
			final String COMPLETELY_FILLED = "COMPLETELY_FILLED";
			final String PARTIALLY_FILLED = "PARTIALLY FILLED";
			final String PARTIALLY_FILLED_COMPLETED = "PARTIALLY_FILLED_COMPLETED";
		}
		/*
		 * 
		 */
		public interface Type{
			final String ENHANCED_LIMIT = "ENHANCED_LIMIT";//涓嶉�傜敤
			final String AT_AUCTION = "AT_AUCTION";//绔炰环鐩�
			final String SPECIAL_LIMIT = "SPECIAL_LIMIT";//鐗瑰埆闄愪环鐩�
			final String CONDITIONAL = "CONDITIONAL";//鏉′欢鎸囩ず鐩�
		}
		
		public interface ConditionType{
			final String GTE = "GTE";
			final String LTE = "LTE";
		}

		public interface Side{
			final String B = "B";//buy
			final String S = "S";//sell
		}

		public interface ListBook{
			public interface PageSize{
				final String NAME = "PAGESIZE";
				final int DEFAULTSIZE = 8;
			}
		}
	}

	/**
	 * @Description:
	 * @Date:2007-5-21
	 */
	public interface Web {
		final String MESSAGE = "message";

		public interface Forward {
			//to change password page
			final String CHGPWD = "chgpwd";
		}
		
		final String ERRORCODE_TRANSFER_MIN = "F01";
		final String ERRORCODE_TRANSFER_MAX = "F02";

		public interface ECert{
			final String SSL_CLIENT_CERT="SSL_CLIENT_CERT";
		}

		/**
		 * @Description:
		 * @Author:Cimenon Saint
		 * @Date:2007-10-23
		 */
		public interface CLV{
			final String EN="WE25";//鑻辨枃
			final String GB="WG25";//绠�浣�
			final String TC="WT25";//绻佷綋
		}
	}
	
	public interface Wmt {
		final String ALLOWCLIENT_PARAM_NAME="WiFi_Browser_Type";
		final String DEFAULT_ALLOWCLIENT="IEMobile";
		final String MESSAGE = "message";
		final String SPLIT = ",";
		public interface CLV{
			final String EN="ME25";//鑻辨枃
			final String GB="MG25";//绠�浣�
			final String TC="MT25";//绻佷綋
		}
		public interface ILLEGALCLIENT{
			final String ILLEGALCLIENT_MSG = "illegalClientMessage";
			final String PC_CLIENT="PC_CLIENT";
			final String AGENT_BLACK="BLACK_LIST";
			final String NOT_AGENT_WHITE="NOT_WHITE_LIST";
			final String ALLOW_CLIENT="ALLOW_CLIENT";
			final String DEFAULT_PC_CLIENT_URL="";
			final String AGENT_PCCLIENT_TW_URL="AGENT_PCCLIENT_TW_URL";
			final String AGENT_PCCLIENT_EN_URL="AGENT_PCCLIENT_EN_URL";
			final String AGENT_PCCLIENT_CN_URL="AGENT_PCCLIENT_CN_URL";
		}
		/*
		 * commentray
		 * @Date:2008-4-14
		 */
		public interface Commentray{
			final String CN_URL = "TF_CN_COMMENTARY_URL";
			final String TW_URL = "TF_TW_COMMENTARY_URL";
			final String EN_URL = "TF_EN_COMMENTARY_URL";
			final String CHART_RTQ_URL = "CHART_RTQ_URL";
		}
		
		public interface Forward {
			//to change password page
			final String CHGPWD = "chgpwd";
			final String PLACECONFIRM_SHOWMSG = "listOrder.do";
			final String PLACEORDER_SHOWMSG = "bringPrice.do";
			final String ORDERLIST_SHOWMSG = "common.do?forward=main";
			final String ORDERDETAIL_SHOWMSG = "listOrder.do";
			final String QUERYACCOUNT_SHOWMSG = "initAccount.do";
			final String QUERYPOSITION_SHOWMSG = "initPosition.do";
			final String CHGPWD_SHOWMSG = "common.do?forward=mySetting";
			final String TRSPTN_SHOWMSG = "common.do?forward=mySetting";
			final String ANNOUNCE_FORWARD = "announce.do?forward=";
			final String FIRST_TRSPTN_SHOWMSG = "common.do?forward=firstConfirmSetting";
			final String MAIN_SHOWMSG = "common.do?forward=main";
			final String CHGPWD_ERROR_SHOWMSG = "common.do?forward=chgpwd";
			final String RISK_FIRSTLOGIN = "common.do?forward=firstConfirmSetting";
			final String LOGIN_SHOWMSG = "initLogin.do";
			final String VERIFYORDER_SHOWMSG = "verifyPlaceOrder.do";
			final String INITMODIFYORDER_SHOWMSG = "initModifyOrder.do";
			final String INITCANCELORDER_SHOWMSG = "initCancelOrder.do";
			final String ANNOUNCE_SHOWMSG = "announce";
			final String FIRST_CONFIRM_SETTING = "first";
			final String FIRST_LOGIN_RISK = "firstlogin";
			final String RISK = "risk.do";
			final String SHOWRISK = "showRisk";
			final String SHOWTP = "showTP";
			final String SHOWANNOUNCE = "showAnnounce";
			final String SHOWSETTINGPAGE = "showSettingPage";
		}
	}
	/**
	 * @Description:
	 * 		different between rtq and qs
	 * @see Consts.Qs
	 * @Date:2007-5-21
	 */
	public interface RTQ {
		public interface Error{
			final String RTQ_ERROR_MSG="RTQ_ERROR_MSG";
		}
		
		public interface productStatus{
			final String AVAIL = "AVAIL";
			final String UNAVAIL = "UNAVAIL";
		}
		final String SIMPLE_RTQ_DELAY_TIME = "SimpleRtqDelayTime";
		/**
		 * @deprecated 
		 * @see Consts.Qs
		 */
		final String DEFAULT_RTQ_DELAY_TIME = "15";
		final String rtq_unavailable = "rtqunavailable";
		final String insufficient_RTQ = "insufficientRTQ";
		final String Product_AAS = "SSTR_AAST";
		final String Product_Etnet = "SSTR_IQS";
		final String Product_AAS_CN = "SSTR_AAST_CN";
		final String Product_Etnet_CN = "SSTR_IQS_CN";
	}

	/**
	 * @Description:
	 * @Date:2007-7-2
	 */
	public interface Epayment {
		//
		public interface FundTransfer{
			//

			final String AUTHURL= "ePayment_authurl";
			final String BANKCODE1 = "ePayment_bankcode1";
			final String BANKCODE2 = "ePayment_bankcode2";
			final String FORWARDURL = "ePayment_forwardurl";
			final String INTERNAL = "ePayment_internal";
			final String PATH = "ePayment_path";
			
			final String SERVICE_NAME_FUND_TX = "Fund Transfer";
			final String SERVICE_NAME_PPS = "IPPS";
			final String SERVICE_NAME_BOC = "BOC";
			final String SERVICE_STATUS_ACTIVE = "ACTIVE";
			final String SERVICE_STATUS_INACTIVE = "INACTIVE";
			final String SERVICE_STATUS_SUSPEND = "SUSPENDED";
			final String SERVICE_PARAM_MIN_TX_VALUE = "MINVAL";
			final String SERVICE_PARAM_MAX_TX_VALUE = "MAXVAL";
			final String ACTYPE_FUTURES = "F";
			final String ACTYPE_CASH = "C";
			final String ACTYPE_MARGIN = "M";
			final String ACTYPE_IPO = "I";
			final String ACTYPE_OPTIONS = "O";
			final String ACTYPE_SHORT_SELL = "S";
			final String ACTYPE_BANK = "B";
			final String ACTYPE_ONLINE = "ON";
			final String ACTYPE_OFFLINE = "OFF";
			//
			public interface PPS {
				final String LOCALE_ENG = "E";
				final String LOCALE_CHI = "C";
			}
		}
	}

	public interface DateTime{
		public interface Format{
			public interface Pattern{
				final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
			}
		}
	}

	/**
	 * @Description:ps
	 * @Date:2007-5-21
	 */
	public interface Ps {
		/**
		 * @deprecated
		 * @see Consts.Channel.Sap
		 */
		public interface Sap{
			final String URL = "SAP_URL";
			final String URL_DEFAULT_VALUE = "http://202.22.244.84/index.jsp";
		}
		public interface Shk{
			final String URL = "SHK_URL";
			final String URL_DEFAULT_VALUE = "http://www.shkfd.com.hk/taifookbasic/login.php";
			final String SHK_KEY = "SHK_KEY";
			final int SHK_KEY_DEFAULT_VALUE = 119337;
		}
		public interface AHQuote{
			final String AHURL = "AH_URL";
			final String AHLoginId = "AH_LoginId";
			final String AHPwd = "AH_Pwd";
		}
	}

	/**
	 * @Description:3g
	 * @Date:2007-5-21
	 */
	public interface Mobile {
		final String HTTPS_ROOT_URL = "HttpsRootURL";
		/**
		 * production evn configration
		 */
		final String DEFAULT_HTTPS_ROOT_URL = "http://mobile.three.com.hk/checker/taifook_3gportal/";

		public interface Language{
			final String DEFAULT_VALUE = Global.Language.PatternC.CHINESE_TRADITIONAL;
			final String REQUEST_KEY = "language";
		}
		public interface LocaleHelper {
			final Locale DEFAULT_LOCALE_VALUE = Locale.TRADITIONAL_CHINESE;
			/**
			 * @deprecated
			 * @see org.apache.struts.Globals#LOCALE_KEY
			 */
			final String LOCALE_VALUE_KEY = "org.apache.struts.action.LOCALE";
		}
	}

	public interface Profile {
		final static String USER = "User";
	}
}
