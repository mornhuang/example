/*
 *
 */
package com.taifook.adminportal.web.ipo.form;

import java.math.BigDecimal;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * @struts.form name="IPOQtyAmtForm"
 */

public class IPOQtyAmtForm extends ActionForm {



    private long ipoMasterId;
    private int  shareQty;
    private BigDecimal amount;

    public long getIpoMasterId() {
        return ipoMasterId;
    }
    public void setIpoMasterId(long ipoMasterId) {
        this.ipoMasterId = ipoMasterId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public int getShareQty() {
        return shareQty;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setShareQty(int shareQty) {
        this.shareQty = shareQty;
    }

}

