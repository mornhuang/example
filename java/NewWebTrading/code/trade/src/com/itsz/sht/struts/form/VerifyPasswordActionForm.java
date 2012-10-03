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
 * @struts:form name="VerifyPasswordForm"
 */
public class VerifyPasswordActionForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** password property */
    private String password;
    
    private String loginID;

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
     * @return Returns the loginID.
     */
    public String getLoginID() {
        return loginID;
    }
    /**
     * @param loginID The loginID to set.
     */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }
}