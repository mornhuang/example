package com.itsz.sht.struts.form;

import java.util.Date;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.itsz.sht.dao.hibernate.model.AclRole;

public class AclUserProfileForm extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lognId;
	private String roleId;
	private String deptNme;
	private String usrNme;
	private String pwd;
	private String emailAddr;
	private String status;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;
	private List<AclRole> roleList;
	private List<String> statusList;
	
	private String newpwd;
	
	public List<AclRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<AclRole> roleList) {
		this.roleList = roleList;
	}
	public String getLognId() {
		return lognId;
	}
	public void setLognId(String lognId) {
		this.lognId = lognId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getDeptNme() {
		return deptNme;
	}
	public void setDeptNme(String deptNme) {
		this.deptNme = deptNme;
	}
	public String getUsrNme() {
		return usrNme;
	}
	public void setUsrNme(String usrNme) {
		this.usrNme = usrNme;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInitBy() {
		return initBy;
	}
	public void setInitBy(String initBy) {
		this.initBy = initBy;
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
	public List<String> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

}
