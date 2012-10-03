
package com.taifook.adminportal.web.ipo.dto;


import java.io.Serializable;


public class IPOResultStatus implements Serializable {

    private String resultStatus;
    private String recordCount;

    public void reset() {

    }

    public String getRecordCount() {
        return recordCount;
    }
    public String getResultStatus() {
        return resultStatus;
    }
    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }
    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }


}

