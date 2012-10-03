package cn.itsz.newsim.dto.response.entity;

import cn.itsz.newsim.dto.BaseDTO;

public class AdminLoginResponseEntity extends BaseDTO {
	private String uname;
	private String upass;
	
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
}
