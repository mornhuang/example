package cn.itsz.newsim.dto.request;

import cn.itsz.newsim.dto.BaseDTO;

public class TradeListRequest extends BaseDTO {
	private String loginId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
