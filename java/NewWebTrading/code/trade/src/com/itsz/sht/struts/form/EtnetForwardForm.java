//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.itsz.sht.struts.form;

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
	private String request = "login";

	/** pwd property */
	private String pwd;
	
	private String url;
	private String lang;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

}

