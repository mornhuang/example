package cn.itsz.newsim.dto.response;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.entity.AdminLoginResponseEntity;

public class AdminLoginResponse extends ResultMessage {
	private AdminLoginResponseEntity adminLoginResponseEntity;

	public AdminLoginResponseEntity getAdminLoginResponseEntity() {
		return adminLoginResponseEntity;
	}

	public void setAdminLoginResponseEntity(
			AdminLoginResponseEntity adminLoginResponseEntity) {
		this.adminLoginResponseEntity = adminLoginResponseEntity;
	}

}
