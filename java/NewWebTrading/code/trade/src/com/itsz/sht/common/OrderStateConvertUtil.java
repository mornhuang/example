package com.itsz.sht.common;

import java.util.Hashtable;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * $Id: OrderStateConvertUtil.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:OrderStateConvertUtil.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-03-14
 */
public class OrderStateConvertUtil {
	
	/*
	 *  
	 */
	private static Hashtable<String, String> orderStateTable = getInstance();
	
	/*
	 * orderStateTable.put("SUCCESS","*");
	 * orderStateTable.put("PROCESS","@");
	 * orderStateTable.put("FAILED","#");
	 */
//	private static Hashtable<String, String> getInstance(){
//		orderStateTable = new Hashtable<String, String>();
//	    orderStateTable.put("FILLED","F");
//		orderStateTable.put("TRANSMITTING","T");
//		orderStateTable.put("RECEIVED","R");
//		orderStateTable.put("QUEUING","Q");
//		orderStateTable.put("CANCELLED","D");
//		orderStateTable.put("REJECTED","J");
//		orderStateTable.put("COMPLETELY_FILLED","C");
//		orderStateTable.put("PARTIALLY FILLED","P");
//		orderStateTable.put("PARTIALLY_FILLED_COMPLETED","CP");
//		return orderStateTable;
//	}
//	
	private static Hashtable<String, String> getInstance(){
		orderStateTable = new Hashtable<String, String>();
		orderStateTable.put("FILLED","FIL");
		orderStateTable.put("TRANSMITTING","TRAN");
		orderStateTable.put("RECEIVED","RCV");
		orderStateTable.put("QUEUING","QUE");
		orderStateTable.put("CANCELLED","CAN");
		orderStateTable.put("REJECTED","REJ");
		orderStateTable.put("COMPLETELY_FILLED","C.FIL");
		orderStateTable.put("PARTIALLY FILLED","P.FIL");
		orderStateTable.put("PARTIALLY_FILLED_COMPLETED","P.COM");
		return orderStateTable;
	}
	
	public static String mappingOrderState(String s,String orderRemark){
		return appendPostfix((String)orderStateTable.get(s),orderRemark);
		
	}
	
	/*
	 * ���orderRemark��ֵ��mtssOrderState����ӱ�־
	 */
	public static String appendPostfix(String state,String orderRemark){
    	if(orderRemark==null){
    		return state;
    	}
        if(orderRemark.trim().endsWith("rogress")) state+="@";//In Progress
        if(orderRemark.trim().endsWith("ailed")) state+="#";//Failed
        if(orderRemark.trim().endsWith("uccess")) state+="*";//Success 
        return state;
	}
	
	public static String getAbbrRemark(String orderRemark){
		String res = "";
		if(StringUtils.isNotBlank(orderRemark)){
	        if(orderRemark.trim().endsWith("rogress")) res = "@";//In Progress
	        if(orderRemark.trim().endsWith("ailed")) res = "#";//Failed
	        if(orderRemark.trim().endsWith("uccess")) res = "*";//Success
		}
		return res;
	}

}
