/*
 * Created on 2005-3-29
 *
 */
package com.itsz.common;

import java.util.Locale;

/**
 * @author wzzhan
 * 
 */
public class Constants {

	public final static String USER = "User";

	public final static String ADMINUSER = "AdminUser";

	public static final Locale defaultLocale = Locale.TRADITIONAL_CHINESE;

	// public static final Locale defaultLocale =Locale.US;
	public static final String defaultLocaleAttributeName = "org.apache.struts.action.LOCALE";

	public static final String DeviceSetting = "DeviceSetting";

	public static final String DeviceNames = "DeviceNames";

	public static final String UserAgents = "UserAgents";

	public static final String Holidays = "Holidays";

	public static final String VedioLinks = "VedioLinks";

	public static final String DefaultVedioLink = "DefaultVedioLink";

	public static final String defaultNewsTimeFormat = "h:mm a";

	public static final String NewsTimeFormat = "yyyy/MM/dd HH:mm:ss";

	public static final String dateFormat = "yyyy/MM/dd";

	public static final String TimeFormat = "yyyy/MMMM/dd HH:mm:ss";

	public static final String BroadTimeFormat = "yyyy-MM-dd HH:mm:ss";

	public static final String LOCALE_NAME = "org.apache.struts.action.LOCALE";

	public static final String RTQ_ProductID = "RTQ_3G";

	public static final String User_Agent = "User-Agent";

	// error code
	public final static String ERRORCODE_SYS = "3G001";

	public final static String ERRORCODE_SESSION_OVERTIME = "3G0002";

	public final static String ERRORCODE_CONNECT_LIMITED = "3G0003";

	public final static String ERRORCODE_CONNECT_MCS = "3G004";

	public final static String ERRORCODE_CONNECT_RTQ = "3G005";

	public final static String ERRORCODE_PARAMETER_MCS = "3G006";

	public final static String ERRORCODE_PARAMETER_RTQ = "3G007";

	public final static String ERRORCODE_PARAMETER_ESERVICE = "3G008";

	public final static String ERRORCODE_CONNECT_ESERVICE = "3G009";

	public static final String STT_MUST_UPDATE = "sttMustUpdate";

	public static final String STT_HAS_NEW_VERSION = "sttHasNewVersion";

	public static final String ERRORCODE_STOCK_SUSPENDED = "stockSuspended";

	public static final String ERRORCODE_STOCK_INEXISTENCE = "stockInexistence";

	public static final String STREAM_RTQ_NOT_AVAILABLE = "streamRtqNotAvailable";

	public final static String ERRORCODE_CHANNEL_STOPPED = "CHANNEL_STOPPED";

	public final static String ERRORCODE_MCS_INVALIDOLDPSW = "MCS_OLDPASSWORD_ERROR";

	public final static String ERRORCODE_MCS_USEDPSW = "MCS_USEDPASSWORD_ERROR";

	public final static String ERRORCODE_RTQ_TYPE_UNKNOW = "RTQ_TYPE_UNKNOW";

	public final static String ERRORCODE_RTQ_NEED_PURCHASE = "RTQ_NEED_PURCHASE";

	public final static String ERRORCODE_RTQ_CLICK_OVER = "RTQ_CLICK_OVER";

	public final static String ERRORCODE_ESRV_POINT_LACK = "ESRV_POINT_LACK";

	public final static String ERRORCODE_ESRV_BALANCE_LACK = "ESRV_BALANCE_LACK";

	public final static String ERRORCODE_ESRV_DEFAULTACCOUNT_NOTEXIST = "ESRV_DEFAULTACCOUNT_NOTEXIST";

	public static final String ERRORCODE_RTQ_Eligible = "ERRORCODE_RTQ_Eligible";

	public static final String ERRORCODE_RTQ_Pause = "ERRORCODE_RTQ_Pause";

	public final static String ERRORCODE_CHECK_TRADING_ACCOUNT = "CHECK_TRADING_ACCOUNT";

