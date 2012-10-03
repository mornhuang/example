package com.itsz.sht.common;


public interface Consts {

	
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
	
	
	
	
	public static String BATCH_SIZE="50";
	
	
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
	
	public interface AdminPortal {
		public interface returnResulst {
			final String FAILED = "FAILED";
			final String SUCCESS = "SUCCESS";			
		}
		
		public interface actionType {
			final String CREATE = "CREATE";
			final String CHANGE = "CHANGE";
			final String DELETE="DELETE";
		}
		
		public interface productStatus {
			final String AVAIL = "AVAIL";
			final String UNAVAIL = "UNAVAIL";
		}
		
		public interface userProductStatus {
			final String AVAIL = "AVAIL";
			final String UNAVAIL = "UNAVAIL";
		}
		
		public interface rtqStatus {
			final String AVAIL = "AVAIL";
			final String UNAVAIL = "UNAVAIL";
		}
		
		public interface allowRenewal {
			final String YES = "Y";
			final String NO = "N";
		}
		
		public interface orderNotification {
			final String expireDate = "2049-12-31 23:59:59";
		}
		
		public interface allotStatus {
			final String SUCC="SUCC";
			final String RESERVE="RESERVE";
			final String RESERVEANDAUTO="RESRV_AUTO";
			final String FAIL="FAIL";
		}
		
		public interface misDayEndProcessingFlag {
			final String Y="Y";
			final String N="N";
		}
		
		public interface payStatus {			
			final String FULLYPAID="FULLY-PAID";
			final String PENDING="PENDING";
			final String FAIL="FAIL";
			final String MEMO="MEMO";
		}
		
		public interface requestSYS{
			final String MIS = "MIS";
		}
		
		public interface memeoCode {
			final String MDAE="MDAE";
			final String MDAC="MDAC";
		}
		
		public interface paysStatus {
			final String FULLYPAID="FULLY-PAID";
		}
		public interface userContext {
			final String USERCONTEXT="USER-CONTEXT";
		}
		public interface aclUserProfile{
			 final int DEFAULT_PWD_LENGTH    = 6;
			 final int DEFAULT_PWD_TYPE      = 1;
		}
		public interface memoDebitSystem{
			final String MSSE = "MSSE";      //MSSE
			final String MIS = "MIS";		//MIS
			final String MANUAL = "MANUAL";  //MANUAL，手动方式
			final String MEMO_DEBIT_SYSTEM = "memoDebitSystem";
		}
	}	
	
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
			final String CONNECT_IDP = "sys020";
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
			final String LOGIN_INVALID_LOGINID = "WMT010001";
			final String LOGIN_INVALID_LOGINPASS = "WMT010002";
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

			final String ORDERLIST_ERROR_SYS = "WEB031001";
			final String ORDERLIST_ERROR_EXCEPTION01 = "WEB031002";
			final String ORDERDETAIL_ERROR_SYS = "WEB031003";
			final String ORDERDETAIL_ERROR_EXCEPTION01 = "WEB031004";

			final String SPOSITION_ERROR_SYS = "WEB041001";
			final String SPOSITION_ERROR_EXCEPTION01 = "WEB041002";
			final String APOSITION_ERROR_SYS = "WEB041003";
			final String APOSITION_ERROR_EXCEPTION01 = "WEB041004";
			final String SPOSITION_NORMAL = "WEB041005";
			final String APOSITION_NORMAL = "WEB041006";
		
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
			
			//2010-12-28 zcy
			final String GET_RTQ_PRODUCT_LIST_FAILED = "WEB060003";//查询产品列表失败
			final String PRODUCT_IS_NULL = "WEB060004";//产品不存在
			final String PURCHASE_SERVICE_FAILED = "WEB060005";//购买产品失败
			final String RESERVE_SERVICE_FAILED = "WEB060006";//预定产品失败
			final String CANCEL_RESERVED_USERPRODUCT_IS_NULL = "WEB060007";//取消客户预定时，客户产品不存在
			final String CANCEL_RESERVED_FAILED = "WEB060008";//取消客户预定失败
			final String UPDATE_STATUS_USERPRODUCT_IS_NULL = "WEB060009";//客户自动续期更改时，客户产品不存在
			final String UPDATE_STATUS_FAILED = "WEB060010";//客户自动续期更改失败
			final String GET_USERNOTIFICATION_EMAIL_FAILED = "WEB060011";//获得客户notification email失败
			final String UPDATE_USERNOTIFICATION_EMAIL_FAILED = "WEB060012";//更新客户notification email失败
			final String GET_NOTIFICATIONMEDIA_FAILED = "WEB060013";//获得客户notification失败
			final String UPDATE_NOTIFICATIONMEDIA_IS_NULL = "WEB060014";//客户notification不存在
			final String UPDATE_NOTIFICATIONMEDIA_FAILED = "WEB060015";//更新客户notification失败
			final String GET_NOTIFICATIONMEDIA_LIST_FAILED = "WEB060016";//获得notification列表失败
			final String EXPORT_NOTIFICATIONMEDIA_FAILED = "WEB060017";//导出notification列表失败
			final String PURCHASE_SERVICE_MEMO_DEBIT_FAILED = "WEB060018";//购买产品时，扣款失败
			final String PURCHASE_SERVICE_MIS_IS_DAYEND_PROCESSING = "WEB060019";//购买产品时，MIS正在月结
			final String HAVE_PURCHASED_PRODUCT_FAILED = "WEB060020";//获得客户产品失败
			final String RESERVE_SERVICE_CANNOT_RESERVE_SHK = "WEB060021"; //不能预订SHK
			final String USERPROFILE_IS_NULL = "WEB060022";//客户信息不存在
			final String PRODUCT_UNAVAIL = "WEB060023";//产品已失效
			final String PRODUCT_EXPIRED = "WEB060024";//产品已过期
			final String PURCHASE_SERVICE_MEMO_DEBIT_INSUFFICIENT_BALANCE = "WEB060025";//余额不足
			final String PURCHASE_SERVICE_NO_DEFAULT_DEBIT_ACCOUNT = "WEB060026";//没有有效的Default Debit Account
			final String PURCHASE_SERVICE_USERPRODUCT_EXSITED = "WEB060027";//已经购买产品，并且尚未过期。
			
