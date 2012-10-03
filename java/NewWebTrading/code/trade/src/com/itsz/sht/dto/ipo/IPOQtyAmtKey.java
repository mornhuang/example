
package com.itsz.sht.dto.ipo;

import java.util.Date;
import java.math.BigDecimal;

import java.io.Serializable;


public class IPOQtyAmtKey implements Serializable {


    private static final long serialVersionUID = 1L;
    private volatile int hashValue = 0;

    public IPOQtyAmtKey()
    {
    }

    public IPOQtyAmtKey(Long ipoMasterId, int shareQty){
        this.ipoMasterId = ipoMasterId;
        this.shareQty = shareQty;
    }


    private Long ipoMasterId;
    private int shareQty;
    private String shareQty_dsply;

    public int getShareQty() {
        return shareQty;
    }
    public void setShareQty(int shareQty) {
        hashValue = 0;
        this.shareQty = shareQty;
    }
    public Long getIpoMasterId() {
        return ipoMasterId;
    }
    public void setIpoMasterId(Long ipoMasterId) {
        hashValue = 0;
        this.ipoMasterId = ipoMasterId;
    }

    public boolean equals(Object rhs)
    {
        if (rhs == null)
            return false;
        if (! (rhs instanceof IPOQtyAmtKey))
            return false;
        IPOQtyAmtKey that = (IPOQtyAmtKey) rhs;
        if (this.getIpoMasterId() != null && that.getIpoMasterId() != null)
        {
            if (! this.getIpoMasterId().equals(that.getIpoMasterId()))
            {
                return false;
            }
        }
        if (Integer.toString(this.getShareQty()) != null && Integer.toString(that.getShareQty()) != null)
        {
            if (! Integer.toString(this.getShareQty()).equals(Integer.toString(that.getShareQty())))
            {
                return false;
            }
        }
        return true;
    }
    public int hashCode()
    {
        if (this.hashValue == 0)
        {
            int result = 17;
            int ipoMasterIdValue = this.getIpoMasterId() == null ? 0 : this.getIpoMasterId().hashCode();
            result = result * 37 + ipoMasterIdValue;
            int shareQtyValue = Integer.toString(this.getShareQty()) == null ? 0 : Integer.toString(this.getShareQty()).hashCode();
            result = result * 37 + shareQtyValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
    public String getShareQty_dsply() {
        return shareQty_dsply;
    }
    public void setShareQty_dsply(String shareQty_dsply) {
        this.shareQty_dsply = shareQty_dsply;
    }


}

