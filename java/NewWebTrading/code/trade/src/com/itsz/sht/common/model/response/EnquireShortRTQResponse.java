package com.itsz.sht.common.model.response;

/**
 * $Id: EnquireShortRTQResponse.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal
 * @File:EnquireShortRTQResponse.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-4-13
 */
public class EnquireShortRTQResponse {
    private String code;
    private String name;
    private String price;
    private String bestBid;
    private String bestAsk;
    private String lotSize;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBestBid() {
		return bestBid;
	}
	public void setBestBid(String bestBid) {
		this.bestBid = bestBid;
	}
	public String getBestAsk() {
		return bestAsk;
	}
	public void setBestAsk(String bestAsk) {
		this.bestAsk = bestAsk;
	}
	public String getLotSize() {
		return lotSize;
	}
	public void setLotSize(String lotSize) {
		this.lotSize = lotSize;
	}

}
