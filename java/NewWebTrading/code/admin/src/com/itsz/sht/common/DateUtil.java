package com.itsz.sht.common;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public final class DateUtil {

    public final static String[] monthAbbr = {"Jan","Feb","Mar","Apr",
                                        "May","Jun","Jul","Aug",
                                        "Sep","Oct","Nov","Dec"
                                        };
    public static Timestamp getDate(int year,int month,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year,month-1,day);
        return new Timestamp( calendar.getTime().getTime() );
    }

    public static Timestamp getLastDateofMonth(Timestamp date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return new Timestamp(calendar.getTime().getTime());
    }
    public static Date getSQLDate(int year,int month,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year,month-1,day);
        return new Date( calendar.getTime().getTime() );
    }
    public static Date getBeforeMonthLastDateofMonth(java.util.Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));        
        return new Date(calendar.getTime().getTime());
    }
    public static Date getBeforeMonthFirstDateofMonth(java.util.Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY,00);
        calendar.set(Calendar.MINUTE,00);
        calendar.set(Calendar.SECOND,1);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return new Date(calendar.getTime().getTime());
    }

    public static Date getThisMonthLastDateofMonth(java.util.Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return new Date(calendar.getTime().getTime());
    }
    public static Date getThisMonthFirstDateofMonth(java.util.Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY,00);        
        calendar.set(Calendar.MINUTE,00);
        calendar.set(Calendar.SECOND,1);
        return new Date(calendar.getTime().getTime());
    }
    public static Date getNextMonthLastDateofMonth(java.util.Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));        
        return new Date(calendar.getTime().getTime());
    }
    public static Date getNextMonthFirstDateofMonth(java.util.Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,00);
        calendar.set(Calendar.MINUTE,00);
        calendar.set(Calendar.SECOND,1);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return new Date(calendar.getTime().getTime());
    }
    /**
     * get yyyy-mm-dd from parameters (yyyy,mm,dd) and convert to java.sql.Date
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static java.sql.Date getDatebyString(String year, String month, String date) {
      try {
        int startY = Integer.parseInt(year.trim());
        int startM = Integer.parseInt(month.trim());
        int startD = Integer.parseInt(date.trim());
        return java.sql.Date.valueOf(startY + "-" + startM + "-" + startD);
      }
      catch (Exception ex) {
        return null;
      }
    }

    /**
     * yyyy-mm-dd => java.sql.Date
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static java.sql.Date getDatebyString(String time) {
      try {
        return java.sql.Date.valueOf(time);
      }
      catch (Exception ex) {
        return null;
      }
    }


    /**
     * get this month 's max (int)Date by default local set
     * @param date
     * @return
     */
    public static int getMaximumDayOfMonth(java.util.Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * get this month 's max (String)Date by default local set
     * @param date
     * @return
     */
    public static int getMaximumDayOfMonth(String year, String month, String date) {
      java.util.Date tmp = getDatebyString(year, month, date);
      return getMaximumDayOfMonth(tmp);
    }

    /**
     * get Today :2003-10-24
     * @return
     */
    public static java.sql.Date getTodayDate() {
      java.sql.Date result = new java.sql.Date(System.currentTimeMillis());
      return result;
    }

    /**
     * get Today :2003-10-24
     * @return
     */
    public static java.sql.Date getTodayDateInString() {
      java.sql.Date result = new java.sql.Date(System.currentTimeMillis());
      return java.sql.Date.valueOf(result.toString());
    }
    
    /**
     * @param date
     * @return  yyyy-MM-dd HH:mm:ss
     */
    public static String getStringforDate(java.util.Date date ){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(date != null){
    		return sdf.format(date);
    	}
    	return null;
    }
    
    public static java.sql.Date getNowDateSql(){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	java.util.Date d=new java.util.Date();
    	java.sql.Date dsql=java.sql.Date.valueOf(sdf.format(d));
        return  dsql ;
    }
    public static void main(String[] args){
    	 java.util.Date date = new  java.util.Date();
    	
//    	 Calendar calendar = Calendar.getInstance(); 
//    	 calendar.add(Calendar.DATE, -1);    //得到前一天 
//    	 calendar.add(Calendar.MONTH, -1);    //得到前一个月 
//    	 int year = calendar.get(Calendar.YEAR); 
//    	 int month = calendar.get(Calendar.MONTH)+1; 

    	 
    	 
    	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   System.out.println("curryDate******"+sdf.format(date));
   System.out.println("getBeforeMonthFirstDateofMonth****"+sdf.format(DateUtil.getBeforeMonthFirstDateofMonth(date)));
   System.out.println("getBeforeMonthLastDateofMonth ****"+sdf.format(DateUtil.getBeforeMonthLastDateofMonth(date)));
   System.out.println("getThisMonthFirstDateofMonth  *****"+sdf.format(DateUtil.getThisMonthFirstDateofMonth(date)));
   System.out.println("getThisMonthLastDateofMonth   **"+sdf.format(DateUtil.getThisMonthLastDateofMonth(date)));
   System.out.println("getNextMonthFirstDateofMonth  ***"+sdf.format(DateUtil.getNextMonthFirstDateofMonth(date)));
   System.out.println("getNextMonthLastDateofMonth   ******"+sdf.format(DateUtil.getNextMonthLastDateofMonth(date)));
    }
}