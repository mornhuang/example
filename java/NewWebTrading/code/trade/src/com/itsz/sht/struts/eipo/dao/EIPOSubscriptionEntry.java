package com.itsz.sht.struts.eipo.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.util.MessageResources;

import com.itsz.eipo.common.Constants;
import com.itsz.eipo.webservice.IpoSubscriptionDto;
import com.itsz.eipo.webservice.IpoSubscrptnNotifyDto;
import com.itsz.sht.common.FieldUtil;
import com.itsz.sht.struts.eipo.util.EIPOClientUtil;
import com.itsz.sht.struts.eipo.util.EIPOConstants;
import com.itsz.sht.struts.eipo.util.EIPODecimalUtil;

public class EIPOSubscriptionEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long ipoId;
	private String instrDsplyCode;
	private String mrktCode;
	private String subMrktCode;
	private String ipoName;
	private Long subscriptionId;
	private String acId;
	private String subscriptionType;
	private String tradeChannelGroupCode;
	private String channelCode;
	private String subscriptionStatus;
	private String remark;
	private Boolean isFaultSubscription;
	private Boolean isSplit;
	private Long parentSubscriptionId;
	
	private BigDecimal offerPrice;
	private Long appliedQuantity;
	private BigDecimal appliedAmount;
	private BigDecimal miscFee;
	private BigDecimal minDepositAmount;
	private BigDecimal depositAmount;
	private BigDecimal handlingCharge;
	private Long allotmentQuantity;
	private BigDecimal allotedAmount;
	
	private Date initTime;
	private List<IpoSubscrptnNotifyDto> ipoSubscriptionNotificationDtoList;
	
	//自定义属性
	private Locale locale;
	private MessageResources mr;
	
	
	public EIPOSubscriptionEntry() {
		
	}
	
	public EIPOSubscriptionEntry(IpoSubscriptionDto subDto,Locale locale,MessageResources mr) {
		
		this.ipoId = subDto.getIpoId();
		this.instrDsplyCode = subDto.getInstrDsplyCode();
		this.mrktCode= subDto.getMrktCode();
		this.ipoName = subDto.getIpoName();
		this.subscriptionId = subDto.getId();
		this.acId = subDto.getAccountId();
		this.subscriptionType = subDto.getSubscriptionType();
		this.tradeChannelGroupCode = subDto.getTradeChannelGroupCode();
		this.channelCode = subDto.getTradeChannelGroupCode();
		this.subscriptionStatus = subDto.getStatus();
		this.remark = subDto.getRemarks();
		this.isFaultSubscription = subDto.isIsFaultSubscription();
		this.isSplit = subDto.isIsSplit();
		this.parentSubscriptionId = subDto.getParentSubscriptionId();
		
		this.appliedQuantity = subDto.getAppliedQuantity();
		this.appliedAmount = subDto.getAppliedAmount();
		this.miscFee = subDto.getMiscFeeAmount();
		this.minDepositAmount = subDto.getMinDepositAmount();
		this.depositAmount = subDto.getDepositAmount();
		this.handlingCharge = subDto.getHandlingChargeAmount();
		this.allotmentQuantity = subDto.getAllotedQuantity();
		this.allotedAmount = subDto.getAllotedAmount();
		this.ipoSubscriptionNotificationDtoList = subDto.getIpoSubscriptionNotificationDtoList();
		this.initTime = EIPOClientUtil.con2Date(subDto.getLastUpdateTime());
		this.offerPrice = subDto.getOfferPrice();
		//
		this.locale = locale;
		this.mr = mr;
	}
	
	/**
	 * @return the subscriptionId
	 */
	public Long getSubscriptionId() {
		return subscriptionId;
	}


	/**
	 * @param subscriptionId the subscriptionId to set
	 */
	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}


	/**
	 * @return the ipoId
	 */
	public Long getIpoId() {
		return ipoId;
	}


	/**
	 * @param ipoId the ipoId to set
	 */
	public void setIpoId(Long ipoId) {
		this.ipoId = ipoId;
	}


	/**
	 * @return the acId
	 */
	public String getAcId() {
		return acId;
	}


	/**
	 * @param acId the acId to set
	 */
	public void setAcId(String acId) {
		this.acId = acId;
	}


	/**
	 * @return the subscriptionType
	 */
	public String getSubscriptionType() {
		return subscriptionType;
	}


	/**
	 * @param subscriptionType the subscriptionType to set
	 */
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}


	/**
	 * @return the appliedQuantity
	 */
	public Long getAppliedQuantity() {
		return appliedQuantity;
	}

	public String getFormatAppliedQuantity() {
		String s = null;
		if (appliedQuantity != null) {
			s = FieldUtil.formatDouble(new Double(appliedQuantity.doubleValue()),
			"#,###");
		}
		return s;
	}

	/**
	 * @param appliedQuantity the appliedQuantity to set
	 */
	public void setAppliedQuantity(Long appliedQuantity) {
		this.appliedQuantity = appliedQuantity;
	}


	/**
	 * @return the appliedAmount
	 */
	public BigDecimal getAppliedAmount() {
		return appliedAmount;
	}

	public String getFormatAppliedAmount() {
		String s = null;
		if (appliedAmount != null) {
			s = FieldUtil.formatDouble(new Double(appliedAmount.doubleValue()),EIPOConstants.PRICE_FORMAT);
		}
		return s;
	}

	/**
	 * @param appliedAmount the appliedAmount to set
	 */
	public void setAppliedAmount(BigDecimal appliedAmount) {
		this.appliedAmount = appliedAmount;
	}


	/**
	 * @return the miscFee
	 */
	public BigDecimal getMiscFee() {
		return miscFee;
	}

	public String getFormatMiscFee() {
		String s = "0";
		if (miscFee != null) {
			s = miscFee.toString();
		}
		return s;
	}

	/**
	 * @param miscFee the miscFee to set
	 */
	public void setMiscFee(BigDecimal miscFee) {
		this.miscFee = miscFee;
	}

	
	public BigDecimal getMinDepositAmount() {
		return minDepositAmount;
	}

	public void setMinDepositAmount(BigDecimal minDepositAmount) {
		this.minDepositAmount = minDepositAmount;
	}
	
	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}
	
	public String getMinDepositFee() {
		return "100%";
	}
	
	public String getFormatMinDepositAmount() {
		String s = null;
		if (minDepositAmount != null) {
			double depositAmount = minDepositAmount.doubleValue();
			if (null != miscFee)
				depositAmount += miscFee.doubleValue();
			if (null != handlingCharge)
				depositAmount += handlingCharge.doubleValue();
			s = FieldUtil.formatDouble(depositAmount,EIPOConstants.PRICE_FORMAT);
		}
		return s;
	}
	
	public String getFormatDepositAmount() {
		String s = null;
		if (depositAmount != null) {
			s = FieldUtil.formatDouble(new Double(depositAmount.doubleValue()),EIPOConstants.PRICE_FORMAT);
		}
		return s;
	}
	
	/**
	 * @return the handlingCharge
	 */
	public BigDecimal getHandlingCharge() {
		return handlingCharge;
	}

	public String getFormatHandlingCharge() {
		String s = null;
		if (handlingCharge != null) {
			s = FieldUtil.formatDouble(new Double(handlingCharge.doubleValue()),EIPOConstants.PRICE_FORMAT);
		}
		return s;
	}
	

	/**
	 * @param handlingCharge the handlingCharge to set
	 */
	public void setHandlingCharge(BigDecimal handlingCharge) {
		this.handlingCharge = handlingCharge;
	}


	/**
	 * @return the tradeChannelGroupCode
	 */
	public String getTradeChannelGroupCode() {
		return tradeChannelGroupCode;
	}


	/**
	 * @param tradeChannelGroupCode the tradeChannelGroupCode to set
	 */
	public void setTradeChannelGroupCode(String tradeChannelGroupCode) {
		this.tradeChannelGroupCode = tradeChannelGroupCode;
	}


	/**
	 * @return the channelCode
	 */
	public String getChannelCode() {
		Locale clocale = locale != null ? locale : Locale.US;
		if (mr != null && channelCode != null) {
			mr.setReturnNull(true);
			return mr.getMessage(clocale,"label.eipo.subscription.enquiry.channel." + channelCode.toLowerCase());
		}
		else {
			return channelCode;
		}
	}


	/**
	 * @param channelCode the channelCode to set
	 */
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}


	/**
	 * @return the subscriptionStatus
	 */
	public String getSubscriptionStatus() {
		Locale clocale = locale != null ? locale : Locale.US;
		if (mr != null && subscriptionStatus != null) {
			mr.setReturnNull(true);
			return mr.getMessage(clocale,"label.eipo.subscription.state." + subscriptionStatus.toLowerCase());
		}
		else {
			return subscriptionStatus;
		}
	}


	/**
	 * @param subscriptionStatus the subscriptionStatus to set
	 */
	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}


	/**
	 * @return the allotmentQuantity
	 */
	public Long getAllotmentQuantity() {
		return allotmentQuantity;
	}

