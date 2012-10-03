package cn.itsz.newsim.dto.request;

import java.util.Vector;
import cn.itsz.newsim.dto.BaseDTO;

/**
 * $Id: EnquireRTQRequest.java,v 1.2 2011/03/04 06:11:43 zxfan Exp $
 * @Project:portal
 * @File:EnquireShortRTQRequest.java
 * @Description:
 * @Author:zxfan
 * @Date:2011-3-2
 */
public class EnquireRTQRequest extends BaseDTO {
	private Vector<String> instrCode;
	private String quoteType;
	private String loginId;
	
	public Vector<String> getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(Vector<String> instrCode) {
		this.instrCode = instrCode;
	}
	public String getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
