//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 07-21-2005
 * 
 * XDoclet definition:
 * @struts:form name="TransactionProtectionForm"
 */
public class TransactionProtectionActionForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** transactionProtection property */
    private String transactionProtection;
    private String password;

    // --------------------------------------------------------- Methods

    /** 
     * Method reset
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

    }

    /** 
     * Returns the transactionProtection.
     * @return String
     */
    public String getTransactionProtection() {
        return transactionProtection;
    }

    /** 
     * Set the transactionProtection.
     * @param transactionProtection The transactionProtection to set
     */
    public void setTransactionProtection(String transactionProtection) {
        this.transactionProtection = transactionProtection;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}