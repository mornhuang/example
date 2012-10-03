//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 05-24-2005
 * 
 * XDoclet definition:
 * @struts:form name="TradeHistoryActionForm"
 */
public class TradeHistoryActionForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** MTSSOrderID property */
    private String MTSSOrderID;

    /** MCSOrderID property */
    private String MCSOrderID;

    // --------------------------------------------------------- Methods

    /** 
     * Method reset
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

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

}