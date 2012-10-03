package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: OrderDetailResponseModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal.head
 * @File:OrderDetailResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class OrderDetailResponseModel extends AbstractResponseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 320398585700783654L;
	//all kyzou
    private String accountId;
    private String marketCode;
    private String instrCode;
    private String orderSide;
    private String orderQuantity;
    private String orderPrice;
    private String orderType;
    private String validityType;
    private String expiryDate;
    private String mcsOrderId;
    private String orderId;
    private String orderState;
    private String ccy;
    private String initialQuantity;
    private String outstandingQuantity;
    private String businessDate;
    private String dateCreated;
    private String orderRequestRefNum;
    private String mtssOrderState;
    private String orderComment;
    private String instrName;
    private String rejectReason;
    private String changedQty;
    private String filledQty;
    private String rejectedQty;
    private String channelType;
    private String orderRemark;
    private String allOrNothingFlag;
    private String triggerPrice;
    private String conditionType;
    private Long basketRef;

    public OrderDetailResponseModel()
    {
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public String getMarketCode()
    {
        return marketCode;
    }

    public void setMarketCode(String marketCode)
    {
        this.marketCode = marketCode;
    }

    public String getInstrCode()
    {
        return instrCode;
    }

    public void setInstrCode(String instrCode)
    {
        this.instrCode = instrCode;
    }

    public String getOrderSide()
    {
        return orderSide;
    }

    public void setOrderSide(String orderSide)
    {
        this.orderSide = orderSide;
    }

    public String getOrderQuantity()
    {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity)
    {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderPrice()
    {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice)
    {
        this.orderPrice = orderPrice;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public String getValidityType()
    {
        return validityType;
    }

    public void setValidityType(String validityType)
    {
        this.validityType = validityType;
    }

    public String getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate)
    {
        this.expiryDate = expiryDate;
    }

    public String getMcsOrderId()
    {
        return mcsOrderId;
    }

    public void setMcsOrderId(String mcsOrderId)
    {
        this.mcsOrderId = mcsOrderId;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderState()
    {
        return orderState;
    }

    public void setOrderState(String orderState)
    {
        this.orderState = orderState;
    }

    public String getCcy()
    {
        return ccy;
    }

    public void setCcy(String ccy)
    {
        this.ccy = ccy;
    }

    public String getInitialQuantity()
    {
        return initialQuantity;
    }

    public void setInitialQuantity(String initialQuantity)
    {
        this.initialQuantity = initialQuantity;
    }

    public String getOutstandingQuantity()
    {
        return outstandingQuantity;
    }

    public void setOutstandingQuantity(String outstandingQuantity)
    {
        this.outstandingQuantity = outstandingQuantity;
    }

    public String getBusinessDate()
    {
        return businessDate;
    }

    public void setBusinessDate(String businessDate)
    {
        this.businessDate = businessDate;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("OrderInfo {");
        sb.append(", accountId= ");
        sb.append(accountId);
        sb.append(", marketCode= ");
        sb.append(marketCode);
        sb.append(", instrCode= ");
        sb.append(instrCode);
        sb.append(", orderSide= ");
        sb.append(orderSide);
        sb.append(", orderQuantity= ");
        sb.append(orderQuantity);
        sb.append(", orderPrice= ");
        sb.append(orderPrice);
        sb.append(", orderType= ");
        sb.append(orderType);
        sb.append(", validityType= ");
        sb.append(validityType);
        sb.append(", expiryDate= ");
        sb.append(expiryDate);
        sb.append(", mcsOrderId= ");
        sb.append(mcsOrderId);
        sb.append(", orderId= ");
        sb.append(orderId);
        sb.append(", orderState= ");
        sb.append(orderState);
        sb.append(", ccy= ");
        sb.append(ccy);
        sb.append(", initialQuantity= ");
        sb.append(initialQuantity);
        sb.append(", outstandingQuantity= ");
        sb.append(outstandingQuantity);
        sb.append(", businessDate= ");
        sb.append(businessDate);
        sb.append(changedQty);
        sb.append("}");
        return sb.toString();
    }

    public String getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public String getOrderRequestRefNum()
    {
        return orderRequestRefNum;
    }

    public void setOrderRequestRefNum(String orderRequestRefNum)
    {
        this.orderRequestRefNum = orderRequestRefNum;
    }

    public String getMtssOrderState()
    {
        return mtssOrderState;
    }

    public void setMtssOrderState(String mtssOrderState)
    {
        this.mtssOrderState = mtssOrderState;
    }

    public String getOrderComment()
    {
        return orderComment;
    }

    public void setOrderComment(String orderComment)
    {
        this.orderComment = orderComment;
    }

    public String getInstrName()
    {
        return instrName;
    }

    public void setInstrName(String instrName)
    {
        this.instrName = instrName;
    }

    public String getFilledQty()
    {
        return filledQty;
    }

    public void setFilledQty(String filledQty)
    {
        this.filledQty = filledQty;
    }

    public String getChangedQty()
    {
        return changedQty;
    }

    public void setChangedQty(String changedQty)
    {
        this.changedQty = changedQty;
    }

    public String getRejectedQty()
    {
        return rejectedQty;
    }

    public void setRejectedQty(String rejectedQty)
    {
        this.rejectedQty = rejectedQty;
    }

    public String getRejectReason()
    {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason)
    {
        this.rejectReason = rejectReason;
    }

    public String getChannelType()
    {
        return channelType;
    }

    public void setChannelType(String channelType)
    {
        this.channelType = channelType;
    }

    public String getOrderRemark()
    {
        return orderRemark;
    }

    public void setOrderRemark(String string)
    {
        orderRemark = string;
    }

    public String getAllOrNothingFlag()
    {
        return allOrNothingFlag;
    }

    public void setAllOrNothingFlag(String flag)
    {
        allOrNothingFlag = flag;
    }

    public String getTriggerPrice()
    {
        return triggerPrice;
    }

    public void setTriggerPrice(String price)
    {
        triggerPrice = price;
    }

    public String getConditionType()
    {
        return conditionType;
    }

    public void setConditionType(String type)
    {
        conditionType = type;
    }

	public Long getBasketRef() {
		return basketRef;
	}

	public void setBasketRef(Long basketRef) {
		this.basketRef = basketRef;
	}
}