	public final static String ERRORCODE_CHECK_ALLOW_TRADE_FLAG = "CHECK_ALLOW_TRADE_FLAG";

	public final static String ERRORCODE_CHECK_TO_ACCOUNT = "CHECK_TO_ACCOUNT";

	public final static String ERRORCODE_ESRV_REPEAT_WATCHLIST = "ESRV_REPEAT_WATCHLIST";

	public final static String ERRORCODE_ORDERQTY_INCORRECT_MCS = "ORDERQTY_INCORRECT";

	public final static String ERRORCODE_Eserv_SaleAppException = "ERRORCODE_SaleAppException";

	public static final String ERRORCODE_TOKEN_INVALID = "tokenInvalid";

	public static final String ERRORCODE_UNKNOWN_EXCEPTION = "unknownException";

	public static final String ERRORCODE_NO_SECURITIES_ACCOUNT = "NO_SECURITIES_ACCOUNT";

	// status from mcs
	public static final String NEW_OLD_PWD_EQUALS = "newOldPwdEquals";

	public static final String NEW_PWD_NOT_EQUALS = "newPwdNotEquals";

	public static final String INVIDPASS = "invalidPwd";

	public static final String NEW_QTY_INVALID = "newQtyInvalid";

	public static final String ELO_NO_PRICE = "ELONoPrice";

	public static final String CHANNEL_TYPE = "channelType";

	public static final String LOGIN_ID = "loginID";

	public static final String LANGUAGE = "language";

	public static final String AO_HAS_PRICE = "AOHasPrice";

	public static final String MCS_INVALID_OLD_PWD = "invalidOldPwd";

	public static final String MCS_INVALID_NEW_PWD = "invalidNewPwd";

	public static final String ESRV_WATCHLIST_SIZE_EXCEED = "watchlistExceedSize";

	public static final String ESRV_WATCHLIST_REPEATED = "repeatedWatchlist";

	// used by get value from properties file
	public final static String VIEW_PROVIDER = ".ViewProvider";

	public final static String CHANNEL_ID = ".ChannelID";

	public final static String CacheTime_AFXNews = "AFXNewCacheTimeOut";

	public final static String CacheTime_InvestmentCommentDaily = "InvestmentCommentDailyCacheTimeOut";

	public final static String ENHANCED_LIMIT = "ENHANCED_LIMIT";

	public final static String AT_AUCTION = "AT_AUCTION";

	public static final String Success = "Success";

	public final static String CacheTime_WarrentComment = "WarrentCommentCacheTimeOut";

	public final static String DELAY_TIME_DELAYRTQ = "DelayRTQDelayTime";

	public final static String CacheTime_DAILY_SPOTLIGHT = "DailySpotlightCacheTimeOut";

	public final static String CacheTime_WARRANT_NEWS = "WarrantNewsCacheTimeOut";

	public final static String SIMPLE_RTQ_DELAY_TIME = "SimpleRtqDelayTime";

	public static final String DOUBLE_CLICK_INTERVAL = "DoubleClickInterval";

	public final static String CacheTime_PROMOTION = "PromotionCacheTimeOut";

	public final static String UnsupportedTradeDevice = "UnsupportedTradeDevice";

	// common logger name
	// public final static String LOG_INFO_COMMON="common_log_info";
	public final static String LOG_INFO_COMMON = "mcs_info";

	public final static String LOG_DEBUG_COMMON = "common_log_debug";

	public final static String LOG_INFO_MCS = "mcs_info";

	public final static String LOG_DEBUG_MCS = "mcs_debug";

	// public final static String LOG_INFO_RTQ="rtq_info";
	public final static String LOG_INFO_RTQ = "mcs_info";

	public final static String LOG_DEBUG_RTQ = "rtq_debug";

	public final static String LOG_DEBUG_STAT = "stat_debug";

	public final static String LOG_INFO_RTQ_LOG = "rtq_log";

