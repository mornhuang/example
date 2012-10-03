//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-29-2005
 * 
 * XDoclet definition:
 * @struts:form name="AccountEnquirySActionForm"
 */
public class AccountEnquirySActionForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** custCode property */
    private String custCode;

    // --------------------------------------------------------- Methods

    /** 
     * Method reset
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

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

}