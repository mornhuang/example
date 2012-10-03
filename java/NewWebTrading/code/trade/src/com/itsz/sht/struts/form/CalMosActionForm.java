//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 07-15-2005
 * 
 * XDoclet definition:
 * @struts:form name="CalMosForm"
 */
public class CalMosActionForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** accountID property */
    private String accountId;

    /** instrCode property */
    private String instrCode;

    /** marketCode property */
    private String marketCode;

    // --------------------------------------------------------- Methods

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
     * Method reset
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

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
     * Returns the marketCode.
     * @return String
     */
    public String getMarketCode() {
        return marketCode;
    }

    /** 
     * Set the marketCode.
     * @param marketCode The marketCode to set
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

}