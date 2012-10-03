//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 07-25-2005
 * 
 * XDoclet definition:
 * @struts:form name="PreInputOrderForm"
 */
public class PreInputOrderActionForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** accountID property */
    private String accountId;

    /** expiryDate property */
    private String expiryDate;

    /** conditionType property */
    private String conditionType;

    /** validityType property */
    private String validityType;

    /** instrCode property */
    private String instrCode;

    /** custCode property */
    private String custCode;

    /** orderQuantity property */
    private String orderQuantity;

    /** orderType property */
    private String orderType;

    /** orderPrice property */
    private String orderPrice;

    /** orderSide property */
    private String orderSide;

    /** allOrNothingFlag property */
    private String allOrNothingFlag;

    /** triggerPrice property */
    private String triggerPrice;

    // --------------------------------------------------------- Methods

    /** 
     * Method reset
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
      
    }
    

    /**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}



	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}



	/** 
     * Returns the expiryDate.
     * @return String
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /** 
     * Set the expiryDate.
     * @param expiryDate The expiryDate to set
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /** 
     * Returns the conditionType.
     * @return String
     */
    public String getConditionType() {
        return conditionType;
    }

    /** 
     * Set the conditionType.
     * @param conditionType The conditionType to set
     */
    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    /** 
     * Returns the validityType.
     * @return String
     */
    public String getValidityType() {
        return validityType;
    }

    /** 
     * Set the validityType.
     * @param validityType The validityType to set
     */
    public void setValidityType(String validityType) {
        this.validityType = validityType;
    }

    /** 
     * Returns the instrCode.
     * @return String
     */
    public String getInstrCode() {
        return instrCode;
    }

    /** 
     * Set the instrCode.
     * @param instrCode The instrCode to set
     */
    public void setInstrCode(String instrCode) {
        this.instrCode = instrCode;
    }

    /** 
     * Returns the custCode.
     * @return String
     */
    public String getCustCode() {
        return custCode;
    }

    /** 
     * Set the custCode.
     * @param custCode The custCode to set
     */
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    /** 
     * Returns the orderQuantity.
     * @return String
     */
    public String getOrderQuantity() {
        return orderQuantity;
    }

    /** 
     * Set the orderQuantity.
     * @param orderQuantity The orderQuantity to set
     */
    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    /** 
     * Returns the orderType.
     * @return String
     */
    public String getOrderType() {
        return orderType;
    }

    /** 
     * Set the orderType.
     * @param orderType The orderType to set
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /** 
     * Returns the orderPrice.
     * @return String
     */
    public String getOrderPrice() {
        return orderPrice;
    }

    /** 
     * Set the orderPrice.
     * @param orderPrice The orderPrice to set
     */
    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    /** 
     * Returns the orderSide.
     * @return String
     */
    public String getOrderSide() {
        return orderSide;
    }

    /** 
     * Set the orderSide.
     * @param orderSide The orderSide to set
     */
    public void setOrderSide(String orderSide) {
        this.orderSide = orderSide;
    }

    /** 
     * Returns the allOrNothingFlag.
     * @return String
     */
    public String getAllOrNothingFlag() {
        return allOrNothingFlag;
    }

    /** 
     * Set the allOrNothingFlag.
     * @param allOrNothingFlag The allOrNothingFlag to set
     */
    public void setAllOrNothingFlag(String allOrNothingFlag) {
        this.allOrNothingFlag = allOrNothingFlag;
    }

    /** 
     * Returns the triggerPrice.
     * @return String
     */
    public String getTriggerPrice() {
        return triggerPrice;
    }

    /** 
     * Set the triggerPrice.
     * @param triggerPrice The triggerPrice to set
     */
    public void setTriggerPrice(String triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

}