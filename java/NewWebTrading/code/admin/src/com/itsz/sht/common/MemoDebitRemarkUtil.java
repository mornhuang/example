package com.itsz.sht.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoDebitRemarkUtil {

	public final static String REMARK_HEAD = "HTIOL - ";
	public final static String REMARK_HK_SEC = "HK Securities RTQ";
	public final static String REMARK_US_SEC = "US Securities RTQ";
	public final static String REMARK_LOCAL_FUT = "Local Futures RTQ";
	public final static String REMARK_OS_FUT = "Overseas Futures RTQ";
	public final static String REMARK_SNAPSHOT = "Price Snap Shot";
	public final static String REMARK_PRICALERT = "Price Alert";
	public final static String REMARK_SMS_CMT = "SMS Commentary";
	public final static String REMARK_SHK = "Intelligent Stock Monitor";
	public final static String REMARK_SHARPPOINT = "Power Futures Account Services";
	public final static String REMARK_RTQ_3G = "Power 3G Finance RTQ";
	public final static String REMARK_NOTIFY = "Order Notification Service";

	public final static String RTQ_SEC_STR_HK = "RTQ_SEC_STR_HK";
	public final static String RTQ_FUT_STR_HK = "RTQ_FUT_STR_HK";
	public final static String RTQ_SEC_STR_MOBILE = "RTQ_SEC_STR_MOBILE";
	public final static String RTQ_SEC_APP_TDX = "RTQ_SEC_APP_TDX";
	public final static String RTQ_SEC_APP_MIE = "RTQ_SEC_APP_MIE";
	public final static String RTQ_SEC_APP_CEP = "RTQ_SEC_APP_CEP";
	public final static String RTQ_SEC_APP_TSCI = "RTQ_SEC_APP_TSCI";
	public final static String RTQ_SEC_SNP_TSCI = "RTQ_SEC_SNP_TSCI";
	public final static String RTQ_SEC_APP_ZZB = "RTQ_SEC_APP_ZZB";
	public final static String RTQ_SEC_SNP_ZZB = "RTQ_SEC_SNP_ZZB";
	public final static String RTQ_SEC_APP_QL = "RTQ_SEC_APP_QL";
	public final static String RTQ_SEC_SNP_HK = "RTQ_SEC_SNP_HK";
	public final static String RTQ_SEC_STR_US = "RTQ_SEC_STR_US";
	public final static String RTQ_SEC_STR_JP = "RTQ_SEC_STR_JP";
	public final static String RTQ_FUT_STR_OVERSEAS = "RTQ_FUT_STR_OVERSEAS";
	public final static String RTQ_FOREX_FUT = "RTQ_FOREX_FUT";
	public final static String RTQ_3G = "RTQ_3G";
	public final static String REDEMPTION = "REDEMPTION";
	public final static String ENEWSPAPER = "ENEWSPAPER";
	public final static String PRCALERT = "PRCALERT";
	public final static String ESTATEMENT = "ESTATEMENT";
	public final static String SHK = "SHK";
	public final static String SMSCOMMENT = "SMSCOMMENT";
	public final static String PORTFOLIO = "PORTFOLIO";
	public final static String EWATCHLIST = "EWATCHLIST";
	public final static String NOTIFY = "NOTIFY";
	public final static String SHARPPOINT = "SHARPPOINT";
	public final static String SNP = "SNP";

	public static final SimpleDateFormat ymFormatter = new SimpleDateFormat(
			"yyyy/MM");

	public static String generateMemoRemarks(String category, Date txDate) {
		String remark = REMARK_HEAD;
		if (RTQ_SEC_STR_HK.equalsIgnoreCase(category)
				|| RTQ_3G.equalsIgnoreCase(category)
				|| RTQ_SEC_APP_TDX.equalsIgnoreCase(category)
				|| RTQ_SEC_APP_MIE.equalsIgnoreCase(category)
				|| RTQ_SEC_APP_CEP.equalsIgnoreCase(category)
				|| RTQ_SEC_APP_QL.equalsIgnoreCase(category)
				|| RTQ_SEC_APP_ZZB.equalsIgnoreCase(category)
				|| RTQ_SEC_APP_TSCI.equalsIgnoreCase(category))
			remark = remark + REMARK_HK_SEC + " (" + formatYM(txDate) + ")";
		else if (RTQ_SEC_STR_US.equalsIgnoreCase(category))
			remark = remark + REMARK_US_SEC + " (" + formatYM(txDate) + ")";
		else if (RTQ_FUT_STR_HK.equalsIgnoreCase(category))
			remark = remark + REMARK_LOCAL_FUT + " (" + formatYM(txDate) + ")";
		else if (RTQ_FUT_STR_OVERSEAS.equalsIgnoreCase(category))
			remark = remark + REMARK_OS_FUT + " (" + formatYM(txDate) + ")";
		else if (SHK.equalsIgnoreCase(category))
			remark = remark + REMARK_SHK + " (" + formatYM(txDate) + ")";
		else if (PRCALERT.equalsIgnoreCase(category))
			remark = remark + REMARK_PRICALERT;
		else if (SMSCOMMENT.equalsIgnoreCase(category))
			remark = remark + REMARK_SMS_CMT;
		else if (RTQ_SEC_SNP_HK.equalsIgnoreCase(category)
				|| RTQ_SEC_SNP_ZZB.equalsIgnoreCase(category)
				|| RTQ_SEC_SNP_TSCI.equalsIgnoreCase(category))
			remark = remark + REMARK_SNAPSHOT;
		else if (SHARPPOINT.equals(category))
			remark = remark + REMARK_SHARPPOINT + " (" + formatYM(txDate) + ")";
		else if (NOTIFY.equals(category))
			remark = remark + REMARK_NOTIFY;

		/*
		if(logger.isInfoEnabled()){
			logger.info(this.getClass() + "#Membit Description for MIS[ category:" + category + "  remark:" + remark + "]");
		}
		 */
		return remark;
	}

	private static String formatYM(Date date) {
		String dateStr = "";
		try {
			dateStr = ymFormatter.format(date);
		} catch (Exception e) {
		}
		return dateStr;
	}
}
