package com.itsz.sht.common.model.common;

import java.util.List;
import com.taifook.mcs.core.beans.msg.TradeListInfo;

/**
 * $Id: TradeDetailEnquiryResult.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:TradeDetailEnquiryResult.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-7-23
 */
public class TradeDetailEnquiryResult {
    private List tradeInfoList;
    private int totalEntry;
    private String accountId;
    private String accountType;

	public TradeDetailEnquiryResult() {
    }

	public TradeDetailEnquiryResult(List tradeInfoList,String accountId,String accountType) {
        this.tradeInfoList = tradeInfoList;
        this.accountId = accountId;
        this.accountType = accountType;
    }
	
    public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

    public void setTradeInfoList(List tradeInfoList) {
        this.tradeInfoList = tradeInfoList;
    }
    public List getTradeInfoList() {
        return tradeInfoList;
    }
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null && obj instanceof java.util.List) {
            List compareList = (List) obj;
            result = true;
            for (int i = 0; i < tradeInfoList.size(); i++) {
            	TradeListInfo oee = (TradeListInfo) tradeInfoList.get(i);
                if (oee != null) {
                    if (!compareList.contains(oee)) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public void setTotalEntry(int totalEntry) {
        this.totalEntry = totalEntry;
    }
    
    public int getTotalEntry() {
    	if (tradeInfoList!= null){
    		totalEntry = tradeInfoList.size();
    	}
        return totalEntry;
    }

}
