package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: MarginFinancingListRequestModel.java,v 1.2 2010/12/07 08:38:33 kyzou Exp $
 * @Project:NewWebTrading
 * @File:MarginFinancingListRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-7
 */
public class MarginFinancingListRequestModel extends AbstractRequestModel {
    private String loginId;
    private String marketCode;
    private String instrCode;
    private Integer fromIdx;
    private Integer toIdx;
    private String language;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public Integer getFromIdx() {
		return fromIdx;
	}
	public void setFromIdx(Integer fromIdx) {
		this.fromIdx = fromIdx;
	}
	public Integer getToIdx() {
		return toIdx;
	}
	public void setToIdx(Integer toIdx) {
		this.toIdx = toIdx;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}
