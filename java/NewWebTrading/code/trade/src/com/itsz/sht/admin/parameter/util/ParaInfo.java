/*
 * Created on 2005-8-29
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.parameter.util;

import java.io.Serializable;

/**
 * @author lmzhang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ParaInfo implements Serializable{

    private String classid;
    private String describe;
    private String key;
    private String value;

    /**
     * @return Returns the classid.
     */
    public String getClassid() {
        return classid;
    }
    /**
     * @param classid The classid to set.
     */
    public void setClassid(String classid) {
        this.classid = classid;
    }
    /**
     * @return Returns the describe.
     */
    public String getDescribe() {
        return describe;
    }
    /**
     * @param describe The describe to set.
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    /**
     * @return Returns the key.
     */
    public String getKey() {
        return key;
    }
    /**
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }
    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
