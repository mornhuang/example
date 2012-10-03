package com.taifook.adminportal.web.ipo.dto;

import java.util.Date;
import java.math.BigDecimal;

import com.taifook.adminportal.web.ipo.form.IPORequestForm;
import java.io.Serializable;

public class SimpleIPORecord
    implements Serializable {

    private String ipoCode;
    private String ipoName;
    private String stockCode;

    private Date deadLine;
    private Date tradeDate;

    private String status;

    private Long ipoMasterId;

    private String ipoName_gb;
    private String ipoName_big;

    private String applyStatus;
    private String resultStatus;

    private String ipoName_dsply;
    private String deadline_dsply;
    private String tradeDate_dsply;

    public SimpleIPORecord() {
    }

    public void reset() {

    }

    public void setIpoMasterId(Long ipoMasterId) {
        this.ipoMasterId = ipoMasterId;
    }

    public Long getIpoMasterId() {
        return ipoMasterId;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public String getIpoCode() {
        return ipoCode;
    }

    public String getIpoName() {
        return ipoName;
    }

    public String getIpoName_big() {
        return ipoName_big;
    }

    public String getIpoName_gb() {
        return ipoName_gb;
    }

    public String getStatus() {
        return status;
    }

    public String getStockCode() {
        return stockCode;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public void setIpoCode(String ipoCode) {
        this.ipoCode = ipoCode;
    }

    public void setIpoName(String ipoName) {
        this.ipoName = ipoName;
    }

    public void setIpoName_big(String ipoName_big) {
        this.ipoName_big = ipoName_big;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public void setIpoName_gb(String ipoName_gb) {
        this.ipoName_gb = ipoName_gb;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getIpoName_dsply() {
        return ipoName_dsply;
    }

    public void setIpoName_dsply(String ipoName_dsply) {
        this.ipoName_dsply = ipoName_dsply;
    }

    public String getDeadline_dsply() {
        return deadline_dsply;
    }

    public void setDeadline_dsply(String deadline_dsply) {
        this.deadline_dsply = deadline_dsply;
    }

    public String getTradeDate_dsply() {
        return tradeDate_dsply;
    }

    public void setTradeDate_dsply(String tradeDate_dsply) {
        this.tradeDate_dsply = tradeDate_dsply;
    }

}
