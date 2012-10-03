package com.itsz.sht.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.itsz.sht.common.PropertyConfig;

/**
 * $Id: FormatUtil.java,v 1.1 2010/11/09 03:57:25 kyzou Exp $
 * 
 * @Project:portal
 * @File:FormatUitl.java
 * @Description:
 * @Author:swLiu
 * @Date:2007-5-28
 */
public class FormatUtil {

	public static final String TIME_PATTERN = "HH:mm:ss";
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-20 13:58:06
	 * @param loginId
	 * @return
	 */
	public static String formatLoginId(String loginId){
		String result = null;
		if (StringUtils.isNotBlank(loginId)){
			if (FormatUtil.isDigit(loginId.trim())){
				result =FormatUtil.loginId527(loginId.trim());
			} else {
				result = loginId;
			}
		}
		return result;
	}
	/**
	 * 判断str是不是全部由数字组成，如："12568"
	 * @deprecated
	 * @see StringUtils#isNumeric(java.lang.String)
	 * @Author:Cimenon Saint
	 * @Time:2007-6-20 13:48:49
	 * @param str
	 * @return
	 */
	public static boolean isDigit(String str){
	   boolean isDigit = true;
	   if(StringUtils.isNotBlank(str)){
		   for (int i = 0; i < str.length(); i++) {
			   if (!Character.isDigit(str.charAt(i))) {
				   isDigit = false;
			   }
		   }
	   }else{
		   isDigit = false;
	   }
	   return isDigit;
	}
	/**
	 * 把login id从五位变为七位
	 * 1.如果loginId为空（blank）的话，返回null;
	 * 2.如果loginId的长度大于等于7位的话，返回与原先的loginId的值一致;
	 * 3.如果loginId的长度小于7位的话，返回原先的loginId的值并在其前加“0”补足七位
	 * @Author:Cimenon Saint
	 * @Time:2007-6-14 9:55:54
	 * @param loginId
	 * @return
	 */
	public static String loginId527(String loginId){
		String newId = null;
		if (StringUtils.isNotBlank(loginId)) {
			if (loginId.length() >= 7) {
				newId = loginId;
			}else {
				StringBuffer sb = new StringBuffer();
				int di = 7 - loginId.length();
				for (int i = 0; i < di; i++) {
					sb.append("0");
				}
				sb.append(loginId);
				newId = sb.toString();
			}
		}
		return newId;
	}

	public static String formatAccountId(String accountId) {
		String result = "";
		String tStr = "";
		if (accountId != null) {
			tStr = accountId.substring(3, 5);
		}
		if (tStr == null) {
			tStr = "";
		}
		if ("00".equals(tStr)) {
			result = accountId.substring(0, 3) + accountId.substring(5);
		} else {
			result = accountId;
		}
		return result;
	}

	/**
	 * @deprecated
	 * @see DateHelper#formatDate(Date, String)
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * @deprecated
	 * @see DateHelper#datePatternConvert(String, String, String)
	 * dateStr must fit sourcePattern pattern
	 * @Author:Cimenon Saint
	 * @Time:2008-1-9 18:48:23
	 * @param dateStr
	 * @param targetPattern -- HH:mm:ss dd-MM-yy
	 * @param sourcePattern -- yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String datePatternConvert(String dateStr , String targetPattern , String sourcePattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(sourcePattern);
			Date date = sdf.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return new SimpleDateFormat(targetPattern).format(cal.getTime());
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @deprecated
	 * @Author:Cimenon Saint
	 * @Time:2007-11-29 11:10:54
	 * @param stockCode
	 * @return
	 */
	public static String formatStockCode(String stockCode) {
		if (stockCode != null) {
			StringBuffer sb = new StringBuffer(stockCode);
			int i = 5 - sb.length();
			while (i > 0) {
				sb.insert(0, "0");
				i--;
			}
			return sb.toString();
		}

		return "00000";
	}
	
	/**
	 * get stock code length
	 * <p>1.默认值</p>
	 * <p>2.从数据库取值</p>
	 * <p>3.如果数据库的值不符合要求，用默认值</p>
	 * @Author:Cimenon Saint
	 * @Time:2007-11-29 14:13:30
	 * @return
	 */
	public static int getStockCodeLength(){
		int len = Consts.Global.Stock.DEFAULTSTOCKLENGTH;
		String stockLen =PropertyConfig.getCommonProperty(Consts.Global.Stock.CODELENGTHKEY);
		if(StringUtils.isNotBlank(stockLen)){
			try{
				len = Integer.parseInt(stockLen);
			}catch (Exception e) {
				//len = Consts.Global.Stock.DEFAULTSTOCKLENGTH;
			}
		}
		return len;
	}
	
