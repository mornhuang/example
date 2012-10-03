/*
 * Created on 2005-8-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.channel.util;

import java.util.Iterator;
import java.util.List;

import com.itsz.sht.admin.service.ChannelMgrService;
import com.itsz.sht.admin.util.xmlparser;
import com.taifook.adminportal.model.CsParameter;

/**
 * @author lmzhang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * �滻com.itsz.admin.channel.util.ChannelParser
 */
public class ChannelParser extends xmlparser{

    public ChannelParser() throws Exception {

    }

    //����ݿ������channel����Ϣ�������ڴ�
    public void readChannelInfo(){
    	ChannelMgrService service=ChannelMgrService.getInstance();
    	
    	List channels=(List)service.findChannelsByAll();
        if(channels==null || channels.size()<1){
            return;
        }
        Iterator it=channels.iterator();
        while(it.hasNext()){
            CsParameter para=(CsParameter)it.next();
            ChannelInfo cs = new ChannelInfo();
            cs.setName(para.getDescription());
            cs.setCode(para.getKey());
            cs.setState(para.getValue());
            if(cs.getCode()!=null&&!cs.getCode().equals("")){
                ChannelInfoManagement.addChannelInfo(cs);
            }
        }
    }
    
    //�޸�һ��channel��״̬Ϊͣ��
    public void stopChannel(String[] channelcode){    	
        if(channelcode==null){
            return;
        }
        for(int i=0;i<channelcode.length;i++){
            setChannelState(channelcode[i],"stopped");
        }
    }
    
    //�޸�һ��channel��״̬Ϊ����
    public void startChannel(String[] channelcode){
        if(channelcode==null){
            return;
        }
        for(int i=0;i<channelcode.length;i++){
            setChannelState(channelcode[i],"started");
        }
    }
    
    //����һ��channel��״̬
    public void setChannelState(String channelcode,String state){
    	ChannelMgrService.getInstance().setChannelState(channelcode,state);
    }
    
    //�ж��û���������Ƿ���ȷ
    public boolean checkPassword(String username,String password){
    	return true;
    }
    
    //�޸ĵ�¼����
    public void changePassword(String username,String newpassword){

    }
}
