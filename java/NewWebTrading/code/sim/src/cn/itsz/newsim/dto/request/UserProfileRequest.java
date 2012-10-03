package cn.itsz.newsim.dto.request;

import cn.itsz.newsim.dto.BaseDTO;

public class UserProfileRequest extends BaseDTO {
	
	private String loginId;
	private String username;
	private int pageSize = 5;
	private int pageNo = 1;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
}
