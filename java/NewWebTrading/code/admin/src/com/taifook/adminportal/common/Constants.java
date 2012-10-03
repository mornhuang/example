package com.taifook.adminportal.common;

/**
 * <p> * Title: admin_portal *
 * </p>
 * <p> * Description: *
 * </p>
 * <p> * Copyright: Copyright (c) 2006 *
 * </p>
 * <p> * Company: TaiFook itsz *
 * </p>
 * 
 * @author hsli
 * @version 1.0
 */
public final class Constants {

	// common
	public static final String SUCCESS = "success";

	public static final String FAILURE = "fail";

	public static final int DEFAULT_PAGE_SIZE = 20;

	public static final int DEFAULT_SYSTEM_MONITOR_PERIOD = 5; // 5 fen zhong

	public static final boolean DEFAULT_SYSTEM_MONITOR_SWITCH = true;

	public static final int DEFAULT_PAGE_NUMBER = 1;

	public static final int DEFAULT_STRING_LENGTH = 20;

	public static final String SESSION_USER = "session_user";

	public static final String COMMON_FAIL_FORWARD_JSP = "/jsp/fail.jsp";

	// page post the currentpage name
	public static final String CURRENT_PAGE = "currentpage";

	public static final String RESULT = "result";
	public static final String FILTER = "filter";
	public static final String URL_PARAMETER="URLParam";

	// system parameter in the DB ,and it's fiel name;
	public static final String PAGE_SIZE = "pagesize";

	public static final String SYSTEM_MONITOR_PERIOD = "systemmonitorperiod";

	public static final String SYSTEM_MONITOR_SWITCH = "systemmonitorswith";

	// broadcast level
/*	public static final String LEVEL_HIGH = "high";

	public static final String LEVEL_NORMAL = "normal";

	public static final String LEVEL_LOW = "low";*/

	public static final String LEVEL_COMMON="common";
	
	public static final String LEVEL_URGENT="urgent";

	// broadcast type
	public static final String TYPE_MARKET = "market";

	public static final String TYPE_MAINTENANCE = "maintenance";

	public static final String TYPE_PROMOTION = "promotion";
	
	//language 
	public final static String LANG_ENG="ENG";
	public final static String LANG_BIG5="BIG5";
	public final static String LANG_GB="GB";

	// broadcast channels name
	public static final String CHANNEL_NAME_WEB = "WEB,";
	
	public static final String CHANNEL_NAME_WMT = "WMT,";

	public static final String CHANNEL_NAME_STT = "STT,";

	public static final String CHANNEL_NAME_3G = "H3G,";
		

	// onlineuser
	public static final String CHANNEL_CODE_WEB = "WEB";
	
	public static final String CHANNEL_CODE_WMT = "WMT";

	public static final String CHANNEL_CODE_STT = "STT";

	public static final String CHANNEL_CODE_3G = "H3G";

	// system monitor status
	public static final String ERROR_STATUS = "Error";

	public static final String WARNING_STATUS = "Warn";

	public static final String NORMAL_STATUS = "Normal";

	// user aciton id define
	public static final String LOGIN_ACTION = "Login";
	public static final String PW_LOGIN_ACTION = "PW_Login";
	public static final String EC_LOGIN_ACTION = "EC_Login";
	public static final String EC_REG_ACTION = "EC_Reg";

	public static final String LOGOUT_ACTION = "Logout";

	public static final String CHANGE_PASSWORD_ACTION = "ChangePassword";

	public static final String QUERY_ORDER_ACTION = "QueryOrder";

	public static final String QUERY_PRICE_ACTION = "QueryPrice";

	public static final String TEST_ACTION = "Test";

	public static final String INPUT_ORDER_ACTION = "InputOrder";

	public static final String DELAY_QUOTATION_ACTION = "DelayQuotation";

	public static final String REALTIME_QUOTATION_ACTION = "RealQuotation";

	// services name flag
	public static final String MCS_SERVICE = "MCS";

	public static final String ES_SERVICE = "ES";

	public static final String QS_SERVICE = "QS";
	
	//notify channel server data Object
	public static final String NOTIFY_PARAMETER = "NotifyParameter";
	public static final String NOTIFY_BROADCAST = "NotifyBroadcast";	
	public static final String NOTIFY_CHANNELSTATUS = "NotifyChannelStatus";
	public static final String NOTIFY_INVALIDLOGINUSER = "NotifyLoginUser";
	public static final String NOTIFY_QUERYONLINEUSER = "NotifyQueryOnLineUser";
	
	//notify action
	public static final String ADD_ACTION = "AddActon";
	public static final String DEL_ACTION = "DelAction";
	public static final String UPDATE_ACTION = "ModifyAction";
	
	//global error	
	public static final String GLOBAL_ERROR = "GlobalError";
	//back linkers
	public static final String RETURN_BACK_URL = "ReturnBackUrl";
	
	public static final String SOCKET_MESSAGE = "SocketMessage";
	
	public static final String SOCKET_TRANSFER_SUCCESS = "Success";
	public static final String SOCKET_TRANSFER_FAIL = "Fail";
	
	//global temp file path
	public static final String TEMP_FILE_PATH="/temp/";
	
	// user action name

	// other
	public static final String DELIMITER = "-";
	public static final String RIGHT_DELIMITER = ",";
	public static final String FILTER_FIELD_SPLITER = "\r\n";

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
	
	public static class  ParamKey {
		public static final String MANUAL_URL_HOME="MANUAL_URL_HOME";
		public static final String DELETE_ONLINEUSER_INTERVAL_TIME="DELETE_ONLINEUSER_INTERVAL_TIME";
		public static final String DELETE_ONLINEUSER_LIVE_TIME="DELETE_ONLINEUSER_LIVE_TIME";
		public static final String AGENT_BLACK_LIST="AgentBlackList";
		public static final String AGENT_WHITE_LIST="AgentWhiteList";
		public static final String AGENT_PC_BLACK_LIST="AgentPcBlackList";
	}	

}
