package com.itsz.sht.struts.eipo.dao;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.itsz.eipo.webservice.IpoSubscrptnNotifyDto;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ipoSubscriptionDto", propOrder = {
    "accountId",
    "allotedAmount",
    "allotedQuantity",
    "appliedAmount",
    "appliedQuantity",
    "depositAmount",
    "depositRate",
    "handlingChargeAmount",
    "id",
    "ipoId",
    "ipoSubscriptionNotificationDtoList",
    "isFaultSubscription",
    "isSplit",
    "minDepositAmount",
    "miscFeeAmount",
    "parentSubscriptionId",
    "remarks",
    "status",
    "subscriptionType",
    "tradeChannelGroupCode",
    "lastUpdatedBy",
    "lastUpdateTime",
    "createTime",
    "recordVersion",
    "tagSequence",
    "ipoName",
    "instrDsplyCode",
    "actionType",
    "initTime",
    "mrktCode",
    "offerPrice"
})

@SuppressWarnings("serial")
public class EIPOSubscriptionDto implements Serializable {
    private String accountId;
    private BigDecimal allotedAmount;
    private Long allotedQuantity;
    private BigDecimal appliedAmount;
    private Long appliedQuantity;
    private BigDecimal depositAmount;
    private BigDecimal depositRate;
    private BigDecimal handlingChargeAmount;
    private Long id;
    private Long ipoId;
    @XmlElement(nillable = true)
    private List<IpoSubscrptnNotifyDto> ipoSubscriptionNotificationDtoList;
    private Boolean isFaultSubscription;
    private Boolean isSplit;
    private BigDecimal minDepositAmount;
    private BigDecimal miscFeeAmount;
    private Long parentSubscriptionId;
    private String remarks;
    private String status;
    private String subscriptionType;
    private String tradeChannelGroupCode;
    private String lastUpdatedBy;
    private Date lastUpdateTime;
    private Date createTime;
    private Long recordVersion;
    private Long tagSequence;
    private String ipoName;
    private String instrDsplyCode;
    private String actionType;
    private Date initTime;
    private String mrktCode;
    private BigDecimal offerPrice;

	public BigDecimal getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(BigDecimal offerPrice) {
		this.offerPrice = offerPrice;
	}
	public String getMrktCode() {
		return mrktCode;
	}
	public void setMrktCode(String mrktCode) {
		this.mrktCode = mrktCode;
	}
	public Date getInitTime() {
		return initTime;
	}
	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getIpoName() {
		return ipoName;
	}
	public void setIpoName(String ipoName) {
		this.ipoName = ipoName;
	}
	public String getInstrDsplyCode() {
		return instrDsplyCode;
	}
	public void setInstrDsplyCode(String instrDsplyCode) {
		this.instrDsplyCode = instrDsplyCode;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getAllotedAmount() {
		return allotedAmount;
	}
	public void setAllotedAmount(BigDecimal allotedAmount) {
		this.allotedAmount = allotedAmount;
	}
	public Long getAllotedQuantity() {
		return allotedQuantity;
	}
	public void setAllotedQuantity(Long allotedQuantity) {
		this.allotedQuantity = allotedQuantity;
	}
	public BigDecimal getAppliedAmount() {
		return appliedAmount;
	}
	public void setAppliedAmount(BigDecimal appliedAmount) {
		this.appliedAmount = appliedAmount;
	}
	public Long getAppliedQuantity() {
		return appliedQuantity;
	}
	public void setAppliedQuantity(Long appliedQuantity) {
		this.appliedQuantity = appliedQuantity;
	}
	public BigDecimal getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}
	public BigDecimal getDepositRate() {
		return depositRate;
	}
	public void setDepositRate(BigDecimal depositRate) {
		this.depositRate = depositRate;
	}
	public BigDecimal getHandlingChargeAmount() {
		return handlingChargeAmount;
	}
	public void setHandlingChargeAmount(BigDecimal handlingChargeAmount) {
		this.handlingChargeAmount = handlingChargeAmount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIpoId() {
		return ipoId;
	}
	public void setIpoId(Long ipoId) {
		this.ipoId = ipoId;
	}
	public Boolean getIsFaultSubscription() {
		return isFaultSubscription;
	}
	public void setIsFaultSubscription(Boolean isFaultSubscription) {
		this.isFaultSubscription = isFaultSubscription;
	}
	public Boolean getIsSplit() {
		return isSplit;
	}
	public void setIsSplit(Boolean isSplit) {
		this.isSplit = isSplit;
	}
	public BigDecimal getMinDepositAmount() {
		return minDepositAmount;
	}
	public void setMinDepositAmount(BigDecimal minDepositAmount) {
		this.minDepositAmount = minDepositAmount;
	}
	public BigDecimal getMiscFeeAmount() {
		return miscFeeAmount;
	}
	public void setMiscFeeAmount(BigDecimal miscFeeAmount) {
		this.miscFeeAmount = miscFeeAmount;
	}
	public Long getParentSubscriptionId() {
		return parentSubscriptionId;
	}
	public void setParentSubscriptionId(Long parentSubscriptionId) {
		this.parentSubscriptionId = parentSubscriptionId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Long getRecordVersion() {
		return recordVersion;
	}
	public void setRecordVersion(Long recordVersion) {
		this.recordVersion = recordVersion;
	}
	public Long getTagSequence() {
		return tagSequence;
	}
	public void setTagSequence(Long tagSequence) {
		this.tagSequence = tagSequence;
	}
	public List<IpoSubscrptnNotifyDto> getIpoSubscriptionNotificationDtoList() {
		if (ipoSubscriptionNotificationDtoList == null) {
			ipoSubscriptionNotificationDtoList = new ArrayList<IpoSubscrptnNotifyDto>();
		}
		return ipoSubscriptionNotificationDtoList;
	}
	public void setIpoSubscriptionNotificationDtoList(
			List<IpoSubscrptnNotifyDto> ipoSubscriptionNotificationDtoList) {
		this.ipoSubscriptionNotificationDtoList = ipoSubscriptionNotificationDtoList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public String getTradeChannelGroupCode() {
		return tradeChannelGroupCode;
	}
	public void setTradeChannelGroupCode(String tradeChannelGroupCode) {
		this.tradeChannelGroupCode = tradeChannelGroupCode;
	}
}
