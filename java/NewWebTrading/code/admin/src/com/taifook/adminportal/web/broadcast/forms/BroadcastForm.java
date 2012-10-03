//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.broadcast.forms;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-28-2006
 * 
 * XDoclet definition:
 * @struts.form name="broadcastForm"
 */
public class BroadcastForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	private static final long serialVersionUID = 1L;

	private java.lang.String seqno;

	/** The value of the simple starttime property. */
	private java.lang.String starttime;

	/** The value of the simple endtime property. */
	private java.lang.String endtime;

	/** The value of the simple broadcastLevel property. */
	private java.lang.String broadcastLevel;

	/** The value of the simple type property. */
	private java.lang.String contentType;

	private java.lang.String[] channels;

	/** The value of the simple contentEnUs property. */
	private java.lang.String contentEnUs;

	/** The value of the simple contentZhCn property. */
	private java.lang.String contentZhCn;

	/** The value of the simple contentZhTw property. */
	private java.lang.String contentZhTw;

	private java.lang.String lastupdatetime;

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

	public java.lang.String getBroadcastLevel() {
		return broadcastLevel;
	}

	public void setBroadcastLevel(java.lang.String broadcastLevel) {
		this.broadcastLevel = broadcastLevel;
	}

	public java.lang.String[] getChannels() {
		return channels;
	}

	public void setChannels(java.lang.String[] channels) {
		this.channels = channels;
	}

	public java.lang.String getContentEnUs() {
		return contentEnUs;
	}

	public void setContentEnUs(java.lang.String contentEnUs) {
		if(contentEnUs!=null){
			this.contentEnUs = contentEnUs.trim();
		}
	}

	public java.lang.String getContentType() {
		return contentType;
	}

	public void setContentType(java.lang.String contentType) {
		this.contentType = contentType;
	}

	public java.lang.String getContentZhCn() {
		return contentZhCn;
	}

	public void setContentZhCn(java.lang.String contentZhCn) {
		if(contentZhCn!=null){
			this.contentZhCn = contentZhCn.trim();
		}
	}

	public java.lang.String getContentZhTw() {
		return contentZhTw;
	}

	public void setContentZhTw(java.lang.String contentZhTw) {
		if(contentZhTw!=null){
			this.contentZhTw = contentZhTw.trim();
		}
	}

	public java.lang.String getEndtime() {
		return endtime;
	}

	public void setEndtime(java.lang.String endtime) {
		this.endtime = endtime;
	}

	public java.lang.String getSeqno() {
		return seqno;
	}

	public void setSeqno(java.lang.String seqno) {
		this.seqno = seqno;
	}

	public java.lang.String getStarttime() {
		return starttime;
	}

	public void setStarttime(java.lang.String starttime) {
		this.starttime = starttime;
	}

	public java.lang.String getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(java.lang.String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}


	/** 
	 * Returns the content_cn.
	 * @return String
	 */


}

