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
 * @struts:form name="BulkOrderCancelActionForm"
 */
public class BulkOrderCancelActionForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** password property */
    private String password;

    /** accountID property */
    private String accountId;

    /** MTSSOrderID property */
    private String mtssOrderId;
    private String mcsOrderId;

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
	 * @return the mtssOrderId
	 */
	public String getMtssOrderId() {
		return mtssOrderId;
	}

	/**
	 * @param mtssOrderId the mtssOrderId to set
	 */
	public void setMtssOrderId(String mtssOrderId) {
		this.mtssOrderId = mtssOrderId;
	}

	/**
	 * @return the mcsOrderId
	 */
	public String getMcsOrderId() {
		return mcsOrderId;
	}

	/**
	 * @param mcsOrderId the mcsOrderId to set
	 */
	public void setMcsOrderId(String mcsOrderId) {
		this.mcsOrderId = mcsOrderId;
	}
}