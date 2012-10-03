package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: LotSizeResponseModel.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal.head
 * @File:LotSizeResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class LotSizeResponseModel extends AbstractResponseModel {

    private String Code;
    private String Name;
    private String Time;
    private String PClose;
    private String Price;
    private String Open;
    private String High;
    private String Low;
    private String Close;
    private String Turnover;
    private String Change;
    private String PctChange;
    private String Volume;
    private String YearHigh;
    private String YearLow;
    private String Pe;
    private String MarketCap;
    private String LowSpread;
    private String HighSpread;
    private String LotSize;
    private String Gearing;
    private String ExpDate;
    private String ExePrice;
    private String ConvRatio;
    private String Preminum;
    private String Note;
    private String Yield;
    private String Currency;
    private String bestBid;
    private String bestAsk;
    private String latest_traded_time;
    private String latest_traded_vol;
    private String latest_traded_price;
    private String last_update_time;
    private String body_type;
    private String LogTime;
    private int Status;
    
	public String getBestAsk() {
		return bestAsk;
	}
	public void setBestAsk(String bestAsk) {
		this.bestAsk = bestAsk;
	}
	public String getBestBid() {
		return bestBid;
	}
	public void setBestBid(String bestBid) {
		this.bestBid = bestBid;
	}
	public String getBody_type() {
		return body_type;
	}
	public void setBody_type(String body_type) {
		this.body_type = body_type;
	}
	public String getChange() {
		return Change;
	}
	public void setChange(String change) {
		Change = change;
	}
	public String getClose() {
		return Close;
	}
	public void setClose(String close) {
		Close = close;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getConvRatio() {
		return ConvRatio;
	}
	public void setConvRatio(String convRatio) {
		ConvRatio = convRatio;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getExePrice() {
		return ExePrice;
	}
	public void setExePrice(String exePrice) {
		ExePrice = exePrice;
	}
	public String getExpDate() {
		return ExpDate;
	}
	public void setExpDate(String expDate) {
		ExpDate = expDate;
	}
	public String getGearing() {
		return Gearing;
	}
	public void setGearing(String gearing) {
		Gearing = gearing;
	}
	public String getHigh() {
		return High;
	}
	public void setHigh(String high) {
		High = high;
	}
	public String getHighSpread() {
		return HighSpread;
	}
	public void setHighSpread(String highSpread) {
		HighSpread = highSpread;
	}
	public String getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}
	public String getLatest_traded_price() {
		return latest_traded_price;
	}
	public void setLatest_traded_price(String latest_traded_price) {
		this.latest_traded_price = latest_traded_price;
	}
	public String getLatest_traded_time() {
		return latest_traded_time;
	}
	public void setLatest_traded_time(String latest_traded_time) {
		this.latest_traded_time = latest_traded_time;
	}
	public String getLatest_traded_vol() {
		return latest_traded_vol;
	}
	public void setLatest_traded_vol(String latest_traded_vol) {
		this.latest_traded_vol = latest_traded_vol;
	}
	public String getLogTime() {
		return LogTime;
	}
	public void setLogTime(String logTime) {
		LogTime = logTime;
	}
	public String getLotSize() {
		return LotSize;
	}
	public void setLotSize(String lotSize) {
		LotSize = lotSize;
	}
	public String getLow() {
		return Low;
	}
	public void setLow(String low) {
		Low = low;
	}
	public String getLowSpread() {
		return LowSpread;
	}
	public void setLowSpread(String lowSpread) {
		LowSpread = lowSpread;
	}
	public String getMarketCap() {
		return MarketCap;
	}
	public void setMarketCap(String marketCap) {
		MarketCap = marketCap;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String getOpen() {
		return Open;
	}
	public void setOpen(String open) {
		Open = open;
	}
	public String getPClose() {
		return PClose;
	}
	public void setPClose(String close) {
		PClose = close;
	}
	public String getPctChange() {
		return PctChange;
	}
	public void setPctChange(String pctChange) {
		PctChange = pctChange;
	}
	public String getPe() {
		return Pe;
	}
	public void setPe(String pe) {
		Pe = pe;
	}
	public String getPreminum() {
		return Preminum;
	}
	public void setPreminum(String preminum) {
		Preminum = preminum;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getTurnover() {
		return Turnover;
	}
	public void setTurnover(String turnover) {
		Turnover = turnover;
	}
	public String getVolume() {
		return Volume;
	}
	public void setVolume(String volume) {
		Volume = volume;
	}
	public String getYearHigh() {
		return YearHigh;
	}
	public void setYearHigh(String yearHigh) {
		YearHigh = yearHigh;
	}
	public String getYearLow() {
		return YearLow;
	}
	public void setYearLow(String yearLow) {
		YearLow = yearLow;
	}
	public String getYield() {
		return Yield;
	}
	public void setYield(String yield) {
		Yield = yield;
	}

}
