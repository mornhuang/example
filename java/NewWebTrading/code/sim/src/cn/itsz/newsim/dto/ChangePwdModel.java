package cn.itsz.newsim.dto;

public class ChangePwdModel {
	private String loginId;
	private String oldPassWrod;
	private String passWord;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getOldPassWrod() {
		return oldPassWrod;
	}
	public void setOldPassWrod(String oldPassWrod) {
		this.oldPassWrod = oldPassWrod;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
