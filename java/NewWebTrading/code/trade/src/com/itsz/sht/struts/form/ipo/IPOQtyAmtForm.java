/*
 *
 */
package com.itsz.sht.struts.form.ipo;

import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.Globals;

import com.itsz.sht.struts.form.ITSZForm;

/**
 * @struts.form name="IPOQtyAmtForm"
 */

public class IPOQtyAmtForm extends ITSZForm {

    private long ipoMasterId;
    private int  shareQty;
    private BigDecimal amount;


    public IPOQtyAmtForm() {
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        ActionErrors ers = new ActionErrors();
        ers = commonCheck(mapping, request);
        return ers;
    }
    protected ActionErrors commonCheck(ActionMapping mapping,
            HttpServletRequest request) {

        Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
        MessageResources mr = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
        mr.setReturnNull(true);

        ActionErrors errors = new ActionErrors();
       /*
        if (quantity == null || "".equals(quantity)) {
            String key = mr.getMessage(locale, "label.general.quantity");
            errors.add("quantity", new WebActionError("errors.required", new Object[]{key}));
        }
        */
        return errors;
    }

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

