//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.channels.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-28-2006
 * 
 * XDoclet definition:
 * @struts.form name="channelsSwitchUpdateForm"
 */
public class ChannelsSwitchUpdateForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	private static final long serialVersionUID = 1L;

	/** stt_status property */
	private String stt_status;

	/** web_status property */
	private String web_status;

	/** g3_status property */
	private String g3_status;

	// --------------------------------------------------------- Methods

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// TODO Auto-generated method stub
	}

	/** 
	 * Returns the stt_status.
	 * @return String
	 */
	public String getStt_status() {
		return stt_status;
	}

	/** 
	 * Set the stt_status.
	 * @param stt_status The stt_status to set
	 */
	public void setStt_status(String stt_status) {
		this.stt_status = stt_status;
	}

	/** 
	 * Returns the web_status.
	 * @return Boolean
	 */
	public String getWeb_status() {
		return web_status;
	}

	/** 
	 * Set the web_status.
	 * @param web_status The web_status to set
	 */
	public void setWeb_status(String web_status) {
		this.web_status = web_status;
	}

	/** 
	 * Returns the g3_status.
	 * @return String
	 */
	public String getG3_status() {
		return g3_status;
	}

	/** 
	 * Set the g3_status.
	 * @param g3_status The g3_status to set
	 */
	public void setG3_status(String g3_status) {
		this.g3_status = g3_status;
	}

}

