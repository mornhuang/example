
package com.taifook.adminportal.web.ipo.form;

import java.util.Date;
import java.math.BigDecimal;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @struts.form name="IPOAddForm"
 */

public class IPOAddForm extends ActionForm {

    private String accountId;
    private String ipoCode;
    private String ipoName;
    private String stockCode;
    private BigDecimal price;
    private int quantity;
    private int shareAmt;
    private BigDecimal handFee;
    private Date deadLine;
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

    private String password;
    private Long ipoMasterId;

    private String ipoName_gb;
    private String ipoName_big;
    private String pageNo;
    private String deadLine_dsply;
    private String depositDate_dsply;
    private String debitDate_dsply;
    private String debitDate_dsply_mng;

    private String prop_url_en;
    private String prop_url_cn;
    private String prop_url_tw;


    public String getAccountId() {
        return accountId;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public Date getDeadLine() {
        return deadLine;
    }
    public Date getDepositDate() {
        return depositDate;
    }
    public BigDecimal getHandFee() {
        return handFee;
    }
    public String getIpoCode() {
        return ipoCode;
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
    public String getPassword() {
        return password;
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
    public int getShareAmt() {
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
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
    public void setShareAmt(int shareAmt) {
        this.shareAmt = shareAmt;
    }
    public void setShrCollectDate(Date shrCollectDate) {
        this.shrCollectDate = shrCollectDate;
    }
    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setMinDpstRate(BigDecimal minDpstRate) {
        this.minDpstRate = minDpstRate;
    }
    public void setLoanIntRate(BigDecimal loanIntRate) {
        this.loanIntRate = loanIntRate;
    }
    public void setLastAmendDate(Date lastAmendDate) {
        this.lastAmendDate = lastAmendDate;
    }
    public void setIpoName(String ipoName) {
        this.ipoName = ipoName;
    }
    public void setIpoCode(String ipoCode) {
        this.ipoCode = ipoCode;
    }
    public void setHandFee(BigDecimal handFee) {
        this.handFee = handFee;
    }
    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }
    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getIpoName_big() {
        return ipoName_big;
    }
    public String getIpoName_gb() {
        return ipoName_gb;
    }
    public void setIpoName_big(String ipoName_big) {
        this.ipoName_big = ipoName_big;
    }
    public void setIpoName_gb(String ipoName_gb) {
        this.ipoName_gb = ipoName_gb;
    }
    public Long getIpoMasterId() {
        return ipoMasterId;
    }
    public void setIpoMasterId(Long ipoMasterId) {
        this.ipoMasterId = ipoMasterId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPageNo() {
        return pageNo;
    }
    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }
    public String getDepositDate_dsply() {
        return depositDate_dsply;
    }
    public void setDepositDate_dsply(String depositDate_dsply) {
        this.depositDate_dsply = depositDate_dsply;
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
    public Date getDebitDate() {
        return debitDate;
    }
    public String getDebitDate_dsply() {
        return debitDate_dsply;
    }
    public void setDebitDate(Date debitDate) {
        this.debitDate = debitDate;
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
//  add by jhu
    public String getDeadLine_dsply() {
        return deadLine_dsply;
    }
    public void setDeadLine_dsply(String deadLine_dsply) {
        this.deadLine_dsply = deadLine_dsply;
    }

}

