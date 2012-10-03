/*
 * Created on 2005-8-16
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.channel.util;

/**
 * @author lmzhang
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ChannelInfo {

    private String name;//��ƣ���3g
    private String code;//���룬��H3G
    private String state;//״̬����Y,N
    private int usercount;//��channel�������û�����
    
    /**
     * @return Returns the usercount.
     */
    public int getUsercount() {
        return usercount;
    }
    /**
     * @param usercount The usercount to set.
     */
    public void setUsercount(int usercount) {
        this.usercount = usercount;
    }
    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }
    /**
     * @param state The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }
}
