package cn.itsz.newsim.view.form;

public class LoginForm extends BaseForm {
	private static final long serialVersionUID = 1L;

	private String loginId;
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
}
