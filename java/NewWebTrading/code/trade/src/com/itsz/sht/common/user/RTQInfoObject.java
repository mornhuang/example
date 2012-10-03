/*
 * Created on 2005-4-11
 *
 */
package com.itsz.sht.common.user;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.PropertyConfig;

/**
 * @author wzzhan
 */
public class RTQInfoObject implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @return Returns the clickCount.
	 */
	public boolean isActive=true;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.ENGLISH);
	private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS", java.util.Locale.ENGLISH);
	private static Date today = null;
	private static long preLongTime=0;//��ɽ���ʱ��u�ʱ��
	private static long delayTime=0;//trade time��charge time����ʱʱ��
	private static final String opTime1 = "09:30:00:000";
	private static final String coTime1 = "12:30:00:001";
	private static final String opTime2 = "14:30:00:000";
	private static final String coTime2 = "16:00:00:001";
	private static final String opTime3 = "09:30:00:000";
	private static final String coTime3 = "16:00:00:001";
	private static long opTime1L;
	private static long coTime1L;
	private static long opTime2L;
	private static long coTime2L;
	private static long opTime3L;
	private static long coTime3L;
	public static  void setTime(Date date) {
		String todayStr = dateFormat.format(date);
		String delaytimestr = PropertyConfig.getCommonProperty("DelayRTQDelayTime");
		if(delaytimestr == null){
			delaytimestr = "60";
		}
		delayTime = Long.parseLong(delaytimestr)*60;
		try {
			opTime1L = dateFormat2.parse(todayStr + " " + opTime1).getTime() / 1000;
			coTime1L = dateFormat2.parse(todayStr + " " + coTime1).getTime() / 1000;
			opTime2L = dateFormat2.parse(todayStr + " " + opTime2).getTime() / 1000;
			coTime2L = dateFormat2.parse(todayStr + " " + coTime2).getTime() / 1000;
			opTime3L = dateFormat2.parse(todayStr + " " + opTime3).getTime() / 1000;
			coTime3L = dateFormat2.parse(todayStr + " " + coTime3).getTime() / 1000;
        } catch (Exception e) {
//			e.printStackTrace();
		}
	}
	
	//�Ƿ�����ͨ��Ʊ�Ľ���ʱ����
	public static boolean isTradeTime() {
	    today = new Date();
	    
	    if(isWeekend(today) || isHoliday(today)){//����ĩ ���\����
	        return false;
	    }

		try {
			
			long now=System.currentTimeMillis()/1000;
			if(preLongTime==0){
				setTime(today);
				preLongTime=System.currentTimeMillis()/1000;
			}
			if(now-preLongTime>60){//ÿ��һ�����������ʱ���
				setTime(today);
				preLongTime=now;
			}
			if ((now >= opTime1L && now <= coTime1L)|| (now >= opTime2L && now <= coTime2L)) {
				 return true;
			}else{
                 return false;
            }
		} catch (Exception eeee) {
			eeee.printStackTrace();
			return false;
		}
	}
	
	//�Ƿ��ڽ���ʱ����
	public static boolean isTradeTime(String stockcode) {
		if(isUSAStock(stockcode)){
			return isUSATradeTime();
		}else{
			return isTradeTime();
		}
	}
	
	//�Ƿ�Ϊ���⽻��ʱ��
	public static boolean isUSATradeTime() {
	    today = new Date();
	    
	    if(isWeekend(today) || isHoliday(today)){//����ĩ ���\����
	        return false;
	    }

		try {
			
			long now=System.currentTimeMillis()/1000;
			if(preLongTime==0){
				setTime(today);
				preLongTime=System.currentTimeMillis()/1000;
			}
			if(now-preLongTime>60){
				setTime(today);
				preLongTime=now;
			}
			if (now >= opTime3L && now <= coTime3L) {
				 return true;
			}else{
                 return false;
            }
		} catch (Exception eeee) {
			eeee.printStackTrace();
			return false;
		}
	}
	
	public int getClickCount() {
		if(clickCount>=initCount){
			return initCount;
		}
		return clickCount;
	}
	/**
	 * @param clickCount The clickCount to set.
	 */
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	private boolean isAccessLimit=true;
	/**
	 * �ɿ�����
	 */
	private int allowAccessCount=0;
	private int clickCount=0;
	private int initCount=0;
	/**
	 * @return Returns the allowAccessCount.
	 */
	public int getAllowAccessCount() {
		return allowAccessCount;
	}
	/**
	 * @param allowAccessCount The allowAccessCount to set.
	 */
	public void setAllowAccessCount(int allowAccessCount) {
		this.allowAccessCount = allowAccessCount;
		initCount=allowAccessCount;
	}
	/**
	 * ����һ��
	 *
	 */

	public void click(int i){
		allowAccessCount=allowAccessCount-i;
		clickCount=clickCount+i;
		System.out.println("+++++++++++++++++++++++++++++++++++++++++totalclickCount="+clickCount);
		if(allowAccessCount<=0){
			isActive=false;
		}
	}
	
	public void clickRestore(){
		allowAccessCount++;
		clickCount--;
	}
	public void clickRestoreX(int i){
		allowAccessCount=allowAccessCount+i;
		clickCount-=i;
	}
	public static void main(String[] args) {
	    isTradeTime();
	}
	/**
	 * @return Returns the isAccessLimit.
	 */
	public boolean isAccessLimit() {
		return isAccessLimit;
	}
	/**
	 * @param isAccessLimit The isAccessLimit to set.
	 */
	public void setAccessLimit(boolean isAccessLimit) {
		this.isAccessLimit = isAccessLimit;
	}
	
	public static boolean isWeekend(Date date) {
		if(date.getDay()==6||date.getDay()==0){//�Ƿ����ĩ
	        System.out.println("===========================����ĩ========================");
	        return true;
	    }
		return false;
	}
	
	public static boolean isHoliday(Date date) {
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    String datestr = sdf.format(date);
		String holidays=PropertyConfig.getCommonProperty(Constants.Holidays);
		Pattern pat = Pattern.compile(datestr);
		Matcher m = pat.matcher(holidays);
		if(m.find()){//�Ƿ�鹫�\����
		    System.out.println("===========================�ǹ��ڼ���========================");
		    return true;
		}
		return false;
	}
	
	//�Ƿ�Ϊ�����Ʊ
	public static boolean isUSAStock(String stockcode) {
		String stockcodes=PropertyConfig.getCommonProperty("USAStockCode");
		Pattern pat = Pattern.compile(stockcode);
		Matcher m = pat.matcher(stockcodes);
		if(m.find()){//�Ƿ�������Ʊ
		    System.out.println("===========================�������Ʊ========================");
		    return true;
		}
		return false;
	}
	
	//�Ƿ�����ͨ��Ʊ���շ�ʱ����
	public static boolean isChargeTime() {
		today = new Date();
	    
	    if(isWeekend(today) || isHoliday(today)){//����ĩ ���\����
	        return false;
	    }

		try {
			
			long now=System.currentTimeMillis()/1000;
			if(preLongTime==0){
				setTime(today);
				preLongTime=System.currentTimeMillis()/1000;
			}
			if(now-preLongTime>60){
				setTime(today);
				preLongTime=now;
			}
			if ((now >= opTime1L && now <= coTime1L + delayTime)|| (now >= opTime2L && now <= coTime2L + delayTime)) {
				 return true;
			}else{
                 return false;
            }
		} catch (Exception eeee) {
			eeee.printStackTrace();
			return false;
		}
	}
	
