package com.taifook.adminportal.common.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.taifook.adminportal.common.Constants;

/**
 * <p> * Title: admin_portal           * </p>
 * <p> * Description:                  * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */
public final class DataFormatUtil {	

	public static final Date String2Date(String str)
	{
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = bartDateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			date=null;
		}
		return date;
	}
	
	public static final String Date2FullDTString(Date date){
		return (new SimpleDateFormat("yyy-mm-dd HH:mm:ss")).format(new Date());
	}

	public static final String Date2String(Date date)
	{
		return date.toString().length()>10?date.toString().substring(0,10):date.toString();
	}

	public static final int getCurrentHour(Date date){
		 SimpleDateFormat dateFormatter =new SimpleDateFormat("HH");
		 int hours;
		try {
			String str=dateFormatter.format(date);
			 hours = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			hours=-1;
		}
		 return hours;
	}

	public static final String getCurrentDate(Date date){
		DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		 String date_str;
		try {
			date_str = d.format(date);
		} catch (RuntimeException e) {
			e.printStackTrace();
			date_str=null;
		}
		 return date_str;
	}
	public static final String GBK2UTF(String str) throws UnsupportedEncodingException{
		return new String(str.getBytes("ISO-8859-1"),"GB2312");
	}
	public static String BIG2UTF(String str) throws UnsupportedEncodingException{
		return new String(str.getBytes("ISO-8859-1"),"BIG5");
	}
	public static final String Convert2ShortString(String str){
		if(str==null||str.trim().length()==0)
			str="null";

		return  str.length()>=Constants.DEFAULT_STRING_LENGTH  ?
			    str.substring(0,Constants.DEFAULT_STRING_LENGTH)+"..."
				: str;
	}
//for web ipo
        public static String formatAmt(BigDecimal d) {
            try {
                String pattern = "##,##0.00";
                DecimalFormat declimalFormat = new DecimalFormat(pattern);
                String s=declimalFormat.format(d.doubleValue());
                if(s.equals("-0.00")) s="0.00";
                return s;
            } catch (Exception ex) {
                return "0.00";
            }

        }

        public static String formatQty(int d) {
            try {
                String pattern = "##,##0";
                DecimalFormat declimalFormat = new DecimalFormat(pattern);
                String s=declimalFormat.format(d);
                if(s.equals("-0")) s="0";
                return s;
            } catch (Exception ex) {
                return "0";
            }

        }

        public static String hold3Decimal(BigDecimal price) {
            try {
                String pattern="0.000";
                DecimalFormat df=new DecimalFormat(pattern);

                String s=df.format(price);
                if(s.equals("-0.000")) s="0.000";
                return s;
            } catch (Exception ex) {
                return "";
            }

        }
        public static String hold2Decimal(BigDecimal capital) {
            try {
                String pattern="0.00";
                DecimalFormat df=new DecimalFormat(pattern);
                String s=df.format(capital);
                if(s.equals("-0.00")) s="0.00";
                return s;
            } catch (Exception ex) {
                return "";
            }
        }
        public static void main(String args[]) {
             System.out.println(formatAmt(new BigDecimal(377074816.14 +100.00)));
         }

}

