package cn.itsz.newsim.view.form;

import org.apache.struts.action.ActionForm;

public class AdminChangePwdForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String uname;
	private String upass;
	private String newUpass;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getNewUpass() {
		return newUpass;
	}
	public void setNewUpass(String newUpass) {
		this.newUpass = newUpass;
	}
}
