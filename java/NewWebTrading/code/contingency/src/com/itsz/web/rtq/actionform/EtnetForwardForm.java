//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.itsz.web.rtq.actionform;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 10-17-2006
 * 
 * XDoclet definition:
 * @struts.form name="etnetForwardForm"
 */
public class EtnetForwardForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** uid property */
	private String uid;

	/** request property */
	private String request = "login";

	/** pwd property */
	private String pwd;
	
	private String server_url;

	// --------------------------------------------------------- Methods

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}

	/** 
	 * Returns the uid.
	 * @return String
	 */
	public String getUid() {
		return uid;
	}

	/** 
	 * Set the uid.
	 * @param uid The uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/** 
	 * Returns the request.
	 * @return String
	 */
	public String getRequest() {
		return request;
	}

	/** 
	 * Set the request.
	 * @param request The request to set
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/** 
	 * Returns the pwd.
	 * @return String
	 */
	public String getPwd() {
		return pwd;
	}

	/** 
	 * Set the pwd.
	 * @param pwd The pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return Returns the server_url.
	 */
	public String getServer_url() {
		return this.server_url;
	}

	/**
	 * @param server_url The server_url to set.
	 */
	public void setServer_url(String server_url) {
		this.server_url = server_url;
	}
	
	

}

