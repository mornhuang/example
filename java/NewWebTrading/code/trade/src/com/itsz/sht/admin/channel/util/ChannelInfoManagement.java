/*
 * Created on 2005-8-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.channel.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lmzhang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ChannelInfoManagement {

    private static List channelInfos;
    
    
    /**
     * @return Returns the channelInfos.
     */
    public static List getChannelInfos() {
        return channelInfos;
    }
    /**
     * @param channelInfos The channelInfos to set.
     */
    public static void setChannelInfos(List channelInfos) {
        ChannelInfoManagement.channelInfos = channelInfos;
    }
    //����һ��channelInfo
    public static void addChannelInfo(ChannelInfo cs){
        if(channelInfos==null){
            channelInfos = new ArrayList();
        }
        channelInfos.add(cs);
    }
    
    //�޸�һ��channelInfo
    public static void modChannelInfo(ChannelInfo cs){
        if(channelInfos==null){
            return;
        }
        for(int i=0;i<channelInfos.size();i++){
            ChannelInfo item = (ChannelInfo)channelInfos.get(i);
            if(cs.getCode().equals(item.getCode())){
                channelInfos.set(i,cs);
                return;
            }
        }
    }

    //�ж�һ��channel�Ƿ�ͣ��
    public static boolean isChannelStopped(String channelcode){
        if(channelInfos==null){
            return false;
        }
        for(int i=0;i<channelInfos.size();i++){
            ChannelInfo cs = (ChannelInfo)channelInfos.get(i);
            if(cs.getCode().equals(channelcode)){
                if(cs.getState().equals("stopped") || cs.getState().equals("stoppedAndClear")){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    
    //��һ��channel��״̬�޸�Ϊͣ��
    public static void stopChannel(String[] channelcode){
        if(channelcode==null){
            return;
        }
        for(int i=0;i<channelcode.length;i++){
            setChannelState(channelcode[i],"stopped");
        }
    }
    
    //��һ��channel��״̬�޸�Ϊ����
    public static void startChannel(String[] channelcode){
        if(channelcode==null){
            return;
        }
        for(int i=0;i<channelcode.length;i++){
            setChannelState(channelcode[i],"started");
        }
    }

    //����ĳ��channel��״̬
    public static void setChannelState(String channelcode,String state){
        if(channelInfos==null){
            return ;
        }
        for(int i=0;i<channelInfos.size();i++){
            ChannelInfo cs = (ChannelInfo)channelInfos.get(i);
            if(cs.getCode().equals(channelcode)){
                if(!cs.getState().equals(state)){
                    cs.setState(state);
                    channelInfos.set(i,cs);
                    return ;
                }else{
                    return ;
                }
            }
        }
    }
}