	// public final static String LOG_INFO_ESERVICE="eservice_info";
	public final static String LOG_INFO_ESERVICE = "mcs_info";

	public final static String LOG_DEBUG_ESERVICE = "eservice_debug";

	// langague setting,for mcs langague type
	public final static String LANG_ENG = "ENG";

	public final static String LANG_BIG5 = "BIG5";

	public final static String LANG_GB = "GB";

	public static final String STT = "STT";

	public static final String WEB = "WEB";

	public static final String CHANNELCODE_3G = "H3G";

	public static final String CHARGEFLAG_FREE = "0";

	public static final String CHARGEFLAG_CHARGE = "1";

	public static final String CHARGEFLAG_TFFREE = "2";

	public final static String RTQ_ImageTime = "RTQ_ImageTime";

	public static final int RTQ_INSTRCODE_DIGIT = 4;

	public static final int ESRV_INSTRCODE_DIGIT = 5;

	public final static String LONGIN_STATUS_NORMAL = "NORMAL";

	public final static String ESRV_WARRANT_NEWS_LOG = "LOG";

	public final static String ESRV_WARRANT_NEWS_COMMENTARY = "COMMENTARY";

	public final static String ESRV_WARRANT_NEWS_ALL = "ALL";

	public final static String LONGIN_STATUS_CHGPASS = "CHGPASS";

	public final static String LONGIN_STATUS_GRACELOGIN = "GRACELOGIN";

	public final static String LOGIN_STATUS_INVIDPASS = "INVIDPASS";

	public final static String LOGIN_STATUS_INVIDLOGID = "INVIDLOGID";

	public final static String LONGIN_STATUS_GRACECNT = "GRACECNT";

	public final static String LONGIN_STATUS_ISSUED = "ISSUED";

	public final static String LONGIN_STATUS_SUSPENDED = "SUSPENDED";

	public final static String LONGIN_STATUS_LOCKED = "LOCKED";

	public final static String LONGIN_STATUS_RETRYCNT = "RETRYCNT";

	public final static String COUNTRY_CODE = "countryCode";

	public final static int RTQ_TYPE_MONTH = 1;

	public final static int RTQ_TYPE_PERCLICK = 2;

	public final static String NoContent_AFXNews = "NoContent_AFXNews";

	public final static String NoContent_AFXNewsTitle = "NoContent_AFXNewsTitle";

	public final static String ESRV_SELLABLE_ID = "esrvSellableId";

	public final static String ESRV_AUTOPURCHASE = "autoPurchase";

	public final static String ESRV_PRODUCT_ID = "productID";

	public final static String ESRV_INVALID_AUTOPURCHASE = "productID";

	public final static String ChangePasswordAction = "ChangePWAction";

	public final static String ChangePasswordActionAfterLogin = "ChangePasswordActionAfterLogin";

	public final static String ChangePasswordActionOwn = "ChangePasswordActionOwn";

	public static final String INPUT_PAGE = "input";

	public static final String DOUBLE_CLICK_PAGE = "doubleClick";

	public static final String PASSWORD_REQUESTED = "passwordRequested";

	public static final String PASSWORD_INVALID = "passwordInvalid";

	public static final String INSTRCODE_REQUESTED = "instrCodeRequested";

	public static final String INSTRCODE_INVALID = "instrCodeInvalid";

	public static final String ORDERSIDE_REQUESTED = "orderSideRequested";

	public static final String QUANTITY_REQUESTED = "quantityRequested";

	public static final String QUANTITY_INVALID = "quantityInvalid";

	public static final String PRICE_INVALID = "priceInvalid";

	public static final String PRICE_REQUESTED = "priceRequested";

	public static final String VALIDATION = "validation";

	public static final String INITCLICK = "initClick";

	public static final String REQUEST_STRING = "requestString";

	public static final String MsgID_Points = "RPON";

	public static final String MsgID_Disclaimer = "QDIS";

