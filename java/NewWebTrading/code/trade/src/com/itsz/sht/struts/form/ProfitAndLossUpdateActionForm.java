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
 * @struts:form name="ProfitAndLossUpdateForm"
 */
public class ProfitAndLossUpdateActionForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** loginID property */
    private String loginID;

    /** profileInfo property */
    private String profileInfo;

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
     * Returns the loginID.
     * @return String
     */
    public String getLoginID() {
        return loginID;
    }

    /** 
     * Set the loginID.
     * @param loginID The loginID to set
     */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    /** 
     * Returns the profileInfo.
     * @return String
     */
    public String getProfileInfo() {
        return profileInfo;
    }

    /** 
     * Set the profileInfo.
     * @param profileInfo The profileInfo to set
     */
    public void setProfileInfo(String profileInfo) {
        this.profileInfo = profileInfo;
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