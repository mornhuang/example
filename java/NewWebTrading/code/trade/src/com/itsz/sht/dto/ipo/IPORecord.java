
package com.itsz.sht.dto.ipo;

import java.util.Date;
import java.math.BigDecimal;

import java.io.Serializable;

public class IPORecord implements Serializable {

    private String ipoCode;
    private String ipoName;
    private String stockCode;
    private BigDecimal price;
    private int quantity;
    private BigDecimal shareAmt;
    private BigDecimal handFee;
    private Date  deadLine;
    private Date depositDate;
    private Date refundDate;
    private Date shrCollectDate;
    private Date tradeDate;
    private BigDecimal loanIntRate;
    private BigDecimal minDpstRate;
    private Date createDate;
    private Date lastAmendDate;
    private String status;

    private Date debitDate;
    //private float brgRate;
    //private int priorityId;

    private String fee;

    private Long ipoMasterId;

    private String deadLine_dsply;
    private String depositDate_dsply;
    private String refundDate_dsply;
    private String shrCollectDate_dsply;
    private String tradeDate_dsply;
    private String createDate_dsply;
    private String lastAmendDate_dsply;
    private String debitDate_dsply;
    private String debitDate_dsply_mng;

    private String ipoName_gb;
    private String ipoName_big;
    private String ipoName_dsply;

    private String price_dsply;
    private String quantity_dsply;
    private String shareAmt_dsply;

    private String prop_url_en;
    private String prop_url_cn;
    private String prop_url_tw;

    private String prop_url_dsply;


    public Date getCreateDate() {
        return createDate;
    }
    public Date getDeadLine() {
        return deadLine;
    }
    public Date getDepositDate() {
        return depositDate;
    }
    public String getFee() {
        return fee;
    }
    public BigDecimal getHandFee() {
        return handFee;
    }
    public String getIpoCode() {
        return ipoCode;
    }
    public Long getIpoMasterId() {
        return ipoMasterId;
    }
    public String getIpoName() {
        return ipoName;
    }
    public Date getLastAmendDate() {
        return lastAmendDate;
    }
    public BigDecimal getLoanIntRate() {
        return loanIntRate;
    }
    public BigDecimal getMinDpstRate() {
        return minDpstRate;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public Date getRefundDate() {
        return refundDate;
    }
    public BigDecimal getShareAmt() {
        return shareAmt;
    }
    public Date getShrCollectDate() {
        return shrCollectDate;
    }
    public String getStockCode() {
        return stockCode;
    }
    public Date getTradeDate() {
        return tradeDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }
    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }
    public void setFee(String fee) {
        this.fee = fee;
    }
    public void setHandFee(BigDecimal handFee) {
        this.handFee = handFee;
    }
    public void setIpoCode(String ipoCode) {
        this.ipoCode = ipoCode;
    }
    public void setIpoMasterId(Long ipoMasterId) {
        this.ipoMasterId = ipoMasterId;
    }
    public void setIpoName(String ipoName) {
        this.ipoName = ipoName;
    }
    public void setLastAmendDate(Date lastAmendDate) {
        this.lastAmendDate = lastAmendDate;
    }
    public void setLoanIntRate(BigDecimal loanIntRate) {
        this.loanIntRate = loanIntRate;
    }
    public void setMinDpstRate(BigDecimal minDpstRate) {
        this.minDpstRate = minDpstRate;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }
    public void setShrCollectDate(Date shrCollectDate) {
        this.shrCollectDate = shrCollectDate;
    }
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }
    public String getCreateDate_dsply() {
        return createDate_dsply;
    }
    public String getDeadLine_dsply() {
        return deadLine_dsply;
    }
    public void setCreateDate_dsply(String createDate_dsply) {
        this.createDate_dsply = createDate_dsply;
    }
    public void setDeadLine_dsply(String deadLine_dsply) {
        this.deadLine_dsply = deadLine_dsply;
    }
    public String getDepositDate_dsply() {
        return depositDate_dsply;
    }
    public void setDepositDate_dsply(String depositDate_dsply) {
        this.depositDate_dsply = depositDate_dsply;
    }
    public void setLastAmendDate_dsply(String lastAmendDate_dsply) {
        this.lastAmendDate_dsply = lastAmendDate_dsply;
    }
    public String getLastAmendDate_dsply() {
        return lastAmendDate_dsply;
    }
    public String getRefundDate_dsply() {
        return refundDate_dsply;
    }
    public void setRefundDate_dsply(String refundDate_dsply) {
        this.refundDate_dsply = refundDate_dsply;
    }
    public void setShrCollectDate_dsply(String shrCollectDate_dsply) {
        this.shrCollectDate_dsply = shrCollectDate_dsply;
    }
    public String getShrCollectDate_dsply() {
        return shrCollectDate_dsply;
    }
    public String getTradeDate_dsply() {
        return tradeDate_dsply;
    }
    public void setTradeDate_dsply(String tradeDate_dsply) {
        this.tradeDate_dsply = tradeDate_dsply;
    }
    public String getIpoName_gb() {
        return ipoName_gb;
    }
    public String getIpoName_big() {
        return ipoName_big;
    }
    public void setIpoName_big(String ipoName_big) {
        this.ipoName_big = ipoName_big;
    }
    public void setIpoName_gb(String ipoName_gb) {
        this.ipoName_gb = ipoName_gb;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getIpoName_dsply() {
        return ipoName_dsply;
    }
    public void setIpoName_dsply(String ipoName_dsply) {
        this.ipoName_dsply = ipoName_dsply;
    }
    public String getPrice_dsply() {
        return price_dsply;
    }
    public void setPrice_dsply(String price_dsply) {
        this.price_dsply = price_dsply;
    }
    public String getQuantity_dsply() {
        return quantity_dsply;
    }
    public void setQuantity_dsply(String quantity_dsply) {
        this.quantity_dsply = quantity_dsply;
    }
    public void setShareAmt_dsply(String shareAmt_dsply) {
        this.shareAmt_dsply = shareAmt_dsply;
    }
    public String getShareAmt_dsply() {
        return shareAmt_dsply;
    }
    public String getProp_url_cn() {
        return prop_url_cn;
    }
    public String getProp_url_en() {
        return prop_url_en;
    }
    public String getProp_url_tw() {
        return prop_url_tw;
    }
    public void setProp_url_cn(String prop_url_cn) {
        this.prop_url_cn = prop_url_cn;
    }
    public void setProp_url_en(String prop_url_en) {
        this.prop_url_en = prop_url_en;
    }
    public void setProp_url_tw(String prop_url_tw) {
        this.prop_url_tw = prop_url_tw;
    }
    public String getProp_url_dsply() {
        return prop_url_dsply;
    }
    public void setProp_url_dsply(String prop_url_dsply) {
        this.prop_url_dsply = prop_url_dsply;
    }
    public Date getDebitDate() {
        return debitDate;
    }
    public void setDebitDate(Date debitDate) {
        this.debitDate = debitDate;
    }
    public String getDebitDate_dsply() {
        return debitDate_dsply;
    }
    public void setDebitDate_dsply(String debitDate_dsply) {
        this.debitDate_dsply = debitDate_dsply;
    }
    public String getDebitDate_dsply_mng() {
        return debitDate_dsply_mng;
    }
    public void setDebitDate_dsply_mng(String debitDate_dsply_mng) {
        this.debitDate_dsply_mng = debitDate_dsply_mng;
    }
    /*
    public float getBrgRate() {
        return brgRate;
    }
    public void setBrgRate(float brgRate) {
        this.brgRate = brgRate;
    }
    public int getPriorityId() {
        return priorityId;
    }
    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }
    */

}

