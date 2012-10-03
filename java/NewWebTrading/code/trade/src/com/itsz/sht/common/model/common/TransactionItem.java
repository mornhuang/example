package com.itsz.sht.common.model.common;

import java.math.BigDecimal;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.FieldUtil;
import com.taifook.mcs.core.beans.msg.TradeInfo;

/**
 * 
 * $Id: TransactionItem.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:TransactionItem.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class TransactionItem implements RawEntity {

    private static final long serialVersionUID = 7893614273622920569L;
    public static final String BROKER_BASED = "B";
    public static final String PRICE_BASED = "P";

    private BigDecimal tradedPrice;
    private BigDecimal tradedQuantity;
    private String brokerId;
    private String brokerName;
    private String groupBased;
    private String tradeSide;

    public TransactionItem(TradeInfo t) {
        if (t != null) {
            tradedPrice = t.getTradePrice();
            tradedQuantity = t.getTradeQuantity();
            tradeSide = t.getTradeSide();
            if (Consts.Order.Side.S.equals(t.getTradeSide())) {
                brokerId = t.getBuyerBrokerNum();
            }
            else {
                brokerId = t.getSellerBrokerNum();
            }
        }
    }
    public void setTradedPrice(BigDecimal tradedPrice) {
        this.tradedPrice = tradedPrice;
    }
    public BigDecimal getTradedPrice() {
        return tradedPrice;
    }
    public String getFormattedTradedPrice() {
        String s = null;
        if (tradedPrice != null) {
            s = FieldUtil.formatDouble(new Double(tradedPrice.doubleValue()), "#,###.###");
        }
        return s;
    }
    public void setTradedQuantity(BigDecimal tradedQuantity) {
        this.tradedQuantity = tradedQuantity;
    }
    public BigDecimal getTradedQuantity() {
        return tradedQuantity;
    }
    public String getFormattedTradedQuantity() {
        String s = null;
        if (tradedQuantity != null) {
            s = FieldUtil.formatDouble(new Double(tradedQuantity.doubleValue()), "#,###");
        }
        return s;
    }
    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }
    public String getBrokerId() {
        return brokerId;
    }
    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }
    public String getBrokerName() {
        return brokerName;
    }
    public void setGroupBased(String groupBased) {
        this.groupBased = groupBased;
    }
    public String getGroupBased() {
        return groupBased;
    }
    public void setTradeSide(String tradeSide) {
        this.tradeSide = tradeSide;
    }
    public String getTradeSide() {
        return tradeSide;
    }
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null && obj instanceof TransactionItem) {
            TransactionItem tt = (TransactionItem) obj;
            if (BROKER_BASED.equals(getGroupBased())) {
                if (getBrokerId() != null) {
                    if (getBrokerId().equals(tt.getBrokerId()) && 
                        getTradedPrice().equals(tt.getTradedPrice())) {
                        result = true;
                    }
                }
            }
            else if (PRICE_BASED.equals(getGroupBased())) {
                if (getTradedPrice() != null) {
                    if (getTradedPrice().equals(tt.getTradedPrice())) {
                        result = true;
                    }
                }
            } else {
                result = false;
            }
        }
        return result;
    }
    public void reset() {
    } 
}
