/*
 * Created on 2005-8-10
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.broadcast.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.itsz.sht.admin.service.BroadcastMgrService;
import com.itsz.sht.admin.util.xmlparser;
import com.itsz.sht.common.Constants;
import com.taifook.adminportal.model.CsBroadcast;

/**
 * @author lmzhang
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 * �滻com.itsz.admin.broadcast.util.BroadcastParser
 */
public class BroadcastParser extends xmlparser{
    	
    public BroadcastParser(String lang) throws Exception {

        this.lang = lang;
        if("en_US".equals(lang)){
            encoding = "utf-8";
        }else if("zh_TW".equals(lang)){
            encoding = "big5";
        }else if("zh_CN".equals(lang)){
            encoding = "gb2312";
        }
    }

    //�s����еĹ㲥��Ϣ�������ڴ棬ͬʱɾ����ڹ㲥
    public void readFile(){
    	BroadcastMgrService service=BroadcastMgrService.getInstance();
    	SimpleDateFormat format = new SimpleDateFormat(Constants.BroadTimeFormat);
    	
    	List broadlist =(List)service.findByLang(this.lang);//�����Խ��в��ҹ㲥��Ϣ
    	Iterator it=broadlist.iterator();
    	while(it.hasNext()){
            BroadcastInfo broad = new BroadcastInfo();
            CsBroadcast broadCast=(CsBroadcast)it.next();
            broad.setId(broadCast.getSeqno().toString());
            broad.setLevel(broadCast.getBroadcastLevel());
            broad.setChannels(broadCast.getChannels());
            broad.setContenttype(broadCast.getContentType());
            broad.setLastmodifytime(format.format(broadCast.getLastupdatetime()));
            
            if(lang.equals("en_US")){
                broad.setContent_en_US(broadCast.getContentEnUs());
            }else if(lang.equals("zh_CN")){
                broad.setContent_zh_CN(broadCast.getContentZhCn());
            }else if(lang.equals("zh_TW")){
                broad.setContent_zh_TW(broadCast.getContentZhTw());
            }
            
            try{
	            broad.setStime(format.format(broadCast.getStarttime()));
	            broad.setEtime(format.format(broadCast.getEndtime()));
	            if(new Date().getTime()> broadCast.getEndtime().getTime()){//���ڹ㲥
	               service.delete(broadCast);
	            }else{
	                BroadcastManagement.modBroadcastByLang(broad,lang);
	            }
            }catch(Exception e){
//                System.out.println("error read broadcast");
//                e.printStackTrace();
            }
        }

    }
    
    //����ݿ��м����Ĺ㲥��Ϣ
    public void addBroadcast(BroadcastInfo broad){
    	BroadcastMgrService service=BroadcastMgrService.getInstance();
    	CsBroadcast broadCast=new CsBroadcast();
    	broadCast.setSeqno(Long.valueOf(broad.getId()));
    	broadCast.setBroadcastLevel(broad.getLevel());
    	broadCast.setChannels(broad.getChannels());
    	broadCast.setContentType(broad.getContenttype());
        if(lang.equals("en_US")){
        	broadCast.setContentEnUs(broad.getContent_en_US());
        }else if(lang.equals("zh_CN")){
        	broadCast.setContentZhCn(broad.getContent_zh_CN());
        }else if(lang.equals("zh_TW")){
        	broadCast.setContentZhTw(broad.getContent_zh_TW());
        }
        broadCast.setStarttime(new Date(broad.getStime()));     
        broadCast.setEndtime(new Date(broad.getEtime()));
        broadCast.setLastupdatetime(new Date(broad.getLastmodifytime()));
        
    	service.saveOrUpdate(broadCast);      
    }
    
    
    //����ݿ���ɾ���Ĺ㲥��Ϣ
    public void removeBroadcast(BroadcastInfo broad){
    	this.removeBroadcast(broad.getId());
    }
    
    //����ݿ���ɾ���Ĺ㲥��Ϣ
    public void removeBroadcast(String id){
    	BroadcastMgrService service=BroadcastMgrService.getInstance();
    	service.deleteByKey(id);
    }
    
    //�޸Ĺ㲥��Ϣ
    public void modBroadcast(BroadcastInfo broad){
    	BroadcastMgrService service=BroadcastMgrService.getInstance();
    	CsBroadcast broadCast=new CsBroadcast();
    	broadCast.setSeqno(Long.valueOf(broad.getId()));
    	broadCast.setBroadcastLevel(broad.getLevel());
    	broadCast.setChannels(broad.getChannels());
    	broadCast.setContentType(broad.getContenttype());
        if(lang.equals("en_US")){
        	broadCast.setContentEnUs(broad.getContent_en_US());
        }else if(lang.equals("zh_CN")){
        	broadCast.setContentZhCn(broad.getContent_zh_CN());
        }else if(lang.equals("zh_TW")){
        	broadCast.setContentZhTw(broad.getContent_zh_TW());
        }
        broadCast.setStarttime(new Date(broad.getStime()));     
        broadCast.setEndtime(new Date(broad.getEtime()));
        broadCast.setLastupdatetime(new Date(broad.getLastmodifytime()));
        
    	service.update(broadCast);    	
    }
}