//	�Ƿ��������շ�ʱ����
	public static boolean isUSAChargeTime() {
		today = new Date();
	    
	    if(isWeekend(today) || isHoliday(today)){//����ĩ ���\����
	        return false;
	    }

		try {
			
			long now=System.currentTimeMillis()/1000;
			if(preLongTime==0){
				setTime(today);
				preLongTime=System.currentTimeMillis()/1000;
			}
			if(now-preLongTime>60){
				setTime(today);
				preLongTime=now;
			}
			if (now >= opTime3L && now <= coTime3L + delayTime) {
				 return true;
			}else{
                 return false;
            }
		} catch (Exception eeee) {
			eeee.printStackTrace();
			return false;
		}
	}
	
	//�Ƿ����շ�ʱ����
	public static boolean isChargeTime(String stockcode) {
		if(isUSAStock(stockcode)){
			return isUSAChargeTime();
		}else{
			return isChargeTime();
		}
	}
	
	//���channelcode�жϵ�ǰ�Ƿ�Ӧ���շѣ�0-���շѣ�1-j����ʹ󸣶�Ҫ�շѣ�2-j����Ҫ�շѣ����󸣲��շ�
	public static String getChargeFlag(String channelcode, String stockcode){
		if(Constants.CHANNELCODE_3G.equals(channelcode)){
			if(isUSAStock(stockcode)){
				if(isUSATradeTime()){
					return Constants.CHARGEFLAG_CHARGE;
				}else if(isUSAChargeTime()){
					return Constants.CHARGEFLAG_TFFREE;
				}else{
					return Constants.CHARGEFLAG_FREE;
				}
			}else{
				if(isTradeTime()){
					return Constants.CHARGEFLAG_CHARGE;
				}else if(isChargeTime()){
					return Constants.CHARGEFLAG_TFFREE;
				}else{
					return Constants.CHARGEFLAG_FREE;
				}
			}
		}else{
			if(isChargeTime(stockcode)){
				return Constants.CHARGEFLAG_CHARGE;
			}else{
				return Constants.CHARGEFLAG_FREE;
			}
		}
	}
	
	
}