	public static final String MsgID_OrderFilter = "QOFT";

	public static final String MsgID_BulkCancelOrder = "QBCL";

	public static final String MsgID_PreInputOrder = "QPIN";

	public static final String MsgID_StreamRTQ = "RSTR";

	public static final String MsgID_QueryPortfolio = "RPFL";

	public static final String MsgID_Quote = "RQUT";

	public static final String MsgID_AFXNews = "RANU";

	public static final String MsgID_Broadcast = "RBRC";

	public static final String MsgID_AcDetailWithCashHoldings = "QACC";

	public static final String MsgID_TermsAndConditionsForCO = "QCTR";

	public static final String MsgID_ProfitAndLossUpdate = "QPLU";

	public static final String MsgID_ProfitAndLossEnquiry = "QPLQ";

	public static final String MsgID_AccountSummary = "QASU";

	public static final String MsgID_StockHoldings = "QSHD";

	public static final String MsgID_ProfitAndLossEnquirySP = "RPLS";

	public static final String MsgID_EcertRegister = "QECU";

	public static final String MsgID_EcertLogin = "QELI";

	public static final String MsgID_ExchangeRate = "QEXR";

	public static final String MsgID_RTQLoginInfo = "ARTQ";

	public static final String REALTIME_QUOTATION = "REALTIME";

	public static final String INSTRCODE_NOTEXIST = "rtq.noCode";

	public static final String INSTRNAME = "instrName";

	public static final String IpgClientURL = "IpgClientURL";

	public static final String PPSDBConnectionString = "PPSDBConnectionString";

	public static final String PPSDBUsername = "PPSDBUsername";

	public static final String PPSDBPassword = "PPSDBPassword";

	public static final String afxnewsByStockCodeBIG5 = "afxnewsByStockCodeBIG5";

	public static final String afxnewsByStockCodeGB = "afxnewsByStockCodeGB";

	public static final String afxnewsByStockCodeEN = "afxnewsByStockCodeEN";

	public static final String afxnewsBIG5 = "afxnewsBIG5";

	public static final String afxnewsGB = "afxnewsGB";

	public static final String afxnewsEN = "afxnewsEN";

	public static final String webAfxnewsBIG5 = "webAfxnewsBIG5";

	public static final String webAfxnewsGB = "webAfxnewsGB";

	public static final String webAfxewsEN = "webAfxnewsEN";

	public static final String ProcessMessage = "ProcessMessage";

	public static final String AdminOperate = "AdminOperate";

	public static final String ERROR_INPUT_STIME = "ERROR_INPUT_STIME";

	public static final String ERROR_INPUT_ETIME = "ERROR_INPUT_ETIME";

	public static final String ERROR_BROADCAST_TIME = "ERROR_BROADCAST_TIME";

	public static final String ERROR_BROADCAST_MODIFIED = "ERROR_BROADCAST_MODIFIED";

	public static final String ERROR_BROADCAST_DELETED = "ERROR_BROADCAST_DELETED";

	public static final String ERROR_ADMIN_LOGIN = "ERROR_ADMIN_LOGIN";

	public static final String ERROR_ADMIN_OLDPASSWORD = "ERROR_ADMIN_OLDPASSWORD";

	public static final String ERROR_REDUPLICATE_PARAKEY = "ERROR_REDUPLICATE_PARAKEY";

	public static final String ItsHandlerURL = "ItsHandlerURL";

	public static final String BOCReturnURL = "BOCReturnURL";

	public static final String BOCDBConnectionString = "BOCDBConnectionString";

	public static final String BOCDBUsername = "BOCDBUsername";

	public static final String BOCDBPassword = "BOCDBPassword";

	public static final String DBConnStr = "DBConnStr";

	public static final String DBUserName = "user";

	public static final String DBPassword = "password";

	public static final String spPath = "spPath";

	public static final String spUserId = "spUserId";

	public static final String spKey = "spKey";

