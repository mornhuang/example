/*
 * Created on 2005-8-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.broadcast.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.itsz.sht.common.Constants;

/**
 * @author lmzhang
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BroadcastManagement {

    //private static List broadcasts;
    private static List broadcastlist;
    
    /**
     * @return Returns the broadcastlist.
     */
    public static List getBroadcastlist() {
        return broadcastlist;
    }
    /**
     * @param broadcastlist The broadcastlist to set.
     */
    public static void setBroadcastlist(List broadcastlist) {
        BroadcastManagement.broadcastlist = broadcastlist;
    }
    
    //��ȡ����ָ�����Ժ�channel�ĵ�ǰ�㲥������ֵΪBroadcast���͵�List
    public static List getCurrentBroadcast(String lang,String channel){
        if(broadcastlist==null){
            return null;
        }
        List current = new ArrayList();
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat(Constants.BroadTimeFormat);
        for(int i=0;i<broadcastlist.size();i++){
            BroadcastInfo broad = (BroadcastInfo)broadcastlist.get(i);
            if(broad.getChannels().indexOf(channel)>=0){
                try{
                    Date stime = format.parse(broad.getStime());
                    Date etime = format.parse(broad.getEtime());
    	            if(time.getTime()>stime.getTime() && time.getTime()<etime.getTime()){    	                
    	                if(lang.equals("en_US") && StringUtils.isNotBlank(broad.getContent_en_US())){
    	                	Broadcast info = new Broadcast();
    	                    info.setContent(broad.getContent_en_US());
    	                    BeanUtils.copyProperties(info,broad);
    	                    current.add(info);
    	                }else if(lang.equals("zh_CN") && StringUtils.isNotBlank(broad.getContent_zh_CN())){
    	                	Broadcast info = new Broadcast();
    	                    info.setContent(broad.getContent_zh_CN());
    	                    BeanUtils.copyProperties(info,broad);
    	                    current.add(info);
    	                }else if(lang.equals("zh_TW") && StringUtils.isNotBlank(broad.getContent_zh_TW())){
    	                	Broadcast info = new Broadcast();
    	                    info.setContent(broad.getContent_zh_TW());
    	                    BeanUtils.copyProperties(info,broad);
    	                    current.add(info);
    	                }
    	            }
                }catch(Exception e){
//                    System.out.println("error get time");
//                    e.printStackTrace();
                }
            }
        }
        return current;
    }

    //����һ��㲥��Ϣ
    public static void addBroadcast(BroadcastInfo broad){
        if(broadcastlist==null){
            broadcastlist = new ArrayList();
        }
        broadcastlist.add(broad);
    }
    
    //��������޸�һ��㲥��Ϣ����ȡ�ļ�ʱ�õ�
    public static void modBroadcastByLang(BroadcastInfo broad,String lang){
        if(broadcastlist==null){
            broadcastlist = new ArrayList();
        }
        for(int i=0;i<broadcastlist.size();i++){
            BroadcastInfo broaditem = (BroadcastInfo)broadcastlist.get(i);
            if(broaditem.getId().equals(broad.getId())){
                if(lang.equals("en_US")){
                    broaditem.setContent_en_US(broad.getContent_en_US());
                }else if(lang.equals("zh_CN")){
                    broaditem.setContent_zh_CN(broad.getContent_zh_CN());
                }else if(lang.equals("zh_TW")){
                    broaditem.setContent_zh_TW(broad.getContent_zh_TW());
                }
                broadcastlist.set(i,broaditem);
                return;
            }
        }
        broadcastlist.add(broad);
    }

    //�޸Ĺ㲥
    public static void modBroadcast(BroadcastInfo broad){
        if(broadcastlist==null){
            return;
        }
        for(int i=0;i<broadcastlist.size();i++){
            BroadcastInfo broaditem = (BroadcastInfo)broadcastlist.get(i);
            if(broaditem.getId().equals(broad.getId())){
                broadcastlist.set(i,broad);
                return;
            }
        }
    }
    
    //ɾ��һ��㲥��Ϣ
    public static void removeBroadcast(BroadcastInfo broad){
        if(broadcastlist.contains(broad)){
            broadcastlist.remove(broad);
        }
    }
    
    //ɾ��һ��㲥��Ϣ
    public static void removeBroadcast(String id){
        if(broadcastlist==null){
            broadcastlist = new ArrayList();
        }
        
        for(int i=0;i<broadcastlist.size();i++){
            BroadcastInfo broad = (BroadcastInfo)broadcastlist.get(i);
            if(id.equals(broad.getId())){
                broadcastlist.remove(i);
                return;
            }
        }
    }

    //��ȡһ��㲥��Ϣ
    public static BroadcastInfo getBroadcastById(String id){
        for(int i=0;i<broadcastlist.size();i++){
            BroadcastInfo broad = (BroadcastInfo)broadcastlist.get(i);
            if(id.equals(broad.getId())){
                return broad;
            }
        }
        return null;
    }

    //�ж�һ��㲥�Ƿ��Ѿ�����
    public static boolean isBroadcastExist(String id){
        if(broadcastlist==null){
            return false;
        }
        for(int i=0;i<broadcastlist.size();i++){
            BroadcastInfo broad = (BroadcastInfo)broadcastlist.get(i);
            if(id.equals(broad.getId())){
                return true;
            }
        }
        return false;
    }
    
    //�ж�һ��㲥�Ƿ��޸Ĺ�
    public static String isBroadcastModified(String id,String lastmodifytime){
        if(broadcastlist==null){
            return "noexist";
        }
        for(int i=0;i<broadcastlist.size();i++){
            BroadcastInfo broad = (BroadcastInfo)broadcastlist.get(i);
            if(id.equals(broad.getId())){
                if(lastmodifytime.equals(broad.getLastmodifytime())){
                    return "n";
                }else{
                    return "y";
                }
            }
        }
        return "noexist";
    }
}
