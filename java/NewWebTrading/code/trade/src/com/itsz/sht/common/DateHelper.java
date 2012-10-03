package com.itsz.sht.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateHelper {
	

	public static String formatDate(Date date, String formatPattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
			return sdf.format(date);
		}
		return null;
	}
	
	public static Date formatDate2(String s) {
			Date result=null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			try {
				result=sdf.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			return result;
	}

	
	public static Date formatDate7(String s) {
		Date result=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
		try {
			result=sdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
}
    public static String formatDate1(Date date) {
    	return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    public static String formatTime(Date date) {
    	return formatDate(date, "HH:mm:ss");
    }


    public static String formatDate2(Date date) {
    	return formatDate(date, "yyyy-MM-dd HH:mm");
    }


    public static String formatDate3(Date date) {
    	return formatDate(date, "yyyy-MM-dd");
    }
    
    public static String formatDate4(Date date) {
    	return formatDate(date, "yyyy-MM-dd-HHmmss");
    }
    
    public static String formatDate5(Date date) {
    	return formatDate(date, "yyMMddHHmm");
    }   
    public static Date formatDate6(String date) {
    	return formatDate2(date);
    } 
    
    public static Date formatDate8(String date) {
    	return formatDate7(date);
    }   

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


    public static String formatDate(Date date) {
        if(date==null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = df.format(date);
        return dateStr;
    }


    public static String formatDateToMM(Date date) {
        if(date==null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = df.format(date);
        return dateStr;
    }


    public static String formatOnlyDate(Date date) {
        if(date==null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = df.format(date);
        return dateStr;
    }
    
	public static Date lastDayOfMonth(Date date) { 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		cal.roll(Calendar.DAY_OF_MONTH, -1); 
		return cal.getTime(); 
    }
	
	public static Date firstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.set(Calendar.DATE, 1); 
		return cal.getTime(); 
    }
	
	public static Date dayOfNextMonth(Date date) {
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.MONTH, -1);
		return cal.getTime();
    }
	
	public static Date firstDayOfNextMonth(Date date) {
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.add(Calendar.MONTH,1);
		cal.set(Calendar.DATE, 1);
		//cal.roll(Calendar.DATE,1);
		return cal.getTime();
    }
	
	public static Date lastdayOfNextMonth(Date date) {
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.add(Calendar.MONTH,1);
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		return cal.getTime();
    }
	
	/**
	 * 返回Day之前到现在的日期集合
	 * @param lastDay
	 * @return
	 */
    public static List<String> getBeforeOneWeekDate(int lastDay) {
    	List<String> list = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
    	for(int i =lastDay-1;i>=0;i--){
    		cal.add(Calendar.DATE, -i);
    		String strDate = formatOnlyDate(cal.getTime());
    		cal.setTime(new Date(System.currentTimeMillis()));
    		list.add(strDate);
    	}
    	return list;
    }

}
