package com.itsz.sht.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Constants;
import com.taifook.mcs.core.beans.msg.AccountDetail;



public class AccountFilterHelp {
	 private static  final  String   FUT_ACCOUNT_PREFIX ="01";
	 private static  final  String   SEC_ACCOUNT_PREFIX= "02";
	 private static  final  String[] SEC_ACCOUN_FILTER_LIST={"31","32","34"};
	 private static  final  String[] FUT_ACCOUNT_KEEP_LIST={"00"};
	 private static Log log_info = LogFactory.getLog(Constants.LOG_INFO_RTQ);
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		boolean  b = filterSecaccount("02-000012-30");
		System.out.print("b=" + b);
		boolean  c =filterFucaccount("01-000012-02");
		System.out.print("c=" + c);
	}
/*
 * filter sec acc with sub code 31,32,33+
 */
	public static boolean filterSecaccount(String accountNo) {
		if (accountNo == null || accountNo.length() == 0)
			return false;
		try {
			int len = accountNo.length();
			if (!accountNo.startsWith(SEC_ACCOUNT_PREFIX))
				return false;
			String suffix = accountNo.substring(len - 2, len);
			if (suffix.equals(SEC_ACCOUN_FILTER_LIST[0]) || suffix.equals(SEC_ACCOUN_FILTER_LIST[1])
					|| suffix.compareTo(SEC_ACCOUN_FILTER_LIST[2]) >= 0)
				return true;
		} catch (Exception ex) {
//			ex.printStackTrace();
		   log_info.info(ex);
		}

		return false;

	}

	/*
	 * filter fut account and only keey ac with sub code =00
	 */
	public static boolean filterFucaccount(String accountNo) {
		if (accountNo == null || accountNo.length() == 0)
			return false;
		try {
			if (!accountNo.startsWith(FUT_ACCOUNT_PREFIX)) return false;
			if  (!accountNo.endsWith(FUT_ACCOUNT_KEEP_LIST[0]))  return true;
			
		} catch (Exception ex) {
//			ex.printStackTrace();
			log_info.info(ex);
		}

		return false;
	}
	
	public static boolean filteraccount( AccountDetail acDetail) {
		if (acDetail==null|| acDetail.getAccountId()==null) return false;
		String acid=acDetail.getAccountId().trim();
		boolean  neddfilter=filterFucaccount(acid)||filterSecaccount(acid);
		return  neddfilter;
		
	}
		

}
