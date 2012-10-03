package com.itsz.sht.common.model.common;

import java.util.List;

/**
 * $Id: TradeListResult.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:TradeListResult.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-7-28
 */
public class TradeListResult {
	private int totalAccount;
	private List tradeDetailEnquiryResults;
	public int getTotalAccount() {
		return totalAccount;
	}
	public void setTotalAccount(int totalAccount) {
		this.totalAccount = totalAccount;
	}
	public List getTradeDetailEnquiryResults() {
		return tradeDetailEnquiryResults;
	}
	public void setTradeDetailEnquiryResults(List tradeDetailEnquiryResults) {
		this.tradeDetailEnquiryResults = tradeDetailEnquiryResults;
	}

}
