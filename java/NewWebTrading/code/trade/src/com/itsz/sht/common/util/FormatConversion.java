/*
 * �������� 2004-12-24
 *
 */
package com.itsz.sht.common.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.itsz.sht.common.OrderStateConvertUtil;
import com.itsz.sht.common.Constants;

/**
 * @author huxin
 */
public class FormatConversion {
	static final String M = "M";
	static final String K = "K";
	static final String NUM_0 = "0";
	public static String formatQTY(
		java.math.BigDecimal qty) { //����ת����ʽ��1000->1K��1000000->1M
		try {
			String retVal = "";
			double d = 0.0;
			if (qty.doubleValue() >= 1000000) { //����1000000
				d = qty.doubleValue() / 1000000;
				if ((d * 10) / ((int) d * 10) != 1)
					retVal = (new Double(d).toString() + M);
			//retVal=formatDecimal(new BigDecimal(d))+M;
				else
					retVal = new Integer((int) d).toString() + M;
					return retVal;
			}
			if (qty.doubleValue() >= 1000) { //����1000
				d = qty.doubleValue() / 1000;
				if ((d * 10) / ((int) d * 10) != 1)
					retVal = (new Double(d).toString() + K);
				else
					retVal = new Integer((int) d).toString() + K;
					return retVal;
			}
			if (qty.doubleValue() <=-1000000) { //С��-1000000
							d = qty.doubleValue()/1000000;
							if ((d * 10) / ((int) d * 10) != 1)
								retVal = (new Double(d).toString() + M);
							else
								retVal = new Integer((int) d).toString() + M;
								return retVal;
			}
			if (qty.doubleValue() <=-1000) { //С��1000
							d = qty.doubleValue() /1000;
							if ((d * 10) / ((int) d * 10) != 1)
								retVal = (new Double(d).toString() + K);
							else
								retVal = new Integer((int) d).toString() + K;
								return retVal;
			}			
			if(qty.doubleValue() - Math.floor(qty.doubleValue()) == 0)	//�����������С���С��						
			retVal = (new Long(qty.longValue()).toString());
			else retVal = (new Double(qty.doubleValue()).toString());				
			return retVal;
		} catch (Exception ex) {
			return "";
		}
	}
	
	public static String formatQtyHold2Decimal(
			java.math.BigDecimal qty,boolean isInt) { //����ת����ʽ��1000->1K��1000000->1M
			try {
				String retVal = "";
				double d = 0.0;
				if (qty.doubleValue() >= 1000000) { //����1000000
					d = qty.doubleValue() / 1000000;				
				    retVal=formatDecimal(new BigDecimal(d))+M;				
					return retVal;
				}
				if (qty.doubleValue() >= 1000) { //����1000
					d = qty.doubleValue() / 1000;					
					retVal=formatDecimal(new BigDecimal(d))+K;					
					retVal=formatDecimal(new BigDecimal(d))+K;
					return retVal;
				}
				if (qty.doubleValue() <=-1000000) { //С��-1000000
					d = qty.doubleValue()/1000000;								
					retVal=formatDecimal(new BigDecimal(d))+M;								
					return retVal;
				}
				if (qty.doubleValue() <=-1000) { //С��1000
					d = qty.doubleValue() /1000;								
					retVal=formatDecimal(new BigDecimal(d))+K;								
					return retVal;
				}			
				if(isInt==true&&qty.doubleValue() - Math.floor(qty.doubleValue()) == 0)	//�����������С���С��						
				retVal = (new Long(qty.longValue()).toString());
				else retVal=formatDecimal(qty);											
				return retVal;
			} catch (Exception ex) {
				return "";
			}
		}
	
	public static String formatStockCode(String stockcode) { //����Ʊ����ǰ���0ȥ����00023->23
		try {
			String retVal = "";
			while (stockcode.startsWith(NUM_0)) {
				stockcode = stockcode.substring(1);
			}
			retVal = stockcode;
			return retVal;
		} catch (Exception ex) {
			return "";
		}
	}

	public static String hold3Decimal(BigDecimal price) { //����3λС��λ������������
		try {
			//double d = 0.0;
			String pattern="0.000";
			DecimalFormat df=new DecimalFormat(pattern);
			//d = (Math.round(price.doubleValue() * 1000)) / 1000.0;
			//return new Double(d).toString();
			String s=df.format(price);
			if(s.equals("-0.000")) s="0.000";
			return s;
		} catch (Exception ex) {
			return "";
		}

	}
	public static String hold2Decimal(BigDecimal capital) { //����2λС��λ������������
		try {
			String pattern="0.00";
			DecimalFormat df=new DecimalFormat(pattern);	
			String s=df.format(capital);
			if(s.equals("-0.00")) s="0.00";
			return s;
			//double d = 0.0;
			//d = (Math.round(capital.doubleValue() * 100)) / 100.0;
			//return new Double(d).toString();
		} catch (Exception ex) {
			return "";
		}
	}

