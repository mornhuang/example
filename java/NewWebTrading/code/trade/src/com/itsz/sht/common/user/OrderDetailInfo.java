/**
 * <p>Title: 3G Portal</p>
 * <p>Description: 3G Portal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common.user;

import java.io.Serializable;

public class OrderDetailInfo implements Serializable{
	private String accountId;      
	private String orderState;  
	private String instrName;  //��Ʊ���      
	private String filledQty;  //�Ѿ��ɽ�����    
	private String mcsOrderId; //
	private String orderId;    //һ��ʹ�������MTSS�ģ�
	private String mtssOrderState; //��Ҫʹ�����״̬ 	  
	private String changedQty; //�ĵ�����    	  	 
	private String orderPrice; //ί�м۸�        	  
	private String initialQuantity; //ԭʼ�µ�����      
	private String rejectReason;    //�ܾ�ԭ�� 
	private String channelType;     //�������� 
	private String orderSide;       //��������
	private String instrCode;       //��Ʊ����
	private String outstandingQuantity; //δ�ɽ�����   	
	private String orderType;	//
	private String shortOrderState;//mtssOrderState(�̣�
	private String formatedOrderState;
	private String allOrNothingFlag;//ȫ���ȡ��
	private String triggerPrice;//������
	private String conditionType;//��������
	  
	/**
	 * @return Returns the shortOrderState.
	 */
	public String getShortOrderState() {
		return shortOrderState;
	}
	/**
	 * @param shortOrderState The shortOrderState to set.
	 */
	public void setShortOrderState(String shortOrderState) {
		this.shortOrderState = shortOrderState;
	}
	/**
	 * @return Returns the accountId.
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId The accountId to set.
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return Returns the channelType.
	 */
	public String getChannelType() {
		return channelType;
	}
	/**
	 * @param channelType The channelType to set.
	 */
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	/**
	 * @return Returns the instrCode.
	 */
	public String getInstrCode() {
		return instrCode;
	}
	/**
	 * @param instrCode The instrCode to set.
	 */
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	/**
	 * @return Returns the instrName.
	 */
	public String getInstrName() {
		return instrName;
	}
	/**
	 * @param instrName The instrName to set.
	 */
	public void setInstrName(String instrName) {
		this.instrName = instrName;
	}
	/**
	 * @return Returns the mcsOrderId.
	 */
	public String getMcsOrderId() {
		return mcsOrderId;
	}
	/**
	 * @param mcsOrderId The mcsOrderId to set.
	 */
	public void setMcsOrderId(String mcsOrderId) {
		this.mcsOrderId = mcsOrderId;
	}
    /**
     * @return Returns the orderId.
     */
    public String getOrderId() {
        return orderId;
    }
    /**
     * @param orderId The orderId to set.
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
	/**
	 * @return Returns the orderSide.
	 */
	public String getOrderSide() {
		return orderSide;
	}
	/**
	 * @param orderSide The orderSide to set.
	 */
	public void setOrderSide(String orderSide) {
		this.orderSide = orderSide;
	}
	/**
	 * @return Returns the orderState.
	 */
	public String getOrderState() {
		return orderState;
	}
	/**
	 * @param orderState The orderState to set.
	 */
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	/**
	 * @return Returns the rejectReason.
	 */
	public String getRejectReason() {
		return rejectReason;
	}
	/**
	 * @param rejectReason The rejectReason to set.
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
		  
	/**
	 * @return Returns the mtssOrderState.
	 */
	public String getMtssOrderState() {
		return mtssOrderState;
	}
	/**
	 * @param mtssOrderState The mtssOrderState to set.
	 */
	public void setMtssOrderState(String mtssOrderState) {
		this.mtssOrderState = mtssOrderState;
	}
    /**
     * @return Returns the changedQty.
     */
    public String getChangedQty() {
        return changedQty;
    }
    /**
     * @param changedQty The changedQty to set.
     */
    public void setChangedQty(String changedQty) {
        this.changedQty = changedQty;
    }
    /**
     * @return Returns the filledQty.
     */
    public String getFilledQty() {
        return filledQty;
    }
    /**
     * @param filledQty The filledQty to set.
     */
    public void setFilledQty(String filledQty) {
        this.filledQty = filledQty;
    }
    /**
     * @return Returns the initialQuantity.
     */
    public String getInitialQuantity() {
        return initialQuantity;
    }
    /**
     * @param initialQuantity The initialQuantity to set.
     */
    public void setInitialQuantity(String initialQuantity) {
        this.initialQuantity = initialQuantity;
    }
    /**
     * @return Returns the orderPrice.
     */
    public String getOrderPrice() {
        return orderPrice;
    }
    /**
     * @param orderPrice The orderPrice to set.
     */
    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }
    /**
     * @return Returns the outstandingQuantity.
     */
    public String getOutstandingQuantity() {
        return outstandingQuantity;
    }
    /**
     * @param outstandingQuantity The outstandingQuantity to set.
     */
    public void setOutstandingQuantity(String outstandingQuantity) {
        this.outstandingQuantity = outstandingQuantity;
    }
    /**
     * @return Returns the orderType.
     */
    public String getOrderType() {
        return orderType;
    }
    /**
     * @param orderType The orderType to set.
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    /**
     * @return Returns the formatedOrderState.
     */
    public String getFormatedOrderState() {
        return formatedOrderState;
    }
    /**
     * @param formatedOrderState The formatedOrderState to set.
     */
    public void setFormatedOrderState(String formatedOrderState) {
        this.formatedOrderState = formatedOrderState;
    }
    /**
     * @return Returns the allOrNothingFlag.
     */
    public String getAllOrNothingFlag() {
        return allOrNothingFlag;
    }
    /**
     * @param allOrNothingFlag The allOrNothingFlag to set.
     */
    public void setAllOrNothingFlag(String allOrNothingFlag) {
        this.allOrNothingFlag = allOrNothingFlag;
    }
    /**
     * @return Returns the conditionType.
     */
    public String getConditionType() {
        return conditionType;
    }
    /**
     * @param conditionType The conditionType to set.
     */
    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }
    /**
     * @return Returns the triggerPrice.
     */
    public String getTriggerPrice() {
        return triggerPrice;
    }
    /**
     * @param triggerPrice The triggerPrice to set.
     */
    public void setTriggerPrice(String triggerPrice) {
        this.triggerPrice = triggerPrice;
    }
}
