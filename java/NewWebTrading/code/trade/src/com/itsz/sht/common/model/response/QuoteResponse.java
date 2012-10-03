/**
 * <p>Title:Channels Server for STT2.5</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common.model.response;


import com.itsz.sht.common.Constants;
import com.taifook.mcs.core.beans.msg.MCSMessage;

public class QuoteResponse extends MCSMessage {
    private String instrCode;
    private String instrName;
    private String lotSize;
    private String spread;
    private String bestBidPrice;
    private String bestAskPrice;
    private String nominalPrice;
    private String highPrice;
    private String lowPrice;
    private String openPrice;
    private String prevClosingPrice;
    private String totalTradeVolume;
    private String tradeAmount;
    private String priceChange;
    private String priceChangePerc;
    private String postTime;
    private int stockStatus;
    
    private String currency;//货币
    private String latestTradedVol;//�?后交易量
    private String latestTradedPrice;//�?后交易价�?
    private String latestTradedTime;//�?后交易时�?
    
    private String pe;//市盈�?
    private String yield;//收益�?
    private String yearHigh;//年最�?
    private String yearLow;//年最�?
    private String marketCapital;//市�??
    
    private String premium;//溢价百分�?
    private String gearing;//杠杆比率
    private String executePrice;//行使�?
    private String conversionRatio;//转换比率
    private String expiryDate;//到期�?
    private String quotType;//类型
    
    private String logTime;



    public QuoteResponse() {
        super.setMessageId(Constants.MsgID_Quote);
    }
    
    /**
     * @return Returns the bestAskPrice.
     */
    public String getBestAskPrice() {
        return bestAskPrice;
    }
    /**
     * @param bestAskPrice The bestAskPrice to set.
     */
    public void setBestAskPrice(String bestAskPrice) {
        this.bestAskPrice = bestAskPrice;
    }
    /**
     * @return Returns the bestBidPrice.
     */
    public String getBestBidPrice() {
        return bestBidPrice;
    }
    /**
     * @param bestBidPrice The bestBidPrice to set.
     */
    public void setBestBidPrice(String bestBidPrice) {
        this.bestBidPrice = bestBidPrice;
    }
    /**
     * @return Returns the instrCode.
     */
    public String getInstrCode() {
        return instrCode;
    }
    /**
     * @param instrCode The instrCode to set.
     */
    public void setInstrCode(String instrCode) {
        this.instrCode = instrCode;
    }
    /**
     * @return Returns the instrName.
     */
    public String getInstrName() {
        return instrName;
    }
    /**
     * @param instrName The instrName to set.
     */
    public void setInstrName(String instrName) {
        this.instrName = instrName;
    }
    /**
     * @return Returns the lotSize.
     */
    public String getLotSize() {
        return lotSize;
    }
    /**
     * @param lotSize The lotSize to set.
     */
    public void setLotSize(String lotSize) {
        this.lotSize = lotSize;
    }
    /**
     * @return Returns the nominalPrice.
     */
    public String getNominalPrice() {
        return nominalPrice;
    }
    /**
     * @param nominalPrice The nominalPrice to set.
     */
    public void setNominalPrice(String nominalPrice) {
        this.nominalPrice = nominalPrice;
    }
    /**
     * @return Returns the highPrice.
     */
    public String getHighPrice() {
        return highPrice;
    }
    /**
     * @param highPrice The highPrice to set.
     */
    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }
    /**
     * @return Returns the lowPrice.
     */
    public String getLowPrice() {
        return lowPrice;
    }
    /**
     * @param lowPrice The lowPrice to set.
     */
    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }
    /**
     * @return Returns the openPrice.
     */
    public String getOpenPrice() {
        return openPrice;
    }
    /**
     * @param openPrice The openPrice to set.
     */
    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }
    /**
     * @return Returns the postTime.
     */
    public String getPostTime() {
        return postTime;
    }
    /**
     * @param postTime The postTime to set.
     */
    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }
    /**
     * @return Returns the prevClosingPrice.
     */
    public String getPrevClosingPrice() {
        return prevClosingPrice;
    }
    /**
     * @param prevClosingPrice The prevClosingPrice to set.
     */
    public void setPrevClosingPrice(String prevClosingPrice) {
        this.prevClosingPrice = prevClosingPrice;
    }
    /**
     * @return Returns the priceChange.
     */
    public String getPriceChange() {
        return priceChange;
    }
    /**
     * @param priceChange The priceChange to set.
     */
    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }
    /**
     * @return Returns the priceChangePerc.
     */
    public String getPriceChangePerc() {
        return priceChangePerc;
    }
    /**
     * @param priceChangePerc The priceChangePerc to set.
     */
    public void setPriceChangePerc(String priceChangePerc) {
        this.priceChangePerc = priceChangePerc;
    }
    /**
     * @return Returns the spread.
     */
    public String getSpread() {
        return spread;
    }
    /**
     * @param spread The spread to set.
     */
    public void setSpread(String spread) {
        this.spread = spread;
    }
    /**
     * @return Returns the totalTradeVolume.
     */
    public String getTotalTradeVolume() {
        return totalTradeVolume;
    }
    /**
     * @param totalTradeVolume The totalTradeVolume to set.
     */
    public void setTotalTradeVolume(String totalTradeVolume) {
        this.totalTradeVolume = totalTradeVolume;
    }
    /**
     * @return Returns the tradeAmount.
     */
    public String getTradeAmount() {
        return tradeAmount;
    }
    /**
     * @param tradeAmount The tradeAmount to set.
     */
    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }
    
    /**
     * @return Returns the stockStatus.
     */
    public int getStockStatus() {
        return stockStatus;
    }
    /**
     * @param stockStatus The stockStatus to set.
     */
    public void setStockStatus(int stockStatus) {
        this.stockStatus = stockStatus;
    }

	public String getConversionRatio() {
		return conversionRatio;
	}

	public void setConversionRatio(String conversionRatio) {
		this.conversionRatio = conversionRatio;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getExecutePrice() {
		return executePrice;
	}

	public void setExecutePrice(String executePrice) {
		this.executePrice = executePrice;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getGearing() {
		return gearing;
	}

	public void setGearing(String gearing) {
		this.gearing = gearing;
	}

	public String getLatestTradedPrice() {
		return latestTradedPrice;
	}

	public void setLatestTradedPrice(String latestTradedPrice) {
		this.latestTradedPrice = latestTradedPrice;
	}

	public String getLatestTradedTime() {
		return latestTradedTime;
	}

	public void setLatestTradedTime(String latestTradedTime) {
		this.latestTradedTime = latestTradedTime;
	}

	public String getLatestTradedVol() {
		return latestTradedVol;
	}

	public void setLatestTradedVol(String latestTradedVol) {
		this.latestTradedVol = latestTradedVol;
	}

	public String getMarketCapital() {
		return marketCapital;
	}

	public void setMarketCapital(String marketCapital) {
		this.marketCapital = marketCapital;
	}

	public String getPe() {
		return pe;
	}

	public void setPe(String pe) {
		this.pe = pe;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(String yearHigh) {
		this.yearHigh = yearHigh;
	}

	public String getYearLow() {
		return yearLow;
	}

	public void setYearLow(String yearLow) {
		this.yearLow = yearLow;
	}

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	public String getQuotType() {
		return quotType;
	}

	public void setQuotType(String quotType) {
		this.quotType = quotType;
	}

	/**
	 * @return Returns the logTime.
	 */
	public String getLogTime() {
		return this.logTime;
	}

	/**
	 * @param logTime The logTime to set.
	 */
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	
}
