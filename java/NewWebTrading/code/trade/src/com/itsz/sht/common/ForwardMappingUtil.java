package com.itsz.sht.common;

import java.util.HashMap;
import java.util.Map;


/**
 * $Id: ForwardMappingUtil.java,v 1.2 2010/12/04 01:34:13 kyzou Exp $
 * @Project:portal
 * @File:ForwardMappingUtil.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-20
 */
public class ForwardMappingUtil {
	final static String SUCCESS = "success";
	final static String WARNING = "warning";
	final static String FAILURE = "failure";
	final static String NEEDCHANGEPASS = "needChangePass";
	final static String CHANGEPASS = "changPass";
	final static String SHOWMSG = "showMsg";
	final static String RELOGIN = "reLogin";
	final static String MAIN = "main";
	final static String INDEX = "index";
	final static String NORMAL = "success";
	
	final static String SPOSITION = "stpListaccount";
	final static String APOSITION = "acListaccount";
	final static String RELOGIN_FORWARD = "initlogin.do";
	final static String illegalClient_FORWARD = "initlogin.do";
	final static String ANNOUNCE = "announce";
	final static String NONANNOUNCE = "nonannounce";
	
	private static Map<String,String> forwardMapping = getInstance();
	
	private static Map<String,String> getInstance(){
		forwardMapping = new HashMap<String,String>();
		forwardMapping.put(Consts.Error.Code.SUCCESS, SUCCESS);
		forwardMapping.put(Consts.Error.Code.WARNING, WARNING);
		forwardMapping.put(Consts.Error.Code.FAILURE, FAILURE);
		forwardMapping.put(Consts.Error.Code.NORMAL, SUCCESS);
		forwardMapping.put(Consts.Error.Code.RELOGIN, RELOGIN);
		forwardMapping.put(Consts.Error.Code.SHOWMSG, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.ANNOUNCE, ANNOUNCE);
		forwardMapping.put(Consts.Error.Code.NONANNOUNCE, NONANNOUNCE);
		//newsubcode
		forwardMapping.put(Consts.Error.Code.INVECERT, FAILURE);
		forwardMapping.put(Consts.Error.Code.INVDOCID, FAILURE);
		forwardMapping.put(Consts.Error.Code.GRACECNT, CHANGEPASS);
		forwardMapping.put(Consts.Error.Code.SUSPENDED, FAILURE);
		forwardMapping.put(Consts.Error.Code.LOCKED, FAILURE);	
		forwardMapping.put(Consts.Error.Code.ISSUED, CHANGEPASS);
		forwardMapping.put(Consts.Error.Code.INVIDLOGID, FAILURE);
		forwardMapping.put(Consts.Error.Code.INVIDPASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHGPASS, CHANGEPASS);
		forwardMapping.put(Consts.Error.Code.CHAADNORMAL, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHASUSP, FAILURE);
		forwardMapping.put(Consts.Error.Code.RETRYCNT, FAILURE);
		forwardMapping.put(Consts.Error.Code.GRACELOGIN, CHANGEPASS);
		//base
		forwardMapping.put(Consts.Error.Code.ERRORCODE_SYS, FAILURE);
		forwardMapping.put(Consts.Error.Code.SESSION_OVERTIME, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.ISNOT_POCKETIE_CLIENT, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.CONNECT_LIMITED, FAILURE);
		forwardMapping.put(Consts.Error.Code.CONNECT_MCS, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.ERRORCODE_CONNECT_RTQ, FAILURE);
		forwardMapping.put(Consts.Error.Code.PARAMETER_MCS, FAILURE);
		forwardMapping.put(Consts.Error.Code.PARAMETER_RTQ, FAILURE);
		forwardMapping.put(Consts.Error.Code.PARAMETER_ESERVICE, FAILURE);
		forwardMapping.put(Consts.Error.Code.ERRORCODE_CONNECT_ESERVICE, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHECK_TRADING_ACCOUNT, FAILURE);
		forwardMapping.put(Consts.Error.Code.ERRORCODE_CHECK_ALLOW_TRADE_FLAG, MAIN);
		forwardMapping.put(Consts.Error.Code.PARAMETER_EMESSAGE, FAILURE);
		forwardMapping.put(Consts.Error.Code.ERRORCODE_CHANNEL_STOPPED, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.ERRORCODE_TOKEN_INVALID, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.ERRORCODE_UNKNOWN_EXCEPTION, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.ITSZ_EXCEPTION, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.ECERT_EXPIRED_OR_REVOKED, FAILURE);
		forwardMapping.put(Consts.Error.Code.ECERT_UNSUPPORTED_CA, FAILURE);
		forwardMapping.put(Consts.Error.Code.ECERT_AUTHENTICATION, FAILURE);
		forwardMapping.put(Consts.Error.Code.LOTSIZENOTMATCH, FAILURE);
		forwardMapping.put(Consts.Error.Code.AONINVALIDQTY, FAILURE);
		forwardMapping.put(Consts.Error.Code.CONNECT_IPO, FAILURE);		
		forwardMapping.put(Consts.Error.Code.STREAM_INFO_UNAVAILABLE, FAILURE);
		//new wmt
		//login
		forwardMapping.put(Consts.Error.Code.LOGIN_INVALID_LOGINID, FAILURE);
		forwardMapping.put(Consts.Error.Code.LOGIN_INVALID_LOGINPASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.RELOGIN_PARAM_NAME,RELOGIN_FORWARD);
		//trading
		forwardMapping.put(Consts.Error.Code.TRAD_PLACE_SUCCESS, SUCCESS);
		forwardMapping.put(Consts.Error.Code.TRAD_NULL_STOCKCODE, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_NULL_PLACEQTY, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_NULL_PLACEPRICE, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_INVALID_STOCKCODE, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_INVALID_PLACEQTY, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_INVALID_PLACEPRICE, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_INVALID_LOTSIZE, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_ERROR_QTYMAXSIZE, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_ERROR_NOAO, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_ERROR_WRONGPASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_ERROR_SYSTEM, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.TRAD_PLCAE_EXCEPTION01, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.TRAD_WARNING_SEPORDER, SUCCESS);
		forwardMapping.put(Consts.Error.Code.TRAD_NULL_NOTAOPLACE, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_INVALID_NOTAOPLACE, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_NULL_MODIFYQTY, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_INVALID_MODIFYQTY, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_TOOMUCH_MODIFYQTY, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_ERROR_MODIFYPASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_MODIFY_ERROR_SYS, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.TRAD_MODIFY_EXCEPTION01, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.TRAD_MODIFY_SUCCESS, SUCCESS);
		forwardMapping.put(Consts.Error.Code.TRAD_CANCEL_WRONGPASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_CANCEL_ERROR_SYS, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.TRAD_CANCEL_EXCEPTION01, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.TRAD_CANCEL_SUCCESS, SUCCESS);
		forwardMapping.put(Consts.Error.Code.TRAD_NOALLOW, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRAD_ERROR_INSTRCODE, FAILURE);
		//query trade 查单
		forwardMapping.put(Consts.Error.Code.ORDERLIST_ERROR_SYS, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.ORDERLIST_ERROR_EXCEPTION01, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.ORDERDETAIL_ERROR_SYS, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.ORDERDETAIL_ERROR_EXCEPTION01, SHOWMSG);
		//position 结存
		forwardMapping.put(Consts.Error.Code.SPOSITION_ERROR_SYS, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.SPOSITION_ERROR_EXCEPTION01, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.APOSITION_ERROR_SYS, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.APOSITION_ERROR_EXCEPTION01, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.SPOSITION_NORMAL, SPOSITION);
		forwardMapping.put(Consts.Error.Code.APOSITION_NORMAL, APOSITION);
		//setting 喜好
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_SUCCESS, SUCCESS);
		forwardMapping.put(Consts.Error.Code.TRSPTD_SUCCESS, SUCCESS);
		forwardMapping.put(Consts.Error.Code.TRSPTD_FAILURE, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_NULL_NEWPASS1, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_NULL_OLDPASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_NULL_NEWPASS2, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_INVALID_NEWPASS1, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_INVALID_OLDPASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_INVALID_NEWPASS2, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_ERROR_NEWANDOLDPASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_ERROR_PASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_ERROR_PASS1, FAILURE);
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_ERROR_SYS, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.CHANGEPASS_ERROR_EXCEPTION01, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRSPTD_INVALID_PASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRSPTD_ERROR_PASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRSPTDS_ERROR_SYS, SHOWMSG);
		forwardMapping.put(Consts.Error.Code.TRSPTD_ERROR_EXCEPTION01, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRSPTD_ERROR_INV_PASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.TRSPTD_ERROR_INV_N_PASS, FAILURE);
		forwardMapping.put(Consts.Error.Code.SECONDDISABLEDLOGIN, FAILURE);
		//rtq
		forwardMapping.put(Consts.Error.Code.RTQ_NULL_STOCKCODE, FAILURE);
		forwardMapping.put(Consts.Error.Code.RTQ_INVALID_STOCKCODE, FAILURE);
		//mcs
		forwardMapping.put(Consts.Error.Code.MCS00403, FAILURE);
		return forwardMapping;
	}
	
	public static String getForward(String code){
		String forward = (String)forwardMapping.get(code);
		if(forward==null){
			return "";
		}
		return forward;
	}
	
	public static void main(String args[]){
		String forward = getForward("sys");
		System.out.println(forward);
	}

}
