package com.itsz.sht.struts.eipo.util;

import java.text.SimpleDateFormat;

public interface EIPOConstants {

	Long TIMEOUT = 60 * 1000L;
	String SESSION_IPO_SUBSCRIPTION = "IPO_ADD_SUBSCRIPTION";
	String SESSION_ENQUIRY_MASTER = "SESSION_ENQUIRY_MASTER";
	String SESSION_ENQUIRY_SUBSCRIPTION = "SESSION_ENQUIRY_SUBSCRIPTION";
	
	String IPO_MASTER_SUBSCRIPTION_STATE_NULL = "-1";  
	String IPO_MASTER_SUBSCRIPTION_STATE_SUB = "0";		//订阅
	String IPO_MASTER_SUBSCRIPTION_STATE_CANCEL = "1";	//取消
	String IPO_MASTER_SUBSCRIPTION_STATE_DETAIL = "2";	//详细信息
	
	String IPO_CHANNELCODE = "WEB";
	
	String REMARKSIGN_DOLLAR = "$";
	String REMARKSIGN_PERCENT = "%";
	
	SimpleDateFormat SDF_YYMMDD = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat SDF_YYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	String PRICE_FORMAT="$#,##0.00";
	String SUBSCRIPTION_STATE_REJECTED="rejected";
}
