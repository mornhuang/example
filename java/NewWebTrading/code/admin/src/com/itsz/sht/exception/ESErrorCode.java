package com.itsz.sht.exception;

/**
 * <p>Title: eService-3G</p>
 * <p>Description: eService extention for 3G Portal Project</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: ITSZ</p>
 * @author yhliu
 * @version 1.0
 */

public interface ESErrorCode {
	
    int getErrCode();
    
    // below 1000 are system exceptions, above 1000 are application exceptions.
    int ERR_UNKNOWN = 0;
    int ERR_ILLEGAL_ARGUMENT = 1;
    int ERR_OBJ_NOTFOUND = 2;
    int ERR_DUPLICATE_PK = 3;

    //base module exceptions
    int BASE_ERR = 100;
    int BASE_INSUFFICIENT_UNIT = 101;
    int NOT_DELETEABLE = 102;
    int ALREADY_EXISTED=103;

    // user module exceptions
	// replaced by USR_PROFILE_ERR ,USR_PROFILE_NOT_FOUND
	// int USER_ERR = 200;
	// int USER_NOTFOUND = 201;

    //product module exceptions
    int PRD_ERR = 300;
    int PRD_INSUFFICIENT_PRD = 301;
	int PRD_INACTIVE = 302;
    int PRD_NOTFOUND = 303;
    int PRD_PRICE_NOTFOUND = 304;
    int PRD_PACK_NOTFOUND = 305;
    int PRD_CHANNEL_NOTFOUND = 306;
    int PRD_SUSPEND = 307;
    //user product module exceptions
    int USRPRD_ERR = 400;
    int USRPRD_INSUFFICIENT_UNIT = 401;
    int USRPRD_SET_ERR = 402;
    int USRPRD_NEED_SUBSCRIBE = 403;
    int USRPRD_INACTIVE = 404;
    int USRPRD_NOTFOUND = 405;
	int USRPRD_ITEM_NOTFOUND = 406;

    //order module exceptions
    int ORDER_ERR = 500;
    int ORDER_NOTFOUND = 501;
    int ORDER_ITEM_NOTFOUND = 502;
    int ORDER_ALREADY_ALLOTED=503;
    int ORDER_NOT_ELIGIBLE = 504;

    //payment module exceptions
    int PAY_ERR = 600;
    int PAY_BALANCE_LACK = 601;
    int PAY_POINT_BALANCE_LACK = 602;
    int PAY_MISACCOUNT_LACK = 603;
    int PAY_NOTFOUND = 604;
    int PAY_MISACCOUNT_BALANCE_LACK = 605;

    //sale module exceptions
    int SALE_ERR = 700;
	int SALE_STOCK_NOT_ENOUGH = 701;
	int SALE_PRODUCT_DUPLICATE_IN_ORDER = 702;
	int SALE_PRODUCT_SUBSCRIBED = 703;
	int SALE_CATALOG_DUPLICATE_IN_ORDER = 704;
	int SALE_CATALOG_SUBSCRIBED = 705;

    //access module exceptions
    int ACCESS_ERR = 800;
	int ACCESS_ILLEGALIP = 801;
	int ACCESS_GET_ACCOUNT_FAIL = 802;
        int ACCESS_DUPLICATE_REFERNO=803;

    //mis module exceptions
    int MIS_ERR = 900;
    int MIS_USR_NOTFOUND = 901;
    int MIS_ACC_CLOSED = 902;
    int MIS_DAYEND_PROCESSING=903;

    //service item exceptions
    int SVCS_ERR = 1000;
    //AFX News Error
    int SVCS_AFXNEWS_ERROR=1010;
    int SVCS_AFXNEWS_NOTFOUND=1011;
    //warrant commentary error
    int SVCS_WARRANTS_ERROR=1020;
    
    int USR_PROFILE_ERR=1100;
    int USR_PROFILE_NOT_FOUND=1101;
    int USR_PROFILE_CREATE_ERR=1102;
    
    int USR_PROFILE_EMAIL_NOT_FOUND=1110;
    
