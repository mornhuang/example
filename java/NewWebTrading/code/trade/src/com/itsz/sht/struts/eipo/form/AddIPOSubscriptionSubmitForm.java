package com.itsz.sht.struts.eipo.form;

import org.apache.struts.validator.ValidatorForm;

import com.itsz.sht.struts.form.ITSZForm;

/**
 * @struts.form name = "addIpoSubscriptionSubmitForm"
 * 
 * 
 */


public class AddIPOSubscriptionSubmitForm extends ITSZForm {

	private static final long serialVersionUID = 1L;
	private String password;
	private boolean email;
	private boolean phone;
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
    /**
     * @struts.validator type="required" msgkey="errors.required" arg0="label.logonForm.password"
     */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public boolean isEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(boolean email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	public boolean isPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(boolean phone) {
		this.phone = phone;
	}

}
