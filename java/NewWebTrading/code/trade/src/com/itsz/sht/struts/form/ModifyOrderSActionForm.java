//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 07-22-2005
 * 
 * XDoclet definition:
 * @struts:form name="ModifyOrderSActionForm"
 */
public class ModifyOrderSActionForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** password property */
    private String password;

    /** accountID property */
    private String accountID;

    /** expiryDate property */
    private String expiryDate;

    /** custCode property */
    private String custCode;

    /** newOrderType property */
    private String newOrderType;

    /** MTSSOrderID property */
    private String MTSSOrderID;

    /** MCSOrderID property */
    private String MCSOrderID;

    /** triggerPrice property */
    private String triggerPrice;

    /** newOrderQty property */
    private String newOrderQty;

    /** newOrderQtyDelta property */
    private String newOrderQtyDelta;

    /** newOrderPrice property */
    private String newOrderPrice;

    // --------------------------------------------------------- Methods

    /** 
     * Method reset
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

    }

    /** 
     * Returns the password.
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /** 
     * Set the password.
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 
     * Returns the accountID.
     * @return String
     */
    public String getAccountID() {
        return accountID;
    }

    /** 
     * Set the accountID.
     * @param accountID The accountID to set
     */
    public void setAccountID(String accountID) {
        this.accountID = accountID;
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
     * Returns the newOrderType.
     * @return String
     */
    public String getNewOrderType() {
        return newOrderType;
    }

    /** 
     * Set the newOrderType.
     * @param newOrderType The newOrderType to set
     */
    public void setNewOrderType(String newOrderType) {
        this.newOrderType = newOrderType;
    }

    /** 
     * Returns the MTSSOrderID.
     * @return String
     */
    public String getMTSSOrderID() {
        return MTSSOrderID;
    }

    /** 
     * Set the MTSSOrderID.
     * @param MTSSOrderID The MTSSOrderID to set
     */
    public void setMTSSOrderID(String MTSSOrderID) {
        this.MTSSOrderID = MTSSOrderID;
    }

    /** 
     * Returns the MCSOrderID.
     * @return String
     */
    public String getMCSOrderID() {
        return MCSOrderID;
    }

    /** 
     * Set the MCSOrderID.
     * @param MCSOrderID The MCSOrderID to set
     */
    public void setMCSOrderID(String MCSOrderID) {
        this.MCSOrderID = MCSOrderID;
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

    /** 
     * Returns the newOrderQty.
     * @return String
     */
    public String getNewOrderQty() {
        return newOrderQty;
    }

    /** 
     * Set the newOrderQty.
     * @param newOrderQty The newOrderQty to set
     */
    public void setNewOrderQty(String newOrderQty) {
        this.newOrderQty = newOrderQty;
    }

    /** 
     * Returns the newOrderQtyDelta.
     * @return String
     */
    public String getNewOrderQtyDelta() {
        return newOrderQtyDelta;
    }

    /** 
     * Set the newOrderQtyDelta.
     * @param newOrderQtyDelta The newOrderQtyDelta to set
     */
    public void setNewOrderQtyDelta(String newOrderQtyDelta) {
        this.newOrderQtyDelta = newOrderQtyDelta;
    }

    /** 
     * Returns the newOrderPrice.
     * @return String
     */
    public String getNewOrderPrice() {
        return newOrderPrice;
    }

    /** 
     * Set the newOrderPrice.
     * @param newOrderPrice The newOrderPrice to set
     */
    public void setNewOrderPrice(String newOrderPrice) {
        this.newOrderPrice = newOrderPrice;
    }

}