package cn.itsz.newsim.view.form;

public class ChangePwdForm extends BaseForm {

	private static final long serialVersionUID = 1L;
	private String loginId;
	private String oldPassword;
	private String passWord;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	
	
}
