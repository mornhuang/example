/*
 * Created on 2005-8-29
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.parameter.util;

import java.util.List;

/**
 * @author lmzhang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ParaClassInfo {

    private String id;
    private String describe;
    private List paraList;
    
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
     * @return Returns the paraList.
     */
    public List getParaList() {
        return paraList;
    }
    /**
     * @param paraList The paraList to set.
     */
    public void setParaList(List paraList) {
        this.paraList = paraList;
    }
}
