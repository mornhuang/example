package com.itsz.sht.common.model.response.listorder;

import java.math.BigDecimal;

public class TradeInfoModel {
	  
	    private BigDecimal tradePrice;
	    private BigDecimal tradeQuantity;
	    private String BrokerNum;
	    
		public String getBrokerNum() {
			return BrokerNum;
		}
		public void setBrokerNum(String brokerNum) {
			BrokerNum = brokerNum;
		}
		public BigDecimal getTradePrice() {
			return tradePrice;
		}
		public void setTradePrice(BigDecimal tradePrice) {
			this.tradePrice = tradePrice;
		}
		public BigDecimal getTradeQuantity() {
			return tradeQuantity;
		}
		public void setTradeQuantity(BigDecimal tradeQuantity) {
			this.tradeQuantity = tradeQuantity;
		}

}
