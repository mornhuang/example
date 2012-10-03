package cn.itsz.newsim.dto.response.entity;

import cn.itsz.newsim.dto.BaseDTO;

public class RegResponseEntity extends BaseDTO {
	private String RegStatus;
	
	public String getRegStatus() {
		return RegStatus;
	}

	public void setRegStatus(String regStatus) {
		RegStatus = regStatus;
	}
}
