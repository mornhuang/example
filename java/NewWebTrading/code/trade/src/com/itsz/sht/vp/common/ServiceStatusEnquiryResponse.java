package com.itsz.sht.vp.common;

import java.io.Serializable;

/**
 * 
 * $Id: ServiceStatusEnquiryResponse.java,v 1.1 2011/03/02 10:30:36 pbxie Exp $
 * @Project:portal
 * @File:ServiceStatusEnquiryResponse.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class ServiceStatusEnquiryResponse implements Serializable{

    private static final long serialVersionUID = 7193442554836966778L;
    public static final String SERVICE_NAME_FUND_TX = "Fund Transfer";
    public static final String SERVICE_NAME_PPS = "IPPS";
    public static final String SERVICE_NAME_BOC = "BOC";
    
    public static final String SERVICE_STATUS_ACTIVE = "ACTIVE";
    public static final String SERVICE_STATUS_INACTIVE = "INACTIVE";
    public static final String SERVICE_STATUS_SUSPEND = "SUSPEND";

    
    private String serviceName;
    private String status;
    private String validPeriod;
    private String remarks;
    private String remarks_en_US;
    private String remarks_zh_TW;
    private String remarks_zh_CN;
    private boolean isWithinValidPeriod;
    
    public String getServiceName() {
        return this.serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getValidPeriod() {
        return this.validPeriod;
    }
    
    public void setValidPeriod(String validPeriod) {
        this.validPeriod = validPeriod;
    }
    
    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getRemarks_en_US() {
        return this.remarks_en_US;
    }
    
    public void setRemarks_en_US(String remarks_en_US) {
        this.remarks_en_US = remarks_en_US;
    }

    public String getRemarks_zh_TW() {
        return this.remarks_zh_TW;
    }
    
    public void setRemarks_zh_TW(String remarks_zh_TW) {
        this.remarks_zh_TW = remarks_zh_TW;
    }

    public String getRemarks_zh_CN() {
        return this.remarks_zh_CN;
    }
    
    public void setRemarks_zh_CN(String remarks_zh_CN) {
        this.remarks_zh_CN = remarks_zh_CN;
    }
    
    public boolean IsWithinValidPeriod() {
        return this.isWithinValidPeriod;
    }
    
    public void setIsWithinValidPeriod(boolean isWithinValidPeriod) {
        this.isWithinValidPeriod = isWithinValidPeriod;
    }   
}
