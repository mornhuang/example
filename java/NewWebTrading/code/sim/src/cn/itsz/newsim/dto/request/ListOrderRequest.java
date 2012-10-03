package cn.itsz.newsim.dto.request;

import cn.itsz.newsim.dto.BaseDTO;

public class ListOrderRequest extends BaseDTO {
	private String loginId;
	private int pageSize;
	private int pageNo;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
}
