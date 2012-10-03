package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: EnquireRTQCharResponse.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal
 * @File:EnquireRTQCharResponse.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-10-21
 */
public class EnquireRTQCharResponse  extends AbstractResponseModel{
	private String stock_code;
	private String char_url;
	private String showRtqButton;
	public String getStock_code() {
		return stock_code;
	}
	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}
	public String getChar_url() {
		return char_url;
	}
	public void setChar_url(String char_url) {
		this.char_url = char_url;
	}
	public String getShowRtqButton() {
		return showRtqButton;
	}
	public void setShowRtqButton(String showRtqButton) {
		this.showRtqButton = showRtqButton;
	}

}