//	label.eipo.subscription.state.allotted_with_fund_returned=结果巳公布
//	label.eipo.subscription.state.allotted_with_stock_returned=结果巳公布
//	label.eipo.subscription.state.completed=结果巳公布
	public String getFormatAllotmentQuantity() {
		String s = null;
		if (allotmentQuantity != null) {
			if ("completed".equalsIgnoreCase(subscriptionStatus) 
				|| "allotted_with_fund_returned".equalsIgnoreCase(subscriptionStatus)
				|| "allotted_with_stock_returned".equalsIgnoreCase(subscriptionStatus)) {
			s = FieldUtil.formatDouble(new Double(allotmentQuantity.doubleValue()),"#,###");
			}
		}
		return s;
	}

	/**
	 * @param allotmentQuantity the allotmentQuantity to set
	 */
	public void setAllotmentQuantity(Long allotmentQuantity) {
		this.allotmentQuantity = allotmentQuantity;
	}

	/**
	 * @return the allotedAmount
	 */
	public BigDecimal getAllotedAmount() {
		return allotedAmount;
	}

	public String getFormatAllotedAmount() {
		String s = null;
		if (allotedAmount != null) {
			s = FieldUtil.formatDouble(new Double(allotedAmount.doubleValue()),EIPOConstants.PRICE_FORMAT);
		}
		return s;
	}
	
	/**
	 * @param allotedAmount the allotedAmount to set
	 */
	public void setAllotedAmount(BigDecimal allotedAmount) {
		this.allotedAmount = allotedAmount;
	}


	/**
	 * @return the remark
	 */
	public String getRemark() {
		String formatRemark = ""; //subscription status = Rejected 都顯示”閣下賬戶未有足夠款項支款按金款項”
		if (null != subscriptionStatus && mr != null
			&& EIPOConstants.SUBSCRIPTION_STATE_REJECTED.equalsIgnoreCase(subscriptionStatus.toLowerCase())){
			Locale clocale = locale != null ? locale : Locale.US;
			mr.setReturnNull(true);
			formatRemark =  mr.getMessage(clocale,"label.eipo.subscription.details.remark");
		} else {
			formatRemark = remark;
		}
		return formatRemark;
	}


	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * @return the isFaultSubscription
	 */
	public Boolean getIsFaultSubscription() {
		return isFaultSubscription;
	}


	/**
	 * @param isFaultSubscription the isFaultSubscription to set
	 */
	public void setIsFaultSubscription(Boolean isFaultSubscription) {
		this.isFaultSubscription = isFaultSubscription;
	}


	/**
	 * @return the isSplit
	 */
	public Boolean getIsSplit() {
		return isSplit;
	}


	/**
	 * @param isSplit the isSplit to set
	 */
	public void setIsSplit(Boolean isSplit) {
		this.isSplit = isSplit;
	}


	/**
	 * @return the parentSubscriptionId
	 */
	public Long getParentSubscriptionId() {
		return parentSubscriptionId;
	}


	/**
	 * @param parentSubscriptionId the parentSubscriptionId to set
	 */
	public void setParentSubscriptionId(Long parentSubscriptionId) {
		this.parentSubscriptionId = parentSubscriptionId;
	}

	/**
	 * @return the initTime
	 */
	public String getInitTime() {
		if (null != initTime) {
			return EIPOConstants.SDF_YYMMDD.format(initTime);
		}
		return "";
	}

	/**
	 * @param initTime the initTime to set
	 */
	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}


	/**
	 * @return the instrDsplyCode
	 */
	public String getInstrDsplyCode() {
		return instrDsplyCode;
	}


	/**
	 * @param instrDsplyCode the instrDsplyCode to set
	 */
	public void setInstrDsplyCode(String instrDsplyCode) {
		this.instrDsplyCode = instrDsplyCode;
	}


	/**
	 * @return the mrktCode
	 */
	public String getMrktCode() {
		return mrktCode;
	}


	/**
	 * @param mrktCode the mrktCode to set
	 */
	public void setMrktCode(String mrktCode) {
		this.mrktCode = mrktCode;
	}


	/**
	 * @return the subMrktCode
	 */
	public String getSubMrktCode() {
		return subMrktCode;
	}


	/**
	 * @param subMrktCode the subMrktCode to set
	 */
	public void setSubMrktCode(String subMrktCode) {
		this.subMrktCode = subMrktCode;
	}


	/**
	 * @return the ipoName
	 */
	public String getIpoName() {
		return ipoName;
	}


	/**
	 * @param ipoName the ipoName to set
	 */
	public void setIpoName(String ipoName) {
		this.ipoName = ipoName;
	}


	/**
	 * @return the offerPrice
	 */
	public BigDecimal getOfferPrice() {
		return offerPrice;
	}

	public String getFormatOfferPrice() {
		String s = null;
		if (offerPrice != null) {
			s = FieldUtil.formatDouble(new Double(offerPrice.doubleValue()),EIPOConstants.PRICE_FORMAT);
		}
		return s;
	}

	public boolean isEmail(){
		boolean flag = false;
		if (null != getIpoSubscriptionNotificationDtoList()) {
			List<IpoSubscrptnNotifyDto> subList = getIpoSubscriptionNotificationDtoList();
			for (IpoSubscrptnNotifyDto dto : subList) {
				if (Constants.NotifyType.EMAIL.equals(dto.getNotifyMethodType())) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	public boolean isPhoto(){
		boolean flag = false;
		if (null != getIpoSubscriptionNotificationDtoList()) {
			List<IpoSubscrptnNotifyDto> subList = getIpoSubscriptionNotificationDtoList();
			for (IpoSubscrptnNotifyDto dto : subList) {
				if (Constants.NotifyType.SMS.equals(dto.getNotifyMethodType())) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	/**
	 * @param offerPrice the offerPrice to set
	 */
	public void setOfferPrice(BigDecimal offerPrice) {
		this.offerPrice = offerPrice;
	}

	public List<IpoSubscrptnNotifyDto> getIpoSubscriptionNotificationDtoList() {
		return ipoSubscriptionNotificationDtoList;
	}

	public void setIpoSubscriptionNotificationDtoList(
			List<IpoSubscrptnNotifyDto> ipoSubscriptionNotificationDtoList) {
		this.ipoSubscriptionNotificationDtoList = ipoSubscriptionNotificationDtoList;
	}
	
	public void reset() {

	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
