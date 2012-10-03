package cn.itsz.newsim.dto.request;

import cn.itsz.newsim.dto.BaseDTO;

public class OrderDetailRequest extends BaseDTO {
	private String loginId;
	private String orderId;

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