	public static final String starsRatingPath = "starsRatingPath";

	public static final String spDisplayItem = "spDisplayItem";

	public static final String STREAM_RTQ_ILLEGAL_LOCALE = "streamRtqIllegalLocale";

	public static final String ECERT_AUTH_HOST = "ecertAuthHost";

	public static final String ECERT_AUTH_PORT = "ecertAuthPort";

	public static final String ECERT_AUTH_URL = "ecertAuthUrl";

	public static final String ECERT_EXPIRED_OR_REVOKED = "ecertExpiredOrRevoked";

	public static final String ECERT_UNSUPPORTED_CA = "ecertUnsupportedCA";

	public static final String ECERT_AUTHENTICATION = "ecertAuthentication";

	public static final String TRADE_HISTORY_GROUP = "tradeHistoryGroup";

	public static final String BROKER_GROUP = "broker";

	public static final String PRICE_GROUP = "price";

	public static final String RTQ_NOT_AVAILABLE = "rtqNotAvailable";

	public static final String RTQ_ILLEGAL_LOCALE = "rtqIllegalLocale";

	public static final String STREAM_RTQ_SubServiceID = "SECSTREAM";

	public static final String SNAPSHOT_SubServiceID = "SECSNAPSHOT";

	public static final String SecAccuontType[] = { "C", "M" };

	public static final String CPS_BANK_LIST = "CPSBankList";

	public static final String CPS_ASK_FOR_KEY = "CPSAskForKey";

	public static final String PROVIDER_QPI_POPUP = "qpifull";

	public static final String PROVIDER_POWER_TICKER = "powerticker";

	public static final String PROVIDER_AASTOCK = "aastock";

	public static final String PROVIDER_HKSTOCK = "hkstock";

	public static final String PROVIDER_TAIFOOK = "taifook";

	public static final String PROVIDER_REUTERS = "reuters";

	public static final String PROVIDER_ESERVICE = "eservice";

	public static final String PROVIDER_EFINET = "Finet";

	public static final String PROVIDER_DELAYED = "delay";

	public static final String PROVIDER_TDX = "TDX";

	public static final String WARRANTS_CODE = "WNT";

	public static final String PROVIDER_ETNET = "ETNet";

	public static final String PROVIDER_QS2 = "qpjunior";

	public static final String PROVIDER_QPILITE="quotepower";

	// /////////////////////////////////////////////////////////
	public static final String ERROR_EXCEPTION = "itszException";

	public static final String ACCOUNTBEAN_KEY = "AccountBean";

	public static final String FUNDTRANSFER_STATUS_DESCRIPTION = "STATUS";

	public static final String FUNDTRANSFER_TIME = "FUNDTRANSFER_TIME";

	public static final String FUNDTRANSFER_STATUS = "FUNDTRANSFER_STATUS";

	public static final String FUNDTRANSFER_SUSPEND_DESC_TRD = "FUNDTRANSFER_SUSPEND_DESC_TRD";

	public static final String FUNDTRANSFER_SUSPEND_DESC_SIM = "FUNDTRANSFER_SUSPEND_DESC_SIM";

	public static final String FUNDTRANSFER_SUSPEND_DESC_ENG = "FUNDTRANSFER_SUSPEND_DESC_ENG";

	public static final String FUNDTRANSFER_ACTIVE_DESC_SJ_TRD = "FUNDTRANSFER_ACTIVE_DESC_SJ_TRD";

	public static final String FUNDTRANSFER_ACTIVE_DESC_SJ_SIM = "FUNDTRANSFER_ACTIVE_DESC_SJ_SIM";

	public static final String FUNDTRANSFER_ACTIVE_DESC_SJ_ENG = "FUNDTRANSFER_ACTIVE_DESC_SJ_ENG";

	public static final String FUNDTRANSFER_ACTIVE_DESC_JR_TRD = "FUNDTRANSFER_ACTIVE_DESC_JR_TRD";

