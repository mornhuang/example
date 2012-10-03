package cn.itsz.newsim.dto.request;

import cn.itsz.newsim.dto.BaseDTO;

public class LoginRequest extends BaseDTO {
	private String loginId;
	private String password;

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
