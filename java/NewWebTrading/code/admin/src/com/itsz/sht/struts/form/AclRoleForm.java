package com.itsz.sht.struts.form;

import java.util.Date;
import org.apache.struts.validator.ValidatorForm;

public class AclRoleForm extends ValidatorForm {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String descr;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;
	private String message;
	private String[] functionId;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getInitBy() {
		return initBy;
	}
	public void setInitBy(String initBy) {
		this.initBy = initBy;
	}
	public String[] getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String[] functionId) {
		this.functionId = functionId;
	}
	public Date getInitDate() {
		return initDate;
	}
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