    int USR_LOGIN_ERR = 1200;
    int USR_LOGIN_CAN_NOT_FOUND_BY_LOGINID=1210;
    int USR_LOGIN_CAN_NOT_FOUND_BY_USRID=1211;
    int USR_LOGIN_ACC_DEACTIVATED=1220;
    int USR_LOGIN_ACC_LOCKED=1221;
    int USR_LOGIN_ACC_ISSUED=1222;
    int USR_LOGIN_ACC_STATUS_ERR=1226;
    
    //webservice module exceptions
    int AUTH_LOGIN_EXCEPTION=1300;
    int ACCESS_RTQ_EXCEPTION = 1301;
    int INSUFFICIENT_ACCESS_UNITS_EXCEPTION = 1302;
    int ILLEGAL_ACCESSIP_EXCEPTION=1303;
    int ACCOUNT_NOT_FOUND_EXCEPTION=1304;
    int POINT_EXCEPTION=1305;
    int NOT_FOUND_AVAIL_REDEMPTION=1306;
    int NOT_FOUND_SUBSCRIBED_SERVICE=1307;
    int NOT_PURCHASE_IN_ADVANCESERVICE=1308;
    
    //Data Sync module exceptions
    int CLIENT_COMPOSITE_KEY_NULL=1400;
    int ENTITY_COMPOSITE_KEY_NULL=1401;
    int ENTITY_NAME_COMPOSITE_KEY_NULL=1402;
    int PHONE_COMPOSITE_KEY_NULL=1403;
    int ADDRESS_COMPOSITE_KEY_NULL=1404;
    int LOGINRPROFILE_COMPOSITE_KEY_NULL=1405;
    
    //QuotationServer module exceptions
    int QS_ErrorMsg=1500;
    int QS_LATEST_UPDATE_TIME_NULL=1501;
    int QS_LATEST_UPDATE_TIME_FORMAT_ERROE=1502;
    
    //CCIS WS API module exceptions
    int CCIS_ErrorMsg=1600;
    int CCIS_GET_ACCINFO_ERROR=1601;
    int CCIS_GET_INV_ERROR=1602;
    int CCIS_UPDATE_INV_ERROR=1603;
    int CCIS_GET_ACCHDR_ERROR=1604;
    int CCIS_GET_CUSTCODELIST_BY_BIRTHDAY_ERROR=1605;
    int CCIS_GET_CUSTCODELIST_BY_DOCID_ERROR=1606;
    int CCIS_GET_ECINFO_ERROR=1607;
    int CCIS_GET_EMAIL_FAX_ERROR=1608;
    int CCIS_UPDATE_EMAIL_FAX_ERROR=1609;
    int CCIS_GET_NOTI_SEND_OPTN_ERROR=1610;
    int CCIS_UPDATE_SEND_OPTN_ERROR=1611;
    int CCIS_DUPLICATED_EMAIL_ERROR=1612;
    
    //ACAEInfo module exceptions
    int AC_AE_INFO_QUERY_BY_CUSTCODE_EXCEPTION = 1700;
    int AC_LIST_QUERY_BY_AECODE_EXCEPTION = 1701;
    int SAVE_AC_AE_INFO_EXCEPTION = 1702;
    int DELETE_AC_AE_INFO_EXCEPTION = 1703;
    int UPDATE_AC_AE_INFO_EXCEPTION = 1704;
    int AC_AE_INFO_FILE_NOT_FOUND_EXCEPTION = 1705;
    int AC_AE_INFO_RENAME_FILE_EXCEPTION = 1706;
    int SAVE_AC_AE_UPLOAD_FILE_EXCEPTION = 1707;
    int AC_AE_INFO_RECORDROW_ERROR = 1708;
    
    //eStatement module exceptions

    //CCIS WS API module exceptions
    int MSS_ErrorMsg=1700;
    int MSS_ACCOUNT_INAVAIL=1703;
    int MSS_DAYEND_PROCESSING=1706;
    int MSS_BALANCE_LACK=1707;
}
