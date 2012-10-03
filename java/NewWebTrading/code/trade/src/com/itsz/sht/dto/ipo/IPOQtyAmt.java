
package com.itsz.sht.dto.ipo;

import java.math.BigDecimal;

import java.io.Serializable;


public class IPOQtyAmt implements Serializable {

    private static final long serialVersionUID = 1L;

    private IPOQtyAmtKey id;

    private BigDecimal amount;
    private String amount_dsply;

    public IPOQtyAmt()
    {
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public IPOQtyAmtKey getId() {
        return id;
    }
    public void setId(IPOQtyAmtKey id) {
        this.id = id;
    }
    public int hashCode() {
      return id == null ? 0 : id.hashCode();
  }
    public String getAmount_dsply() {
        return amount_dsply;
    }
    public void setAmount_dsply(String amount_dsply) {
        this.amount_dsply = amount_dsply;
    }

}