	public static final String FUNDTRANSFER_ACTIVE_DESC_JR_SIM = "FUNDTRANSFER_ACTIVE_DESC_JR_SIM";

	public static final String FUNDTRANSFER_ACTIVE_DESC_JR_ENG = "FUNDTRANSFER_ACTIVE_DESC_JR_ENG";

	public static final String FUNDTRANSFER_MIN_LIMIT = "FUNDTRANSFER_MIN_LIMIT";

	public static final String FUNDTRANSFER_MAX_LIMIT = "FUNDTRANSFER_MAX_LIMIT";

	public static final String FUNDTRANSFER_VALID_PERIOD = "FUNDTRANSFER_VALID_PERIOD";

	public static final String FUNDTRANSFER_DEFAULT_TIMEZONE = "FUNDTRANSFER_DEFAULT_TIMEZONE";

	public static final String ERRORCODE_TRANSFER_MIN = "F01";

	public static final String ERRORCODE_TRANSFER_MAX = "F02";

	public static final String FUNDTRANSFER_STATUS_INACTIVE = "F03";

	public static final String FUNDTRANSFER_STATUS_SUSPEND = "F04";

	public static final String dateFormat1 = "yyyy-MM-dd";

	public static final String dateFormat2 = "yyyy-MM-dd HH:mm:ss:SS";

	// ////////////////////////////////////////////////////////

	// for web
	public static final String USER_ID = "USERID";

	public static final String ACTION_NAME = "ACTION";

	public static final String TIME_SPENT = "TIME_SPENT";

	public static final String ERROR_REF = "ERROR_REF";

	public static final String IPO_APP_PRG = "Application in Progress";

	public static final String IPO_ALL_AVL = "Allotment Result is available";

	public static final String IPO_MIS_CLD = "MIS cancelled";

	public static final String IPO_MIS_RJD = "MIS rejected";

	public static final String IPO_TOTAL = "Total";

	public static final String ESERVICEHOST = "eserviceHost";

	public static final String ESERVICEPORT = "eservicePort";

	private static final String fullClassName = Constants.class.getClass()
	.getName();

	public static final String USER_DTO = fullClassName + "USER_DTO";

	public static final String ERRORCODE_DB_NORECORD = "db_no_record";

	public static final String ERRORCODE_DB_CONNECT = "db_un_connect";

	public static final String ERRORCODE_COMMON = "common_failure";

	public static final String ERRORCODE_PWD = "pwd_failure";

	public static final String ERRORCODE_ONLINE = "online_failure";

	public final static String LOG_INFO_DB = "db_info";

	public final static String LOG_DEBUG_DB = "db_debug";

	public final static String NUM_IPORECORD = "numIpoRecord";

	public static final String GET_DATA_ACTION = "getDataAction";

	public static final String SYNC_DATA = "syncData";

	public static final String GET_DATA_SUCCESS = "getDataSuccess";

	public static final String IQ_SERVER_ERROR = "9001";

	public static final String IQ_RESULT = "result";

	public static final String IQ_PASSPORT = "passport";

	public static final String ETNET_LOGIN_URL = "EtnetLoginUrl";

	public static final String ETNET_SERVER_URL = "EtnetServerUrl";

	public static final String QS2_TOKEN_URL = "QS2TokenUrl";
	
	public static final String TFF_CONN_URL = "TFFUrl";
	
	public static final String TFFND_CONN_URL = "TFFNDUrl";
	

	public static final String RTQ_INFO="RTQInfo";

	public static final String DBConnUrl="url";

	public static final String HTTP_MaxConn_PerHost="maxConnectionsPerHost";

	public static final String HTTP_MaxConn_Total="maxTotalConnections";

	public static final String HTTP_Client_Timeout="httpClientTimeout";

	public static final String CNN_Timeout="cnnTimeout";

	public static final String HTTP_CNN_Timeout="httpCnnTimeout";



}