	public static String formatDecimal(BigDecimal d) { //��ʽ��������ʾ ��2342565.555->2,342,565.56
		try {
			String pattern = "##,##0.00";
			DecimalFormat declimalFormat = new DecimalFormat(pattern);
			String s=declimalFormat.format(d.doubleValue());
			if(s.equals("-0.00")) s="0.00";
			return s;
		} catch (Exception ex) {
			//ex.printStackTrace();
			return "0.00";
		}

	}
	
	public static String formatQtyNoDecimal(BigDecimal d) { //��ʽ��������ʾ ��2342565.555->2,342,565.56
		try {
			String pattern = "##,##0";
			DecimalFormat declimalFormat = new DecimalFormat(pattern);
			String s=declimalFormat.format(d.doubleValue());
			if(s.equals("-0")) s="0";
			return s;
		} catch (Exception ex) {
			//ex.printStackTrace();
			return "0";
		}

	}
	
	/**
	 * @deprecated
	 * @see com.itsz.channelsserver.common.FormatUtil.datePatternConvert(String dateStr , String targetPattern , String sourcePattern)
	 */
	public static String formatDate(String  s) { //��ʽ��ʱ����ʾ ��2005-01-25 12:29:54.503->12:29:54 25-01-05
			try {
				
				// d=new Date(s);
				//long c=java.sql.Timestamp.parse(s);
				String oldPattern="yyyy-MM-dd HH:mm:ss.SSS";
				String pattern="HH:mm:ss dd-MM-yy";
				//DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG,Locale.CHINESE);				
				SimpleDateFormat osdf=new SimpleDateFormat(oldPattern);
				Date d=osdf.parse(s);
				SimpleDateFormat sdf=new SimpleDateFormat(pattern);
				//return d.toLocaleString();
				return sdf.format(d);
			} catch (Exception ex) {
				ex.printStackTrace();
				return "";
			}
		}
		
	public static long string2timestamp(String s) throws ParseException { // �ض���ʽ��ʱ��ת����timestamp ��"29 SEP 2006"->1159459200000
		String pattern = "dd MMM yyyy";
		Date d = new Date();
		SimpleDateFormat osdf = new SimpleDateFormat(pattern, Locale.US);
		d = osdf.parse(s);
		return d.getTime();
	}
	
	/**
	 * @deprecated
	 * @see com.itsz.channelsserver.common.FormatUtil : formatLoginId
	 */
	public static String formatLoginID(String s) {//��ȫ��������С��7λ��loginIDǰ���0

		   String result = null;
		   boolean isEmpty=true;
		   isEmpty=s == null || "".equals(s.trim());
		   if (!isEmpty) {
			   s = s.trim();
			   boolean isAllDigit = true;
			   for (int i = 0; i < s.length(); i++) {
				   if (!Character.isDigit(s.charAt(i))) {
					   isAllDigit = false;
				   }
			   }
			   if (isAllDigit) {
				   result =fillLeadingCharacterToLength(s, '0', 7);
			   }
			   else {
				   result = s;
			   }
		   }
		   return result;
	   }	
	   
	private static String fillLeadingCharacterToLength(String s, char toFill, int length) {

			String result = null;
			if (s != null) { 
				if (s.length() >= length) {
					result = s;
				}
				else {
					StringBuffer sb = new StringBuffer();
					int di = length - s.length();
					for (int i = 0; i < di; i++) {
						sb.append(toFill);
					}
					sb.append(s);
					result = sb.toString();
				}
			}
			return result;
		}
	public static String replace(String s,String oldStr,String newStr){
	    if(s==null||oldStr==null||newStr==null) return s;
	    String returnValue=s.replaceAll(oldStr,newStr);
	    return returnValue;
	}
	/**
	 * @deprecated
	 * @see FormatUtil
	 * @param s
	 * @param digit
	 * @return
	 */
	public static String formatInstrCode(String s,int digit){
	    if(s==null) return s;
	    s=s.trim();
	    int size=s.length();
	    String ss = s;
	    for(int j=digit;j>size;j--){
	        ss="0"+s;
	        s=ss;
	    }    
	    return ss;
	}
	
	/**
	 * @deprecated
	 * @see OrderStateConvertUtil#mappingOrderState(String)
	 * @Author:kyzou
	 * @Time:2008-4-17 上午10:24:13
	 * @param s
	 * @return
	 */
	
