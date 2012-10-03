/*
 * Created on 2005-8-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.broadcast.util;

import java.io.Serializable;
import java.util.Collection;


import com.itsz.sht.common.Constants;
import com.taifook.mcs.core.beans.msg.MCSMessage;

/**
 * @author lmzhang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BroadcastInfo extends MCSMessage implements Serializable{
    private String id;
    private String stime;//��Ч�ڿ�ʼʱ��(yyyy-MM-dd hh:mm:ss)
    private String etime;
    private String level;//��������
    private String contenttype;	//�㲥���Market/maintenance/promotion
    private String content_en_US;//�㲥����
    private String content_zh_CN;//�㲥����
    private String content_zh_TW;//�㲥����
    private String channels;//���������channel���磺H3G,STT
    private String lastmodifytime;//����޸�ʱ��
    private Collection broadcastCol;
    public BroadcastInfo() {
        super.setMessageId(Constants.MsgID_Broadcast);
    }
    
    

    /**
	 * @return the broadcastCol
	 */
	public Collection getBroadcastCol() {
		return broadcastCol;
	}



	/**
	 * @param broadcastCol the broadcastCol to set
	 */
	public void setBroadcastCol(Collection broadcastCol) {
		this.broadcastCol = broadcastCol;
	}



	/**
     * @return Returns the lastmodifytime.
     */
    public String getLastmodifytime() {
        return lastmodifytime;
    }
    /**
     * @param lastmodifytime The lastmodifytime to set.
     */
    public void setLastmodifytime(String lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
    /**
     * @return Returns the level.
     */
    public String getLevel() {
        return level;
    }
    /**
     * @param level The level to set.
     */
    public void setLevel(String level) {
        this.level = level;
    }
    /**
     * @return Returns the content_en_US.
     */
    public String getContent_en_US() {
        return content_en_US;
    }
    /**
     * @param content_en_US The content_en_US to set.
     */
    public void setContent_en_US(String content_en_US) {
        this.content_en_US = content_en_US;
    }
    /**
     * @return Returns the content_zh_CN.
     */
    public String getContent_zh_CN() {
        return content_zh_CN;
    }
    /**
     * @param content_zh_CN The content_zh_CN to set.
     */
    public void setContent_zh_CN(String content_zh_CN) {
        this.content_zh_CN = content_zh_CN;
    }
    /**
     * @return Returns the content_zh_TW.
     */
    public String getContent_zh_TW() {
        return content_zh_TW;
    }
    /**
     * @param content_zh_TW The content_zh_TW to set.
     */
    public void setContent_zh_TW(String content_zh_TW) {
        this.content_zh_TW = content_zh_TW;
    }
    /**
     * @return Returns the etime.
     */
    public String getEtime() {
        return etime;
    }
    /**
     * @param etime The etime to set.
     */
    public void setEtime(String etime) {
        this.etime = etime;
    }
    /**
     * @return Returns the stime.
     */
    public String getStime() {
        return stime;
    }
    /**
     * @param stime The stime to set.
     */
    public void setStime(String stime) {
        this.stime = stime;
    }
    /**
     * @return Returns the contenttype.
     */
    public String getContenttype() {
        return contenttype;
    }
    /**
     * @param contenttype The contenttype to set.
     */
    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }
    /**
     * @return Returns the channels.
     */
    public String getChannels() {
        return channels;
    }
    /**
     * @param channels The channels to set.
     */
    public void setChannels(String channels) {
        this.channels = channels;
    }
    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }
}
