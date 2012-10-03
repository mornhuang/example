package com.itsz.sht.struts.eipo.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.util.MessageResources;

import com.itsz.eipo.common.Constants;
import com.itsz.eipo.webservice.IpoDenominationDto;
import com.itsz.eipo.webservice.IpoMaster;
import com.itsz.eipo.webservice.IpoSubscriptionDto;
import com.itsz.sht.common.FieldUtil;
import com.itsz.sht.struts.eipo.util.EIPOClientUtil;
import com.itsz.sht.struts.eipo.util.EIPOConstants;
import com.itsz.sht.struts.eipo.util.EIPODecimalUtil;

public class EIPOMasterEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ipoId;
	private String instrDsplyCode;
	private String mrktCode;
	private String subMrktCode;
	private String ipoStatus;
	private String ipoSubscriptionStatus;
	private String ccyCode;
	private String ipoName;
	private String subscriptionType;
	private String isEnable;
	private String displayUrl;
	
	private Date applyDate; //无用字段
	private Date applyDeadline;
	private Date depositDate;
	private Date refundDate;
	private Date collectionDate;
	private Date listingDate;
	private Date startDateTime;
	private Date endDateTime;
	private Date holdFundDateTime;
	private Date paymentDeadlineDateTime;
	
	private BigDecimal offerPrice;  //面额
	private BigDecimal lotSize;
	private BigDecimal commissionRate;  //经纪佣金比率
	private BigDecimal tradingFee;		//联交所交易费率
	private BigDecimal transactionLevy;   //证监会交易费率
	private BigDecimal compensationFee;	   //投资者赔偿费率
	private BigDecimal handlingCharge;  //手续费
	private BigDecimal miscFee;  //杂费率

	//object
	private List<IpoDenominationDto> ipoDenominationDtoList;
	private IpoSubscriptionDto ipoSubscription;
	
	//自定义属性
	private BigDecimal appliedShare;   //申购股数
	private BigDecimal amountPayable;   //股数面额
	private BigDecimal tradeAmount; //总金额
	
	private Locale locale;
	private MessageResources mr;
	
	public EIPOMasterEntry() {
		
	}
	
	public EIPOMasterEntry(IpoMaster master,Locale locale,MessageResources mr) {
		
		this.ipoId = master.getIpoId();
		this.instrDsplyCode = master.getInstrDsplyCode();
		this.mrktCode = master.getMrktCode();
		this.subMrktCode = master.getSubMrktCode();
		this.ipoStatus = master.getIpoStatus();
		this.ipoSubscriptionStatus = master.getIpoSubStatus();
		this.ccyCode = master.getCcyCode();
		this.ipoName = master.getIpoName();
		this.subscriptionType = master.getSubscriptionType();
		this.isEnable = master.getIsEnable();
		this.displayUrl = ""; //TODO:modify url.
		
		this.applyDate = EIPOClientUtil.con2Date(master.getApplyDate());
		this.applyDeadline = EIPOClientUtil.con2Date(master.getApplyDeadline());
		this.depositDate = EIPOClientUtil.con2Date(master.getDepositDate());
		this.refundDate = EIPOClientUtil.con2Date(master.getRefundDate());
		this.collectionDate = EIPOClientUtil.con2Date(master.getCollectionDate());
		this.listingDate = EIPOClientUtil.con2Date(master.getListingDate());

		this.offerPrice = master.getOfferPrice();
		this.lotSize = master.getLotSize();
		this.commissionRate = master.getCommissionRate();
		this.tradingFee = master.getTradingFee();
		this.transactionLevy = master.getTransactionLevy();
		this.compensationFee = master.getCompensationFee();
		this.handlingCharge = master.getHandlingCharge();
		this.miscFee = master.getMiscFee();
		this.holdFundDateTime = EIPOClientUtil.con2Date(master.getHoldFundDateTime());
		this.paymentDeadlineDateTime = EIPOClientUtil.con2Date(master.getPaymentDeadlineDateTime());
		this.startDateTime =  EIPOClientUtil.con2Date(master.getStartDateTime());
		this.endDateTime = EIPOClientUtil.con2Date(master.getEndDateTime());
		
		//set object
		this.ipoDenominationDtoList = master.getIpoDenominationDtoList();
		this.ipoSubscription = master.getIpoSubscriptionDto();
		this.locale = locale;
		this.mr = mr;
	}
	
	
	
	/**
	 * @return the ipoId
	 */
	public String getIpoId() {
		return ipoId;
	}



	/**
	 * @return the instrDsplyCode
	 */
	public String getInstrDsplyCode() {
		return instrDsplyCode;
	}



	/**
	 * @return the mrktCode
	 */
	public String getMrktCode() {
		return mrktCode;
	}



	/**
	 * @return the subMrktCode
	 */
	public String getSubMrktCode() {
		return subMrktCode;
	}



	/**
	 * @return the ipoStatus
	 */
	public String getIpoStatus() {
		return ipoStatus;
	}



	/**
	 * @return the ccyCode
	 */
	public String getCcyCode() {
		return ccyCode;
	}



	/**
	 * @return the ipoName
	 */
	public String getIpoName() {
		return ipoName;
	}



	/**
	 * @return the subscriptionType
	 */
	public String getSubscriptionType() {
		Locale clocale = locale != null ? locale : Locale.US;
		if (mr != null && subscriptionType != null) {
			mr.setReturnNull(true);
			return mr.getMessage(clocale,"label.eipo.master.enquiry.type." + subscriptionType.toLowerCase());
		}
		else {
			return subscriptionType;
		}
	}



	/**
	 * @return the isEnable
	 */
	public String getIsEnable() {
		return isEnable;
	}



	/**
	 * @return the applyDate
	 */
	public String getApplyDate() {
		if (null != applyDate) {
			return EIPOConstants.SDF_YYMMDD.format(applyDate);
		}
		return "";
	}



	public String getDisplayUrl() {
		return displayUrl;
	}

	/**
	 * @return the applyDeadline
	 */
	public String getApplyDeadline() {
		if (null != applyDeadline) {
			return EIPOConstants.SDF_YYMMDD.format(applyDeadline);
		}
		return "";
	}



	/**
	 * @return the depositDate
	 */
	public String getDepositDate() {
		if (null != depositDate) {
			return EIPOConstants.SDF_YYMMDDHHMMSS.format(depositDate);
		}
		return "";
	}



	/**
	 * @return the refundDate
	 */
	public String getRefundDate() {
		if (null != refundDate) {
			return EIPOConstants.SDF_YYMMDD.format(refundDate);
		}
		return "";
	}



	/**
	 * @return the collectionDate
	 */
	public String getCollectionDate() {
		if (null != collectionDate) {
			return EIPOConstants.SDF_YYMMDD.format(collectionDate);
		}
		return "";
	}



	/**
	 * @return the listingDate
	 */
	public String getListingDate() {
		if (null != listingDate) {
			return EIPOConstants.SDF_YYMMDD.format(listingDate);
		}
		return "";
	}



	/**
	 * @return the startDateTime
	 */
	public String getStartDateTime() {
		if (null != startDateTime) {
			return EIPOConstants.SDF_YYMMDD.format(startDateTime);
		}
		return "";
	}



	public String getPaymentDeadlineDateTime() {
		if (null != paymentDeadlineDateTime) {
			return EIPOConstants.SDF_YYMMDDHHMMSS.format(paymentDeadlineDateTime);
		}
		return "";
	}

	/**
	 * @return the endDateTime
	 */
	public String getEndDateTime() {
		if (null != endDateTime) {
			return EIPOConstants.SDF_YYMMDDHHMMSS.format(endDateTime);
		}
		return "";
	}



	/**
	 * @return the holdFundDateTime
	 */
	public String getHoldFundDateTime() {
		if (null != holdFundDateTime) {
			return EIPOConstants.SDF_YYMMDDHHMMSS.format(holdFundDateTime);
		}
		return "";
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

	/**
	 * @return the lotSize
	 */
	public BigDecimal getLotSize() {
		return lotSize;
	}

	public String getFormatLotSize() {
		String s = null;
		if (lotSize != null) {
			s = FieldUtil.formatDouble(new Double(lotSize.doubleValue()),
			"#,###");
		}
		return s;
	}

	/**
	 * @return the commissionRate
	 */
	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public String getFormatCommissionRate() {
		return EIPODecimalUtil.getFormatPriceRate(commissionRate);
	}

	/**
	 * @return the tradingFee
	 */
	public BigDecimal getTradingFee() {
		return tradingFee;
	}

	public String getFormatTradingFee() {
		return EIPODecimalUtil.getFormatPriceRate(tradingFee);
	}

	/**
	 * @return the transactionLevy
	 */
	public BigDecimal getTransactionLevy() {
		return transactionLevy;
	}

	public String getFormatTransactionLevy() {
		return EIPODecimalUtil.getFormatPriceRate(transactionLevy);
	}

	/**
	 * @return the compensationFee
	 */
	public BigDecimal getCompensationFee() {
		return compensationFee;
	}

	public String getFormatCompensationFee() {
		return EIPODecimalUtil.getFormatPriceRate(compensationFee);
	}

	/**
	 * @return the handlingCharge
	 */
	public BigDecimal getHandlingCharge() {
		return handlingCharge;
	}

	/**
	 * @return the handlingCharge
	 */
	public String getFormatHandlingCharge() {
		String s = null;
		if (handlingCharge != null) {
			s = FieldUtil.formatDouble(new Double(handlingCharge.doubleValue()),EIPOConstants.PRICE_FORMAT);
		}
		return s;
	}

	/**
	 * @return the miscFee
	 */
	public BigDecimal getMiscFee() {
		return miscFee;
	}

	public String getFormatMiscFee() {
		String s = "0";
		if (miscFee != null) {  //为0显示0
			if (new BigDecimal(0).compareTo(miscFee)!=0) {
				s = EIPODecimalUtil.getFormatPriceRate(miscFee);
			}
		}
		return s;
	}

	/**
	 * @return the ipoDenominationDtoList
	 */
	public List<IpoDenominationDto> getIpoDenominationDtoList() {
		return ipoDenominationDtoList;
	}



	/**
	 * @return the ipoSubscription
	 */
	public IpoSubscriptionDto getIpoSubscription() {
		return ipoSubscription;
	}

	public void setIpoSubscription(IpoSubscriptionDto ipoSubscription) {
		this.ipoSubscription = ipoSubscription;
	}
	
	public String getIpoSubscriptionStatus() {
		return ipoSubscriptionStatus;
	}

	public void setIpoSubscriptionStatus(String ipoSubscriptionStatus) {
		this.ipoSubscriptionStatus = ipoSubscriptionStatus;
	}
	

	//************** 以下是自定义属性

	public String getIpoSubscriptionState() {
		
		if (Constants.IpoMasterState.SUBSCRIPTION_SESSION.equals(ipoStatus)
				&& BooleanUtils.toBoolean(isEnable)) {  //open status
			if (null == ipoSubscription) {   //add
				return EIPOConstants.IPO_MASTER_SUBSCRIPTION_STATE_SUB;
			} else {
				return EIPOConstants.IPO_MASTER_SUBSCRIPTION_STATE_CANCEL;
			}
		}
		return EIPOConstants.IPO_MASTER_SUBSCRIPTION_STATE_NULL;
	}
	
	public boolean getAllowCancelState() {
		boolean flag = false;
		if (null != ipoSubscription) {   //cancel
			if (Constants.SubscriptionType.FULLY_PAID.equals(ipoSubscription.getSubscriptionType())) {
				flag = true;
			}
		}
		return flag;
	}
	
	public String getFormatIpoStatus() {
		Locale clocale = locale != null ? locale : Locale.US;
		if (mr != null && ipoSubscriptionStatus != null) {
			return mr.getMessage(clocale,"label.eipo.master.status."+ipoSubscriptionStatus.toLowerCase());
		} else {
			return "";
		}
	}
	
	public boolean getAllowIpoSubscriptionState() {
		boolean flag = false;
		if (null != ipoSubscription) {   //cancel
			flag = true;
		}
		return flag;
	}
	
	public Long getIpoSubscriptionId() {
		if (null != ipoSubscription) {
			return ipoSubscription.getId();
		}
		return 0L;
	}
	
	public List<EIPODenominationEntry> getIpoDenominationList() {
		List<EIPODenominationEntry> ipoDenominationList = new ArrayList<EIPODenominationEntry>();
		if (null != ipoDenominationDtoList) {
			for (IpoDenominationDto dto : ipoDenominationDtoList) {
				ipoDenominationList.add(new EIPODenominationEntry(dto));
			}
		}
		return ipoDenominationList;
	}

	public BigDecimal getAppliedShare() {
		return appliedShare;
	}

	public String getFormatAppliedShare() {
		String s = null;
		if (appliedShare != null) {
			s = FieldUtil.formatDouble(new Double(appliedShare.doubleValue()),
			"#,###");
		}
		return s;
	}
	
	public void setAppliedShare(BigDecimal appliedShare) {
		this.appliedShare = appliedShare;
	}

	public BigDecimal getAmountPayable() {
		return amountPayable;
	}
	
	public String getFormatAmountPayable() {
		String s = null;
		if (amountPayable != null) {
			s = FieldUtil.formatDouble(new Double(amountPayable.doubleValue()),EIPOConstants.PRICE_FORMAT);
		}
		return s;
	}

	public void setAmountPayable(BigDecimal amountPayable) {
		this.amountPayable = amountPayable;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getTradeAmount() {
		String s = null;
		if (tradeAmount != null) {
			s = FieldUtil.formatDouble(new Double(tradeAmount.doubleValue()),EIPOConstants.PRICE_FORMAT);
		}
		return s;
	}
	
	public String getSubscriptionStatus() {
		Locale clocale = locale != null ? locale : Locale.US;
		if (mr != null && ipoSubscription != null && ipoSubscription.getStatus() != null) {
			mr.setReturnNull(true);
			return mr.getMessage(clocale,"label.eipo.subscription.state." + ipoSubscription.getStatus().toLowerCase());
		}
		else {
			return "";
		}
	}
	
	public void reset() {
		
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
