//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.parameter.forms;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-27-2006
 * 
 * XDoclet definition:
 * @struts.form name="updateParameterOtherForm"
 */
public class ParameterForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	private static final long serialVersionUID = 1L;

	/** key property */
	private String key;

	/** value property */
	private String value;

	/** description property */
	private String description;

	/** updatetime property */
	private String updatetime;

	/** classid property */
	private String classid;
	
	private String dataType;
	
	/** readonly property */
	private String readonly;

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
		this.key=this.key;
	}

	/** 
	 * Returns the key.
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/** 
	 * Set the key.
	 * @param key The key to set
	 */
	public void setKey(String key) {
			this.key = key.trim();
	}

	/** 
	 * Returns the value.
	 * @return String
	 */
	public String getValue() {
		return value;
	}

	/** 
	 * Set the value.
	 * @param value The value to set
	 */
	public void setValue(String value) {
			this.value = value.trim();
	}

	/** 
	 * Returns the description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/** 
	 * Set the description.
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** 
	 * Returns the updatetime.
	 * @return String
	 */
	public String getUpdatetime() {
		return updatetime;
	}

	/** 
	 * Set the updatetime.
	 * @param updatetime The updatetime to set
	 */
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	/** 
	 * Returns the classid.
	 * @return String
	 */
	public String getClassid() {
		return classid;
	}

	/** 
	 * Set the classid.
	 * @param classid The classid to set
	 */
	public void setClassid(String classid) {
		this.classid = classid;
	}

	/** 
	 * Returns the readonly.
	 * @return boolean
	 */
	public String getReadonly() {
		return readonly;
	}

	/** 
	 * Set the readonly.
	 * @param readonly The readonly to set
	 */
	public void setReadonly(String readonly) {
		this.readonly =  readonly;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}	
	
}