			//mcs
			final String MCS00403 = "MCS00403";	
			// new  admin  protal
			final String ADMINPROTAL_RTQACCESS_ERROR_NOACCOUNT="ADMINPROTAL070001";
			final String ADMINPROTAL_RTQACCESS_ERROR_NORIGHT="ADMINPROTAL070002";
			final String ADMINPROTAL_RTQACCESS_ERROR_SYSTEMERROR="ADMINPROTAL070003";
			final String ADMINPROTAL_SHKACCESS_ERROR_NORIGHT="ADMINPROTAL070004";
			final String ADMINPROTAL_SHKACCESS_ERROR_SYSTEMERROR="ADMINPROTAL070005";
			final String ADMINPROTAL_SHKACCESS_ERROR_NOPRODUCT="ADMINPROTAL070013";
			final String ADMINPROTAL_SHKACCESS_ERROR_NOUSERPRODUCT="ADMINPROTAL070014";
			final String ADMINPROTAL_SHKACCESS_ERROR_NORTQAPPLICATION="ADMINPROTAL070015";
			final String ADMINPROTAL_ORDERNOTIFACATIONACCESS_SUCCESS="ADMINPROTAL070016";
			final String ADMINPROTAL_DAOERROR="ADMINPROTAL070017";
			final String ADMINPROTAL_ORDERNOTIFACATIONACCESS_ERROR_NORIGHT="ADMINPROTAL070006";
			final String ADMINPROTAL_ORDERNOTIFACATIONACCESS_ERROR_SYSTEMERROR="ADMINPROTAL070007";	
			final String ADMINPROTAL_EXECUTEAUTOENROLLMENT_ERROR_SYSTEMERROR="ADMINPROTAL070008";
			final String ADMINPROTAL_EXECUTEAUTOENROLLMENT_ERROR_FLAG="ADMINPROTAL070009";
			final String ADMINPROTAL_EXECUTEAUTOENROLLMENT_SUCCESS="ADMINPROTAL070010";
			final String ADMINPROTAL_EXECUTEAUTOENROLLMENT_ERROR_NOUSERPROFILE="ADMINPROTAL070011";
			final String ADMINPROTAL_EXECUTEAUTOENROLLMENT_ERROR_USERPROFILEISNOAVAIL="ADMINPROTAL070012";
			final String ADMINPROTAL_EXECUTEAUTOENROLLMENT_NOACCOUNT="ADMINPROTAL070018";
			final String ADMINPROTAL_EXEMEMODEBIT_ERROR_MISDBEXCEPTION="ADMINPROTAL070019";
			final String ADMINPROTAL_EXEMEMODEBIT_ERROR_CCISDBEXCEPTION="ADMINPROTAL070020";
			final String ADMINPROTAL_RELEASERTQACCOUNT_SUCCESS="ADMINPROTAL070021";
			final String ADMINPROTAL_RELEASERTQACCOUNT_FAILD="ADMINPROTAL070022";
			final String ADMINPROTAL_PRODUCT_EXISTED="ADMINPROTAL070023";
			final String ADMINPROTAL_RTQACCESS_ERROR_RTQACC_UNAVAIL="ADMINPROTAL070024";
			final String ADMINPROTAL_SHKACCESS_ERROR_RTQACC_UNAVAIL="ADMINPROTAL070025";
			final String ADMINPROTAL_PRODUCT_CANNOT_CHANGE_PRICE="ADMINPROTAL070026";
			final String ADMINPROTAL_MEMO_DEBIT_SUCCESS="ADMINPROTAL070027";
		}		
	}
	
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
		}

	
		

	}

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
			
			final String TEL_HK = "HK_Service_Hotline_Tel";
			final String TEL_MAINLAND = "Mainland_Service_Hotline_Tel";
			
			final String SESSION_AGENT_ID = "agent_id";
			
			final String FORM_PARAM_SEPARATOR_CHAR = ",";
			final String CONFIG_PARAM_ALL = "ALL";
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
			/**
			 * @deprecated
			 * @see PatternC#ENGLISH
			 */
			final String ENGLISH = "en_US";
			/**
			 * @deprecated
			 * @see PatternC#CHINESE_TRADITIONAL
			 */
			final String TRADITIONAL_CHINESE = "zh_TW";
			/**
			 * @deprecated
			 * @see PatternC#CHINESE_SIMPLIFIED
			 */
			final String SIMPLIFIED_CHINESE = "zh_CN";
			/**
			 * @Description:"B5", "EN", "GB"
			 * @Author:Cimenon Saint
			 * @Date:2008-3-3
			 */
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
}