	public static String mappingOrderState(String s){//��MtssOrderStateת���ɽ϶̵��ַ���TRANSMITTING->T
		Map m=new HashMap();
		m.put("TRANSMITTING","T");
		m.put("RECEIVED","R");
		m.put("QUEUING","Q");
		m.put("CANCELLED","D");
		m.put("REJECTED","J");
		m.put("COMPLETELY_FILLED","C");
		m.put("PARTIALLY FILLED","P");
		m.put("PARTIALLY_FILLED_COMPLETED","CP");
	    String shortValue=(String)m.get(s);
		return shortValue;
		
	}
	
	public static String appendPostfix(String state,String orderRemark){//���orderRemark��ֵ��mtssOrderState����ӱ�־
	    try {		
	        if(orderRemark.trim().endsWith("rogress")) state+="@";//In Progress
	        if(orderRemark.trim().endsWith("ailed")) state+="#";//Failed
	        if(orderRemark.trim().endsWith("uccess")) state+="*";//Success 
	        return state;
	    } catch (Exception e) {
	        //e.printStackTrace();
	        return state;
	    }
	}
	public static Timestamp formatDate2timeStamp(String date)throws Exception{//��yyyy/MM/dd ��ʽ���ַ�ת��Timestamp
	    try {											
			SimpleDateFormat dateFormat=new SimpleDateFormat(Constants.dateFormat);
			Date d = dateFormat.parse(date);
			long time = d.getTime();
			Timestamp timestamp = new Timestamp(time);
			//return d.toLocaleString();
			return timestamp;
		} catch (Exception ex) {			
			throw new Exception();
		}
	}
	public static String formatAcShort(String ac) throws Exception{//��ʽ���˻���ƣ���02-0042038-30->02-42038-30
	    //if (ac==null) return null;
	    String returnValue = ac;
	    String str = null;
	    if(ac.length()==13){
	       str  = ac.trim().substring(3,5);
	    }
	    if("00".equals(str)){
	        String oriValue;
		    String resultValue;	   
	        oriValue = ac.substring(3,10);
	        resultValue = oriValue.substring(2);
	        returnValue = ac.replaceAll(oriValue,resultValue);
	    }
	    return returnValue;
	}
	
	public static String formatSpInstrCode(String s,int digit){
	    if(s==null) return s;
	    s=s.trim();
	    int size=s.length();
	    String ss = s;
	    for(int j=digit;j>size;j--){
	        ss="0"+s;
	        s=ss;
	    }    
	    return ss+".HK";
	}
	
	public static void main(String[] args) {
		//BigDecimal bigDecimal = new BigDecimal("000.8");
		//System.out.println(formatQtyNoDecimal(bigDecimal));		
            //System.out.println(formatQTY(bigDecimal));
            //System.out.println(formatQtyHold2Decimal(bigDecimal,true));
            //System.out.println(Math.round(-19.5));
            //System.out.println(hold3Decimal(bigDecimal));
            //System.out.println(hold2Decimal(bigDecimal));
            //System.out.println(Math.round(555.05));
            //System.out.println(Math.round(-453453.5));
            //System.out.println(formatDecimal(bigDecimal));
            //System.out.println(formatDate("2005-01-25 12:29:54.503"));
            /*System.out.println(formatLoginID("343"));
            System.out.println(formatLoginID("adf"));
            System.out.println(formatLoginID("11111a"));
            System.out.println(formatLoginID("111123232323"));
            System.out.println(formatLoginID("aaaaaaa1"));
            System.out.println(formatLoginID("1zaaaaaa"));
            System.out.println(formatLoginID("a1"));
            System.out.println(formatLoginID("1a"));
            System.out.println(formatLoginID("6666666"));
            System.out.println(formatLoginID("77777777"));
            System.out.println(formatLoginID("7777.7"));
            System.out.println(replace("45   &&&w5ss&&00","&","&amp;"));*/
            /*System.out.println(formatInstrCode("-444444",4));
            System.out.println(formatInstrCode("0505",8));
            System.out.println(formatInstrCode(null,5));
            System.out.println(formatInstrCode("abccddd",0));*/
	    //System.out.println("there you will be");
		
//		String s = "29 SEP 2006";
//		try {
//			System.out.println(string2timestamp(s));
//			Date date = new Date(string2timestamp(s));
//			System.out.println(date.toLocaleString());
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	    
//		try {
//            //System.out.println(formatDate2timeStamp("2004/02/22"));
//		    System.out.println(formatSpInstrCode("  11113",4));
//            //System.out.println(hold3Decimal(new BigDecimal(0)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		System.out.println(formatInstrCode("  11113",8));
		
	}

}