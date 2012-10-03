//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 10-14-2005
 * 
 * XDoclet definition:
 * @struts:form name="AcSummaryForm"
 */
public class AccountSummaryForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** accountID property */
    private String accountId;

    // --------------------------------------------------------- Methods

    /** 
     * Method reset
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        
    }

    /** 
     * Returns the accountID.
     * @return String
     */
    public String getAccountId() {
        return accountId;
    }

    /** 
     * Set the accountID.
     * @param accountID The accountID to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}