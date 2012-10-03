//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 08-23-2005
 * 
 * XDoclet definition:
 * @struts:form name="ProfitAndLossEnquiryForm"
 */
public class ProfitAndLossEnquiryActionForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** accountID property */
    private String accountID;

    /** quoteType property */
    private String quoteType;

    /** profileID property */
    private String profileID;

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
     * Returns the quoteType.
     * @return String
     */
    public String getQuoteType() {
        return quoteType;
    }

    /** 
     * Set the quoteType.
     * @param quoteType The quoteType to set
     */
    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    /** 
     * Returns the profileID.
     * @return String
     */
    public String getProfileID() {
        return profileID;
    }

    /** 
     * Set the profileID.
     * @param profileID The profileID to set
     */
    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

}