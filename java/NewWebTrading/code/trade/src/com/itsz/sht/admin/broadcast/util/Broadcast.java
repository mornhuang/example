/*
 * Created on 2005-8-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.broadcast.util;

/**
 * @author lmzhang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Broadcast {
    private String id;
    private String stime;//��Ч�ڿ�ʼʱ��(yyyy-MM-dd hh:mm:ss)
    private String etime;
    private String level;//��������,common,urgent
    private String contenttype;	//�㲥���Market/maintenance/promotion
    private String content;//�㲥����
    private String channels;//���������channel���磺H3G,STT

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
     * @return Returns the content.
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content The content to set.
     */
    public void setContent(String content) {
        this.content = content;
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
}