	/**
	 * 根据配置的值，格式化股票代码
	 * @Author:Cimenon Saint
	 * @Time:2007-11-29 11:12:06
	 * @param code
	 * @return
	 */
	public static String formatInstrCode(String code){
		return formatInstrCode(code, getStockCodeLength());
	}

    /**
     * <p>if code is blank then return ""</p>
     * @Author:Cimenon Saint
     * @Time:2007-7-25 18:37:01
     * @param code
     * @param len
     * @return
     */
    public static String formatInstrCode(String code , int len){
        String result = "";
        if(StringUtils.isNotBlank(code)){
            String temp = minusInstrCode(code.trim());
            if(StringUtils.isNotBlank(temp)){
                if(temp.length() < len){
//                    int size = temp.length();
//                    result = temp;
//                    for (int i = len; i > size; i--) {
//                        result = "0" + result;
//                    }
                    result = StringUtils.leftPad(temp, len, "0");
                }else{
                    result = temp;
                }
            }
        }
        return result;
    }
    
    /**
     * @deprecated
     * <p>没有使用参数配置，不建议使用该方法</p>
     * <p>刚开始是这个方法是为qs而准备的</p>
     * @Author:kyzou
     * @Time:2007-11-13 上午10:32:26
     * @param code
     * @param len
     * @param forRtq
     * @return
     */
    public static String formatInstrCode(String code , int len , boolean forQs){
    	if(forQs){
    		len = 4;
    	}
        return formatInstrCode(code, len);
    }
    
    /**
     * <p>去掉code前面的“0”</p>
     * <p>eg.</p>
     * <p>"0056" >> "56"</p>
     * <p>"4526" >> "4526"</p>
     * <p>"01050" >> "1050"</p>
     * <p>if code is blank then return ""</p>
     * @Author:Cimenon Saint
     * @Time:2007-7-25 18:36:23
     * @param code
     * @return
     */
    public static String minusInstrCode(String code){
        String result = "";
        if(StringUtils.isNotBlank(code)){
            if(code.charAt(0)!='0'){
                result = code;
            }else{
                if(code.length() > 1){
                    result = minusInstrCode(code.substring(1));
                }
            }
        }
        return result;
    }

	public static String data2Round(BigDecimal value, int bit, boolean isFormat) {
		if (value != null) {
			value = value.setScale(bit, BigDecimal.ROUND_HALF_UP);
			if (isFormat) {
				return formatNumber(value, bit);
			}
			return value.toString();
		}
		return "0";
	}
	
	public static String data2Round(BigDecimal value, int bit, String formatPattern) {
		if (value != null) {
			value = value.setScale(bit, BigDecimal.ROUND_HALF_UP);
			if (StringUtils.isBlank(formatPattern)) {
				StringBuffer pattern = new StringBuffer("#,##0");
				for (int index = 0; index < bit; index++) {
					if (index == 0) {
						pattern.append(".");
					}
					pattern.append("0");
				}
				formatPattern=pattern.toString();
			}			
			return formatNumber(value, bit,formatPattern);
		}
		return "0";
	}	

	public static String formatNumber(Number num, int bit, String formatPattern) {
		if (num != null) {
			DecimalFormat df = new DecimalFormat(formatPattern.toString());
			return df.format(num);
		}
		return "0";
	}

	public static String formatNumber(Number num, int bit) {
		if (num != null) {
			StringBuffer formatPattern = new StringBuffer("#,##0");
			for (int index = 0; index < bit; index++) {
				if (index == 0) {
					formatPattern.append(".");
				}
				formatPattern.append("0");
			}
			DecimalFormat df = new DecimalFormat(formatPattern.toString());
			return df.format(num);
		}
		return "0";
	}

	public static String number2String(Number num, boolean isInt) {
		return num == null ? "0" : isInt ? String.valueOf(num.longValue())
				: String.valueOf(num.doubleValue());
	}
	
	public static void main(String[] args){
		System.out.println(FormatUtil.formatInstrCode(" 0000 "));
		//System.out.println(FormatUtil.minusInstrCode("0000"));
	}

}
