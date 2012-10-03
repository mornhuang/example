package cn.itsz.newsim.dto.response;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;

public class LoginResponse extends ResultMessage {
	private LoginResponseEntity loginResponseEntity;

	public LoginResponseEntity getLoginResponseEntity() {
		return loginResponseEntity;
	}

	public void setLoginResponseEntity(LoginResponseEntity loginResponseEntity) {
		this.loginResponseEntity = loginResponseEntity;
	}
}
