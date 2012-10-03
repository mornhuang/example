/*
 * Created on 2005-8-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.parameter.util;

import java.net.InetAddress;
import java.util.Hashtable;

/**
 * @author lmzhang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ParaManagement {

    private static Hashtable paraTable=new Hashtable();
    
    //�Ѽ��ֵѹ�뵽Hashtable����
    public static void putParaTable(String key,String value){
        System.out.println("==========put paraTable key="+key+" value="+value);
        paraTable.put(key,value);
    }
    
    //��ݼ�����ֵ
    public static String getParaValueByKey(String key){
        if(paraTable.containsKey(key)){
            return paraTable.get(key).toString();
        }
        return null;
    }
    
    //�ж��Ƿ��ü�
    public static boolean containsKey(String key){
        return paraTable.containsKey(key);
    }
    
    //ɾ��һ���
    public static void remove(String key){
        paraTable.remove(key);
    }
    
    public static Hashtable getAllParas(){
        return paraTable;
    }
    
    //��ñ���ip
    public static String getLocalHostAddress(){
        String ip ;
        try{
	        InetAddress addr = InetAddress.getLocalHost();
	        ip=addr.getHostAddress().toString();//��ñ���IP
	    }catch(Exception e){
	        System.out.println("Bad IP Address!"+e);
	        return null;
	    }
	    return ip;
    }
}
