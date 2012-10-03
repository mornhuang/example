//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 08-17-2005
 * 
 * XDoclet definition:
 * @struts:form name="BroadcastActionForm"
 */
public class BroadcastActionForm extends ITSZForm {

    // --------------------------------------------------------- Instance Variables

    /** id property */
    private String id;

    // --------------------------------------------------------- Methods

    /** 
     * Method reset
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }

    /** 
     * Returns the id.
     * @return String
     */
    public String getId() {
        return id;
    }

    /** 
     * Set the id.
     * @param id The id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}